/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Person;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Christian
 */
public class Tester {
     private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    
    public static void main(String[] args) {
       // Persistence.generateSchema("pu", null);
    EntityManager em = emf.createEntityManager();
////    
    Date date = new Date();
    em.getTransaction().begin();
    Person p1 = new Person("Bob", "theBuilder", "555", date, date);
    Person p2 = new Person("James", "Bond", "007", date, date);
    
    em.persist(p1);
    em.persist(p2);
////    
    em.getTransaction().commit();
    }
}
