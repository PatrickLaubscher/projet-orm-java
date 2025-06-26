package com.hb.cda.projetorm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hb.cda.projetorm.entity.Developer;
import com.hb.cda.projetorm.entity.Project;
import com.hb.cda.projetorm.entity.ProjectApplication;
import com.hb.cda.projetorm.entity.ProjectApplicationId;
import com.hb.cda.projetorm.entity.ProjectLeader;
import com.hb.cda.projetorm.entity.Subject;
import com.hb.cda.projetorm.repository.DeveloperRepositoryImpl;
import com.hb.cda.projetorm.repository.ProjectApplicationRepositoryImpl;
import com.hb.cda.projetorm.repository.ProjectLeaderRepositoryImpl;
import com.hb.cda.projetorm.repository.ProjectRepositoryImpl;
import com.hb.cda.projetorm.repository.SubjectRepositoryImpl;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        /* Initialisation des variables d'environnement */
        Dotenv dotenv = Dotenv.load();
        Map<String, String> envOverrides = new HashMap<>();
        envOverrides.put("jakarta.persistence.jdbc.user", dotenv.get("DB_USER"));
        envOverrides.put("jakarta.persistence.jdbc.password", dotenv.get("DB_PASS"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU", envOverrides);



        EntityManager em = emf.createEntityManager();
        
        ProjectLeaderRepositoryImpl projectLeaderRepository = new ProjectLeaderRepositoryImpl(em);
        ProjectLeader projectLeader = new ProjectLeader( "John", "Doe", "jdoe@gmail.com", "password123", LocalDateTime.now(), 1);
        projectLeaderRepository.persist(projectLeader);
        projectLeaderRepository.findAll(ProjectLeader.class);
        ProjectLeader newProjectLeader = projectLeaderRepository.findById(ProjectLeader.class, 1);
        
        System.out.println("Project Leader: " + newProjectLeader.getName() + " " + newProjectLeader.getFirstname());
        
        LocalDate deliveryDate = LocalDate.of(2025, 6, 25);

        SubjectRepositoryImpl subjectRepository = new SubjectRepositoryImpl(em);
        Subject subject = new Subject("Application de gestion");
        subjectRepository.persist(subject);

        ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl(em);
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

        DeveloperRepositoryImpl developerRepository = new DeveloperRepositoryImpl(em);
        Developer developer1 = new Developer("Marc", "Dupont", "md@gmail.com", "devpass", LocalDateTime.now(), 2, "Développeur Java expérimenté");
        developerRepository.persist(developer1);
        developerRepository.findAll(Developer.class);
        Developer newDeveloper = developerRepository.findById(Developer.class, 2);
        System.out.println("Developer: " + newDeveloper.getName() + " " + newDeveloper.getFirstname() + 
                           " | Description: " + newDeveloper.getDescription());
        
        ProjectApplicationRepositoryImpl projectApplicationRepository = new ProjectApplicationRepositoryImpl(em);
        ProjectApplication projectApplication = new ProjectApplication(developer1, project1, LocalDateTime.now());
        projectApplication.setId(new ProjectApplicationId(developer1.getId(), project1.getId()));
        projectApplicationRepository.persist(projectApplication);
        ProjectApplicationId id = new ProjectApplicationId(developer1.getId(), project1.getId());
        ProjectApplication newProjectApplication = projectApplicationRepository.findById(ProjectApplication.class, id);
        System.out.println("Project Application: Developer ID: " + newProjectApplication.getDeveloper().getId() + 
                           " | Project ID: " + newProjectApplication.getProject().getId() + 
                           " | Is Validated: " + newProjectApplication.getIsValidated() + 
                           " | Created At: " + newProjectApplication.getCreatedAt());   

        em.close();
        emf.close();

    }

}
