package com.pragma.optimizacionia.infrastructure.adapters;

import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import com.pragma.optimizacionia.domain.ports.out.AnalyticsServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Component
public class AnalyticsServiceAdapter implements AnalyticsServicePort {

    private static final Logger log = LoggerFactory.getLogger(AnalyticsServiceAdapter.class);

    private final RestTemplate restTemplate;
    private final String analyticsServiceUrl;
    private final int analyticsServiceTimeout;

    public AnalyticsServiceAdapter(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${external.analytics.service.url:http://localhost:8081/api/analytics}") String analyticsServiceUrl,
            @Value("${external.analytics.service.timeout:5000}") int analyticsServiceTimeout) {
        this.restTemplate = restTemplateBuilder
                .connectTimeout(Duration.ofMillis(analyticsServiceTimeout))
                .readTimeout(Duration.ofMillis(analyticsServiceTimeout))
                .build();
        this.analyticsServiceUrl = analyticsServiceUrl;
        this.analyticsServiceTimeout = analyticsServiceTimeout;
    }

    @Override
    public CompletionStage<AnalyticsResult> analyzeTransaction(String transactionId, Map<String, Object> transactionData) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.debug("Enviando transacción {} al servicio de analíticas", transactionId);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("transactionId", transactionId);
                requestBody.put("data", transactionData);

                HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

                @SuppressWarnings("unchecked")
                Map<String, Object> response = restTemplate.postForObject(
                        analyticsServiceUrl + "/analyze",
                        request,
                        Map.class
                );

                if (response == null) {
                    log.warn("Respuesta nula del servicio de analíticas para transacción {}", transactionId);
                    return new AnalyticsResult(false, false);
                }

                boolean highRisk = Boolean.TRUE.equals(response.get("highRisk"));
                boolean fraudSuspicious = Boolean.TRUE.equals(response.get("fraudSuspicious"));

                log.debug("Análisis completado para transacción {}: highRisk={}, fraudSuspicious={}",
                        transactionId, highRisk, fraudSuspicious);

                return new AnalyticsResult(highRisk, fraudSuspicious);

            } catch (Exception e) {
                log.error("Error al analizar transacción {}: {}", transactionId, e.getMessage());
                return new AnalyticsResult(false, false);
            }
        });
    }

    @Override
    public CompletionStage<Boolean> healthCheck() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                restTemplate.getForObject(analyticsServiceUrl + "/health", String.class);
                return true;
            } catch (Exception e) {
                log.warn("Health check fallido para servicio de analíticas: {}", e.getMessage());
                return false;
            }
        });
    }
}