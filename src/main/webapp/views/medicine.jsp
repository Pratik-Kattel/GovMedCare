<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    com.govmedcare.model.User loggedInUser = (com.govmedcare.model.User) session.getAttribute("loggedInUser");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MediCare – Supplier Panel</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700&family=Sora:wght@400;600;700&display=swap" rel="stylesheet">
    <style>
        *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }

        :root {
            --teal:       #1A9E8F;
            --teal-dark:  #147A6E;
            --teal-light: #E6F7F5;
            --accent:     #FF6B35;
            --gray-50:    #F8FAFB;
            --gray-100:   #F0F4F5;
            --gray-200:   #E2E8EA;
            --gray-400:   #9AABB0;
            --gray-600:   #5A6D72;
            --gray-800:   #1E2E33;
            --white:      #FFFFFF;
            --shadow-sm:  0 1px 3px rgba(0,0,0,.06), 0 1px 2px rgba(0,0,0,.04);
            --shadow-md:  0 4px 16px rgba(0,0,0,.08);
            --shadow-lg:  0 8px 32px rgba(0,0,0,.12);
            --radius:     14px;
        }

        body { font-family: 'DM Sans', sans-serif; background: var(--gray-50); color: var(--gray-800); min-height: 100vh; }

        /* ── NAV ── */
        nav {
            background: var(--white);
            border-bottom: 1px solid var(--gray-200);
            position: sticky; top: 0; z-index: 100;
            box-shadow: var(--shadow-sm);
        }
        .nav-inner {
            max-width: 1280px; margin: 0 auto;
            display: flex; align-items: center; justify-content: space-between;
            padding: 0 28px; height: 66px;
        }
        .brand { display: flex; align-items: center; gap: 10px; text-decoration: none; }
        .brand-icon {
            width: 36px; height: 36px; background: var(--teal);
            border-radius: 10px; display: grid; place-items: center;
        }
        .brand-icon svg { width: 20px; height: 20px; fill: white; }
        .brand-name { font-family: 'Sora', sans-serif; font-size: 1.15rem; font-weight: 700; color: var(--gray-800); }
        .brand-badge {
            font-size: .65rem; font-weight: 600; letter-spacing: .05em;
            background: var(--teal-light); color: var(--teal-dark);
            padding: 2px 8px; border-radius: 20px; text-transform: uppercase;
        }

        .nav-links { display: flex; gap: 4px; }
        .nav-link {
            font-size: .9rem; font-weight: 500; color: var(--gray-600);
            padding: 6px 14px; border-radius: 8px; text-decoration: none;
            transition: background .15s, color .15s;
        }
        .nav-link:hover { background: var(--gray-100); color: var(--gray-800); }
        .nav-link.active { color: var(--teal); font-weight: 600; }

        .nav-right { display: flex; align-items: center; gap: 16px; }
        .nav-user { display: flex; align-items: center; gap: 10px; }
        .nav-user-info { text-align: right; }
        .nav-user-name { font-size: .875rem; font-weight: 600; color: var(--gray-800); }
        .nav-user-role { font-size: .72rem; color: var(--teal); font-weight: 500; text-transform: uppercase; letter-spacing: .04em; }
        .avatar {
            width: 36px; height: 36px; border-radius: 50%;
            background: linear-gradient(135deg, var(--teal) 0%, var(--teal-dark) 100%);
            display: grid; place-items: center; font-weight: 700; font-size: .85rem; color: white;
        }
        .btn-logout {
            font-size: .8rem; font-weight: 600; color: var(--gray-600);
            padding: 5px 12px; border: 1px solid var(--gray-200);
            border-radius: 8px; text-decoration: none; transition: all .15s;
        }
        .btn-logout:hover { border-color: var(--gray-400); color: var(--gray-800); }

        /* ── LAYOUT ── */
        .page { max-width: 1280px; margin: 0 auto; padding: 36px 28px; display: flex; gap: 28px; }

        /* ── SIDEBAR ── */
        .sidebar { width: 240px; flex-shrink: 0; }
        .sidebar-card {
            background: var(--white); border-radius: var(--radius);
            border: 1px solid var(--gray-200); padding: 20px;
            box-shadow: var(--shadow-sm); margin-bottom: 16px;
        }
        .sidebar-title { font-size: .8rem; font-weight: 700; letter-spacing: .06em; text-transform: uppercase; color: var(--gray-400); margin-bottom: 12px; }
        .category-item {
            display: flex; align-items: center; justify-content: space-between;
            padding: 8px 10px; border-radius: 8px; cursor: pointer;
            transition: background .15s; text-decoration: none; color: inherit;
            margin-bottom: 2px;
        }
        .category-item:hover { background: var(--gray-100); }
        .category-item.active { background: var(--teal-light); }
        .category-item.active .cat-name { color: var(--teal-dark); font-weight: 600; }
        .cat-left { display: flex; align-items: center; gap: 10px; }
        .cat-dot { width: 8px; height: 8px; border-radius: 50%; background: var(--gray-400); flex-shrink: 0; }
        .category-item.active .cat-dot { background: var(--teal); }
        .cat-name { font-size: .875rem; font-weight: 500; color: var(--gray-700); }
        .cat-count {
            font-size: .72rem; font-weight: 600; color: var(--gray-400);
            background: var(--gray-100); padding: 1px 7px; border-radius: 20px;
        }
        .category-item.active .cat-count { background: var(--teal); color: white; }

        .btn-add-medicine {
            display: flex; align-items: center; justify-content: center; gap: 8px;
            width: 100%; padding: 11px; background: var(--teal); color: white;
            border: none; border-radius: 10px; font-family: inherit;
            font-size: .875rem; font-weight: 600; cursor: pointer;
            transition: background .15s, transform .1s; text-decoration: none;
        }
        .btn-add-medicine:hover { background: var(--teal-dark); transform: translateY(-1px); }

        /* ── MAIN ── */
        .main { flex: 1; min-width: 0; }
        .main-header { display: flex; align-items: flex-end; justify-content: space-between; margin-bottom: 24px; }
        .main-title { font-family: 'Sora', sans-serif; font-size: 1.6rem; font-weight: 700; color: var(--gray-800); line-height: 1.1; }
        .main-subtitle { font-size: .875rem; color: var(--gray-400); margin-top: 4px; }

        .toolbar { display: flex; align-items: center; gap: 12px; margin-bottom: 20px; }
        .search-wrap { position: relative; flex: 1; }
        .search-wrap svg { position: absolute; left: 12px; top: 50%; transform: translateY(-50%); color: var(--gray-400); pointer-events: none; }
        .search-input {
            width: 100%; padding: 9px 12px 9px 38px;
            border: 1px solid var(--gray-200); border-radius: 10px;
            font-family: inherit; font-size: .875rem; color: var(--gray-800);
            background: var(--white); transition: border-color .15s, box-shadow .15s;
        }
        .search-input:focus { outline: none; border-color: var(--teal); box-shadow: 0 0 0 3px rgba(26,158,143,.1); }
        .search-input::placeholder { color: var(--gray-400); }

        .result-bar { display: flex; align-items: center; justify-content: space-between; margin-bottom: 18px; }
        .result-text { font-size: .85rem; color: var(--gray-600); }
        .result-text strong { color: var(--gray-800); }

        /* ── GRID ── */
        .med-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(240px, 1fr)); gap: 20px; }
        .med-card {
            background: var(--white); border-radius: var(--radius);
            border: 1px solid var(--gray-200); overflow: hidden;
            box-shadow: var(--shadow-sm); transition: box-shadow .2s, transform .2s;
            display: flex; flex-direction: column;
        }
        .med-card:hover { box-shadow: var(--shadow-md); transform: translateY(-2px); }

        .card-img-wrap {
            position: relative; height: 150px; background: var(--gray-100);
            display: flex; align-items: center; justify-content: center; overflow: hidden;
        }
        .card-img-wrap img { max-width: 90%; max-height: 90%; object-fit: contain; }
        .card-img-placeholder { font-size: 2.5rem; opacity: .25; }
        .stock-badge {
            position: absolute; top: 10px; right: 10px;
            font-size: .65rem; font-weight: 700; letter-spacing: .06em; text-transform: uppercase;
            padding: 3px 8px; border-radius: 20px;
        }
        .stock-in    { background: #D1FAE5; color: #065F46; }
        .stock-low   { background: #FEF3C7; color: #92400E; }
        .stock-out   { background: #FEE2E2; color: #991B1B; }

        .card-body { padding: 14px 16px 16px; flex: 1; display: flex; flex-direction: column; }
        .card-cat { font-size: .65rem; font-weight: 700; letter-spacing: .07em; text-transform: uppercase; color: var(--teal); margin-bottom: 4px; }
        .card-name { font-family: 'Sora', sans-serif; font-size: 1rem; font-weight: 700; color: var(--gray-800); margin-bottom: 5px; line-height: 1.25; }
        .card-desc { font-size: .78rem; color: var(--gray-600); line-height: 1.5; flex: 1; margin-bottom: 12px; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; }
        .card-footer { display: flex; align-items: center; justify-content: space-between; }
        .price-label { font-size: .65rem; font-weight: 600; letter-spacing: .05em; text-transform: uppercase; color: var(--gray-400); }
        .price-val { font-family: 'Sora', sans-serif; font-size: 1.05rem; font-weight: 700; color: var(--teal-dark); }
        .qty-badge {
            font-size: .7rem; font-weight: 600; color: var(--gray-400);
            background: var(--gray-100); padding: 3px 8px; border-radius: 6px;
        }

        /* ── MODAL ── */
        .modal-overlay {
            display: none; position: fixed; inset: 0;
            background: rgba(0,0,0,.45); z-index: 200;
            align-items: center; justify-content: center;
        }
        .modal-overlay.open { display: flex; animation: fadeIn .2s ease; }
        @keyframes fadeIn { from { opacity: 0; } to { opacity: 1; } }
        .modal {
            background: var(--white); border-radius: 18px;
            width: min(540px, 95vw); max-height: 90vh; overflow-y: auto;
            box-shadow: var(--shadow-lg); animation: slideUp .25s ease;
        }
        @keyframes slideUp { from { transform: translateY(30px); opacity: 0; } to { transform: translateY(0); opacity: 1; } }
        .modal-header {
            padding: 22px 24px 18px; border-bottom: 1px solid var(--gray-200);
            display: flex; align-items: center; justify-content: space-between; position: sticky; top: 0; background: white; border-radius: 18px 18px 0 0;
        }
        .modal-title { font-family: 'Sora', sans-serif; font-size: 1.1rem; font-weight: 700; }
        .modal-close {
            width: 32px; height: 32px; border-radius: 8px; border: none;
            background: var(--gray-100); cursor: pointer; font-size: 1.1rem;
            display: grid; place-items: center; transition: background .15s;
        }
        .modal-close:hover { background: var(--gray-200); }
        .modal-body { padding: 24px; }

        .form-group { margin-bottom: 18px; }
        .form-label { font-size: .8rem; font-weight: 600; color: var(--gray-600); margin-bottom: 6px; display: block; }
        .form-label span { color: var(--accent); }
        .form-control {
            width: 100%; padding: 9px 12px; border: 1px solid var(--gray-200);
            border-radius: 9px; font-family: inherit; font-size: .875rem; color: var(--gray-800);
            transition: border-color .15s, box-shadow .15s;
        }
        .form-control:focus { outline: none; border-color: var(--teal); box-shadow: 0 0 0 3px rgba(26,158,143,.1); }
        .form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 14px; }
        textarea.form-control { resize: vertical; min-height: 80px; }

        .img-upload-area {
            border: 2px dashed var(--gray-200); border-radius: 10px;
            padding: 24px; text-align: center; cursor: pointer;
            transition: border-color .15s, background .15s;
        }
        .img-upload-area:hover { border-color: var(--teal); background: var(--teal-light); }
        .img-upload-area input { display: none; }
        .upload-icon { font-size: 2rem; margin-bottom: 6px; opacity: .4; }
        .upload-text { font-size: .8rem; color: var(--gray-400); }
        .upload-text strong { color: var(--teal); }

        .alert {
            padding: 10px 14px; border-radius: 8px; font-size: .85rem; margin-bottom: 16px;
        }
        .alert-success { background: #D1FAE5; color: #065F46; border: 1px solid #6EE7B7; }
        .alert-error   { background: #FEE2E2; color: #991B1B; border: 1px solid #FCA5A5; }

        .btn-submit {
            width: 100%; padding: 12px; background: var(--teal); color: white;
            border: none; border-radius: 10px; font-family: inherit;
            font-size: .9rem; font-weight: 600; cursor: pointer;
            transition: background .15s; margin-top: 4px;
        }
        .btn-submit:hover { background: var(--teal-dark); }

        /* ── EMPTY STATE ── */
        .empty-state {
            grid-column: 1/-1; padding: 60px 20px; text-align: center; color: var(--gray-400);
        }
        .empty-icon { font-size: 3rem; margin-bottom: 12px; opacity: .4; }
        .empty-title { font-size: 1rem; font-weight: 600; color: var(--gray-600); }
        .empty-sub { font-size: .85rem; margin-top: 4px; }

        /* ── RESPONSIVE ── */
        @media (max-width: 768px) {
            .page { flex-direction: column; padding: 20px 16px; }
            .sidebar { width: 100%; }
            .form-row { grid-template-columns: 1fr; }
        }
    </style>
</head>
<body>

<!-- ════════════════════ NAV ════════════════════ -->
<nav>
    <div class="nav-inner">
        <a href="${pageContext.request.contextPath}/" class="brand">
            <div class="brand-icon">
                <svg viewBox="0 0 24 24"><path d="M19 3H5a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V5a2 2 0 0 0-2-2zm-7 3a1 1 0 0 1 1 1v3h3a1 1 0 0 1 0 2h-3v3a1 1 0 0 1-2 0v-3H8a1 1 0 0 1 0-2h3V7a1 1 0 0 1 1-1z"/></svg>
            </div>
            <span class="brand-name">MediCare</span>
            <span class="brand-badge">Supplier</span>
        </a>

        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/" class="nav-link">Home</a>
            <a href="${pageContext.request.contextPath}/supplier/medicine" class="nav-link active">Medicines</a>
            <a href="${pageContext.request.contextPath}/about" class="nav-link">About</a>
        </div>

        <div class="nav-right">
            <div class="nav-user">
                <div class="nav-user-info">
                    <div class="nav-user-name"><%= loggedInUser != null ? loggedInUser.getName() : "Supplier" %></div>
                    <div class="nav-user-role">Supplier</div>
                </div>
                <div class="avatar"><%= loggedInUser != null ? String.valueOf(loggedInUser.getName().charAt(0)).toUpperCase() : "S" %></div>
            </div>
            <a href="${pageContext.request.contextPath}/logout" class="btn-logout">Logout</a>
        </div>
    </div>
</nav>

<!-- ════════════════════ PAGE ════════════════════ -->
<div class="page">

    <!-- SIDEBAR -->
    <aside class="sidebar">
        <div class="sidebar-card">
            <button class="btn-add-medicine" onclick="openModal()">
                <svg width="16" height="16" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" d="M12 5v14M5 12h14"/></svg>
                Add New Medicine
            </button>
        </div>

        <div class="sidebar-card">
            <div class="sidebar-title">Categories</div>

            <a href="${pageContext.request.contextPath}/supplier/medicine"
               class="category-item ${param.category_id == null || param.category_id == '' ? 'active' : ''}">
                <div class="cat-left">
                    <div class="cat-dot"></div>
                    <span class="cat-name">All Medicines</span>
                </div>
                <span class="cat-count">${medicines.size()}</span>
            </a>

            <c:forEach var="cat" items="${categories}">
                <a href="${pageContext.request.contextPath}/supplier/medicine?category_id=${cat.categoryId}"
                   class="category-item ${param.category_id == cat.categoryId ? 'active' : ''}">
                    <div class="cat-left">
                        <div class="cat-dot"></div>
                        <span class="cat-name">${cat.name}</span>
                    </div>
                </a>
            </c:forEach>
        </div>
    </aside>

    <!-- MAIN CONTENT -->
    <main class="main">
        <div class="main-header">
            <div>
                <div class="main-title">Medicine Catalog</div>
                <div class="main-subtitle">Manage and list government-funded medications.</div>
            </div>
        </div>

        <!-- Alerts -->
        <c:if test="${not empty sessionScope.success}">
            <div class="alert alert-success">✓ ${sessionScope.success}</div>
            <c:remove var="success" scope="session"/>
        </c:if>
        <c:if test="${not empty requestScope.error}">
            <div class="alert alert-error">⚠ ${requestScope.error}</div>
        </c:if>
        <c:if test="${not empty requestScope.Failed}">
            <div class="alert alert-error">⚠ ${requestScope.Failed}</div>
        </c:if>

        <!-- Toolbar -->
        <div class="toolbar">
            <div class="search-wrap">
                <svg width="15" height="15" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"><circle cx="11" cy="11" r="8"/><path stroke-linecap="round" d="m21 21-4.35-4.35"/></svg>
                <input type="text" class="search-input" placeholder="Search by medicine name..." id="searchInput" oninput="filterCards()">
            </div>
        </div>

        <div class="result-bar">
            <span class="result-text">Showing <strong id="visibleCount">${medicines.size()}</strong> medicines</span>
        </div>

        <!-- Medicine Grid -->
        <div class="med-grid" id="medGrid">
            <c:choose>
                <c:when test="${empty medicines}">
                    <div class="empty-state">
                        <div class="empty-icon">💊</div>
                        <div class="empty-title">No medicines found</div>
                        <div class="empty-sub">Add a medicine to get started.</div>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="med" items="${medicines}">
                        <div class="med-card" data-name="${med.name.toLowerCase()}">
                            <div class="card-img-wrap">
                                <c:choose>
                                    <c:when test="${not empty med.imageURL}">
                                        <img src="${pageContext.request.contextPath}/uploads/${med.imageURL}" alt="${med.name}" onerror="this.style.display='none';this.nextElementSibling.style.display='block'">
                                        <div class="card-img-placeholder" style="display:none">💊</div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="card-img-placeholder">💊</div>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${med.quantity == 0}">
                                        <span class="stock-badge stock-out">Out of Stock</span>
                                    </c:when>
                                    <c:when test="${med.quantity <= 10}">
                                        <span class="stock-badge stock-low">Low Stock</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="stock-badge stock-in">In Stock</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div class="card-body">
                                <div class="card-cat">${med.category_name}</div>
                                <div class="card-name">${med.name}</div>
                                <div class="card-desc">${med.description}</div>
                                <div class="card-footer">
                                    <div>
                                        <div class="price-label">Citizen Price</div>
                                        <div class="price-val">NPR <fmt:formatNumber value="${med.price}" pattern="#,##0"/></div>
                                    </div>
                                    <div class="qty-badge">Qty: ${med.quantity}</div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
</div>

<!-- ════════════════════ MODAL ════════════════════ -->
<div class="modal-overlay" id="modalOverlay" onclick="closeOnBackdrop(event)">
    <div class="modal">
        <div class="modal-header">
            <span class="modal-title">Add New Medicine</span>
            <button class="modal-close" onclick="closeModal()">✕</button>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/supplier/medicine" method="post" enctype="multipart/form-data">

                <div class="form-group">
                    <label class="form-label">Medicine Name <span>*</span></label>
                    <input type="text" name="medicineName" class="form-control" placeholder="e.g. Paracetamol 500mg" required>
                </div>

                <div class="form-group">
                    <label class="form-label">Description <span>*</span></label>
                    <textarea name="description" class="form-control" placeholder="Brief description of the medicine..." required></textarea>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label class="form-label">Price (NPR) <span>*</span></label>
                        <input type="number" name="price" class="form-control" placeholder="0.00" min="0" step="0.01" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Quantity <span>*</span></label>
                        <input type="number" name="quantity" class="form-control" placeholder="0" min="0" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="form-label">Category <span>*</span></label>
                    <select name="category_id" class="form-control" required>
                        <option value="" disabled selected>Select a category</option>
                        <c:forEach var="cat" items="${categories}">
                            <option value="${cat.categoryId}">${cat.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label class="form-label">Medicine Image</label>
                    <div class="img-upload-area" onclick="document.getElementById('imgInput').click()">
                        <input type="file" name="image" id="imgInput" accept="image/*" onchange="previewImage(this)">
                        <div id="uploadPrompt">
                            <div class="upload-icon">📷</div>
                            <div class="upload-text"><strong>Click to upload</strong> or drag & drop<br>PNG, JPG up to 10MB</div>
                        </div>
                        <img id="imgPreview" src="" alt="Preview" style="display:none; max-height:120px; border-radius:8px; margin-top:8px;">
                    </div>
                </div>

                <button type="submit" class="btn-submit">Save Medicine</button>
            </form>
        </div>
    </div>
</div>

<script>
    function openModal()  { document.getElementById('modalOverlay').classList.add('open'); }
    function closeModal() { document.getElementById('modalOverlay').classList.remove('open'); }
    function closeOnBackdrop(e) { if (e.target === document.getElementById('modalOverlay')) closeModal(); }

    function previewImage(input) {
        if (input.files && input.files[0]) {
            const reader = new FileReader();
            reader.onload = e => {
                document.getElementById('imgPreview').src = e.target.result;
                document.getElementById('imgPreview').style.display = 'block';
                document.getElementById('uploadPrompt').style.display = 'none';
            };
            reader.readAsDataURL(input.files[0]);
        }
    }

    function filterCards() {
        const q = document.getElementById('searchInput').value.toLowerCase();
        const cards = document.querySelectorAll('.med-card');
        let visible = 0;
        cards.forEach(c => {
            const match = c.dataset.name.includes(q);
            c.style.display = match ? '' : 'none';
            if (match) visible++;
        });
        document.getElementById('visibleCount').textContent = visible;
    }

    // auto-close success alert after 3s
    const alert = document.querySelector('.alert-success');
    if (alert) setTimeout(() => alert.style.display = 'none', 3000);

    // re-open modal on validation error
    <c:if test="${not empty requestScope.error}">openModal();</c:if>
</script>
</body>
</html>
