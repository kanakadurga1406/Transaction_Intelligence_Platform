
package com.transactionintel.transaction_intelligence_platform.repository;

import com.transactionintel.transaction_intelligence_platform.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findByStatusIgnoreCase(String status);

    List<Transaction> findByAccountId(String accountId);

    List<Transaction> findByStatusIgnoreCaseAndAmountGreaterThanEqual(
            String status,
            java.math.BigDecimal amount
    );

    List<Transaction> findByTransactionId(String transactionId);
}
