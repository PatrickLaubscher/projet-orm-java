package com.hb.cda.projetorm.entity;

import java.time.LocalDateTime;
import java.util.Date;
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
    private Date deliveryDate;
    @Column(name = "budget")
    private Integer budget;
    @Column(name = "created_at", columnDefinition="DATETIME")
    private LocalDateTime createdAt;
    @Column(name = "subject_id")
    private Integer subject;

    @OneToMany(mappedBy = "project")
    private List<ProjectCandidacyList> candidacies;     


    public Project() {
    }
    
    public Project(String title, String description, Date deliveryDate, Integer budget, LocalDateTime createdAt, Integer subject) {
        this.title = title;
        this.description = description;
        this.deliveryDate = deliveryDate;
        this.budget = budget;
        this.createdAt = createdAt;
        this.subject = subject;
    }

    public Project(Integer id, String title, String description, Date deliveryDate, Integer budget,
            LocalDateTime createdAt, Integer subject) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deliveryDate = deliveryDate;
        this.budget = budget;
        this.createdAt = createdAt;
        this.subject = subject;
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
    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
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

    public List<ProjectCandidacyList> getCandidacies() {
        return candidacies;
    }

    public void setCandidacies(List<ProjectCandidacyList> candidacies) {
        this.candidacies = candidacies;
    }

    
    


}
