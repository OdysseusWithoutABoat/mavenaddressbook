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

import com.damianosimoni.mavenaddressbook.controller.ControllerRegistration;
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
@WebServlet("/registration")
public class RegistrationHandler extends HttpServlet {
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
        ControllerRegistration controller = new ControllerRegistration(request);
        ServletContext application = getServletContext();
        String destination = "/WEB-INF/access.jsp";

        if(controller.checkData()){
            controller.insertUser();
            request.setAttribute("message", "Registration completed");
        }
        else
            request.setAttribute("message", controller.getError());
        RequestDispatcher rd =		
                        application.getRequestDispatcher(destination);
        rd.forward(request, response);
                		
    }
}
