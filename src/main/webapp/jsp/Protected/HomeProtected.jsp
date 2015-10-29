<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 21/05/15
  Time: 17:43
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
    <link rel="stylesheet" type="text/css" href="../Styles/MainStyle.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

    <script>
        $(document).ready(function () {

            $("#orders").hover(
                    function () {
                        $("ul#menu li ul").slideDown(150);
                    },
                    function () {
                        $("ul#menu li ul").slideUp(150);
                    });

            function doAjax() {
                $.ajax({
                    url : 'testAjax',
                    type : 'GET',

                    success : function(response) {
                        $("#typeAjaxHere").html(response);
                    }
                });
            }

            setTimeout(doAjax, 1500);

        });
    </script>
</head>
<body>
<a href="../index">
    <img id="logo" src="../Images/Velex.png"></a>
<ul id="menu">
    <li id="protected_first_li"><a href="../index"> Домой </a></li>
    <li id="orders">
        <a id="test" href=""> Заявки </a>
        <ul>
            <li><a href="">Что-то там</a></li>
            <li><a href="">Что-то там</a></li>
            <li><a href="">Что-то там</a></li>
        </ul>
    </li>
    <li><a href=""> Что-то там </a></li>
    <li><a href=""> Что-то там </a></li>
    <li><a href="../logout"> Logout </a></li>
</ul>
<div id="typeAjaxHere"></div>
</body>
</html>