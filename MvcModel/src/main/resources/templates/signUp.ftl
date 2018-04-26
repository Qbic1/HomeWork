<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/css/styles.css" rel="stylesheet">
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Sign Up!
    </div>
    <form method="post" action="/signUp">
        <label for="login">Login
            <input class="input-field" type="text" id="login" name="login">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <label for="first-name">First name
            <input class="input-field"  id="first-name" name="firstName">
        </label>
        <label for="last-name">Last name
            <input class="input-field"  id="last-name" name="lastName">
        </label>
        <label for="age">Age
            <input class="input-field"  id="age" name="age">
        </label>
        <input type="submit" value="Sign Up">
    </form>
</div>
</body>
</html>