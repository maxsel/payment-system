<%--@elvariable id="currentPage" type="int"--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript"
        src="<c:url value="/resources/js/jquery.min.js" />">
</script>
<script
        type="text/javascript"
        src="<c:url value="/resources/js/jquery-ui.js" />">
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $("#" + '${currentPage}').attr('style', 'font-weight: bold');
    });
</script>

<div class="sidebar-content">
    <div class="menu-list">
        <ul type="circle">
            <li id="news-list">
                <a href="<c:url value="/admin/news-list/" />">
                    <spring:message code="menu.news_list" />
                </a>
            </li>

            <li id="edit-news">
                <a href="<c:url value="/admin/news/add" />">
                    <spring:message code="menu.add_news"/>
                </a>
            </li>

            <li id="edit-authors">
                <a href="<c:url value="/admin/authors/edit-authors" />">
                    <spring:message code="menu.add_update_authors"/>
                </a>
            </li>

            <li id="edit-tags">
                <a href="<c:url value="/admin/tags/edit-tags" />">
                    <spring:message code="menu.add_update_tags"/>
                </a>
            </li>
        </ul>
    </div>
</div>