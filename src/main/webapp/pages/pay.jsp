<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 28.10.2016
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pay</title>
</head>
<body>



<jsp:include page="header.jsp"/>

<h3>Your choice:</h3>
<c:set var="total" value="${0}"/>
<c:if test="${error ne null}">
    <span style="color: red; align-content: center">${error}</span>
</c:if>

<table align="center" width="50%" border="0">
    <tr>
        <form action="/pay" method="post">
            <c:forEach items="${choise}" var="ch">

    <tr>

        <span><img src="/image?id=${ch.key.id}" width="20" height="20"/></span> <br/>
        <span style="font-size: 8px; color: blue">${ch.key.name} </span>
    <span style="font-size: 16px; color: black">Unit price: ${ch.key.price} UAH</span>


        <span style="font-size: 16px; color: black"> Amount: ${ch.value} items</span>
    <c:set var="summ" value="${ch.key.price * ch.value}"/>
    <span style="font-size: 16px; color: black">Total: <c:out value="${summ}"/> UAH</span>
        <c:set var="total" value="${total + summ}"/>

        </select>

        <br/>
    </tr>

    </c:forEach>

    </tr>
    <button type="submit" name="payment" style="color: green; height:50px; width:150px">PAY THIS </button>
    </form>
</table>
</br>
<span style="font-size: 24px; color: blue">Total price: <c:out value="${total}"/> UAH</span>

<jsp:include page="footer.jsp"/>





</body>
</html>
