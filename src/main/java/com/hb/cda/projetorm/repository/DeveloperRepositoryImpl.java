package com.hb.cda.projetorm.repository;


import java.util.List;

import com.hb.cda.projetorm.entity.Developer;
import com.hb.cda.projetorm.repository.interfaces.DeveloperRepository;
import com.hb.cda.projetorm.repository.util.AbstractRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;


public class DeveloperRepositoryImpl extends AbstractRepository<Developer, Integer> implements DeveloperRepository {


    public DeveloperRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
    

    @Override
    public List<Developer> findAll(Class<Developer> type) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Developer> criteriaQuery = criteriaBuilder.createQuery(type);
            criteriaQuery.select(criteriaQuery.from(type));
            TypedQuery<Developer> query = em.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    
    @Override
    public Developer findById(Class<Developer> entityType, Integer id) {
        EntityManager em = getEntityManager();
        try {
            Developer foundEntity = em.find(entityType, id);
            return foundEntity;
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }


    @Override
    public boolean persist(Developer entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }


    @Override
    public boolean update(Developer entity) {
        EntityManager em = getEntityManager();
        try  {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }


    @Override
    public boolean delete(Developer entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Developer merged = em.merge(entity);
            em.remove(merged);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }

    
}
