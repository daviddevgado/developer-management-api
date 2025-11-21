package com.daviddevgado.developer_management_api.service;

import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.CreateDevProfileRequest;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.DevProfileDTO;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.UpdateDevProfileRequest;

public interface DeveloperProfileService {
    DevProfileDTO createDeveloperProfile(CreateDevProfileRequest request);
    DevProfileDTO updateDeveloperProfile(Long profileId, UpdateDevProfileRequest request);
    DevProfileDTO deleteDeveloperProfile(Long profileId);
    DevProfileDTO getDeveloperProfileId(Long profileId);
}
