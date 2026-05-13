package com.api.taskmanagementapi.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record UserResponse(
        Integer id,
        String username,
        String email,
        LocalDateTime createdAt
) {
}
