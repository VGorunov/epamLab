<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>$Title$</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body class="bg-light">
<header>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <span class="navbar-text mr-2">
            <h2 class="h2">Hello,${user.login}</h2>
        </span>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/logout" method="POST">LogOut</a>
            </li>
        </ul>
    </nav>
</header>
<section>
    <div class="mb-3 mt-3">
    <form method="post" class="form-inline" action="/todo/main">
        <div class="form-group mb-2">
            description
            <input type="text" class="form-control" name="description" required>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            date of completion
            <input type="date" class="form-control" name="dateCompletion" required>
        </div>
        <button type="submit" class="btn btn-secondary mb-2">Send</button>
    </form>
    </div>
</section>
<section>
<input id="todayTasks" type="button" class="btn btn-secondary mx-auto" name="section" value="TODAY">
<input id="tomorrowTasks" type="button" class="btn btn-secondary mx-auto" name="section" value="TOMORROW">
<input id="somedayTasks" type="button" class="btn btn-secondary mx-auto" name="section" value="SOMEDAY">
<input id="fixed" type="button" class="btn btn-secondary mx-auto" name="section" value="FIXED">
<input id="recycleBin" type="button" class="btn btn-secondary mx-auto" name="section" value="RECYCLE BIN">
</section>
<section>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">description</th>
        <th scope="col">date of completion</th>
        <th scope="col">file</th>
    </tr>
    </thead>
    <tbody id="bodyTasks">

    </tbody>
</table>
</section>
<section>
<input id="delete" type="button" class="btn btn-secondary" value="Delete task">
<input id="executed" type="button" class="btn btn-secondary" value="Executed task">
<input id="restore" type="button" class="btn btn-secondary" value="Restore task">
<input id="destroy" type="button" class="btn btn-secondary" value="Destroy task">
</section>
<footer class="page-footer font-small blue pt-4">
    <div class="footer-copyright text-center py-3">
        ©2019 Разработчик <a href="https://vk.com/id119733827" target="_blank">Василий Горюнов</a>
    </div>
</footer>

<script>
    <%@include file="/js/script.js"%>
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
