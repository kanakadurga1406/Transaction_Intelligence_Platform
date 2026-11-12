
package com.transactionintel.transaction_intelligence_platform.service;

import com.transactionintel.transaction_intelligence_platform.entity.Transaction;
import com.transactionintel.transaction_intelligence_platform.repository.TransactionRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Retrieve all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Search transactions by status
    public List<Transaction> getTransactionsByStatus(String status) {
        return transactionRepository.findByStatusIgnoreCase(status);
    }

    // Search transactions by account
    public List<Transaction> getTransactionsByAccount(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    // Find transactions by status and minimum amount
    public List<Transaction> getTransactionsByStatusAndAmount(
            String status, BigDecimal amount) {

        return transactionRepository
                .findByStatusIgnoreCaseAndAmountGreaterThanEqual(
                        status, amount);
    }

    // Retrieve transaction using its reference ID
    public Optional<Transaction> getTransactionById(String transactionId) {
        return transactionRepository.findByTransactionId(transactionId)
                .stream()
                .findFirst();
    }
}
