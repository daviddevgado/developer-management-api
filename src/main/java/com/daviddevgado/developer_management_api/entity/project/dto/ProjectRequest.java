package com.daviddevgado.developer_management_api.entity.project.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;

public record ProjectRequest(
      @NotBlank(message = "Project name cannot be blank")
      String name,
      String description){

    public ProjectRequest {
        name = name != null ? name.trim() : null;
        description = description != null ? description.trim() : null;
    }

    @AssertTrue(message = "Description cannot be blank if provided")
    public boolean isDescriptionValid() {
        return description == null || !description.isBlank();
    }
}
