<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="rootUrl" value="/"/>

<h1>Items list</h1>
<div>
    <c:forEach items="${items}" var="item">
        <div>
            <div class = "panel panel-success" id="item_${item.id}">
                <div class="panel-heading"><h3>${item.title}</h3></div>
                <div class = "panel-body">
                    <ul class="list-inline">
                        <li><img width="150" src="resources/info?itemId=${item.id}"/></li>
                        <li>${item.id}</li>
                        <li>${item.description}</li>
                        <security:authorize access="hasRole('ROLE_USER')">
                            <li class="pull-right">
                                <button type="button" class="btn btn-primary btn-circle btn-lg" onclick="addToCart(${item.id})">+</button>
                            </li>
                        </security:authorize>
                    </ul>
                </div>
            </div>
        </div>
    </c:forEach>

    <script>
        $(document).ready(()=> {

            window.addToCart = function (itemId) {
                var csrfParameter = '${_csrf.parameterName}';
                var csrfToken = '${_csrf.token}';
                var csrfHeader = '${_csrf.headerName}';

                var data = {};
                var headers = {};

                data[csrfParameter] = csrfToken;
                headers[csrfHeader] = csrfToken;

                $.ajax({
                    type: "POST",
                    async: false,
                    <%--url: '${rootUrl}user/cart/add?id=' + itemId,--%>
                    url: 'items-list/add?id=' + itemId,
                    data: data,
                    headers: headers,
                    success: function (res) {
                        console.log(res);
                        let item = $('#item_' + itemId);
                        item.removeClass('panel-success');
                        item.addClass('panel-danger');
                    }
                });
            };
        });
    </script>
</div>
