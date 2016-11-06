ADMIN items manage page

<form:form action="${rootUrl}add" method="post" modelAttribute="newItem" class="sa-innate-form">
    <label>title</label>
    <form:input path="title" type="text" name="title"/>
    <label>description</label>
    <form:input path="description" type="password" name="description"/>
    <button type="submit">Create</button>
    <p>By clicking Join now, you agree to our User Agreement, Privacy Policy, and Cookie Policy.</p>
</form:form>