package com.hb.cda.projetorm.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name="description", columnDefinition="TEXT")
    private String description;
    @Column(name = "delivery_date", columnDefinition="DATE")
    private LocalDate deliveryDate;
    @Column(name = "budget")
    private Integer budget;
    @Column(name = "created_at", columnDefinition="DATETIME")
    private LocalDateTime createdAt;
    @Column(name = "subject_id")
    private Integer subject;
    @Column(name = "project_leader_id")
    private Integer projectleaderId;

    @OneToMany(mappedBy = "project")
    private List<ProjectApplication> applications;     



    public Project() {
    }
    
    public Project(Integer id, String title, String description, LocalDate deliveryDate, Integer budget,
            LocalDateTime createdAt, Integer subject, Integer projectleaderId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deliveryDate = deliveryDate;
        this.budget = budget;
        this.createdAt = createdAt;
        this.subject = subject;
        this.projectleaderId = projectleaderId;
    }

    public Project(String title, String description, LocalDate deliveryDate, Integer budget,
            LocalDateTime createdAt, Integer subject, Integer projectleaderId) {
        this.title = title;
        this.description = description;
        this.deliveryDate = deliveryDate;
        this.budget = budget;
        this.createdAt = createdAt;
        this.subject = subject;
        this.projectleaderId = projectleaderId;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public Integer getBudget() {
        return budget;
    }
    public void setBudget(Integer budget) {
        this.budget = budget;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public Integer getSubject() {
        return subject;
    }
    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public List<ProjectApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<ProjectApplication> applications) {
        this.applications = applications;
    }

    public Integer getProjectleaderId() {
        return projectleaderId;
    }

    public void setProjectleaderId(Integer projectleaderId) {
        this.projectleaderId = projectleaderId;
    }

    
    


}
