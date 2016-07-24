<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>AddressBook</title> 
    <link rel="stylesheet" href="css/mainStyle.css">     
  </head>
  <header>
        <div class="logo"/>
  </header>
  <body>
    <div class="login-page">
        <div class="form">
            <form class="register-form" action="registration" method="post">
               <input name="username" type="text" placeholder="username"/>
               <input name="password" type="password" placeholder="password"/>
               <input name="passwordRepetead" type="password" placeholder="repeat password"/>
               <button type="submit">Create</button>            
               <p class="message">Already registered? <a href="#">Sign In</a></p>
            </form>
            <form class="login-form" action="login" method="post">
               <input name="username" type="text" placeholder="username"/>
               <input name="password" type="password" placeholder="password"/>
               <button type="submit">login</button>
               <p class="message">Not registered? <a href="#">Create an account</a></p>
            </form>
            <div id="messageReg"><%=(request.getAttribute("message")!=null)? request.getAttribute("message"):"" %></div>
        </div>
    </div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src="js/access.js"></script>  
  </body>
</html>
