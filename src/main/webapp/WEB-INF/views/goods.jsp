<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
            <security:authorize access="hasRole('admin')">
            <th>Edit</th>
            <th>Delete</th>
            </security:authorize>
            <th>Add to Cart</th>
        </tr>
        <c:forEach var="good" items="${goods}">
            <tr>
                <td><c:out value="${good.name}"/></td>
                <td><c:out value="${good.description}"/></td>
                <td><c:out value="${good.price}"/></td>
                <security:authorize access="hasRole('admin')">
                <td><a href="<c:url value="/good/edit?id=${good.id}"/>">edit</a></td>
                <td><a href="<c:url value="/good/delete?id=${good.id}"/>">delete</a></td>
                </security:authorize>
                <td><a href="<c:url value="/cart/addGood?goodId=${good.id}"/>">add to cart</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <br>

    <a href="<c:url value="/cart/all"/>">Cart</a>
    <br>

    <security:authorize access="hasRole('admin')">
        <a href="<c:url value="/good/add"/>">Add goods</a>
        <br>
        <a href="<c:url value="/user/all"/>">List of users</a>
    </security:authorize>



</center>


</body>
</html>