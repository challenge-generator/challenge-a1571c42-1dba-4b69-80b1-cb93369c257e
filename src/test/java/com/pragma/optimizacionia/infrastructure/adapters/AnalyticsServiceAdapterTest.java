package com.pragma.optimizacionia.infrastructure.adapters;

import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.infrastructure.exception.ResourceOptimizationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para AnalyticsServiceAdapter")
class AnalyticsServiceAdapterTest {

    private static final String ANALYTICS_API_URL = "http://analytics-service:8080/api/v1/analyze";

    @Mock
    private RestTemplate restTemplate;

    private AnalyticsServiceAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new AnalyticsServiceAdapter(restTemplate);
    }

    @Test
    @DisplayName("Debe retornar análisis exitoso cuando el servicio responde correctamente")
    void shouldReturnAnalysisWhenServiceRespondsSuccessfully() {
        Transaction transaction = new Transaction(
            "TXN100", "transfer", new BigDecimal("15000.00"),
            "ACC100", "ACC200", LocalDateTime.now(), "PENDING"
        );

        Map<String, Object> apiResponse = Map.of(
            "highRisk", true,
            "fraudSuspicious", false,
            "riskScore", 0.75
        );

        when(restTemplate.postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        )).thenReturn(new ResponseEntity<>(apiResponse, HttpStatus.OK));

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(transaction);

        AnalyticsResult analyticsResult = result.toCompletableFuture().join();

        assertNotNull(analyticsResult);
        assertTrue(analyticsResult.isHighRisk());
        assertFalse(analyticsResult.isFraudSuspicious());
        assertEquals(0.75, analyticsResult.riskScore(), 0.01);

        verify(restTemplate, times(1)).postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        );
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el servicio de analíticas retorna error")
    void shouldThrowExceptionWhenServiceReturnsError() {
        Transaction transaction = new Transaction(
            "TXN101", "payment", new BigDecimal("5000.00"),
            "ACC300", "ACC400", LocalDateTime.now(), "PENDING"
        );

        when(restTemplate.postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        )).thenReturn(new ResponseEntity<>(Map.of(), HttpStatus.INTERNAL_SERVER_ERROR));

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(transaction);

        assertThrows(ResourceOptimizationException.class, () -> result.toCompletableFuture().join());
    }

    @Test
    @DisplayName("Debe manejar respuestas con valores nulos correctamente")
    void shouldHandleNullValuesInResponse() {
        Transaction transaction = new Transaction(
            "TXN102", "deposit", new BigDecimal("8000.00"),
            "ACC500", "ACC600", LocalDateTime.now(), "PENDING"
        );

        Map<String, Object> apiResponse = Map.of(
            "highRisk", (Object) null,
            "fraudSuspicious", null,
            "riskScore", null
        );

        when(restTemplate.postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        )).thenReturn(new ResponseEntity<>(apiResponse, HttpStatus.OK));

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(transaction);

        AnalyticsResult analyticsResult = result.toCompletableFuture().join();

        assertNotNull(analyticsResult);
        assertFalse(analyticsResult.isHighRisk());
        assertFalse(analyticsResult.isFraudSuspicious());
    }

    @Test
    @DisplayName("Debe transformar correctamente transacciones de alto riesgo")
    void shouldTransformHighRiskTransactionsCorrectly() {
        Transaction highRiskTransaction = new Transaction(
            "TXN103", "wire_transfer", new BigDecimal("100000.00"),
            "ACC700", "ACC800", LocalDateTime.now(), "PENDING"
        );

        Map<String, Object> apiResponse = Map.of(
            "highRisk", true,
            "fraudSuspicious", true,
            "riskScore", 0.95
        );

        when(restTemplate.postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        )).thenReturn(new ResponseEntity<>(apiResponse, HttpStatus.OK));

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(highRiskTransaction);

        AnalyticsResult analyticsResult = result.toCompletableFuture().join();

        assertTrue(analyticsResult.isHighRisk());
        assertTrue(analyticsResult.isFraudSuspicious());
        assertTrue(analyticsResult.riskScore() > 0.9);
    }

    @Test
    @DisplayName("Debe manejar excepción de conexión correctamente")
    void shouldHandleConnectionException() {
        Transaction transaction = new Transaction(
            "TXN104", "transfer", new BigDecimal("2000.00"),
            "ACC900", "ACC901", LocalDateTime.now(), "PENDING"
        );

        when(restTemplate.postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        )).thenThrow(new ResourceOptimizationException("Error de conexión con el servicio de analíticas"));

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(transaction);

        assertThrows(ResourceOptimizationException.class, () -> result.toCompletableFuture().join());
    }

    @Test
    @DisplayName("Debe mapear correctamente el campo fraudSuspicious del API")
    void shouldMapFraudSuspiciousFieldCorrectly() {
        Transaction transaction = new Transaction(
            "TXN105", "payment", new BigDecimal("25000.00"),
            "ACC902", "ACC903", LocalDateTime.now(), "PENDING"
        );

        Map<String, Object> apiResponse = Map.of(
            "highRisk", false,
            "fraudSuspicious", true,
            "riskScore", 0.45
        );

        when(restTemplate.postForEntity(
            eq(ANALYTICS_API_URL),
            any(Transaction.class),
            eq(Map.class)
        )).thenReturn(new ResponseEntity<>(apiResponse, HttpStatus.OK));

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(transaction);

        AnalyticsResult analyticsResult = result.toCompletableFuture().join();

        assertFalse(analyticsResult.isHighRisk());
        assertTrue(analyticsResult.isFraudSuspicious());
    }

    @Test
    @DisplayName("Debe rechazar transacciones con monto cero")
    void shouldRejectTransactionsWithZeroAmount() {
        Transaction zeroAmountTransaction = new Transaction(
            "TXN106", "payment", BigDecimal.ZERO,
            "ACC904", "ACC905", LocalDateTime.now(), "PENDING"
        );

        CompletionStage<AnalyticsResult> result = adapter.analyzeTransaction(zeroAmountTransaction);

        assertThrows(Exception.class, () -> result.toCompletableFuture().join());
        verify(restTemplate, never()).postForEntity(anyString(), any(), any(Class.class));
    }
}