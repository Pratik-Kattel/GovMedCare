<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/16/2026
  Time: 9:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>My Cart – GovMediCare</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/CSS/patient.css">
</head>
<body>

<%@ include file="patient-navbar.jsp" %>

<div class="main">
    <div class="topbar">
        <h1>My Cart</h1>
        <div class="topbar-user">
            <div class="avatar">P</div>
            <span>Patient</span>
        </div>
    </div>

    <div class="content">

        <div class="table-card">
            <c:choose>
                <c:when test="${empty cartItems}">
                    <div class="empty-state">Your cart is empty.</div>
                </c:when>

                <c:otherwise>
                    <table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Medicine</th>
                            <th>Price (NPR)</th>
                            <th>Quantity</th>
                            <th>Subtotal</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:set var="grandTotal" value="0"/>

                        <c:forEach var="item" items="${cartItems}" varStatus="loop">
                            <c:set var="subtotal" value="${item.price * item.quantity}"/>
                            <c:set var="grandTotal" value="${grandTotal + subtotal}"/>

                            <tr>
                                <td class="td-index">${loop.index + 1}</td>
                                <td class="td-name">${item.medicineName}</td>
                                <td>NPR ${item.price}</td>
                                <td>${item.quantity}</td>
                                <td>NPR ${subtotal}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div class="cart-summary">
                        <h3>Total: NPR ${grandTotal}</h3>

                        <a href="${pageContext.request.contextPath}/patient/checkout"
                           class="btn-submit">
                            Proceed to Checkout
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</div>

</body>
</html>