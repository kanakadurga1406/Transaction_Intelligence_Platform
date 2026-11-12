
package com.transactionintel.transaction_intelligence_platform.controller;

import jakarta.validation.Valid;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Profile;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@Profile("ai-client")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> chat(
            @Valid @RequestBody ChatRequest request) {

        String response = chatClient.prompt()
                .system("""
                    You are a transaction intelligence assistant.
                    Help users investigate synthetic financial transactions.
                    Use available tools to retrieve transaction information
                    and investigate suspicious activity.
                    Explain findings clearly.
                    Never describe a flagged transaction as confirmed fraud.
                    Do not invent transaction details or investigation results.
                    """)
                .user(request.message())
                .call()
                .content();

        return ResponseEntity.ok(Map.of("response", response));
    }
}
