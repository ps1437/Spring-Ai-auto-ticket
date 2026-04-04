package com.syshco.ai.mcp.profile.entity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dummy")
public class DummyCmdbController {

    private final DummyCmdbService service;

    public DummyCmdbController(DummyCmdbService service) {
        this.service = service;
    }

    @GetMapping("/products/team/{teamName}")
    public List<Product> getProductsByTeam(@PathVariable String teamName) {
        return service.getProductsByTeam(teamName);
    }

    @GetMapping("/products/owner/{email}")
    public Product getProductByOwner(@PathVariable String email) {
        return service.getProductByOwnerEmail(email);
    }

    @GetMapping("/members/team/{teamName}")
    public List<TeamMember> getMembersByTeam(@PathVariable String teamName) {
        return service.getMembersByTeam(teamName);
    }

    @GetMapping("/members/email/{email}")
    public TeamMember getMemberByEmail(@PathVariable String email) {
        return service.getMemberByEmail(email);
    }
}
