<%--
  Created by IntelliJ IDEA.
  User: Dinjvald
  Date: 17/12/15
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <mytag:head/>
    <%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Plugins/DataTables/datatables.min.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/Plugins/DataTables/datatables.min.js"></script>--%>
    <%--<link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/s/dt/dt-1.10.10,r-2.0.0/datatables.min.css"/>
    <script type="text/javascript" src="https://cdn.datatables.net/s/dt/dt-1.10.10,r-2.0.0/datatables.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js"></script>
    <script type="text/javascript"
            src="https://cdn.datatables.net/plug-ins/1.10.10/sorting/datetime-moment.js"></script>--%>
    <c:set var="dateFormat" value="dd.MM.yyyy" scope="page"/>
    <style>
        #div-agreement-table {
            position: relative;
            top: 30px;
        }

        #agreement-table thead {
            background-color: #252525;
        }

        /*#agreement-table > tbody > tr {
            background-color: #c3c3c3;
        }*/

        td {
            padding: 5px;
            color: #151515;
        }

        .col-1 {
            padding: 0 3px;
            width: 10%;
            min-width: 70px;
            max-width: 80px;
            text-align: center;
            word-wrap: break-word;
        }

        .col-2 {
            min-width: 185px;
        }

        .col-3 {
            width: 80%;
            min-width: 130px;
        }

        #agreement-table_length, #agreement-table_filter, #agreement-table_info, #agreement-table_paginate,
        .dtr-modal-content h2, .dtr-modal-content td {
            color: azure;
        }

        #agreement-table_length, #agreement-table_filter {
            margin-bottom: 10px;
        }

        #agreement-table_paginate .previous, #agreement-table_paginate .next {
            color: azure !important;
        }

        .dtr-modal-display {
            width: 517px !important;
            background-color: #252525 !important;
        }

        .dtr-modal-close {
            background-color: #252525 !important;
            color: #dc7700;
            border: solid 1px #252525 !important;
            width: 30px !important;
            height: 30px !important;
            font-size: 200%;
            line-height: 30px;
        }

        table.dataTable.dtr-inline.collapsed > tbody > tr > td:first-child:before,
        table.dataTable.dtr-inline.collapsed > tbody > tr > th:first-child:before {
            position: static;
        }

        #agreement-table tbody .col-4 {
            padding: 0 3px;
            cursor: pointer;
        }

        .dtr-modal > .dtr-modal-display > .dtr-modal-content > table > tbody > tr > .dtr-modal-title {
            width: 195px;
        }

        .dtr-modal > .dtr-modal-display > .dtr-modal-content > table > tbody > tr > td > .dtr-modal-link:link,
        .dtr-modal > .dtr-modal-display > .dtr-modal-content > table > tbody > tr > td > .dtr-modal-link:visited {
            color: azure;
        }

        .dtr-modal > .dtr-modal-display > .dtr-modal-content > table > tbody > tr > td > .dtr-modal-link:hover,
        .dtr-modal > .dtr-modal-display > .dtr-modal-content > table > tbody > tr > td > .dtr-modal-link:active {
            color: #dc7700;
        }

        #delete-check-agreement-id, #agreement-row-index {
            display: none;
        }

        #agreement-table_filter > label > input {
            width: 250px;
            padding-left: 0;
        }

        /* AGREEMENT FORM */

        div.agreement-form-position {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            background-color: rgba(0, 0, 0, .7);
            width: 100%;
            height: 100%;
        }

        div.agreement-form-position > div#agreement {
            position: absolute;
            width: 850px;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            background-color: #151515;
            padding: 7px;
            border-radius: 8px;
        }

        #close-form {
            display: block;
            color: #dc7700;
            cursor: pointer;
            float: right;
            font-size: 150%;
        }

        #agreement-table-summary > table > tbody > tr > td {
            color: azure;
        }

        #agreement-table-summary > table > tbody > tr:first-child > td:first-child,
        #agreement-table-summary > table > tbody > tr:last-child > td:first-child {
            text-align: right;
        }

        #agreement-table-summary {
            position: relative;
            left: 40px;
            bottom: 59px;
        }

    </style>
    <script>
        $(document).ready(function () {
            menuHover();
            datepickerConfig();
            datepickerSetRegionalRU();
            $(".date").datepicker();

            jQuery.extend(jQuery.fn.dataTable.moment("D.MM.YYYY"));
            jQuery.extend(jQuery.fn.dataTableExt.oSort, {
                "invoice-pre": function (a) {
                    var year = a.slice(2, 4),
                            month = a.slice(0, 2),
                            invoiceNr = a.slice(4);
                    return year.concat(month, invoiceNr);
                },
                "invoice-asc": function (a, b) {
                    var date1 = parseInt(a.slice(0, 4)),
                            date2 = parseInt(b.slice(0, 4)),
                            number1 = parseInt(a.slice(4)),
                            number2 = parseInt(b.slice(4));

                    return ((date1 < date2) ? -1 : (date1 > date2) ? 1 : (number1 < number2) ? -1 : (number1 > number2) ? 1 : 0);
                },
                "invoice-desc": function (a, b) {
                    var date1 = parseInt(a.slice(0, 4)),
                            date2 = parseInt(b.slice(0, 4)),
                            number1 = parseInt(a.slice(4)),
                            number2 = parseInt(b.slice(4));

                    return ((date1 < date2) ? 1 : (date1 > date2) ? -1 : (number1 < number2) ? 1 : (number1 > number2) ? -1 : 0);
                }
            });
            $("#agreement-table").DataTable({
                language: {
                    "processing": "Подождите...",
                    "search": "Поиск:",
                    "lengthMenu": "Показать _MENU_ записей",
                    "info": "Записи с _START_ до _END_ из _TOTAL_ записей",
                    "infoEmpty": "Записи с 0 до 0 из 0 записей",
                    "infoFiltered": "(отфильтровано из _MAX_ записей)",
                    "infoPostFix": "",
                    "loadingRecords": "Загрузка записей...",
                    "zeroRecords": "Записи отсутствуют.",
                    "emptyTable": "В таблице отсутствуют данные",
                    "paginate": {
                        "first": "Первая",
                        "previous": "Предыдущая",
                        "next": "Следующая",
                        "last": "Последняя"
                    }
                },
                "columns": [
                    {"name": "details"},
                    {"name": "edit"},
                    {"name": "delete"},
                    {"name": "nameSurname"},
                    {"name": "invoiceNr"},
                    {"name": "agreementNr"},
                    {"name": "clientName"},
                    {"name": "loadingAddress"},
                    {"name": "loadingDate"},
                    {"name": "unloadingAddress"},
                    {"name": "unloadingDate"},
                    {"name": "price"},
                    {"name": "estimatedDateOfPayment"},
                    {"name": "driver"},
                    {"name": "plateNr"},
                    {"name": "valueAddedTax"},
                    {"name": "paymentTerm"},
                    {"name": "invoiceSendDate"},
                    {"name": "onBehalfOf"},
                    {"name": "notes"},
                    {"name": "agreementId"},
                    {"name": "fileLinkInvoice"},
                    {"name": "fileLinkAgreement"},
                    {"name": "paid"}
                ],
                "drawCallback": function (settings) {
                    editAgreementClickEvent();
                    checkForDebtor();
                    countTotalAgreements();
                },
                "searchDelay": 500,
                "autoWidth": false,
                "paging": false,
                "columnDefs": [
                    {
                        "targets": [3, 5],
                        "visible": false
                    },
                    {
                        "type": "invoice",
                        "targets": 4
                    }
                ],
                responsive: {
                    details: {
                        display: $.fn.dataTable.Responsive.display.modal({
                            header: function (row) {
                                var clientNameColumn = 6;
                                var loadingDateColumn = 8;
                                var data = row.data();
                                return 'Данные перевозки:' + '<br>' + ' ' + data[clientNameColumn] + ' - ' + data[loadingDateColumn];
                            }
                        }),
                        renderer: function (api, rowIdx, columns) {
                            var minRenderColumn = 3;
                            var maxRenderColumn = 19;
                            var invoiceNrColumn = 4;
                            var agreementNrColumn = 5;
                            var fileLinkInvoiceColumn = 21;
                            var fileLinkAgreementColumn = 22;

                            var data = $.map(columns, function (col, i) {
                                if (i < minRenderColumn || i > maxRenderColumn) return false;
                                if (i == invoiceNrColumn && columns[fileLinkInvoiceColumn].data != "") {
                                    return '<tr>' +
                                            '<td class="dtr-modal-title">' + col.title + ':' + '</td>' +
                                            '<td>' + '<a class="dtr-modal-link" target="_blank" href="' + columns[fileLinkInvoiceColumn].data + '">' + col.data + '</a>' +
                                            '</tr>';
                                }
                                if (i == agreementNrColumn && columns[fileLinkAgreementColumn].data != "") {
                                    return '<tr>' +
                                            '<td class="dtr-modal-title">' + col.title + ':' + '</td>' +
                                            '<td>' + '<a class="dtr-modal-link" target="_blank" href="' + columns[fileLinkAgreementColumn].data + '">' + col.data + '</a>' +
                                            '</tr>';
                                }
                                return '<tr>' +
                                        '<td class="dtr-modal-title">' + col.title + ':' + '</td>' +
                                        '<td>' + col.data + '</td>' +
                                        '</tr>';
                            }).join('');
                            return $('<table width="500px"/>').append(data);
                        }

                    }
                }
            });

            function checkForDebtor() {
                $("#agreement-table").find(".is-paid").each(function () {
                    var isPaid = $.trim($(this).html());
                    var currentDate = getCurrentMomentDate("day");
                    var paymentDateString = $.trim($(this).parents("tr").find(".estimated-date-of-payment").html());
                    var paymentDate = stringToMomentDate(paymentDateString);

                    if (isPaid == "true") {
                        $(this).parents("tr").css({"background-color": "#c3c3c3"});
                    }
                    if (isPaid == "false" && moment(currentDate).isBefore(paymentDate) || moment(currentDate).isSame(paymentDate)) {
                        $(this).parents("tr").css({"background-color": "##b6d7a8"});
                    }
                    if (isPaid == "false" && moment(currentDate).isAfter(paymentDate)) {
                        $(this).parents("tr").css({"background-color": "#f9cb9c"});
                    }
                });
            }

            checkForDebtor();

            $(".delete-agreement").click(function () {
                toggleVisibility("delete-check-wrapper");
                var table = $("#agreement-table").DataTable();
                var tr = $(this).parents("tr");
                var rowIndex = table.row(tr).index();
                var id = tr.find(".agreement-id").html();
                var client = tr.find(".client-name").html() + "<br>";
                var route = tr.find(".loading-address").html();
                route += " - " + tr.find(".unloading-address").html();
                $("#delete-check-body").html(client + route);
                $("#delete-check-agreement-id").html(id);
                $("#agreement-row-index").html(rowIndex);
            });

            editAgreementClickEvent();

            $("#close-form").click(function () {
                toggleVisibility("agreement-form-wrapper");
            });

            $("#agreement-form-wrapper").click(function (e) {
                if (e.target == this) {
                    toggleVisibility("agreement-form-wrapper");
                }
            });

            $("#delete-check-no").click(function () {
                toggleVisibility("delete-check-wrapper");
            });

            $("#delete-check-yes").click(function () {
                var id = $("#delete-check-agreement-id").html();
                $.ajax({
                    type: "POST",
                    url: "delete-agreement",
                    data: "agreementId=" + id,
                    dataType: "text",
                    success: function (response) {
                        if (response == "success") {
                            deleteRow("agreement-table", "agreement-row-index");
                        }
                        if (response == "error") {
                            alertMessage("Ошибка со стороны сервера. Удалить не удалось.");
                        }
                        if (response == "can't delete") {
                            alertMessage("Вы не являетесь менеджером перевозки. Удаление невозможно.");
                        }
                    }
                });
                toggleVisibility("delete-check-wrapper");
            });

            function stringToMomentDate(string) {
                var dateFormat = "DD.MM.YYYY";
                return moment(string, dateFormat);
            }

            function getCurrentMomentDate(string) {
                if (string != null) {
                    return moment().startOf(string);
                }
                return moment();
            }

            function deleteRow(id, index) {
                var tableElement = $("#" + id);
                var rowIndex = parseInt($("#" + index).html());
                var table = tableElement.DataTable();
                var row = table.row(rowIndex);
                table.row(row).remove().draw();
            }

            datepickerInit();

            alertPopupBoxClickEvent();

            function editAgreementClickEvent() {
                var editAgreement = $(".edit-agreement");
                editAgreement.off();
                editAgreement.on("click", function () {
                    toggleVisibility("agreement-form-wrapper");
                    var table = $("#agreement-table").DataTable();
                    var tr = $(this).parents("tr");
                    var rowIndex = table.row(tr).index();
                    var rowData = table.row(rowIndex).data();

                    $(".agreement-table-row-index").html(rowIndex);
                    fillAgreementFormWithCurrentRowData(rowData);
                });
            }

            function fillAgreementFormWithCurrentRowData(rowData) {
                var table = $("#agreement-table").DataTable();
                var inputNames = getAgreementFormInputNames();

                for (var x = 0; x < inputNames.length; x++) {
                    if (inputNames[x] != "paid") {
                        var columnIndex = table.column(inputNames[x] + ":name").index();
                        var value = rowData[columnIndex];
                        var input = $("#agreement-form").find(":input[name=" + inputNames[x] + "]");
                        input.val(value);
                    }

                }
            }

            function getAgreementFormInputNames() {
                var agreementForm = $("#agreement-form");
                var inputNames = [];
                agreementForm.find("input").each(function () {
                    inputNames[inputNames.length] = $(this).attr("name");
                });
                agreementForm.find("textarea").each(function () {
                    inputNames[inputNames.length] = $(this).attr("name");
                });
                return inputNames;
            }

            $("#save-agreement").click(function () {
                updateAgreementAJAX();
                return false;
            });

            function updateAgreementAJAX() {
                if (!isDataValid()) return false;
                var dataToSend = getAgreementFormData();

                $.ajax({
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    url: "update-agreement",
                    data: JSON.stringify(dataToSend),
                    dataType: "text",
                    success: function (response) {
                        if (response == "can't update") {
                            toggleVisibility("agreement-form-wrapper");
                            alertMessage("Вы не являетесь менеджером перевозки, обновить невозможно")
                        } else {
                            if (response == "error") {
                                toggleVisibility("agreement-form-wrapper");
                                alertMessage("Сервер принял данные, но не смог их сохранить")
                            } else {
                                updateAgreementTableRow(dataToSend, response);
                                toggleVisibility("agreement-form-wrapper");
                                alertMessage("Заявка обновлена успешно");
                            }
                        }
                    },
                    error: function () {
                        alertMessage("Сервет не принимает данные")
                    }
                });
            }

            function updateAgreementTableRow(dataToSend, estimatedDateOfPayment) {
                var table = $("#agreement-table").DataTable();
                var defInt = -1;
                var defText = "empty";
                var defDate = "01.01.1971";
                var rowIndex = parseInt($(".agreement-table-row-index").html());
                var rowElement = table.row(rowIndex);
                var rowData = rowElement.data();

                $.each(dataToSend, function (key, value) {
                    var colIndex = table.column(key + ":name").index();
                    if (value == defInt || value == defDate || value == defText) {
                        rowData[colIndex] = "";
                    } else {
                        if($.isNumeric(value)) {
                            value = parseInt(value);
                            value = value.toFixed(2);
                        }
                        rowData[colIndex] = value;
                    }
                });
                var colIndex2 = table.column("estimatedDateOfPayment:name").index();
                rowData[colIndex2] = estimatedDateOfPayment;
                rowElement.data(rowData);
                table.draw();
            }

            function countTotalAgreements() {
                var table = $("#agreement-table").DataTable();
                var data = table.rows({"page":"current"}).data();
                var priceColumn = 11;
                var agreementCount = data.length;
                var sum = 0;
                for (var i = 0; i < data.length; i++) {
                    var price = data[i][priceColumn];
                    if ($.isNumeric(price)) {
                        sum += parseInt(price);
                    } else {
                        agreementCount--;
                    }
                }
                $("#agreement-count").html(agreementCount);
                $("#agreement-income").html(sum);
            }
        });
    </script>
