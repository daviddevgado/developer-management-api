package com.daviddevgado.developer_management_api.entity.developer;

import com.daviddevgado.developer_management_api.entity.project_assignment.ProjectAssignment;
import com.daviddevgado.developer_management_api.entity.technology.Technology;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "developer_technologies",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private Set<Technology> technologies = new HashSet<>();

    @OneToMany(mappedBy = "developer")
    private List<ProjectAssignment> projectAssignments = new ArrayList<>();

    @OneToOne(mappedBy = "developer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DeveloperProfile profile;

    @Column(nullable = false)
    private LocalDateTime startedAt = LocalDateTime.now();

    private LocalDateTime finishedAt;
    private String finishedReason;
    private String finishedBy;

    @Column(nullable = false)
    private Boolean active = true;

    public Developer() {}

    public Developer(String name, String email, Seniority seniority, BigDecimal salary) {
        this.name = name;
        this.email = email;
        this.seniority = seniority;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Set<Technology> getTechnologies() {
        return technologies;
    }
}
