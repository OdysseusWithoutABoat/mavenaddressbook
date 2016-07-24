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

import com.damianosimoni.mavenaddressbook.controller.ControllerContact;
import com.damianosimoni.mavenaddressbook.model.Contact;
import java.io.IOException;
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
@WebServlet("/contact")
public class ContactHandler extends HttpServlet {
    
    String oldName;
    String oldSurname;
    String oldNumber;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerContact controller = new ControllerContact();
        ServletContext application = getServletContext();
        String destination = "/contactlist";
        String action = request.getParameter("action");
 
        if(action.equals("confirm")){
            
            boolean inserted;
            int age = parseAge(request.getParameter("age"));
            Contact contact = new Contact(request.getParameter("name"), request.getParameter("surname"),
                request.getParameter("number"), request.getParameter("address"), 
                age);   
            inserted = controller.insertContact(contact, oldName, oldSurname, oldNumber);
            if(!inserted){
                request.setAttribute("message", controller.getError());
                destination = "/WEB-INF/contact.jsp";
            }
            oldName = "";
            oldSurname = "";
            oldNumber = "";
        }
        
        RequestDispatcher rd =		
                        application.getRequestDispatcher(destination);
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
        
        Contact contact =(Contact) request.getAttribute("contact");
        if(contact !=null && contact.getName() != null){
            request.setAttribute("name", contact.getName());
            request.setAttribute("surname", contact.getSurname());
            request.setAttribute("number", contact.getNumber());
            request.setAttribute("address", contact.getAddress());
            request.setAttribute("age", contact.getAge());
            this.oldName = contact.getName();
            this.oldSurname = contact.getSurname();
            this.oldNumber = contact.getNumber();
        }
        request.getRequestDispatcher("/WEB-INF/contact.jsp").forward(request, response);
    }
            
    private int parseAge(String age){
        int ageR = 0;
        if(age != null && !age.isEmpty())
            ageR = Integer.valueOf(age);
        return ageR;
    }
}

