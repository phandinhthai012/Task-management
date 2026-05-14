package com.api.taskmanagementapi.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ProjectRequest(
        @NotBlank(message = "Project name is required")
        String name,
        String description
) {
}
