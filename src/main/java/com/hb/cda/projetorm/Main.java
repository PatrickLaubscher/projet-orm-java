package com.hb.cda.projetorm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hb.cda.projetorm.entity.Project;
import com.hb.cda.projetorm.entity.ProjectLeader;
import com.hb.cda.projetorm.entity.Subject;
import com.hb.cda.projetorm.repository.ProjectLeaderRepository;
import com.hb.cda.projetorm.repository.ProjectRepository;
import com.hb.cda.projetorm.repository.SubjectRepository;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        Map<String, String> envOverrides = new HashMap<>();
        envOverrides.put("jakarta.persistence.jdbc.user", dotenv.get("DB_USER"));
        envOverrides.put("jakarta.persistence.jdbc.password", dotenv.get("DB_PASS"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU", envOverrides);

        EntityManager em = emf.createEntityManager();
        
        ProjectLeaderRepository projectLeaderRepository = new ProjectLeaderRepository(em);
        ProjectLeader projectLeader = new ProjectLeader( "John", "Doe", "jdoe@gmail.com", "password123", LocalDateTime.now(), 1);
        projectLeaderRepository.persist(projectLeader);
        projectLeaderRepository.findAll(ProjectLeader.class);
        ProjectLeader newProjectLeader = projectLeaderRepository.findById(ProjectLeader.class, 1);
        
        System.out.println("Project Leader: " + newProjectLeader.getName() + " " + newProjectLeader.getFirstname());
        
        LocalDate deliveryDate = LocalDate.of(2025, 6, 25);

        SubjectRepository subjectRepository = new SubjectRepository(em);
        Subject subject = new Subject("Application de gestion");
        subjectRepository.persist(subject);

        ProjectRepository projectRepository = new ProjectRepository(em);
        Project project1 = new Project( "Projet 1", "Description projet 1", deliveryDate, 20000, LocalDateTime.now(), 1, projectLeader.getId());
        projectRepository.persist(project1);
        
        List<Project> projects = projectRepository.findAll(Project.class);
        System.out.println("Projects:");
        for (Project project : projects) {
            System.out.println(" - " + project.getTitle() + " (ID: " + project.getId() + ")" + 
                               " | Subject ID: " + project.getSubject() + 
                               " | Leader ID: " + project.getProjectleaderId() + 
                               " | Delivery Date: " + project.getDeliveryDate());
        }



        em.close();
        emf.close();

    }

}
