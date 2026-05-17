<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/17/2026
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Supplier Dashboard – MediCare</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/CSS/supplier.css">
</head>
<body>

<aside class="sidebar">
    <div class="sidebar-logo"><span>GovMedi<em>Care</em></span></div>
    <div class="sidebar-label">Supplier Panel</div>
    <nav>
        <a href="${pageContext.request.contextPath}/supplier/dashboard" class="active">
            <svg width="16" height="16" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/><rect x="14" y="14" width="7" height="7"/><rect x="3" y="14" width="7" height="7"/></svg>
            Dashboard
        </a>
        <a href="${pageContext.request.contextPath}/supplier/medicines/add">
            <svg width="16" height="16" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="16"/><line x1="8" y1="12" x2="16" y2="12"/></svg>
            Add Medicine
        </a>
        <a href="${pageContext.request.contextPath}/supplier/medicines">
            <svg width="16" height="16" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><path d="M9 3H5a2 2 0 0 0-2 2v4m6-6h10a2 2 0 0 1 2 2v4M9 3v18m0 0h10a2 2 0 0 0 2-2V9M9 21H5a2 2 0 0 1-2-2V9m0 0h18"/></svg>
            View Medicines
        </a>
        <a href="${pageContext.request.contextPath}//supplier/viewsoldhistory" class="active">
            <svg width="16" height="16" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><path d="M9 3H5a2 2 0 0 0-2 2v4m6-6h10a2 2 0 0 1 2 2v4M9 3v18m0 0h10a2 2 0 0 0 2-2V9M9 21H5a2 2 0 0 1-2-2V9m0 0h18"/></svg>
            Sales History
        </a>
    </nav>
    <div class="sidebar-footer">GovMediCare © 2025</div>
</aside>

<div class="main">
    <div class="topbar">
        <h1>Dashboard</h1>
        <div class="topbar-user">
            <div class="avatar">S</div>
            <span>Supplier</span>
        </div>
    </div>

    <div class="content">

        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-icon teal">
                    <svg width="22" height="22" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"/><polyline points="22 4 12 14.01 9 11.01"/></svg>
                </div>
                <div class="stat-info">
                    <div class="label">Approved Medicines</div>
                    <div class="value">${approvedCount}</div>
                </div>
            </div>
            <div class="stat-card">
                <div class="stat-icon amber">
                    <svg width="22" height="22" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/></svg>
                </div>
                <div class="stat-info">
                    <div class="label">Pending Medicines</div>
                    <div class="value">${pendingCount}</div>
                </div>
            </div>
            <div class="stat-card">
                <div class="stat-icon red">
                    <svg width="22" height="22" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"/></svg>
                </div>
                <div class="stat-info">
                    <div class="label">Total Medicines</div>
                    <div class="value">${approvedCount + pendingCount}</div>
                </div>
            </div>
        </div>

        <p class="section-title">Quick Actions</p>
        <div class="actions-grid">
            <a class="action-card" href="${pageContext.request.contextPath}/supplier/medicines/add">
                <div class="action-icon">
                    <svg width="22" height="22" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="16"/><line x1="8" y1="12" x2="16" y2="12"/></svg>
                </div>
                <div>
                    <h3>Add Medicine</h3>
                    <p>Submit a new medicine with category, price, quantity, and image.</p>
                </div>
                <div class="action-arrow">→</div>
            </a>
            <a class="action-card" href="${pageContext.request.contextPath}/supplier/medicines">
                <div class="action-icon">
                    <svg width="22" height="22" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><path d="M9 3H5a2 2 0 0 0-2 2v4m6-6h10a2 2 0 0 1 2 2v4M9 3v18m0 0h10a2 2 0 0 0 2-2V9M9 21H5a2 2 0 0 1-2-2V9m0 0h18"/></svg>
                </div>
                <div>
                    <h3>View Medicines</h3>
                    <p>Browse and filter your submitted medicines by category.</p>
                </div>
                <div class="action-arrow">→</div>
            </a>
        </div>

    </div>
</div>

</body>
</html>
