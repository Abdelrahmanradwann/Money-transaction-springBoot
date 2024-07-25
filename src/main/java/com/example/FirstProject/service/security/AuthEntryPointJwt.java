package com.example.FirstProject.service.security;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws
            IOException, java.io.IOException {
            log.error("Error while hanlding JWT authentication - {}",authException.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Error unauthorized");
    }
}
