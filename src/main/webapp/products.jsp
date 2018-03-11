<%@ page import="ru.homework.products.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.homework.dao.ProductDao" %>
<%@ page import="ru.homework.dao.ProductDaoImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product list</title>
</head>
<body>

<%
    ProductDao productDao = new ProductDaoImpl();
    List<Product> products = productDao.findAll();
%>

<h1>List of Goods</h1>
<table frame="border">
    <tr>
        <td>
            <b>Name</b>
        </td>
        <td>
            <b>Cost</b>
        </td>
    </tr>
    <%
        if (products!=null)
        for (Product product: products) {
    %>
    <tr>
        <td>
            <%=product.getName()%>
        </td>
        <td>
            <%=product.getCost()%>
        </td>
    </tr>
    <%
    }
    %>
</table>

</body>
</html>
