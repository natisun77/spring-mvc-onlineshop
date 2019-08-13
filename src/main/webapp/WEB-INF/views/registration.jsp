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
    Форма регистрации
    <br>
    <c:set var="message" value='${requestScope["message"]}'/>
    <c:if test="${not empty message}"><p>${message}</p></c:if>

    <form action="<c:url value="/registration"/>" method="post">
        Имя <input type="text" name="firstName"/><br><br>
        Фамилия <input type="text" name="lastName"/><br><br>
        Имеил <input type="text" name="email"/><br><br>
        Пароль <input type="password" name="password"/><br><br>
        Повторите пароль <input type="password" name="repeatedPassword"/><br><br>
        <input type="submit">
    </form>

    <a href="index.jsp">Войти в систему</a>
</center>
</body>
</html>
