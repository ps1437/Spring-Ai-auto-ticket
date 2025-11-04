package com.syshco.ai.mcp.profile;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final AiChatService aiChatService;

    public AiController(AiChatService aiChatService) {
        this.aiChatService = aiChatService;
    }

    @GetMapping("/query")
    public String query(@RequestParam String prompt) {
        return aiChatService.ask(prompt);
    }
}
