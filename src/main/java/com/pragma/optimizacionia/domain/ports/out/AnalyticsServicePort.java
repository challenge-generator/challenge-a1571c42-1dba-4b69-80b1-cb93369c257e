package com.pragma.optimizacionia.domain.ports.out;

import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import com.pragma.optimizacionia.domain.model.Transaction;

import java.util.concurrent.CompletableFuture;

/**
 * Puerto de salida para comunicar resultados al servicio de analíticas de cliente (SAC).
 * Define el contrato para enviar resultados del procesamiento de transacciones
 * al sistema externo de analíticas.
 */
public interface AnalyticsServicePort {

    /**
     * Envía un resultado de análisis al servicio de analíticas.
     * @param transaction la transacción procesada
     * @param result el resultado del análisis realizado
     * @return un CompletableFuture que se completa con true si el envío fue exitoso
     */
    CompletableFuture<Boolean> sendResult(Transaction transaction, AnalyticsResult result);

    /**
     * Envía un lote de resultados de análisis al servicio de analíticas.
     * @param transactions iterable de transacciones procesadas
     * @param results iterable de resultados correspondientes
     * @return un CompletableFuture que se completa con el número de resultados enviados
     */
    CompletableFuture<Integer> sendBatchResults(
            Iterable<Transaction> transactions,
            Iterable<AnalyticsResult> results
    );

    /**
     * Verifica la disponibilidad del servicio de analíticas.
     * @return un CompletableFuture que se completa con true si el servicio está disponible
     */
    CompletableFuture<Boolean> isAvailable();
}