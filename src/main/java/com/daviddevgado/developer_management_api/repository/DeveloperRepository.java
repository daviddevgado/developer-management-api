package com.daviddevgado.developer_management_api.repository;

import com.daviddevgado.developer_management_api.entity.developer.Developer;
import com.daviddevgado.developer_management_api.entity.developer.Seniority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Long> {

    List<Developer> findByNameIgnoreCase(String name);
    Optional<Developer> findByEmailIgnoreCase(String email);
    Page<Developer> findBySeniorityAndActive(Seniority seniority, Boolean active, Pageable pageable);
    Page<Developer> findByActiveTrue(Pageable pageable);
    Page<Developer> findByActiveFalse(Pageable pageable);
    Page<Developer> findByTechnologies_NameAndActive(String technologyName, Boolean active, Pageable pageable);
    Optional<Developer> findByProfile_LinkedinUrlIgnoreCase(String linkedinUrl);
    Optional<Developer> findByProfile_GitHubUrlIgnoreCase(String gitHubUrl);
    List<Developer> findByProjectAssignments_Project_Id(Long projectId);
}
