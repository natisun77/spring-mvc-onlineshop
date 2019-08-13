<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Goods</title>
</head>
<body>
<center>
    <h2>All Goods:</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Edit</th>
            <th>Delete</th>
            <th>Add to Cart.</th>
        </tr>
        <c:forEach var="good" items="${goods}">
            <tr>
                <td><c:out value="${good.name}"/></td>
                <td><c:out value="${good.description}"/></td>
                <td><c:out value="${good.price}"/></td>
                <td><a href="<c:url value="/good/edit?id=${good.id}"/>">edit</a></td>
                <td><a href="<c:url value="/good/delete?id=${good.id}"/>">delete</a></td>
                <td><a href="<c:url value="/cart/addGood?goodId=${good.id}"/>">add o cart</a></td>
            </tr>
        </c:forEach>
    </table>
    </br>
    </br>

    <a href="<c:url value="/good/add"/>">Добавить товар</a>
    </br>
    <a href="<c:url value="/user/all"/>">Пользователи</a>
    </br>
    <a href="<c:url value="/cart/all"/>">Cart</a>
</center>


</body>
</html>