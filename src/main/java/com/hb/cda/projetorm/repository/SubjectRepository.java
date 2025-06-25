package com.hb.cda.projetorm.repository;

import com.hb.cda.projetorm.entity.Subject;
import com.hb.cda.projetorm.repository.util.GenericRepository;

import jakarta.persistence.EntityManager;

public class SubjectRepository extends GenericRepository<Subject, Integer> {
    
    public SubjectRepository(EntityManager entityManager) {
        super(entityManager);
    }

 
}
