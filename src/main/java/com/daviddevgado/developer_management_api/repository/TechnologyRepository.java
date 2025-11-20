package com.daviddevgado.developer_management_api.repository;

import com.daviddevgado.developer_management_api.entity.technology.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    List<Technology> findByDevelopersId(Long developerId);
    List<Technology> findByProjectsId(Long projectId);
}
