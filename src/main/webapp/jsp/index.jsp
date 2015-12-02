<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 28/02/15
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <mytag:head lang="en, ru, lv"/>
    <script>
        $(document).ready(function () {

            function popupMainWord() {
                var speed = 400;
                $("#first_word").animate({color: "#dc7700", fontSize: "40px"}, speed);
                $("#second_word").delay(150).animate({color: "#dc7700", fontSize: "40px"}, speed);
                $("#third_word").delay(350).animate({color: "#dc7700", fontSize: "40px"}, speed);
            }

            popupMainWord();

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
    <li id="first_li"><a href="index"> Начало </a></li>
    <li><a href="aboutus"> О нас </a></li>
    <li><a href="contacts"> Контакты </a></li>
    <li><a href="protected/home"> Login </a></li>
</ul>--%>
<mytag:menuBar/>
<img id="daf"
     src="Images/truck000.png">

<div id="first_word" class="first_part"> Надежность</div>
<div class="global_main_text first_part"> Мы считаем, что главное в выгодных партнерских отношениях является возможность
    положиться друг на друга. Имено это свойство мы стараемся выдерживать.
</div>
<br>

<div id="second_word" class="second_part"> Честность</div>
<div class="global_main_text second_part"> Мы ценим доверие своих партнеров. Поэтому мы всегда предоставляем оперативную
    и правдивую информацию о движении груза и текущем состоянии перевозки.⁄
</div>
<br>

<div id="third_word" class="third_part"> Оперативность</div>
<div class="global_main_text third_part"> В реалиях современной бизнес среды каждая минута времени критически важна для
    успешной работы предприятия. Мы это понимаем и осуществляем доставку груза в максимально сжатые сроки.
</div>
<div id="typeAjaxHere"></div>
</body>
</html>
