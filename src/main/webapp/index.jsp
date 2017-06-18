<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Home</title>
    <meta name="generator" content="Bootply">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="Bootstrap">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-combined.min.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="bootstrap/css/style.css" rel="stylesheet" type="text/css" media="all" />
</head>

<!-- HTML code from Bootply.com editor -->
<body>

<div class="container-fluid">
<%@include file="navbar.jsp" %>
</div>

<!-- MAIN CONTAINER-->
<div class="container">
    <!-- CONTENT SIDE-->
    <!-- MAIN PRODUCTS GRID-->
    <div class="row-fluid container-folio">
        <div class="container-body">
            <a href="games?isOnSale=True">
                <div>
                    <div class="col-xs-5">
                        <img src="bootstrap/images/sales.png" alt="Games on sale">
                        <div class="well text-center h5">Games on sale</div>
                    </div>
                </div>
            </a>
            <a href="games?isHot=True">
                <div>
                    <div class="col-xs-5">
                        <img src="bootstrap/images/fire.png" alt="Hot games">
                        <div class="well text-center h5">Hot games</div>
                    </div>
                </div>
            </a>
        </div>
        <div class="container-body">
            <a href="games?isNew=True">
                <div>
                    <div class="col-xs-5">
                        <img src="bootstrap/images/new.png" alt="New games">
                        <div class="well text-center h5">New games</div>
                    </div>
                </div>
            </a>
            <a href="games?isBest=True">
                <div>
                    <div class="col-xs-5">
                        <img src="bootstrap/images/podium.png" alt="Best games">
                        <div class="well text-center h5">Best games</div>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>


<!-- FOOTER-->
<%@include file="footer.jsp" %>




<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

</body>
</html>