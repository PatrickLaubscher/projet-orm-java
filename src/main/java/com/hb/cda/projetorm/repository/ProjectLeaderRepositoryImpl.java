package com.hb.cda.projetorm.repository;

import java.util.List;

import com.hb.cda.projetorm.entity.ProjectLeader;
import com.hb.cda.projetorm.repository.interfaces.ProjectLeaderRepository;
import com.hb.cda.projetorm.repository.util.AbstractRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;


public class ProjectLeaderRepositoryImpl extends AbstractRepository<ProjectLeader, Integer> implements ProjectLeaderRepository {

    
    public ProjectLeaderRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public List<ProjectLeader> findAll(Class<ProjectLeader> type) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<ProjectLeader> criteriaQuery = criteriaBuilder.createQuery(type);
            criteriaQuery.select(criteriaQuery.from(type));
            TypedQuery<ProjectLeader> query = em.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    
    @Override
    public ProjectLeader findById(Class<ProjectLeader> entityType, Integer id) {
        EntityManager em = getEntityManager();
        try {
            ProjectLeader foundEntity = em.find(entityType, id);
            return foundEntity;
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }


    @Override
    public boolean persist(ProjectLeader entity) {
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
    public boolean update(ProjectLeader entity) {
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
    public boolean delete(ProjectLeader entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            ProjectLeader merged = em.merge(entity);
            em.remove(merged);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }

}
