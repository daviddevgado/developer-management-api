package com.daviddevgado.developer_management_api.service;

import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.DevProfileRequest;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.DevProfileDTO;
import org.springframework.web.multipart.MultipartFile;

public interface DeveloperProfileService {
    DevProfileDTO createProfile(Long developerId, DevProfileRequest request, MultipartFile profilePicture);
    DevProfileDTO updateProfile(Long profileId, DevProfileRequest request, MultipartFile profilePicture);
    void deleteProfile(Long profileId);
    DevProfileDTO getProfileById(Long profileId);
}
