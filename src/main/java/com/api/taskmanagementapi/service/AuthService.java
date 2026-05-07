package com.api.taskmanagementapi.service;

import com.api.taskmanagementapi.dto.request.LoginRequest;
import com.api.taskmanagementapi.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

}
