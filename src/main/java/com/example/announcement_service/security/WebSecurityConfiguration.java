package com.example.announcement_service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.announcement_service.security.jwt.SkipPathRequestMatcher;
import com.example.announcement_service.security.jwt.filter.TokenAuthenticationFilter;
import com.example.announcement_service.security.jwt.login.LoginAuthenticationFilter;
import com.example.announcement_service.security.jwt.login.RefreshTokenAuthenticationFilter;
import com.example.announcement_service.security.jwt.service.JwtService;
import com.example.announcement_service.service.JwtRefreshTokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    private final List<String> ANONYMOUS_PATHS = List.of(
            "/api/v1/login", "/api/v1/register", "/api/v1/check", "/api/v1/refresh", "/actuator/**",
            "/api/v1/announcement/**", "/api/v1/subscription/**", "/api/v1/profile/**", "/favicon.ico");

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/api/v1/login", "/api/v1/register", "/api/v1/check", "/actuator/**",
              "/api/v1/announcement/**","/api/v1/subscription/**", "/api/v1/profile/**");
    }

    @Bean
    public SecurityFilterChain apiSecurityFilterChain(
            HttpSecurity http,
            LoginAuthenticationFilter loginAuthenticationFilter,
            TokenAuthenticationFilter tokenAuthenticationFilter,
            RefreshTokenAuthenticationFilter refreshTokenAuthenticationFilter
    )
            throws Exception {

        HttpSecurity httpSecurity = http.securityMatcher("/api/**")
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(refreshTokenAuthenticationFilter, TokenAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/login", "/api/v1/register", "/api/v1/check", "/actuator/**",
                                "/api/v1/announcement/**", "/api/v1/subscription/**", "api/v1/favicon.ico").permitAll()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    public Algorithm algorithm(@Value("${jwt.secret}") String secret) {
        return Algorithm.HMAC256(secret);
    }

    @Bean
    public LoginAuthenticationFilter loginAuthenticationFilter(
            AuthenticationManager authenticationManager,
            @Qualifier("tokenAuthenticationSuccessHandler") AuthenticationSuccessHandler successHandler,
            @Qualifier("tokenAuthenticationFailureHandler") AuthenticationFailureHandler failureHandler
    ) {
        return new LoginAuthenticationFilter(
                "/api/v1/login", authenticationManager, successHandler, failureHandler);
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter(
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            @Qualifier("defaultAuthenticationFailureHandler") AuthenticationFailureHandler failureHandler) {
        SkipPathRequestMatcher requestMatcher = new SkipPathRequestMatcher(ANONYMOUS_PATHS);

        return new TokenAuthenticationFilter(
                requestMatcher, jwtService, authenticationManager, failureHandler);
    }

    @Bean
    public RefreshTokenAuthenticationFilter refreshTokenAuthenticationFilter(
            AuthenticationManager authenticationManager,
            @Qualifier("refreshTokenAuthenticationSuccessHandler") AuthenticationSuccessHandler successHandler,
            @Qualifier("refreshTokenAuthenticationFailureHandler") AuthenticationFailureHandler failureHandler,
            JwtRefreshTokenService jwtRefreshTokenService,
            JwtService jwtService) {
        return new RefreshTokenAuthenticationFilter(
                "/api/v1/refresh",
                authenticationManager,
                successHandler,
                failureHandler,
                jwtRefreshTokenService,
                jwtService
        );
    }

    @Bean
    public AuthenticationManager providerManager(List<AuthenticationProvider> providers) {
        return new ProviderManager(providers);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) {
        var provider = new DaoAuthenticationProvider(passwordEncoder);

        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

    @Bean
    public JWTVerifier jwtVerifier(Algorithm algorithm) {
        return JWT.require(algorithm).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
