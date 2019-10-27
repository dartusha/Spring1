<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customers</title>
</head>
<body>

<a href="/geek_spring_start_war/">Home</a>
<br>
<br>

<a href="create">Create new Customer</a>

<table border="1">
    <tr>
        <th>Id</th>
        <th>FIO</th>
    </tr>

    <c:forEach items="${customers}" var="customer">
    <tr>
        <c:url value="/customers/edit" var="editUrl">
            <c:param name="id" value="${customer.id}"/>
        </c:url>
        <td>${customer.id}</td>

        <td>
            <a href="${editUrl}">${customer.fio}</a>
        </td>

    </tr>
    </c:forEach>
</table>

</body>
</html>
