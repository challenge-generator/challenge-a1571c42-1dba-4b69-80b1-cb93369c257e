package com.pragma.optimizacionia.infrastructure.controllers.dto;

import com.pragma.optimizacionia.domain.model.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO para recibir solicitudes de procesamiento de transacciones desde el STP.
 * Representa la entrada de datos en la capa de infraestructura que será
 * transformada a una entidad del dominio.
 */
public record TransactionRequest(

        @NotBlank(message = "Transaction ID cannot be blank")
        @Size(max = 50, message = "Transaction ID must be less than 50 characters")
        String transactionId,

        @NotBlank(message = "Transaction type cannot be blank")
        @Size(max = 20, message = "Transaction type must be less than 20 characters")
        String transactionType,

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        @NotBlank(message = "Source account cannot be blank")
        @Size(max = 20, message = "Source account must be less than 20 characters")
        String sourceAccount,

        @NotBlank(message = "Destination account cannot be blank")
        @Size(max = 20, message = "Destination account must be less than 20 characters")
        String destinationAccount,

        @NotNull(message = "Timestamp cannot be null")
        Instant timestamp,

        String clientId,

        String merchantId,

        String location
) {
    /**
     * Transforma este DTO en una entidad del dominio.
     * @return una nueva instancia de Transaction con los datos de esta solicitud
     */
    public Transaction toDomain() {
        return new Transaction(
                transactionId,
                transactionType,
                amount,
                sourceAccount,
                destinationAccount,
                timestamp,
                clientId,
                merchantId,
                location
        );
    }

    /**
     * Valida que la transacción sea de alto valor según el umbral configurado.
     * @param threshold el umbral en centavos para considerar alta valor
     * @return true si el monto supera el umbral
     */
    public boolean isHighValue(long threshold) {
        return amount.multiply(BigDecimal.valueOf(100)).longValue() > threshold;
    }
}