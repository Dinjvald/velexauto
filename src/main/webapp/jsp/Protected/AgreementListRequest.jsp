<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 17/12/15
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <mytag:head/>
    <script>
        $(document).ready(function() {
            menuHover();
        });
    </script>
    <style>
        div {
            border: solid 1px azure;
            padding: 5px;
        }
    </style>
</head>
<body>
<mytag:logo/>
<mytag:menuBarProtected/>
<div>
    <form method="post" action="agreementListResult">
        <label for="startDate">Начало</label>
        <input type="text" name="startDate" class="date" id="startDate" value="01.01.2015">
        <label for="endDate">Конец</label>
        <input type="text" name="endDate" class="date" id="endDate" value="11.02.2015">
        <input type="submit">
    </form>
</div>
</body>
</html>
