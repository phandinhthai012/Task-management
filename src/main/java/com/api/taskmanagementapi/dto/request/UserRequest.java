package com.api.taskmanagementapi.dto.request;
import jakarta.validation.constraints.*;

public record UserRequest (
        @Size(min = 1, max = 100)
        String username,
        @Email(message = "Email should be valid")
        String email,
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password
){}

