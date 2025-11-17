package com.daviddevgado.developer_management_api.entity.project;

import com.daviddevgado.developer_management_api.entity.project_assignment.ProjectAssignment;
import com.daviddevgado.developer_management_api.entity.technology.Technology;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ManyToMany
    @JoinTable(
            name = "project_technologies",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private Set<Technology> technologies = new HashSet<>();

    @OneToMany(mappedBy = "project")
    private final List<ProjectAssignment> developerAssignments = new ArrayList<>();
}
