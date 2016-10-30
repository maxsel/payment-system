<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<h1>---Items---</h1>
<div>
    <c:forEach items="${items}" var="item">
        <div>
            ${item.id}
            ${item.title}
            ${item.description}
            <security:authorize url="/admin/**">
                <input type="button" onclick="addToCart(${item.id})" value="Add">
            </security:authorize>
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
                    url: 'admin/cart/add?id=' + itemId,
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
