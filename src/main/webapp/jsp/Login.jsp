<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 21/05/15
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <mytag:head lang="en, ru, lv"/>
    <style>
        #account_form {
            margin: auto;
            /*border: solid 1px #dc7700;*/
            width: 200px;
            padding: 10px;
            position: relative;
            top: 300px
        }

        #account_form > input:not([type=checkbox]) {
            width: 180px;
        }

        #account_form > input[type=checkbox] {
            margin-right: 10px;
        }
    </style>
    <script>
        $(document).ready(function () {

            function isUserCredentialsValid() {
                var URLparameters = $(location).attr("href").split("?")[1].split("&");
                for (var x = 0; x < URLparameters.length; x++) {
                    if (URLparameters[x] == "error") {
                        $("#invalid-credentials").html("Неверный логин или пароль")
                    }
                }
            }

            isUserCredentialsValid();
        });
    </script>
</head>
<body>
<form id="account_form" name="account_form" action="login" method="POST">
    <input type="text" name="username" placeholder="Логин"><br>
    <br>
    <input type="password" name="password" placeholder="Пароль"><br>
    <br>
    <input class="submit-button" type="submit" value="Войти">
    <br><br>
    <input type="checkbox" name="remember-me-param"/>Запомнить меня
    <br><br>
    <div id="invalid-credentials"></div>
</form>
</body>
</html>
