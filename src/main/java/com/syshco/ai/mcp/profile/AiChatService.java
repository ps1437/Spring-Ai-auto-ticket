package com.syshco.ai.mcp.profile;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiChatService {

    private final ChatClient chatClient;

    public AiChatService(ChatClient.Builder builder, UserProfileTool userProfileTool, DummyCmdbTool dummyCmdbTool) {
        this.chatClient = builder
                .defaultTools(userProfileTool, dummyCmdbTool)
                .build();
    }

    public String ask(String userPrompt) {
        return chatClient.prompt()
                .user(userPrompt)
                .call()
                .content();
    }
}
