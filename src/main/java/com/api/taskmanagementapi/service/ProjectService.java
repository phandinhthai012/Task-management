package com.api.taskmanagementapi.service;

import com.api.taskmanagementapi.dto.request.ProjectRequest;
import com.api.taskmanagementapi.dto.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse createProject(ProjectRequest request, String emailUserLogged);
    List<ProjectResponse> getProjectsByUser(String emailUserLogged);
}
