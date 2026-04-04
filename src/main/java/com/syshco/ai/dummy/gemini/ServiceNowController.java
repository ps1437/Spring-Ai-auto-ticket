//package com.syshco.ai.gemini;
//
//import com.google.api.pathtemplate.ValidationException;
//import com.syshco.ai.external.snow.ServiceNowClient;
//import com.syshco.ai.external.snow.SoftwareTicket;
//import lombok.RequiredArgsConstructor;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.chat.messages.UserMessage;
//import org.springframework.ai.chat.prompt.Prompt;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequiredArgsConstructor
//public class ServiceNowController {
//
//    private final ChatClient chatClient;
//
//    private final ServiceNowClient serviceNowClient;
//    private final WeaviateService weaviateService;
//    private final List<RequestHandler> handlers;
//
//    @PostMapping("/api/structured")
//    public ResponseEntity<?> extractStructured(@RequestBody String userInput) {
//        String promptText = """
//                Extract the following fields from the user input text:
//
//                - email address
//                - mobile number
//                - description
//                - team info
//
//                Input: "%s"
//
//                Return a JSON object with the above fields.
//                """.formatted(userInput);
//
//        Prompt prompt = new Prompt(new UserMessage(promptText));
//
//        ServiceNowRequest request = chatClient.prompt(prompt).call().entity(ServiceNowRequest.class);
//
//        if (request.getEmail() == null || request.getEmail().isBlank()) {
//            Map<String, String> response = Map.of("message", "Please provide your email address.");
//            return ResponseEntity.badRequest().body(response);
//        }
//
//        return ResponseEntity.ok(request);
//    }
//
//
//    @PostMapping("/api/software")
//    public ResponseEntity<?> handleRequest(@RequestBody String userInput) {
//        String promptText = """
//                    You are a smart assistant that extracts structured information from user requests.
//
//                    Given the user input below, extract the following fields in a JSON format:
//
//                    - intent: the user's intent as a lowercase string (e.g., "software_installation")
//                    - softwareNames: a list (array) of software names mentioned (can be empty if none found)
//                    - requestedBy: the name or identifier of the requester (string, null if not found)
//
//                    If any field is missing or unclear, use null (for strings) or empty array (for softwareNames).
//
//                    Input: "%s"
//
//                    Respond ONLY with the JSON object, no additional text or explanation.
//                """.formatted(userInput);
//
//
//        Prompt prompt = new Prompt(new UserMessage(promptText));
//
//        UserRequestData requestData = chatClient.prompt(prompt).call().entity(UserRequestData.class);
//
//        if ("software_installation".equalsIgnoreCase(requestData.getIntent())) {
//            List<SoftwareTicket> tickets = new ArrayList<>();
//            for (String software : requestData.getSoftwareNames()) {
//                SoftwareTicket ticket = serviceNowClient.createTicket(software, requestData.getRequestedBy());
//                tickets.add(ticket);
//            }
//            return ResponseEntity.ok(tickets);
//        } else {
//            return ResponseEntity.badRequest().body("Unsupported intent: " + requestData.getIntent());
//        }
//    }
//
//
//    @PostMapping("/api/request")
//    public ResponseEntity<?> handle(@RequestBody String userInput) {
//        // 1. embed & search template
//        Map<String, Object> template = weaviateService.fetchRequestTemplate(userInput);
//        String requestType = (String) template.get("requestType");
//        List<String> mandatory = (List<String>) template.get("mandatoryFields");
//        List<String> optional = (List<String>) template.get("optionalFields");
//
//        // 2. build prompt & call LLM to get extractedFields
//        Map<String, Object> extracted = llmExtract(userInput, requestType, mandatory, optional);
//
//        // 3. find handler and process
//        for (RequestHandler h : handlers) {
//            if (h.supports(requestType)) {
//                try {
//                    h.validate(extracted);
//                    Object result = h.process(extracted);
//                    return ResponseEntity.ok(result);
//                } catch (ValidationException ve) {
//                    return ResponseEntity.badRequest().body(Map.of("message", ve.getMessage()));
//                }
//            }
//        }
//
//        return ResponseEntity.badRequest().body(Map.of("message", "Unsupported request type: " + requestType));
//    }
//
//
//    public Map<String, Object> llmExtract(String userInput,
//                                          String requestType,
//                                          List<String> mandatoryFields,
//                                          List<String> optionalFields) {
//
//        String prompt = buildPrompt(userInput, requestType, mandatoryFields, optionalFields);
//
//        return chatClient.prompt(prompt).call().entity(Map.class);
//
//    }
//
//    private String buildPrompt(String userInput,
//                               String requestType,
//                               List<String> mandatoryFields,
//                               List<String> optionalFields) {
//
//        StringBuilder sb = new StringBuilder();
//
//        sb.append("You are an intelligent assistant extracting structured fields for a request of type: \"")
//                .append(requestType)
//                .append("\".\n");
//
//        sb.append("Mandatory fields: ").append(String.join(", ", mandatoryFields)).append("\n");
//        sb.append("Optional fields: ").append(String.join(", ", optionalFields)).append("\n");
//
//        sb.append("User input:\n\"").append(userInput).append("\"\n\n");
//
//        sb.append("Return a JSON object with all the above fields. If a mandatory field is missing, set it to null.\n");
//        sb.append("Do not include any explanation. Return **only** valid JSON.\n");
//
//        return sb.toString();
//    }
//
//
//}
