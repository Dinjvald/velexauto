<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 28/02/15
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en, ru">
    <meta charset="UTF-8">
    <meta name="description" content="Transport WebSite from Latvia">
    <meta name="keywords" content="Latvia, transport, truck">
    <meta name="author" content="Deniss Beskorovainijs">
    <title>SIA VelexAuto</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script>
        $(document).ready(function () {
            var speed = 400;
            $("#first_main_word").animate({color: "#dc7700", fontSize: "40px"}, speed);
            $("#second_main_word").delay(150).animate({color: "#dc7700", fontSize: "40px"}, speed);
            $("#third_main_word").delay(350).animate({color: "#dc7700", fontSize: "40px"}, speed);
        });
    </script>
    <style>
        body {
            background-color: #151515;
            background-image: url("Images/binding_dark.png");
            color: white;
            width: 1000px;
            margin: auto;
            /*border: 1px solid azure;
            border-bottom: none;
            border-top: none;*/
        }

        div {
            font-family: Georgia, serif;
        }

        ul#menu {
            list-style-type: none;
            margin: 0 0 30px 0;
            padding: 0;
            overflow: hidden;
        }

        ul#menu li {
            float: left;
            border-bottom: 2px solid #dc7700;
        }

        ul#menu li a:link, ul#menu li a:visited {
            display: block;
            width: 120px;
            color: azure;
            font-weight: bold;
            text-align: center;
            background-color: #424242;
            padding: 10px 5px;
            text-decoration: none;
        }

        ul#menu li a:hover, ul#menu li a:active {
            background-color: #dc7700;
        }

        .main_text {
            text-align: justify;
            text-justify: inter-word;
        }

        #first_main_div, #first_main_word {
            position: relative;
            bottom: -30px;
        }

        #second_main_div, #second_main_word {
            position: relative;
            bottom: -50px;
        }

        #third_main_div, #third_main_word {
            position: relative;
            bottom: -70px;
        }

        #logo {
            width: 435px;
            height: 100px;
            margin: 20px 0 20px 20px;
        }

        #daf {
            float: left;
            width: 420px;
            height: 506px;
            margin: 0 20px 0 0;
        }

    </style>
</head>
<body>
<a href=""><img id="logo" src="Images/Velex.png"></a>
<ul id="menu">
    <li><a href=""> О Нас </a></li>
    <li><a href=""> Услуги </a></li>
    <li><a href=""> Контакты </a></li>
    <li><a href=""> Что-то там </a></li>
    <li><a href=""> Что-то там </a></li>
    <li><a href=""> Что-то там </a></li>
    <li><a href=""> Что-то там </a></li>
</ul>
<img id="daf" src="Images/truck000.png">

<div id="first_main_word" class="main_text"> Надежность.</div>
<div id="first_main_div" class="main_text"> Мы считаем, что главное в выгодных
    партнерских отношениях является возможность положиться друг на друга. Имено это свойство мы стараемся выдерживать.
</div>
<br>

<div id="second_main_word" class="main_text"> Честность.</div>
<div id="second_main_div" class="main_text"> Мы ценим доверие своих партнеров.
    Поэтому мы всегда предоставляем оперативную и правдивую информацию о движении груза и текущем состоянии перевозки
</div>
<br>

<div id="third_main_word" class="main_text"> Оперативность.</div>
<div id="third_main_div" class="main_text"> В реалиях современной бизнес среды
    каждая минута времени критически важна для успешной работы предприятия. Мы это понимаем и осуществляем доставку
    груза в максимально сжатые сроки.
</div>
</body>
</html>
