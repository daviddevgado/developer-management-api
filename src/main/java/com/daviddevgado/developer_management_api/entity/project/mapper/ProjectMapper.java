package com.daviddevgado.developer_management_api.entity.project.mapper;

import com.daviddevgado.developer_management_api.entity.project.Project;
import com.daviddevgado.developer_management_api.entity.project.dto.ProjectDTO;
import com.daviddevgado.developer_management_api.entity.project.dto.ProjectRequest;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectRequest request) {
        return new Project(request.name(), request.description());
    }

    public ProjectDTO toDTO(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription()
        );
    }
}
