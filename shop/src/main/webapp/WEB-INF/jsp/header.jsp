<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="rootUrl" value="/"/>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-head">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <div class="icon-bar"></div>
                <div class="icon-bar"></div>
                <div class="icon-bar"></div>
            </button>
            <div class="navbar-brand">
                <spring:message code="header.text"/> - <spring:message code="header.administration"/>
            </div>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li>Home</li>
                <li>About</li>
                <li>Contact</li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <security:authorize url="/admin/**">
                        <div>
                            <form:form action="${rootUrl}logout" method="POST">
                                <div>
                                    <spring:message code="header.hello"/>,
                                    <security:authentication property="principal.username"/>
                                </div>
                                <div>
                                    <input type="submit" value="<spring:message code="header.logout"/>"/>
                                </div>
                            </form:form>
                        </div>
                    </security:authorize>
                </li>
                <li>
                    <a href="?lang=en_US">EN</a>&nbsp;
                </li>
                <li>
                    <a href="?lang=ru_RU">RU</a>
                </li>
            </ul>
        </div>
    </div>
</div>
