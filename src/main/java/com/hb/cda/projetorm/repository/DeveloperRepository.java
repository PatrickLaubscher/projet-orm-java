package com.hb.cda.projetorm.repository;

import java.util.List;

import com.hb.cda.projetorm.entity.Developer;
import com.hb.cda.projetorm.repository.util.Database;
import com.hb.cda.projetorm.repository.util.GenericRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class DeveloperRepository extends GenericRepository<Developer, Integer> {


    @Override
    public List<Developer> findAll(Class<Developer> type) {
        try {
            EntityManager em = Database.getManager();
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
        try {
            EntityManager em = Database.getManager();
            Developer foundEntity = em.find(entityType, id);
            return foundEntity;

        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    
    @Override
    public boolean persist(Developer entity) {
        try {
            EntityManager em = Database.getManager();
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
        try  {
            EntityManager em = Database.getManager();
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
        try {
            EntityManager em = Database.getManager();
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
