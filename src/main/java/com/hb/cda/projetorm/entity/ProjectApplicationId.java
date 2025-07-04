package com.hb.cda.projetorm.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProjectApplicationId implements Serializable {
    private Integer developerId;
    private Integer projectId;


    public ProjectApplicationId() {}

    public ProjectApplicationId(Integer developerId, Integer projectId) {
        this.developerId = developerId;
        this.projectId = projectId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectApplicationId)) return false;
        ProjectApplicationId that = (ProjectApplicationId) o;
        return Objects.equals(developerId, that.developerId) &&
               Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(developerId, projectId);
    }

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    
}