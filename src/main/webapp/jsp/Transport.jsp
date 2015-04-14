<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 28/02/15
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Transport page</title>
    <meta charset="UTF-8" content=text/html http-equiv="Content-Type">
    <link rel="stylesheet" href="Style.css" type="text/css">
</head>
<body>
<span style="color: azure">
    <%List<String> result = (ArrayList<String>) request.getAttribute("model"); %>
    Hello Mr. <%=result.get(0) + " " + result.get(1)%> <br>
    <%=result.get(2)%>
</span><br>
<div align="rigth"> <a href="/velex/index"> BACK </a> </div>
</body>
</html>