</head>
<body>
<c:set var="defText" value="${defValues.defText}" scope="page"/>
<c:set var="defDate" value="${defValues.defDate}" scope="page"/>
<c:set var="defInt" value="${defValues.defInt}" scope="page"/>
<mytag:logo/>
<mytag:menuBarProtected/>
<mytag:AgreementListRequest/>
<div class="div-display-inline-block" id="agreement-table-summary">
    <table>
        <tbody>
        <tr>
            <td>
                Перевозок:
            </td>
            <td id="agreement-count"></td>
        </tr>
        <tr>
            <td>
                Доход:
            </td>
            <td id="agreement-income"></td>
        </tr>
        </tbody>
    </table>
</div>
<div id="div-agreement-table">
    <table border="1" class="compact" id="agreement-table">
        <thead>
        <tr>
            <th rowspan="2" colspan="3"></th>
            <th rowspan="2">Менеджер</th>
            <th rowspan="2">Номер счета</th>
            <th rowspan="2">Номер заявки</th>
            <th rowspan="2">Заказчик</th>
            <th colspan="2">Загрузка</th>
            <th colspan="2">Разгрузка</th>
            <th rowspan="2">Цена (EUR)</th>
            <th rowspan="2">Оплата</th>
            <th rowspan="2">Водитель</th>
            <th rowspan="2">Сцепка</th>
            <th rowspan="2">НДС (EUR)</th>
            <th rowspan="2">Срок оплаты (дн.)</th>
            <th rowspan="2">Счет отправлен</th>
            <th rowspan="2">На основании документа</th>
            <th rowspan="2">Примечания</th>
            <th rowspan="2">ID заявки</th>
            <th rowspan="2">Ссылка на счет</th>
            <th rowspan="2">Ссылка на заявку</th>
            <th rowspan="2">Оплачено ли?</th>
        </tr>
        <tr>
            <th>Адрес</th>
            <th>Дата</th>
            <th>Адрес</th>
            <th>Дата</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${agreement}" var="agr" varStatus="counter">
            <tr>
                <td class="col-4"></td>
                <td class="col-4"><img class="edit-agreement" src="../Images/edit.png" align="center"></td>
                <td class="col-4"><img class="delete-agreement" src="../Images/DeleteRed.png" align="center"></td>
                <td class="name-surname">
                    <c:if test="${agr.employee.name != defText}">
                        ${agr.employee.name}
                    </c:if>
                    <c:if test="${agr.employee.surname != defText}">
                        ${agr.employee.surname}
                    </c:if>
                </td>
                <td title="invoiceNr" class="col-1 invoice-nr">
                    <c:if test="${agr.invoiceNr != defText}">
                        ${agr.invoiceNr}
                    </c:if>
                </td>
                <td class="agreement-nr">
                    <c:if test="${agr.agreementNr != defText}">
                        ${agr.agreementNr}
                    </c:if>
                </td>
                <td class="col-2 client-name">
                    <c:if test="${agr.clientName != defText}">
                        ${agr.clientName}
                    </c:if>
                </td>
                <td class="col-3 loading-address">
                    <c:if test="${agr.loadingAddress != defText}">
                        ${agr.loadingAddress}
                    </c:if>
                </td>
                <td class="col-1 loading-date">
                    <fmt:formatDate var="loadingDate" value="${agr.loadingDate}" pattern="${dateFormat}"/>
                    <c:if test="${loadingDate != defDate}">
                        ${loadingDate}
                    </c:if>
                </td>
                <td class="col-3 unloading-address">
                    <c:if test="${agr.unloadingAddress != defText}">
                        ${agr.unloadingAddress}
                    </c:if>
                </td>
                <td class="col-1 unloading-date">
                    <fmt:formatDate var="unloadingDate" value="${agr.unloadingDate}" pattern="${dateFormat}"/>
                    <c:if test="${unloadingDate != defDate}">
                        ${unloadingDate}
                    </c:if>
                </td>
                <td class="col-1 price">
                    <c:set var="price" value="${agr.price}"/>
                    <c:if test="${price != defInt}">
                        <fmt:formatNumber var="pat" value="${price}" minFractionDigits="2" maxFractionDigits="2"
                                          type="number" groupingUsed="false"/>
                        ${fn:replace(pat, ",", ".")}
                    </c:if>
                </td>
                <td class="col-1 estimated-date-of-payment">
                    <fmt:formatDate var="estimatedDateOfPayment" value="${agr.estimatedDateOfPayment}"
                                    pattern="${dateFormat}"/>
                    <c:if test="${estimatedDateOfPayment != defDate}">
                        ${estimatedDateOfPayment}
                    </c:if>
                </td>
                <td class="driver">
                    <c:if test="${agr.driver != defText}">
                        ${agr.driver}
                    </c:if>
                </td>
                <td class="plate-nr">
                    <c:if test="${agr.plateNr != defText}">
                        ${agr.plateNr}
                    </c:if>
                </td>
                <td class="value-added-tax">
                    <c:set var="valueAddedTax" value="${agr.valueAddedTax}"/>
                    <c:if test="${agr.valueAddedTax != defInt}">
                        <fmt:formatNumber var="pat" value="${valueAddedTax}" minFractionDigits="2" maxFractionDigits="2"
                                          type="number"/>
                        ${fn:replace(pat, ",", ".")}
                    </c:if>
                </td>
                <td class="payment-term">
                    <c:if test="${agr.paymentTerm != defInt}">
                        ${agr.paymentTerm}
                    </c:if>
                </td>
                <td class="invoice-send-date">
                    <fmt:formatDate var="invoiceSendDate" value="${agr.invoiceSendDate}" pattern="${dateFormat}"/>
                    <c:if test="${invoiceSendDate != defDate}">
                        ${invoiceSendDate}
                    </c:if>
                </td>
                <td class="on-behalf-of">
                    <c:if test="${agr.onBehalfOf != defText}">
                        ${agr.onBehalfOf}
                    </c:if>
                </td>
                <td class="notes">
                    <c:if test="${agr.notes != defText}">
                        ${agr.notes}
                    </c:if>
                </td>
                <td class="agreement-id">${agr.agreementId}</td>
                <td class="file-link-invoice">
                    <c:if test="${agr.fileLinkInvoice != defText}">
                        ${agr.fileLinkInvoice}
                    </c:if>
                </td>
                <td class="file-link-agreement">
                    <c:if test="${agr.fileLinkAgreement != defText}">
                        ${agr.fileLinkAgreement}
                    </c:if>
                </td>
                <td class="is-paid">
                        ${agr.paid}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>

<div id="agreement-form-wrapper" class="agreement-form-position">
    <mytag:agreementForm/>
</div>
<div id="delete-check-wrapper" class="delete-check-position">
    <div id="delete-check-agreement-id"></div>
    <div id="agreement-row-index"></div>
    <div id="delete-check-box">
        <div id="delete_check_head">
            Хотите удалить перевозку?
        </div>
        <div id="delete-check-body"></div>
        <div id="delete-check-yes-no">
            <a id="delete-check-yes" href="" onclick="return false">Да</a>
            <a id="delete-check-no" href="" onclick="return false">Нет</a>
        </div>
    </div>
</div>
<mytag:allertPopupBox/>
</body>
</html>
