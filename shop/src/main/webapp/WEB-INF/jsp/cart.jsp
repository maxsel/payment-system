<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
    <h1>CART:</h1>
    <c:forEach items="${cart_items}" var="cart_item">
        <div class = "panel panel-success">
            <div class="panel-heading">${cart_item.item.title}</div>
            <div class = "panel-body">
                <ul class="list-inline">
                    <li>${cart_item.amount}</li>
                    <li class="pull-right">
                        <button type="button" class="btn btn-primary btn-circle btn-lg" onclick="removeFromCart(${cart_item.id})">-</button>
                        <button type="button" class="btn btn-primary btn-circle btn-lg" onclick="addToCart(${cart_item.id})">+</button>
                    </li>
                </ul>
            </div>
        </div>
    </c:forEach>

    <script>
        $(document).ready(()=> {
            window.removeFromCart = function (itemId) {
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
                    url: 'items-list/remove?id=' + itemId,
                    data: data,
                    headers: headers,
                    success: function (res) {
                        console.log(res);
                    }
                });
            };

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
                url: 'items-list/add?id=' + itemId,
                data: data,
                headers: headers,
                success: function (res) {
                    console.log(res);
                }
            });
        };
        });
    </script>
</div>