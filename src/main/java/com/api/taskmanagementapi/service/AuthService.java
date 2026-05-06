package com.api.taskmanagementapi.service;

import com.api.taskmanagementapi.dto.request.LoginRequest;
import com.api.taskmanagementapi.dto.respone.LoginRespone;

public interface AuthService {
    LoginRespone login(LoginRequest loginRequest);
}
