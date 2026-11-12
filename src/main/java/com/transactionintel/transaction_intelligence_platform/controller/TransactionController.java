
package com.transactionintel.transaction_intelligence_platform.controller;

import com.transactionintel.transaction_intelligence_platform.entity.Transaction;
import com.transactionintel.transaction_intelligence_platform.service.TransactionService;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // GET all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // GET transactions by status
    @GetMapping("/status/{status}")
    public List<Transaction> getByStatus(@PathVariable String status) {
        return transactionService.getTransactionsByStatus(status);
    }

    // GET transactions by account
    @GetMapping("/account/{accountId}")
    public List<Transaction> getByAccount(
            @PathVariable String accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }

    // GET transactions by status and minimum amount
    @GetMapping("/filter")
    public List<Transaction> getByStatusAndAmount(
            @RequestParam String status,
            @RequestParam BigDecimal minAmount) {

        return transactionService.getTransactionsByStatusAndAmount(
                status, minAmount);
    }

    // GET transaction by transaction reference
    @GetMapping("/{transactionId}")
    public Transaction getByTransactionId(
            @PathVariable String transactionId) {

        return transactionService.getTransactionById(transactionId)
                .orElseThrow(() -> new RuntimeException(
                        "Transaction not found: " + transactionId));
    }
}
