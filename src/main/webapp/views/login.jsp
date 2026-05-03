<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 3/20/2026
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login - MediCare</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600&family=Playfair+Display:wght@600&display=swap" rel="stylesheet">

    <style>
        /* ── CSS Variables ── */
        :root {
            --green-primary:   #2e7d52;
            --green-light:     #e8f5ee;
            --green-mid:       #a8d5b5;
            --green-accent:    #3daa6e;
            --white:           #ffffff;
            --gray-soft:       #f4f7f5;
            --gray-border:     #d0e4d8;
            --text-dark:       #1a2e24;
            --text-muted:      #6b8f7a;
            --error-red:       #c0392b;
            --shadow:          0 8px 32px rgba(46, 125, 82, 0.12);
            --radius:          14px;
        }

        /* ── Reset & Base ── */
        *, *::before, *::after {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'DM Sans', sans-serif;
            background-color: var(--green-light);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background-image:
                    radial-gradient(ellipse at 20% 20%, rgba(61,170,110,0.18) 0%, transparent 60%),
                    radial-gradient(ellipse at 80% 80%, rgba(46,125,82,0.12) 0%, transparent 60%);
        }

        /* ── Page Wrapper ── */
        .page-wrapper {
            display: flex;
            width: 900px;
            min-height: 520px;
            background: var(--white);
            border-radius: 24px;
            box-shadow: var(--shadow);
            overflow: hidden;
            animation: fadeUp 0.55s ease both;
        }

        @keyframes fadeUp {
            from { opacity: 0; transform: translateY(28px); }
            to   { opacity: 1; transform: translateY(0); }
        }

        /* ── Left Panel (decorative) ── */
        .left-panel {
            width: 42%;
            background: linear-gradient(145deg, var(--green-primary) 0%, #1b5e38 100%);
            padding: 52px 40px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            position: relative;
            overflow: hidden;
        }

        .left-panel::before {
            content: '';
            position: absolute;
            width: 300px;
            height: 300px;
            background: rgba(255,255,255,0.05);
            border-radius: 50%;
            top: -80px;
            left: -80px;
        }

        .left-panel::after {
            content: '';
            position: absolute;
            width: 200px;
            height: 200px;
            background: rgba(255,255,255,0.06);
            border-radius: 50%;
            bottom: -60px;
            right: -40px;
        }

        .brand {
            display: flex;
            flex-direction: column;
            align-items: flex-start; /* keep left aligned */
        }


        .brand-icon {
            width: 90px;
            height: 90px;
            background: none;
            margin-bottom: 12px;
        }

        .brand-icon img {
            width: 100%;
            height: auto;
            object-fit: contain;
        }


        .brand-name {
            font-family: 'Playfair Display', serif;
            font-size: 28px;
            color: var(--white);
            letter-spacing: 0.5px;
            line-height: 1.2;
        }

        .brand-tagline {
            margin-top: 10px;
            font-size: 14px;
            color: rgba(255,255,255,0.65);
            line-height: 1.6;
        }
        /* ── Right Panel (form) ── */
        .right-panel {
            flex: 1;
            padding: 52px 48px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        .form-header {
            margin-bottom: 32px;
        }

        .form-header h2 {
            font-family: 'Playfair Display', serif;
            font-size: 26px;
            color: var(--text-dark);
            margin-bottom: 6px;
        }

        .form-header p {
            font-size: 14px;
            color: var(--text-muted);
        }

        /* ── Error Message ── */
        .error-message {
            background: #fdecea;
            border-left: 4px solid var(--error-red);
            border-radius: 8px;
            padding: 12px 16px;
            font-size: 13.5px;
            color: var(--error-red);
            margin-bottom: 22px;
        }

        /* ── Form Elements ── */
        form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .input-field {
            display: flex;
            flex-direction: column;
            gap: 7px;
        }

        .input-field label {
            font-size: 13px;
            font-weight: 600;
            color: var(--text-dark);
            letter-spacing: 0.2px;
        }

        .input-field input {
            padding: 12px 16px;
            border: 1.8px solid var(--gray-border);
            border-radius: var(--radius);
            font-family: 'DM Sans', sans-serif;
            font-size: 14.5px;
            color: var(--text-dark);
            background: var(--gray-soft);
            outline: none;
            transition: border-color 0.2s, background 0.2s, box-shadow 0.2s;
        }

        .input-field input:focus {
            border-color: var(--green-accent);
            background: var(--white);
            box-shadow: 0 0 0 4px rgba(61,170,110,0.10);
        }

        .input-field input::placeholder {
            color: #b0c4ba;
        }

        /*  Login Button ── */
        .btn-login {
            margin-top: 6px;
            padding: 14px;
            background: linear-gradient(135deg, var(--green-accent) 0%, var(--green-primary) 100%);
            color: var(--white);
            border: none;
            border-radius: var(--radius);
            font-family: 'DM Sans', sans-serif;
            font-size: 15px;
            font-weight: 600;
            cursor: pointer;
            letter-spacing: 0.3px;
            transition: opacity 0.2s, transform 0.15s, box-shadow 0.2s;
            box-shadow: 0 4px 16px rgba(46,125,82,0.28);
        }

        .btn-login:hover {
            opacity: 0.92;
            transform: translateY(-1px);
            box-shadow: 0 6px 20px rgba(46,125,82,0.34);
        }

        .btn-login:active {
            transform: translateY(0);
        }

        /* ── Divider ── */
        .divider {
            display: flex;
            align-items: center;
            gap: 12px;
            color: var(--text-muted);
            font-size: 12.5px;
        }

        .divider::before,
        .divider::after {
            content: '';
            flex: 1;
            height: 1px;
            background: var(--gray-border);
        }

        /* ── Register CTA button ── */
        .btn-register-outline {
            padding: 13px;
            background: transparent;
            border: 1.8px solid var(--gray-border);
            border-radius: var(--radius);
            font-family: 'DM Sans', sans-serif;
            font-size: 14.5px;
            font-weight: 600;
            color: var(--green-primary);
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            display: block;
            transition: background 0.2s, border-color 0.2s, transform 0.15s;
        }

        .btn-register-outline:hover {
            background: var(--green-light);
            border-color: var(--green-mid);
            transform: translateY(-1px);
        }

        /* ── Footer link ── */
        .form-footer {
            margin-top: 8px;
            text-align: center;
            font-size: 13.5px;
            color: var(--text-muted);
        }

        .form-footer a {
            color: var(--green-primary);
            font-weight: 600;
            text-decoration: none;
            transition: opacity 0.2s;
        }

        .form-footer a:hover {
            opacity: 0.75;
            text-decoration: underline;
        }

        /* ── Responsive ── */
        @media (max-width: 700px) {
            .page-wrapper  { flex-direction: column; width: 95%; }
            .left-panel    { width: 100%; padding: 36px 28px 28px; }
            .panel-quote   { display: none; }
            .right-panel   { padding: 36px 28px; }
        }
    </style>
</head>

<body>

<%
    // Retrieve error message from request (set by servlet)
    String errorMessage = (String) request.getAttribute("error");
%>

<div class="page-wrapper">

    <!-- ── Left decorative panel ── -->
    <div class="left-panel">
        <div class="brand">

<%--            <div class="brand-icon">--%>
<%--                <img src="${pageContext.request.contextPath}assest/logo.png" alt="Logo">--%>
<%--            </div>--%>

            <div class="brand-name">MediCare</div>
            <p class="brand-tagline">Your Health, Our Responsibility.<br>"The Ministry of Health & Population of Nepal".</p>
        </div>
    </div>

    <!-- ── Right form panel ── -->
    <div class="right-panel">

        <div class="form-header">
            <h2>Welcome Back</h2>
            <p>Sign in to your MediCare account</p>
        </div>

        <!-- Display error message if any -->
        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
        <div class="error-message">⚠️ <%= errorMessage %></div>
        <% } %>

        <!-- Login Form -->
        <form method="post" action="login">

            <!-- Email Field -->
            <div class="input-field">
                <label for="email">Gmail / Email Address</label>
                <input type="email" id="email" name="email" placeholder="sumitpkhrl@gmail.com" required>
            </div>

            <!-- Password Field -->
            <div class="input-field">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="password" required>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="btn-login">Sign In</button>

        </form>

        <!-- Divider -->
        <div class="divider" style="margin-top: 22px;">or</div>

        <!-- Register CTA -->
        <a href="<%=request.getContextPath()%>/register" class="btn-register-outline" style="margin-top: 14px;">
            New to MediCare? Register here
        </a>
    </div>
</div>

</body>
</html>
