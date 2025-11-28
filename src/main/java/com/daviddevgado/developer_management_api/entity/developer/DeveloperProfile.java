package com.daviddevgado.developer_management_api.entity.developer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "developer_profiles")
public class DeveloperProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String bio;
    private String linkedinUrl;
    private String gitHubUrl;
    private byte[] profilePicture;

    @OneToOne
    @JoinColumn(name = "developer_id")
    @NotNull
    private Developer developer;

    public DeveloperProfile() {}

    public Long getId() {return id;}
    public String getBio() {return bio;}
    public String getLinkedinUrl() {return linkedinUrl;}
    public String getGitHubUrl() {return gitHubUrl;}
    public Developer getDeveloper() {return developer;}

    public void setBio(String bio) {this.bio = bio;}
    public void setLinkedinUrl(String linkedinUrl) {this.linkedinUrl = linkedinUrl;}
    public void setGitHubUrl(String gitHubUrl) {this.gitHubUrl = gitHubUrl;}
    public void setProfilePicture(byte[] profilePicture) {this.profilePicture = profilePicture;}
    public void setDeveloper(Developer developer) {this.developer = developer;}
}
