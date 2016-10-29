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
                    <a class="btn btn-danger" href="#" data-toggle="modal" data-target="#login-modal">Login</a>

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
                </div>
            </security:authorize>
        </ul>
    </div>
</nav>