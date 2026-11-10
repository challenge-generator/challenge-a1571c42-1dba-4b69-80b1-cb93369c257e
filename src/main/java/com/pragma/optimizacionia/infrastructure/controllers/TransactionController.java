package com.pragma.optimizacionia.infrastructure.controllers;


import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.domain.ports.in.ProcessTransactionPort;
import com.pragma.optimizacionia.infrastructure.controllers.dto.TransactionRequest;
import com.pragma.optimizacionia.infrastructure.controllers.dto.TransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/api/v1/transactions")
@Tag(name = "Transacciones", description = "Endpoints para procesamiento de transacciones bancarias")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    private final ProcessTransactionPort processTransactionPort;

    public TransactionController(ProcessTransactionPort processTransactionPort) {
        this.processTransactionPort = processTransactionPort;
    }

    @PostMapping
    @Operation(summary = "Procesar transacción individual", description = "Procesa una transacción bancaria individual a través del componente de IA")
    public CompletionStage<ResponseEntity<TransactionResponse>> processTransaction(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la transacción a procesar",
                    required = true)
            @RequestBody TransactionRequest request) {

        log.info("Recibida solicitud de procesamiento de transacción: {}", request.transactionId());

        return processTransactionPort.processTransaction(request.toDomain())
                .thenApply(result -> {
                    TransactionResponse response = TransactionResponse.fromDomain(
                            request.transactionId(),
                            request.amount(),
                            request.transactionType(),
                            result
                    );
                    log.info("Transacción {} procesada exitosamente", request.transactionId());
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                })
                .exceptionally(ex -> {
                    log.error("Error al procesar transacción {}: {}", request.transactionId(), ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(TransactionResponse.error(request.transactionId(), ex.getMessage()));
                });
    }

    @PostMapping("/batch")
    @Operation(summary = "Procesar lote de transacciones", description = "Procesa múltiples transacciones bancarias en lote")
    public CompletionStage<ResponseEntity<Map<String, Object>>> processBatchTransactions(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Lista de transacciones a procesar",
                    required = true)
            @RequestBody List<TransactionRequest> requests) {

        log.info("Recibida solicitud de procesamiento en lote: {} transacciones", requests.size());

        List<com.pragma.optimizacionia.domain.model.Transaction> transactions = requests.stream()
                .map(TransactionRequest::toDomain)
                .toList();

        return processTransactionPort.processBatchTransactions(transactions)
                .thenApply(count -> {
                    log.info("Lote de {} transacciones procesado exitosamente", count);
                    return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                            "status", "SUCCESS",
                            "processedCount", count,
                            "totalReceived", requests.size()
                    ));
                })
                .exceptionally(ex -> {
                    log.error("Error al procesar lote de transacciones: {}", ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(Map.of(
                                    "status", "ERROR",
                                    "message", ex.getMessage()
                            ));
                });
    }

    @GetMapping("/{transactionId}")
    @Operation(summary = "Consultar estado de transacción", description = "Retorna el estado de procesamiento de una transacción específica")
    public ResponseEntity<TransactionResponse> getTransactionStatus(
            @Parameter(description = "ID de la transacción", required = true)
            @PathVariable String transactionId) {

        log.debug("Consultando estado de transacción: {}", transactionId);

        return ResponseEntity.status(HttpStatus.OK).body(
                TransactionResponse.pending(transactionId)
        );
    }

    @GetMapping("/health")
    @Operation(summary = "Verificar salud del servicio", description = "Endpoint de health check para el servicio de transacciones")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "UP",
                "service", "transaction-processor"
        ));
    }
}