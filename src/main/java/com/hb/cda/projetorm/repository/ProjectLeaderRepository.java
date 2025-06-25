package com.hb.cda.projetorm.repository;

import java.util.List;

import com.hb.cda.projetorm.entity.ProjectLeader; 
import com.hb.cda.projetorm.repository.util.Database;
import com.hb.cda.projetorm.repository.util.GenericRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;




public class ProjectLeaderRepository extends GenericRepository<ProjectLeader, Integer> {


    @Override
    public List<ProjectLeader> findAll(Class<ProjectLeader> type) {
        try {
            EntityManager em = Database.getManager();
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
        try {
            EntityManager em = Database.getManager();
            ProjectLeader foundEntity = em.find(entityType, id);
            return foundEntity;

        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    
    @Override
    public boolean persist(ProjectLeader entity) {
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
    public boolean update(ProjectLeader entity) {
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



    boolean delete(ProjectLeader entity) {
        try {
            EntityManager em = Database.getManager();
            em.getTransaction().begin();
            //On commence par faire un merge dans le delete pour le cas où l'entité qu'on lui
            //fournirai serait une entité détachée. Le remove n'acceptant que les entités managées
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
