<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 02/12/15
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>
        <spring:message code="locale.title"/>
    </title>
    <mytag:head/>
    <script>
        $(document).ready(function () {
            var fullURL = window.location.href;
            console.log(fullURL);
            $("#fullURL").html(fullURL);
            var pathURL = window.location.pathname;
            console.log(pathURL);
            $("#pathURL").html(pathURL);
            var URL = $(location).attr("href").split("?")[0];
            console.log(URL);
            $("#URL").html(URL);

            var urlEn = URL + "?mylocale=en";
            console.log(urlEn);
            var urlRu = URL + "?mylocale=ru";
            console.log(urlRu);

            $("#en-locale-link").attr("href", urlEn);
            $("#ru-locale-link").attr("href", urlRu);

        });
    </script>
</head>
<body>
<div>
    <spring:message code="locale.name"/>
    <spring:message code="locale.age"/>
    <spring:message code="locale.success"/>
</div>

<div id="fullURL"></div>
<div id="pathURL"></div>
<div id="URL"></div>
<br>
<a id="en-locale-link" href="">Англ</a>
<a id="ru-locale-link" href="">Рус</a>
</body>
</html>
