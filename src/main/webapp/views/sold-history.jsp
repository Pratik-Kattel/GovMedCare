<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/17/2026
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sales History – GovMediCare</title>

    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700&display=swap" rel="stylesheet">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/views/CSS/supplier.css">
</head>
<body>

<aside class="sidebar">
    <div class="sidebar-logo"><span>GovMedi<em>Care</em></span></div>
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
    </nav>

    <div class="sidebar-footer">
        GovMediCare © 2025
    </div>
</aside>

<div class="main">

    <div class="topbar">
        <h1>Sales History</h1>
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

        <div class="sales-summary-card">
            <h2>Total Sold Medicines</h2>
            <p>${soldHistory.size()} Items Sold</p>
        </div>

        <div class="table-card">

            <c:choose>

                <c:when test="${empty soldHistory}">
                    <div class="empty-state">
                        <p>No sales history available.</p>
                    </div>
                </c:when>

                <c:otherwise>

                    <table>

                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Medicine</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                            <th>Patient</th>
                        </tr>
                        </thead>

                        <tbody>

                        <c:forEach var="sale" items="${soldHistory}" varStatus="loop">

                            <tr>

                                <td class="td-index">
                                        ${loop.index + 1}
                                </td>

                                <td class="td-name">
                                        ${sale.medicineName}
                                </td>

                                <td>
    <span class="badge badge-teal">
            ${sale.quantity}
    </span>
                                </td>

                                <td>
                                    NPR ${sale.price}
                                </td>

                                <td class="sales-total">
                                    NPR ${sale.price * sale.quantity}
                                </td>

                                <td>
                                    Order #${sale.orderId}
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