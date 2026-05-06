package com.api.taskmanagementapi.controller;

import com.api.taskmanagementapi.dto.request.LoginRequest;
import com.api.taskmanagementapi.dto.respone.LoginRespone;
import com.api.taskmanagementapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<LoginRespone> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginRespone response = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
