<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/16/2026
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Patient Dashboard – GovMediCare</title>
  <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/CSS/patient.css">
</head>
<body>

<%@ include file="patient-navbar.jsp" %>

<div class="main">
  <div class="topbar">
    <h1>Patient Dashboard</h1>
    <div class="topbar-user">
      <div class="avatar">P</div>
      <span>Patient</span>
    </div>
  </div>

  <div class="content">

    <section class="hero-section">
      <h2>Welcome to GovMedCare</h2>
      <p>Access verified government-approved medicines safely and easily.</p>
    </section>

    <div class="stats-grid">
      <div class="stat-card">
        <div class="stat-icon teal">
          <svg width="22" height="22" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
            <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"/>
            <polyline points="22 4 12 14.01 9 11.01"/>
          </svg>
        </div>

        <div class="stat-info">
          <div class="label">Approved Medicines</div>
          <div class="value">${TotalApproved}</div>
        </div>
      </div>
    </div>

    <h2 class="section-title">Recently Available Medicines</h2>

    <div class="table-card">
      <c:choose>
        <c:when test="${empty medicine}">
          <div class="empty-state">No approved medicines available.</div>
        </c:when>

        <c:otherwise>
          <table>
            <thead>
            <tr>
              <th>#</th>
              <th>Image</th>
              <th>Name</th>
              <th>Price (NPR)</th>
              <th>Quantity</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="med" items="${medicine}" varStatus="loop">
              <tr>
                <td class="td-index">${loop.index + 1}</td>

                <td>
                  <c:choose>
                    <c:when test="${not empty med.imageURL}">
                      <img class="med-img"
                           src="${pageContext.request.contextPath}/${med.imageURL}"
                           alt="${med.name}">
                    </c:when>
                    <c:otherwise>
                      <div class="med-img-placeholder">No Image</div>
                    </c:otherwise>
                  </c:choose>
                </td>

                <td class="td-name">${med.name}</td>
                <td>NPR ${med.price}</td>

                <td>
                  <c:choose>
                    <c:when test="${med.quantity == 0}">
                      <span class="stock-out">Out of Stock</span>
                    </c:when>
                    <c:when test="${med.quantity <= 10}">
                      <span class="stock-low">${med.quantity} Low</span>
                    </c:when>
                    <c:otherwise>
                      <span class="stock-ok">${med.quantity}</span>
                    </c:otherwise>
                  </c:choose>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </c:otherwise>
      </c:choose>
    </div>

    <div class="page-actions">
      <a href="${pageContext.request.contextPath}/patient/medicines" class="btn-submit">
        View All Medicines
      </a>
    </div>

  </div>
</div>

</body>
</html>
