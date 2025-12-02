package com.daviddevgado.developer_management_api.service.impl;

import com.daviddevgado.developer_management_api.entity.technology.Technology;
import com.daviddevgado.developer_management_api.entity.technology.dto.CreateTechnologyRequest;
import com.daviddevgado.developer_management_api.entity.technology.dto.TechnologyDTO;
import com.daviddevgado.developer_management_api.entity.technology.dto.UpdateTechnologyRequest;
import com.daviddevgado.developer_management_api.entity.technology.exception.TechnologyAlreadyExistsException;
import com.daviddevgado.developer_management_api.entity.technology.exception.TechnologyDataInvalidException;
import com.daviddevgado.developer_management_api.entity.technology.mapper.TechnologyMapper;
import com.daviddevgado.developer_management_api.repository.TechnologyRepository;
import com.daviddevgado.developer_management_api.service.TechnologyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private static final Logger log = LoggerFactory.getLogger(TechnologyServiceImpl.class);

    private final TechnologyRepository technologyRepository;
    private final TechnologyMapper technologyMapper;

    public TechnologyServiceImpl (TechnologyRepository technologyRepository, TechnologyMapper technologyMapper) {
        this.technologyRepository = technologyRepository;
        this.technologyMapper = technologyMapper;
    }

    @Override
    @Transactional
    public TechnologyDTO addTechnology(CreateTechnologyRequest request) {
        log.info("🚀 Starting technology creation with name: {}", request.name());
        if (technologyRepository.existsByNameIgnoreCase(request.name())) {
            log.warn("⚠️ Duplicate technology creation attempted: '{}'", request.name());
            throw new TechnologyAlreadyExistsException("Technology with name '" + request.name() + "' already exists.");
        }

        if(request.name() == null || request.name().trim().isBlank()) {
            log.debug("🔄 Technology creation rejected: empty name");
            throw new TechnologyDataInvalidException("Technology name cannot be null or blank.");
        }

        Technology newTechnology = technologyMapper.toEntity(request);
        Technology savedTechnology = technologyRepository.save(newTechnology);
        log.info("✅ Technology successfully created - ID: {}, Name: {}",
                savedTechnology.getId(), savedTechnology.getName());

        return technologyMapper.toDTO(savedTechnology);
    }

    @Override
    @Transactional
    public TechnologyDTO updateTechnology(Long id, UpdateTechnologyRequest request) {
        return null;
    }

    @Override
    @Transactional
    public TechnologyDTO deleteTechnology(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public TechnologyDTO getTechnologyById(Long id) {
        return null;
    }
}
