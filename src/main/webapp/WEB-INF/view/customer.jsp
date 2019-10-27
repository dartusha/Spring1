<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Customer</title>
</head>
<body>

<a href="/geek_spring_start_war/">Home</a>
<br>
<br>

<form action="${action}" method="post">
    <%-- Очень часто применяемый способ передачи id через форму --%>
    <input type="hidden" name="id" id="id" value="${customer.id}">
    <p>
        <label for="fio">Name</label>
        <input type="text" id="fio" name="fio" value="${customer.fio}" />
    </p>
    <input type="submit" />
</form>

</body>
</html>