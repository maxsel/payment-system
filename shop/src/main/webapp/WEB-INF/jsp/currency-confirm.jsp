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
        <div class="row">
            <div class="col-md-4 col-sm-12 col-xs-12 my-col text-center">
                <div class="well">make order in different currency?</div>
            </div>
            <div class="col-md-4 col-sm-12 col-xs-12 my-col text-center">
                <form:form action="${rootUrl}/order/" method="post">
                    <input name="cvv" value="${cvv}" type="hidden">
                    <input class="btn btn-success" type="submit" value="Yes" class="btn">
                </form:form>
                <a class="btn btn-success" href="/tofi-shop" class="btn">No</a>
            </div>
        </div>
    </div>
</body>
</html>
