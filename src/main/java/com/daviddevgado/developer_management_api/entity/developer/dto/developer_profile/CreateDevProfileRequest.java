package com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record CreateDevProfileRequest (
        @Size(max = 500, message = "Bio must be at most 500 characters")
        String bio,

        @URL(message = "LinkedIn URL must be valid")
        @Pattern(regexp = ".*linkedin\\.com.*", message = "Must be a LinkedIn URL")
        String linkedinUrl,

        @URL(message = "GitHub URL must be valid")
        @Pattern(regexp = ".*github\\.com.*", message = "Must be a GitHub URL")
        String gitHubUrl
) {}
