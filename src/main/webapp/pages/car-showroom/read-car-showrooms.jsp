<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Read car showrooms</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>ADDRESS</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="carShowroom" items="${carShowrooms}">
        <tr>
            <td>${carShowroom.id}</td>
            <td>${carShowroom.name}</td>
            <td>${carShowroom.address}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="http://localhost:8080/car-showrooms">Go to start page</a>
</body>
</html>