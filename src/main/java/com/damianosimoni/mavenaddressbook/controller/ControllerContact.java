/*
 * Copyright (C) 2016 Damiano Simoni
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.damianosimoni.mavenaddressbook.controller;

import com.damianosimoni.mavenaddressbook.model.Contact;
import com.damianosimoni.mavenaddressbook.persistence.ContactDBOperations;

/**
 *
 * @author Damiano Simoni
 */
public class ControllerContact {

    ContactDBOperations operations;
    String error;
    
    public ControllerContact() {
        this.operations = new ContactDBOperations();
    }
    
    public boolean insertContact(Contact contact, String nameOld, String surnameOld,
            String numberOld){
        boolean result = false;
        if(doCheck(contact)){
            if(nameOld == null || nameOld.isEmpty()){
                    addContact(contact);
            }
            else
                editContact(contact, nameOld, surnameOld, numberOld);
            result = true;
        }
            
        return result;
    }
    
    public void editContact(Contact contact, String nameOld, String surnameOld,
            String numberOld){               
        operations.updateContact(contact, nameOld, surnameOld, numberOld);
    }
    
    public void addContact(Contact contact){                
        operations.addContact(contact);
    }
    
    private boolean doCheck(Contact contact){
        boolean result = true;
        
        if(contact.getName().isEmpty()){
             result = false;
             error = "Name is required";
        }        
        else if(contact.getSurname().isEmpty()){
            result = false;
            error = "Surname is required";
        }
        else if(contact.getNumber().isEmpty()){
            result = false;
            error = "Number is required";
        }
        
        return result;
    }

    public String getError() {
        return error;
    }
    
}
