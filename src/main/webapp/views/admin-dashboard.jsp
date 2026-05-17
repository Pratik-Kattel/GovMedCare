<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/17/2026
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard – GovMediCare</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/CSS/admin.css">
</head>
<body>

<%@ include file="admin-navbar.jsp" %>

<div class="main">
    <div class="topbar">
        <h1>Admin Dashboard</h1>

        <div class="topbar-user">
            <div class="avatar">A</div>
            <span>Admin</span>
        </div>
    </div>

    <div class="content">

        <c:if test="${not empty sessionScope.error}">
            <div class="alert alert-error">${sessionScope.error}</div>
            <c:remove var="error" scope="session"/>
        </c:if>

        <div class="stats-grid">

            <div class="stat-card">
                <div class="stat-info">
                    <div class="label">Active Users</div>
                    <div class="value">${activeUsers}</div>
                </div>
            </div>

            <div class="stat-card">
                <div class="stat-info">
                    <div class="label">Blocked Users</div>
                    <div class="value">${blockedUsers}</div>
                </div>
            </div>

            <div class="stat-card">
                <div class="stat-info">
                    <div class="label">Pending Medicines</div>
                    <div class="value">${pendingMedicines}</div>
                </div>
            </div>

            <div class="stat-card">
                <div class="stat-info">
                    <div class="label">Approved Medicines</div>
                    <div class="value">${approvedMedicines}</div>
                </div>
            </div>

            <div class="stat-card">
                <div class="stat-info">
                    <div class="label">Total Revenue</div>
                    <div class="value">NPR ${revenue}</div>
                </div>
            </div>

            <div class="stat-card">
                <div class="stat-info">
                    <div class="label">Total Tax</div>
                    <div class="value">NPR ${tax}</div>
                </div>
            </div>

        </div>

    </div>
</div>

</body>
</html>
