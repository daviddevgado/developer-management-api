package com.daviddevgado.developer_management_api.entity.developer.mapper;

import com.daviddevgado.developer_management_api.entity.developer.Developer;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer.CreateDeveloperRequest;
import com.daviddevgado.developer_management_api.entity.developer.dto.developer.DeveloperDTO;
import com.daviddevgado.developer_management_api.entity.technology.Technology;
import com.daviddevgado.developer_management_api.entity.technology.dto.TechnologyDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DeveloperMapper {

    public DeveloperDTO toDTO(Developer developer) {
        return new DeveloperDTO(
                developer.getId(),
                developer.getName(),
                developer.getEmail(),
                developer.getSeniority(),
                developer.getSalary(),
                mapTechnologiesToDTO(developer.getTechnologies())
        );
    }

    public Developer toEntity(CreateDeveloperRequest developerDTO) {
        return new Developer(
                developerDTO.name(),
                developerDTO.email(),
                developerDTO.seniority(),
                developerDTO.salary()
        );
    }

    private Set<TechnologyDTO> mapTechnologiesToDTO(Set<Technology> technologies) {
        if (technologies == null) {
            return new HashSet<>();
        }
        return technologies.stream()
                .map(this::mapTechnologyToDTO)
                .collect(Collectors.toSet());
    }

    private TechnologyDTO mapTechnologyToDTO (Technology technology) {
        return new TechnologyDTO(
                technology.getId(),
                technology.getName(),
                technology.getDescription()
        );
    }
}
