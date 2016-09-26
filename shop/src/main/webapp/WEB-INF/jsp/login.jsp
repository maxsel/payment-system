<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.CsrfAuthenticationStrategy.SaveOnAccessCsrfToken"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="login-box">
    <form class="login-form"
          action="<c:url value="/j_spring_security_check" />"
          method='POST'>

        <div class="block-center">

            <div class="login-messages">
                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="msg">${msg}</div>
                </c:if>
            </div>

            <div class="login-table">

                <table>
                    <tr>
                        <td>
                            <label for="username">
                                <spring:message code="login.login_input"/>:
                            </label>
                        </td>
                        <td>
                            <input type='text' name='username' id='username'
                                   value=''/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="password">
                                <spring:message code="login.password_input"/>:
                            </label>
                        </td>
                        <td>
                            <input type='password' name='password'
                                   id='password'/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan='2'>
                            <input class="login-button" name="submit"
                                   type="submit"
                                   value="<spring:message code="login.login" />"/>
                        </td>
                    </tr>
                </table>

                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </div>
        </div>

    </form>
</div>
