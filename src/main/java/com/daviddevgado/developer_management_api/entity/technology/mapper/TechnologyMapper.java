package com.daviddevgado.developer_management_api.entity.technology.mapper;

import com.daviddevgado.developer_management_api.entity.technology.Technology;
import com.daviddevgado.developer_management_api.entity.technology.dto.CreateTechnologyRequest;
import com.daviddevgado.developer_management_api.entity.technology.dto.TechnologyDTO;
import org.springframework.stereotype.Component;

@Component
public class TechnologyMapper {

    public Technology toEntity(CreateTechnologyRequest request) {
        return new Technology(request.name(), request.description());
    }

    public TechnologyDTO toDTO(Technology technology) {
        return new TechnologyDTO(
                technology.getId(),
                technology.getName(),
                technology.getDescription()
        );
    }
}
