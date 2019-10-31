<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.geekbrains.*" %>
<html>

<head>
    <title>Products</title>
</head>

<body>

<a href="/geek_spring_start_war/">Home</a>
<br>
<br>

<c:url value="/products/create" var="createUrl">
</c:url>
<a href="${createUrl}">Create new Product</a>
<br>
<br>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Category</th>
        <th>Price</th>
    </tr>

    <form action="" method="get">
        <label for="categoryFilter">Category filter  </label>
        <select id="categoryFilter" name="categoryId">
            <option value="${-1}" ${param['categoryId'] == null || param['categoryId'] == -1 ? 'selected' : ''}></option>
            <c:forEach items="${categories}" var="category">
                <option value="${category.id}" ${param['categoryId'] == category.id ? 'selected' : ''} >${category.name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Apply" />
    </form>
    <br>

    <form action="" method="get">
        <label for="priceMin">Min Price  </label>
        <input type="number" id="priceMin" name="priceMin" />
        <label for="priceMin">Max Price </label>
        <input type="number" id="priceMax" name="priceMax" />
        <input type="submit" value="Filter" />
    </form>

    <form action="" method="get">
        <label for="pageFilter">  Page filter  </label>
        <select id="pageFilter" name="pageId">
            <c:forEach begin="1" end="${size}" var="i">
                <option>${i}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Apply" />
    </form>

    <c:forEach items="${products}" var="prod">
        <tr>
            <c:url value="/products/edit" var="editUrl">
                <c:param name="id" value="${prod.id}"/>
                <c:param name="categoryId" value="${prod.category.id}"/>
            </c:url>

            <td>${prod.id}</td>

            <td>
                <a href="${editUrl}">${prod.name}</a>
            </td>

            <td>${prod.description}</td>

            <td>${prod.category.name}</td>

            <td>${prod.price}</td>
        </tr>
    </c:forEach>

</table>

</body>

</html>
