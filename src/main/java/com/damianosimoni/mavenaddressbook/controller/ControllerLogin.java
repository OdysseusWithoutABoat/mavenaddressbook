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

import com.damianosimoni.mavenaddressbook.model.User;
import com.damianosimoni.mavenaddressbook.persistence.UserDBOperations;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Damiano Simoni
 */
public class ControllerLogin {
    
    private String username;
    private String password;
    private HttpServletRequest request;
    private UserDBOperations operationsDB;
    private String error;

    public ControllerLogin() {
    }

    public ControllerLogin(HttpServletRequest request) {
        this.username = (String) request.getParameter("username");
	this.password = (String) request.getParameter("password");
        this.request = request;
        this.operationsDB = new UserDBOperations();
    }

    public boolean checkData(){
        User user = new User(username, password);
        boolean result = true;
        if(username.length()<5){
            this.error = "Incorrect username";
            result = false;
        }
        else if(password.length()<8){
            this.error = "Incorrect password";
            result = false;
        }  
        else if(!operationsDB.containsUser(user)){
            this.error = "This user doesn't exist";
            result = false;
        }
        return  result;
    }    
    
    public String getError() {
        return error;
    }
    
    
}
