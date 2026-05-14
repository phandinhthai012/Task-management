package com.api.taskmanagementapi.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record ProjectResponse(
        Integer id,
        String name,
        String description,
        LocalDateTime createdAt
) {
}
