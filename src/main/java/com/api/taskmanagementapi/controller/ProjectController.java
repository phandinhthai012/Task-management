package com.api.taskmanagementapi.controller;

import com.api.taskmanagementapi.dto.request.ProjectRequest;
import com.api.taskmanagementapi.dto.response.ApiResponse;
import com.api.taskmanagementapi.dto.response.ProjectResponse;
import com.api.taskmanagementapi.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponse>> createProject(
            @RequestBody @Valid ProjectRequest request,
            Principal principal) {

        String emailUserLogged = principal.getName();
        ProjectResponse response = projectService.createProject(request, emailUserLogged);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Project created successfully", response));
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getMyProjects(Principal principal) {
        String emailUserLogged = principal.getName();
        List<ProjectResponse> responses = projectService.getProjectsByUser(emailUserLogged);

        return ResponseEntity.ok(ApiResponse.success("Projects retrieved successfully", responses));
    }
}
