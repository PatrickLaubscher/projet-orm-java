package com.hb.cda.projetorm.repository;

import java.util.List;

import com.hb.cda.projetorm.entity.Subject;
import com.hb.cda.projetorm.repository.interfaces.SubjectRepository;
import com.hb.cda.projetorm.repository.util.AbstractRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class SubjectRepositoryImpl extends AbstractRepository<Subject, Integer> implements SubjectRepository {
    
    public SubjectRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Subject> findAll(Class<Subject> type) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Subject> criteriaQuery = criteriaBuilder.createQuery(type);
            criteriaQuery.select(criteriaQuery.from(type));
            TypedQuery<Subject> query = em.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public Subject findById(Class<Subject> type, Integer id) {
        EntityManager em = getEntityManager();
        try {
            Subject foundEntity = em.find(type, id);
            return foundEntity;
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override    
    public boolean persist(Subject type) {
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

    @Override
    public boolean update(Subject type) {
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


    @Override
    public boolean delete(Subject type) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Subject merged = em.merge(type);
            em.remove(merged);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }
}
