<%@tag pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div id="choose-locale">
    <a id="locale-ru" href="">RUS</a>
    <a id="locale-en" href="">ENG</a>
    <a id="locale-lv" href="">LAT</a>
</div>
<ul id="menu">
    <li id="first-li"><a href="index"><spring:message code="locale.home"/></a></li>
    <li><a href="about-us"><spring:message code="locale.about-us"/></a></li>
    <li><a href="contacts"><spring:message code="locale.contacts"/></a></li>
    <li><a href="protected/home"> Login </a></li>
</ul>