package com.syshco.ai.ai;

import com.syshco.ai.ai.model.ChatMessage;
import com.syshco.ai.external.snow.ServiceNowClient;
import com.syshco.ai.external.snow.SoftwareCatalogItem;
import com.syshco.ai.external.snow.SoftwareTicket;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class AiChatController {

    private final VectorDBService vectorDBService;
    private final ServiceNowClient serviceNowClient;

    @PostMapping("/message")
    public ResponseEntity<Map<String, Object>> handleChat(@RequestBody ChatMessage msg) {

        SoftwareCatalogItem software = vectorDBService.findClosest(msg.getMessage());
        if (software == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "No matching software found. Please check your request."));
        }
        String context = software.getDescription();

        SoftwareTicket ticket = serviceNowClient.createTicket(software.getName(), msg.getUser());

        Map<String, Object> response = Map.of(
                "status", "success",
                "software", software.getName(),
                "ticketNumber", ticket.getTicketNumber(),
                "context", context,
                "message", "Your request has been created successfully."
        );

        return ResponseEntity.ok(response);
    }
}
