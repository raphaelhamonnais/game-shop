<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<header>
    <title>Home</title>
   <%--for mobile app, make sure that the site is self-adaption to the mobile-phone--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <%--import the file bootstrap.min.css--%>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
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
</header>
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
            <form style="height: 30px" method="get" action="game.jsp">
                <div class="search">
                    <input type="search" value="Search" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Search';}" required="">
                </div>
                <div class="sear-sub">
                    <input type="submit" value=" ">
                </div>
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
                                                <li><a href="game.jsp">Category</a></li>
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
                    <div class="resp-tabs-container">
                        <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
                            <div class="col-md-3 product">
                                <div class="pro-item simpleCart_shelfItem">
                                    <div class="thumb-item">
                                        <img src="bootstrap/images/coming-soon.jpg" alt="" class="pro-image-front">
                                        <img src="bootstrap/images/coming-soon.jpg" alt="" class="pro-image-back">
                                        <div class="cart-pro">
                                            <div class="inner-cart-pro">
                                                <a href="#" class="link-product-add-cart" data-toggle="modal" data-target="#myModal5">Quick View</a>
                                            </div>
                                        </div>
                                        <span class="product-new-top">On Sale</span>
                                    </div>
                                    <div class="item-info-product ">
                                        <h4><a href="game.jsp">Game</a></h4>
                                        <div class="info-product-price">
                                            <span class="item_price">$5.99</span>
                                            <del>$9.71</del>
                                        </div>
                                        <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-1">
                            <div class="col-md-3 product">
                                <div class="pro-item simpleCart_shelfItem">
                                    <div class="thumb-item">
                                        <img src="bootstrap/images/coming-soon.jpg" alt="" class="pro-image-front">
                                        <img src="bootstrap/images/coming-soon.jpg" alt="" class="pro-image-back">
                                        <div class="cart-pro">
                                            <div class="inner-cart-pro">
                                                <a href="#" class="link-product-add-cart" data-toggle="modal" data-target="#myModal5">Quick View</a>
                                            </div>
                                        </div>
                                        <span class="product-new-top">New</span>
                                    </div>
                                    <div class="item-info-product ">
                                        <h4><a href="game.jsp">Game2</a></h4>
                                        <div class="info-product-price">
                                            <span class="item_price">$45.99</span>
                                            <del>$69.71</del>
                                        </div>
                                        <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-2">
                            <div class="col-md-3 product">
                                <div class="pro-item simpleCart_shelfItem">
                                    <div class="thumb-item">
                                        <img src="bootstrap/images/coming-soon.jpg" alt="" class="pro-image-front">
                                        <img src="bootstrap/images/coming-soon.jpg" alt="" class="pro-image-back">
                                        <div class="cart-pro">
                                            <div class="inner-cart-pro">
                                                <a href="#" class="link-product-add-cart" data-toggle="modal" data-target="#myModal5">Quick View</a>
                                            </div>
                                        </div>
                                        <span class="product-new-top">New</span>

                                    </div>
                                    <div class="item-info-product ">
                                        <h4><a href="game.jsp">Game3</a></h4>
                                        <div class="info-product-price">
                                            <span class="item_price">$45.99</span>
                                            <del>$69.71</del>
                                        </div>
                                        <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-3">
                            <div class="col-md-3 product">
                                <div class="pro-item simpleCart_shelfItem">
                                    <div class="thumb-item">
                                        <img src="bootstrap/images/coming-soon.jpg" alt="" class="pro-image-front">
                                        <img src="bootstrap/images/coming-soon.jpg" alt="" class="pro-image-back">
                                        <div class="cart-pro">
                                            <div class="inner-cart-pro">
                                                <a href="#" class="link-product-add-cart" data-toggle="modal" data-target="#myModal5">Quick View</a>
                                            </div>
                                        </div>
                                        <span class="product-new-top">New</span>

                                    </div>
                                    <div class="item-info-product ">
                                        <h4><a href="game.jsp">Game4</a></h4>
                                        <div class="info-product-price">
                                            <span class="item_price">$45.99</span>
                                            <del>$69.71</del>
                                        </div>
                                        <a href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //product-nav -->

    <!-- footer -->
    <div class="footer">
        <div class="container">
            <div class="col-md-3 footer-left">
                <h2><a href="index.jsp"><img src="bootstrap/images/logo3.jpg" alt=" " /></a></h2>
                <p>The foot of the home page.</p>
            </div>
            <div class="col-md-9 footer-right">
                <div class="sign-grds">
                    <div class="col-md-4 sign-gd">
                        <h4>Information</h4>
                        <ul>
                            <li><a href="index.jsp">Home</a></li>
                            <li><a href="game.jsp">Categories</a></li>
                            <li><a href="game.jsp">Games</a></li>
                            <li><a href="contact.jsp">Contact</a></li>
                        </ul>
                    </div>

                    <div class="col-md-4 sign-gd-two">
                        <h4>Store Information</h4>
                        <ul>
                            <li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>Address : 14 bis Rue <span>Compiegne</span></li>
                            <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i>Email : <a href="han.lu@etu.utc.fr">han.lu@etu.utc.fr</a></li>
                            <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>Phone : 0669176977</li>
                        </ul>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>

