package com.hb.cda.projetorm.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "developer")
@PrimaryKeyJoinColumn(name = "id")
public class Developer extends User {

    @Column(name="description", columnDefinition="TEXT")
    private String description;

        
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

    
    
}
