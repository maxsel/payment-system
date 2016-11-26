<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="rootUrl" value="/user" />

<div>
    <form:form method="post" action="${rootUrl}/checkOrder" >
        <h1>Order:</h1>
        <c:forEach items="${cart_items}" var="cart_item">
            <div class = "panel panel-success" id="item_${cart_item.item.id}">
                <div class="panel-heading"><h3>${cart_item.item.title}</h3></div>
                <div class = "panel-body">
                    <ul class="list-inline">
                        <li><img width=150 src="${rootUrl}resources/info?itemId=${cart_item.item.id}"/></li>
                        <li>${cart_item.item.description}</li>
                        <li><h2>Amount: ${cart_item.amount}</h2></li>
                    </ul>
                </div>
            </div>
        </c:forEach>

        <input type="text" value="CVV">
        <c:if test="${!empty cart_items}">
            <input type="submit" value="Order">
        </c:if>
    </form:form>
</div>