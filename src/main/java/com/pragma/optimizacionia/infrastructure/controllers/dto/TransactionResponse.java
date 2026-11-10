package com.pragma.optimizacionia.infrastructure.controllers.dto;

import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

public record TransactionResponse(
    @NotNull
    private final String transactionId,
    
    @NotNull
    private final String transactionType,
    
    @NotNull
    private final BigDecimal amount,
    
    @NotNull
    private final Instant processedAt,
    
    @NotNull
    private final boolean isHighValue,
    
    @NotNull
    private final boolean isHighRisk,
    
    @NotNull
    private final boolean isFraudSuspicious,
    
    @NotNull
    private final String status,
    
    private final String message
) {
    public static TransactionResponse fromDomain(Transaction transaction, AnalyticsResult analyticsResult, String transactionId, String status, String message) {
        return new TransactionResponse(
            transactionId,
            transaction.type(),
            transaction.amount(),
            Instant.now(),
            transaction.isHighValue(),
            analyticsResult.isHighRisk(),
            analyticsResult.isFraudSuspicious(),
            status,
            message
        );
    }
    
    public static TransactionResponse success(Transaction transaction, AnalyticsResult analyticsResult, String transactionId) {
        return fromDomain(transaction, analyticsResult, transactionId, "SUCCESS", "Transaction processed successfully");
    }
    
    public static TransactionResponse failed(Transaction transaction, String transactionId, String errorMessage) {
        return fromDomain(
            transaction, 
            new AnalyticsResult(false, false), 
            transactionId, 
            "FAILED", 
            errorMessage
        );
    }
    
    public static TransactionResponse pending(Transaction transaction, String transactionId) {
        return fromDomain(
            transaction, 
            new AnalyticsResult(false, false), 
            transactionId, 
            "PENDING", 
            "Transaction is being processed"
        );
    }
    
    public String getTransactionId() {
        return this.transactionId;
    }
    
    public String getTransactionType() {
        return this.transactionType;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public Instant getProcessedAt() {
        return this.processedAt;
    }
    
    public boolean isHighValue() {
        return this.isHighValue;
    }
    
    public boolean isHighRisk() {
        return this.isHighRisk;
    }
    
    public boolean isFraudSuspicious() {
        return this.isFraudSuspicious;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public String getMessage() {
        return this.message;
    }
}