<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/6/9
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row-fluid">
    <a href="games?isOnSale=True">
        <div>
            <div class="col-xs-3">
                <img src="bootstrap/images/sales.png" alt="Games on sale" title="Games on sale">
                <%--<div class="well text-center h5">Games on sale</div>--%>
            </div>
        </div>
    </a>
    <a href="games?isHot=True">
        <div>
            <div class="col-xs-3">
                <img src="bootstrap/images/fire.png" alt="Hot games" title="Hot games">
                <%--<div class="well text-center h5">Hot games</div>--%>
            </div>
        </div>
    </a>
    <a href="games?isNew=True">
        <div>
            <div class="col-xs-3">
                <img src="bootstrap/images/new.png" alt="New games" title="New games">
                <%--<div class="well text-center h5">New games</div>--%>
            </div>
        </div>
    </a>
    <a href="games?isBest=True">
        <div>
            <div class="col-xs-3">
                <img src="bootstrap/images/podium.png" alt="Best games" title="Best games">
                <%--<div class="well text-center h5">Best games</div>--%>
            </div>
        </div>
    </a>
</div>
<div class="row-fluid"><p></p></div>
<div class="row-fluid"><p></p></div>