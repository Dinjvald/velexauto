<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 21/05/15
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Transport WebSite from Latvia">
    <meta name="keywords" content="Latvia, transport, truck">
    <meta name="author" content="Deniss Beskorovainijs">
    <title>SIA VelexAuto</title>
    <link rel="stylesheet" type="text/css" href="Styles/MainStyle.css">
    <style>
        #account_form {
            margin: auto;
            border: solid 1px #dc7700;
            width: 135px;
            padding: 10px;
            position: relative;
            top: 300px
        }
    </style>
</head>
<body>
<form id="account_form" name="account_form" action="login" method="POST">
    <input type="text" name="username" placeholder="Логин"><br>
    <br>
    <input type="password" name="password" placeholder="Пароль"><br>
    <br>
    <button type="submit">Войти</button>
</form>
</body>
</html>