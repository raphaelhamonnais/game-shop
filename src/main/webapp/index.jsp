<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<header>
    <title>Home</title>
   <%--for mobile app, make sure that the site is self-adaption to the mobile-phone--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,
user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <%--import the file bootstrap.min.css--%>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</header>
<body>
<!--create an navigation-->
<nav class="navbar navbar-default navbar-fixed-top">
    <!--define a container-->
    <div class="container">
        <div class="navbar-header">
            <!--insert the logo -->
            <a href="#" class="navbar-brand">LOGO</a>
            <!--create a button for the narrow window-->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
                <!--add three lines for the button-->
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <!--create the items and the corresponding icons in the navigation-->
        <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home Page <span class="glyphicon glyphicon-home"></span></a></li>
                <li><a href="#">Catalogue <span class="glyphicon glyphicon-triangle-bottom"></span></a></li>
                <li><a href="#">Game <span class="glyphicon glyphicon-triangle-bottom"></span></a></li>
                <li><a href="#">Console <span class="glyphicon glyphicon-triangle-bottom"></span></a></li>
            </ul>
        </div>
    </div>
</nav>
<a href="./game.html">Game</a>
<a href="./login.html">Login</a>
</body>
</html>