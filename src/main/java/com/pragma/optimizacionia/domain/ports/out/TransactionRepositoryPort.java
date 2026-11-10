package com.pragma.optimizacionia.domain.ports.out;

import com.pragma.optimizacionia.domain.model.Transaction;

import java.util.concurrent.CompletableFuture;

/**
 * Puerto de salida para la persistencia de transacciones.
 * Define el contrato que la capa de dominio utiliza para guardar transacciones.
 * La implementación concreta reside en la capa de infraestructura.
 */
public interface TransactionRepositoryPort {

    /**
     * Persiste una transacción en el repositorio.
     * @param transaction la transacción a persistir
     * @return un CompletableFuture que se completa con la transacción persistida
     */
    CompletableFuture<Transaction> save(Transaction transaction);

    /**
     * Persiste un lote de transacciones en el repositorio.
     * @param transactions iterable de transacciones a persistir
     * @return un CompletableFuture que se completa con el número de transacciones persistidas
     */
    CompletableFuture<Integer> saveBatch(Iterable<Transaction> transactions);

    /**
     * Busca una transacción por su identificador único.
     * @param transactionId identificador de la transacción
     * @return un CompletableFuture que puede completarse con la transacción si existe
     */
    CompletableFuture<Transaction> findById(String transactionId);
}