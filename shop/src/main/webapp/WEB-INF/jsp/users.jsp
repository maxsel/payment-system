<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="rootUrl" value="/admin/manage-users" />

<div>
<h1>USERS:</h1>
<c:forEach items="${users}" var="userVAR">
    <form:form action="${rootUrl}/update-user" method="post" modelAttribute="user">
        <form:input path="id" value="${userVAR.id}" />
        <form:input path="login" value="${userVAR.login}" type="hidden" />
        <form:input path="password" value="${userVAR.password}" type="hidden" />
        <form:input path="cardId" value="${userVAR.cardId}" type="hidden" />

        <form:input path="blocked" value="${userVAR.blocked}" type="hidden" />

        <c:choose>
            <c:when test="${userVAR.blocked}">
                <c:out value="BLOCKED" />
                <a href="${rootUrl}/block-user/${userVAR.id}/">Unblock</a>
            </c:when>
            <c:otherwise>
                <c:out value="ACTIVE" />
                <a href="${rootUrl}/block-user/${userVAR.id}/">Block</a>
            </c:otherwise>
        </c:choose>
        Discount: <form:input path="discount" value="${userVAR.discount}" />
        <input type="submit" value="Update">
    </form:form>
</c:forEach>