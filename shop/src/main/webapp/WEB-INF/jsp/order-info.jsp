<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="rootUrl" value="/"/>

<div id="code">
    <pre>Your order code: ${orderCode}</pre>
</div>
<ul class="list-inline">
    <li>
        <div><a class="btn btn-default" href="${rootUrl}user/orders">OK</a></div>
    </li>
    <li>
        <div class="btn btn-success" onClick="print()">print code</div>
    </li>
    <li>
        <a class="btn btn-success" href="#" data-toggle="modal" data-target="#login-modal">email me</a>

        <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="loginmodal-container">
                    <h1>Please enter your email</h1><br>
                    <form action="<c:url value="/j_spring_security_check" />">
                        <input type="text" name="email" placeholder="email" id="email" value="email@mailme.com">
                        <input type="button" name="login" class="login loginmodal-submit" value="email me" id="button0" onClick="emailMe()">

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
        </div>
    </li>
</ul>

<script>
    window.print = function() {
        console.log('print');
        printCode('code');
    };

    window.emailMe = function() {
        console.log('emailMe');
        $('#button0').prop('disabled',true);
        $('#button0').hide();
        sendEmail($('#email').val());

    };

    window.printCode = function(div_id) {
        var prtContent = document.getElementById(div_id);
        var WinPrint = window.open('', '', 'left=0,top=0,width=800,height=900,toolbar=0,scrollbars=0,status=0');
        WinPrint.document.write('Thanks for your order\n' + prtContent.innerHTML);
        WinPrint.document.close();
        WinPrint.focus();
        WinPrint.print();
        WinPrint.close();
    };

    window.sendEmail = function(email) {
        console.log(email);
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
            url: '/tofi-shop/user/sendMail?mail=' + email,
            data: data,
            headers: headers,
            success: function (res) {
                console.log(res);
            }
        });
    };
</script>
