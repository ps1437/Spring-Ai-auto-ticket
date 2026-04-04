package com.syshco.ai.mcp.profile;

import com.syshco.ai.mcp.profile.entity.DummyCmdbService;
import com.syshco.ai.mcp.profile.entity.Product;
import com.syshco.ai.mcp.profile.entity.TeamMember;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyCmdbTool {

    private final DummyCmdbService dummyCmdbService;

    public DummyCmdbTool(DummyCmdbService dummyCmdbService) {
        this.dummyCmdbService = dummyCmdbService;
    }

    @Tool(description = "Get team member details by email")
    public TeamMember getMemberByEmail(
        @ToolParam(description = "Email ID of the member") String email) {
        return dummyCmdbService.getMemberByEmail(email);
    }

    @Tool(description = "List all products owned by a team")
    public List<Product> getProductsByTeam(
        @ToolParam(description = "Name of the team") String teamName) {
        return dummyCmdbService.getProductsByTeam(teamName);
    }
}
