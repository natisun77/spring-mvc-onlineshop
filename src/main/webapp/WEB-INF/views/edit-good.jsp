<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<center>
    <head>
        <title>Edit Good</title>
    </head>
    <body>
    <h2>Edit Good</h2>

    <spring:form modelAttribute="good" action="/good/edit" method="post">
        <spring:input path="id" hidden="true"/>

        <label for="name">Name</label>
        <spring:input path="name" id="name"/> <br><br>

        <label for="description">Description</label>
        <spring:input path="description" id="description"/> <br><br>

        <label for="price">Price</label>
        <spring:input path="price" id="price"/> <br><br>

        <button type="submit">Submit</button>
    </spring:form>

    </body>
</center>
</html>