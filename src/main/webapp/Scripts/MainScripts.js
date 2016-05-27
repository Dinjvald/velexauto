/**
 * Created by Dinjvald on 04/12/15.
 */

/* --- MENU START --- */

function menuHover() {
    $("ul#menu li#orders").hover(
        function () {
            $("ul#menu li ul#orders-submenu").slideDown(150);
        },
        function () {
            $("ul#menu li ul#orders-submenu").slideUp(150);
        });
}

/* --- MENU END --- */

/* --- LOCALE START --- */

function localeInit() {
    var fullURL = $(location).attr("href").split("?")[0];
    var ruURL = fullURL + "?mylocale=ru";
    var enURL = fullURL + "?mylocale=en";
    var lvURL = fullURL + "?mylocale=lv";

    $("#locale-ru").attr("href", ruURL);
    $("#locale-en").attr("href", enURL);
    $("#locale-lv").attr("href", lvURL);
}

/* --- LOCALE END --- */

/* --- DATEPICKER START --- */

function datepickerInit() {

    datepickerConfig();
    datepickerSetRegionalRU();
    bindDatepicker();
}

function datepickerConfig() {
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
}

function datepickerSetRegionalRU() {
    $.datepicker.setDefaults(
        $.datepicker.regional["ru"]
    );
}

function bindDatepicker() {
    $("#loading-date").datepicker();
    $("#unloading-date").datepicker();
    $("#invoice-send-date").datepicker();
}

/* --- DATEPICKER END --- */

/* --- AGREEMENT FORM AJAX REQUEST PROCESSING START --- */

function initAgreementFormAJAX(url) {
    $("#save-agreement").click(function () {
        postAgreementAJAX(url);
        return false;
    });
}

function postAgreementAJAX(url) {
    if (!isDataValid()) return false;
    var dataToSend = getAgreementFormData();

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: url,
        data: JSON.stringify(dataToSend),
        dataType: "text",
        success: function (response) {
            if (response == "success") {
                $("#agreement-form")[0].reset();
                alertMessage("Заяка сохранена успешно");
            }
            if (response == "error") {
                alertMessage("Отказ от сервера. Неверные данные в заявке");
            }
            /*showResult(response);
            $("#agreement-form")[0].reset();*/
        },
        error: function () {
            alertMessage("Сервер не может обработать данные");
            /*$("#result")
                .css({"color": "#C40005"})
                .html("Сервер не может обработать данные.");*/
        }
    })
}

function isDataValid() {
    var isValid = true;
    var msg = "Проверьте данные.";
    if (!isNumbersValid()) isValid = false;
    if (!isCharValid()) isValid = false;
    if (isUnloadingDateEmpty()) {
        msg += "<br>Дата разгрузки не может быть пустой. Укажите примерную дату, если не знаете точно.";
        isValid = false;
    }
    if (!canCalculatePaymentTerm()){
        msg += "<br>Укажите срок оплаты и дату отправки счета или оставьте эти поля пустыми.";
        isValid = false;
    }
    /*if (!isValid) $("#result")
        .css({"color": "#dc7700"})
        .html(msg);*/
    if (!isValid) alertMessage(msg);
    return isValid;
}

function isUnloadingDateEmpty() {
    var unloadingDate = $("#unloading-date");
    if (unloadingDate.val() == ""){
        unloadingDate.css({"border-color": "#920007"});
        return true;
    }
    return false;
}

function canCalculatePaymentTerm() {
    var invoiceSendDate = $("#invoice-send-date");
    var paymentTerm = $("#payment-term");
    if (invoiceSendDate.val() == "" && paymentTerm.val() != "") {
        invoiceSendDate.css({"border-color": "#920007"});
        paymentTerm.css({"border-color": "#920007"});
        return false;
    } else {
        return true;
    }
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
        if ($(this).val() < -1) {
            $(this).css({"border-color": "#920007"});
            isNumbersValid = false;
        }
    });
    return isNumbersValid;
}

function setDefaultBorderColor(object) {
    $(object).css({"border-color": "#666666"});
    $("#invoice-send-date").css({"border-color": "#666666"});
    $("#unloading-date").css({"border-color": "#666666"});
}

function isInputEmpty(object) {
    return object.val() == "";
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
    $(".agreement-form-date").each(function () {
        if (isInputEmpty($(this))) $(this).val("01.01.1971")
    });
    $(".text").each(function () {
        if (isInputEmpty($(this))) $(this).val("empty");
    });
}

function getAgreementFormData() {
    fillDefaultIfEmpty();
    var data = {};
    var agreementForm = $("#agreement-form");

    agreementForm.find("input:not([type=radio])").each(function () {
        var name = $(this).attr("name");
        data[name] = $(this).val();
    });
    agreementForm.find("textarea").each(function () {
        var name = $(this).attr("name");
        data[name] = $(this).val();
    });
    var radioCheckedElement = agreementForm.find("input[type=radio]:checked");
    var radioName = radioCheckedElement.attr("name");
    data[radioName] = radioCheckedElement.val();
    return data;
}

function showResult(response) {
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

/* --- AGREEMENT FORM AJAX REQUEST PROCESSING END --- */

/* --- ALERT POPUP BOX START --- */

function alertPopupBoxClickEvent() {
    $("#alert-button-close").click(function () {
        toggleVisibility("alert-wrapper");
    });
}

function alertMessage(msg) {
    toggleVisibility("alert-wrapper");
    $("#alert-text").html(msg);
}

/*function alertResponseFromServer(key) {
    var agreement = {
        "can't delete" : "Вы не являетесь менеджером перевозки. Удаление невозможно",
        "error" : "Ошибка со стороны сервера. Удалить не удалось"
    };
    if (key[1] == "agreement") {
        var msg = agreement[key[2]];
        alertMessage(msg);
    }
}*/

/* --- ALERT POPUP BOX END --- */

function toggleVisibility(id) {
    var e = document.getElementById(id);
    if (e.style.display == "block") {
        e.style.display = "none";
    } else {
        e.style.display = "block";
    }
}