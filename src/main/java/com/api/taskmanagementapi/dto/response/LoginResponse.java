package com.api.taskmanagementapi.dto.response;

import java.time.LocalDateTime;

public record LoginResponse(
        Integer id,
        String username,
        String email,
        LocalDateTime createdAt,
        String token

) {
}
