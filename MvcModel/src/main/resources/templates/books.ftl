<html>
<head>
    <title>Library Info System</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
</head>

<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        List of books
    </div>
    <table>
        <tr>
            <th>Title</th>
            <th>Author</th>
        </tr>
    <#list booksFromServer as book>
        <tr>
            <td>${book.title}</td>
            <td>${book.author}</td>
        </tr>
    </#list>
    </table>
    <form method="post" action="/books">
        <label for="title">Title
            <input class="input-field" type="text" id="title" name="title">
        </label>
        <label for="author">Author
            <input class="input-field" type="text" id="author" name="author">
        </label>
        <input type="submit" value="Add book">
    </form>

    <form method="get" action="/books">
        <label for="authorForSearch">Author name
            <select id="authorForSearch" name="authorForSearch">
                <option>All</option>
        <#list authorList as author>
                <option>${author.getAuthor()}</option>
        </#list>
            </select>
        </label>
        <input type="submit" value="Find by author name">
    </form>
</div>
</body>