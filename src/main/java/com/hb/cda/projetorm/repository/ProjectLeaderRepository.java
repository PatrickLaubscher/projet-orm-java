package com.hb.cda.projetorm.repository;

import com.hb.cda.projetorm.entity.ProjectLeader; 
import com.hb.cda.projetorm.repository.util.GenericRepository;

import jakarta.persistence.EntityManager;


public class ProjectLeaderRepository extends GenericRepository<ProjectLeader, Integer> {

    public ProjectLeaderRepository(EntityManager entityManager) {
        super(entityManager);
    }


}
