<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 13/05/15
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <meta charset="UTF-8" content=text/html http-equiv="Content-Type">
    <%--    <link rel="stylesheet" href="Style.css" type="text/css">--%>
    <style>
        body {
            background-color: #1e1e1e;
            background-image: url("Images/binding_dark.png");
        }
    </style>
</head>
<body>
<div align="center"><img src="Images/logo.jpg"/></div>
<%--<div align="left"><a href="jsp/Contact.jsp"> Contacts </a> </div>--%>
<div align="left"><a href="Contact"> Contacts </a> </div>
<br>
<div align="left"><a href="protected/Test"> Protected </a> </div>
<br>
<div align="left"><a href="logout"> Logout </a></div>
<div align="center">
    <form method="post" action="Transport">
        <span style="color: azure" > Name </span> &nbsp; <input type="text" name="Name"> <br>
        <span style="color: azure"> Surname </span> &nbsp; <input type="text" name="Surname"> <br>
        <select name="transportType" size="1">
            <option value="refrigerator">refrigerator</option>
            <option value="tilt">tilt</option>
            <option value="platform">platform</option>
        </select> <br>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
