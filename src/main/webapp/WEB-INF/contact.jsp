<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact page</title>
        <link rel="stylesheet" href="css/mainStyle.css">
    </head>
    <body>
        <div class="form">
          <form action="contact" method="get">
               <input name="name" type="text" placeholder="name" value="<c:out value="${name}"/>"/>
               <input name="surname" type="text" placeholder="surname" value="<c:out value="${surname}"/>"/>
               <input name="number" type="text" placeholder="number" value="<c:out value="${number}"/>"/>
               <input name="address" type="text" placeholder="address" value="<c:out value="${address}"/>"/>
               <input name="age" type="text" placeholder="age" value="<c:out value="${age}"/>"/>
               <button type="submit" name="action" id="confirm" value="confirm">Confirm</button>
               <button type="submit" name="action" id="cancel" value="cancel">Cancel</button>
               <div id="messageReg"><%=(request.getAttribute("message")!=null)? request.getAttribute("message"):"" %></div>
          </form>
        </div>
    </body>
</html>
