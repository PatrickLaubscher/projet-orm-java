package com.hb.cda.projetorm.repository.interfaces;

import java.util.List;

import com.hb.cda.projetorm.entity.ProjectApplication;
import com.hb.cda.projetorm.entity.ProjectLeader;

public interface ProjectApplicationRepository extends GenericRepository<ProjectApplication, Integer> {

    public List<ProjectApplication> findAllProjectApplicationByProject(ProjectLeader leader);
    
}
