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
package com.damianosimoni.mavenaddressbook.handler;

import com.damianosimoni.mavenaddressbook.controller.ControllerContactList;
import com.damianosimoni.mavenaddressbook.model.Contact;
import com.damianosimoni.mavenaddressbook.persistence.ContactDBOperations;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Damiano Simoni
 */
@WebServlet("/contactlist")
public class ContactListHandler extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ContactDBOperations operations = new ContactDBOperations();
        List<Contact> contacts = operations.getAllContacts();
        request.setAttribute("contacts", contacts);
        
        request.getRequestDispatcher("/WEB-INF/contactlist.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
        ServletContext application = getServletContext();
        ControllerContactList controller = new ControllerContactList();
        String destination;
        String action = request.getParameter("action");
        int age = parseAge(request.getParameter("age"));     
        
        Contact contact = new Contact(request.getParameter("name"), request.getParameter("surname"),
                request.getParameter("number"), request.getParameter("address"), 
                age);
        if(action.equals("edit")){
            destination ="/contact";
            request.setAttribute("contact", contact);
            RequestDispatcher rd =		
                        application.getRequestDispatcher(destination);
            rd.forward(request, response);
        }
        else if(action.equals("remove")){
            controller.removeContact(contact);
            destination = request.getContextPath() + "/contactlist";
            response.sendRedirect(destination);
        }
        else if(action.equals("add")){
            destination = "/contact";           
            RequestDispatcher rd =		
                        application.getRequestDispatcher(destination);
            rd.forward(request, response);
        }
        else if(action.equals("logout")){
            destination = request.getContextPath() + "/login";
            response.sendRedirect(destination);
        }
     
    }
    
    private int parseAge(String age){
        int ageR = 0;
        if(age != null && !age.isEmpty())
            ageR = Integer.valueOf(age);
        return ageR;
    }
}
