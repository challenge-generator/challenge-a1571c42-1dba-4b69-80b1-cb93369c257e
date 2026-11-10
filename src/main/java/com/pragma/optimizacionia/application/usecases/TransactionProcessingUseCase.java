package com.pragma.optimizacionia.application.usecases;

import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.domain.ports.in.ProcessTransactionPort;
import com.pragma.optimizacionia.domain.ports.out.AnalyticsServicePort;
import com.pragma.optimizacionia.domain.ports.out.TransactionRepositoryPort;
import com.pragma.optimizacionia.infrastructure.controllers.dto.TransactionResponse;
import com.pragma.optimizacionia.infrastructure.exception.ResourceOptimizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.StreamSupport;

@Service
public class TransactionProcessingUseCase implements ProcessTransactionPort {
    
    private static final Logger logger = LoggerFactory.getLogger(TransactionProcessingUseCase.class);
    private static final int MAX_BATCH_SIZE = 1000;
    private static final int MIN_AMOUNT_THRESHOLD = 10000;
    
    private final TransactionRepositoryPort transactionRepository;
    private final AnalyticsServicePort analyticsService;
    
    public TransactionProcessingUseCase(
            TransactionRepositoryPort transactionRepository,
            AnalyticsServicePort analyticsService) {
        this.transactionRepository = transactionRepository;
        this.analyticsService = analyticsService;
    }
    
    @Override
    public CompletionStage<AnalyticsResult> processTransaction(Transaction transaction) {
        logger.info("Processing transaction: type={}, amount={}", transaction.type(), transaction.amount());
        
        if (transaction == null) {
            logger.error("Transaction cannot be null");
            return CompletableFuture.failedFuture(
                new ResourceOptimizationException("Transaction cannot be null"));
        }
        
        if (transaction.type() == null || transaction.type().isBlank()) {
            logger.error("Transaction type is required");
            return CompletableFuture.failedFuture(
                new ResourceOptimizationException("Transaction type is required"));
        }
        
        if (transaction.amount() == null || transaction.amount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            logger.error("Transaction amount must be positive");
            return CompletableFuture.failedFuture(
                new ResourceOptimizationException("Transaction amount must be positive"));
        }
        
        String transactionId = generateTransactionId();
        
        try {
            transactionRepository.save(transaction);
            logger.debug("Transaction saved with id: {}", transactionId);
        } catch (Exception e) {
            logger.error("Failed to save transaction: {}", e.getMessage());
            return CompletableFuture.failedFuture(
                new ResourceOptimizationException("Failed to save transaction: " + e.getMessage()));
        }
        
        CompletionStage<AnalyticsResult> analyticsResultFuture = analyticsService.analyze(transaction);
        
        return analyticsResultFuture.whenComplete((result, throwable) -> {
            if (throwable != null) {
                logger.error("Analytics service failed for transaction {}: {}", transactionId, throwable.getMessage());
            } else {
                logger.info("Analytics completed for transaction {}: highRisk={}, fraudSuspicious={}",
                    transactionId, result.isHighRisk(), result.isFraudSuspicious());
                
                if (result.isFraudSuspicious()) {
                    logger.warn("FRAUD ALERT: Transaction {} flagged as suspicious", transactionId);
                }
                
                if (result.isHighRisk() && transaction.isHighValue()) {
                    logger.warn("HIGH RISK ALERT: High-value transaction {} requires manual review", transactionId);
                }
            }
        });
    }
    
    @Override
    public CompletionStage<Integer> processBatchTransactions(Iterable<Transaction> transactions) {
        logger.info("Starting batch transaction processing");
        
        if (transactions == null) {
            logger.error("Transaction batch cannot be null");
            return CompletableFuture.failedFuture(
                new ResourceOptimizationException("Transaction batch cannot be null"));
        }
        
        long transactionCount = StreamSupport.stream(transactions.spliterator(), false).count();
        logger.info("Batch size: {} transactions", transactionCount);
        
        if (transactionCount > MAX_BATCH_SIZE) {
            logger.warn("Batch size {} exceeds maximum allowed {}. Processing in chunks.", 
                transactionCount, MAX_BATCH_SIZE);
        }
        
        int[] processedCount = {0};
        int[] failedCount = {0};
        
        return CompletableFuture.supplyAsync(() -> {
            int chunkNumber = 0;
            java.util.Iterator<Transaction> iterator = transactions.iterator();
            
            while (iterator.hasNext()) {
                chunkNumber++;
                int processedInChunk = 0;
                
                for (int i = 0; i < MAX_BATCH_SIZE && iterator.hasNext(); i++) {
                    Transaction transaction = iterator.next();
                    
                    try {
                        CompletionStage<AnalyticsResult> resultFuture = processTransaction(transaction);
                        AnalyticsResult result = resultFuture.toCompletableFuture().get();
                        processedInChunk++;
                        
                        if (result.isHighRisk()) {
                            logger.debug("High-risk transaction detected in chunk {}", chunkNumber);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        logger.error("Processing interrupted for transaction in chunk {}: {}", chunkNumber, e.getMessage());
                        failedCount[0]++;
                    } catch (ExecutionException e) {
                        logger.error("Execution failed for transaction in chunk {}: {}", chunkNumber, e.getMessage());
                        failedCount[0]++;
                    }
                }
                
                processedCount[0] += processedInChunk;
                logger.info("Chunk {} processed: {} transactions", chunkNumber, processedInChunk);
                
                if (chunkNumber % 10 == 0) {
                    logger.info("Progress: {} / {} transactions processed", processedCount[0], transactionCount);
                }
            }
            
            logger.info("Batch processing completed. Total: {}, Failed: {}", processedCount[0], failedCount[0]);
            return processedCount[0];
        });
    }
    
    public TransactionResponse processAndRespond(Transaction transaction) {
        try {
            CompletionStage<AnalyticsResult> resultFuture = processTransaction(transaction);
            AnalyticsResult result = resultFuture.toCompletableFuture().get();
            
            String transactionId = generateTransactionId();
            return TransactionResponse.success(transaction, result, transactionId);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            String transactionId = generateTransactionId();
            logger.error("Processing interrupted: {}", e.getMessage());
            return TransactionResponse.failed(transaction, transactionId, "Processing interrupted: " + e.getMessage());
            
        } catch (ExecutionException e) {
            String transactionId = generateTransactionId();
            logger.error("Processing failed: {}", e.getMessage());
            return TransactionResponse.failed(transaction, transactionId, "Processing failed: " + e.getCause().getMessage());
        }
    }
    
    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
    
    public boolean shouldOptimizeProcessing(Transaction transaction) {
        return transaction.isHighValue() || 
               (transaction.amount() != null && transaction.amount().compareTo(java.math.BigDecimal.valueOf(MIN_AMOUNT_THRESHOLD)) >= 0);
    }
    
    public int getOptimalThreadCount() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        return Math.max(2, availableProcessors - 1);
    }
}