<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title><tiles:getAsString name="title"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap/css/bootstrap.css" />" />
    <script src="<c:url value="/resources/css/jquery-3.1.1.min.js" />" ></script>
    <script src="<c:url value="/resources/css/tether-1.3.3/dist/js/tether.js" />" ></script>
    <script src="<c:url value="/resources/css/bootstrap/js/bootstrap.js" />" ></script>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css" />" />
</head>
<body>
    <tiles:insertAttribute name="header"/>
    <div class="container body-content">
        <tiles:insertAttribute name="body"/>
        <hr />
        <footer>
            <tiles:insertAttribute name="footer"/>
        </footer>
    </div>
</body>
</html>