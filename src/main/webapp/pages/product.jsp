<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 09.10.2016
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h3>Product:</h3>


<c:forEach items="${listproducts}" var="product">

    <span><img src="http://localhost:8080/image?id=${product.id}" width="80" height="100"/></span><br/>
    <span style="font-size: 12px; color: blue">${product.name} </span> <br/>
    <span style="font-size: 16px; color: red">${product.price} UAH</span><br/>
    <span></span><button type="submit" name="${product.id}" style="color: green">Buy</button></span> <br/>
</c:forEach>


<jsp:include page="footer.jsp"/>
</body>
</html>
