package com.hb.cda.projetorm.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "developer")
@PrimaryKeyJoinColumn(name = "id")
public class Developer extends User {

    @Column(name="description", columnDefinition="TEXT")
    private String description;

    @ManyToMany
    private List<Hardskill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "developer")
    private List<ProjectApplication> applications;

        
    public Developer() {
    }

    public Developer(String name, String firstname, String email, String password, LocalDateTime createdAt,
            String role, String description) {
        super(name, firstname, email, password, createdAt, role);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Hardskill> getSkills() {
        return skills;
    }

    public void setSkills(List<Hardskill> skills) {
        this.skills = skills;
    }

    public List<ProjectApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<ProjectApplication> applications) {
        this.applications = applications;
    }

    
    
}
