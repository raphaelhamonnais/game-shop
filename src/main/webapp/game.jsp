<%@ page import="model.entity.Game" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>Game</title>
        <meta name="generator" content="Bootply">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="description" content="Bootstrap">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/bootstrap-combined.min.css" rel="stylesheet">
        <link href="bootstrap/css/bootstrap-theme.css" rel="stylesheet">
        <link href="bootstrap/css/style.css" rel="stylesheet" type="text/css" media="all" />
    </head>
<body>
<%@include file="header.jsp" %>

<div class="container">

    <%@include file="genre.jsp" %>
    <h1>This is the game page.</h1>
    <%
        List<Game> gameList = (List<Game>) request.getAttribute("gamesList");
    %>
    <table class="table table-bordered">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Category</th>
            <th>Publisher</th>
            <th>Release Year</th>
            <th>sale Rate</th>
            <th>IsOnSale</th>
            <th>IsBest</th>
            <th>IsHot</th>
            <th>IsNew</th>
        </tr>
        <% for (Game game : gameList) { %>
        <tr>
            <td><%=game.getGameId()%></td>
            <td><a href="#" data-toggle="modal" data-target="#myModal2"><%=game.getGameName()%></a></td>
            <%--<td><%=for (Category cat game.getCategories()%></td>--%>
            <td><%=game.getPublisher().getPublisherName()%></td>
            <td><%=game.getGameReleaseYear()%></td>
            <td><%=game.getGameSaleRate()%></td>
            <td><%=game.isGameIsOnSale()%></td>
            <td><%=game.isGameIsBest()%></td>
            <td><%=game.isGameIsHot()%></td>
            <td><%=game.isGameIsNew()%></td>
        </tr>
        <% } %>
    </table>
</div>

<%@include file="game_modal.jsp" %>
<%@include file="footer.jsp" %>
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
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

</html>
