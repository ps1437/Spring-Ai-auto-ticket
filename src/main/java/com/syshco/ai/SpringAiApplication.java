package com.syshco.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiApplication.class, args);
	}


	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder
				.defaultAdvisors(new SimpleLoggerAdvisor())
				.build();
	}

}
