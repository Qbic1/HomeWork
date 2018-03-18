<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product list</title>
    <style>
        <%@include file="../css/style.css" %>
    </style>
</head>
<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        List of goods
    </div>
    <table frame="border" class="t01">
        <tr>
            <th>Name</th>
            <th>Cost</th>
        </tr>
        <c:forEach items="${productsFromServer}" var="product">
            <tr>
                <td>${product.name}</td>
                <td>${product.cost}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
