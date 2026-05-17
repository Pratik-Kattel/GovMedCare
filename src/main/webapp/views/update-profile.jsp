<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/17/2026
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.govmedcare.model.User" %>

<%
  User loggedInUser = (User) request.getAttribute("loggedInUser");
  if (loggedInUser == null) {
    loggedInUser = (User) session.getAttribute("loggedInUser");
  }
%>

<!DOCTYPE html>
<html>
<head>
  <title>Update Profile</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views//CSS/patient-profile.css">
</head>
<body>

<div class="container">
  <div class="card">
    <h2>Update Profile</h2>

    <form action="${pageContext.request.contextPath}/patient/update-info" method="post">

      <label>Name</label>
      <input type="text" name="name" value="<%= loggedInUser.getName() %>" required>

      <label>Phone</label>
      <input type="text" name="phone" value="<%= loggedInUser.getPhone() %>" required>

      <label>Address</label>
      <input type="text" name="address" value="<%= loggedInUser.getAddress() %>" required>

      <button type="submit" class="btn">Save Changes</button>
    </form>

    <a class="back" href="${pageContext.request.contextPath}/patient/profile">
      Cancel
    </a>
  </div>
</div>

</body>
</html>
