package com.syshco.ai.gemini;

import lombok.Data;

@Data
public class ServiceNowRequest {
    private String email;
    private String mobileNumber;
    private String description;
    private String team;
}