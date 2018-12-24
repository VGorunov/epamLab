<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 03.09.2018
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
      <title>$Title$</title>
      <link rel="stylesheet" type="text/css" href="css/style.css">

  </head>
  <body>
        <div class="header">
            <h3><font color="#f0f8ff">Hello,${user.login}</font></h3>
            <a href="/logout" methods="POST"><p>LogOut</p></a>
        </div>


            <input id= "todayTasks" type="button" name="section" value="TODAY" >
            <input id="tomorrowTasks" type="button" name="section" value="TOMORROW" >
            <input id = "somedayTasks" type="button" name="section" value="SOMEDAY" >
            <input id="fixed" type="button" name="section" value="FIXED" >
            <input id="recycleBin" type="button" name="section" value="RECYCLE BIN" >


        <form method="post" action = "/todo/main">
            description
            <input type="text" name="description" required>
            dateCompletion
            <input type="date" name="dateCompletion" required>
            <input type="submit" value="Send">
        </form>

        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>description</th>
                    <th>date of completion</th>
                    <th>file</th>
                </tr>
            </thead>
            <tbody id="bodyTasks">

            </tbody>
        </table>


        <input id = "delete" type = "button" value="Delete task">
        <input id="executed" type="button" value="Executed task">
        <input id="restore" type="button" value="Restore task">
        <input id="destroy" type="button" value="Destroy task">


        <script>
            <%@include file="/js/script.js"%>
        </script>
  </body>
</html>
