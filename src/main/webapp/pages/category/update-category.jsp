<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update category</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>NAME</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="category" items="${categories}">
        <form method="post">
            <tr>
                <td><input type="text" name="id" value="${category.id}" readonly size="1"></td>
                <td><input type="text" name="name" value="${category.name}" required></td>
                <td><input type="submit" formaction="/car-showrooms/category/update" value="Update"></td>
            </tr>
        </form>
    </c:forEach>
    </tbody>
</table>
<a href="http://localhost:8080/car-showrooms">Go to start page</a>
</body>
</html>