package com.hb.cda.projetorm.repository;

import com.hb.cda.projetorm.entity.Hardskill;
import com.hb.cda.projetorm.repository.util.GenericRepository;

import jakarta.persistence.EntityManager;

public class HardskillRepository extends GenericRepository<Hardskill, Integer> {
    
    public HardskillRepository(EntityManager entityManager) {
        super(entityManager);
    }


}
