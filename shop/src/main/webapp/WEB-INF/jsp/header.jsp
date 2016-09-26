<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>

<c:url var="rootUrl" value="/"/>

<header style="position: fixed; top:0; width: 100%; background-color: white">
    <table border="1"
           style="width: 98.8%;border-collapse:collapse;border:solid;">
        <tr style="height:3%;border-top:solid;">
            <td>
                <div class="header">
                    <security:authorize url="/admin/**">
                        <div class="logout-form">
                            <form:form action="${rootUrl}logout" method="POST">
                                <span class="greeting">
                                    <spring:message code="header.hello"/>,
                                    <security:authentication property="principal.username"/>
                                </span>
                                <span class="logout-button">
                                    <input type="submit"
                                       value="<spring:message code="header.logout"/>"/>
                                </span>
                            </form:form>
                        </div>
                    </security:authorize>
                    <span class="title">
                        <h1>
                            <spring:message code="header.text"/> - <spring:message code="header.administration"/>
                        </h1>
                    </span>
                    <span class="lang">
                        <a href="?lang=en_US">EN</a>&nbsp;
                        <a href="?lang=ru_RU">RU</a>
                    </span>
                </div>
            </td>
        </tr>
    </table>
</header>
