<%@ page import="jakarta.servlet.http.*" %>
<%
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("accno") == null) {
        response.sendRedirect("login.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<title>Bank Dashboard</title>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: 'Poppins', Arial, sans-serif;
        background: url('https://www.policycircle.org/wp-content/uploads/2023/03/BankingSector.jpg') no-repeat center center/cover;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .dashboard-container {
        background: orange(255, 255, 255, 0.9);
        padding: 40px 60px;
        border-radius: 15px;
        box-shadow: 0 6px 20px rgba(0,0,0,0.2);
        text-align: center;
        width: 400px;
        backdrop-filter: blur(5px);
    }

    h2 {
        color: #2e7d32;
        margin-bottom: 30px;
        font-size: 24px;
        text-transform: capitalize;
    }

    button {
        background: skyblue;
        color: white;
        border: none;
        padding: 12px 20px;
        border-radius: 10px;
        margin: 10px;
        width: 100%;
        font-size: 16px;
        cursor: pointer;
        transition: all 0.3s ease;
    }

    button:hover {
        background: orange;
        transform: scale(1.05);
    }

    .logout-btn {
        background: #d32f2f;
    }

    .logout-btn:hover {
        background: #b71c1c;
    }

</style>
</head>
<body>
    <div class="dashboard-container">
        <h2>Welcome, Account No : <%= session.getAttribute("accno") %></h2>

        <button onclick="window.location='deposit.html'">Deposit</button>
        <button onclick="window.location='withdraw.html'">Withdraw</button>
        <button onclick="window.location='BalanceServlet'">View Balance</button>
        <button onclick="window.location='history.jsp'">Transaction History</button>
        <button class="logout-btn" onclick="window.location='LogoutServlet'">Logout</button>
    </div>
</body>
</html>
