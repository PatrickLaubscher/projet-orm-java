package com.hb.cda.projetorm.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;


@Entity
@Table(name = "project_application")
public class ProjectApplication {

    @EmbeddedId
    private ProjectApplicationId id;

    @ManyToOne
    @MapsId("developerId")
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;
    
    @Column(name="is_validated")
    private Boolean isValidated;
    @Column(name = "created_at", columnDefinition="DATETIME")
    private LocalDateTime createdAt;

    public ProjectApplicationId getId() {
        return id;
    }
    public void setId(ProjectApplicationId id) {
        this.id = id;
    }


    public Developer getDeveloper() {
        return developer;
    }
    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public Boolean getIsValidated() {
        return isValidated;
    }
    public void setIsValidated(Boolean isValidated) {
        this.isValidated = isValidated;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }




}
