<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/css/styles.css" rel="stylesheet">
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<#if error??>
<div class="alert alert-danger" role="alert">Wrong login or password</div>
</#if>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Login!
    </div>
    <form method="post" action="/signIn">
        <label for="login">Login
            <input class="input-field" type="text" id="login" name="login">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <label for="remember-me">
            <input type="checkbox" id="remember-me" name="remember-me">Remember</label>
        <input type="submit" value="Login">
    </form>
    <a href="/signUp"><input type="submit" value="Sign Up"></a>
</div>
</body>
</html>