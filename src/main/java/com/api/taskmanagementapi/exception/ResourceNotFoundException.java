package com.api.taskmanagementapi.exception;
// dùng khi không tìm tháy tài nguyên nào đó, ví dụ như user, task, project,... khi client gửi request lên mà không tìm thấy tài nguyên đó thì sẽ trả về lỗi này
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}