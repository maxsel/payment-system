<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.CsrfAuthenticationStrategy.SaveOnAccessCsrfToken"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>
    <a href="#" data-toggle="modal" data-target="#login-modal">Login</a>

    <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="loginmodal-container">
                <h1>Login to Your Account</h1><br>
                <form action="<c:url value="/j_spring_security_check" />" method='POST'>
                    <input type="text" name="username" placeholder="Username" id="username">
                    <input type="password" name="password" placeholder="Password" id='password'>
                    <input type="submit" name="login" class="login loginmodal-submit" value="<spring:message code="login.login" />">

                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </form>

                <div class="login-help">
                    <a href="#">Register</a> - <a href="#">Forgot Password</a>
                </div>
            </div>
        </div>
    </div>
</div>
