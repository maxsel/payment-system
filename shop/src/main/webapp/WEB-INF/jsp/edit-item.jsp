<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:if test="${operation=='add'}">
    <c:url var="rootUrl" value="/admin/items/add"/>
</c:if>
<c:if test="${operation=='edit'}">
    <c:url var="rootUrl" value="/admin/items/${item.id}/edit"/>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-md-1">
            <div class="form-body">
                <div class="tab-content">
                    <div id="sectionA" class="tab-pane fade in active">
                        <div class="innter-form">
                            <form:form action="${rootUrl}add" method="post" modelAttribute="item" class="sa-innate-form">
                                <label>Title</label>
                                <form:input path="title" type="text" name="title"/>
                                <label>Description</label>
                                <form:input path="description" type="text" name="description"/>
                                <label>Price</label>
                                <form:input path="price" type="text" name="price"/>
                                <form:select path="category">
                                    <form:options itemLabel="name" items="${categoriesList}" itemValue="id"/>
                                </form:select>
                                <button type="submit">Create</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>