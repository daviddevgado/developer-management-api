package com.daviddevgado.developer_management_api.repository;

import com.daviddevgado.developer_management_api.entity.developer.DeveloperProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevProfileRepository extends JpaRepository<DeveloperProfile, Long> {
}
