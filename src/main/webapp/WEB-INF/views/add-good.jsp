<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add good</title>
</head>
<body>
<center>
    <spring:form modelAttribute="good" action="/good/add" method="post">
        <label for="name">Name</label>
        <spring:input path="name" id="name"/><br><br>
        <label for="description">Description</label>
        <spring:input path="description" id="description"/><br><br>
        <label for="price">Price</label>
        <spring:input path="price" id="price"/><br><br>
        <button type="submit">Submit</button>
    </spring:form>
</center>
</body>
</html>