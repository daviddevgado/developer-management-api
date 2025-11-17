package com.daviddevgado.developer_management_api.entity.developer;

import com.daviddevgado.developer_management_api.entity.project_assignment.ProjectAssignment;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Seniority seniority;

    private BigDecimal salary;

    @ElementCollection
    private List<String> techStack = new ArrayList<>();

    @OneToMany(mappedBy = "developer")
    private List<ProjectAssignment> projectAssignments = new ArrayList<>();

    @OneToOne(mappedBy = "developer", cascade = CascadeType.ALL)
    private DeveloperProfile profile;

    private LocalDateTime createdAt;

    private Boolean active;
}
