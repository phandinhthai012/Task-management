package com.api.taskmanagementapi.service;

import com.api.taskmanagementapi.dto.request.UpdateUserRequest;
import com.api.taskmanagementapi.dto.request.UserRequest;
import com.api.taskmanagementapi.dto.response.UserResponse;

import java.util.List;

public interface UserService {
   UserResponse createUser(UserRequest request, String clinetIp);
   UserResponse getUserById(Integer id);
   List<UserResponse> getAllUsers();
   UserResponse updateUser(Integer id, UpdateUserRequest request);
   Boolean deleteUser(Integer id);
   UserResponse Login(String email, String password);

}
