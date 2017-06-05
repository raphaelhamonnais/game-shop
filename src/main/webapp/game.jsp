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
<div class="navbar navbar-inverse">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <div class="nav-collapse in collapse" style="height: auto;">
                <ul class="nav">
                    <li class="active"><a href="index.jsp">HOME</a></li>
                    <li class="dropdown">
                        <a href="game.jsp" class="dropdown-toggle" data-toggle="dropdown">Categories <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="game.jsp">Action</a></li>
                            <li><a href="gsme.jsp">Aventure</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="game.jsp" class="dropdown-toggle" data-toggle="dropdown">Consoles <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="game.jsp">PC</a></li>
                            <li><a href="gsme.jsp">Box</a></li>
                        </ul>
                    </li>
                    <li><a href="contact.jsp">Contact us</a></li>
                </ul>
                <div class="pull-right">
                    <a class="btn btn-mini" style="margin:10px 5px 5px 10px;"
                       href="#" data-toggle="modal" data-target="#myModal1">Login</a>
                </div>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>

<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content modal-info">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>	</div>
            <div class="modal-body modal-spa">
                <div class="login-grids">
                    <div class="login">
                        <div class="login-bottom">
                            <h3>Sign up for free</h3>
                            <form>
                                <div class="sign-up">
                                    <h4>Identifiant :</h4>
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
                                    <h4>Identifiant :</h4>
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

<div class="container">
    <h1>This is the game page.</h1>
        <%
            String gameList = (String) request.getAttribute("gamesList");
            String categoriesList = (String) request.getAttribute("categoriesList");
        %>
    <form action="POST">
        Game: <input name="gamesList" type="text" />
        Category: <input name="categoriesList" type="text" />
        <input type="submit" value="Send" />
    </form>
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
