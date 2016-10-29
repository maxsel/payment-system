<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
    <c:forEach items="${items}" var="item">
        <div>
            ${item.id}
            ${item.title}
            ${item.description}
            <input type="button" class="addToCart" value="${item.id}">
        </div>
    </c:forEach>

    ${_csrf.parameterName}
    ${_csrf.token}

    <script>
        $(document).ready(()=> {
            $(".addToCart").click(() => {


                var csrfParameter = '${_csrf.parameterName}';
                var csrfToken = '${_csrf.token}';
                var csrfHeader = '${_csrf.headerName}';

                var data = {};
                var headers = {};
                console.log(csrfParameter);
                console.log(csrfToken);
                console.log(csrfHeader);
                data[csrfParameter] = csrfToken;
                headers[csrfHeader] = csrfToken;
                console.log(data);
                console.log(headers);
                $.ajax({
                    type: "POST",
                    async: false,
                    url: 'cart/add?id=1',
                    data: data,
                    headers: headers,
                    success: function (res) {
                        console.log(res);
                    }
                });
            });
        });
    </script>
</div>
