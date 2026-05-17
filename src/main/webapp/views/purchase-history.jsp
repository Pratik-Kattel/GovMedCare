<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/16/2026
  Time: 10:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Purchase History – GovMediCare</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/CSS/patient.css">
</head>
<body>

<%@ include file="patient-navbar.jsp" %>

<div class="main">
    <div class="topbar">
        <h1>My Purchases</h1>
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
        <div class="table-card">
            <c:choose>
                <c:when test="${empty purchaseHistory}">
                    <div class="empty-state">No purchase history found.</div>
                </c:when>

                <c:otherwise>
                    <table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Order ID</th>
                            <th>Total Amount</th>
                            <th>Purchase Date</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="order" items="${purchaseHistory}" varStatus="loop">
                            <tr>
                                <td class="td-index">${loop.index + 1}</td>
                                <td class="td-name">#${order.orderId}</td>
                                <td>NPR ${order.totalAmount}</td>
                                <td>${order.createdAt}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</div>

</body>
</html>