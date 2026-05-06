package com.api.taskmanagementapi.service;

import com.api.taskmanagementapi.dto.request.UserRequest;
import com.api.taskmanagementapi.dto.respone.UserRespone;
import com.api.taskmanagementapi.entity.User;

import java.util.List;

public interface UserService {
   UserRespone createUser(UserRequest request, String clinetIp);
   UserRespone getUserById(Integer id);
   List<UserRespone> getAllUsers();
   UserRespone updateUser(Integer id, UserRequest request);
   Boolean deleteUser(Integer id);
   UserRespone Login(String email, String password);

}
