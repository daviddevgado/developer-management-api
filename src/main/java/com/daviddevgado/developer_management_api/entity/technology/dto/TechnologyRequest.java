package com.daviddevgado.developer_management_api.entity.technology.dto;

import jakarta.validation.constraints.NotBlank;

public record TechnologyRequest(
    @NotBlank String name,
    String description){ }