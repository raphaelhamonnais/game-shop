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
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Categories <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="games?category=Action">Action</a></li>
                            <li><a href="games?category=Adventure">Adventure</a></li>
                            <li><a href="games?category=Fighting">Fighting</a></li>
                            <li><a href="games?category=Misc">Misc</a></li>
                            <li><a href="games?category=Platform">Platform</a></li>
                            <li><a href="games?category=Puzzle">Puzzle</a></li>
                            <li><a href="games?category=Racing">Racing</a></li>
                            <li><a href="games?category=Role-Playing">Role-Playing</a></li>
                            <li><a href="games?category=Shooter">Shooter</a></li>
                            <li><a href="games?category=Simulation">Simulation</a></li>
                            <li><a href="games?category=Sports">Sports</a></li>
                            <li><a href="games?category=Strategy">Strategy</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Consoles <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="games?console=3DS">3DS</a></li>
                            <li><a href="games?console=PS">PS</a></li>
                            <li><a href="games?console=GBA">GBA</a></li>
                            <li><a href="games?console=XOne">XOne</a></li>
                            <li><a href="games?console=SAT">SAT</a></li>
                            <li><a href="games?console=N64">N64</a></li>
                            <li><a href="games?console=PS2">PS2</a></li>
                            <li><a href="games?console=DS">DS</a></li>
                            <li><a href="games?console=PS4">PS4</a></li>
                            <li><a href="games?console=PS3">PS3</a></li>
                            <li><a href="games?console=2600">2600</a></li>
                            <li><a href="games?console=WiiU">WiiU</a></li>
                            <li><a href="games?console=GB">GB</a></li>
                            <li><a href="games?console=GC">GC</a></li>
                            <li><a href="games?console=WS">WS</a></li>
                            <li><a href="games?console=GG">GG</a></li>
                            <li><a href="games?console=PCFX">PCFX</a></li>
                            <li><a href="games?console=XB">XB</a></li>
                            <li><a href="games?console=PSP">PSP</a></li>
                            <li><a href="games?console=SNES">SNES</a></li>
                            <li><a href="games?console=GEN">GEN</a></li>
                            <li><a href="games?console=PSV">PSV</a></li>
                            <li><a href="games?console=PC">PC</a></li>
                            <li><a href="games?console=SCD">SCD</a></li>
                            <li><a href="games?console=Wii">Wii</a></li>
                            <li><a href="games?console=NG">NG</a></li>
                            <li><a href="games?console=NES">NES</a></li>
                            <li><a href="games?console=X360">X360</a></li>
                            <li><a href="games?console=3DO">3DO</a></li>
                            <li><a href="games?console=DC">DC</a></li>
                            <li><a href="games?console=TG16">TG16</a></li>
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