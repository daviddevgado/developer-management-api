package com.daviddevgado.developer_management_api.repository;

import com.daviddevgado.developer_management_api.entity.project_assignment.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {
    List<ProjectAssignment> findByDeveloperId(Long developerId);
}
