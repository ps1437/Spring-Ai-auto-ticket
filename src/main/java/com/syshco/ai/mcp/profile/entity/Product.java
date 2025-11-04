package com.syshco.ai.mcp.profile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String productName;
    private String ownerEmail;
    private String teamName;
    private String description;
    private String criticality;
    private String status;


}
