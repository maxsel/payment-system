<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="rootUrl" value="/"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${rootUrl}">Tofi-shop</a>
        </div>
        <div class="navbar-collapse collapse navbar-responsive-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <!--li><a href="/tofi-shop/about">About Us</a></li-->
            <!--li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Language<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="?lang=en_US">EN</a>
                    <li><a href="?lang=ru_RU">RU</a></li>
                </ul>
            </li-->
            <security:authorize access="hasRole('ROLE_USER')">
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">User Actions<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${rootUrl}user/orders">Orders</a></li>
                        <li><a href="${rootUrl}user/profile">Profile</a></li>
                        <li><a href="${rootUrl}user/purchase">Purchase</a></li>
                    </ul>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin Actions<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${rootUrl}admin/orders">Orders</a></li>
                    </ul>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_USER')">
                <li><a href="${rootUrl}user/cart">Cart</a></li>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="${rootUrl}admin/manage-users">Manage users</a></li>
            </security:authorize>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
                <div class="menu-item-login">
                    <form:form action="${rootUrl}logout" method="POST">
                        <div>
                            <input type="submit" class="btn btn-danger" value="<spring:message code="header.logout"/>"/>
                        </div>
                    </form:form>
                </div>
            </security:authorize>
            <security:authorize access="isAnonymous()">
                <a class="btn btn-success menu-item-login" href="#" data-toggle="modal" data-target="#login-modal"><spring:message code="header.login"/></a>

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
    </div>
</nav>