<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="rootUrl" value="/user" />

<html>
<head>
    <title>currentcy-confirm</title>
</head>
<body>
    <div class="container">
        <div class="well text-center">make order in different currency?</div>
        <div class="text-center">
            <form:form action="${rootUrl}/order/" method="post">
                <input name="cvv" value="${cvv}" type="hidden">
                <input class="btn btn-success" type="submit" value="Yes">
                <a class="btn btn-success" href="/tofi-shop">No</a>
            </form:form>
        </div>
    </div>
</body>
</html>
