<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
    <div class="alert alert-success"><h1>Your Orders</h1></div>
    <c:forEach items="${orders}" var="order">
        <div class = "panel panel-success" id="item_${order.id}">
            <div class="panel-heading"><h2>STATUS: ${order.status.name}</h2></div>
            <div class = "panel-body">
                <h2>ITEMS:</h2>
                <ul class="list-inline">
                    <c:forEach items="${order.items}" var="item">
                        <li>
                            <div class="panel panel-default">
                                <div class="panel-heading" style="height: 40px;"><h5>${item.item.title}</h5></div>
                                <ul class="list-inline list-cell">
                                    <li>
                                        ${item.amount} x ${item.item.price} BYN = ${item.instantPrice} BYN
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