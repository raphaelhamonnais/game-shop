<%@ page import="webservices.httpclient.CategoryClient" %>
<%@ page import="model.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.json.parser.JsonParser" %>
<%@ page import="com.mashape.unirest.http.exceptions.UnirestException" %>
<%@ page import="model.entity.Console" %>
<%@ page import="webservices.httpclient.ConsoleClient" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse pagination-centered">
    <div class="container-fluid">
        <div class="nav-collapse in collapse" style="height: auto;">
            <ul class="nav">
                <li class="active"><a href="index">HOME</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Categories <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <%
                            try {
                                List<Category> catList = new JsonParser().parseJsonListOfObjects(
                                        new CategoryClient().getAll().getBody().toString(),
                                        Category[].class
                                );
                                for (Category cat : catList) { %>
                        <li><a href="games?category=<%=cat.getCatName()%>"><%=cat.getCatName()%></a></li>
                        <%}
                        } catch (UnirestException e) {
                            e.printStackTrace();
                        }
                        %>
                    </ul>
                </li>
            </ul>
            <ul class="nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Consoles <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <%
                            try {
                                List<Console> consolesList = new JsonParser().parseJsonListOfObjects(
                                        new ConsoleClient().getAll().getBody().toString(),
                                        Console[].class
                                );
                                for (Console console : consolesList) { %>
                        <li><a href="games?console=<%=console.getConsoleName()%>"><%=console.getConsoleName()%></a></li>
                        <%}
                        } catch (UnirestException e) {
                            e.printStackTrace();
                        }
                        %>
                    </ul>
                </li>
            </ul>
            <ul class="nav">
                <li class="nav navbar">
                    <form action="games" class="navbar-form navbar-left">
                        <div class="input-group">
                            <input type="Search" id="gameName" name="gameName" placeholder="Search..." class="form-control" />
                        </div>
                    </form>
                </li>
            </ul>
            <%--<ul class="nav navbar-nav navbar-right">--%>
                <%--<li>--%>
                    <%--<a class="btn btn-mini" href="#" data-toggle="modal" data-target="#myModal1">Login</a>--%>
                <%--</li>--%>
            <%--</ul>--%>
            <ul class="nav navbar-nav navbar-right">
                <li class="nav">
                    <div class="">
                        <a class="btn" href="#" data-toggle="modal" data-target="#myModal1">Login</a>
                        <%--<a class="btn" href="login.html">Login</a>--%>
                    </div>
                </li>
            </ul>

        </div>
    </div>
</nav>