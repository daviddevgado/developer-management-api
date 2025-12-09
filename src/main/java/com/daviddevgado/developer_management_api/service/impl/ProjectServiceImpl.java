package com.daviddevgado.developer_management_api.service.impl;

import com.daviddevgado.developer_management_api.entity.project.Project;
import com.daviddevgado.developer_management_api.entity.project.dto.ProjectRequest;
import com.daviddevgado.developer_management_api.entity.project.dto.ProjectDTO;
import com.daviddevgado.developer_management_api.entity.project.exception.ProjectAlreadyExistsException;
import com.daviddevgado.developer_management_api.entity.project.exception.ProjectDataInvalidException;
import com.daviddevgado.developer_management_api.entity.project.exception.ProjectNotFoundException;
import com.daviddevgado.developer_management_api.entity.project.mapper.ProjectMapper;
import com.daviddevgado.developer_management_api.entity.technology.exception.TechnologyAlreadyExistsException;
import com.daviddevgado.developer_management_api.repository.ProjectRepository;
import com.daviddevgado.developer_management_api.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

        ProjectRequest cleanedRequest = sanitizeRequest(request);

        if(cleanedRequest.name() == null || cleanedRequest.name().isBlank()) {
            log.debug("🔄 Project creation rejected: null or empty name");
            throw new ProjectDataInvalidException("Project name cannot be null or blank.");
        }

        if (projectRepository.existsByNameIgnoreCase(cleanedRequest.name())) {
            log.warn("⚠️ Duplicate project creation attempted: '{}'", cleanedRequest.name());
            throw new ProjectAlreadyExistsException("Project with name '" + cleanedRequest.name() + "' already exists.");
        }

        Project newProject = projectMapper.toEntity(cleanedRequest);
        Project savedProject = projectRepository.save(newProject);
        log.info("✅ Project successfully created - ID: {}, Name: {}",
                savedProject.getId(), savedProject.getName());

        return projectMapper.toDTO(savedProject);
    }

    @Override
    @Transactional
    public ProjectDTO updateProject(Long id, ProjectRequest request) {
        log.info("🚀 Starting project update for ID: {}", id);

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id '" + id + "' not found"));


        if (!request.name().equalsIgnoreCase(project.getName()) &&
                projectRepository.existsByNameIgnoreCaseAndIdNot(request.name(), id)) {
            log.warn("⚠️ Duplicate project name '{}' attempted during update for ID: {}", request.name(),id);
            throw new ProjectAlreadyExistsException("Project with name '" + request.name() + "' already exists.");
        }

        boolean hasChanges = false;

        if (!request.name().equalsIgnoreCase(project.getName())) {
            project.setName(request.name());
            hasChanges = true;
            log.debug("Name updated to: {}", request.name());
        }

        if (request.description() != null &&
                !request.description().equalsIgnoreCase(project.getDescription())) {
            project.setDescription(request.description());
            hasChanges = true;
            log.debug("Description updated to: {}", request.description());
        }

        if (!hasChanges) {
            log.info("🔄 No changes for project ID: {}", id);
            return projectMapper.toDTO(project);
        }

        Project updatedProject = projectRepository.save(project);
        log.info("✅ Project successfully updated - ID: {}, Name: {}",
                updatedProject.getId(), updatedProject.getName());

        return projectMapper.toDTO(updatedProject);
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
}
