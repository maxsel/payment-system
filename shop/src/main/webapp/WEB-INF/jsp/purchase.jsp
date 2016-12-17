<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="rootUrl" value="/user" />

<div>
    <form:form method="post" action="${rootUrl}/checkOrder" >
        <h1>Order:</h1>
        <c:forEach items="${cart_items}" var="cart_item">
            <div class = "panel panel-success" id="item_${cart_item.item.id}">
                <div class="panel-heading"><h3>${cart_item.item.title}</h3></div>
                <div class = "panel-body">
                    <ul class="list-inline">
                        <li><img width="150" src="/tofi-shop/resources/info?itemId=${cart_item.item.id}"/></li>
                        <li>${cart_item.item.description}</li>
                        <li><h2>Amount: ${cart_item.amount}</h2></li>
                    </ul>
                </div>
            </div>
        </c:forEach>

        <input type="text" value="CVV" name="cvv" id="cvv">
        <input type="text" value="Month" name="Month" id="month">
        <input type="text" value="Year" name="Year" id="year">
        <c:if test="${!empty cart_items}">
            <input type="submit" value="Order" id="order" class="btn btn-success">
        </c:if>
    </form:form>
</div>
<script>
    $('document').ready(function() {
        window.checkFields = function() {
            let order = $('#order');
            let cvv = $('#cvv').val().replace(/\s/g, '');;
            let month = $('#month').val().replace(/\s/g, '');;
            let year = $('#year').val().replace(/\s/g, '');;

            if(!isCVVValid(cvv) || !isMonthValid(month) || !isYearValid(year)) {
                order.prop('disabled',true);
            } else {
                order.prop('disabled',false);
            }
        };

        window.isCVVValid = function(value) {
            if(value === '') {
                return false;
            } else {
                return true;
            }
        };

        window.isYearValid = function(value) {
            var reg = new RegExp("^[0-9]+$");
            if (!reg.test(value)) {
                return false;
            }
            return Number.isInteger(parseInt(value));
        };

        window.isMonthValid = function(value) {
            var reg = new RegExp("^[0-9]+$");
            if (!reg.test(value)) {
                return false;
            }
            value = parseInt(value);
            if(Number.isInteger(value)) {
                if(value > 0 && value < 13) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        };

        $('#cvv').change(() => {checkFields();});
        $('#month').change(() => {checkFields();});
        $('#year').change(() => {checkFields();});

        checkFields();

    });
</script>