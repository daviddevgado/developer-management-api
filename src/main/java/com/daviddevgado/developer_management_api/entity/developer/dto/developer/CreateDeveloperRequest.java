package com.daviddevgado.developer_management_api.entity.developer.dto.developer;

import com.daviddevgado.developer_management_api.entity.developer.Seniority;
import com.daviddevgado.developer_management_api.entity.technology.Technology;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;

public record CreateDeveloperRequest (
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotNull Seniority seniority,
        BigDecimal salary,
        Set<Technology> technologyIds){ }
