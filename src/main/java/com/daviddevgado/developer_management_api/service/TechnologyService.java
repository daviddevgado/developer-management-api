package com.daviddevgado.developer_management_api.service;

import com.daviddevgado.developer_management_api.entity.technology.dto.TechnologyRequest;
import com.daviddevgado.developer_management_api.entity.technology.dto.TechnologyDTO;

public interface TechnologyService {
    TechnologyDTO addTechnology(TechnologyRequest request);
    TechnologyDTO updateTechnology(Long id, TechnologyRequest request);
    TechnologyDTO deleteTechnology(Long id);
    TechnologyDTO getTechnologyById(Long id);
}
