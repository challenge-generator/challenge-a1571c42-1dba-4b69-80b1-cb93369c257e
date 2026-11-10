package com.pragma.optimizacionia.application.usecases;

import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.domain.ports.out.AnalyticsServicePort;
import com.pragma.optimizacionia.domain.ports.out.TransactionRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para TransactionProcessingUseCase")
class TransactionProcessingUseCaseTest {

    @Mock
    private TransactionRepositoryPort transactionRepositoryPort;

    @Mock
    private AnalyticsServicePort analyticsServicePort;

    private TransactionProcessingUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new TransactionProcessingUseCase(transactionRepositoryPort, analyticsServicePort);
    }

    @Test
    @DisplayName("Debe procesar una transacción válida exitosamente")
    void shouldProcessValidTransactionSuccessfully() throws Exception {
        Transaction transaction = new Transaction(
            "TXN001",
            "transfer",
            new BigDecimal("1000.00"),
            "ACC123",
            "ACC456",
            LocalDateTime.now(),
            "PENDING"
        );

        AnalyticsResult mockResult = new AnalyticsResult(true, false, 0.65);
        when(analyticsServicePort.analyzeTransaction(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(mockResult));
        when(transactionRepositoryPort.save(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(transaction));

        CompletionStage<AnalyticsResult> result = useCase.processTransaction(transaction);

        AnalyticsResult actualResult = result.toCompletableFuture().get();
        assertNotNull(actualResult);
        assertTrue(actualResult.isHighRisk());
        assertFalse(actualResult.isFraudSuspicious());

        verify(analyticsServicePort, times(1)).analyzeTransaction(any(Transaction.class));
        verify(transactionRepositoryPort, times(1)).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe identificar transacciones de alto valor correctamente")
    void shouldIdentifyHighValueTransactions() throws Exception {
        Transaction highValueTransaction = new Transaction(
            "TXN002",
            "wire_transfer",
            new BigDecimal("50000.00"),
            "ACC789",
            "ACC012",
            LocalDateTime.now(),
            "PENDING"
        );

        assertTrue(highValueTransaction.isHighValue(),
            "Transacción con monto mayor a 10000 debe ser de alto valor");

        AnalyticsResult mockResult = new AnalyticsResult(false, true, 0.92);
        when(analyticsServicePort.analyzeTransaction(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(mockResult));
        when(transactionRepositoryPort.save(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(highValueTransaction));

        CompletionStage<AnalyticsResult> result = useCase.processTransaction(highValueTransaction);

        AnalyticsResult actualResult = result.toCompletableFuture().get();
        assertNotNull(actualResult);
        assertFalse(actualResult.isHighRisk());
        assertTrue(actualResult.isFraudSuspicious());
    }

    @Test
    @DisplayName("Debe procesar lote de transacciones correctamente")
    void shouldProcessBatchTransactions() throws Exception {
        Transaction tx1 = new Transaction(
            "TXN003", "payment", new BigDecimal("500.00"),
            "ACC111", "ACC222", LocalDateTime.now(), "PENDING"
        );
        Transaction tx2 = new Transaction(
            "TXN004", "payment", new BigDecimal("750.00"),
            "ACC333", "ACC444", LocalDateTime.now(), "PENDING"
        );
        List<Transaction> transactions = List.of(tx1, tx2);

        AnalyticsResult result1 = new AnalyticsResult(false, false, 0.10);
        AnalyticsResult result2 = new AnalyticsResult(false, false, 0.15);

        when(analyticsServicePort.analyzeTransaction(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(result1))
            .thenReturn(CompletableFuture.completedFuture(result2));
        when(transactionRepositoryPort.save(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(tx1))
            .thenReturn(CompletableFuture.completedFuture(tx2));

        CompletionStage<Integer> result = useCase.processBatchTransactions(transactions);

        Integer processedCount = result.toCompletableFuture().get();
        assertEquals(2, processedCount, "Debe procesar exactamente 2 transacciones");
        verify(analyticsServicePort, times(2)).analyzeTransaction(any(Transaction.class));
        verify(transactionRepositoryPort, times(2)).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe manejar excepción del servicio de analíticas gracefully")
    void shouldHandleAnalyticsServiceException() {
        Transaction transaction = new Transaction(
            "TXN005", "transfer", new BigDecimal("2000.00"),
            "ACC555", "ACC666", LocalDateTime.now(), "PENDING"
        );

        when(analyticsServicePort.analyzeTransaction(any(Transaction.class)))
            .thenReturn(CompletableFuture.failedFuture(
                new RuntimeException("Servicio de analíticas no disponible")));

        CompletionStage<AnalyticsResult> result = useCase.processTransaction(transaction);

        assertThrows(Exception.class, () -> result.toCompletableFuture().get());
        verify(transactionRepositoryPort, never()).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe guardar transacción antes de invocar análisis")
    void shouldSaveTransactionBeforeCallingAnalysis() throws Exception {
        Transaction transaction = new Transaction(
            "TXN006", "deposit", new BigDecimal("3000.00"),
            "ACC777", "ACC888", LocalDateTime.now(), "PENDING"
        );

        AnalyticsResult mockResult = new AnalyticsResult(true, false, 0.50);
        when(analyticsServicePort.analyzeTransaction(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(mockResult));
        when(transactionRepositoryPort.save(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(transaction));

        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);

        useCase.processTransaction(transaction).toCompletableFuture().get();

        verify(transactionRepositoryPort, times(1)).save(transactionCaptor.capture());
        verify(analyticsServicePort, times(1)).analyzeTransaction(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe procesar transacciones pequeñas correctamente")
    void shouldProcessSmallTransactions() throws Exception {
        Transaction smallTransaction = new Transaction(
            "TXN007", "payment", new BigDecimal("50.00"),
            "ACC999", "ACC000", LocalDateTime.now(), "PENDING"
        );

        assertFalse(smallTransaction.isHighValue(),
            "Transacción con monto menor a 10000 no debe ser de alto valor");

        AnalyticsResult mockResult = new AnalyticsResult(false, false, 0.05);
        when(analyticsServicePort.analyzeTransaction(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(mockResult));
        when(transactionRepositoryPort.save(any(Transaction.class)))
            .thenReturn(CompletableFuture.completedFuture(smallTransaction));

        CompletionStage<AnalyticsResult> result = useCase.processTransaction(smallTransaction);

        AnalyticsResult actualResult = result.toCompletableFuture().get();
        assertNotNull(actualResult);
        assertFalse(actualResult.isHighRisk());
        assertFalse(actualResult.isFraudSuspicious());
        assertTrue(actualResult.riskScore() < 0.3);
    }
}