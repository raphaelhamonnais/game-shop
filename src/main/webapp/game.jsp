<%@ page import="model.entity.Game" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>--%>
<!doctype html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>Game</title>
        <meta name="generator" content="Bootply">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="description" content="Bootstrap">
        <link href="bootstrap/css/bootstrap-combined.min.css" rel="stylesheet">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://www.bootply.com/bootply/themes/metroid/theme.css" type="text/css" rel="stylesheet">
        <!-- CSS code from Bootply.com editor -->
        <link href="bootstrap/css/style.css" rel="stylesheet" type="text/css" media="all" />
        <!--[if lt IE 9]>
        <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <!--  <link rel="apple-touch-icon" href="/bootstrap/img/apple-touch-icon.png">
         <link rel="apple-touch-icon" sizes="72x72" href="/bootstrap/img/apple-touch-icon-72x72.png">
         <link rel="apple-touch-icon" sizes="114x114" href="/bootstrap/img/apple-touch-icon-114x114.png"> -->
    </head>
    <!-- HTML code from Bootply.com editor -->
    <style>
       tr {
            border: black 3px solid;
        }
        td {
            border:black 3px solid;
        }
    </style>
<body>
<%@include file="header.jsp" %>

<div class="container">

    <h1>This is the game page.</h1>
    <%
        List<Game> gameList = (List<Game>) request.getAttribute("gamesList");
    %>
    <%--<% for (Game game : gameList) { %>--%>
    <table class="table table-bordered">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Category</th>
            <th>Publisher</th>
            <th>Release Year</th>
            <th>sale Rate</th>
        </tr>
        <% for (Game game : gameList) { %>
        <tr>
            <td><%=game.getGameId()%></td>
            <td><%=game.getGameName()%></td>
            <td><%=game.getCategories()%></td>
            <td><%=game.getPublisher()%></td>
            <td><%=game.getGameReleaseYear()%></td>
            <td><%=game.getGameSaleRate()%></td>
        </tr>
        <% } %>
    </table>
    <%--<% } %>--%>
</div>

</body>
<script>
    function getXhr()
    {
        var xhr = null;
        if(window.XMLHttpRequest)
            xhr = new XMLHttpRequest();
        else if(window.ActiveXObject){
            try {
                xhr = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                xhr = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }
        else {
            alert("Votre navigateur ne supporte pas les objets XMLHTTPRequest...");
            xhr = false;
        }
        return xhr
    }
    function traitementAjax() {
        var xhr = null;
        xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resultat = xhr.responseText;
            }
            xhr.open("GET", "http://localhost:8080/sr03-game-shop/rest/physical-games", true);
            xhr.send();
            xhr.responseType = 'json';
        }
    }
</script>


    <script async="" src="//www.google-analytics.com/analytics.js"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>


    <script type="text/javascript" src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>


    <!-- JavaScript jQuery code from Bootply.com editor  -->

    <script type="text/javascript">

        $(document).ready(function() {
        });

    </script>

    <script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
                (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
        ga('create', 'UA-40413119-1', 'bootply.com');
        ga('send', 'pageview');
    </script>
</html>
