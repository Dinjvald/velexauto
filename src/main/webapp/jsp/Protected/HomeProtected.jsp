<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 21/05/15
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <mytag:head lang="ru"/>
    <script>
        $(document).ready(function () {

            $("ul#menu li#orders").hover(
                    function () {
                        $("ul#menu li ul#orders_submenu").slideDown(150);
                    },
                    function () {
                        $("ul#menu li ul#orders_submenu").slideUp(150);
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
        <ul id="orders_submenu">
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