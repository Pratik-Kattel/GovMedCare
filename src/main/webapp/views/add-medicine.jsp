<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Medicine – MediCare</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/CSS/supplier.css">
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
        <a href="${pageContext.request.contextPath}/supplier/medicines/add" class="active">
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
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </nav>
    <div class="sidebar-footer">GovMediCare © 2025</div>
</aside>

<div class="main">
    <div class="topbar">
        <h1>Add Medicine</h1>
        <div class="topbar-user">
            <div class="avatar">S</div>
            <span>Supplier</span>
        </div>
    </div>

    <div class="content">

        <c:if test="${not empty error}">
            <div class="alert alert-error">
                <svg width="16" height="16" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/></svg>
                    ${error}
            </div>
        </c:if>

        <c:if test="${not empty sessionScope.success}">
            <div class="alert alert-success">
                <svg width="16" height="16" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"/><polyline points="22 4 12 14.01 9 11.01"/></svg>
                    ${sessionScope.success}
            </div>
            <c:remove var="success" scope="session"/>
        </c:if>

        <div class="form-card">
            <h2>New Medicine Entry</h2>
            <p class="subtitle">Fill in the details below. The medicine will be reviewed before going live.</p>

            <form action="${pageContext.request.contextPath}/supplier/medicines" method="post" enctype="multipart/form-data">
                <div class="form-grid">

                    <div class="form-group full">
                        <label for="medicineName">Medicine Name</label>
                        <input type="text" id="medicineName" name="medicineName"
                               placeholder="e.g. Paracetamol 500mg" required>
                    </div>

                    <div class="form-group">
                        <label for="category_id">Category</label>
                        <select id="category_id" name="category_id" required>
                            <option value="" disabled selected>Select a category</option>
                            <c:forEach var="cat" items="${categories}">
                                <option value="${cat.category_id}">${cat.category_name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="price">Price (NPR)</label>
                        <input type="number" id="price" name="price"
                               placeholder="0.00" min="0" step="0.01" required>
                    </div>

                    <div class="form-group">
                        <label for="quantity">Quantity</label>
                        <input type="number" id="quantity" name="quantity"
                               placeholder="0" min="0" required>
                    </div>

                    <div class="form-group full">
                        <label for="description">Description</label>
                        <textarea id="description" name="description"
                                  placeholder="Brief description of the medicine, usage, and warnings…" required></textarea>
                    </div>

                    <div class="form-group full">
                        <label>Product Image</label>
                        <div class="file-upload-area">
                            <input type="file" name="image" id="imageInput" accept="image/*">


                            <img id="previewImg"
                                 style="display:none; max-width:100%; height:150px; object-fit:cover; border-radius:10px; margin-top:10px;" />


                            <div id="uploadText">
                                <svg width="32" height="32" fill="none" stroke="currentColor" stroke-width="1.5" viewBox="0 0 24 24">
                                    <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"/>
                                    <polyline points="17 8 12 3 7 8"/>
                                    <line x1="12" y1="3" x2="12" y2="15"/>
                                </svg>
                                <p><strong>Click to upload</strong> or drag and drop</p>
                                <p>PNG, JPG up to 10MB</p>
                            </div>


                            <p id="imageMsg" style="display:none; color:green; margin-top:8px;">
                                Image selected successfully !!!
                            </p>
                        </div>
                    </div>
                </div>

                <hr class="divider">

                <div class="form-actions">
                    <button type="submit" class="btn-submit">Submit Medicine</button>
                    <a href="${pageContext.request.contextPath}/supplier/medicines" class="btn-cancel">Cancel</a>
                </div>
            </form>
        </div>

    </div>
</div>
<script>
    document.getElementById("imageInput").addEventListener("change", function(event) {
        const file = event.target.files[0];

        if (file) {
            // Show message
            document.getElementById("imageMsg").style.display = "block";


            const reader = new FileReader();
            reader.onload = function(e) {
                const img = document.getElementById("previewImg");
                img.src = e.target.result;
                img.style.display = "block";

                document.getElementById("uploadText").style.display = "none";
            };
            reader.readAsDataURL(file);
        }
    });
</script>
</body>
</html>
