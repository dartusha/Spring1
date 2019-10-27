<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.geekbrains.*" %>
<html>

<head>
    <title>Products</title>
</head>

<body>

<c:url value="/products/create" var="createUrl">
    <%-- Нам нужно указать, продукт какой категории мы создаем --%>
    <c:param name="categoryId" value="${category.id}"/>
</c:url>
<a href="${createUrl}">Create new Product</a>



<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Category</th>
        <th>Price</th>
    </tr>

    <form action="" method="get">
        <label for="categoryFilter">Category filter</label>
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
        <label for="priceFilter">Price filter  </label>
        <select id="priceFilter" name="priceOpt">
            <option id="opt1">Minimum price</option>
            <option id="opt2">Maximum price</option>
            <option id="opt3">Min and Max price</option>
            <option id="opt4">All</option>
        </select>
        <input type="submit" value="Apply" />
    </form>

    <form action="" method="get">
        <label for="pageFilter">  Page filter</label>
        <select id="pageFilter" name="pageId">
            <c:forEach begin="1" end="${size}" var="i">
                <option>${i}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Apply" />
    </form>

    <c:forEach items="${products}" var="prod">
        <tr>
            <td>${prod.id}</td>

            <td>${prod.name}</td>

            <td>${prod.description}</td>

            <td>${prod.category.name}</td>

            <td>${prod.price}</td>
        </tr>
    </c:forEach>

</table>

</body>

</html>
