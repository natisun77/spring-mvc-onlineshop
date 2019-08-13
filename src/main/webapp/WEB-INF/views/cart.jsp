<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<center>
    <h2>In your cart:</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>delete</th>
        </tr>
        <c:forEach var="good" items="${goodsInCart}">
            <tr>
                <td><c:out value="${good.name}"/></td>
                <td><c:out value="${good.description}"/></td>
                <td><c:out value="${good.price}"/></td>
                <td><a href="<c:url value="/cart/deleteGood?goodId=${good.id}"/>">delete</a></td>
            </tr>
        </c:forEach>
    </table>
    </br>
    </br>

    <a href="<c:url value="/good/all"/>">Add more goods</a>
    </br>
    <a href="<c:url value="/cart/deleteAllGoods"/>">Clean cart</a>
    </br>
    <a href="<c:url value="/order/add"/>">Make order</a>
</center>


</body>
</html>