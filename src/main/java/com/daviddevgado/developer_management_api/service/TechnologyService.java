package com.daviddevgado.developer_management_api.service;

import com.daviddevgado.developer_management_api.entity.technology.dto.CreateTechnologyRequest;
import com.daviddevgado.developer_management_api.entity.technology.dto.TechnologyDTO;
import com.daviddevgado.developer_management_api.entity.technology.dto.UpdateTechnologyRequest;

public interface TechnologyService {
    TechnologyDTO addTechnology(CreateTechnologyRequest request);
    TechnologyDTO updateTechnology(Long id, UpdateTechnologyRequest request);
    TechnologyDTO deleteTechnology(Long id);
    TechnologyDTO getTechnologyById(Long id);
}
