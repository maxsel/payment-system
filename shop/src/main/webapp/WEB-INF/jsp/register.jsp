
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="<c:url value="/resources/layout/bootstrap.min.css" />">
    <script src="<c:url value="/resources/layout/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/layout/bootstrap.min.js" />"></script>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/register.css" />" />
</head>
<body>

<c:url var="rootUrl" value="/"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav navbar-right">
            <a class="btn btn-danger" href="/tofi-shop/">Back</a>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="form-body">
                <h1><span class="label label-danger">Sign In</span></h1>
                <div class="tab-content">
                    <div id="sectionA" class="tab-pane fade in active">
                        <div class="innter-form">
                            <form:form action="${rootUrl}signup" method="post" modelAttribute="newUser" class="sa-innate-form">
                                <label>Login</label>
                                <form:input path="login" type="text" name="login"/>
                                <label>Password</label>
                                <form:input path="password" type="password" name="password"/>
                                <label>Card Id</label>
                                <form:input path="cardId" type="text" name="card_id"/>
                                <button type="submit">Join now</button>
                                <p>By clicking Join now, you agree to our User Agreement, Privacy Policy, and Cookie Policy.</p>
                            </form:form>
                            ${error}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
