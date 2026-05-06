package com.api.taskmanagementapi.dto.respone;

import java.time.LocalDateTime;

public record LoginRespone(
        Integer id,
        String username,
        String email,
        LocalDateTime createdAt,
        String token

) {
}
