package com.daviddevgado.developer_management_api.entity.project_assignment;

import com.daviddevgado.developer_management_api.entity.developer.Developer;
import com.daviddevgado.developer_management_api.entity.project.Project;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "project_assignments")
public class ProjectAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Developer developer;

    @ManyToOne
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectRole role;

    private LocalDateTime assignedAt;

    private LocalDateTime finishedAt;

    private Integer hoursPerWeek;

    private Boolean active;
}
