package com.hb.cda.projetorm.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name= "firstname")
    private String firstname;
    @Column(name= "email")
    private String email;
    @Column(name= "password")
    private String password;
    @Column(name = "created_at", columnDefinition="DATETIME")
    private LocalDateTime createdAt;
    @Column(name = "updated_at", columnDefinition="DATETIME")
    private LocalDateTime updatedAt;
    @Column(name= "role_id")
    private String role;

}
