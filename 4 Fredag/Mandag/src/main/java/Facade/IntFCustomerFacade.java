/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entitys.Customer;
import java.util.List;

/**
 *
 * @author Christian
 */
public interface IntFCustomerFacade {
     public Customer addCustomer(String Name, String Email);

    public Customer getCustomer(int id); //throws CustomerNotFoundException;

    public List<Customer> getAllPersons(); //throws CustomerNotFoundException;


}
