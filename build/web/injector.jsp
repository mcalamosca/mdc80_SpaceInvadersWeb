<%-- 
    Document   : injector
    Created on : Mar 3, 2016, 6:54:18 PM
    Author     : mcala_000
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="edu.pitt.is1017.spaceinvaders.DbUtilities"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SQL Injections</title>
    </head>
    <%
        if(request.getParameter("txtUserName") != null){
            String userName = request.getParameter("txtUserName");
            String sql = "SELECT * FROM users WHERE email = '" + userName + "';";
            out.println(sql);
            DbUtilities db = new DbUtilities();
            ResultSet rs = db.getResultSet(sql);
            while(rs.next())out.println(rs.getString("lastName"));
        }
    %>
    <body>
        <form name="frmTest" action="injector.jsp" method="post">
            <label for="txtUserName">User Name: </label>&nbsp;<input type="text" id="txtUserName" name="txtUserName" value="">
            <input type="submit" id="btnSubmit" name="btnSubmit" value="Login">
        </form>
    </body>
</html>
