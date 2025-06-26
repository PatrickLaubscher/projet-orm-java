package com.hb.cda.projetorm.repository;

import java.util.List;

import com.hb.cda.projetorm.entity.Project;
import com.hb.cda.projetorm.entity.ProjectApplication;
import com.hb.cda.projetorm.entity.ProjectLeader;
import com.hb.cda.projetorm.repository.interfaces.ProjectApplicationRepository;
import com.hb.cda.projetorm.repository.util.AbstractRepository;
import com.hb.cda.projetorm.repository.util.Database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public class ProjectApplicationRepositoryImpl extends AbstractRepository<ProjectApplication, Integer> implements ProjectApplicationRepository {


    public ProjectApplicationRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public List<ProjectApplication> findAll(Class<ProjectApplication> type) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<ProjectApplication> criteriaQuery = criteriaBuilder.createQuery(type);
            criteriaQuery.select(criteriaQuery.from(type));
            TypedQuery<ProjectApplication> query = em.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }


    @Override
    public ProjectApplication findById(Class<ProjectApplication> type, Integer id) {
        EntityManager em = getEntityManager();
        try {
            ProjectApplication foundEntity = em.find(type, id);
            return foundEntity;
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    
    @Override
    public boolean persist(ProjectApplication type) {
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
    public boolean update(ProjectApplication type) {
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
    public boolean delete(ProjectApplication type) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            ProjectApplication merged = em.merge(type);
            em.remove(merged);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }

    
    @Override
    public List<ProjectApplication> findAllProjectApplicationByProject(ProjectLeader leader) {
        try {
            EntityManager em = Database.getManager();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<ProjectApplication> criteriaQuery = criteriaBuilder.createQuery(ProjectApplication.class);
            Root<ProjectApplication> root = criteriaQuery.from(ProjectApplication.class);
            Join<ProjectApplication, Project> projectJoin = root.join("project");
            criteriaQuery.select(root).where(criteriaBuilder.equal(projectJoin.get("leader"), leader));
            TypedQuery<ProjectApplication> query = em.createQuery(criteriaQuery);
            return query.getResultList();

        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }
    
}
