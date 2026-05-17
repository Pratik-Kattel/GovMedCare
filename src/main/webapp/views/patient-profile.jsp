<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/17/2026
  Time: 11:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.govmedcare.model.User" %>

<%
  User loggedInUser = (User) session.getAttribute("loggedInUser");
  if (loggedInUser == null) {
    response.sendRedirect(request.getContextPath() + "/login");
    return;
  }
%>

<!DOCTYPE html>
<html>
<head>
  <title>Patient Profile</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/CSS/patient-profile.css">
</head>
<body>

<div class="container">
  <div class="card">
    <h2>My Profile</h2>

    <% if (session.getAttribute("success") != null) { %>
    <p class="success"><%= session.getAttribute("success") %></p>
    <% session.removeAttribute("success"); %>
    <% } %>

    <% if (session.getAttribute("error") != null) { %>
    <p class="error"><%= session.getAttribute("error") %></p>
    <% session.removeAttribute("error"); %>
    <% } %>

    <div class="profile-row">
      <span>Name:</span>
      <p><%= loggedInUser.getName() %></p>
    </div>

    <div class="profile-row">
      <span>Email:</span>
      <p><%= loggedInUser.getEmail() %></p>
    </div>

    <div class="profile-row">
      <span>Phone:</span>
      <p><%= loggedInUser.getPhone() %></p>
    </div>

    <div class="profile-row">
      <span>Address:</span>
      <p><%= loggedInUser.getAddress() %></p>
    </div>

    <a class="btn" href="${pageContext.request.contextPath}/patient/update-info">
      Update Profile
    </a>

    <a class="back" href="${pageContext.request.contextPath}/patient/dashboard">
      Back to Dashboard
    </a>
  </div>
</div>

</body>
</html>
