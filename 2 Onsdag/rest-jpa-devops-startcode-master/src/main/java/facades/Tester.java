/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.PersonDTO;
import entities.Person;
import exceptions.PersonNotFoundException;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Christian
 */
public class Tester  {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    //teste methods
    public static void main(String[] args) throws PersonNotFoundException{
        // Persistence.generateSchema("pu", null);
        EntityManager em = emf.createEntityManager();
        PersonFacade personFacade = facades.PersonFacade.getFacadeExample(emf);
        Date date = new Date();
        Person p1 = new Person("Bob", "theBuilder", "555", date, date);
        Person p2 = new Person("James", "Bond", "007", date, date);
        Person p3 = new Person("He", "Man", "1970", date, date);
        //dto

////    
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.getTransaction().commit();
        em.getTransaction().begin();
        //addPerson (Works)
        personFacade.addPerson("Don Diego", "De la Vega", "52");
        //deletePerson (work)
        personFacade.deletePerson(p2.getId());
////    
        // getPersonById (Works)
        System.out.println("Finde Zorro = " + personFacade.getPerson(p1.getId()));

// getAllPersons (works)
        System.err.println("All persons = " + personFacade.getAllPersons());
        PersonDTO p4 = new PersonDTO(p3.getId(), "Prince", "Aiden", "12345");
// edidtPerson (works)
        personFacade.editPerson(p4);
        em.getTransaction().commit();
    }
}
