<html>
<head>
    <title>Library Info System</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>

<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        List of readers
    </div>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
        </tr>
    <#list readersFromServer as reader>
        <tr>
            <td>${reader.firstName}</td>
            <td>${reader.lastName}</td>
            <td>${reader.age}</td>
        </tr>
    </#list>
    </table>
    <form method="post" action="/readers">
        <label for="firstName">First Name
            <input class="input-field" type="text" id="firstName" name="firstName">
        </label>
        <label for="lastName">Last Name
            <input class="input-field" type="text" id="lastName" name="lastName">
        </label>
        <label for="age">Age
            <input class="input-field" type="number" id="age" name="age">
        </label>
        <input type="submit" value="Add reader" name="type">
    </form>

    <form method="get" action="/readers">
        <label for="ageFrom">Age from
            <input class="input-field" type="text" id="ageFrom" name="ageFrom">
        </label>
        <label for="ageTo">Age to
            <input class="input-field" type="text" id="ageTo" name="ageTo">
        </label>
        <input type="submit" value="Find by age">
    </form>
</div>
</body>