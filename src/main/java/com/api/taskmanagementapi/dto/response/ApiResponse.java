package com.api.taskmanagementapi.dto.response;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
    // Static method cho trường hợp thành công
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    // Static method cho trường hợp thất bại
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}