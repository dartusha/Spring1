<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Customer</title>
</head>
<body>

<form action="${action}" method="post">
    <%-- Очень часто применяемый способ передачи id через форму --%>
    <input type="hidden" name="id" id="id" value="${customer.id}">
    <p>
        <label for="fio">Name</label>
        <input type="text" id="fio" name="fio" value="${customer.fio}" />
    </p>
    <input type="submit" />
</form>

<c:url value="/products/create" var="createUrl">
    <%-- Нам нужно указать, продукт какой категории мы создаем --%>
    <c:param name="customerId" value="${customer.id}"/>
</c:url>
<a href="${createUrl}">Create customers</a>

<table border="1">
    <tr>
        <th>Id</th>
        <th>FIO</th>
    </tr>

    <c:forEach items="${customers}" var="cust">
        <tr>
            <td>${cust.id}</td>
            <td>${cust.fio}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>