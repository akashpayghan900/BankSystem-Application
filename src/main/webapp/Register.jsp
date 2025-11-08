<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Successful</title>
<style>
    body {
        background: 1px solid #aaa;
        font-family: 'Poppins', sans-serif;
        text-align: center;
        color: white;
        padding-top: 150px;
        margin: 0;
    }
    .card {
        background: white;
        color: orange;
        display: inline-block;
        padding: 50px 80px;
        border-radius: 15px;
        box-shadow: 0 5px 25px rgba(0,0,0,0.3);
    }
    h2 {
        color: skyblue;
        font-size: 28px;
        margin-bottom: 10px;
    }
    h3 {
        font-weight: normal;
        font-size: 20px;
        color: orange;
    }
    a {
        display: inline-block;
        margin-top: 25px;
        text-decoration: none;
        color: white;
        background: blue;
        padding: 12px 25px;
        border-radius: 8px;
        font-size: 16px;
        transition: 0.3s;
    }
    a:hover {
        background: orange;
    }
</style>
</head>
<body>
    <div class="card">
        <h2>Registration Successful!</h2>
        <h3>Welcome <%= request.getAttribute("msg") %></h3>
        <p>Your account has been successfully created.</p>
        <a href="login.html">Go to Login</a>
    </div>
</body>
</html>
