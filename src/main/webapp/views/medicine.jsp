<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Medicines – MediCare</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/CSS/supplier.css">
</head>
<body>

<aside class="sidebar">
    <div class="sidebar-logo"><span>GovMed<em>Care</em></span></div>
    <div class="sidebar-label">Supplier Panel</div>
    <nav>
        <a href="${pageContext.request.contextPath}/supplier/dashboard">
            <svg width="16" height="16" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/><rect x="14" y="14" width="7" height="7"/><rect x="3" y="14" width="7" height="7"/></svg>
            Dashboard
        </a>
        <a href="${pageContext.request.contextPath}/supplier/medicines/add">
            <svg width="16" height="16" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="16"/><line x1="8" y1="12" x2="16" y2="12"/></svg>
            Add Medicine
        </a>
        <a href="${pageContext.request.contextPath}/supplier/medicines" class="active">
            <svg width="16" height="16" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><path d="M9 3H5a2 2 0 0 0-2 2v4m6-6h10a2 2 0 0 1 2 2v4M9 3v18m0 0h10a2 2 0 0 0 2-2V9M9 21H5a2 2 0 0 1-2-2V9m0 0h18"/></svg>
            View Medicines
        </a>
        <a href="${pageContext.request.contextPath}//supplier/viewsoldhistory" class="active">
            <svg width="16" height="16" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><path d="M9 3H5a2 2 0 0 0-2 2v4m6-6h10a2 2 0 0 1 2 2v4M9 3v18m0 0h10a2 2 0 0 0 2-2V9M9 21H5a2 2 0 0 1-2-2V9m0 0h18"/></svg>
            Sales History
        </a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </nav>
    <div class="sidebar-footer">GovMedCare © 2026</div>
</aside>

<div class="main">
    <div class="topbar">
        <h1>Medicine Inventory</h1>
        <div class="topbar-user">
            <div class="avatar">S</div>
            <span>Supplier</span>
        </div>
    </div>

    <div class="content">

        <% if (session.getAttribute("success") != null) { %>
        <div class="alert alert-success">
            <%= session.getAttribute("success") %>
        </div>
        <% session.removeAttribute("success"); %>
        <% } %>

        <% if (session.getAttribute("error") != null) { %>
        <div class="alert alert-error">
            <%= session.getAttribute("error") %>
        </div>
        <% session.removeAttribute("error"); %>
        <% } %>

        <form method="get" action="${pageContext.request.contextPath}/supplier/medicines" class="filter-bar">
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
            <c:if test="${not empty param.category_id}">
                <a href="${pageContext.request.contextPath}/supplier/medicines" class="clear-btn">✕ Clear</a>
            </c:if>
        </form>

        <div class="result-summary">
            Showing <strong>${medicines.size()} medicine(s)</strong>
            <c:if test="${not empty param.category_id}"> in selected category</c:if>
        </div>

        <div class="table-card">
            <c:choose>
                <c:when test="${empty medicines}">
                    <div class="empty-state">
                        <svg width="56" height="56" fill="none" stroke="currentColor" stroke-width="1.5" viewBox="0 0 24 24"><path d="M9 3H5a2 2 0 0 0-2 2v4m6-6h10a2 2 0 0 1 2 2v4M9 3v18m0 0h10a2 2 0 0 0 2-2V9M9 21H5a2 2 0 0 1-2-2V9m0 0h18"/></svg>
                        <p>No medicines found for this category.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Image</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Price (NPR)</th>
                            <th>Quantity</th>
                            <th>Status</th>
                            <th>Update Stock</th>
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
                                                 alt="${med.name}"
                                                 onerror="this.style.display='none';this.nextElementSibling.style.display='grid';">
                                            <div class="med-img-placeholder" style="display:none;">
                                                <svg width="20" height="20" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><rect x="3" y="3" width="18" height="18" rx="2"/><circle cx="8.5" cy="8.5" r="1.5"/><polyline points="21 15 16 10 5 21"/></svg>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="med-img-placeholder">
                                                <svg width="20" height="20" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><rect x="3" y="3" width="18" height="18" rx="2"/><circle cx="8.5" cy="8.5" r="1.5"/><polyline points="21 15 16 10 5 21"/></svg>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="td-name">${med.name}</td>
                                <td><span class="badge badge-teal">${med.category_name}</span></td>
                                <td>NPR ${med.price}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${med.quantity == 0}">
                                            <span class="stock-out">Out of Stock</span>
                                        </c:when>
                                        <c:when test="${med.quantity <= 10}">
                                            <span class="stock-low">${med.quantity} (Low)</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="stock-ok">${med.quantity}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${med.is_verified}">
                                            <span class="badge badge-teal">Approved</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-amber">Pending</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <form method="post"
                                          action="${pageContext.request.contextPath}/supplier/medicines/updateStock"
                                          class="stock-update-form">

                                        <input type="hidden" name="medicine_ID" value="${med.medicineID}">

                                        <input type="number"
                                               name="quantity"
                                               min="1"
                                               placeholder="Qty"
                                               required
                                               class="stock-input">

                                        <button type="submit" class="btn-stock">
                                            Update
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
