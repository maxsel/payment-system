<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.CsrfAuthenticationStrategy.SaveOnAccessCsrfToken"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>
    <form action="<c:url value="/j_spring_security_check" />" method='POST'>
        <div>
            <div>
                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="msg">${msg}</div>
                </c:if>
            </div>

            <div>
                <label for="username">
                    <spring:message code="login.login_input"/>:
                </label>

                <input type='text' name='username' id='username'
                       value=''/>

                <label for="password">
                    <spring:message code="login.password_input"/>:
                </label>

                <input type='password' name='password'
                       id='password'/>


                <input class="login-button" name="submit"
                       type="submit"
                       value="<spring:message code="login.login" />"/>

                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </div>
        </div>
    </form>
</div>
