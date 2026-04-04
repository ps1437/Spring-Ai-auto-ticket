package com.syshco.ai.mcp.wallet;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final ChatClient chatClient;
    private final WalletTools walletTools;


    @GetMapping("/with-tools")
    public String calculateWalletValueWithTools() {
        PromptTemplate pt = new PromptTemplate("""
                What’s the current value in dollars of my wallet based on the latest stock daily prices ?
                """);

        return this.chatClient.prompt(pt.create())
                .tools(walletTools)
                .call()
                .content();
    }


}