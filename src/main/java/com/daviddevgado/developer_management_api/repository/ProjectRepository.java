package com.daviddevgado.developer_management_api.repository;

import com.daviddevgado.developer_management_api.entity.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // By name
    Optional<Project> findByNameIgnoreCase(String name);

    // By active status
    List<Project> findByActiveTrue();
    List<Project> findByActiveFalse();
    Page<Project> findByActiveFalse(Pageable pageable);
    long countByActiveTrue();
    long countByActiveFalse();

    // By technology ID
    List<Project> findByTechnologies_Id(Long technologyId);
    Page<Project> findByTechnologies_Id(Long technologyId, Pageable pageable);
    List<Project> findByTechnologies_IdAndActiveTrue(Long technologyId);
    List<Project> findByTechnologies_IdAndActiveFalse(Long technologyId);
    Page<Project> findByTechnologies_IdAndActiveFalse(Long technologyId, Pageable pageable);
    long countByTechnologies_IdAndActiveFalse(Long technologyId);

    // By technology name
    List<Project> findByTechnologies_NameIgnoreCase(String technologyName);
    Page<Project> findByTechnologies_NameIgnoreCase(String technologyName, Pageable pageable);
    long countByTechnologies_NameIgnoreCase(String technologyName);
    List<Project> findByTechnologies_NameIgnoreCaseAndActiveTrue(String technologyName);
    List<Project> findByTechnologies_NameIgnoreCaseAndActiveFalse(String technologyName);
    Page<Project> findByTechnologies_NameIgnoreCaseAndActiveFalse(String technologyName, Pageable pageable);
    long countByTechnologies_NameIgnoreCaseAndActiveFalse(String technologyName);
}
