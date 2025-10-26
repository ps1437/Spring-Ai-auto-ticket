package com.syshco.ai.gemini;

import com.google.api.pathtemplate.ValidationException;
import com.syshco.ai.external.snow.SoftwareTicket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SoftwareInstallationHandler implements RequestHandler {
    @Override
    public boolean supports(String requestType) {
        return "software_installation".equalsIgnoreCase(requestType);
    }

    @Override
    public void validate(Map<String, Object> extracted) {
        if (extracted.get("softwareNames") == null || ((List<?>)extracted.get("softwareNames")).isEmpty()) {
            throw new ValidationException("softwareNames is mandatory");
        }
        if (extracted.get("requestedBy") == null || ((String)extracted.get("requestedBy")).isBlank()) {
            throw new ValidationException("requestedBy is mandatory");
        }
    }

    @Override
    public List<SoftwareTicket> process(Map<String, Object> extracted) {
        List<String> names = (List<String>) extracted.get("softwareNames");
        String requestedBy = (String) extracted.get("requestedBy");
        List<SoftwareTicket> tickets = new ArrayList<>();
        for (String name : names) {
            tickets.add(createTicket(name, requestedBy));
        }
        return tickets;
    }

    private SoftwareTicket createTicket(String softwareName, String requestedBy) {
        int rand = new Random().nextInt(9000) + 1000;
        String ticketNumber = "RTM-" + rand;
        return new SoftwareTicket(ticketNumber, softwareName, requestedBy, "Created");
    }
}