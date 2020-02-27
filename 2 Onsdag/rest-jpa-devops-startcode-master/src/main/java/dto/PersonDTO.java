/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;

/**
 *
 * @author Christian
 */
public class PersonDTO {
    private int id;
    private String fName;
    private String lName;
    private String phone;
   
    public PersonDTO(Person p) {
        this.fName = p.getFirstName();
        this.lName = p.getLastName();
        this.phone = p.getPhone();
        this.id = p.getId();
    }
    public PersonDTO(int id, String fn,String ln, String phone) {
        this.id = id;
        this.fName = fn;
        this.lName = ln;
        this.phone = phone;               
}

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "PersonDTO{" + "fName=" + fName + ", lName=" + lName + ", phone=" + phone + '}';
    }

   
    
    
}