package com.api.taskmanagementapi.exception;
//Đăng nhập khi người dùng nhập sai Mật khẩu hoặc Token không hợp lệ.
public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(String message) {
        super(message);
    }
}