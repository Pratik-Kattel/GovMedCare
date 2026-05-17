<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/17/2026
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<<aside class="sidebar">
    <div class="sidebar-logo">
        <span>GovMedi<em>Care</em></span>
    </div>

    <div class="sidebar-label">Admin Panel</div>

    <nav>
        <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>

        <a href="${pageContext.request.contextPath}/admin/medicines">Approve Medicine</a>

        <a href="${pageContext.request.contextPath}/admin/approved-medicines">Monitor Medicine</a>

        <a href="${pageContext.request.contextPath}/admin/block-users">Manage Users</a>

        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </nav>

    <div class="sidebar-footer">GovMediCare © 2025</div>
</aside>

</body>
</html>
