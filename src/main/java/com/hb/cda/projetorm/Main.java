package com.hb.cda.projetorm;

import java.time.LocalDateTime;

import com.hb.cda.projetorm.entity.ProjectLeader;
import com.hb.cda.projetorm.repository.ProjectLeaderRepository;

public class Main {


    public static void main(String[] args) {
        
        ProjectLeaderRepository projectLeaderRepository = new ProjectLeaderRepository();
        ProjectLeader projectLeader = new ProjectLeader( "John", "Doe", "jdoe@gmail.com", "password123", LocalDateTime.now(), 1);
        projectLeaderRepository.persist(projectLeader);
        projectLeaderRepository.findAll(ProjectLeader.class);
        ProjectLeader newProjectLeader = projectLeaderRepository.findById(ProjectLeader.class, 1);
        
        System.out.println("Project Leader: " + newProjectLeader.getName() + " " + newProjectLeader.getFirstname());
 
    }

}
