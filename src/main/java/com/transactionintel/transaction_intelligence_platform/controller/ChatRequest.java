
package com.transactionintel.transaction_intelligence_platform.controller;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(
        @NotBlank(message = "Message cannot be empty")
        String message
) {}
