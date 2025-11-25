package com.daviddevgado.developer_management_api.entity.developer.dto.developer;

import com.daviddevgado.developer_management_api.entity.developer.Seniority;

import java.math.BigDecimal;

public record UpdateDeveloperRequest(
        String name,
        String email,
        Seniority seniority,
        BigDecimal salary) {}
