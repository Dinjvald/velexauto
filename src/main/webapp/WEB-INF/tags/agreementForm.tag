<%@tag pageEncoding="UTF-8" %>
<div id="agreement">
    <form id="agreement-form" action="">
        <div class="agreement-table-row-index"></div>
        <div class="input" id="agreement-id-div">
            <label for="agreement-id">ID заявки</label>
            <input type="text" name="agreementId" class="number" id="agreement-id">
        </div>
        <div class="input">
            <label for="loading-date">Дата загрузки</label><br>
            <input type="text" name="loadingDate" readonly="readonly" class="date" id="loading-date">
            <br>
            <label for="loading-address">Адрес загрузки</label><br>
            <input type="text" name="loadingAddress" class="char" id="loading-address">
        </div>

        <div class="input">
            <div id="close-form">&#x2715;</div> <%--CLOSE HERE--%>
            <label for="unloading-date">Дата разгрузки</label><br>
            <input type="text" name="unloadingDate" readonly="readonly" class="date" id="unloading-date">
            <br>
            <label for="unloading-address">Адрес разгрузки</label><br>
            <input type="text" name="unloadingAddress" class="char" id="unloading-address">
        </div>
        <br>

        <div class="input">
            <label for="client-name">Заказчик</label><br>
            <input type="text" name="clientName" class="char" id="client-name">
        </div>
        <div class="input">
            <label for="agreement-nr">Номер заявки</label><br>
            <input type="text" name="agreementNr" class="char" id="agreement-nr">
        </div>
        <div class="input">
            <label for="invoice-nr">Номер счета</label><br>
            <input type="text" name="invoiceNr" class="char" id="invoice-nr">
        </div>
        <br>

        <div class="input">
            <label for="on-behalf-of">На основании документа</label><br>
            <input type="text" name="onBehalfOf" class="char" id="on-behalf-of">
        </div>

        <div class="input">
            <label for="file-link-agreement">Ссылка на заявку</label><br>
            <input type="text" name="fileLinkAgreement" class="text" id="file-link-agreement">
        </div>

        <div class="input">
            <label for="file-link-invoice">Ссылка на счет</label><br>
            <input type="text" name="fileLinkInvoice" class="text" id="file-link-invoice">
        </div>
        <br>

        <div class="input">
            <label for="price">Цена EUR</label><br>
            <input type="text" name="price" class="number" id="price">
        </div>

        <div class="input">
            <label for="value-added-tax">PVN EUR</label><br>
            <input type="text" name="valueAddedTax" class="number" id="value-added-tax">
        </div>

        <div class="input">
            <label for="payment-term">Срок оплаты в днях</label><br>
            <input type="text" name="paymentTerm" class="number" id="payment-term">
        </div>
        <br>

        <div class="input">
            <label for="driver">Водитель</label><br>
            <input type="text" name="driver" class="char" id="driver">
        </div>

        <div class="input">
            <label for="plate-nr">Номера сцепки</label><br>
            <input type="text" name="plateNr" class="char" id="plate-nr">
        </div>

        <div class="input">
            <label for="invoice-send-date">Счет отправлен</label><br>
            <input type="text" name="invoiceSendDate" readonly="readonly" class="date" id="invoice-send-date">
        </div>
        <br>

        <div class="input" id="notesDiv">
            <label for="notes">Примечания</label><br>
            <textarea name="notes" id="notes" class="text"></textarea>
        </div>
        <div class="input agreement-radio">
            <input type="radio" name="paid" value="false" checked><span>Перевозка не оплачена</span><br>
            <input type="radio" name="paid" value="true"><span>Перевозка оплачена</span>
        </div>
        <a href="" id="save-agreement">Сохранить</a>
    </form>
    <%--<div id="result">

    </div>--%>
</div>