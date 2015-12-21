<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 17/12/15
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <mytag:head/>
    <script>
        $(document).ready(function () {
            menuHover();
        });
    </script>
    <style>
        td {
            padding: 5px;
        }

/*        div {
            border: solid 1px #666666;
        }*/
    </style>
</head>
<body>
<mytag:logo/>
<mytag:menuBarProtected/>

<div>
    Перед списком
</div>
<div style="border: solid 1px #666666">
    <table border="1">
        <tr>
            <td>Номер счета</td>
            <td>Заказчик</td>
            <td>Загрузка</td>
            <td>Дата</td>
            <td>Разгрузка</td>
            <td>Дата</td>
        </tr>
        <c:forEach items="${agreement}" var="agr">
        <tr>
            <td>${agr.invoiceNr}</td>
            <td>${agr.clientName}</td>
            <td>${agr.loadingAddress}</td>
            <td><fmt:formatDate value="${agr.loadingDate}" pattern="dd.MM.yyyy"/></td>
            <td>${agr.unloadingAddress}</td>
            <td><fmt:formatDate value="${agr.unloadingDate}" pattern="dd.MM.yyyy"/></td>
        </tr>
        </c:forEach>
        </table>
</div>
<div>
    После списка
</div>
<br>

<div>
    <c:out value="${message}"/>
</div>
<br>
</body>
</html>
