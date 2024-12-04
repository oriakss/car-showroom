<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Create category</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>NAME</th>
    </tr>
    </thead>
    <tbody>
    <form action="/car-showrooms/category/create" method="post">
        <tr>
            <td><input type="text" name="name" placeholder="category name" required></td>
            <td><input type="submit" value="Create"></td>
        </tr>
    </form>
    </tbody>
</table>
<a href="http://localhost:8080/car-showrooms">Go to start page</a>
</body>
</html>