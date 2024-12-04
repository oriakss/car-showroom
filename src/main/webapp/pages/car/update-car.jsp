<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update car</title>
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
    </tr>
    </thead>
    <tbody>
    <c:forEach var="car" items="${cars}">
        <form method="post">
            <tr>
                <td><input type="text" name="id" value="${car.id}" readonly size="1"></td>
                <td><input type="text" name="model" value="${car.model}" required></td>
                <td><input type="text" name="brand" value="${car.brand}" required></td>
                <td><input type="text" name="year" value="${car.year}" required></td>
                <td><input type=number name="price" value="${car.price}" required></td>
                <td><input type="number" name="category id" value="${car.category.id}" required></td>
                <td><input type="number" name="car showroom id" value="${car.carShowroom.id}" required></td>
                <td><input type="submit" formaction="/car-showrooms/car/update" value="Update"></td>
            </tr>
        </form>
    </c:forEach>
    </tbody>
</table>
<a href="http://localhost:8080/car-showrooms">Go to start page</a>
</body>
</html>