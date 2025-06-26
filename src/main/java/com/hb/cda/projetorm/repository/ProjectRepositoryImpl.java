package com.hb.cda.projetorm.repository;

import java.util.List;

import com.hb.cda.projetorm.entity.Project; 
import com.hb.cda.projetorm.entity.ProjectLeader;
import com.hb.cda.projetorm.repository.interfaces.ProjectRepository;
import com.hb.cda.projetorm.repository.util.AbstractRepository;
import com.hb.cda.projetorm.repository.util.Database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;



public class ProjectRepositoryImpl extends AbstractRepository<Project, Integer> implements ProjectRepository {

    public ProjectRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Project> findAll(Class<Project> type) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(type);
            criteriaQuery.select(criteriaQuery.from(type));
            TypedQuery<Project> query = em.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public Project findById(Class<Project> type, Integer id) {
        EntityManager em = getEntityManager();
        try {
            Project foundEntity = em.find(type, id);
            return foundEntity;
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override    
    public boolean persist(Project type) {
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
    public boolean update(Project type) {
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
    public boolean delete(Project type) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Project merged = em.merge(type);
            em.remove(merged);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }

    @Override
    public List<Project> findAllProjectByProjectLeader(ProjectLeader leader) {
        try {
            EntityManager em = Database.getManager();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
            Root<Project> root = criteriaQuery.from(Project.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("leader"), leader));
            TypedQuery<Project> query = em.createQuery(criteriaQuery);
            return query.getResultList();

        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }
    
    @Override
    public List<Project> findAllProjectByDate(String date) {
        try {
            EntityManager em = Database.getManager();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
            Root<Project> root = criteriaQuery.from(Project.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("date"), date));
            TypedQuery<Project> query = em.createQuery(criteriaQuery);
            return query.getResultList();

        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public List<Project> findAllProjectsBySubject(String subject) {
        try {
            EntityManager em = Database.getManager();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
            Root<Project> root = criteriaQuery.from(Project.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("subject"), subject));
            TypedQuery<Project> query = em.createQuery(criteriaQuery);
            return query.getResultList();

        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

@Override
    public List<Project> findAllProjectsByBudget(Integer budget) {
        try {
            EntityManager em = Database.getManager();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
            Root<Project> root = criteriaQuery.from(Project.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("budget"), budget));
            TypedQuery<Project> query = em.createQuery(criteriaQuery);
            return query.getResultList();

        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }
}