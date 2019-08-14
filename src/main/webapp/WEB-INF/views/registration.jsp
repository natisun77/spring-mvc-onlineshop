<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.04.2019
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<center>
    <head>
        <title>Registration</title>
    </head>
    <body>
    <h3>Form of registration</h3>
    <br>
    <c:set var="message" value='${requestScope["message"]}'/>
    <c:if test="${not empty message}"><p>${message}</p></c:if>

    <form action="<c:url value="/registration"/>" method="post">
        Name <input type="text" name="firstName"/><br><br>
        Surname <input type="text" name="lastName"/><br><br>
        Email <input type="text" name="email"/><br><br>
        Password <input type="password" name="password"/><br><br>
        Repeat password <input type="password" name="repeatedPassword"/><br><br>
        <button type="submit">Submit</button>
    </form>

    <a href="index.jsp">Log in</a>
</center>
</body>
</html>
