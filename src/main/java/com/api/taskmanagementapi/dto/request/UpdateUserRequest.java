package com.api.taskmanagementapi.dto.request;

import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @Size(min = 1, max = 100, message = "Username must be between 1 and 100 characters")
        String username
        // Thêm các trường
) {}