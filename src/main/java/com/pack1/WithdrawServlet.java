package com.pack1;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("accno") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String accno = (String) session.getAttribute("accno");
        double amount = Double.parseDouble(request.getParameter("amount"));

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps1 = con.prepareStatement(
                "SELECT balance FROM bank_customer WHERE account_no=?"
            );
            ps1.setString(1, accno);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (amount > balance) {
                    out.println("<h2 style='color:red;text-align:center;'>Insufficient Balance!</h2>");
                } else {
                    PreparedStatement ps2 = con.prepareStatement(
                        "UPDATE bank_customer SET balance = balance - ? WHERE account_no=?"
                    );
                    ps2.setDouble(1, amount);
                    ps2.setString(2, accno);
                    ps2.executeUpdate();

                    
                    PreparedStatement ps3 = con.prepareStatement(
                        "INSERT INTO bank_transactions(account_no, trans_type, amount) VALUES(?, 'Withdraw', ?)"
                    );
                    ps3.setString(1, accno);
                    ps3.setDouble(2, amount);
                    ps3.executeUpdate();

                    out.println("<h2 style='color:green;text-align:center;'>₹" + amount + " Withdrawn Successfully!</h2>");
                }
            }
            out.println("<p style='text-align:center;'><a href='dashboard.jsp'>Back to Dashboard</a></p>");

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
