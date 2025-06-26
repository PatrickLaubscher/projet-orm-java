package com.hb.cda.projetorm.repository;

import java.util.List;

import com.hb.cda.projetorm.entity.Role;
import com.hb.cda.projetorm.repository.interfaces.RoleRepository;
import com.hb.cda.projetorm.repository.util.AbstractRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;


public class RoleRepositoryImpl extends AbstractRepository<Role, Integer> implements RoleRepository {


    public RoleRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Role> findAll(Class<Role> type) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(type);
            criteriaQuery.select(criteriaQuery.from(type));
            TypedQuery<Role> query = em.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }


    @Override
    public Role findById(Class<Role> type, Integer id) {
        EntityManager em = getEntityManager();
        try {
            Role foundEntity = em.find(type, id);
            return foundEntity;
        } catch (PersistenceException e) {
            System.err.println(e);
        }
        return null;
    }

    
    @Override
    public boolean persist(Role type) {
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
    public boolean update(Role type) {
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
    public boolean delete(Role type) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Role merged = em.merge(type);
            em.remove(merged);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println(e);

        }
        return false;
    }
   
}
