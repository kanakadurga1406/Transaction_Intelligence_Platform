
package com.transactionintel.transaction_intelligence_platform.mcp;

import com.transactionintel.transaction_intelligence_platform.entity.Transaction;
import com.transactionintel.transaction_intelligence_platform.service.TransactionService;
import com.transactionintel.transaction_intelligence_platform.service.FraudDetectionService;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionMcpTools {

    private final TransactionService transactionService;
    private final FraudDetectionService fraudDetectionService;

    public TransactionMcpTools(TransactionService transactionService, FraudDetectionService fraudDetectionService) {
        this.transactionService = transactionService;
        this.fraudDetectionService = fraudDetectionService;
    }

    @McpTool(
        name = "search_transactions_by_status",
        description = "Search financial transactions by their status, such as SUCCESS, FAILED, or PENDING."
    )
    public String searchTransactionsByStatus(
        @McpToolParam(description = "Transaction status: SUCCESS, FAILED, or PENDING")
        String status
    ) {

        List<Transaction> transactions =
            transactionService.getTransactionsByStatus(status);

        if (transactions.isEmpty()) {
            return "No transactions found with status: " + status;
        }

        StringBuilder result = new StringBuilder();

        result.append("Found ")
              .append(transactions.size())
              .append(" transactions with status ")
              .append(status)
              .append(":\n");

        for (Transaction transaction : transactions) {
            result.append("\nTransaction ID: ")
                  .append(transaction.getTransactionId())
                  .append("\nAccount ID: ")
                  .append(transaction.getAccountId())
                  .append("\nAmount: ")
                  .append(transaction.getCurrency())
                  .append(" ")
                  .append(transaction.getAmount())
                  .append("\nMerchant: ")
                  .append(transaction.getMerchant())
                  .append("\nTime: ")
                  .append(transaction.getTransactionTime())
                  .append("\n--------------------\n");
        }

        return result.toString();
    }


@McpTool(
    name = "get_transaction_details",
    description = "Retrieve detailed information about a financial transaction using its transaction ID."
)
public String getTransactionDetails(
    @McpToolParam(description = "Unique transaction ID, for example TXN10001")
    String transactionId
) {

    return transactionService.getTransactionById(transactionId)
        .map(transaction ->
            "Transaction Details:\n" +
            "Transaction ID: " + transaction.getTransactionId() + "\n" +
            "Account ID: " + transaction.getAccountId() + "\n" +
            "Amount: " + transaction.getCurrency() + " " +
                transaction.getAmount() + "\n" +
            "Status: " + transaction.getStatus() + "\n" +
            "Type: " + transaction.getTransactionType() + "\n" +
            "Merchant: " + transaction.getMerchant() + "\n" +
            "Transaction Time: " + transaction.getTransactionTime()
        )
        .orElse("Transaction not found: " + transactionId);
}


@McpTool(
    name = "investigate_account_transactions",
    description = "Retrieve all financial transactions associated with a given account ID for account-level investigation."
)
public String investigateAccountTransactions(
    @McpToolParam(description = "Account ID, for example ACC1001")
    String accountId
) {

    List<Transaction> transactions =
        transactionService.getTransactionsByAccount(accountId);

    if (transactions.isEmpty()) {
        return "No transactions found for account: " + accountId;
    }

    StringBuilder result = new StringBuilder();

    result.append("Account Investigation Report\n");
    result.append("Account ID: ").append(accountId).append("\n");
    result.append("Total Transactions: ")
          .append(transactions.size()).append("\n\n");

    for (Transaction transaction : transactions) {
        result.append("Transaction ID: ")
              .append(transaction.getTransactionId())
              .append("\nAmount: ")
              .append(transaction.getCurrency())
              .append(" ")
              .append(transaction.getAmount())
              .append("\nStatus: ")
              .append(transaction.getStatus())
              .append("\nType: ")
              .append(transaction.getTransactionType())
              .append("\nMerchant: ")
              .append(transaction.getMerchant())
              .append("\n--------------------\n");
    }

    return result.toString();
}

@McpTool(
    name = "detect_suspicious_transactions",
    description = "Analyze financial transactions and identify suspicious activity using high-value transaction and repeated failed payment rules. Returns alerts with explanations."
)
public String detectSuspiciousTransactions() {

    List<String> alerts =
        fraudDetectionService.detectSuspiciousActivity();

    if (alerts.isEmpty()) {
        return "No suspicious transaction activity detected.";
    }

    StringBuilder result = new StringBuilder();

    result.append("FRAUD DETECTION REPORT\n");
    result.append("Total Alerts: ").append(alerts.size());
    result.append("\n\n");

    for (String alert : alerts) {
        result.append(alert)
              .append("\n\n");
    }

    return result.toString();
}


}