<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update review</title>
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
        <form method="post">
            <tr>
                <td><input type="text" name="id" value="${review.id}" readonly size="1"></td>
                <td><input type="text" name="text" value="${review.text}" required></td>
                <td><input type="number" name="rating" value="${review.rating}" required></td>
                <td><input type="number" name="client id" value="${review.client.id}" readonly></td>
                <td><input type="number" name="car id" value="${review.car.id}" readonly></td>
                <td><input type="submit" formaction="/car-showrooms/review/update" value="Update"></td>
            </tr>
        </form>
    </c:forEach>
    </tbody>
</table>
<a href="http://localhost:8080/car-showrooms">Go to start page</a>
</body>
</html>