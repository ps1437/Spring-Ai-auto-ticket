package com.syshco.ai.mcp.wallet;

import org.springframework.ai.tool.annotation.Tool;

import java.util.List;

public class WalletTools {

    private WalletRepository walletRepository;

    public WalletTools(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Tool(description = "Number of shares for each company in my wallet")
    public List<Share> getNumberOfShares() {
        return (List<Share>) walletRepository.findAll();
    }
}