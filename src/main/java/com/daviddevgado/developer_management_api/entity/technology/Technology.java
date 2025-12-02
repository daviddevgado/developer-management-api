package com.daviddevgado.developer_management_api.entity.technology;

import com.daviddevgado.developer_management_api.entity.developer.Developer;
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

    @ManyToMany(mappedBy = "technologies")
    private Set<Developer> developers = new HashSet<>();

    public Technology() {}

    public Technology (String name, String description){
        this.name = name;
        this.description = description;
    }

    public Long getId() {return id;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public Set<Project> getProjects() {return projects;}
    public Set<Developer> getDevelopers() {return developers;}

    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
}
