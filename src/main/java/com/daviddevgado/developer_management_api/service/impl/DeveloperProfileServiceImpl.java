package com.daviddevgado.developer_management_api.service.impl;

import com.daviddevgado.developer_management_api.entity.developer.Developer;
import com.daviddevgado.developer_management_api.entity.developer.DeveloperProfile;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.DevProfileRequest;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile.DevProfileDTO;
import com.daviddevgado.developer_management_api.entity.developer.exception.DeveloperNotFoundException;
import com.daviddevgado.developer_management_api.entity.developer.exception.InvalidDeveloperDataException;
import com.daviddevgado.developer_management_api.entity.developer.exception.InvalidProfileDataException;
import com.daviddevgado.developer_management_api.entity.developer.exception.ProfileNotFoundException;
import com.daviddevgado.developer_management_api.entity.developer.mapper.ProfileMapper;
import com.daviddevgado.developer_management_api.repository.DevProfileRepository;
import com.daviddevgado.developer_management_api.repository.DeveloperRepository;
import com.daviddevgado.developer_management_api.service.DeveloperProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DeveloperProfileServiceImpl implements DeveloperProfileService {

    private static final Logger log = LoggerFactory.getLogger(DeveloperProfileServiceImpl.class);

    private final DeveloperRepository developerRepository;
    private final DevProfileRepository devProfileRepository;
    private final ProfileMapper profileMapper;

    public DeveloperProfileServiceImpl(DeveloperRepository developerRepository,
                                       DevProfileRepository devProfileRepository,
                                       ProfileMapper profileMapper) {
        this.developerRepository = developerRepository;
        this.profileMapper = profileMapper;
        this.devProfileRepository = devProfileRepository;
    }

    private static final int MAX_BIO_LENGTH = 500;
    private static final long MAX_PROFILE_PICTURE_SIZE = 2 * 1024 * 1024;

    @Override
    public DevProfileDTO createDeveloperProfile(Long developerId, DevProfileRequest request, MultipartFile profilePicture) {
        log.info("🚀 Starting developer profile creation for developer: {}", developerId);
        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new DeveloperNotFoundException("Developer not found with id: " + developerId));

        if (developer.getProfile() != null) {
            throw new InvalidDeveloperDataException("Developer already has a profile");
        }

        if (request.bio() != null) {
            String trimmedBio = request.bio().trim();
            if(trimmedBio.length() > MAX_BIO_LENGTH) {
                throw new InvalidProfileDataException("Bio exceeds maximum length of 500 characters");
            }
            if(trimmedBio.isBlank()) {
                throw new InvalidProfileDataException(("Bio cannot be empty or blank"));
            }
        }

        if (request.gitHubUrl() != null) {
            String trimmedUrl = request.gitHubUrl().trim();
            if (trimmedUrl.isBlank()) {
                throw new InvalidProfileDataException("GitHub URL cannot be empty or blank");
            }
        }

        if (request.linkedinUrl() != null) {
            String trimmedUrl = request.linkedinUrl().trim();
            if (trimmedUrl.isBlank()) {
                throw new InvalidProfileDataException("Linkedin URL cannot be empty or blank");
            }
        }

        byte[] pictureBytes = null;
        if (profilePicture != null && !profilePicture.isEmpty()) {
            if (profilePicture.getSize() > MAX_PROFILE_PICTURE_SIZE) {
                throw new InvalidProfileDataException("Profile picture exceeds maximum size of 2MB");
            }
            try {
                pictureBytes = profilePicture.getBytes();
            } catch (IOException e) {
                log.error("❌Error processing profile picture for developer {}: {}", developerId, e.getMessage());
                throw new InvalidDeveloperDataException("Error processing profile picture");
            }
        }

        DeveloperProfile profile = profileMapper.toEntity(request, developer, pictureBytes);
        developer.setProfile(profile);

        DeveloperProfile savedProfile = devProfileRepository.save(profile);

        log.info("✅ Profile created for developer: {} - Profile ID: {}", developerId, savedProfile.getId());
        return profileMapper.toDTO(savedProfile);
    }

    @Override
    public DevProfileDTO updateDeveloperProfile(Long profileId, DevProfileRequest request, MultipartFile profilePicture) {
        log.info("🚀 Starting developer profile updating for profile: {}", profileId);
        DeveloperProfile profile = devProfileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Developer profile not found with id: " + profileId));

        if (request.bio() != null) {
            String trimmedBio = request.bio().trim();
            if(trimmedBio.length() > MAX_BIO_LENGTH) {
                throw new InvalidProfileDataException("Bio exceeds maximum length of 500 characters");
            }
            if(trimmedBio.isBlank()) {
                throw new InvalidProfileDataException(("Bio cannot be empty or blank"));
            }
            profile.setBio(trimmedBio);
        }

        if (request.gitHubUrl() != null) {
            String trimmedUrl = request.gitHubUrl().trim();
            if (trimmedUrl.isBlank()) {
                throw new InvalidProfileDataException("GitHub URL cannot be empty or blank");
            }
            profile.setGitHubUrl(trimmedUrl);
        }

        if (request.linkedinUrl() != null) {
            String trimmedUrl = request.linkedinUrl().trim();
            if (trimmedUrl.isBlank()) {
                throw new InvalidProfileDataException("Linkedin URL cannot be empty or blank");
            }
            profile.setLinkedinUrl(trimmedUrl);
        }

        if (profilePicture != null && !profilePicture.isEmpty()) {
            if (profilePicture.getSize() > MAX_PROFILE_PICTURE_SIZE) {
                throw new InvalidProfileDataException("Profile picture exceeds maximum size of 2MB");
            }
            try {
                profile.setProfilePicture(profilePicture.getBytes());
            } catch (IOException e) {
                log.error("❌Error processing profile picture for profile {}: {}", profileId, e.getMessage());
                throw new InvalidDeveloperDataException("Error processing profile picture");
            }
        }

        DeveloperProfile updatedProfile = devProfileRepository.save(profile);
        log.info("✅ Profile updated successfully: {}", profileId);
        return profileMapper.toDTO(updatedProfile);
    }

    @Override
    public void deleteDeveloperProfile(Long profileId) {
        log.info("🚀 Starting developer profile deleting for profile: {}", profileId);
        DeveloperProfile profile = devProfileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Developer profile not found with id: " + profileId));

        if (profile.getDeveloper() != null) {
            profile.getDeveloper().setProfile(null);
        }

        devProfileRepository.delete(profile);
        log.info("🗑️ Profile permanently deleted - ID: {}", profileId);
    }

    @Override
    public DevProfileDTO getProfileById(Long profileId) {
        log.debug("🔍 Fetching profile with ID: {}", profileId);
        DeveloperProfile profile = devProfileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with id: " + profileId));
        log.info("✅ Profile retrieved - ID: {}", profile.getId());
        return profileMapper.toDTO(profile);
    }
}
