<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Read clients</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>CONTACTS</th>
        <th>REGISTRATION DATE</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="client" items="${clients}">
        <tr>
            <td>${client.id}</td>
            <td>${client.name}</td>
            <td>${client.contacts}</td>
            <td>${client.registrationDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="http://localhost:8080/car-showrooms">Go to start page</a>
</body>
</html>