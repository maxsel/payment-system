<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet"/>

<span>
    <h1>CART:</h1>
    <c:forEach items="${cart_items}" var="cart_item">
        <h2>Item: ${cart_item.item}</h2>
        <h2>Amount: ${cart_item.amount}</h2>
    </c:forEach>
</span>