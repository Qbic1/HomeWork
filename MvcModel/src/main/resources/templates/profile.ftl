<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="form-style-2-heading">
    <label style="color: #985f0d; font-size: 20px">First name</label>
    ${reader.firstName}
</div>
<div class="form-style-2-heading">
    <label style="color: #985f0d; font-size: 20px">Last name</label>
    ${reader.lastName}
</div>
<div class="form-style-2-heading">
    <label style="color: #985f0d; font-size: 20px">Age</label>
    ${reader.age}
</div>

<table>
    <tr>
        <th>Your books</th>
    </tr>
    <#list yourBooks as book>
        <tr>
            <td>${book}</td>
        </tr>
    </#list>
</table>


<form action="/" method="post">

    <label for="book">Book
        <select id="book" name="book">
        <#list freeBooks as book>
            <option>${book}</option>
        </#list>
        </select>
    </label>

    <input type="submit" value="Take book">
</form>
<a href="/logout">Logout</a>
</body>
</html>