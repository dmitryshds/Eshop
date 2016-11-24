<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 02.11.2016
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Success</title>
    </head>
    <body>
        <div class="header-align">
            <c:import url="header.jsp"/>
        </div>
        <div class="content-align">
            <h3>Your order №<c:out value="${order}"/> is accepted</h3>

        </div>
        <div class="footer-align">
            <c:import url="footer.jsp"/>
        </div>
    </body>
</html>
