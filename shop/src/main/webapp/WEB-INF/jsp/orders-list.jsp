<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
    <h1>ORDERS:</h1>
    <c:forEach items="${orders}" var="order">
        <h2>ID: ${order.id}</h2>
        <h2>STATUS: ${order.status.name}</h2>
        <h2>ITEMS:</h2>
        <c:forEach items="${order.items}" var="item">
            <c:out value="${item}" />
            <hr/>
        </c:forEach>
    </c:forEach>
</div>