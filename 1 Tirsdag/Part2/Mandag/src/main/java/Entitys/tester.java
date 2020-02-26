/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Christian
 */
public class tester {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    
    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
    EntityManager em = emf.createEntityManager();
////    
    em.getTransaction().begin();
    //Assingment 1 - 3
    Customer c1 = new Customer("Bob", "bobsen");
    Customer c2 = new Customer("Lars", "Petersen");
//    c1.addAdress()
//    //Assignmet 4
////    c1.addPhone("55 55 55 55", "Bobs Phone");
////    c2.addPhone("66 66 66 66", "Lars Pone or the devils");
////    c1.addHobby("lazerTag");
////    c1.addHobby("Beer");
////    c1.addHobby("Cloth");
////    c2.addHobby("Life");
//    
////    
    em.persist(c1);
    em.persist(c2);
////    
    em.getTransaction().commit();
    }
}
