<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Read reviews</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>TEXT</th>
        <th>RATING</th>
        <th>CLIENT ID</th>
        <th>CAR ID</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="review" items="${reviews}">
        <tr>
            <td>${review.id}</td>
            <td>${review.text}</td>
            <td>${review.rating}</td>
            <td>${review.client.id}</td>
            <td>${review.car.id}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="http://localhost:8080/car-showrooms">Go to start page</a>
</body>
</html>