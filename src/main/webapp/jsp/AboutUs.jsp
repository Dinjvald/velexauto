<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 21/05/15
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en, ru, lv">
    <meta charset="UTF-8">
    <meta name="description" content="Transport WebSite from Latvia">
    <meta name="keywords" content="Latvia, transport, truck">
    <meta name="author" content="Deniss Beskorovainijs">
    <title>SIA VelexAuto</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <!-- Change for jsp START -->
    <link rel="stylesheet" type="text/css" href="Styles/MainStyle.css">
    <!-- Change for jsp END -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script>
        $(document).ready(function () {

            function loopSpansInMainTextToPopup() {
                var spanLength = $(".global_main_text span");
                var speed = 250;
                var delay = 150;
                spanLength.eq(0).animate({color: "#dc7700", fontSize: "24px"}, speed);
                for (var i = 1; i < spanLength.length; i++) {
                    spanLength.eq(i).delay(delay).animate({color: "#dc7700"}, speed);
                    delay += 150;
                    console.log(delay);
                }
            }

            loopSpansInMainTextToPopup();
        });
    </script>
    <style>
        #daf {
            float: right;
            width: 500px;
            height: 365px;
            margin: 20px 10px 0 20px;
        }
    </style>
</head>
<body>
<a href="index">
    <img id="logo" src="Images/Velex.png"></a>
<ul id="menu">
    <li id="first_li"><a href="index"> Начало </a></li>
    <li><a href="aboutus"> О нас </a></li>
    <li><a href="contacts"> Контакты </a></li>
    <li><a href=""> Login </a></li>
</ul>
<img id="daf" src="Images/DAFSketch.png">

<div class="global_main_text">
    <span>SIA VelexAuto</span> - это компания, основаная в январе 2014 года человеком с более чем
    <span>25-ти летним стажем</span> в области грузоперевозок и уже длительное время предоставлет свои услуги. За это
    время мы успели зарекомендовать себя как надежного и честного партнера.<br>
    <br>
    Сотрудничая с нами Вы можете расчитывать на отзывчивость, професионализм, а, самое главное, на
    <span>оперативность</span> в работе. Будьте уверены, что Ваш груз будет доставлен по месту назначения в максимально
    короткие сроки.<br>
    <br>
    Всем известно как быстро может меняться текущее положение вещей в грузоперевозках. Мы всегда <span>открыты</span>
    для диалога, преложений и просьб со стороны клиентов.<br>
    <br>
    Мы можем предложить вам скорость и адекватные расценки на любые грузы для тентовых полуприцепов. Мы всегда нацелены
    на предоставление <span>качественных</span> услуг, чтобы к нам возвращались снова и снова.
</div>
</body>
</html>