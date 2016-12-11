<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:if test="${operation=='add'}">
    <c:url var="rootUrl" value="/admin/items/add"/>
</c:if>
<c:if test="${operation=='edit'}">
    <c:url var="rootUrl" value="/admin/items/${item.id}/edit"/>
</c:if>


<form:form action="${rootUrl}/save?${_csrf.parameterName}=${_csrf.token}"
           method="post" modelAttribute="item" enctype="multipart/form-data"
           class="sa-innate-form" >

    <ul class="list-inline">
        <li>
            <div class="panel panel-success">
                <div class="panel-heading">Title</div>
                <div class="panel-body">
                    <form:input path="title" type="text" name="title" class="form-control"/>
                </div>
            </div>
        </li>
        <li>
            <div class="panel panel-success">
                <div class="panel-heading">Description</div>
                <div class="panel-body">
                    <form:input path="description" type="text" name="description" class="form-control"/>
                </div>
            </div>
        </li>
        <li>
            <div class="panel panel-success">
                <div class="panel-heading">Price</div>
                <div class="panel-body">
                    <form:input path="price" type="text" name="price" class="form-control"/>
                </div>
            </div>
        </li>
        <li>
            <div class="panel panel-success">
                <div class="panel-heading">Category</div>
                <div class="panel-body">
                    <form:select path="category" class="form-control">
                        <form:options itemLabel="name" items="${categoriesList}" itemValue="id" class="form-control"/>
                    </form:select>
                </div>
            </div>
        </li>
        <li>
            <div class="panel panel-success">
                <div class="panel-heading">Category</div>
                <div class="panel-body">
                    <form:select path="status" class="form-control">
                        <form:options itemLabel="name" items="${statusList}" itemValue="id" class="form-control"/>
                    </form:select>
                </div>
            </div>
        </li>
        <li>
        <li>
            <img height="150" width="200" src="${pageContext.request.contextPath}/resources/image/${item.id}"/>
            <input id="file" name="file" type="file" />
            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
        </li>
    </ul>

    <form:hidden path="id" />
    <button type="submit" class="btn btn-danger">Save</button>
</form:form>