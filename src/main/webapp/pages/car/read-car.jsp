<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Read cars</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>MODEL</th>
        <th>BRAND</th>
        <th>YEAR</th>
        <th>PRICE</th>
        <th>CATEGORY ID</th>
        <th>CAR SHOWROOM ID</th>
        <th>CAR OWNER</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>${car.id}</td>
            <td>${car.model}</td>
            <td>${car.brand}</td>
            <td>${car.year}</td>
            <td>${car.price}</td>
            <td>${car.category.id}</td>
            <td>${car.carShowroom.id}</td>
            <td>${car.carOwner}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="http://localhost:8080/car-showrooms">Go to start page</a>
</body>
</html>