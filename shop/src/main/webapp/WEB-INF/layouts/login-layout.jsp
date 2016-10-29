<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%--
    <title><tiles:getAsString name="title"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap/css/bootstrap.css" />" />
    <script src="<c:url value="/resources/css/jquery-3.1.1.min.js" />" ></script>
    <script src="<c:url value="/resources/css/tether-1.3.3/dist/js/tether.js" />" ></script>
    <script src="<c:url value="/resources/css/bootstrap/js/bootstrap.js" />" ></script>
    --%>



        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css" />" />

</head>
<body>
    <tiles:insertAttribute name="header"/>
    <tiles:insertAttribute name="body"/>
    <tiles:insertAttribute name="footer"/>
</body>
</html>