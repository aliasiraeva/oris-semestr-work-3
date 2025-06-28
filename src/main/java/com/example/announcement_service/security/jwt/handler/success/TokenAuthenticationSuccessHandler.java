package com.example.announcement_service.security.jwt.handler.success;

import com.example.announcement_service.dto.JwtTokenPairDto;
import com.example.announcement_service.security.jwt.service.JwtService;
import com.example.announcement_service.util.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class TokenAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        String phoneNumber = ((UserDetails) authentication.getPrincipal()).getUsername();
        JwtTokenPairDto tokenPair = jwtService.getTokenPair(phoneNumber);

        response.setStatus(200);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JsonUtil.write(tokenPair));
    }

}
