<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="rootUrl" value="/admin/manage-items/"/>

ADMIN items manage page
<div class="container">
<div class="row">
<div class="col-md-4 col-md-offset-4">
<div class="form-body">
<h1><span class="label label-danger">Create Item</span></h1>
<div class="tab-content">
<div id="sectionA" class="tab-pane fade in active">
<div class="innter-form">

<form:form action="${rootUrl}add" method="post" modelAttribute="newItem" class="sa-innate-form">
    <label>title</label>
    <form:input path="title" type="text" name="title"/>
    <label>description</label>
    <form:input path="description" type="password" name="description"/>
    <button type="submit">Create</button>
    <p>By clicking Join now, you agree to our User Agreement, Privacy Policy, and Cookie Policy.</p>
</form:form>

</div>
</div>
</div>
</div>
</div>
</div>
</div>