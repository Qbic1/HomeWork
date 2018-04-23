<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn</title>
    <style>
        <%@include file="../css/style.css" %>
    </style>
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Sign In!
    </div>
    <form method="post" action="signIn">
        <label for="name">User name
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <input type="submit" value="Sign In">
    </form>
    <a href="/signUp">Create account</a>
</div>
</body>
</html>
