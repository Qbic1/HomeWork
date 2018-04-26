<html>
<head>
    <title>Library Info System</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
</head>

<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        List of cards
    </div>
    <table>
        <tr>
            <th>Reader</th>
            <th>Book</th>
        </tr>
    <#list cardsFromServer as card>
        <tr>
            <td>${card.reader}</td>
            <td>${card.book}</td>
        </tr>
    </#list>
    </table>

    <form action="/cards" method="get">

        <label for="readerForSearch">Reader
            <select id="readerForSearch" name="readerForSearch">
                <option>All</option>
        <#list readers as reader>
                <option>${reader}</option>
        </#list>
            </select>
        </label>

        <input type="submit" value="Find cards by reader name">
    </form>

    <form action="/cards" method="post">

        <label for="reader">Reader
            <select id="reader" name="reader">
        <#list readers as reader>
                <option>${reader}</option>
        </#list>
            </select>
        </label>

        <label for="book">Book
            <select id="book" name="book">
        <#list freeBooks as book>
                <option>${book}</option>
        </#list>
            </select>
        </label>

        <input type="submit" value="Add card">
    </form>
</div>
</body>