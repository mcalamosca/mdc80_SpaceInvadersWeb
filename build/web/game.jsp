<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alien Invasion</title>
        <style>
            #alienImage{
                position: absolute;
                top: 20px;
                left: 20px;
            }
        </style>
        <script src="assets/js/jquery-2.2.2.min.js"></script>
        <script>
            $(document).ready(function(){
                $alien = $('#alienImage');
                //console.log(#alienImage);
            });
            
        </script>
    </head>
    <body>
        <img src='assets/images/space.png' id="alienImage"/>
    </body>
</html>
