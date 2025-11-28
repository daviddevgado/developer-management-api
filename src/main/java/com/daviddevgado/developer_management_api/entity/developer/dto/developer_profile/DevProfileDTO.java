package com.daviddevgado.developer_management_api.entity.developer.dto.developer_profile;

public record DevProfileDTO (
        Long idDeveloper,
        String name,
        String bio,
        String gitHubUrl,
        String linkedinUrl) {}
