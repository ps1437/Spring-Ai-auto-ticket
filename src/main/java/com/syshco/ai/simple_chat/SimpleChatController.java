package com.syshco.ai.simple_chat;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/chat")
public class SimpleChatController {

    private final ChatClient chatClient;

    public SimpleChatController(@Qualifier("chatMemoryClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Operation(summary = "Ask a question to the Gemini AI model",
            description = "Send a question via query parameter 'q' and get a text response")
    @GetMapping("/simple")
    public ResponseEntity<String> ask(
            @Parameter(description = "The question to ask Gemini", example = "Hello, Gemini!")
            @RequestHeader(name = "username", defaultValue = "user") String username,
            @RequestParam(value = "q", defaultValue = "Hello, Gemini!") String message
    ) {

        return ResponseEntity.ok(chatClient.prompt().user(message).advisors(
                        advisorSpec -> advisorSpec.param(CONVERSATION_ID, username)
                )
                .call().content());
    }


}
