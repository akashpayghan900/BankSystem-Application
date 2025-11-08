package com.pack1;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;

@WebServlet("/BalanceServlet")
public class BalanceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("accno") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String accno = (String) session.getAttribute("accno");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT balance FROM bank_customer WHERE account_no=?");
            ps.setString(1, accno);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                out.println("<h2 style='text-align:center;color:green;'>Current Balance: ₹" + balance + "</h2>");
            }
            out.println("<p style='text-align:center;'><a href='dashboard.jsp'>Back to Dashboard</a></p>");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
