<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sping" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
<center>
    <spring:form modelAttribute="user" action="/user/add" method="post">
        <label for="name">Name</label>
        <sping:input path="name" id="name"/>
        <label for="email">Email</label>
        <sping:input path="email" id="email"/>
        <button type="submit">Submit</button>
    </spring:form>>
</center>
</body>
</html>