<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 21/05/15
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <mytag:head lang="en, ru, lv"/>
    <style>
        .global-main-text {
            float: left;
            margin: 10px 60px 0 0;
        }

        #contact-1 {
            position: inherit;
            width: 175px;
        }

        #contact-2 {
            position: inherit;
            width: 310px;
        }

        .popup_text {
            color: #dc7700;
        }

        #details {
            position: inherit;
            width: 225px;
            margin-left: 170px;
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
    <script>
        $(document).ready(function() {
           localeInit()
        });
    </script>
</head>
<body>
<mytag:logo/>
<mytag:menuBar/>
<div id="contact-1" class="global-main-text">
    <span class="popup_text">
        <spring:message code="locale.legal-address"/> <br>
        <spring:message code="locale.actual-address"/> <br>
    </span>
    <br>
    <span class="popup_text"><spring:message code="locale.telephones"/></span><br>
    <spring:message code="locale.vladimir"/><br>
    <br>
    <%--Денис<br>--%>
    <br>

    <span class="popup_text">Е-почта:</span><br>
</div>
<div id="contact-2" class="global-main-text">
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
</div>
<div id="details" class="global-main-text">
    <span class="popup_text"><spring:message code="locale.details"/></span><br>
    SIA “VelexAuto”<br>
    <spring:message code="locale.reg-nr"/> <span class="numbers">45403038976</span><br>
    <spring:message code="locale.vat"/> LV<span class="numbers">45403038976</span><br>
    <br>
    Lāčplēša <span class="numbers">29-36</span>, Aizkraukle,<br>
    Aizkraukles novads, LV-<span class="numbers">5101</span> <br>
    <br>
    AS “Swedbank”, HABALV<span class="numbers">22,</span> <br>
    LV<span class="numbers">59</span>HABA<span class="numbers">0551037808876</span><br>
</div>
</body>
</html>