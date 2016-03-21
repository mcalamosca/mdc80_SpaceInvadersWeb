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
    </head>
    <body>
        <form id="frmRegistration" action="Register" method="post">
            <label for="txtFirstName">First Name: </label>&nbsp;<input type="text" id="txtFirstName" name="txtFirstName" value="">
            <br />            
            <label for="txtLastName">Last Name: </label>&nbsp;<input type="text" id="txtLastName" name="txtLastName" value="">
            <br />
            <label for="txtEmail">Email: </label>&nbsp;<input type="text" id="txtEmail" name="txtEmail" value="">
            <br />
            <label for="txtPassword">Password: </label>&nbsp;<input type="password" id="txtPassword" name="txtPassword" value="">
            <br />
            <label for="txtPasswordConfirm">Confirm Password: </label>&nbsp;<input type="password" id="txtPasswordConfirm" name="txtPasswordConfirm" value="">
            <br />
            <input type="submit" id="btnSubmit" name="btnSubmit" value="Register">

        </form>
    </body>
</html>
