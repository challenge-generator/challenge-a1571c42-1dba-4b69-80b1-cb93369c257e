package com.pragma.optimizacionia.domain.ports.in;

import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.domain.model.AnalyticsResult;
import java.util.concurrent.CompletionStage;

public interface ProcessTransactionPort {
    /**
     * Processes a transaction and returns the analytics result.
     * @param transaction the transaction to process
     * @return a CompletionStage containing the analytics result
     */
    CompletionStage<AnalyticsResult> processTransaction(Transaction transaction);
    
    /**
     * Processes a batch of transactions and returns the count of successfully processed transactions.
     * @param transactions the list of transactions to process
     * @return a CompletionStage containing the count of successfully processed transactions
     */
    CompletionStage<Integer> processBatchTransactions(Iterable<Transaction> transactions);
}