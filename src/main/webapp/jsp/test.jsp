<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 03/05/15
  Time: 23:03
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
    <style>
        body {
            background-color: #151515;
            background-image: url("Images/binding_dark.png");
            color: white;
        }
        #wrapper {
            width: 1000px;
            margin: auto;
            border: 1px solid azure;
        }
        ul#menu {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }
        ul#menu li {
            float: left;
        }
        ul#menu li a:link, ul#menu li a:visited {
            display: block;
            width: 120px;
            color: azure;
            font-weight: bold;
            text-align: center;
            background-color: #5a5a5a;
            padding: 10px 5px;
            text-decoration: none;
        }
        ul#menu li a:hover, ul#menu li a:active {
            background-color: #dc7700;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <h2> MENU </h2>
    <ul id="menu">
        <li><a class="link_menu" href=""> О Нас </a></li>
        <li><a class="link_menu" href=""> Услуги </a></li>
        <li><a class="link_menu" href=""> Контакты </a></li>
    </ul>
</div>
</body>
</html>
