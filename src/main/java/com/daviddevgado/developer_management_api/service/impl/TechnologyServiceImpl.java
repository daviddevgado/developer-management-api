package com.daviddevgado.developer_management_api.service.impl;

import com.daviddevgado.developer_management_api.entity.technology.Technology;
import com.daviddevgado.developer_management_api.entity.technology.dto.TechnologyRequest;
import com.daviddevgado.developer_management_api.entity.technology.dto.TechnologyDTO;
import com.daviddevgado.developer_management_api.entity.technology.exception.TechnologyAlreadyExistsException;
import com.daviddevgado.developer_management_api.entity.technology.exception.TechnologyDataInvalidException;
import com.daviddevgado.developer_management_api.entity.technology.exception.TechnologyNotFoundException;
import com.daviddevgado.developer_management_api.entity.technology.mapper.TechnologyMapper;
import com.daviddevgado.developer_management_api.repository.TechnologyRepository;
import com.daviddevgado.developer_management_api.service.TechnologyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public TechnologyDTO addTechnology(TechnologyRequest request) {
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
    public TechnologyDTO updateTechnology(Long id, TechnologyRequest request) {
        log.info("🚀 Starting technology update for ID: {}", id);
        Technology technology = technologyRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn(" Technology with id '{}' not found for update", id);
                    return new TechnologyNotFoundException("Technology with id '" + id + "' not found.");
                });

        log.info("📝 Updating fields for technology {}: {}", id, getUpdatedFields(request));

        if(request.name() != null) {
            String trimmedName = request.name().trim();
            if(trimmedName.isBlank()) {
                log.debug("🔄 Technology update rejected: empty name");
                throw new TechnologyDataInvalidException("Technology name cannot be blank");
            }

            if(technologyRepository.existsByNameIgnoreCaseAndIdNot(trimmedName, id)) {
                log.warn("⚠️ Duplicate technology name '{}' attempted during update for ID: {}", trimmedName,id);
                throw new TechnologyAlreadyExistsException("Technology with name '" + trimmedName + "' already exists.");
            }

            technology.setName(trimmedName);
        }

        if(request.description() != null) {
            String trimmedDescription = request.description().trim();
            technology.setDescription(trimmedDescription.isBlank() ? null : trimmedDescription);
        }

        Technology updatedTechnology = technologyRepository.save(technology);
        log.info("✅ Technology successfully updated - ID: {}, Name: {}",
                updatedTechnology.getId(), updatedTechnology.getName());

        return technologyMapper.toDTO(updatedTechnology);
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

    private String getUpdatedFields(TechnologyRequest request) {
        List<String> updatedFields = new ArrayList<>();
        if (request.name() != null) updatedFields.add("name");
        if (request.description() != null) updatedFields.add("description");
        return String.join(", ", updatedFields);
    }
}
