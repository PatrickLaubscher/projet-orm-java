package com.hb.cda.projetorm.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "projectLeader")
@PrimaryKeyJoinColumn(name = "id")
public class ProjectLeader extends User {


    @OneToMany(mappedBy = "projectleaderId")
    private List<Project> project;

    public List<Project> getProject() {
        return project;
    }

    public void setProject(List<Project> project) {
        this.project = project;
    }

}
