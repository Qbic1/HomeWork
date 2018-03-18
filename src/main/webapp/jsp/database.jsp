<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Work with database</title>
    <style>
        <%@include file="../css/style.css" %>
    </style>
</head>
<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Work with database
    </div>
    <form action="" method="post">
        <p>Enter name: <input type="text" name="name"
                              value="<%=request.getParameter("name")==null ? "" : request.getParameter("name")%>"></p>
        <p>Enter cost: <input type="text" name="cost"
                              value="<%=request.getParameter("cost")==null ? "" : request.getParameter("cost")%>"></p>
        <input type="submit" value="Find" name="type"/>
        <input type="submit" value="Save" name="type"/>
        <input type="submit" value="Update" name="type"/>
        <input type="submit" value="Delete" name="type"/>
    </form>
</div>

</body>
</html>
