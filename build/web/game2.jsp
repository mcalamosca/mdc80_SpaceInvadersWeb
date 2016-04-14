<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            int userID = Integer.parseInt(session.getAttribute("userID").toString());
            String lastName = (String) session.getAttribute("lastName");
            String firstName = (String) session.getAttribute("firstName");
            String email = (String) session.getAttribute("email");

            
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alien Invasion</title>
        <style>
            body{
                background-color: #000;
            }
            canvas {
                background-color: #000;
                display: block;
                position: absolute;
                margin: auto;
                top: 0;
                bottom: 0;
                left: 0;
                right: 0;
            }
            div.score {
                color: #fff;
            }
        </style>
        <script src="assets/js/jquery-2.2.2.min.js"></script>
        <script src="assets/js/helpers.js"></script>

    </head>
    <body>
            <input type="hidden" id="firstName" name="firstName" value="<%=firstName%>"/>
            <input type="hidden" id="lastName" name="firstName" value="<%=lastName%>"/>
            <input type="hidden" id="userID" name="firstName" value="<%=userID%>"/>
            <input type="hidden" id="email" name="firstName" value="<%=email%>"/>
            
        <script src="assets/js/gameJQuery.js" type="text/javascript"></script>        
    </body>
</html>
