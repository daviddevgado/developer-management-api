package com.daviddevgado.developer_management_api.service;

import com.daviddevgado.developer_management_api.entity.project.dto.ProjectRequest;
import com.daviddevgado.developer_management_api.entity.project.dto.ProjectDTO;

public interface ProjectService {
    ProjectDTO createProject(ProjectRequest request);
    ProjectDTO updateProject(Long id, ProjectRequest request);
    void deleteProject(Long id);
    ProjectDTO getProjectById(Long id);
}
