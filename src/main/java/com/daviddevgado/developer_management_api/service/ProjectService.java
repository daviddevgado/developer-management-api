package com.daviddevgado.developer_management_api.service;

import com.daviddevgado.developer_management_api.entity.project.dto.CreateProjectRequest;
import com.daviddevgado.developer_management_api.entity.project.dto.ProjectDTO;
import com.daviddevgado.developer_management_api.entity.project.dto.UpdateProjectRequest;

public interface ProjectService {
    ProjectDTO createProject(CreateProjectRequest request);
    ProjectDTO updateProject(Long id, UpdateProjectRequest request);
    ProjectDTO deleteProject(Long id);
    ProjectDTO getProjectById(Long id);
}
