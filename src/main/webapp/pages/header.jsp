<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 09.10.2016
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="width: 100%; height: 100px; text-align: center; font-size: 32px; background-color: #74dc9a"> Welcome to
    Eshop
</div>
<br/>

<c:choose>
    <c:when test="${user.name ne null}">
        <div>Hello <c:out value="${user.name}"/>!</div>
        <a href="logout">Log Out</a><br/>
    </c:when>
    <c:otherwise>
        <div>Hello anonimus!</div>
        <a href="login">Log In</a><br/>
    </c:otherwise>


</c:choose>

<div style="text-align: right"><a href="shopping">Shopping cart</a></div>
<br/>


</body>
