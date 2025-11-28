package com.daviddevgado.developer_management_api.service;

import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.CreateDevProfileRequest;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.DevProfileDTO;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.UpdateDevProfileRequest;
import org.springframework.web.multipart.MultipartFile;

public interface DeveloperProfileService {
    DevProfileDTO createDeveloperProfile(Long developerId, CreateDevProfileRequest request, MultipartFile profilePicture);
    DevProfileDTO updateDeveloperProfile(Long profileId, UpdateDevProfileRequest request);
    DevProfileDTO deleteDeveloperProfile(Long profileId);
    DevProfileDTO getDeveloperProfileId(Long profileId);
}
