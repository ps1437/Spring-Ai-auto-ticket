package com.syshco.ai.gemini;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class ChatController {

    private final ChatModel chatModel;

    @Autowired
    public ChatController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/api/ask")
    public String ask(@RequestParam(value = "q", defaultValue = "Hello, Gemini!") String q) {
        Prompt prompt = new Prompt(new UserMessage(q));
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }

}
