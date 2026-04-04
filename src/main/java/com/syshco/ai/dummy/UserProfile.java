package com.syshco.ai.dummy;

public class UserProfile {
    private String name;
    private String email;
    private String role;
    private String team;
    private String location;

    public UserProfile(String name, String email, String role, String team, String location) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.team = team;
        this.location = location;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
