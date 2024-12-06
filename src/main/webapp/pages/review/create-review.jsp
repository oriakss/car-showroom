<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Create review</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>TEXT</th>
        <th>RATING</th>
    </tr>
    </thead>
    <tbody>
    <form action="/car-showrooms/review/create" method="post">
        <tr>
            <td><input type="text" name="text" placeholder="text" required></td>
            <td><input type="number" name="rating" placeholder="rating" required max="10" min="0"></td>
            <td><input type="number" name="client id" placeholder="client id" required></td>
            <td><input type="number" name="car id" placeholder="car id" required></td>
            <td><input type="submit" value="Create"></td>
        </tr>
    </form>
    </tbody>
</table>
<a href="http://localhost:8080/car-showrooms">Go to start page</a>
</body>
</html>