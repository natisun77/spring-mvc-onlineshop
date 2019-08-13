<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<center>

    <h2>In your order "${goodsSize}" goods.</h2>
    </br>

    <h2>Total sum is: "${ totalAmount}" goods.</h2>
    </br>


    </br>
    <spring:form modelAttribute="order" action="/order/add" method="post">
        <label for="address">Address</label>
        <spring:input path="address" id="address"/></br>

        <label for="formOfPayment">Form of payment</label>
        <spring:input path="formOfPayment" id="formOfPayment"/></br>

        <button type="submit">Submit</button>
    </spring:form>


    </br>
    </br>
    </br>
    <a href="<c:url value="/order/delete"/>">Cancel order</a>
    </br>
    <a href="<c:url value="/good/all"/>">Add more goods</a>
    </br>
</center>


</body>
</html>