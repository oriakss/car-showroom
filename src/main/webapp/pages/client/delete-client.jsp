<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete client</title>
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
        <form method="post">
            <tr>
                <td><input type="text" name="id" value="${client.id}" readonly size="1"></td>
                <td><input type="text" name="name" value="${client.name}" readonly></td>
                <td><input type="text" name="contacts" value="${client.contacts}" readonly></td>
                <td><input type="text" name="registrationDate" value="${client.registrationDate}" readonly></td>
                <td><input type="submit" formaction="/car-showrooms/client/delete" value="Delete"></td>
            </tr>
        </form>
    </c:forEach>
    </tbody>
</table>
<a href="http://localhost:8080/car-showrooms">Go to start page</a>
</body>
</html>