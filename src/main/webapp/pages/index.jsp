<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 05.10.2016
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eshop</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<h2>Categories:</h2>
<c:forEach items="${categories}" var="category">
    <h3><a href="/index?category=${category.type}"/>${category.type}</h3>





</c:forEach>
<hr/>

<jsp:include page="footer.jsp"/>
</body>
</html>
