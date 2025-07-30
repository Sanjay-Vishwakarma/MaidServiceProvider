package com.maid.service.provider.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maid.service.provider.dto.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        Exception exception = (Exception) request.getAttribute("exception");

        if (exception != null) {
            String message;
            HttpStatus status;

            if (exception instanceof ExpiredJwtException) {
                message = "JWT token has expired";
                status = HttpStatus.UNAUTHORIZED;
            } else if (exception instanceof SignatureException) {
                message = "Invalid JWT signature";
                status = HttpStatus.UNAUTHORIZED;
            } else if (exception instanceof UsernameNotFoundException) {
                message = "User not found";
                status = HttpStatus.NOT_FOUND;
            } else {
                message = "Authentication failed";
                status = HttpStatus.UNAUTHORIZED;
            }

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(status.value());

            ErrorResponse error = new ErrorResponse(
                    status.value(),
                    status.getReasonPhrase(),
                    message
            );

            response.getWriter().write(new ObjectMapper().writeValueAsString(error));
        } else {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        }
    }
}