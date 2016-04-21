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
    <script>
        $(document).ready(function () {

            function loopSpansInMainTextToPopup() {
                var spanLength = $(".global-main-text span");
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
            localeInit();
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
<mytag:logo/>
<mytag:menuBar/>
<img id="daf" src="Images/DAFSketch.png">

<div class="global-main-text"><%--<span>SIA VelexAuto</span> - это компания, основаная в январе 2014 года человеком с более чем
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
    на предоставление <span>качественных</span> услуг, чтобы к нам возвращались снова и снова.--%><spring:message code="locale.about-us-text"/></div>
</body>
</html>