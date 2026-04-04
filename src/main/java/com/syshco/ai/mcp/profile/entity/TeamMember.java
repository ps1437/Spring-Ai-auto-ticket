package com.syshco.ai.mcp.profile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamMember {
    private String name;
    private String email;
    private String teamName;
    private String role;

}
