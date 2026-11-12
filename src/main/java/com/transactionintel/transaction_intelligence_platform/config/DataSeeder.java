
package com.transactionintel.transaction_intelligence_platform.config;

import com.transactionintel.transaction_intelligence_platform.entity.Transaction;
import com.transactionintel.transaction_intelligence_platform.repository.TransactionRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedTransactions(TransactionRepository repository) {
        return args -> {

            if (repository.count() > 0) {
                return;
            }

            LocalDateTime now = LocalDateTime.now();

            List<Transaction> transactions = List.of(
                create("TXN10001", "ACC1001", "7500.00",
                    "INR", "SUCCESS", "PAYMENT",
                    "Amazon", now.minusHours(1)),

                create("TXN10002", "ACC1001", "65000.00",
                    "INR", "FAILED", "TRANSFER",
                    "Bank Transfer", now.minusHours(2)),

                create("TXN10003", "ACC1002", "12000.00",
                    "INR", "SUCCESS", "PAYMENT",
                    "Flipkart", now.minusHours(3)),

                create("TXN10004", "ACC1003", "85000.00",
                    "INR", "PENDING", "TRANSFER",
                    "NEFT", now.minusHours(4)),

                create("TXN10005", "ACC1002", "55000.00",
                    "INR", "FAILED", "PAYMENT",
                    "Online Store", now.minusHours(5)),

                create("TXN10006", "ACC1004", "2500.00",
                    "INR", "SUCCESS", "PAYMENT",
                    "Swiggy", now.minusHours(6)),

                create("TXN10007", "ACC1001", "72000.00",
                    "INR", "FAILED", "TRANSFER",
                    "Bank Transfer", now.minusMinutes(30)),

                create("TXN10008", "ACC1005", "150000.00",
                    "INR", "SUCCESS", "TRANSFER",
                    "Investment Platform", now.minusMinutes(15))
            );

            repository.saveAll(transactions);

            System.out.println("Sample transactions inserted successfully!");
        };
    }

    private Transaction create(
            String transactionId,
            String accountId,
            String amount,
            String currency,
            String status,
            String transactionType,
            String merchant,
            LocalDateTime transactionTime) {

        Transaction transaction = new Transaction();

        transaction.setTransactionId(transactionId);
        transaction.setAccountId(accountId);
        transaction.setAmount(new BigDecimal(amount));
        transaction.setCurrency(currency);
        transaction.setStatus(status);
        transaction.setTransactionType(transactionType);
        transaction.setMerchant(merchant);
        transaction.setTransactionTime(transactionTime);

        return transaction;
    }
}
