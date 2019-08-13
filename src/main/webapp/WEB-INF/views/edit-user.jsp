<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<center>
    <head>
        <title>Edit User</title>
    </head>
    <body>
    <h2> Edit User</h2>


    <spring:form modelAttribute="userPayload" action="/user/edit" method="post">
        <spring:input path="id" hidden="true"/>

        <label for="firstName">First name</label>
        <spring:input path="firstName" id="firstName"/> <br><br>

        <label for="lastName">Last name</label>
        <spring:input path="lastName" id="lastName"/> <br><br>

        <label for="email">Email</label>
        <spring:input path="email" id="email"/> <br><br>

        <label for="password">Password</label>
        <spring:input path="password" id="password"/> <br><br>

        <label for="repeatedPassword">Repeated password</label>
        <spring:input path="repeatedPassword" id="repeatedPassword"/> <br><br>


        <button type="submit">Submit</button>
    </spring:form>

    </body>
</center>
</html>