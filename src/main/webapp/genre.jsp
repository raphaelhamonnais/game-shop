<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/6/9
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row-fluid">
    <div class="span3">
        <div class="well text-center">
            <a href="games?isOnSale=True">On Sale</a>
        </div>
    </div>
    <div class="span3 hidden-phone">
        <div class="well text-center">
            <a href="games?isBest=True">Best</a>
        </div>
    </div>
    <div class="span3 hidden-phone">
        <div class="well text-center">
            <a href="games?isHot=True">Hot</a>
        </div>
    </div>
    <div class="span3 hidden-phone">
        <div class="well text-center">
            <a href="games?isNew=True">New</a>
        </div>
    </div>
</div>
