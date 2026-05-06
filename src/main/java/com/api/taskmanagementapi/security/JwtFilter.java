package com.api.taskmanagementapi.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter  implements Filter {

    private JwtUtil jwtUtil;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String authorization = httpServletRequest.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ")) {
            String header = authorization.substring(7);
            try {
               Claims token = jwtUtil.parseToken(header);

            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid token");
            }
        }
    }
}
