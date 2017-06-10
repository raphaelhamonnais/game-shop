<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Home</title>
    <meta name="generator" content="Bootply">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="Bootstrap">
    <%--<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
    <link href="bootstrap/css/bootstrap-combined.min.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="bootstrap/css/style.css" rel="stylesheet" type="text/css" media="all" />
</head>

<!-- HTML code from Bootply.com editor -->
<body>

<%@include file="header.jsp" %>


<!-- MAIN CONTAINER-->
<div class="container">
    <!-- CONTENT SIDE-->
    <!-- MAIN PRODUCTS GRID-->
    <div class="row-fluid container-folio">
        <%@include file="genre.jsp" %>

        <div class="container-body">
            <%@include file="game_picture.jsp" %>
            <%@include file="game_picture.jsp" %>
            <%@include file="game_picture.jsp" %>

        </div>
    </div>
</div>

<%@include file="game_modal.jsp" %>

<!-- PAGINATION-->
<div class="pagination pull-right">
    <ul>
        <li><a href="#">Prev</a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">Next</a></li>
    </ul>
</div>
<!-- /PAGINATION-->

<!-- FOOTER-->
<%@include file="footer.jsp" %>


<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

</body>
</html>