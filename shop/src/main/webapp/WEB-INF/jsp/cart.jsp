<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
    <div class="alert alert-success"><h1>Your Cart</h1></div>
    <h3><span class="alert alert-success btn-block" id="discount_cost">Cost with discount: ${discount_cost}</span></h3>
    <h3><span class="alert alert-success btn-block" id="discount">Your current discount: ${discount}%</span></h3>
    <h3><span class="alert alert-success btn-block" id="total_cost">Total cost: ${total_cost}</span></h3>

    <a class="btn btn-danger btn-block" href="purchase" id="make_order"><h3>Make order</h3></a>
    <label style="visibility: hidden;" id="items_count">${cart_items.size()}</label>
    <c:forEach items="${cart_items}" var="cart_item">
        <div class = "panel panel-success" id="item_${cart_item.item.id}">
            <div class="panel-heading">${cart_item.item.title}</div>
            <div class = "panel-body">
                <ul class="list-inline">
                    <li id="${cart_item.item.id}">${cart_item.amount}</li>
                    <li class="pull-right">
                        <button type="button" class="btn btn-primary btn-circle btn-lg" onclick="removeFromCart(${cart_item.item.id})">-</button>
                        <button type="button" class="btn btn-primary btn-circle btn-lg" onclick="addToCart(${cart_item.item.id})">+</button>
                    </li>
                </ul>
            </div>
        </div>
    </c:forEach>

    <script>
        $(document).ready(()=> {
            // hide make_order button if list empty
            (() => {
                console.log('ok');
                if($('#items_count').text() == 0) {
                    console.log('item_count = 0');
                    $('#make_order').hide();
                }
            })();
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
                    url: 'cart/remove?id=' + itemId,
                    data: data,
                    headers: headers,
                    success: function (res) {
                        console.log(res);
                        $('#' + itemId).text(res);
                        // count of items is zero => remove item from list
                        if(res == 0) {
                            console.log('removing item from list');
                            $('#item_' + itemId).remove();
                            let new_items_count = (+$('#items_count').text()) - 1;
                            $('#items_count').text(new_items_count);
                            console.log('new_items_count', new_items_count);
                            if(new_items_count <= 0) {
                                console.log('items count == 0');
                                $('#make_order').hide();
                            }
                        }
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
                    url: 'cart/add?id=' + itemId,
                    data: data,
                    headers: headers,
                    success: function (res) {
                        console.log(res);
                        $('#' + itemId).text(res);
                    }
                });
            };
        });
    </script>
</div>