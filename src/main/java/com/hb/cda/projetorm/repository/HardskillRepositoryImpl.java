package com.hb.cda.projetorm.repository;

import java.util.List;

import com.hb.cda.projetorm.entity.Hardskill;
import com.hb.cda.projetorm.repository.interfaces.HardskillRepository;
import com.hb.cda.projetorm.repository.util.AbstractRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;


public class HardskillRepositoryImpl extends AbstractRepository<Hardskill, Integer> implements HardskillRepository {
    
    public HardskillRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public List<Hardskill> findAll(Class<Hardskill> type) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Hardskill> criteriaQuery = criteriaBuilder.createQuery(type);
            criteriaQuery.select(criteriaQuery.from(type));
            TypedQuery<Hardskill> query = em.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    
    @Override
    public Hardskill findById(Class<Hardskill> entityType, Integer id) {
        EntityManager em = getEntityManager();
        try {
            Hardskill foundEntity = em.find(entityType, id);
            return foundEntity;
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }


    @Override
    public boolean persist(Hardskill entity) {
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
    public boolean update(Hardskill entity) {
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
    public boolean delete(Hardskill entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Hardskill merged = em.merge(entity);
            em.remove(merged);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }

}