<%--Quick View--%>
<div class="modal fade" id="myModal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="single">
                    <div class="container">
                        <div class="thumb-image"> <img src="bootstrap/images/coming-soon.jpg" data-imagezoom="true" class="img-responsive"> </div>
                        <div class="col-md-6 single-right-left" data-wow-delay=".5s" style="visibility: visible;">
                            <h3 style="margin: 5px">Game1</h3>
                            <p><span class="item_price">Price: $55</span> <del>- $90</del></p>
                            <div class="rating1">
								<span class="starRating">
								<input id="rating5" type="radio" name="rating" value="5">
									<label for="rating5">5</label>
								<input id="rating4" type="radio" name="rating" value="4">
									<label for="rating4">4</label>
								<input id="rating3" type="radio" name="rating" value="3" checked="">
									<label for="rating3">3</label>
								<input id="rating2" type="radio" name="rating" value="2">
									<label for="rating2">2</label>
								<input id="rating1" type="radio" name="rating" value="1">
									<label for="rating1">1</label>
								</span>
                            </div>
                            <div class="occasion-cart">
                                <a href="#" class="item_add hvr-outline-out button2">Add to cart</a>
                            </div>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


    <!-- login -->
    <div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content modal-info">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body modal-spa">
                    <div class="login-grids">
                        <div class="login">
                            <div class="login-bottom">
                                <h3>Sign up for free</h3>
                                <form>
                                    <div class="sign-up">
                                        <h4>Login :</h4>
                                        <input type="text" value="Type here" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Type here';}" required="">
                                    </div>
                                    <div class="sign-up">
                                        <h4>Password :</h4>
                                        <input type="password" value="Password" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Password';}" required="">

                                    </div>
                                    <div class="sign-up">
                                        <h4>Re-type Password :</h4>
                                        <input type="password" value="Password" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Password';}" required="">

                                    </div>
                                    <div class="sign-up">
                                        <input type="submit" value="REGISTER NOW" >
                                    </div>

                                </form>
                            </div>
                            <div class="login-right">
                                <h3>Sign in with your account</h3>
                                <form>
                                    <div class="sign-in">
                                        <h4>Login :</h4>
                                        <input type="text" value="Type here" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Type here';}" required="">
                                    </div>
                                    <div class="sign-in">
                                        <h4>Password :</h4>
                                        <input type="password" value="Password" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Password';}" required="">
                                        <a href="#">Forgot password?</a>
                                    </div>
                                    <div class="single-bottom">
                                        <input type="checkbox"  id="brand" value="">
                                        <label for="brand"><span></span>Remember Me.</label>
                                    </div>
                                    <div class="sign-in">
                                        <input type="submit" value="SIGNIN" >
                                    </div>
                                </form>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <p>By logging in you agree to our <a href="#">Terms and Conditions</a> and <a href="#">Privacy Policy</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>