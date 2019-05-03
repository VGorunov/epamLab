<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/styleRegistration.css">
</head>
<body>
<div class="registration-box">
    <img src="css/images/avatar.png" class="avatar">
    <div class="registration">
        <h1>Регистрация</h1>
        <form action="/registration" method="POST">
            <p><label for="login-field">Login</label></p>
            <input type="text" name="login" id="login-field">
            <br>
            <p><label for="password-field">Password</label></p>
            <input type="password" name="password" id="password-field">
            <br>
            <input type="submit" value="Create new account" size="">
        </form>
        <form action="/login.jsp">
            <input type="submit" value="Back">
        </form>
        <b><font color="red">${errorMessage}</font></b>
    </div>
</div>
</body>
</html>
