<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 11.10.2016
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="html" uri="http://eshop.shds.bagira.biz" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration form</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<c:if test="${error ne null}">
    <span style="color: red; align-content: center">${error}</span>
</c:if>
<center>
    <table cellpadding=4 cellspacing=2 border=0>
        <th bgcolor="#74dc9a" colspan=2>
            <font size=5>USER REGISTRATION</font>
            <font size=1>* Required Fields</font>
        </th>
        <tr bgcolor="#74dc9a">
            <td valign=top>
                <form action="registration" method="post">
                    Name*:
                    <input type="text" name="name" size=15 maxlength=20 value="<html:requestParametr property="name"/>"><br/>
            <td valign=top>
                E-mail*:
                <input type="text" name="email" size=25 maxlength=125 value="<html:requestParametr property="email"/>"><br/>
        </tr>
        <tr bgcolor="#74dc9a">
            <td valign=top>
                Password*:
                <input type="password" name="password" size=15 maxlength=20><br/>
            </td>
            <td valign=top>
                Confirm password*:
                <input type="password" name="confpass" size=15 maxlength=20><br/>
        </tr>
        <tr bgcolor="#74dc9a">
            <td valign=top colspan=2>
                Phone number:
                <input type="text" name="phone" size=10 maxlength=15 ><br/>
            </td>
        </tr>
        <tr bgcolor="#74dc9a">
            <td valign=top colspan=2>
                Address:
                <input type="text" name="address" size=100 maxlength=200><br/>
            </td>
        </tr>
        <tr bgcolor="#74dc9a">
            <td align=center colspan=2>
                <input type="submit" value="Register">
            </td>
        </tr>
    </table>
</center>
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>
