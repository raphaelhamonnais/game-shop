<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Home</title>
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
</body>
</html>