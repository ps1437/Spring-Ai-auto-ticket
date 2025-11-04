package com.syshco.ai.simple_chat;

import com.syshco.ai.simple_chat.model.CountryCities;
import com.syshco.ai.simple_chat.model.HospitalInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Structured Chat API", description = "Endpoints for AI-powered structured responses including generic and hospital-specific data")
public class StructuredResponseController {

    public static final String HOSPITAL_SYSTEM_PROMPT = """
            You are an internal hospital assistant.
            Your job is to answer patient or visitor queries about hospital services.
            Respond in a structured JSON format that matches the HospitalInfoResponse class:
            - patientName (optional)
            - query
            - responseMessage
            - services: List of objects with type, name, description, cost, availability
            Only include relevant information for the user's query.
            """;
    private final ChatClient chatClient;

    @Operation(
            summary = "Generic Structured Response",
            description = "Returns a structured CountryCities object based on user input. Placeholder for general structured responses."
    )
    @GetMapping("/cities")
    public ResponseEntity<CountryCities> chatBean(@RequestParam("message") String message) {
        CountryCities countryCities = chatClient
                .prompt()
                .system("""
                    You are an AI assistant that only provides information about countries and cities.
                    If the user asks anything outside of countries or cities, politely respond with:
                    'Sorry, I can only provide information about countries and cities.'
                    Always respond in a structured JSON format that matches CountryCities.
                    """)
                .user(message)
                .call().entity(CountryCities.class);
        return ResponseEntity.ok(countryCities);
    }

    @Operation(
            summary = "Hospital Structured Response",
            description = "Returns a structured hospital response in JSON format including patient info, query, response message, and list of hospital services relevant to the query."
    )
    @GetMapping("/hospital-info")
    public ResponseEntity<HospitalInfoResponse> hospitalInfo(@RequestParam("message") String message) {

        HospitalInfoResponse response = chatClient
                .prompt()
                .system(HOSPITAL_SYSTEM_PROMPT)
                .user(message)
                .call().entity(HospitalInfoResponse.class);

        return ResponseEntity.ok(response);
    }
}
