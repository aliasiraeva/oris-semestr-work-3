package com.example.announcement_service.security.jwt;

import com.example.announcement_service.security.details.UserDetailsImpl;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private String token;
    private UserDetailsImpl userDetails;

    public JwtAuthenticationToken(String token) {
        super(null);
        this.token = token;
    }

    public JwtAuthenticationToken(UserDetailsImpl userDetails) {
        super(null);
        this.userDetails = userDetails;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

}
