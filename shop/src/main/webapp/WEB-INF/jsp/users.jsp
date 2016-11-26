<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="rootUrl" value="/admin/manage-users" />

<div>
<h1>USERS:</h1>
<c:forEach items="${users}" var="userVAR">
    <form:form action="${rootUrl}/block-user" method="post" modelAttribute="user">
        <form:input path="id" value="${userVAR.id}" type="hidden" />
        <form:input path="login" value="${userVAR.login}" />
        <form:input path="password" value="${userVAR.password}" type="hidden" />
        <form:input path="cardId" value="${userVAR.cardId}" type="hidden" />
        <form:input path="discount" alue="${userVAR.discount}" type="hidden" />
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
        <form:input path="roles" value="${userVAR.roles.toArray()[0].getName()}" type="hidden" />
        <form:input path="itemsInCart" value="${userVAR.itemsInCart}" type="hidden" />
        <form:input path="orders" value="${userVAR.orders}" type="hidden" />
        <%--<form:button>Update</form:button>--%>
    </form:form>
</c:forEach>