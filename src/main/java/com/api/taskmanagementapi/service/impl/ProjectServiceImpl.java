package com.api.taskmanagementapi.service.impl;

import com.api.taskmanagementapi.dto.request.ProjectRequest;
import com.api.taskmanagementapi.dto.response.ProjectResponse;
import com.api.taskmanagementapi.entity.Project;
import com.api.taskmanagementapi.entity.User;
import com.api.taskmanagementapi.exception.ResourceNotFoundException;
import com.api.taskmanagementapi.repository.ProjectRepository;
import com.api.taskmanagementapi.repository.UserRepository;
import com.api.taskmanagementapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public ProjectResponse createProject(ProjectRequest request, String emailUserLogged) {
        User user = userRepository.findByEmail(emailUserLogged)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + emailUserLogged));
        Project project = new Project();
        project.setName(request.name());
        project.setDescription(request.description());
        project.setUser(user);

        Project savedProject = projectRepository.save(project);
        return mapToResponse(savedProject);
    }

    @Override
    public List<ProjectResponse> getProjectsByUser(String emailUserLogged) {
        User user = userRepository.findByEmail(emailUserLogged)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return projectRepository.findByUserId(user.getId()).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ProjectResponse mapToResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .createdAt(project.getCreatedAt())
                .build();
    }
}
