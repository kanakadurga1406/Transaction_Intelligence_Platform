
package com.transactionintel.transaction_intelligence_platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;

@Configuration
@Profile("ai-client")
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(
            ChatClient.Builder builder,
            SyncMcpToolCallbackProvider toolCallbackProvider) {

        return builder
                .defaultToolCallbacks(toolCallbackProvider.getToolCallbacks())
                .build();
    }
}
