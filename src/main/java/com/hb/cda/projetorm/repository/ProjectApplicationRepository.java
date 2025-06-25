package com.hb.cda.projetorm.repository;

import java.util.List;

import com.hb.cda.projetorm.entity.Project;
import com.hb.cda.projetorm.entity.ProjectApplication;
import com.hb.cda.projetorm.entity.ProjectLeader;
import com.hb.cda.projetorm.repository.util.Database;
import com.hb.cda.projetorm.repository.util.GenericRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public class ProjectApplicationRepository extends GenericRepository<ProjectApplication, Integer> {


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
