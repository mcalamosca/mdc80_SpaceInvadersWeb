<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            int userID;
            String lastName;
            String firstName;
            String email;
                     
            userID = (int) request.getAttribute("userID");
            lastName = (String) request.getAttribute("lastName");
            firstName = (String) request.getAttribute("firstName");
            email = (String) request.getAttribute("email");
            
            JSONObject json = new JSONObject();
            json.put("userID", userID);
            json.put("lastName", lastName);
            json.put("firstName", firstName);
            json.put("email", email);
            
            out.print(json);
            //out.flush();
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
        <script src="assets/js/gameJQuery.js" type="text/javascript"></script>

    </body>
</html>
