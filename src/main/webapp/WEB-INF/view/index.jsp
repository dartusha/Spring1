<html>
<body>
<h1>${message}</h1>
<h2>Hello World!</h2>
<ul>
    <c:url value="/geek_spring_start_war/categories/" var="categoriesUrl" />
    <li><a href="/geek_spring_start_war/categories/">Categories</a></li>
    <c:url value="/geek_spring_start_war/products/" var="productsUrl" />
    <li><a href="/geek_spring_start_war/products/">Products</a></li>
    <li><a href="/geek_spring_start_war/customers/">Customers</a></li>
</ul>
</body>
</html>
