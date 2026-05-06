package com.api.taskmanagementapi.service;

import com.api.taskmanagementapi.entity.User;

public interface UserDetailsService {
    User loadUserByUsername(String username);
}
