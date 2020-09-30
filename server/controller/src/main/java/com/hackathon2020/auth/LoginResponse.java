package com.hackathon2020.auth;

import com.hackathon2020.domain.Role;
import com.hackathon2020.security.UserDetails;
import lombok.Data;

@Data
public class LoginResponse {

    private String login;

    private Boolean enabled;

    private Role role;

    private String token;

    public LoginResponse(UserDetails user, String token) {
        this.login = user.getUsername();
        this.role = user.getRole();
        this.enabled = user.isEnabled();
        this.token = token;
    }
}
