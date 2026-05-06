package com.api.taskmanagementapi.controller;

import com.api.taskmanagementapi.dto.request.UserRequest;
import com.api.taskmanagementapi.dto.respone.UserRespone;
import com.api.taskmanagementapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRespone> createUser(@RequestBody UserRequest request) {
        String clientIp = "unknown";
        UserRespone response = userService.createUser(request, clientIp);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
