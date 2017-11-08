<%--
  Created by IntelliJ IDEA.
  User: sysadmin
  Date: 08.11.17
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h2>Meals222</h2>
<table border=1>
    <thead>
    <tr>
        <th>User Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>DOB</th>
        <th>Email</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="user">
        <tr>
            <td><c:out value="${user.userid}" /></td>
            <td><c:out value="${user.firstName}" /></td>
            <td><c:out value="${user.lastName}" /></td>
            <td><fmt:formatDate pattern="yyyy-MMM-dd" value="${user.dob}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><a href="UserController?action=edit&userId=<c:out value="${user.userid}"/>">Update</a></td>
            <td><a href="UserController?action=delete&userId=<c:out value="${user.userid}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
    </table>
</body>
</html>
