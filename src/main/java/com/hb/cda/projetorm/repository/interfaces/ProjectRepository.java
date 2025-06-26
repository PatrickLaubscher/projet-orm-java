package com.hb.cda.projetorm.repository.interfaces;

import java.util.List;

import com.hb.cda.projetorm.entity.Project;
import com.hb.cda.projetorm.entity.ProjectLeader;

public interface ProjectRepository extends GenericRepository<Project, Integer> {

    public List<Project> findAllProjectByProjectLeader(ProjectLeader leader);
    public List<Project> findAllProjectByDate(String date);
    public List<Project> findAllProjectsBySubject(String subject);
    public List<Project> findAllProjectsByBudget(Integer budget);
    
}
