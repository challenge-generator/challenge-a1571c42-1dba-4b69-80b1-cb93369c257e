package com.pragma.optimizacionia.domain.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Transaction(
    @NotNull(message = "Transaction ID cannot be null")
    UUID id,

    @NotBlank(message = "Account ID cannot be blank")
    @Size(max = 50, message = "Account ID must be less than 50 characters")
    String accountId,

    @NotBlank(message = "Transaction type cannot be blank")
    @Size(max = 20, message = "Transaction type must be less than 20 characters")
    String type,

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    BigDecimal amount,

    @NotBlank(message = "Currency cannot be blank")
    @Size(min = 3, max = 3, message = "Currency must be 3 characters")
    String currency,

    @NotNull(message = "Timestamp cannot be null")
    @PastOrPresent(message = "Timestamp cannot be in the future")
    LocalDateTime timestamp,

    @NotBlank(message = "Status cannot be blank")
    @Size(max = 20, message = "Status must be less than 20 characters")
    String status,

    @Size(max = 200, message = "Description must be less than 200 characters")
    String description
) {
    public Transaction {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
    
    public boolean isHighValue() {
        return amount.compareTo(new BigDecimal("10000")) > 0;
    }
}