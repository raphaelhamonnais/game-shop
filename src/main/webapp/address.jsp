<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Address</title>
</head>
<body>
<div class="container">
    <h1>This is the address page.</h1>
        <%
            String addresses = (String) request.getAttribute("addressesList");
            System.out.println(addresses);
        %>
</div>

</body>
</html>
