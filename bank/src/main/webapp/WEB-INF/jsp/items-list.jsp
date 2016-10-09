<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<table id="content" cellspacing="50">
    <tbody>
    <jsp:useBean id="now" class="java.util.Date" />
    <c:forEach items="${items}" var="item">
        <form:form action="/tofi-shop/update" method="post" modelAttribute="item">
            <tr>
                <td>
                    <b><spring:message code="edit_authors.author" />:</b>
                    <form:input path="id" value="${item.id}" />
                </td>
                <td>
                    <form:input path="title" value="${item.title}"
                                style="width:400" type="text" />
                    <br/>
                </td>
                <td>
                    <form:input path="description" value="${item.description}"
                                style="width:400" type="text" />
                    <br/>
                </td>
                <td>
                    <form:input path="price" value="${item.price}"
                                style="width:400" type="text" disabled="true" />
                    <br/>
                </td>
            </tr>
        </form:form>
    </c:forEach>
    </tbody>
</table>
</div>