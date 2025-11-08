package com.pack1;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
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

            
            PreparedStatement ps = con.prepareStatement(
                "UPDATE bank_customer SET balance = balance + ? WHERE account_no = ?"
            );
            ps.setDouble(1, amount);
            ps.setString(2, accno);
            int rows = ps.executeUpdate();

            if (rows > 0) {
               
                PreparedStatement ps2 = con.prepareStatement(
                    "INSERT INTO bank_transactions(account_no, trans_type, amount) VALUES(?, 'Deposit', ?)"
                );
                ps2.setString(1, accno);
                ps2.setDouble(2, amount);
                ps2.executeUpdate();

                out.println("<h2 style='color:green;text-align:center;'>₹" + amount + " Deposited Successfully!</h2>");
                out.println("<p style='text-align:center;'><a href='dashboard.jsp'>Back to Dashboard</a></p>");
            }

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
