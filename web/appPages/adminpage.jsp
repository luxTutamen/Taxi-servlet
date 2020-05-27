<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: myself
  Date: 4/29/20
  Time: 7:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="/public/css/login-page.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Taxi company</title>
</head>

<%--
<nav class="navbar navbar-dark bg-primary">
    <a class="navbar-brand" href="#">
        <img src="./resources/logo.png" height="50px" class="d-inline-block align-top" alt="">
        {{word.company-name}}
    </a>
    <div class="col-3">
        {{sentence.logged-as}}
        {{username}}
    </div>
    <div class="col-3"></div>
    <iframe name="dummyframe" id="dummyframe" style="display: none;"></iframe>
    <!--    language select -->
    <div class="col-2">
        <form action="/api/locale" method="post" target="dummyframe">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    {{current-locale}}
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    {{#locales}}
                    <input type="submit" class="dropdown-item" value="{{toString}}" name="name" onClick="window.location.reload();"/>
                    {{/locales}}
                </div>
            </div>
        </form>

    </div>
    <!--  logout    -->
    <div class="col-2">
        <form action="/logout" method="POST">
            <button class="btn btn-info my-2 my-sm-0" type="submit">{{word.logout}}</button>
        </form>
    </div>
</nav>
--%>
<jsp:include page="../misc/navbar.jsp"/>

<body>
    <h1>Admin page <%= ((Map<String, Object>)request.getAttribute("model")).get("hehe") %></h1>
    <div class="container">
        <div class="row">

            <div class="col-4">
                <div class="card">
                    <div class="card-header">
                        <p>clients </p>
                        <form action="/adminpage/prevpage" method="post">
                            <button class="btn btn-light" type="submit" name="what" value="clientPage">prev clientPage</button>
                        </form>
                        <form action="/adminpage/nextpage" method="post">
                            <button class="btn btn-light" type="submit" name="what" value="clientPage">next clientPage</button>
                        </form>
                        <label class="col-1">Page: <%= ((Map<String, Object>)request.getAttribute("model")).get("clientPage") %> </label>
                    </div>
                    <div class="card-body">
                        <ul class="list-group">
                            <%--
                            {{#clients-list}}
                            <li class="list-group-item">
                                <p>name: {{username}}</p>
                                <p>balance: {{money}}</p>
                                <form method="post" action="adminpage/ban">
                                    <button class="btm btn-danger" type="submit" name="bannedId" value={{id}}>ban</button>
                                </form>
                            </li>
                            {{/clients-list}}
                            --%>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="col-4">
                <div class="card">
                    <div class="card-header">
                        <p>drivers</p>
                        <form action="/adminpage/prevpage" method="post">
                            <button class="btn btn-light" type="submit" name="what" value="driverPage">prev driverPage</button>
                        </form>
                        <form action="/adminpage/nextpage" method="post">
                            <button class="btn btn-light" type="submit" name="what" value="driverPage">next driverPage</button>
                        </form>
                        <label class="col-1">Page: {{driverPage}}</label>
                    </div>
                    <div class="card-body">
                        <ul class="list-group">
                            <%--
                            {{#drivers-list}}
                            <li class="list-group-item">
                                <p>name: {{username}}</p>
                                <p>balance: {{balance}}</p>
                                <form method="post" action="adminpage/ban">
                                    <button class="btm btn-danger" type="submit" name="bannedId" value={{id}}>ban</button>
                                </form>
                            </li>
                            {{/drivers-list}}
                            --%>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="col-4">
                <div class="card">
                    <div class="card-header">
                        <p>admins</p>
                        <form action="/adminpage/prevpage" method="post">
                            <button class="btn btn-light" type="submit" name="what" value="adminPage">prev adminPage</button>
                        </form>
                        <form action="/adminpage/nextpage" method="post">
                            <button class="btn btn-light" type="submit" name="what" value="adminPage">next adminPage</button>
                        </form>
                        <label class="col-1">Page: <%= ((Map<String, Object>)request.getAttribute("model")).get("adminPage") %></label>
                    </div>
                    <div class="card-body">
                        <ul class="list-group">
                            <%--
                            {{#admins-list}}
                            <li class="list-group-item">
                                <p>name: {{username}}</p>
                                <form method="post" action="adminpage/ban">
                                    <button class="btm btn-danger" type="submit" name="bannedId" value={{id}}>ban</button>
                                </form>
                            </li>
                            {{/admins-list}}
                            --%>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </div>

</body>
</html>
