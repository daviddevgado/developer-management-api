package com.daviddevgado.developer_management_api.entity.developer.mapper;

import com.daviddevgado.developer_management_api.entity.developer.Developer;
import com.daviddevgado.developer_management_api.entity.developer.DeveloperProfile;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.DevProfileRequest;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.DevProfileDTO;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public DevProfileDTO toDTO(DeveloperProfile developerProfile) {
        return new DevProfileDTO(
                developerProfile.getDeveloper().getId(),
                developerProfile.getDeveloper().getName(),
                developerProfile.getBio(),
                developerProfile.getGitHubUrl(),
                developerProfile.getLinkedinUrl()
        );
    }

    public DeveloperProfile toEntity(DevProfileRequest request, Developer developer, byte[] profilePicture) {
        DeveloperProfile profile = new DeveloperProfile();
        profile.setDeveloper(developer);
        if (request.bio() != null) {
            profile.setBio(request.bio().trim());
        }
        if (request.linkedinUrl() != null) {
            profile.setLinkedinUrl(request.linkedinUrl().trim());
        }
        if (request.gitHubUrl() != null) {
            profile.setGitHubUrl(request.gitHubUrl().trim());
        }
        profile.setProfilePicture(profilePicture);
        return profile;
    }
}
