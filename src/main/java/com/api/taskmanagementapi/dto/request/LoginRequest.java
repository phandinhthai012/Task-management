package com.api.taskmanagementapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @Email(message = "Email should be valid")
        String email,
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password
) {
}
