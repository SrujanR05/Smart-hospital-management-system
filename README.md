<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Smart Hospital Management System</title>

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet">

    <style>
        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family:'Poppins',sans-serif;
        }

        body{
            background:#f4f7fb;
            color:#333;
        }

        header{
            background:linear-gradient(135deg,#1976d2,#0d47a1);
            color:white;
            text-align:center;
            padding:70px 20px;
        }

        header h1{
            font-size:3rem;
        }

        header p{
            margin-top:15px;
            font-size:1.2rem;
        }

        .btn{
            display:inline-block;
            margin-top:25px;
            background:white;
            color:#1976d2;
            padding:12px 28px;
            text-decoration:none;
            border-radius:30px;
            font-weight:bold;
            transition:.3s;
        }

        .btn:hover{
            background:#e3f2fd;
        }

        section{
            max-width:1100px;
            margin:auto;
            padding:60px 20px;
        }

        h2{
            text-align:center;
            margin-bottom:40px;
            color:#1976d2;
        }

        .grid{
            display:grid;
            grid-template-columns:repeat(auto-fit,minmax(250px,1fr));
            gap:25px;
        }

        .card{
            background:white;
            padding:25px;
            border-radius:12px;
            box-shadow:0 5px 15px rgba(0,0,0,.1);
            transition:.3s;
        }

        .card:hover{
            transform:translateY(-8px);
        }

        .card h3{
            color:#1976d2;
            margin-bottom:10px;
        }

        ul{
            padding-left:20px;
        }

        .tech{
            display:flex;
            flex-wrap:wrap;
            justify-content:center;
            gap:15px;
        }

        .badge{
            background:#1976d2;
            color:white;
            padding:10px 20px;
            border-radius:30px;
        }

        footer{
            background:#0d47a1;
            color:white;
            text-align:center;
            padding:25px;
            margin-top:40px;
        }
    </style>
</head>

<body>

<header>

<h1>🏥 Smart Hospital Management System</h1>

<p>
Enterprise-Level Hospital Management System built using Java Spring Boot
</p>

<a href="https://github.com/yourusername/Smart-Hospital-Management-System" class="btn">
View on GitHub
</a>

</header>

<section>

<h2>📖 About Project</h2>

<p align="center">
The Smart Hospital Management System is a complete backend solution developed
using Spring Boot that helps hospitals efficiently manage patients, doctors,
appointments, prescriptions, laboratory tests, billing, authentication,
analytics, and much more.
</p>

</section>

<section>

<h2>✨ Features</h2>

<div class="grid">

<div class="card">
<h3>🔐 Authentication</h3>
<p>
JWT Authentication, Spring Security, Role-Based Access Control.
</p>
</div>

<div class="card">
<h3>👨‍⚕️ Doctor Management</h3>
<p>
Add, Update, Delete, Search Doctors and Manage Availability.
</p>
</div>

<div class="card">
<h3>🧑‍🤝‍🧑 Patient Management</h3>
<p>
Patient Registration, History, Search, Update and Delete.
</p>
</div>

<div class="card">
<h3>📅 Appointment</h3>
<p>
Book, Cancel and Manage Appointments with Conflict Detection.
</p>
</div>

<div class="card">
<h3>💊 Prescription</h3>
<p>
Generate Prescriptions and View Medical History.
</p>
</div>

<div class="card">
<h3>🧪 Laboratory</h3>
<p>
Manage Lab Tests and Reports.
</p>
</div>

<div class="card">
<h3>💳 Billing</h3>
<p>
Automatic Bill Generation and Payment Tracking.
</p>
</div>

<div class="card">
<h3>📊 Dashboard</h3>
<p>
Hospital Analytics, Revenue Reports and Statistics.
</p>
</div>

</div>

</section>

<section>

<h2>🚀 Advanced Features</h2>

<div class="grid">

<div class="card">
<h3>🚨 Emergency Queue</h3>
<p>Critical, High and Normal Priority Patients.</p>
</div>

<div class="card">
<h3>📧 Email Reminder</h3>
<p>Appointment Confirmation & Reminder Emails.</p>
</div>

<div class="card">
<h3>📱 QR Code</h3>
<p>Generate QR Codes for Patient Records.</p>
</div>

<div class="card">
<h3>🤖 AI Symptom Checker</h3>
<p>Suggest Doctor Specialization using Keyword Matching.</p>
</div>

</div>

</section>

<section>

<h2>🛠 Tech Stack</h2>

<div class="tech">

<span class="badge">Java 21</span>
<span class="badge">Spring Boot</span>
<span class="badge">Spring Security</span>
<span class="badge">JWT</span>
<span class="badge">Spring Data JPA</span>
<span class="badge">Hibernate</span>
<span class="badge">MySQL</span>
<span class="badge">Maven</span>
<span class="badge">Lombok</span>
<span class="badge">Bootstrap</span>

</div>

</section>

<section>

<h2>📂 Project Structure</h2>

<pre>
src/
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── security
 ├── config
 ├── exception
 └── util
</pre>

</section>

<section>

<h2>👨‍💻 Developer</h2>

<p align="center">
<b>Srujan</b><br><br>
Computer Science Engineering Student<br>
Aspiring Software Development Engineer (SDE)
</p>

</section>

<footer>

© 2026 Smart Hospital Management System | Developed by Srujan

</footer>

</body>
</html>
