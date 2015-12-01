<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 05/11/15
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en, ru, lv">
    <meta charset="UTF-8">
    <meta name="description" content="Transport WebSite from Latvia">
    <meta name="keywords" content="Latvia, transport, truck">
    <meta name="author" content="Deniss Beskorovainijs">
    <title>SIA VelexAuto</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script>
        $(document).ready(function () {

            function checkAge (age) {
                if (age == "") {
                    return "0";
                } else {
                    return age;
                }
            }

            function sendAJAX() {
                var dataToSend = {};
                dataToSend["username"] = $("#username").val();
                dataToSend["age"] = checkAge($("#age").val());
                dataToSend["date"] = $("#date").val();

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "dotheajax",
                    data: JSON.stringify(dataToSend),
                    dataType: "text",
                    success: function (response) {
                        $("#typeAjaxHere").html(response);
                    }
                });
            }

            $("#form").submit(function (event) {
                event.preventDefault();
                sendAJAX();
            })
        });
    </script>
</head>
<body>
<div id="form">
    <form id="user_form">
        <label for="username">Name</label>
        <input type="text" name="username" id="username">
        <label for="age">Age</label>
        <input type="text" name="age" id="age">
        <label for="date">Birth date</label>
        <input type="text" name="date" id="date">
        <input type="submit" value="Submit">
    </form>
</div>
<br>

<div id="typeAjaxHere"></div>
</body>
</html>
