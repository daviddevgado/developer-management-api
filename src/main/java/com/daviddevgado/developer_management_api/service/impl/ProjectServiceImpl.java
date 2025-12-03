package com.daviddevgado.developer_management_api.service.impl;

import com.daviddevgado.developer_management_api.entity.project.Project;
import com.daviddevgado.developer_management_api.entity.project.dto.ProjectRequest;
import com.daviddevgado.developer_management_api.entity.project.dto.ProjectDTO;
import com.daviddevgado.developer_management_api.entity.project.exception.ProjectAlreadyExistsException;
import com.daviddevgado.developer_management_api.entity.project.exception.ProjectDataInvalidException;
import com.daviddevgado.developer_management_api.entity.project.mapper.ProjectMapper;
import com.daviddevgado.developer_management_api.repository.ProjectRepository;
import com.daviddevgado.developer_management_api.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    @Transactional
    public ProjectDTO createProject(ProjectRequest request) {
        log.info("🚀 Starting project creation with name: {}", request.name());

        ProjectRequest cleanRequest = sanitizeRequest(request);

        if(cleanRequest.name() == null || cleanRequest.name().isBlank()) {
            log.debug("🔄 Project creation rejected: null or empty name");
            throw new ProjectDataInvalidException("Project name cannot be null or blank.");
        }

        if (projectRepository.existsByNameIgnoreCase(cleanRequest.name())) {
            log.warn("⚠️ Duplicate project creation attempted: '{}'", cleanRequest.name());
            throw new ProjectAlreadyExistsException("Project with name '" + cleanRequest.name() + "' already exists.");
        }

        Project newProject = projectMapper.toEntity(cleanRequest);
        Project savedProject = projectRepository.save(newProject);
        log.info("✅ Project successfully created - ID: {}, Name: {}",
                savedProject.getId(), savedProject.getName());

        return projectMapper.toDTO(savedProject);
    }

    @Override
    @Transactional
    public ProjectDTO updateProject(Long id, ProjectRequest request) {
        return null;
    }

    @Override
    @Transactional
    public ProjectDTO deleteProject(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDTO getProjectById(Long id) {
        return null;
    }

    private ProjectRequest sanitizeRequest(ProjectRequest request) {
        return new ProjectRequest(
                request.name() != null ? request.name().trim() : null,
                request.description() != null ? request.description().trim() : null
        );
    }
}
