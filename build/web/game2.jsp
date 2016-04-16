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
        <script src="assets/js/jquery-2.2.2.min.js"></script>
        <script src="assets/js/helpers.js"></script>
        <script src="assets/js/starfield.js"></script>
        <link href="assets/css/game.css" rel="stylesheet" type="text/css"/>


    </head>
    <body>
        <div id="scoreboard">
            <table id= "tblScore" class="score" cellpadding="3" border="2">
                <thead>
                <th>First Name</th>             
                <th>Last Name</th> 
                <th>Score</th>
                </thead>
                <tbody>                    
                </tbody>
            </table>
        </div>
        
        <div id="starfield" class="starfield"></div>
        <input type="hidden" id="firstName" name="firstName" value="<%=firstName%>"/>
        <input type="hidden" id="lastName" name="firstName" value="<%=lastName%>"/>
        <input type="hidden" id="userID" name="firstName" value="<%=userID%>"/>
        <input type="hidden" id="email" name="firstName" value="<%=email%>"/>

        <script src="assets/js/scoreboard.js" type="text/javascript"></script>
        <script src="assets/js/gameJQuery.js" type="text/javascript"></script> 
        <script src="assets/js/runStarfield.js" type="text/javascript"></script>
    </body>
</html>
