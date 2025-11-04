package com.syshco.ai.dummy;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final List<UserProfile> users = new ArrayList<>();

    public UserProfileService() {
        // Dummy data initialization
        users.add(new UserProfile("Alice Johnson", "alice@alpha.com", "Developer", "Alpha", "Bangalore"));
        users.add(new UserProfile("Bob Smith", "bob@alpha.com", "QA Engineer", "Alpha", "Pune"));
        users.add(new UserProfile("Charlie Brown", "charlie@beta.com", "Tech Lead", "Beta", "Chennai"));
        users.add(new UserProfile("Diana Prince", "diana@beta.com", "Project Manager", "Beta", "Hyderabad"));
        users.add(new UserProfile("Ethan Hunt", "ethan@gamma.com", "DevOps Engineer", "Gamma", "Mumbai"));
    }

    public UserProfile getUserByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public List<UserProfile> getUserByName(String name) {
        return users.stream()
                .filter(u -> u.getName().contains(name)).toList();
    }

    public List<UserProfile> getUsersByTeam(String teamName) {
        return users.stream()
                .filter(u -> u.getTeam().equalsIgnoreCase(teamName))
                .collect(Collectors.toList());
    }

    public List<UserProfile> getAllUsers() {
        return users;
    }
}
