<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="rootUrl" value="/"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">WebSiteName</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Page 1</a></li>
                    <li><a href="#">Page 1</a></li>
                    <li><a href="#">Page 1</a></li>
                </ul>
            </li>
            <li><a href="#">About Us</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <%--
            <li><a href="#"><span class="glyphicon glyphicon-user"></span>Sign Up</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
            --%>
            <security:authorize url="/admin/**">
                <div>
                    <form:form action="${rootUrl}logout" method="POST">
                        <div>
                            <spring:message code="header.hello"/>,
                            <security:authentication property="principal.username"/>
                        </div>
                        <div>
                            <input type="submit" class="btn btn-danger" value="<spring:message code="header.logout"/>"/>
                        </div>
                    </form:form>
                </div>
            </security:authorize>
        </ul>
    </div>
</nav>
<%--
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-head">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-navbar-collapse-0">
                <div class="icon-bar"></div>
                <div class="icon-bar"></div>
                <div class="icon-bar"></div>
            </button>
            <div class="navbar-brand">
                <spring:message code="header.text"/> - <spring:message code="header.administration"/>
            </div>
        </div>
        <div class="navbar-collapse collapse" id="bs-navbar-collapse-0">
            <ul class="nav navbar-nav">
                <li><a href="#">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>
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
</nav>
--%>