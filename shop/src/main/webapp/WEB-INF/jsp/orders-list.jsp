<%--suppress XmlPathReference --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>

<div>
    <div class="alert alert-success"><h1>Your Orders</h1></div>
    <c:forEach items="${orders}" var="order">
        <div class = "panel panel-success" id="item_${order.id}">
            <div class="panel-heading"><h2>STATUS: ${order.status.name}</h2></div>
            <div class = "panel-body">
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="panel">
                        <div class="panel-body">
                            <c:url var="rootUrl" value="/admin"/>
                            <c:choose>
                                <c:when test="${order.status.id == 1}">
                                    <a href="${rootUrl}/change-order-status/${order.id}/ready" class="btn btn-success">Complete</a>
                                    <a href="${rootUrl}/change-order-status/${order.id}/reject" class="btn btn-danger">Reject</a>
                                </c:when>
                                <c:when test="${order.status.id == 2}">
                                    <a href="${rootUrl}/change-order-status/${order.id}/archive" class="btn btn-success">Archive</a>
                                    <a href="${rootUrl}/change-order-status/${order.id}/reject" class="btn btn-danger">Reject</a>
                                </c:when>
                                <c:when test="${order.status.id == 3}">
                                    <a href="${rootUrl}/change-order-status/${order.id}/archive" class="btn btn-success">Archive</a>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </security:authorize>
                <h2>ITEMS:</h2>
                <ul class="list-inline">
                    <c:forEach items="${order.items}" var="item">
                        <li>
                            <div class="panel panel-default">
                                <div class="panel-heading" style="height: 40px;"><h5>${item.item.title}</h5></div>
                                <ul class="list-inline list-cell">
                                    <li>
                                        <pre>${item.amount} x ${item.item.price} BYN = ${item.instantPrice} BYN</pre>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>

    </c:forEach>
</div>