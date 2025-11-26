package com.daviddevgado.developer_management_api.service;

import com.daviddevgado.developer_management_api.entity.developer.dto.developer.CreateDeveloperRequest;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer.DeveloperDTO;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer.UpdateDeveloperRequest;

public interface DeveloperService {
    DeveloperDTO addDeveloper(CreateDeveloperRequest request);
    DeveloperDTO updateDeveloper(Long developerId, UpdateDeveloperRequest request);
    void deleteDeveloper(Long developerId);
    DeveloperDTO getDeveloperById(Long developerId);
}
