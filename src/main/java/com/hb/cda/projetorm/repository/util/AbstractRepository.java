package com.hb.cda.projetorm.repository.util;

import java.util.List;

import com.hb.cda.projetorm.repository.interfaces.GenericRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;


public abstract class AbstractRepository<T, K> implements GenericRepository<T, K> {
    
    private final EntityManager entityManager;

    public AbstractRepository() {
        this.entityManager = null;
    }
    
    public AbstractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    protected EntityManager getEntityManager() {
        return entityManager != null ? entityManager : Database.getManager();
    }


    
    public List<T> findAll(Class<T> type) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
            criteriaQuery.select(criteriaQuery.from(type));
            TypedQuery<T> query = em.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }


    public T findById(Class<T> type, K id) {
        EntityManager em = getEntityManager();
        try {
            T foundEntity = em.find(type, id);
            return foundEntity;
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    
    public boolean persist(T type) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(type);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }


    public boolean update(T type) {
        EntityManager em = getEntityManager();
        try  {
            em.getTransaction().begin();
            em.merge(type);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }



    public boolean delete(T type) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T merged = em.merge(type);
            em.remove(merged);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }


} 