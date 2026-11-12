
package com.transactionintel.transaction_intelligence_platform.service;

import com.transactionintel.transaction_intelligence_platform.entity.Transaction;
import com.transactionintel.transaction_intelligence_platform.repository.TransactionRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FraudDetectionService {

    private static final BigDecimal HIGH_VALUE_THRESHOLD =
        new BigDecimal("50000");

    private final TransactionRepository transactionRepository;

    public FraudDetectionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<String> detectSuspiciousActivity() {

        List<Transaction> transactions =
            transactionRepository.findAll();

        List<String> alerts = new java.util.ArrayList<>();

        // Rule 1: Detect high-value transactions
        for (Transaction transaction : transactions) {

            if (transaction.getAmount()
                    .compareTo(HIGH_VALUE_THRESHOLD) >= 0) {

                alerts.add(
                    "HIGH VALUE ALERT | Transaction: "
                    + transaction.getTransactionId()
                    + " | Account: " + transaction.getAccountId()
                    + " | Amount: " + transaction.getCurrency()
                    + " " + transaction.getAmount()
                    + " | Reason: Transaction amount is at least 50000."
                );
            }
        }

        // Rule 2: Detect repeated failed transactions in 24 hours
        LocalDateTime twentyFourHoursAgo =
            LocalDateTime.now().minusHours(24);

        Map<String, List<Transaction>> failedTransactionsByAccount =
            transactions.stream()
                .filter(t -> "FAILED".equalsIgnoreCase(t.getStatus()))
                .filter(t -> t.getTransactionTime()
                    .isAfter(twentyFourHoursAgo))
                .collect(Collectors.groupingBy(
                    Transaction::getAccountId
                ));

        for (Map.Entry<String, List<Transaction>> entry
                : failedTransactionsByAccount.entrySet()) {

            if (entry.getValue().size() >= 2) {

                alerts.add(
                    "REPEATED FAILURE ALERT | Account: "
                    + entry.getKey()
                    + " | Failed Attempts: "
                    + entry.getValue().size()
                    + " | Reason: Multiple failed transactions "
                    + "detected within 24 hours."
                );
            }
        }

        return alerts;
    }
}
