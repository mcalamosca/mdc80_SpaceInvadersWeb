<%-- 
    Document   : index
    Created on : Feb 18, 2016, 7:00:50 PM
    Author     : mcala_000
--%>

<%@page import="edu.pitt.is1017.spaceinvaders.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alien Invasion - Login</title>
        <link rel="stylesheet" id="login"  href='assets/css/login.css'/>
        <script src="assets/js/jquery-2.2.2.min.js" type="text/javascript"></script>
    </head>
    <%
    %>
    <body>
        <section class="loginform ai">
            <div>
                <form id="frmLogin" class="frmLogin" action="LoginValidator" method="post">
                    <ul class="loginform">
                        <li class="title">Space Invaders</li><br/><br/>
                        <li><label for="txtUserName">Email: </label>
                            <input type="text" id="txtUserName" name="txtUserName" placeholder="yourname@email.com" required>&nbsp;</li>
                        <li><label for="txtPassword">Password: </label>
                            <input type="password" id="txtPassword" name="txtPassword" placeholder="password" required>&nbsp;</li>
                        <li><input type="submit" id="btnSubmit" name="btnSubmit" value="Login">&nbsp;</li>
                        <li><a href="registration.jsp"><input type="button" id="btnRegister" name="btnRegister" value="Register"></a></li>
                    </ul>

                </form>
            </div>
        </section>
    </body>
</html>
