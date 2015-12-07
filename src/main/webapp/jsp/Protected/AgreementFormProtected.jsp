<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 03/12/15
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <mytag:head/>
    <style>
        div#agreementForm {
            margin: auto;
            width: 85%;
            /*border: solid 1px;*/
        }

        div#agreementForm label, div#agreementForm input {
            margin-bottom: 5px;
            margin-top: 5px;
        }

        div#agreementForm input {
            border: solid 1px #666666;
            height: 25px;
            font-size: 15px;
            width: 263px;
            background-color: #252525;
            color: azure;
        }

        div#agreementForm textarea#notes {
            height: 200px;
            width: 400px;
            background-color: #252525;
            border: solid 1px #666666;
            color: azure;
            font-size: 15px;

        }

        div.input {
            display: inline-block;
            /*border: solid 1px red;*/
            margin: 3px 7px 3px;
        }

        div.input #loadingAddress, div.input #unloadingAddress {
            width: 404px;
        }

        div.input input#loadingDate, div.input input#unloadingDate, div.input input#invoiceSendDate {
            width: 110px;
        }

        #saveAgreement:link, #saveAgreement:visited {
            width: 200px;
            height: 40px;
            background-color: #252525;
            border: solid 1px #666666;
            color: azure;
            text-align: center;
            text-decoration: none;
            font-weight: bold;
            font-size: larger;
            line-height: 40px;
            float: right;
            margin-top: 182px;
            margin-right: 20px;
        }

        #saveAgreement:active, #saveAgreement:hover {
            border: solid 1px #dc7700;
            color: #dc7700;
        }

        .ui-datepicker {
            background: #252525;
            border: solid 1px #666666;
            color: azure;
        }
        .ui-datepicker-calendar a.ui-state-default:hover {
            background: #dc7700;
            border: solid 1px #dc7700;
        }

        .ui-datepicker-calendar .ui-datepicker-today a {
            background: #666666;
            border: solid 1px #666666;
        }

        /*.ui-datepicker-trigger {*/
            /*position: relative;*/
            /*top: 6px;*/
            /*left: 7px;*/
        /*}*/

    </style>
    <script>
        $(document).ready(function () {

            $.datepicker.regional["ru"] = {
                monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август",
                    "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
                monthNamesShort: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август",
                    "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
                dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пн", "Сб"],
                currentText: "Сегодня",
                closeText: "Закрыть",
                dateFormat: "dd.mm.yy",
                showButtonPanel: true,
                changeMonth: true,
                changeYear: true
                /*showOn: "button",
                buttonImage: "../Images/CalendarButton.png",
                buttonImageOnly: true*/
            };

            $.datepicker.setDefaults(
                    $.datepicker.regional["ru"]
            );

            function datePickerInit() {
                $("#loadingDate").datepicker();
                $("#unloadingDate").datepicker();
                $("#invoiceSendDate").datepicker();
            }

            function getAgreementFormData() {
                var input = $("#agreementForm input");
                var data = {};
                for (var i = 0; i < input.length; i++) {
                    var name = input.eq(i).attr("name");
                    var id = "#" + name;
                    data[name] = $(id).val();
                }
                data["notes"] = $("#notes").val();
                return data;
            }

            function postAgreementAJAX() {
                var dataToSend = getAgreementFormData();
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "addagreement",
                    data: JSON.stringify(dataToSend),
                    dataType: "text",
                    success: function(response) {
                        $("#result").html(response);
                    }
                })
            }

            $("#saveAgreement").click(function () {
                postAgreementAJAX();
                return false;
            });

            menuHover();
            datePickerInit();
        });
    </script>
</head>
<body>
<mytag:logo/>
<mytag:menuBarProtected/>
<div id="result">

</div>
<div id="agreementForm">
    <form action="">
        <div class="input">
            <label for="loadingDate">Дата загрузки</label><br>
            <input type="text" name="loadingDate" readonly="readonly" id="loadingDate">
            <br>
            <label for="loadingAddress">Адрес загрузки</label><br>
            <input type="text" name="loadingAddress" id="loadingAddress">
        </div>

        <div class="input">
            <label for="unloadingDate">Дата разгрузки</label><br>
            <input type="text" name="unloadingDate" readonly="readonly" id="unloadingDate">
            <br>
            <label for="unloadingAddress">Адрес разгрузки</label><br>
            <input type="text" name="unloadingAddress" id="unloadingAddress">
        </div>
        <br>

        <div class="input">
            <label for="clientName">Заказчик</label><br>
            <input type="text" name="clientName" id="clientName">
        </div>
        <div class="input">
            <label for="agreementNr">Номер заявки</label><br>
            <input type="text" name="agreementNr" id="agreementNr">
        </div>
        <div class="input">
            <label for="invoiceNr">Номер счета</label><br>
            <input type="text" name="invoiceNr" id="invoiceNr">
        </div>
        <br>

        <div class="input">
            <label for="onBehalfOf">На основании документа</label><br>
            <input type="text" name="onBehalfOf" id="onBehalfOf">
        </div>

        <div class="input">
            <label for="fileLinkAgreement">Ссылка на заявку</label><br>
            <input type="text" name="fileLinkAgreement" id="fileLinkAgreement">
        </div>

        <div class="input">
            <label for="fileLinkInvoice">Ссылка на счет</label><br>
            <input type="text" name="fileLinkInvoice" id="fileLinkInvoice">
        </div>
        <br>

        <div class="input">
            <label for="price">Цена EUR</label><br>
            <input type="text" name="price" id="price">
        </div>

        <div class="input">
            <label for="valueAddedTax">PVN 21%</label><br>
            <input type="text" name="valueAddedTax" id="valueAddedTax">
        </div>

        <div class="input">
            <label for="paymentTerm">Срок оплаты в днях</label><br>
            <input type="text" name="paymentTerm" id="paymentTerm">
        </div>
        <br>

        <div class="input">
            <label for="driver">Водитель</label><br>
            <input type="text" name="driver" id="driver">
        </div>

        <div class="input">
            <label for="plateNr">Номера сцепки</label><br>
            <input type="text" name="plateNr" id="plateNr">
        </div>

        <div class="input">
            <label for="invoiceSendDate">Счет отправлен</label><br>
            <input type="text" name="invoiceSendDate" readonly="readonly" id="invoiceSendDate">
        </div>
        <br>

        <div class="input" id="notesDiv">
            <label for="notes">Примечания</label><br>
            <%--<input type="text" name="notes" id="notes">--%>
            <textarea name="notes" id="notes"></textarea>
        </div>
        <a href="" id="saveAgreement">Сохранить</a>

        <%--<input type="submit" value="Сохранить" id="saveAgreement">--%>

    </form>
</div>

</body>
</html>
