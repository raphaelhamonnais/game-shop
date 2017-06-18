<%@ page import="model.entity.*" %>
<%@ page import="java.util.*" %>
</html>
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
    <h1>Shopping Cart</h1>
    <p></p>
    <p></p>
    <p></p>
</div>

<div class="container">
    <%
        String userName = (String) request.getAttribute("name");
        ShoppingBag shoppingBag = (ShoppingBag) request.getAttribute("shoppingBag");
        Collection<ShoppingBagRow> shoppingBagRows = new HashSet<>();
    %>

    <div class="container">

        <div class="collapse-custom">
            <%if (shoppingBag == null) { %>
            <h3>Aucun jeu dans le panier...</h3>
            <%} else {
                shoppingBagRows = shoppingBag.getShoppingBagRows();
            }%>

            <% for (ShoppingBagRow row : shoppingBagRows) { %>
            <nav class="navbar navbar-default" role="navigation">
                <%--GAME BASIC INFORMATIONS--%>
                <div class="collapse navbar-collapse"
                     data-toggle="collapse"
                     id="game-header-collapse-<%=row.getPhysicalGame().getGame().getGameId()%>"
                     href="#game-details-collapse-<%=row.getPhysicalGame().getGame().getGameId()%>"
                     onclick="getGameInfos(<%=row.getPhysicalGame().getGame().getGameId()%>)">
                    <ul class="nav navbar-nav">
                        <li><a><%=row.getPhysicalGame().getGame().getGameName()%></a></li>
                    </ul>
                </div>

                <%--HIDDEN CONTENT : GAME DETAILLED INFORMATIONS--%>
                <div id="game-details-collapse-<%=row.getPhysicalGame().getGame().getGameId()%>" class="collapse" data-parent="game-header-collapse-<%=row.getPhysicalGame().getGame().getGameId()%>">
                    <div class="panel-body">
                        <!-- LISTE-->
                        <div class="row-fluid">
                            <div class="col-xs-12">
                                <ul class="list-group">
                                    <li class="list-group-item"> Categories:
                                        <%
                                            StringBuilder categoriesString = new StringBuilder();
                                            Iterator<Category> it = row.getPhysicalGame().getGame().getCategories().iterator();
                                            while (it.hasNext()) {
                                                Category cat = it.next();
                                                categoriesString.append(cat.getCatName());
                                                if (it.hasNext())
                                                    categoriesString.append(", ");
                                            }
                                        %>
                                        <%=categoriesString.toString()%>
                                    </li>
                                    <li class="list-group-item">Publisher: <%=row.getPhysicalGame().getGame().getPublisher().getPublisherName()%></li>
                                    <li class="list-group-item">Release year: <%=row.getPhysicalGame().getGame().getGameReleaseYear()%></li>
                                </ul>
                            </div>
                            <div class="col-xs-12">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <td>Console</td>
                                        <td>Available quantity</td>
                                        <td>Price</td>
                                        <td class="hidden"></td>
                                    </tr>
                                    </thead>
                                    <tbody id="game-detailled-table-<%=row.getPhysicalGame().getGame().getGameId()%>">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
            <%}%>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
</body>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<script>
    function getGameInfos(gameId) {
        var xhttp = null;
        var gameTable = "";
        document.getElementById("game-detailled-table-" + gameId).innerHTML = gameTable;
        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                var jsonResponse = JSON.parse(this.responseText);
                jsonResponse.forEach(function(physicalGame) {
                    gameTable += "<tr>";
                    gameTable += "<td>" + physicalGame.console.consoleName + "</td>";
                    gameTable += "<td>" + physicalGame.gameStock + "</td>";
                    gameTable += "<td>" + physicalGame.gamePrice + " € </td>";
                    gameTable += "<td><button class='btn btn-danger'><span class='glyphicon glyphicon-trash' aria-hidden='true'></span></button></td>";
                    gameTable += "</tr>";
                });
                document.getElementById("game-detailled-table-" + gameId).innerHTML = gameTable;
            }
        };
        xhttp.open("GET", "http://localhost:8080/sr03-game-shop/rest/games/" + gameId + "/physical-games", true);
        xhttp.send();
    }
</script>

</html>
