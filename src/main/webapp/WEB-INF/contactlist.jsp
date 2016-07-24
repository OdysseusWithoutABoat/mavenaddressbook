<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Address Book</title>      
    <link rel="stylesheet" href="css/normalize.css">
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/styleContactList.css">           
  </head>
  <body>
    <div class="container">
        <header>
            <div class="logo"/>
        </header>
        <div class="header-buttons">
            <form action="contactlist" method="post">
                <button type="submit" name='action' id='add' value="add"/>                          
                <button type="submit" name='action' id='logout' value="logout"/>
            </form>
        </div>
        <table id='table1' class="table table-bordered">
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Age</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${contacts}" var="listContact">
        <tr>
            <td>
                ${listContact.getName()}
            </td>
            <td>
                ${listContact.getSurname()}
            </td>
            <td>
                ${listContact.getNumber()}
            </td>
            <td>
                ${listContact.getAddress()}
            </td>
            <td>
                ${listContact.getAge()}
            </td>
            <td>
                <form action="contactlist" method="post">
                    <button type="submit" name='action' id='edit' value='edit'/>                         
                    <button type="submit" name='action' id='remove' value='remove'/>
                    <input type="hidden" name="name" value="${listContact.getName()}" />
                    <input type="hidden" name="surname" value="${listContact.getSurname()}"/>
                    <input type="hidden" name="number" value="${listContact.getNumber()}"/>
                    <input type="hidden" name="address" value="${listContact.getAddress()}"/>
                    <input type="hidden" name="age" value="${listContact.getAge()}"/>
                </form>
            </td>            
        </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        </tfoot>
        </table>
    </div>
  </body>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src="js/contactlist.js"></script>   
</html>
