<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 08.09.2018
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/registration" method="POST">
        <label for="login-field">Login</label>
        <input type="text" name="login" id="login-field">
        <br>
        <label for="password-field">Password</label>
        <input type="password" name="password" id="password-field">
        <br>
        <input type="submit" value="Create new account">
    </form>
    <form action="/login.jsp">
        <input type="submit" value="Back">
    </form>
    <b><font color="red">${errorMessage}</font></b>
</body>
</html>
