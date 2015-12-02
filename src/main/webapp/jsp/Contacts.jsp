<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 21/05/15
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <mytag:head lang="en, ru, lv"/>
    <style>
        .global_main_text {
            float: left;
            margin: 10px 60px 0 0;
        }

        .popup_text {
            color: #dc7700;
        }

        #details {
            margin-left: 186px;
            margin-right: 0;
        }

        .numbers {
            font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif;
        }

        .email:link, .email:visited {
            color: white;
            text-decoration: none;
        }

        .email:hover, .email:active {
            color: #dc7700;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<mytag:logo/>
<mytag:menuBar/>
<div class="global_main_text">
    <span class="popup_text">
        Юридический адрес:<br>
        Фактический адрес:<br>
    </span>
    <br>
    <span class="popup_text">Телефоны:</span><br>
    Владимир<br>
    <br>
    <%--Денис<br>--%>
    <br>

    <span class="popup_text">Е-почта:</span><br>
</div>
<div class="global_main_text">
    Lāčplēša iela <span class="numbers">29-36</span>, Aizkraukle, LV-<span class="numbers">5101</span><br>
    Lāčplēša iela <span class="numbers">29-36</span>, Aizkraukle, LV-<span class="numbers">5101</span><br>
    <br>
    <br>
    <span class="numbers">
        +371 26338745<br>
        +375 298409229<br>
        <%--+371 26936982<br>--%>
    </span>
    <br>
    <a class="email" href="mailto:velexvladimir@gmail.com">velexvladimir@gmail.com</a><br>
    <a class="email" href="mailto:velexauto@gmail.com">velexauto@gmail.com</a>
</div>
<div id="details" class="global_main_text">
    <span class="popup_text">Реквизиты:</span><br>
    SIA “VelexAuto”<br>
    Рег.: <span class="numbers">45403038976</span><br>
    НДС: LV<span class="numbers">45403038976</span><br>
    <br>
    Lāčplēša <span class="numbers">29-36</span>, Aizkraukle,<br>
    Aizkraukles novads, LV-<span class="numbers">5101</span> <br>
    <br>
    AS “Swedbank”, HABALV<span class="numbers">22,</span> <br>
    LV<span class="numbers">59</span>HABA<span class="numbers">0551037808876</span><br>
</div>
</body>
</html>