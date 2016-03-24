<%-- 
    Document   : registration
    Created on : Mar 3, 2016, 6:45:32 PM
    Author     : mcala_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alien Invasion - Registration</title>
        <link rel="stylesheet" id="register"  href='assets/css/register.css'/>

    </head>
    <body>
        <section class="registerform ai">
            <div>
                <form id="frmRegistration" class="frmRegistration" action="Register" method="post">
                    <ul>
                        <li><label for="txtFirstName">First Name: </label>&nbsp;<input type="text" id="txtFirstName" name="txtFirstName" value=""></li>
                        <li><label for="txtLastName">Last Name: </label>&nbsp;<input type="text" id="txtLastName" name="txtLastName" value=""></li>
                        <li><label for="txtEmail">Email: </label>&nbsp;<input type="text" id="txtEmail" name="txtEmail" value=""></li>
                        <li><label for="txtPassword">Password: </label>&nbsp;<input type="password" id="txtPassword" name="txtPassword" value=""></li>
                        <li><label for="txtPasswordConfirm">Confirm Password: </label>&nbsp;<input type="password" id="txtPasswordConfirm" name="txtPasswordConfirm" value=""></li>
                        <br/>
                        <li><input type="submit" id="btnSubmit" name="btnSubmit" value="Register">&nbsp;<a href="index.jsp"><input type="button" id="btnReturn" name="btnReturn" value="Back to Login"></a></li>
                    </ul>
                </form>
            </div>
        </section>
    </body>
</html>
