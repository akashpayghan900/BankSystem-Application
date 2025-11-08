<%@ page import="java.sql.*,com.pack1.DBConnection,jakarta.servlet.http.*,jakarta.servlet.*" %>
<%
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("accno") == null) {
        response.sendRedirect("login.html");
        return;
    }

    String accno = (String) s.getAttribute("accno");
    String name = (String) s.getAttribute("name");
    double balance = 0;

    try {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT balance FROM bank_customer WHERE account_no=?");
        ps.setString(1, accno);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) balance = rs.getDouble("balance");
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account Balance</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', Arial, sans-serif;
            background: url('https://www.policycircle.org/wp-content/uploads/2022/11/banking.jpg') no-repeat center center fixed;
            background-size: cover;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .balance-box {
            background: rgba(255, 255, 255, 0.95);
            padding: 40px 50px;
            border-radius: 15px;
            box-shadow: 0 6px 25px rgba(0, 0, 0, 0.25);
            text-align: center;
            width: 380px;
        }

        h2 {
            color: #1b5e20;
            font-size: 24px;
            margin-bottom: 10px;
        }

        h3 {
            color: #2e7d32;
            font-size: 22px;
            margin-bottom: 20px;
        }

        a {
            text-decoration: none;
            background-color: #2e7d32;
            color: white;
            padding: 10px 25px;
            border-radius: 6px;
            font-size: 15px;
            font-weight: bold;
            transition: 0.3s;
        }

        a:hover {
            background-color: #1b5e20;
        }

        .footer {
            margin-top: 10px;
            font-size: 13px;
            color: #555;
        }

        @media (max-width: 480px) {
            .balance-box {
                width: 90%;
                padding: 25px;
            }
        }
    </style>
</head>
<body>
    <div class="balance-box">
        <h2>Hello, <%= name %></h2>
        <h3>Your Current Balance:  <%= balance %></h3>
        <a href="dashboard.jsp">Back to Dashboard</a>
        <div class="footer">ALS Bank 2025</div>
    </div>
</body>
</html>
