<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="<c:url value="/resources/layout/bootstrap.min.css" />">
    <script src="<c:url value="/resources/layout/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/layout/bootstrap.min.js" />"></script>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css" />" />
</head>
<body>
    <tiles:insertAttribute name="header"/>
    <div class="container body-content">
        <tiles:insertAttribute name="body"/>
    </div>
    <tiles:insertAttribute name="footer"/>
</body>
</html>