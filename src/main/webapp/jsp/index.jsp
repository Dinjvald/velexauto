<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 28/02/15
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <mytag:head lang="en, ru, lv"/>
    <script>
        $(document).ready(function () {

            function popupMainWord() {
                var speed = 400;
                $("#first_word").delay(350).animate({color: "#dc7700", fontSize: "40px"}, speed);
                $("#second_word").delay(500).animate({color: "#dc7700", fontSize: "40px"}, speed);
                $("#third_word").delay(650).animate({color: "#dc7700", fontSize: "40px"}, speed);
            }

            popupMainWord();
            localeInit();
        });
    </script>
    <style>

        .first_part {
            position: relative;
            bottom: -30px;
        }

        .second_part {
            position: relative;
            bottom: -50px;
        }

        .third_part {
            position: relative;
            bottom: -70px;
        }

        #daf {
            float: left;
            width: 420px;
            height: 506px;
            margin: 0 20px 0 0;
        }

        #typeAjaxHere {
            position: relative;
            bottom: -150px;
        }

    </style>
</head>
<body>
<%--<a href="index">
    <img id="logo" src="Images/Velex.png"></a>--%>
<mytag:logo/>
<%--<ul id="menu">
    <li id="first-li"><a href="index"> Начало </a></li>
    <li><a href="aboutus"> О нас </a></li>
    <li><a href="contacts"> Контакты </a></li>
    <li><a href="protected/home"> Login </a></li>
</ul>--%>
<mytag:menuBar/>
<img id="daf"
     src="Images/truck000.png">

<div id="first_word" class="first_part"><spring:message code="locale.reliability"/></div>
<div class="global-main-text first_part"><spring:message code="locale.first-part"/></div>
<br>

<div id="second_word" class="second_part"><spring:message code="locale.clarity"/></div>
<div class="global-main-text second_part"><spring:message code="locale.second-part"/></div>
<br>

<div id="third_word" class="third_part"><spring:message code="locale.efficiency"/></div>
<div class="global-main-text third_part"><spring:message code="locale.third-part"/></div>
</body>
</html>
