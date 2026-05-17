<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/17/2026
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Checkout – GovMediCare</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/CSS/patient.css">
</head>
<body>

<%@ include file="patient-navbar.jsp" %>

<div class="main">
    <div class="topbar">
        <h1>Checkout</h1>
        <div class="topbar-user">
            <div class="avatar">P</div>
            <span>Patient</span>
        </div>
    </div>

    <div class="content">
        <c:if test="${not empty sessionScope.success}">
            <div class="alert alert-success">
                    ${sessionScope.success}
            </div>

            <c:remove var="success" scope="session"/>
        </c:if>

        <c:if test="${not empty sessionScope.error}">
            <div class="alert alert-error">
                    ${sessionScope.error}
            </div>

            <c:remove var="error" scope="session"/>
        </c:if>

        <div class="checkout-layout">

            <div class="table-card checkout-card">
                <h2 class="checkout-title">Order Summary</h2>

                <table>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Medicine</th>
                        <th>Price</th>
                        <th>Qty</th>
                        <th>Subtotal</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="item" items="${cartItems}" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td class="td-name">${item.medicineName}</td>
                            <td>NPR ${item.price}</td>
                            <td>${item.quantity}</td>
                            <td>NPR ${item.price * item.quantity}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="payment-card">
                <h2>Payment Details</h2>
                <p>Choose your payment method and confirm your order.</p>

                <form method="post"
                      action="${pageContext.request.contextPath}/patient/checkout">

                    <input type="hidden" name="paidAmount" value="${grandTotal}">

                    <div class="form-group">
                        <label>Total Amount</label>
                        <input type="text" value="NPR ${grandTotal}" readonly>
                    </div>

                    <div class="form-group">
                        <label>Payment Method</label>
                        <select name="paymentMethod" required>
                            <option value="">Choose Payment Method</option>
                            <option value="CASH">Cash</option>
                            <option value="CARD">Card</option>
                            <option value="ESEWA">eSewa</option>
                            <option value="KHALTI">Khalti</option>
                        </select>
                    </div>

                    <button type="submit" class="btn-submit full-btn">
                        Confirm Payment
                    </button>

                    <a href="${pageContext.request.contextPath}/patient/cart"
                       class="btn-cancel full-btn">
                        Back to Cart
                    </a>

                </form>
            </div>

        </div>

    </div>
</div>

</body>
</html>