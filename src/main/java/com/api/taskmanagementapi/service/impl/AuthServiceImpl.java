package com.api.taskmanagementapi.service.impl;

import com.api.taskmanagementapi.dto.request.LoginRequest;
import com.api.taskmanagementapi.dto.response.LoginResponse;
import com.api.taskmanagementapi.entity.User;
import com.api.taskmanagementapi.repository.UserRepository;
import com.api.taskmanagementapi.security.JwtUtil;
import com.api.taskmanagementapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(loginRequest.password())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtUtil.generateToken(user);
        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                token
        );
    }
}
