<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Create client</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>NAME</th>
        <th>CONTACT</th>
    </tr>
    </thead>
    <tbody>
    <form action="/car-showrooms/client/create" method="post">
        <tr>
            <td><input type="text" name="name" placeholder="name" required></td>
            <td><input type="text" name="contacts" placeholder="contact" required></td>
            <td><input type="submit" value="Create"></td>
        </tr>
    </form>
    </tbody>
</table>
<a href="http://localhost:8080/car-showrooms">Go to start page</a>
</body>
</html>