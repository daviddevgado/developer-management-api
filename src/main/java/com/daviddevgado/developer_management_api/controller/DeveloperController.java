package com.daviddevgado.developer_management_api.controller;

import com.daviddevgado.developer_management_api.entity.developer.dto.developer.CreateDeveloperRequest;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer.DeveloperDTO;
import com.daviddevgado.developer_management_api.service.DeveloperService;
import com.daviddevgado.developer_management_api.service.impl.DeveloperServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    private static final Logger log = LoggerFactory.getLogger(DeveloperController.class);

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping
    public ResponseEntity<DeveloperDTO> createDeveloper(@Valid @RequestBody CreateDeveloperRequest request) {
        log.info("📥 POST /api/v1/developers - Email: {}", request.email());
        try {
            DeveloperDTO developerCreated = developerService.addDeveloper(request);
            log.info("📤 Response 201 - Developer ID: {}", developerCreated.id());
            return ResponseEntity.status(HttpStatus.CREATED).body(developerCreated);
        } catch (Exception e) {
            log.error("❌ Error creating developer: {}", e.getMessage());
            throw e;
        }
    }
}
