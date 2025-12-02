package com.daviddevgado.developer_management_api.repository;

import com.daviddevgado.developer_management_api.entity.technology.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    boolean existsByNameIgnoreCase(String name);
    List<Technology> findByDevelopersId(Long developerId);
    List<Technology> findByProjectsId(Long projectId);
}
