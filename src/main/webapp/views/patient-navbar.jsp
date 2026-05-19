<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/16/2026
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<aside class="sidebar">
  <div class="sidebar-logo">
    <span>GovMed<em>Care</em></span>
  </div>

  <div class="sidebar-label">Patient Panel</div>

  <nav>
    <a href="${pageContext.request.contextPath}/patient/dashboard">Home</a>
    <a href="${pageContext.request.contextPath}/patient/medicines">View Medicines</a>
    <a href="${pageContext.request.contextPath}/patient/cart">My Cart</a>
    <a href="${pageContext.request.contextPath}/patient/purchase-history">My Purchases</a>
    <a href="${pageContext.request.contextPath}/about">About Us</a>
    <a href="${pageContext.request.contextPath}/patient/profile">Profile</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
  </nav>

  <div class="sidebar-footer">GovMedCare © 2026</div>
</aside>

</body>
</html>
