<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h1>---Items---</h1>
<div>
    <c:forEach items="${items}" var="item">
        <div>
            ${item.id}
            ${item.title}
            ${item.description}
            <a href="cart/add?id=${item.id}"> add to cart</a>
        </div>
    </c:forEach>
</div>
