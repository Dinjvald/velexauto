<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 03/12/15
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <mytag:head/>
    <style>
        div#agreement {
            margin: auto;
            width: 85%;
        }

        div#agreement label, div#agreement input {
            margin-bottom: 5px;
            margin-top: 5px;
        }

        div#agreement input {
            border: solid 1px #666666;
            height: 25px;
            font-size: 15px;
            width: 263px;
            background-color: #252525;
            color: azure;
        }

        div#agreement textarea#notes {
            height: 200px;
            width: 400px;
            background-color: #252525;
            border: solid 1px #666666;
            color: azure;
            font-size: 15px;
        }

        div.input {
            display: inline-block;
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

        #result {
            /*border: solid 1px #666666;*/
            height: 40px;
            width: 83%;
            margin: auto;
            font-weight: bold;
            font-size: 125%;
            line-height: 40px;
            text-align: right;
        }

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

            function isDataValid() {
                var isValid = true;
                if (!isNumbersValid()) isValid = false;
                if (!isCharValid()) isValid = false;
                if (!isValid) $("#result").html("Проверьте данные");
                return isValid;
            }

            function setDefaultBorderColor(object) {
                $(object).css({"border-color": "#666666"});
            }

            function isInputEmpty(object) {
                return object.val() == "";
            }

            function isNumbersValid() {
                var isNumbersValid = true;

                $(".number").each(function () {
                    setDefaultBorderColor($(this));
                    if (isInputEmpty($(this))) return true;
                    if (!$.isNumeric($(this).val())) {
                        $(this).css({"border-color": "#920007"});
                        isNumbersValid = false;
                    }
                });
                return isNumbersValid;
            }

            function isCharValid() {
                var isCharValid = true;

                $(".char").each(function () {
                    var length = $(this).val().length;

                    setDefaultBorderColor($(this));
                    if (isInputEmpty($(this))) return true;
                    if (length > 200) {
                        $(this).css({"border-color": "#920007"});
                        isCharValid = false;
                    }
                });
                return isCharValid;
            }

            function fillDefaultIfEmpty() {
                $(".number").each(function () {
                    if (isInputEmpty($(this))) $(this).val("-1");
                });
                $(".char").each(function () {
                    if (isInputEmpty($(this))) $(this).val("empty");
                });
                $(".date").each(function () {
                    if (isInputEmpty($(this))) $(this).val("01.01.1971")
                });
                $(".text").each(function () {
                    if (isInputEmpty($(this))) $(this).val("empty");
                });
            }

            function getAgreementFormData() {
                fillDefaultIfEmpty();
                var data = {};
                var agreementForm = $("#agreementForm");

                agreementForm.find("input").each(function () {
                    var name = $(this).attr("name");
                    if ($(this).val() != "") data[name] = $(this).val();
                });
                agreementForm.find("textarea").each(function () {
                    var name = $(this).attr("name");
                    if ($(this).val() != "") data[name] = $(this).val();
                });
                console.log(data);
                return data;
            }

            function postAgreementAJAX() {
                if (!isDataValid()) return false;
                var dataToSend = getAgreementFormData();

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "addagreement",
                    data: JSON.stringify(dataToSend),
                    dataType: "text",
                    success: function (response) {
                        showResult(response);
                        $("#agreementForm")[0].reset();
                    },
                    error: function () {
                        $("#result")
                                .css({"color": "#C40005"})
                                .html("Сервер не может обработать данные.");

                    }
                })
            }

            function showResult(response) {
                var result = $("#result");
                var key = {
                    agreement: "Заявка сохранена"
                };
                var responseArray = JSON.parse(response);

                if (responseArray[0] == "success") {
                    alertSuccess(responseArray[1]);
                }

                if (responseArray[0] == "error") {
                    alertError(responseArray[1]);
                }
            }

            function alertSuccess(key) {
                var result = $("#result");
                var message = {
                    agreement: "Заявка сохранена."
                };

                result
                        .css({"color": "#26a300"})
                        .html(message[key]);
                setTimeout(function () {
                    result.html("");
                }, 10000);
            }

            function alertError(key) {
                var result = $("#result");
                var message = {
                    agreement: "Отказ от сервера. Неверные данные в заявке."
                };

                result
                        .css({"color": "#C40005"})
                        .html(message[key]);
                setTimeout(function () {
                    result.html("")
                }, 15000);
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
<div id="agreement">
    <form id="agreementForm" action="">
        <div class="input">
            <label for="loadingDate">Дата загрузки</label><br>
            <input type="text" name="loadingDate" readonly="readonly" class="date" id="loadingDate">
            <br>
            <label for="loadingAddress">Адрес загрузки</label><br>
            <input type="text" name="loadingAddress" class="char" id="loadingAddress">
        </div>

        <div class="input">
            <label for="unloadingDate">Дата разгрузки</label><br>
            <input type="text" name="unloadingDate" readonly="readonly" class="date" id="unloadingDate">
            <br>
            <label for="unloadingAddress">Адрес разгрузки</label><br>
            <input type="text" name="unloadingAddress" class="char" id="unloadingAddress">
        </div>
        <br>

        <div class="input">
            <label for="clientName">Заказчик</label><br>
            <input type="text" name="clientName" class="char" id="clientName">
        </div>
        <div class="input">
            <label for="agreementNr">Номер заявки</label><br>
            <input type="text" name="agreementNr" class="char" id="agreementNr">
        </div>
        <div class="input">
            <label for="invoiceNr">Номер счета</label><br>
            <input type="text" name="invoiceNr" class="char" id="invoiceNr">
        </div>
        <br>

        <div class="input">
            <label for="onBehalfOf">На основании документа</label><br>
            <input type="text" name="onBehalfOf" class="char" id="onBehalfOf">
        </div>

        <div class="input">
            <label for="fileLinkAgreement">Ссылка на заявку</label><br>
            <input type="text" name="fileLinkAgreement" class="text" id="fileLinkAgreement">
        </div>

        <div class="input">
            <label for="fileLinkInvoice">Ссылка на счет</label><br>
            <input type="text" name="fileLinkInvoice" class="text" id="fileLinkInvoice">
        </div>
        <br>

        <div class="input">
            <label for="price">Цена EUR</label><br>
            <input type="text" name="price" class="number" id="price">
        </div>

        <div class="input">
            <label for="valueAddedTax">PVN EUR</label><br>
            <input type="text" name="valueAddedTax" class="number" id="valueAddedTax">
        </div>

        <div class="input">
            <label for="paymentTerm">Срок оплаты в днях</label><br>
            <input type="text" name="paymentTerm" class="number" id="paymentTerm">
        </div>
        <br>

        <div class="input">
            <label for="driver">Водитель</label><br>
            <input type="text" name="driver" class="char" id="driver">
        </div>

        <div class="input">
            <label for="plateNr">Номера сцепки</label><br>
            <input type="text" name="plateNr" class="char" id="plateNr">
        </div>

        <div class="input">
            <label for="invoiceSendDate">Счет отправлен</label><br>
            <input type="text" name="invoiceSendDate" readonly="readonly" class="date" id="invoiceSendDate">
        </div>
        <br>

        <div class="input" id="notesDiv">
            <label for="notes">Примечания</label><br>
            <%--<input type="text" name="notes" id="notes">--%>
            <textarea name="notes" id="notes" class="text"></textarea>
        </div>
        <a href="" id="saveAgreement">Сохранить</a>
    </form>
</div>
<br>

<div id="result">
</div>
</body>
</html>
