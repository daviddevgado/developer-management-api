package com.daviddevgado.developer_management_api.service.impl;

import com.daviddevgado.developer_management_api.entity.developer.Developer;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer.CreateDeveloperRequest;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer.DeveloperDTO;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer.UpdateDeveloperRequest;
import com.daviddevgado.developer_management_api.entity.developer.mapper.DeveloperMapper;
import com.daviddevgado.developer_management_api.repository.DeveloperRepository;
import com.daviddevgado.developer_management_api.service.DeveloperService;
import org.springframework.stereotype.Service;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;
    private final DeveloperMapper developerMapper;

    public DeveloperServiceImpl(DeveloperRepository developerRepository, DeveloperMapper developerMapper) {
        this.developerRepository = developerRepository;
        this.developerMapper = developerMapper;
    }

    @Override
    public DeveloperDTO addDeveloper(CreateDeveloperRequest request) {

        Developer developer = new Developer(
                request.name(),
                request.email(),
                request.seniority(),
                request.salary()
        );

        Developer savedDeveloper = developerRepository.save(developer);
        return developerMapper.toDTO(savedDeveloper);
    }

    @Override
    public DeveloperDTO updateDeveloper(Long developerId, UpdateDeveloperRequest request) {
        return null;
    }

    @Override
    public DeveloperDTO deleteDeveloper(Long developerId, String reason, String deletedBy) {
        return null;
    }

    @Override
    public DeveloperDTO getDeveloperById(Long developerId) {
        return null;
    }
}
