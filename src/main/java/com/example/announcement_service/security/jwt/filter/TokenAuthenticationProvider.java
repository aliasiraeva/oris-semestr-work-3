package com.example.announcement_service.security.jwt.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.announcement_service.security.details.UserDetailsImpl;
import com.example.announcement_service.security.details.UserDetailsServiceImpl;
import com.example.announcement_service.security.jwt.JwtAuthenticationToken;
import com.example.announcement_service.security.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String rawToken = (String) authentication.getCredentials();

        try {
            if (jwtService.isAccessToken(rawToken)) {
                throw new BadCredentialsException("Это не access-токен!");
            }
        } catch (TokenExpiredException ex) {
            throw new BadCredentialsException("Токен истёк");
        } catch (JWTVerificationException ex) {
            throw new BadCredentialsException("Некорректный токен");
        }


        String username = getUsername(rawToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
        return new JwtAuthenticationToken(userDetails);
    }

    private String getUsername(String rawToken) {
        try {
            return jwtService.getPhoneNumber(rawToken);
        } catch (JWTVerificationException e) {
            throw new BadCredentialsException("Некорректный токен");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
