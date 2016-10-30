<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="rootUrl" value="/"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${rootUrl}items-list/">Tofi-shop</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">About Us</a></li>
            <security:authorize access="hasRole('ROLE_USER')">
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">My Actions<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/user/orders">Orders</a></li>
                        <li><a href="/user/profile">Profile</a></li>
                        <li><a href="/user/purchase">Purchase</a></li>
                    </ul>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_USER')">
                <li><a href="/user/cart">Cart</a></li>
            </security:authorize>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
                <div>
                    <form:form action="${rootUrl}logout" method="POST">
                        <div style="color: white;">
                            <spring:message code="header.hello"/>,
                            <security:authentication property="principal.username"/>
                        </div>
                        <div>
                            <input type="submit" class="btn btn-danger" value="<spring:message code="header.logout"/>"/>
                        </div>
                    </form:form>
                </div>
            </security:authorize>
            <security:authorize access="isAnonymous()">
                <a class="btn btn-danger" href="#" data-toggle="modal" data-target="#login-modal"><spring:message code="header.login"/></a>

                <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                    <div class="modal-dialog">
                        <div class="loginmodal-container">
                            <h1>Login to Your Account</h1><br>
                            <form action="<c:url value="/j_spring_security_check" />" method='POST'>
                                <input type="text" name="username" placeholder="Username" id="username" value="admin">
                                <input type="password" name="password" placeholder="Password" id='password' value="admin">
                                <input type="submit" name="login" class="login loginmodal-submit" value="<spring:message code="login.login" />">

                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                            </form>

                            <div class="login-help">
                                <a href="<c:url value="/register"/>">Register</a>
                            </div>
                        </div>
                    </div>
                </div>
            </security:authorize>
        </ul>
    </div>
</nav>