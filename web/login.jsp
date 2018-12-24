<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 05.09.2018
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToDo</title>
    <link rel="stylesheet" type="text/css" href="css/styleLogin.css">
</head>
<body>
    <div class="login-box">
        <img src="css/images/avatar.png" class = "avatar">
        <div class="login">
            <h1>ToDoList</h1>
            <form action="/login" method="POST">
                <p><label for="login-field">Login</label></p>
                <input type="text" name="login" id="login-field">
                <p><label for="password-field">Password</label></p>
                <input type="password" name="password" id="password-field">
                <input type="submit" value="Log In" name="log">
                <b><font color="red">${errorMessage}</font></b>
                <a href="registration.jsp">Registration</a>
            </form>
        </div>
    </div>
</body>
</html>
