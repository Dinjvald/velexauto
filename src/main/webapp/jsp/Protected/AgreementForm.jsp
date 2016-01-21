<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 03/12/15
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <mytag:head/>
    <script>
        $(document).ready(function () {

            datepickerInit();
            initAgreementFormAJAX("add-agreement");
            menuHover();

        });
    </script>
</head>
<body>
<mytag:logo/>
<mytag:menuBarProtected/>
<mytag:agreementForm/>
</body>
</html>
