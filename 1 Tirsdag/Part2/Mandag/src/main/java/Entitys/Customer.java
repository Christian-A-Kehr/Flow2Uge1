/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
//database : Flow2Uge1Dag1

/**
 *
 * @author Christian
 */
@Entity
public class Customer implements Serializable {

    // Assingment 2
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    private String firstName, lastName;

    // Assignmet 3
    @ElementCollection // Specifies a collection of instances of a basic type or embeddable class. Must be specified if the collection is to be mapped by means of a collection table.
    @CollectionTable(
            name = "Hobbies", // name of table 
            joinColumns = @JoinColumn(name = "customer_id"))
    private List<String> hobby = new ArrayList();

    @ElementCollection(fetch = FetchType.LAZY) // LAZY = only getting the necessery objcets 
    @MapKeyColumn(name = "PHONE") // = Assigning name for the map 
    @Column(name = "Description") // = Assingning Value name
    private Map<String, String> phones = new HashMap();

    public Long getId() {
        return customer_id;
    }

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setId(Long id) {
        this.customer_id = id;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Assignmet 2 methods 
    public void addHobby(String s) {
        hobby.add(s);
    }

    // Assignmet 2 methods
    public String getHobbies() {
        return String.join(",", hobby);
    }

    // Assignmet 4 Maps of Basic Types
    public void addPhone(String phoneNo, String description) {
        //Assuming a customer only has one number, nothing more needs to be done.  
        phones.put(phoneNo, description);
    }

    // Assignmet 4 Maps of Basic Types
    public String getPhoneDescription(String phoneNo) {
        // not sure what get method to use here ask around
        // return phones.getOrDefault(phoneNo, getPhoneDescription(phoneNo));
        return phones.get(getPhoneDescription(phoneNo));
    }

    @Override
    public String toString() {
        return "Customer{" + "customer_id=" + customer_id + ", firstName=" + firstName + ", lastName=" + lastName + ", hobbies=" + hobby + '}';
    }

}
