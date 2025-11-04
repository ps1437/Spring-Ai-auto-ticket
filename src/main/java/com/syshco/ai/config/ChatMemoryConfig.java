package com.syshco.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Schedulers;

/**
 * ChatConfig class configures the ChatClient bean for the application.
 * It connects the chat model with memory and logging advisors.
 *
 * Simple explanation:
 * - ChatClient: The main object you use to talk to the AI.
 * - Advisors: Extra helpers that can log messages or store conversation history.
 * - ChatMemory: Keeps track of previous messages so the AI "remembers" the conversation.
 */
@Configuration
public class ChatMemoryConfig {

    /**
     * Defines a ChatClient bean to be used in your services or controllers.
     *
     * @param builder     Builder to create ChatClient instances
     * @param chatMemory  Memory object to keep conversation history
     * @return configured ChatClient
     */
    @Bean("chatMemoryClient")
    public ChatClient chatMemoryClient(ChatClient.Builder builder, ChatMemory chatMemory) {
        return builder
                // defaultAdvisors: attach helpers to ChatClient
                // 1. SimpleLoggerAdvisor: logs messages for debugging
                // 2. messageChatMemoryAdvisor: saves conversation history in chatMemory
                .defaultAdvisors(new SimpleLoggerAdvisor(), messageChatMemoryAdvisor(chatMemory))
                .build();
    }

    /**
     * Creates a MessageChatMemoryAdvisor to store messages in ChatMemory.
     *
     * Simple explanation:
     * - This advisor watches all messages.
     * - Saves them into ChatMemory so AI can remember previous messages.
     * - Scheduler makes this run asynchronously without blocking.
     * - conversationId: identifies this chat session. Can be dynamic for multiple users.
     *
     * @param chatMemory ChatMemory bean to store conversation
     * @return configured MessageChatMemoryAdvisor
     */
    private static MessageChatMemoryAdvisor messageChatMemoryAdvisor(ChatMemory chatMemory) {
        return MessageChatMemoryAdvisor.builder(chatMemory)
                .scheduler(Schedulers.boundedElastic()) // run memory updates on separate thread
                .build();
    }

}
