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
    <title>Register - MediCare</title>
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
            min-height: 560px;
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
            position: relative;
            z-index: 1;
        }

        .brand-icon {
            width: 48px;
            height: 48px;
            background: rgba(255,255,255,0.15);
            border-radius: 12px;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 18px;
            font-size: 22px;
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
            font-size: 13.5px;
            color: rgba(255,255,255,0.65);
            line-height: 1.6;
        }

        .panel-features {
            position: relative;
            z-index: 1;
        }

        .feature-item {
            display: flex;
            align-items: center;
            gap: 12px;
            margin-bottom: 18px;
        }

        .feature-dot {
            width: 32px;
            height: 32px;
            background: rgba(255,255,255,0.15);
            border-radius: 50%;
            flex-shrink: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 15px;
        }

        .feature-text {
            font-size: 13px;
            color: rgba(255,255,255,0.80);
            line-height: 1.4;
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

        /* ── Register Button ── */
        .btn-register {
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

        .btn-register:hover {
            opacity: 0.92;
            transform: translateY(-1px);
            box-shadow: 0 6px 20px rgba(46,125,82,0.34);
        }

        .btn-register:active {
            transform: translateY(0);
        }

        /* Footer link */
        .form-footer {
            margin-top: 24px;
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
        .input-field textarea {
            height: 44px;
            padding: 12px 16px;
            border: 1.8px solid var(--gray-border);
            border-radius: var(--radius);
            font-family: 'DM Sans', sans-serif;
            font-size: 14.5px;
            color: var(--text-dark);
            background: var(--gray-soft);
            outline: none;
            resize: none;
        }

        .form-footer a:hover {
            opacity: 0.75;
            text-decoration: underline;
        }

        /* ── Responsive ── */
        @media (max-width: 700px) {
            .page-wrapper  { flex-direction: column; width: 95%; }
            .left-panel    { width: 100%; padding: 36px 28px 28px; }
            .panel-features { display: none; }
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
            <h2>Create an Account</h2>
            <p>Join MediCare to manage your health records</p>
        </div>

        <!-- Display error message if any -->
        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
        <div class="error-message">⚠️ <%= errorMessage %></div>
        <% } %>

        <!-- Registration Form -->
        <form method="post" action="register">

            <!-- Full Name Field -->
            <div class="input-field">
                <label for="fullname">Full Name</label>
                <input type="text" id="fullname" name="fullname" placeholder="GovMedCare" required>
            </div>

            <!-- Email Field -->
            <div class="input-field">
                <label for="email">Email Address</label>
                <input type="email" id="email" name="email" placeholder="govmedcare@gmail.com" required>
            </div>

            <!-- Phone Field -->
            <div class="input-field">
                <label for="phone">Phone Number</label>
                <textarea id="phone" name="phone" rows="1" placeholder="e.g. 98XXXXXXXX" required></textarea>
            </div>

            <!-- Address Field -->
            <div class="input-field">
                <label for="address">Address</label>
                <textarea id="address" name="address" rows="1" placeholder="Enter your address" required></textarea>
            </div>

            <!-- Password Field -->
            <div class="input-field">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Create a strong password" required>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="btn-register">Register</button>

        </form>

        <!-- Login redirect -->
        <p class="form-footer">
            Already have an account? <a href="login.jsp">Sign in here</a>
        </p>

    </div>
</div>

</body>
</html>
