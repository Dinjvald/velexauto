<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 17/12/15
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <mytag:head/>
    <script>
        $(document).ready(function () {
            menuHover();
            datepickerConfig();
            datepickerSetRegionalRU();
            $(".date").datepicker();

        });
    </script>
    <style>
        #agreement-date-form-wrapper {
            width: 274px;
            padding-right: 20px;
            /*margin-right: 30px;*/
        }

        .agreement-date-form-child-div {
            display: inline-block;
        }

        #agreement-date-form > div:nth-child(2) {
            margin-right: 25px;
        }

        #agreement-date-form-head {
            font-size: 130%;
            margin-bottom: 10px;
        }

        #agreement-date-form-wrapper > #agreement-date-form > .submit-button {
            margin-top: 20px;
            position: relative;
            left: 100%;
            transform: translate(-100%);
        }

        .div-display-inline-block {
            display: inline-block;
            /*border: solid 1px azure;*/
        }

        ul.agreement-list-links {
            list-style-type: circle;
        }

        ul.agreement-list-links > li/*:not(:first-child):not(:last-child)*/ {
            margin: 7px 0;
        }

        ul.agreement-list-links > li > a:link, ul.agreement-list-links > li > a:visited {
            text-decoration: none;
            color: azure;
        }

        ul.agreement-list-links > li > a:hover, ul.agreement-list-links > li > a:active {
            color: #dc7700;
        }

        #agreement-list-links-wrapper-first, #agreement-list-links-wrapper-second {
            margin-left: 30px;
        }

        #agreement-list-links-wrapper-second {
            position: relative;
            bottom: 75px;
        }
    </style>
</head>
<body>
<mytag:logo/>
<mytag:menuBarProtected/>
<div id="agreement-date-form-wrapper" class="div-display-inline-block">
    <form id="agreement-date-form" method="post" action="agreement-list-result">
        <div id="agreement-date-form-head">
            Дата разгрузки
        </div>
        <div class="agreement-date-form-child-div">
            <label for="agreement-date-form-start-date">Начало</label>
            <br>
            <input type="text" name="startDate" class="date" id="agreement-date-form-start-date" readonly="readonly"
                   value="01.01.2015">
        </div>
        <div class="agreement-date-form-child-div">
            <label for="agreement-date-form-end-date">Конец</label>
            <br>
            <input type="text" name="endDate" class="date" id="agreement-date-form-end-date" readonly="readonly"
                   value="31.01.2016">
        </div>
        <br>
        <input class="submit-button" type="submit" value="Отправить">
    </form>
</div>
<div class="div-display-inline-block" id="agreement-list-links-wrapper-first">
    <ul class="agreement-list-links">
        <li><a href="this-month-agreements">За этот месяц</a></li>
        <li><a href="this-quarter-agreements">За этот квартал</a></li>
        <li><a href="this-half-year-agreements">За это полугодие</a></li>
        <li><a href="this-year-agreements">За этот год</a></li>
        <li><a href="this-and-last-years-agreements">За этот и прошлый года</a></li>
    </ul>
</div>
<div class="div-display-inline-block" id="agreement-list-links-wrapper-second">
    <ul class="agreement-list-links">
        <li><a href="unpaid-agreements">Неоплаченные перевозки</a></li>
        <li><a href="late-payment-agreements">Просроченные перевозки</a></li>
    </ul>
</div>
</body>
</html>
