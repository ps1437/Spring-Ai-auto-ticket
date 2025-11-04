package com.syshco.ai.mcp.profile.entity;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DummyCmdbService {

    private final List<Product> products = new ArrayList<>();
    private final List<TeamMember> teamMembers = new ArrayList<>();

    public DummyCmdbService() {
        // Dummy products
        products.add(new Product(1L, "OrderService", "alice@company.com", "Alpha", "Handles orders", "High", "Active"));
        products.add(new Product(2L, "BillingService", "bob@company.com", "Beta", "Manages billing", "Medium", "Active"));
        products.add(new Product(3L, "UserService", "charlie@company.com", "Alpha", "Handles user accounts", "High", "Active"));
        products.add(new Product(4L, "NotificationService", "diana@company.com", "Gamma", "Sends notifications", "Low", "Active"));
        products.add(new Product(5L, "InventoryService", "eve@company.com", "Beta", "Tracks stock inventory", "Medium", "Inactive"));

        // Dummy team members
        teamMembers.add(new TeamMember("Alice", "alice@company.com", "Alpha", "Product Owner"));
        teamMembers.add(new TeamMember("Charlie", "charlie@company.com", "Alpha", "Developer"));
        teamMembers.add(new TeamMember("Bob", "bob@company.com", "Beta", "Tech Lead"));
        teamMembers.add(new TeamMember("Eve", "eve@company.com", "Beta", "QA Engineer"));
        teamMembers.add(new TeamMember("Diana", "diana@company.com", "Gamma", "Project Manager"));
    }

    public List<Product> getProductsByTeam(String teamName) {
        return products.stream()
                .filter(p -> p.getTeamName().equalsIgnoreCase(teamName))
                .collect(Collectors.toList());
    }

    public Product getProductByOwnerEmail(String email) {
        return products.stream()
                .filter(p -> p.getOwnerEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public List<TeamMember> getMembersByTeam(String teamName) {
        return teamMembers.stream()
                .filter(m -> m.getTeamName().equalsIgnoreCase(teamName))
                .collect(Collectors.toList());
    }

    public TeamMember getMemberByEmail(String email) {
        return teamMembers.stream()
                .filter(m -> m.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}
