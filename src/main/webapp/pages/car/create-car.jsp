<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Create car</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>MODEL</th>
        <th>BRAND</th>
        <th>YEAR</th>
        <th>PRICE</th>
        <th>CATEGORY ID</th>
        <th>CAR SHOWROOM ID</th>
    </tr>
    </thead>
    <tbody>
    <form action="/car-showrooms/car/create" method="post">
        <tr>
            <td><input type="text" name="model" placeholder="model" required></td>
            <td><input type="text" name="brand" placeholder="brand" required></td>
            <td><input type="text" name="year" placeholder="year" required></td>
            <td><input type="number" name="price" placeholder="price" required></td>
            <td><input type="number" name="category id" placeholder="category id" required></td>
            <td><input type="number" name="car showroom id" placeholder="car showroom id" required></td>
            <td><input type="submit" value="Create"></td>
        </tr>
    </form>
    </tbody>
</table>
<a href="http://localhost:8080/car-showrooms">Go to start page</a>
</body>
</html>