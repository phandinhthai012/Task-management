package com.api.taskmanagementapi.dto.respone;

import java.time.LocalDateTime;

public record UserRespone(
        Integer id,
        String username,
        String email,
        LocalDateTime createdAt
) {
}
