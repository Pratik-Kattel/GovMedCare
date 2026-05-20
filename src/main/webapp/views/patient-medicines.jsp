<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/16/2026
  Time: 9:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.govmedcare.model.User" %>

<%
    User loggedInUser = (User) session.getAttribute("loggedInUser");

    String userName = "Patient";
    String firstLetter = "P";

    if (loggedInUser != null && loggedInUser.getName() != null) {
        userName = loggedInUser.getName();
        firstLetter = userName.substring(0,1).toUpperCase();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>View Medicines – GovMediCare</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/CSS/patient.css">
</head>
<body>

<%@ include file="patient-navbar.jsp" %>

<div class="main">
    <div class="topbar">
        <h1>Available Medicines</h1>
        <div class="topbar-user">
            <div class="avatar"><%= firstLetter %></div>
            <span><%= userName %></span>
        </div>
    </div>

    <div class="content">

        <form method="get"
              action="${pageContext.request.contextPath}/patient/medicines"
              class="filter-bar">

            <label for="categorySelect">Filter by Category:</label>

            <select id="categorySelect" name="category_id" onchange="this.form.submit()">
                <option value="">All Categories</option>

                <c:forEach var="cat" items="${categories}">
                    <option value="${cat.category_id}"
                            <c:if test="${param.category_id == cat.category_id}">selected</c:if>>
                            ${cat.category_name}
                    </option>
                </c:forEach>
            </select>

            <label for="sortSelect">Sort by Price:</label>

            <select id="sortSelect" name="sort" onchange="this.form.submit()">
                <option value="">Default</option>
                <option value="low" <c:if test="${param.sort == 'low'}">selected</c:if>>
                    Low to High
                </option>
                <option value="high" <c:if test="${param.sort == 'high'}">selected</c:if>>
                    High to Low
                </option>
            </select>

            <c:if test="${not empty param.category_id || not empty param.sort}">
                <a href="${pageContext.request.contextPath}/patient/medicines" class="clear-btn">
                    ✕ Clear
                </a>
            </c:if>
        </form>

        <div class="result-summary">
            Showing <strong>${medicines.size()} medicine(s)</strong>
        </div>

        <div class="table-card">
            <c:choose>
                <c:when test="${empty medicines}">
                    <div class="empty-state">No medicines found.</div>
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

                                <td>
                                    <c:choose>
                                        <c:when test="${med.quantity == 0}">
                                            <button class="btn-disabled" disabled>Unavailable</button>
                                        </c:when>
                                        <c:otherwise>
                                            <form method="post"
                                                  action="${pageContext.request.contextPath}/patient/cart/add">
                                                <input type="hidden" name="medicine_id" value="${med.medicineID}">
                                                <input type="hidden" name="quantity" value="1">

                                                <button type="submit" class="btn-stock">
                                                    Add to Cart
                                                </button>
                                            </form>
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

    </div>
</div>

</body>
</html>