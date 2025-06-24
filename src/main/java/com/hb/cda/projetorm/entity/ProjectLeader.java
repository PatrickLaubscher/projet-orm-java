package com.hb.cda.projetorm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "projectLeader")
@PrimaryKeyJoinColumn(name = "id")
public class ProjectLeader extends User {

    
}
