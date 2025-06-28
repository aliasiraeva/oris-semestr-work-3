package com.example.announcement_service.security.jwt.login;

import com.example.announcement_service.dto.JwtTokenPairDto;
import com.example.announcement_service.entity.RefreshToken;
import com.example.announcement_service.exception.InvalidRefreshTokenException;
import com.example.announcement_service.security.exception.AuthMethodNotSupportedException;
import com.example.announcement_service.security.jwt.JwtAuthenticationToken;
import com.example.announcement_service.security.jwt.service.JwtService;
import com.example.announcement_service.service.JwtRefreshTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

public class RefreshTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    private final JwtRefreshTokenService jwtRefreshTokenService;
    private final JwtService jwtService;

    public RefreshTokenAuthenticationFilter(
            String defaultFilterProcessesUrl,
            AuthenticationManager authenticationManager,
            AuthenticationSuccessHandler successHandler,
            AuthenticationFailureHandler failureHandler,
            JwtRefreshTokenService jwtRefreshTokenService,
            JwtService jwtService
    ) {
        super(defaultFilterProcessesUrl, authenticationManager);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.jwtRefreshTokenService = jwtRefreshTokenService;
        this.jwtService = jwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!POST.asHttpMethod().matches(request.getMethod())) {
            throw new AuthMethodNotSupportedException("Этот метод не поддерживается!");
        }

        String token = jwtService.getRawToken(request);

        RefreshToken refreshToken = jwtRefreshTokenService.findByToken(token)
                .orElseThrow(() -> new InvalidRefreshTokenException("Некорректный refresh-токен!"));

        JwtTokenPairDto jwtPair = jwtService.getTokenPair(refreshToken.getPhone());
        request.setAttribute("jwt", jwtPair);

        JwtAuthenticationToken authToken = new JwtAuthenticationToken(jwtPair.accessToken());
        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {

        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
