package com.syshco.ai;

import com.syshco.ai.mcp.wallet.WalletRepository;
import com.syshco.ai.mcp.wallet.WalletResponse;
import com.syshco.ai.mcp.wallet.WalletService;
import com.syshco.ai.mcp.wallet.WalletTools;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;

import java.util.function.Supplier;

@SpringBootApplication
public class SpringAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiApplication.class, args);
    }

    @Bean
    @Description("Number of shares for each company in my portfolio")
    public Supplier<WalletResponse> numberOfShares(WalletRepository walletRepository) {
        return new WalletService(walletRepository);
    }

    @Bean
    public WalletTools walletTools(WalletRepository walletRepository) {
        return new WalletTools(walletRepository);
    }


    @Bean
    public ObservationRegistry observationRegistry() {
        return ObservationRegistry.create();
    }

}
