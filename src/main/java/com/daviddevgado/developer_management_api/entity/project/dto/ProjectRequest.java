package com.daviddevgado.developer_management_api.entity.project.dto;

import jakarta.validation.constraints.NotBlank;

public record ProjectRequest(
      @NotBlank String name,
      String description){ }
