<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Categories</title>
</head>
<body>

<a href="/geek_spring_start_war/">Home</a>
<br>
<br>
<a href="create">Create new Category</a>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
    </tr>

    <c:forEach items="${categories}" var="category">
        <tr>
            <c:url value="/categories/edit" var="editUrl">
                <c:param name="id" value="${category.id}"/>
            </c:url>
            <td>${category.id}</td>

            <td>
                <a href="${editUrl}">${category.name}</a>
            </td>

            <td>${category.description}</td>
        </tr>
    </c:forEach>

</table>
</body>

</html>