package com.daviddevgado.developer_management_api.service;

import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.DevProfileRequest;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.DevProfileDTO;
import org.springframework.web.multipart.MultipartFile;

public interface DeveloperProfileService {
    DevProfileDTO createDeveloperProfile(Long developerId, DevProfileRequest request, MultipartFile profilePicture);
    DevProfileDTO updateDeveloperProfile(Long profileId, DevProfileRequest request, MultipartFile profilePicture);
    void deleteDeveloperProfile(Long profileId);
    DevProfileDTO getDeveloperProfileId(Long profileId);
}
