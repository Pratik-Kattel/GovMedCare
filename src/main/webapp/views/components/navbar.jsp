<%--
  Created by IntelliJ IDEA.
  User: Sumit
  Date: 5/3/2026
  Time: 8:53 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navbar</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>../CSS/navbar.css">
</head>
<body>
<div class="navbar">

    <div class="brand-name">govMedCare</div>

    <ul class="nav-links">
        <li><a href="<%= request.getContextPath() %>/adminDashboard.jsp">Dashboard</a></li>
        <li><a href="<%= request.getContextPath() %>/viewMedicine.jsp">View Medicine</a></li>
        <li><a href="<%= request.getContextPath() %>/addMedicine.jsp">Add Medicine</a></li>
    </ul>

</div>
</body>
</html>
