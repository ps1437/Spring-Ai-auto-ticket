package com.syshco.ai.tools;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/tools")
public class AiToolController {

    private final ChatClient chatClient;
    private final ApiTools apiTools;

    public AiToolController(ChatClient chatClient, ApiTools apiTools) {
        this.chatClient = chatClient;
        this.apiTools = apiTools;
    }

//    @GetMapping("/query")
//    public String query(@RequestParam String prompt) {
//        var response = chatClient.prompt()
//                .tools(apiTools)
//                .user(prompt)
//                .call();
//
//        return response.content();
//    }


    @GetMapping("/query")
    public String query(@RequestParam String prompt) {

        var response = chatClient.prompt()
                .tools(apiTools)
                .system("""
                        You are an AI assistant that can fetch employee information by calling internal APIs.
                        - Use the `callApi(url, filterField, filterValue)` tool when you need data.
                        - If user asks for "get employee all", call: http://localhost:8088/employees
                        - If user asks for "get employee by id 2", call: http://localhost:8088/employees/2
                        - If user searches by another field (like name, role, age, etc.), 
                          call http://localhost:8088/employees, then filter locally to return only matching employees.
                        - Return only the matched records in JSON.
                        - If nothing matches, return {"message": "No employee found"}.
                        """)
                .user(prompt)
                .call();

        return response.content();
    }

}
