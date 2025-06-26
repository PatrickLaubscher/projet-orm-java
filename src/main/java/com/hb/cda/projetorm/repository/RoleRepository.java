package com.hb.cda.projetorm.repository;

import com.hb.cda.projetorm.entity.Role;
import com.hb.cda.projetorm.repository.util.GenericRepository;

import jakarta.persistence.EntityManager;

public class RoleRepository extends GenericRepository<Role, Integer> {

    public RoleRepository(EntityManager entityManager) {
        super(entityManager);
    }

   
}
