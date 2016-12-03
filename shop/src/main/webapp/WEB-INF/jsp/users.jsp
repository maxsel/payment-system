<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="rootUrl" value="/admin/manage-users" />

<div>
    <div class="alert alert-success"><h1>Users List</h1></div>
    <c:forEach items="${users}" var="userVAR">
        <div class="panel panel-success">
            <form:form action="${rootUrl}/update-user" method="post" modelAttribute="user">
                <div class="panel">
                    <div class="panel-heading">User login</div>
                    <div class="panel-body">
                        <form:input path="login" value="${userVAR.login}" class="form-control" readonly="true" />
                    </div>
                </div>

                <div class="panel">
                    <div class="panel-heading">Discount</div>
                    <div class="panel-body">
                        <form:input path="discount" value="${userVAR.discount}" class="form-control"/>
                    </div>
                </div>

                <div class="panel">
                    <c:choose>
                        <c:when test="${userVAR.blocked}">
                            <div class="panel-heading"><c:out value="BLOCKED" /></div>
                            <div class="panel-body">
                                <a class="btn btn-danger" href="${rootUrl}/block-user/${userVAR.id}/">Unblock</a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="panel-heading"><c:out value="ACTIVE" /></div>
                            <div class="panel-body">
                                <a class="btn btn-danger" href="${rootUrl}/block-user/${userVAR.id}/">Block</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <form:input path="id" value="${userVAR.id}" type="hidden" class="form-control"/>
                <form:input path="password" value="${userVAR.password}" type="hidden" />
                <form:input path="cardId" value="${userVAR.cardId}" type="hidden" />
                <form:input path="blocked" value="${userVAR.blocked}" type="hidden" />
                <input type="submit" value="Update" class="btn btn-danger">
            </form:form>
        </div>
    </c:forEach>
</div>