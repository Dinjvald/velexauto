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
            /*border-right: solid 10px #dc7700;*/
            width: 274px;
            padding-right: 20px;
        }

        .agreement-date-form-child-div {
            display: inline-block;
        }

        #agreement-date-form > div:nth-child(2) {
            margin-right: 25px;
        }

        #agreement-date-form-head {
            /*text-align: center;*/
            font-size: 130%;
            margin-bottom: 10px;
        }

        #agreement-date-form-wrapper > #agreement-date-form > .submit-button {
            margin-top: 20px;
            position: relative;
            left: 100%;
            transform: translate(-100%);
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
<div class="div-display-inline-block">

</div>
</body>
</html>
