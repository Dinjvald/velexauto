<%@tag pageEncoding="UTF-8" %>
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
            <div id="close_form">&#x2715;</div>
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
            <textarea name="notes" id="notes" class="text"></textarea>
        </div>
        <a href="" id="saveAgreement">Сохранить</a>
    </form>
    <div id="result">

    </div>
</div>