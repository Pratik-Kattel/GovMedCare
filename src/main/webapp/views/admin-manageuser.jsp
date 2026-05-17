<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/17/2026
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Manage Users – GovMediCare</title>
  <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/CSS/admin.css">
</head>
<body>

<%@ include file="admin-navbar.jsp" %>

<div class="main">
  <div class="topbar">
    <h1>Manage Users</h1>

    <div class="topbar-user">
      <div class="avatar">A</div>
      <span>Admin</span>
    </div>
  </div>

  <div class="content">

    <c:if test="${not empty sessionScope.success}">
      <div class="alert alert-success">${sessionScope.success}</div>
      <c:remove var="success" scope="session"/>
    </c:if>

    <c:if test="${not empty sessionScope.error}">
      <div class="alert alert-error">${sessionScope.error}</div>
      <c:remove var="error" scope="session"/>
    </c:if>

    <div class="table-card">
      <c:choose>
        <c:when test="${empty users}">
          <div class="empty-state">No users found.</div>
        </c:when>

        <c:otherwise>
          <table>
            <thead>
            <tr>
              <th>#</th>
              <th>User ID</th>
              <th>Name</th>
              <th>Phone</th>
              <th>Address</th>
              <th>Action</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="user" items="${users}" varStatus="loop">
              <tr>
                <td class="td-index">${loop.index + 1}</td>
                <td>${user.id}</td>
                <td class="td-name">${user.name}</td>
                <td>${user.phone}</td>
                <td>${user.address}</td>
                <td>
                  <form method="post"
                        action="${pageContext.request.contextPath}/admin/block-users">
                    <input type="hidden" name="user_id" value="${user.id}">

                    <button type="submit" class="btn-delete">
                      Block
                    </button>
                  </form>
                </td>
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