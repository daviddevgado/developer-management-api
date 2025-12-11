package com.daviddevgado.developer_management_api.controller;

import com.daviddevgado.developer_management_api.entity.developer.dto.developer.CreateDeveloperRequest;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer.DeveloperDTO;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer.UpdateDeveloperRequest;
import com.daviddevgado.developer_management_api.service.DeveloperService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        DeveloperDTO developerCreated = developerService.addDeveloper(request);
        log.info("📝 Created developer ID: {} - Email: {}", developerCreated.id(), developerCreated.email());
        return ResponseEntity.status(HttpStatus.CREATED).body(developerCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeveloperDTO> getDeveloperById(@PathVariable Long id) {
        log.info("📥 GET /api/v1/developers/{}", id);
        DeveloperDTO developer = developerService.getDeveloperById(id);
        log.info("✅ Developer retrieved - ID: {}, Email: {}", developer.id(), developer.email());
        return ResponseEntity.ok(developer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeveloperDTO> updateDeveloper(@PathVariable Long id,
                                                        @Valid @RequestBody UpdateDeveloperRequest request) {
        log.info("📥 PUT /api/v1/developers/{id} - Starting update: {}", id);
        DeveloperDTO developerUpdated = developerService.updateDeveloper(id, request);
        log.info("✅ Developer updated - ID: {}, Email: {}", developerUpdated.id(), developerUpdated.email());
        return ResponseEntity.ok(developerUpdated);
    }






}
