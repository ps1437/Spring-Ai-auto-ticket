package com.syshco.ai.tools.employee;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class ApiMappingService {

    // Key = logical topic, Value = actual REST API URL
    private static final Map<String, String> apiMappings = Map.of(
        "employees", "http://localhost:8088/employees",
        "departments", "http://localhost:8088/departments",
        "products", "http://localhost:8088/products"
    );

    public String resolveApi(String topic) {
        return apiMappings.getOrDefault(topic.toLowerCase(), null);
    }
}
