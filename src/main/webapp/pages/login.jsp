<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 11.10.2016
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/html.tld" prefix="html" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login form</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/global.css" />" />
</head>
<body>
<c:import url="header.jsp"/>
<table>
<%--<table cellpadding=2 cellspacing=1 border=0>--%>
    <c:if test="${error ne null}">
        <span style="color: red; align-content: center">${error}</span>
    </c:if>
    <form action="login" method="post">
        <tr>
            <td valign=top>
                Name:<input type="text" name="name" value="<html:requestParametr property="name"/>"><br/>
            </td>
            <br/>
            <td valign=top>
                Password:<input type="password" name="password"><br/>
                <input type="submit" value="Login">
            </td>
        </tr>
    </form>


</table>
<a href="registration">Create new account</a>
<c:import url="footer.jsp"/>
</body>
</html>
