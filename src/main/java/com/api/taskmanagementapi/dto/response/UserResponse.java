package com.api.taskmanagementapi.dto.response;

import java.time.LocalDateTime;

public record UserResponse(
        Integer id,
        String username,
        String email,
        LocalDateTime createdAt
) {
}
