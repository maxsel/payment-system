<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="rootUrl" value="/"/>

<h1>Items list</h1>
<div>
    Total Price: <div id="totalPrice"></div>
</div>

<div class="text-center">
    <security:authorize access="hasRole('ROLE_ADMIN')">
        <a href="${rootUrl}admin/items/add" class="btn btn-default">Add item</a>
    </security:authorize>
    <ul class="list-inline">
        <c:forEach items="${items}" var="item">
            <li>
                <div class = "panel panel-success " id="item_${item.id}">
                    <div class="panel-heading">
                        <ul class="list-inline">
                            <li>
                                <h3>${item.title}</h3>
                            </li>
                            <li class="pull-right">
                                <div>${item.price} BYN</div>
                                <div id="count_${item.id}">count: 0</div>
                            </li>
                            <security:authorize access="hasRole('ROLE_ADMIN')">
                                <li class="pull-right">
                                    <a href="${rootUrl}admin/items/${item.id}/edit" class="btn btn-default pull-right">Edit</a>
                                </li>
                            </security:authorize>
                        </ul>
                    </div>
                    <div class = "panel-body">
                        <ul class="list-inline">
                            <li><img height="150" width="200" src="resources/image/${item.id}"/></li>
                            <li>${item.description}</li>
                            <security:authorize access="hasRole('ROLE_USER')">
                                <li class="pull-right">
                                    <button type="button" class="btn btn-primary btn-circle btn-lg" onclick="addToCart(${item.id})">+</button>
                                </li>
                            </security:authorize>
                        </ul>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
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
                        $('#count_'+itemId).text('count: ' + res.itemsCount);
                        $('#totalPrice').text(res.totalPriceWithDiscount);
                    }
                });
            };
        });
    </script>
</div>
