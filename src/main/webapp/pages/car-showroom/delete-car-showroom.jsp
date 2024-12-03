<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete car showroom</title>
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
        <form method="post">
            <tr>
                <td><input type="text" name="id" value="${carShowroom.id}" readonly size="1"></td>
                <td><input type="text" name="name" value="${carShowroom.name}" readonly></td>
                <td><input type="text" name="address" value="${carShowroom.address}" readonly></td>
                <td><input type="submit" formaction="/car-showroom/delete" value="Delete"></td>
            </tr>
        </form>
    </c:forEach>
    </tbody>
</table>
<a href="http://localhost:8080/car-showroom">Go to start page</a>
</body>
</html>