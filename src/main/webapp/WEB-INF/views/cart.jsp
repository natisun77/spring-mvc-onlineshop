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
        <c:forEach var="product" items="${productsInCart}">
            <tr>
                <td><c:out value="${product.name}"/></td>
                <td><c:out value="${product.description}"/></td>
                <td><c:out value="${product.price}"/></td>
                <td><a href="<c:url value="/cart/deleteProduct?productId=${product.id}"/>">delete</a></td>
            </tr>
        </c:forEach>
    </table>
    </br>
    </br>

    <a href="<c:url value="/product/all"/>">Add more products to cart</a>
    </br>
    <a href="<c:url value="/cart/deleteAllProducts"/>">Clean cart</a>
    </br>
    <a href="<c:url value="/booking/add"/>">Make booking</a>

</center>


</body>
</html>