<%--
  Created by
  User: raphael
  Date: 08/06/2017
  Time: 17:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                            <li><a href="games?category=Action">Action</a></li>
                            <li><a href="games?category=Aventure">Aventure</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="game.jsp" class="dropdown-toggle" data-toggle="dropdown">Consoles <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="games?console=PC">PC</a></li>
                            <li><a href="games?console=X360">XBox 360</a></li>
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
<%@include file="sign-in.jsp" %>