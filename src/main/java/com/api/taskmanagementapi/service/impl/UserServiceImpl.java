package com.api.taskmanagementapi.service.impl;

import com.api.taskmanagementapi.dto.request.UserRequest;
import com.api.taskmanagementapi.dto.response.UserResponse;
import com.api.taskmanagementapi.entity.User;
import com.api.taskmanagementapi.repository.UserRepository;
import com.api.taskmanagementapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    @Transactional
    public UserResponse createUser(UserRequest request, String clinetIp) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        User savedUser = userRepository.save(user);
        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedAt()
        );
    }

    @Override
    public UserResponse getUserById(Integer id) {
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return List.of();
    }

    @Override
    public UserResponse updateUser(Integer id, UserRequest request) {
        return null;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        return null;
    }

    @Override
    public UserResponse Login(String email, String password) {
        return null;
    }


}
