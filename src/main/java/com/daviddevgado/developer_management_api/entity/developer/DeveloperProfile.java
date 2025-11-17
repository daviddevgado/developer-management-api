package com.daviddevgado.developer_management_api.entity.developer;

import jakarta.persistence.*;

@Entity
@Table(name = "developer_profiles")
public class DeveloperProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;
    private String linkedinUrl;
    private String gitHubUrl;

    private byte[] profilePicture;

    @OneToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;
}
