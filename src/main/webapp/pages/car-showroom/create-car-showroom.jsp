<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Create Car Showroom</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>NAME</th>
        <th>ADDRESS</th>
    </tr>
    </thead>
    <tbody>
    <form action="/car-showrooms/car-showroom/create" method="post">
        <tr>
            <td><input type="text" name="name" placeholder="name" required></td>
            <td><input type="text" name="address" placeholder="address" required></td>
            <td><input type="submit" value="Create"></td>
        </tr>
    </form>
    </tbody>
</table>
<a href="http://localhost:8080/car-showrooms">Go to start page</a>
</body>
</html>