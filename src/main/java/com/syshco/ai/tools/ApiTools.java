package com.syshco.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiTools {

    private final RestTemplate restTemplate = new RestTemplate();

    @Tool(name = "callApi", description = "Calls an external API with the given URL and returns its JSON response")
    public String callApi(
            @ToolParam(description = "Full URL of the API to call") String url
    ) {
        try {
            System.out.println("calling API url");
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return "Error calling API: " + e.getMessage();
        }
    }
}
