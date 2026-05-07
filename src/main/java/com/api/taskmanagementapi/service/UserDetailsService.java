package com.api.taskmanagementapi.service;

import com.api.taskmanagementapi.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    UserDetails loadUserByEmail(String Email);
}
