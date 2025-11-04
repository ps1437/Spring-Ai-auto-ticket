package com.syshco.ai.simple_chat;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


record DnaInfo(String name, int age, String gender) {}

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "DNA Extraction API", description = "Extracts name, age, and gene/DNA information from user input")
public class DnaExtractionController {

    private final ChatClient chatClient;

    @Operation(
            summary = "Extract Name, Age, Gene",
            description = "Extracts name, age, and gene/DNA from the provided text and returns it in structured JSON format."
    )
    @GetMapping("/extract-dna")
    public ResponseEntity<DnaInfo> extractDna(@RequestParam("text") String text) {

        DnaInfo dnaInfo = chatClient
                .prompt()
                .system("""
                        You are a data extraction assistant.
                        Extract the following details from the user input:
                        - name
                        - age
                        - gender (male/female/other)
                        Respond only in JSON matching the DnaInfo structure.
                        If a value is missing, use null for name/gender and 0 for age.
                        """)
                .user(text)
                .call()
                .entity(DnaInfo.class);

        return ResponseEntity.ok(dnaInfo);
    }
}
