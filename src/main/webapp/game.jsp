<%@ page import="model.entity.Game" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.Category" %>
<%@ page import="java.util.Iterator" %>
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

<div class="container-fluid">
    <%@include file="navbar.jsp" %>
</div>


<div class="container">

    <%@include file="genre.jsp" %>
    <%
        List<Game> gameList = (List<Game>) request.getAttribute("gamesList");
    %>
    <table class="table table-bordered">
        <tr>
            <th>Name</th>
            <th>Categories</th>
            <th>Publisher</th>
            <th>Release Year</th>
        </tr>
        <% for (Game game : gameList) { %>
        <tr>
            <td>
                <a href="#" id="<%=game.getGameId()%>" data-toggle="modal" data-target="#myModal2" onclick="getGameInfos(<%=game.getGameId()%>)"><%=game.getGameName()%></a>
            </td>
            <td>
                <%
                    String categoriesString = "";
                    Iterator<Category> it = game.getCategories().iterator();
                    while (it.hasNext()) {
                        Category cat = it.next();
                        categoriesString += cat.getCatName();
                        if (it.hasNext())
                            categoriesString += ", ";
                %>
                <%=categoriesString%>
                <%}%>
            </td>
            <td><%=game.getPublisher().getPublisherName()%></td>
            <td><%=game.getGameReleaseYear()%></td>
        </tr>
        <% } %>
    </table>
</div>

<%@include file="game_modal.jsp" %>
<%@include file="footer.jsp" %>
<%@include file="sign-in.jsp"%>
</body>
<script>
    function getGameInfos(gameId) {
        var xhttp = null;
        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                document.getElementById(gameId).innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "http://localhost:8080/sr03-game-shop/rest/games/" + gameId + "/physical-games", true);
        xhttp.send();
//        xhttp.responseType = 'json';
    }
</script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

</html>
