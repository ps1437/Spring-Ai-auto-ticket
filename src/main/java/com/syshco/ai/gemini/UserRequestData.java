package com.syshco.ai.gemini;

import lombok.Data;

import java.util.List;

@Data
public class UserRequestData {
    private String intent;
    private List<String> softwareNames;
    private String requestedBy;
}

