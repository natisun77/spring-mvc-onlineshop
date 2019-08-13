<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<center>
    <head>
        <title>Users: </title>
    </head>
    <body>

    <h2>All Users:</h2>
    <table border="1">
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
            <th>Password</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="u" items="${users}">
            <tr>
                <td><c:out value="${u.firstName}"/></td>
                <td><c:out value="${u.lastName}"/></td>
                <td><c:out value="${u.email}"/></td>
                <td><c:out value="${u.password}"/></td>


                <td><a href="<c:url value="/user/edit?id=${u.id}"/>">edit</a></td>
                <td><a href="<c:url value="/user/delete?id=${u.id}"/>">delete</a></td>
            </tr>
        </c:forEach>
    </table>
    </br>

    <a href="<c:url value="/registration"/>">Регистрация</a>
    </br>
    <a href="<c:url value="/good/all"/>">Товары</a>
    </br>
    <a href="<c:url value="/cart/all"/>">Cart</a>

    </body>
</center>
</html>