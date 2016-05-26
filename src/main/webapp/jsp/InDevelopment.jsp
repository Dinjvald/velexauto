<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 26/05/16
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <mytag:head lang="en, ru, lv"/>
    <style>
        body > div {
            font-size: 250%;
        }
    </style>
    <script>
        $(document).ready(function() {
            menuHover();
        });
    </script>
</head>
<body>
<mytag:logo/>
<mytag:menuBarProtected/>
<div>В разработке</div>

</body>
</html>
