<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/5/25
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game</title>
    <%--for mobile app, make sure that the site is self-adaption to the mobile-phone--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <%--import the file bootstrap.min.css--%>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="bootstrap/css/jquery-ui.css">
    <link href="bootstrap/css/style1.css" rel="stylesheet" type="text/css" media="all" />
    <!-- js -->
    <script type="text/javascript" src="bootstrap/js/jquery.min.js"></script>
    <!-- //js -->
    <!-- cart -->
    <script src="bootstrap/js/simpleCart.min.js"></script>
    <!-- cart -->
    <!-- for bootstrap working -->
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <!-- //for bootstrap working -->
    <link href='http://fonts.useso.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.useso.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,900,900italic,700italic' rel='stylesheet' type='text/css'>
    <script src="bootstrap/js/jquery.easing.min.js"></script>
</head>
<body>
<!-- header of the site: include the logo of the site, the search bar and the login logo -->
<!-- header-bot -->
<div class="header-bot">
    <div class="container">
        <%-- the logo of the site--%>
        <div class="col-md-3 header-left">
            <h1><a href="index.jsp"><img src="bootstrap/images/logo3.jpg"></a></h1>
        </div>
        <%--the search bar--%>
        <div class="col-md-6 header-middle">
            <form style="height: 30px">
                <div class="search">
                    <input type="search" value="Search" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Search';}" required="">
                </div>
                <div class="section_room">
                    <select id="country" onChange="change_country(this.value)" class="frm-field required">
                        <option value="category">Categories</option>
                        <option value="game">Games</option>
                    </select>
                </div>
                <div class="sear-sub">
                    <input type="submit" value=" ">
                </div>
                <div class="clearfix"></div>
            </form>
        </div>
        <%--the login logo--%>
        <div class="col-md-3 header-right footer-bottom">
            <a href="#" class="login" data-toggle="modal" data-target="#myModal4"><span>Login</span></a>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<%--the navagation --%>
<div class="ban-top">
    <div class="container">
        <div class="top_nav_left">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse menu--shylock" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav menu__list">
                            <li class="active menu__item menu__item--current"><a class="menu__link" href="index.jsp">Home <span class="sr-only">(current)</span></a></li>
                            <li class="dropdown menu__item">
                                <a href="#" class="dropdown-toggle menu__link" id="categories" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Categories <span class="caret"></span></a>
                                <ul class="dropdown-menu multi-column columns-3">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <ul class="multi-column-dropdown">
                                                <li><a href="category.jsp">Category1</a></li>
                                            </ul>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </ul>
                            </li>
                            <li class="dropdown menu__item">
                                <a href="#" class="dropdown-toggle menu__link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Games<span class="caret"></span></a>
                                <ul class="dropdown-menu multi-column columns-3">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <ul class="multi-column-dropdown">
                                                <li><a href="game.jsp">Game1</a></li>
                                            </ul>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </ul>
                            </li>
                            <li class=" menu__item"><a class="menu__link" href="contact.jsp">contact</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        <div class="top_nav_right">
            <div class="cart box_1">
                <a href="checkout.jsp">
                    <h3> <div class="total">
                        <i class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></i>
                        <span class="simpleCart_total"></span> (<span id="simpleCart_quantity" class="simpleCart_quantity"></span> items)</div>
                    </h3>
                </a>
                <p><a href="#" class="simpleCart_empty">Empty Cart</a></p>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<!-- //content-bottom -->
<!-- product-nav -->
<div class="product-easy">
    <div class="container">
        <script src="bootstrap/js/easyResponsiveTabs.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#horizontalTab').easyResponsiveTabs({
                    type: 'default', //Types: default, vertical, accordion
                    width: 'auto', //auto or any width like 600px
                    fit: true   // 100% fit in a container
                });
            });
        </script>
        <div class="sap_tabs">
            <div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
                <ul class="resp-tabs-list">
                    <li class="resp-tab-item" aria-controls="tab_item-0" role="tab"><span>On sale</span></li>
                    <li class="resp-tab-item" aria-controls="tab_item-1" role="tab"><span>Best</span></li>
                    <li class="resp-tab-item" aria-controls="tab_item-2" role="tab"><span>Hot</span></li>
                    <li class="resp-tab-item" aria-controls="tab_item-3" role="tab"><span>New</span></li>
                </ul>
            </div>
        </div>
    </div>
</div>

</body>
</html>
