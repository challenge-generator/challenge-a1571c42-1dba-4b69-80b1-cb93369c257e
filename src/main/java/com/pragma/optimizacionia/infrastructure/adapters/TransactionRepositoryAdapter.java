package com.pragma.optimizacionia.infrastructure.adapters;

import com.pragma.optimizacionia.domain.model.Transaction;
import com.pragma.optimizacionia.domain.ports.out.TransactionRepositoryPort;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {
    
    private static final Logger logger = LoggerFactory.getLogger(TransactionRepositoryAdapter.class);
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Transaction save(Transaction transaction) {
        logger.debug("Saving transaction: type={}, amount={}", transaction.type(), transaction.amount());
        
        try {
            TransactionEntity entity = toEntity(transaction);
            entityManager.persist(entity);
            
            logger.info("Transaction saved successfully with ID: {}", entity.getId());
            return transaction;
            
        } catch (PersistenceException e) {
            logger.error("Failed to save transaction: {}", e.getMessage());
            throw new RuntimeException("Failed to save transaction: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Optional<Transaction> findById(Long id) {
        logger.debug("Finding transaction by ID: {}", id);
        
        try {
            TransactionEntity entity = entityManager.find(TransactionEntity.class, id);
            if (entity == null) {
                logger.debug("Transaction not found with ID: {}", id);
                return Optional.empty();
            }
            
            logger.debug("Transaction found: {}", entity.getId());
            return Optional.of(toDomain(entity));
            
        } catch (Exception e) {
            logger.error("Error finding transaction by ID {}: {}", id, e.getMessage());
            return Optional.empty();
        }
    }
    
    @Override
    public List<Transaction> findAll() {
        logger.debug("Retrieving all transactions");
        
        try {
            TypedQuery<TransactionEntity> query = entityManager.createQuery(
                "SELECT t FROM TransactionEntity t", TransactionEntity.class);
            List<TransactionEntity> entities = query.getResultList();
            
            logger.debug("Retrieved {} transactions", entities.size());
            return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
            
        } catch (Exception e) {
            logger.error("Error retrieving all transactions: {}", e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public List<Transaction> findByType(String type) {
        logger.debug("Finding transactions by type: {}", type);
        
        try {
            TypedQuery<TransactionEntity> query = entityManager.createQuery(
                "SELECT t FROM TransactionEntity t WHERE t.type = :type", TransactionEntity.class);
            query.setParameter("type", type);
            List<TransactionEntity> entities = query.getResultList();
            
            logger.debug("Found {} transactions of type: {}", entities.size(), type);
            return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
            
        } catch (Exception e) {
            logger.error("Error finding transactions by type {}: {}", type, e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public List<Transaction> findHighValueTransactions(BigDecimal threshold) {
        logger.debug("Finding high-value transactions with threshold: {}", threshold);
        
        try {
            TypedQuery<TransactionEntity> query = entityManager.createQuery(
                "SELECT t FROM TransactionEntity t WHERE t.amount >= :threshold", TransactionEntity.class);
            query.setParameter("threshold", threshold);
            List<TransactionEntity> entities = query.getResultList();
            
            logger.debug("Found {} high-value transactions", entities.size());
            return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
            
        } catch (Exception e) {
            logger.error("Error finding high-value transactions: {}", e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public void deleteById(Long id) {
        logger.debug("Deleting transaction by ID: {}", id);
        
        try {
            TransactionEntity entity = entityManager.find(TransactionEntity.class, id);
            if (entity != null) {
                entityManager.remove(entity);
                logger.info("Transaction deleted successfully: {}", id);
            } else {
                logger.warn("Transaction not found for deletion: {}", id);
            }
            
        } catch (Exception e) {
            logger.error("Error deleting transaction {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete transaction: " + e.getMessage(), e);
        }
    }
    
    @Override
    public long count() {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(t) FROM TransactionEntity t", Long.class);
            return query.getSingleResult();
            
        } catch (Exception e) {
            logger.error("Error counting transactions: {}", e.getMessage());
            return 0L;
        }
    }
    
    @Override
    public boolean existsById(Long id) {
        logger.debug("Checking if transaction exists: {}", id);
        
        try {
            TransactionEntity entity = entityManager.find(TransactionEntity.class, id);
            return entity != null;
            
        } catch (Exception e) {
            logger.error("Error checking transaction existence {}: {}", id, e.getMessage());
            return false;
        }
    }
    
    private TransactionEntity toEntity(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.setType(transaction.type());
        entity.setAmount(transaction.amount());
        entity.setCreatedAt(Instant.now());
        entity.setIsHighValue(transaction.isHighValue());
        return entity;
    }
    
    private Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
            entity.getType(),
            entity.getAmount(),
            entity.getIsHighValue() != null && entity.getIsHighValue()
        );
    }
    
    @Entity
    @Table(name = "transactions")
    public static class TransactionEntity {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Column(nullable = false, length = 20)
        private String type;
        
        @Column(nullable = false, precision = 19, scale = 4)
        private BigDecimal amount;
        
        @Column(name = "created_at", nullable = false)
        private Instant createdAt;
        
        @Column(name = "is_high_value")
        private Boolean isHighValue;
        
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        public BigDecimal getAmount() {
            return amount;
        }
        
        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
        
        public Instant getCreatedAt() {
            return createdAt;
        }
        
        public void setCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
        }
        
        public Boolean getIsHighValue() {
            return isHighValue;
        }
        
        public void setIsHighValue(Boolean isHighValue) {
            this.isHighValue = isHighValue;
        }
    }
}