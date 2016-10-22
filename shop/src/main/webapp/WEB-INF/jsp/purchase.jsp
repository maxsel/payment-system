<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="rootUrl" value="/user" />

<span>
    <form:form method="post" action="${rootUrl}/order" >
        <h1>Order:</h1>
        <c:forEach items="${cart_items}" var="cart_item">
            <h2>Item: ${cart_item.item}</h2>
            <h2>Amount: ${cart_item.amount}</h2>
        </c:forEach>
        <input type="text" value="CVV">
        <input type="submit" value="Order">
    </form:form>
</span>