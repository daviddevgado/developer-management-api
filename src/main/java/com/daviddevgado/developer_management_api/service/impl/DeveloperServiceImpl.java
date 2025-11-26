package com.daviddevgado.developer_management_api.service.impl;

import com.daviddevgado.developer_management_api.entity.developer.Developer;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer.CreateDeveloperRequest;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer.DeveloperDTO;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer.UpdateDeveloperRequest;
import com.daviddevgado.developer_management_api.entity.developer.exception.DeveloperNotFoundException;
import com.daviddevgado.developer_management_api.entity.developer.exception.EmailAlreadyExistsException;
import com.daviddevgado.developer_management_api.entity.developer.exception.InvalidDeveloperDataException;
import com.daviddevgado.developer_management_api.entity.developer.mapper.DeveloperMapper;
import com.daviddevgado.developer_management_api.repository.DeveloperRepository;
import com.daviddevgado.developer_management_api.service.DeveloperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private static final Logger log = LoggerFactory.getLogger(DeveloperServiceImpl.class);

    private final DeveloperRepository developerRepository;
    private final DeveloperMapper developerMapper;

    public DeveloperServiceImpl(DeveloperRepository developerRepository, DeveloperMapper developerMapper) {
        this.developerRepository = developerRepository;
        this.developerMapper = developerMapper;
    }

    @Override
    public DeveloperDTO addDeveloper(CreateDeveloperRequest request) {
        log.info("🚀 Starting developer creation for email: {}", request.email());
        Developer developer = developerMapper.toEntity(request);
        Developer savedDeveloper = developerRepository.save(developer);
        log.info("✅ Developer successfully created - ID: {}, Email: {}",
                savedDeveloper.getId(), savedDeveloper.getEmail());
        return developerMapper.toDTO(savedDeveloper);
    }

    @Override
    public DeveloperDTO updateDeveloper(Long developerId, UpdateDeveloperRequest request) {
        log.info("🚀 Starting developer updating for id: {}", developerId);
        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new DeveloperNotFoundException("Developer not found with id: " + developerId));

        log.info("📝 Updating fields for developer {}: {}", developerId, getUpdatedFields(request));

        if(request.name() != null) {
            String trimmedName = request.name().trim();
            if(trimmedName.isBlank()) {
                throw new InvalidDeveloperDataException("Name cannot be blank");
            }
            developer.setName(trimmedName);
        }

        if(request.email() != null) {
            String trimmedEmail = request.email().trim();
            if (trimmedEmail.isBlank()) {
                throw new InvalidDeveloperDataException("Email cannot be blank");
            }
            Optional<Developer> existingDeveloper = developerRepository.findByEmailIgnoreCase(trimmedEmail);
            boolean emailBelongsToOtherPeople = existingDeveloper.isPresent() && !existingDeveloper.get().getId().equals(developerId);

            if(emailBelongsToOtherPeople) {
                throw new EmailAlreadyExistsException("Email " + trimmedEmail + " already exists");
            }
            developer.setEmail(trimmedEmail);
        }

        if(request.seniority() != null) {
            developer.setSeniority(request.seniority());
        }

        if(request.salary() != null) {
            developer.setSalary(request.salary());
        }

        Developer updatedDeveloper = developerRepository.save(developer);
        log.info("✅ Developer successfully updated - ID: {}, Email: {}",
                updatedDeveloper.getId(), updatedDeveloper.getEmail());

        return developerMapper.toDTO(updatedDeveloper);
    }

    @Override
    public void deleteDeveloper(Long developerId) {
        log.info("🚀 Starting developer deleting for id: {}", developerId);
        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new DeveloperNotFoundException("Developer not found with id: " + developerId));

        if(!developer.getActive()) {
            throw new InvalidDeveloperDataException("Developer with id " + developerId + " is already inactive");
        }

        developerRepository.delete(developer);
        log.info("🗑️ Developer permanently deleted - ID: {}", developerId);
    }

    @Override
    public DeveloperDTO getDeveloperById(Long developerId) {
        log.debug("🔍 Fetching developer with ID: {}", developerId);
        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new DeveloperNotFoundException("Developer not found with id: " + developerId));
        log.info("✅ Developer retrieved - ID: {}, Email: {}", developer.getId(), developer.getEmail());
        return developerMapper.toDTO(developer);
    }

    private String getUpdatedFields(UpdateDeveloperRequest request) {
        List<String> fields = new ArrayList<>();
        if (request.name() != null) fields.add("name");
        if (request.email() != null) fields.add("email");
        if (request.seniority() != null) fields.add("seniority");
        if (request.salary() != null) fields.add("salary");
        return String.join(", ", fields);
    }
}
