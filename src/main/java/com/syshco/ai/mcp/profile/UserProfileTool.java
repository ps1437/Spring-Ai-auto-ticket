package com.syshco.ai.mcp.profile;

import com.syshco.ai.dummy.UserProfile;
import com.syshco.ai.dummy.UserProfileService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileTool {

    @Autowired
    private UserProfileService userProfileService;

    @Tool(description = "Get a user profile by email address")
    public UserProfile getUserProfileByEmail(
            @ToolParam(description = "Email address of the user") String email) {
        return userProfileService.getUserByEmail(email);
    }

    @Tool(description = "Get a user profile by name")
    public UserProfile getUserProfileByName(
            @ToolParam(description = "Email address of the user") String email) {
        return userProfileService.getUserByEmail(email);
    }


    @Tool(description = "Get all user profiles in a specific team")
    public List<UserProfile> getUserProfilesByTeam(
            @ToolParam(description = "Team name") String teamName) {
        return userProfileService.getUsersByTeam(teamName);
    }

}
