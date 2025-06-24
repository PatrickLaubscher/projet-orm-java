/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.hb.cda.projetorm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

/**
 *
 * @author patri
 */
public class Main {


    public static void main(String[] args) {
        EntityManager em = Persistence
        .createEntityManagerFactory("main")
        .createEntityManager();

    }


}
