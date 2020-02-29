/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entitys.Customer;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Christian
 */
public class CustomerFacade implements IntFCustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CustomerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Customer addCustomer(String Name, String Email) {
        EntityManager em = getEntityManager();
        try {
            Date date = new Date();
            Customer newCus = new Customer(Name, Email);
            em.getTransaction().begin();
            em.persist(newCus);
            em.getTransaction().commit();

            // add main test
            return newCus;

        } finally {
            em.close();
        }
    }

    @Override
    public Customer getCustomer(int id) {
        EntityManager em = getEntityManager();
        try {
            Customer entity = em.find(Customer.class, id);
            //if (entity != null) {
                Customer cus = new Customer(entity.getName(), entity.getEmail());
                return cus;
//            } else {
//                throw new CustomerNotFoundException("Person not found");
//            }
        } finally {
            em.close();
        }
    }

    @Override
    public List<Customer> getAllPersons() {
         EntityManager em = getEntityManager();

        try {
            TypedQuery<Customer> persons = em.createQuery("SELECT c FROM Customer c", Customer.class);
            List<Customer> list = persons.getResultList();
            return list;

        } finally {
            em.close();
        }
    }

}
