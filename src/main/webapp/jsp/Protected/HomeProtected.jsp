<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 21/05/15
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <mytag:head lang="ru"/>
    <style>
        div {
            /*border: solid 1px orange;*/
            font-size: 17px;
        }

        #company-info {
            position: relative;
            left: 50%;
            transform: translate(-50%);
        }

        #company-info > div:nth-child(2) {
            margin-left: 30px;
        }

        #table-company-info > tbody > tr > td:last-child {
            padding-left: 25px;
        }

        #div-employee-table {
            width: 635px;
            position: relative;
            left: 50%;
            transform: translate(-50%);
            margin-top: 30px;
        }

        #employee-table, #employee-table > thead > tr > th, #employee-table > tbody > tr > td {
            border-collapse: collapse;
            border: 2px solid azure;
            padding: 5px;
        }

        #div-employee-table > p {
            position: relative;
            width: 215px;
            left: 50%;
            transform: translate(-50%);
            font-size: 140%;
            margin-bottom: 10px;
        }
    </style>
    <script>
        $(document).ready(function () {
            menuHover();

            function showCurrentEmployee() {
                var emplID = $.trim($("#current-employee-id").html());
                $(".table-employee-id").each(function () {
                    if (emplID == $(this).html()) {
                        $(this).parent().css({"color": "#dc7700"});
                    }
                });
            }

            showCurrentEmployee();
        });

    </script>
</head>
<body>
<c:set var="dateFormat" value="dd.MM.yyyy" scope="page"/>
<mytag:logo/>
<mytag:menuBarProtected/>
<div class="display-none" id="current-employee-id">
    ${currentEmployee.employeeId}
</div>
<div class="div-display-inline-block" id="company-info">
    <%--<div class="div-display-inline-block">
        Название компании:<br>
        Рег. номер:<br>
        Номер НДС:<br>
        Дата основания:<br>
        Юрид. адрес:<br>
        Фактич. адрес:<br>
        Номер телефона:<br>
        Э-почта:<br>
        Вэб-сайт:<br>
        Текущий пользователь:<br>
    </div>
    <div class="div-display-inline-block">
        ${currentCompany.name}<br>
        ${currentCompany.registrationNr}<br>
        ${currentCompany.vatNr}<br>
        <fmt:formatDate var="establishmentDate" value="${currentCompany.establishmentDate}" pattern="${dateFormat}"/>
        ${establishmentDate}<br>
        ${currentCompany.address}<br>
        ${currentCompany.legalAddress}<br>
        ${currentCompany.telephoneNr}<br>
        ${currentCompany.eMail}<br>
        ${currentCompany.web}<br>
        ${currentEmployee.name} ${currentEmployee.surname}<br>
    </div>--%>
    <div>
        <table id="table-company-info">
            <tbody>
            <tr>
                <td>Название компании:</td>
                <td>${currentCompany.name}</td>
            </tr>
            <tr>
                <td>Рег. номер:</td>
                <td>${currentCompany.registrationNr}</td>
            </tr>
            <tr>
                <td>Номер НДС:</td>
                <td>${currentCompany.vatNr}</td>
            </tr>
            <tr>
                <td>Дата основания:</td>
                <td><fmt:formatDate var="establishmentDate" value="${currentCompany.establishmentDate}"
                                    pattern="${dateFormat}"/>
                    ${establishmentDate}</td>
            </tr>
            <tr>
                <td>Юрид. адрес:</td>
                <td>${currentCompany.address}</td>
            </tr>
            <tr>
                <td>Фактич. адрес:</td>
                <td>${currentCompany.legalAddress}</td>
            </tr>
            <tr>
                <td>Номер телефона:</td>
                <td>${currentCompany.telephoneNr}</td>
            </tr>
            <tr>
                <td>Э-почта:</td>
                <td>${currentCompany.eMail}</td>
            </tr>
            <tr>
                <td>Вэб-сайт:</td>
                <td>${currentCompany.web}</td>
            </tr>
            <tr>
                <td>Текущий пользователь:</td>
                <td>${currentEmployee.name} ${currentEmployee.surname}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="div-employee-table">
    <p>Список сотрудников</p>
    <%--<div>Список сотрудников</div>--%>
    <table id="employee-table">
        <thead>
        <tr>
            <th>Имя, Фамилия</th>
            <th>Персональный код</th>
            <th>Э-почта</th>
            <th>Контактный номер</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employee}" var="empl">
            <tr>
                <td>${empl.name} ${empl.surname}</td>
                <td>${empl.personalCode}</td>
                <td>${empl.eMail}</td>
                <td>${empl.telephoneNr}</td>
                <td class="display-none table-employee-id">${empl.employeeId}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>