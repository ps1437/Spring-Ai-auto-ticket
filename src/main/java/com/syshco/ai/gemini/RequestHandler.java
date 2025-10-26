package com.syshco.ai.gemini;

import java.util.Map;

public interface RequestHandler {
    boolean supports(String requestType);
    void validate(Map<String, Object> extracted);
    Object process(Map<String, Object> extracted);
}


