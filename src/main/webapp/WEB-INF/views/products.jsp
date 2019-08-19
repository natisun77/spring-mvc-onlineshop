<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<center>
    <h2>All Products:</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <security:authorize access="hasRole('admin')">
            <th>Edit</th>
            <th>Delete</th>
            </security:authorize>
            <th>Add to Cart</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td><c:out value="${product.name}"/></td>
                <td><c:out value="${product.description}"/></td>
                <td><c:out value="${product.price}"/></td>
                <security:authorize access="hasRole('admin')">
                <td><a href="<c:url value="/product/edit?id=${product.id}"/>">edit</a></td>
                <td><a href="<c:url value="/product/delete?id=${product.id}"/>">delete</a></td>
                </security:authorize>
                <td><a href="<c:url value="/cart/addProduct?productId=${product.id}"/>">add to cart</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <br>

    <a href="<c:url value="/cart/all"/>">Cart</a>
    <br>

    <security:authorize access="hasRole('admin')">
        <a href="<c:url value="/product/add"/>">Add products</a>
        <br>
        <a href="<c:url value="/user/all"/>">List of users</a>
    </security:authorize>



</center>


</body>
</html>