package com.hb.cda.projetorm.repository;

import java.util.List;

import com.hb.cda.projetorm.entity.Project; 
import com.hb.cda.projetorm.entity.ProjectLeader;
import com.hb.cda.projetorm.repository.util.Database;
import com.hb.cda.projetorm.repository.util.GenericRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;




public class ProjectRepository extends GenericRepository<Project, Integer> {

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
    

}