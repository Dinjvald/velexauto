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
        #divAgreementTable {
            margin-top: 150px;
        }

        #agreementTable thead {
            background-color: #252525;
        }

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

        #agreementTable_length, #agreementTable_filter, #agreementTable_info, #agreementTable_paginate,
        .dtr-modal-content h2, .dtr-modal-content td {
            color: azure;
        }

        #agreementTable_length, #agreementTable_filter {
            margin-bottom: 10px;
        }

        #agreementTable_paginate .previous, #agreementTable_paginate .next {
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

        #agreementTable tbody .col-4 {
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

        /* AGREEMENT FORM */

        div.agreement_form_position {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            background-color: rgba(0, 0, 0, .7);
            width: 100%;
            height: 100%;
        }

        div.agreement_form_position > div#agreement {
            position: absolute;
            width: 845px;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            background-color: #151515;
            padding: 7px;
            border-radius: 8px;
        }

        #close_form {
            display: block;
            color: #dc7700;
            cursor: pointer;
            float: right;
            font-size: 150%;
        }

    </style>
    <script>
        $(document).ready(function () {
            menuHover();

            $.fn.dataTable.moment("D.MM.YYYY");
            $("#agreementTable").DataTable({
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
                "autoWidth": false,
                "pageLength": 50,
                "columnDefs": [
                    {
                        "targets": [3, 5],
                        "visible": false
                    }
                ],
                responsive: {
                    details: {
                        display: $.fn.dataTable.Responsive.display.modal({
                            header: function (row) {
                                var data = row.data();
                                return 'Данные перевозки:' + '<br>' + ' ' + data[6] + ' - ' + data[8];
                            }
                        }),
                        renderer: function (api, rowIdx, columns) {
                            var data = $.map(columns, function (col, i) {
                                if (i < 3 || i > 19) return false;
                                if (i == 4 && columns[21].data != "") {
                                    return '<tr>' +
                                            '<td class="dtr-modal-title">' + col.title + ':' + '</td>' +
                                            '<td>' + '<a class="dtr-modal-link" target="_blank" href="' + columns[21].data + '">' + col.data + '</a>' +
                                            '</tr>';
                                }
                                if (i == 5 && columns[22].data != "") {
                                    return '<tr>' +
                                            '<td class="dtr-modal-title">' + col.title + ':' + '</td>' +
                                            '<td>' + '<a class="dtr-modal-link" target="_blank" href="' + columns[22].data + '">' + col.data + '</a>' +
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

            $(".delete_agreement").click(function () {
                var tr = $(this).parents("tr");
                var id = tr.find(".agreementID").html();
                $.ajax({
                    type: "POST",
                    url: "deleteAgreement",
                    data: "agreementID=" + id,
                    dataType: "text",
                    success: function (response) {
                        if (response == "success") {
                            tr.remove();
                        }
                        if (response == "error") {
                            alert("Error on server side")
                        }
                        if (response == "can't delete") {
                            alert("Вы не можете удалить эту запись.")
                        }
                    }
                });
            });

            datepickerInit();
            initAgreementFormAJAX();

            /*
             * Keep in mind that some columns (3rd and 5th in this case) can be made invisible in DataTables
             * configuration:
             *
             *   "columnDefs": [
             *       {
             *           "targets": [3, 5],
             *           "visible": false
             *       }
             *   ]
             * So the Jquery .each() function will not see the hidden columns. If needed you must make them visible
             * before the function and invisible after, so that the main table remains the same.
             */
            $(".edit_agreement").click(function () {
                toggle_visibility("agreement_form_wrapper");
                var table = $("#agreementTable").DataTable();
                table.column(5).visible(true);

                var inputNames = getAgreementFormInputNamesArray();

                /*var agreementForm = $("#agreementForm");
                 var inputNames = [];
                 agreementForm.find("input").each(function () {
                 inputNames[inputNames.length] = $(this).attr("name");
                 });
                 agreementForm.find("textarea").each(function () {
                 inputNames[inputNames.length] = $(this).attr("name");
                 });*/

                var tr = $(this).parents("tr");

                /*tr.find("td").each(function () {
                    var name = $(this).attr("class");

                    var classNames = name.split(" ");

                    var value = $.trim($(this).html());

                    for (var x = 0; x < inputNames.length; x++) {
                        for (var y = 0; y < classNames.length; y++) {
                            if (inputNames[x] == classNames[y]) {
                                var input = "#" + inputNames[x];
                                $("#agreementForm").find(input).val(value);
                            }
                        }
                    }
                });*/

                fillAgreementFormWithCurrentRowData(tr, inputNames);

                table.column(5).visible(false);
            });
            $("#close_form").click(function () {
                toggle_visibility("agreement_form_wrapper");
            });
            $("#agreement_form_wrapper").click(function (e) {
                if (e.target == this) {
                    toggle_visibility("agreement_form_wrapper");
                }
            });

            function toggle_visibility(id) {
                var e = document.getElementById(id);
                if (e.style.display == "block") {
                    e.style.display = "none";
                } else {
                    e.style.display = "block";

                }
            }

            function getAgreementFormInputNamesArray() {
                var agreementForm = $("#agreementForm");
                var inputNames = [];
                agreementForm.find("input").each(function () {
                    inputNames[inputNames.length] = $(this).attr("name");
                });
                agreementForm.find("textarea").each(function () {
                    inputNames[inputNames.length] = $(this).attr("name");
                });
                return inputNames;
            }

            function fillAgreementFormWithCurrentRowData(tr, inputNames) {
                tr.find("td").each(function () {
                    var name = $(this).attr("class");

                    var classNames = name.split(" ");

                    var value = $.trim($(this).html());

                    for (var x = 0; x < inputNames.length; x++) {
                        for (var y = 0; y < classNames.length; y++) {
                            if (inputNames[x] == classNames[y]) {
                                var input = "#" + inputNames[x];
                                $("#agreementForm").find(input).val(value);
                            }
                        }
                    }
                });
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
<div id="divAgreementTable">
    <table border="1" class="compact" id="agreementTable">
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
        </tr>
        <tr>
            <th>Адрес</th>
            <th>Дата</th>
            <th>Адрес</th>
            <th>Дата</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${agreement}" var="agr">
            <tr>
                <td class="col-4"></td>
                <td class="col-4"><img class="edit_agreement" src="../Images/edit.png" align="center"></td>
                <td class="col-4"><img class="delete_agreement" src="../Images/DeleteRed.png" align="center"></td>
                <td class="name_surname">
                    <c:if test="${agr.employee.name != defText}">
                        ${agr.employee.name}
                    </c:if>
                    <c:if test="${agr.employee.surname != defText}">
                        ${agr.employee.surname}
                    </c:if>
                </td>
                <td class="col-1 invoiceNr">
                    <c:if test="${agr.invoiceNr != defText}">
                        ${agr.invoiceNr}
                    </c:if>
                </td>
                <td class="agreementNr">
                    <c:if test="${agr.agreementNr != defText}">
                        ${agr.agreementNr}
                    </c:if>
                </td>
                <td class="col-2 clientName">
                    <c:if test="${agr.clientName != defText}">
                        ${agr.clientName}
                    </c:if>
                </td>
                <td class="col-3 loadingAddress">
                    <c:if test="${agr.loadingAddress != defText}">
                        ${agr.loadingAddress}
                    </c:if>
                </td>
                <td class="col-1 loadingDate">
                    <fmt:formatDate var="loadingDate" value="${agr.loadingDate}" pattern="${dateFormat}"/>
                    <c:if test="${loadingDate != defDate}">
                        ${loadingDate}
                    </c:if>
                </td>
                <td class="col-3 unloadingAddress">
                    <c:if test="${agr.unloadingAddress != defText}">
                        ${agr.unloadingAddress}
                    </c:if>
                </td>
                <td class="col-1 unloadingDate">
                    <fmt:formatDate var="unloadingDate" value="${agr.unloadingDate}" pattern="${dateFormat}"/>
                    <c:if test="${unloadingDate != defDate}">
                        ${unloadingDate}
                    </c:if>
                </td>
                <td class="col-1 price">
                    <c:set var="price" value="${agr.price}"/>
                    <c:if test="${price != defInt}">
                        <fmt:formatNumber var="pat" value="${price}" minFractionDigits="2" maxFractionDigits="2"
                                          type="number"/>
                        ${fn:replace(pat, ",", " ")}
                    </c:if>
                </td>
                <td class="col-1 estimatedDateOfPayment">
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
                <td class="plateNr">
                    <c:if test="${agr.plateNr != defText}">
                        ${agr.plateNr}
                    </c:if>
                </td>
                <td class="valueAddedTax">
                    <c:set var="valueAddedTax" value="${agr.valueAddedTax}"/>
                    <c:if test="${agr.valueAddedTax != defInt}">
                        <fmt:formatNumber var="pat" value="${valueAddedTax}" minFractionDigits="2" maxFractionDigits="2"
                                          type="number"/>
                        ${fn:replace(pat, ",", " ")}
                    </c:if>
                </td>
                <td class="paymentTerm">
                    <c:if test="${agr.paymentTerm != defInt}">
                        ${agr.paymentTerm}
                    </c:if>
                </td>
                <td class="invoiceSendDate">
                    <fmt:formatDate var="invoiceSendDate" value="${agr.invoiceSendDate}" pattern="${dateFormat}"/>
                    <c:if test="${invoiceSendDate != defDate}">
                        ${invoiceSendDate}
                    </c:if>
                </td>
                <td class="onBehalfOf">
                    <c:if test="${agr.onBehalfOf != defText}">
                        ${agr.onBehalfOf}
                    </c:if>
                </td>
                <td class="notes">
                    <c:if test="${agr.notes != defText}">
                        ${agr.notes}
                    </c:if>
                </td>
                <td class="agreementID">${agr.agreementId}</td>
                <td class="fileLinkInvoice">
                    <c:if test="${agr.fileLinkInvoice != defText}">
                        ${agr.fileLinkInvoice}
                    </c:if>
                </td>
                <td class="fileLinkAgreement">
                    <c:if test="${agr.fileLinkAgreement != defText}">
                        ${agr.fileLinkAgreement}
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>

<div id="agreement_form_wrapper" class="agreement_form_position">
    <mytag:agreementForm/>
</div>
</body>
</html>
