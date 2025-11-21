package com.daviddevgado.developer_management_api.entity.developer.dto.developer;

import com.daviddevgado.developer_management_api.entity.developer.Seniority;
import com.daviddevgado.developer_management_api.entity.project_assignment.dto.ProjAssignmentDTO;
import com.daviddevgado.developer_management_api.entity.technology.dto.TechnologyDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record DeveloperDTO(
        Long id,
        String name,
        String email,
        Seniority seniority,
        BigDecimal salary,
        Set<TechnologyDTO> technologies
) {
}
