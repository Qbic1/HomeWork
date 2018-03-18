<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
    <style>
        <%@include file="../css/style.css" %>
    </style>
</head>
<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Hello ${cookie.name.value}!
        Choose what you want to do
    </div>
    <form action="products">
        <input type="submit" value="See a list of goods"/>
    </form>

    <form action="database">
        <input type="submit" value="Change database files"/>
    </form>
</div>

</body>
</html>
