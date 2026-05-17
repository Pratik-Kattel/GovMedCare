<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/17/2026
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Approve Medicine – GovMediCare</title>
  <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/CSS/admin.css">
</head>
<body>

<%@ include file="admin-navbar.jsp" %>

<div class="main">
  <div class="topbar">
    <h1>Approve Medicine</h1>

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
        <c:when test="${empty medicines}">
          <div class="empty-state">No pending medicines found.</div>
        </c:when>

        <c:otherwise>
          <table>
            <thead>
            <tr>
              <th>#</th>
              <th>Image</th>
              <th>Medicine</th>
              <th>Description</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Created At</th>
              <th>Action</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="med" items="${medicines}" varStatus="loop">
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
                <td>${med.description}</td>
                <td>NPR ${med.price}</td>
                <td>${med.quantity}</td>
                <td>${med.created_at}</td>

                <td>
                  <form method="post"
                        action="${pageContext.request.contextPath}/admin/medicines">
                    <input type="hidden" name="medicine_id" value="${med.medicineID}">
                    <input type="hidden" name="action" value="approve">

                    <button type="submit" class="btn-approve">
                      Approve
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