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

    </head>
    <script>
        var loggedIn;
        if (${loggedIn == 'false'}) {
            alert('Username and/or password is incorrect');
        }
    </script>
    <%

    %>
    <body>
        <section class="loginform ai">
            <form id="frmLogin" action="LoginValidator" method="post">
                <ul>
                    <li><label for="txtUserName">Email: </label>
                        <input type="text" id="txtUserName" name="txtUserName" placeholder="yourname@email.com" required>&nbsp;</li>
                    <li><label for="txtPassword">Password: </label>
                        <input type="password" id="txtPassword" name="txtPassword" placeholder="password" required>&nbsp;</li>
                    <li><input type="submit" id="btnSubmit" name="btnSubmit" value="Login">&nbsp;</li>
                    <li><a href="registration.jsp"><input type="button" id="btnRegister" name="btnRegister" value="Register"></a></li>
                </ul>

            </form>
        </section>
    </body>
</html>
