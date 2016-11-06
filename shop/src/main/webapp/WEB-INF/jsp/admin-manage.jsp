<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="rootUrl" value="/admin/manage-items/"/>


<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="form-body">
                <h1><span class="label label-danger">Sign In</span></h1>
                <div class="tab-content">
                    <div id="sectionA" class="tab-pane fade in active">
                        <div class="innter-form">
                            <form:form action="${rootUrl}add" method="post" modelAttribute="newItem" class="sa-innate-form">
                                <label>Login</label>
                                <form:input path="title" type="text" name="title"/>
                                <label>Password</label>
                                <form:input path="description" type="password" name="description"/>
                                <label>price</label>
                                <form:input path="price" type="text" name="price"/>
                                <button type="submit">Join now</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>