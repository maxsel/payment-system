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
        <div class="btn btn-success" onClick="print()">print</div>
    </li>
    <li>
        <div class="btn btn-success" onClick="emailMe()">email me</div>
    </li>
</ul>

<script>
    window.print = function() {
        console.log('print');
        printCode('code');
    };

    window.emailMe = function() {
        console.log('emailMe');
    };

    window.printCode = function(div_id) {
        var prtContent = document.getElementById(div_id);
        var WinPrint = window.open('', '', 'left=0,top=0,width=800,height=900,toolbar=0,scrollbars=0,status=0');
        WinPrint.document.write('Thanks for your order\n' + prtContent.innerHTML);
        WinPrint.document.close();
        WinPrint.focus();
        WinPrint.print();
        WinPrint.close();
    }
</script>
