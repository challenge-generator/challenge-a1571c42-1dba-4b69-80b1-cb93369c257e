package com.pragma.optimizacionia.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AnalyticsResult(
    @NotNull(message = "Result ID cannot be null")
    UUID id,

    @NotBlank(message = "Account ID cannot be blank")
    @Size(max = 50, message = "Account ID must be less than 50 characters")
    String accountId,

    @NotNull(message = "Risk score cannot be null")
    BigDecimal riskScore,

    @NotNull(message = "Fraud probability cannot be null")
    BigDecimal fraudProbability,

    @NotNull(message = "Customer behavior score cannot be null")
    BigDecimal customerBehaviorScore,

    @NotNull(message = "Last updated cannot be null")
    LocalDateTime lastUpdated,

    @Size(max = 500, message = "Additional info must be less than 500 characters")
    String additionalInfo
) {
    public AnalyticsResult {
        if (riskScore.compareTo(BigDecimal.ZERO) < 0 || riskScore.compareTo(BigDecimal.TEN) > 0) {
            throw new IllegalArgumentException("Risk score must be between 0 and 10");
        }
        if (fraudProbability.compareTo(BigDecimal.ZERO) < 0 || fraudProbability.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("Fraud probability must be between 0 and 1");
        }
        if (customerBehaviorScore.compareTo(BigDecimal.ZERO) < 0 || customerBehaviorScore.compareTo(BigDecimal.TEN) > 0) {
            throw new IllegalArgumentException("Customer behavior score must be between 0 and 10");
        }
    }
    
    public boolean isHighRisk() {
        return riskScore.compareTo(new BigDecimal("7")) > 0;
    }
    
    public boolean isFraudSuspicious() {
        return fraudProbability.compareTo(new BigDecimal("0.8")) > 0;
    }
}