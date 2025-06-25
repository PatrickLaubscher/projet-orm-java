package com.hb.cda.projetorm.repository.util;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;


public abstract class GenericRepository<T, K> {
    

    public List<T> findAll(Class<T> type) {
        try {
            EntityManager em = Database.getManager();
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


    public T findById(Class<T> entityType, K id) {
        try {
            EntityManager em = Database.getManager();
            T foundEntity = em.find(entityType, id);
            return foundEntity;

        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    
    public boolean persist(T entity) {
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


    boolean update(T entity) {
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



    boolean delete(T entity) {
        try {
            EntityManager em = Database.getManager();
            em.getTransaction().begin();
            //On commence par faire un merge dans le delete pour le cas où l'entité qu'on lui
            //fournirai serait une entité détachée. Le remove n'acceptant que les entités managées
            T merged = em.merge(entity);
            em.remove(merged);

            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }


} 