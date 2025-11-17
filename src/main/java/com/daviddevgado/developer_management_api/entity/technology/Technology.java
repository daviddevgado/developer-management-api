package com.daviddevgado.developer_management_api.entity.technology;

import com.daviddevgado.developer_management_api.entity.project.Project;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "technologies")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "technologies")
    private Set<Project> projects = new HashSet<>();
}
