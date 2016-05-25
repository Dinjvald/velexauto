<%@tag pageEncoding="UTF-8" %>
<div id="agreement-date-form-wrapper" class="div-display-inline-block">
    <form id="agreement-date-form" method="post" action="agreement-list-result">
        <div id="agreement-date-form-head">
            Дата разгрузки
        </div>
        <div class="agreement-date-form-child-div">
            <label for="agreement-date-form-start-date">Начало</label>
            <br>
            <input type="text" name="startDate" class="date" id="agreement-date-form-start-date" readonly="readonly">
        </div>
        <div class="agreement-date-form-child-div">
            <label for="agreement-date-form-end-date">Конец</label>
            <br>
            <input type="text" name="endDate" class="date" id="agreement-date-form-end-date" readonly="readonly">
        </div>
        <br>
        <input class="submit-button" type="submit" value="Отправить">
    </form>
</div>
<div class="div-display-inline-block" id="agreement-list-links-wrapper-first">
    <ul class="agreement-list-links">
        <li><a href="this-month-agreements">За этот месяц</a></li>
        <li><a href="this-quarter-agreements">За этот квартал</a></li>
        <li><a href="this-half-year-agreements">За это полугодие</a></li>
        <li><a href="this-year-agreements">За этот год</a></li>
        <li><a href="this-and-last-years-agreements">За этот и прошлый года</a></li>
    </ul>
</div>
<div class="div-display-inline-block" id="agreement-list-links-wrapper-second">
    <ul class="agreement-list-links">
        <li><a href="unpaid-agreements">Оплата не поступила</a></li>
        <li><a href="late-payment-agreements">Оплата просрочена</a></li>
        <li><a href="planned-agreements">Запланированные перевозки</a></li>
    </ul>
</div>
