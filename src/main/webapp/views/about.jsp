<%--
  Created by IntelliJ IDEA.
  User: CPN_J
  Date: 5/19/2026
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>About Us – GovMedCare</title>

    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/CSS/about.css">
</head>
<body>

<%@ include file="patient-navbar.jsp" %>

<div class="main">

    <div class="topbar">
        <h1>About Us</h1>
    </div>

    <div class="content">

        <section class="about-hero">
            <h2>GovMedCare</h2>
            <p>Secure Healthcare Medicine Management System</p>
            <span>
                Improving accessibility, transparency, and reliability in healthcare medicine services.
            </span>
        </section>

        <section class="about-section">
            <h2>About GovMedCare</h2>
            <p>
                GovMedCare is a digital healthcare medicine management platform designed to support
                secure and transparent medicine distribution. The system allows patients to view
                approved medicines, manage carts, complete purchases, and track purchase history,
                while suppliers and administrators manage medicine availability and verification.
            </p>
        </section>

        <section class="two-grid">
            <div class="info-card">
                <h3>Our Mission</h3>
                <p>
                    Our mission is to simplify access to healthcare medicines by providing a secure,
                    user-friendly, and transparent digital platform for patients, suppliers, and administrators.
                </p>
            </div>

            <div class="info-card">
                <h3>Our Vision</h3>
                <p>
                    Our vision is to support a modern healthcare ecosystem where medicine access,
                    monitoring, and distribution can be managed efficiently through digital technology.
                </p>
            </div>
        </section>

        <section class="about-section">
            <h2>What Services We Provide</h2>

            <div class="service-grid">
                <div class="service-card">
                    <h3>Patient Medicine Access</h3>
                    <p>Patients can browse approved medicines, add items to cart, and complete purchases easily.</p>
                </div>

                <div class="service-card">
                    <h3>Supplier Medicine Management</h3>
                    <p>Suppliers can add medicines, manage stock, and submit medicine details for approval.</p>
                </div>

                <div class="service-card">
                    <h3>Admin Monitoring</h3>
                    <p>Administrators can approve medicines, monitor records, and manage users securely.</p>
                </div>

                <div class="service-card">
                    <h3>Secure Purchase Flow</h3>
                    <p>The system supports cart management, checkout, payment method selection, and purchase history.</p>
                </div>
            </div>
        </section>

        <section class="verified-box">
            <h2>Authentic & Verified Medicines</h2>
            <p>
                All medicines available through GovMedCare are authentic and verified under the monitoring
                of the Ministry of Health & Population of Nepal. This helps ensure safety, transparency,
                and reliability in healthcare medicine distribution services.
            </p>
        </section>

        <section class="about-section">
            <h2>System Features</h2>

            <div class="feature-list">
                <span>Role-Based Access Control</span>
                <span>Medicine Approval System</span>
                <span>Cart and Checkout</span>
                <span>Purchase History</span>
                <span>Supplier Stock Management</span>
                <span>Admin Reports</span>
            </div>
        </section>
        <section class="team-section">

            <div class="section-heading">
                <h2>Meet Our Teams</h2>
            </div>

            <div class="team-grid">

                <div class="team-card">

                    <img src="${pageContext.request.contextPath}/views/resources/pratikk.jpg"
                         alt="Pratik Kattel"
                         class="team-photo">

                    <h3>Pratik Kattel</h3>

                    <span>
                Backend Developer
            </span>

                    <p>
                        Responsible for backend system development, database structure design,
                        query management, and integration of core healthcare functionalities.
                    </p>

                </div>

                <div class="team-card">

                    <img src="${pageContext.request.contextPath}/views/resources/sumit.jpg"
                         alt="Sumit Pokharel"
                         class="team-photo">

                    <h3>Sumit Pokharel</h3>

                    <span>
                Frontend Designer
            </span>

                    <p>
                        Worked on frontend layout development, responsive page structure,
                        and maintaining a clean and user-friendly interface design.
                    </p>

                </div>

                <div class="team-card">

                    <img src="${pageContext.request.contextPath}/resources/ankit.jpg"
                         alt="Ankit Karki"
                         class="team-photo">

                    <h3>Ankit Karki</h3>

                    <span>
                UI/UX Designer
            </span>

                    <p>
                        Focused on improving user experience, interface consistency,
                        dashboard usability, and overall visual design planning.
                    </p>

                </div>

                <div class="team-card">

                    <img src="${pageContext.request.contextPath}/resources/risabh.jpg"
                         alt="Risabh Karki"
                         class="team-photo">

                    <h3>Risabh Karki</h3>

                    <span>
                System Tester
            </span>

                    <p>
                        Responsible for system testing, identifying functionality issues,
                        validating workflows, and ensuring overall platform reliability.
                    </p>

                </div>

                <div class="team-card">

                    <img src="${pageContext.request.contextPath}/resources/manisha.jpg"
                         alt="Manisha Rai"
                         class="team-photo">

                    <h3>Manisha Rai</h3>

                    <span>
                Documentation
            </span>

                    <p>
                        Contributed to project documentation, report preparation,
                        requirement organization, and system documentation management.
                    </p>

                </div>

                <div class="team-card">

                    <img src="${pageContext.request.contextPath}/resources/soni.jpg"
                         alt="Soni Limbu"
                         class="team-photo">

                    <h3>Soni Limbu</h3>

                    <span>
                Documentation
            </span>

                    <p>
                        Assisted in preparing technical documentation, project records,
                        and maintaining organized documentation throughout development.
                    </p>

                </div>

            </div>

        </section>

        <section class="contact-footer">
            <h2>Contact</h2>
            <p>GovMedCare </p>
            <p>Email: support@govmedcare.com</p>
            <p>Location: SundarHaraincha-8, Morang</p>
            <span>© 2026 GovMedCare. All Rights Reserved.</span>
        </section>

    </div>
</div>

</body>
</html>
