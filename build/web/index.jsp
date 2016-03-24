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
    <%
            String userName = "";
            String password = "";
            User user;
            
            if (request.getParameter("btnSubmit") != null) {
                if (request.getParameter("txtUserName") != null) {
                    if (request.getParameter("txtUserName") != "") {
                        userName = request.getParameter("txtUserName");
                    }
                }

                if (request.getParameter("txtPassword") != null) {
                    if (request.getParameter("txtPassword") != "") {
                        password = request.getParameter("txtPassword");
                    }
                }

                if (!userName.equals("") && !password.equals("")) {
                    user = new User(userName, password);
                    if(user.isLoggedIn()) {
                        out.println("<script>alert('Successful Login')</script>");
                        response.sendRedirect("game.jsp");
                    } else {
                        out.println("<script>alert('Username and/or password is incorrect')</script>");
                    }

                } else {
                    out.println("<script>alert('You must enter both User Name and Password')</script>");
                }
            }
        
    %>
    <body>
        <section class="loginform ai">
            <form id="frmLogin" action="index.jsp" method="post">
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
