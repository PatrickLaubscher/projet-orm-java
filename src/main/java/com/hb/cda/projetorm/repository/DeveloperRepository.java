package com.hb.cda.projetorm.repository;


import com.hb.cda.projetorm.entity.Developer;
import com.hb.cda.projetorm.repository.util.GenericRepository;

import jakarta.persistence.EntityManager;


public class DeveloperRepository extends GenericRepository<Developer, Integer> {

    public DeveloperRepository(EntityManager entityManager) {
        super(entityManager);
    }

    
}
