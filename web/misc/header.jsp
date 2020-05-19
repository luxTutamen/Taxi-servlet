
<nav class="navbar navbar-dark bg-primary">
    <a class="navbar-brand" href="#">
        <img src="./resources/logo.png" height="50px" class="d-inline-block align-top" alt="">
        <%= request.getAttribute("word.company-name") %>
        <h1>
            <c:forEach var="var" items="${request.getAttributeNames()}" >
                <h1>${var}</h1>
            </c:forEach>
        </h1>
    </a>
    <div class="col-3">
        <%= request.getAttribute("sentence.logged-as") %>
        <%= request.getAttribute("username") %>
    </div>
    <div class="col-3"></div>
    <iframe name="dummyframe" id="dummyframe" style="display: none;"></iframe>
    <!--    language select -->
    <div class="col-2">
        <form action="/api/locale" method="post" target="dummyframe">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <%= request.getAttribute("current-locale") %>
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    {{#locales}}
                    <input type="submit" class="dropdown-item" value="{{name}}" name="name" onClick="window.location.reload();"/>
                    {{/locales}}
                </div>
            </div>
        </form>

    </div>
    <!--  logout    -->
    <div class="col-2">
        <form action="/logout" method="POST">
            <button class="btn btn-outline-secondary my-2 my-sm-0" type="submit"><%= request.getAttribute("word.logout") %>></button>
        </form>
    </div>
</nav>