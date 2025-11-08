package com.pack1;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String accno = request.getParameter("accno");
        String pass = request.getParameter("password");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bank_customer WHERE account_no=? AND password=?");
            ps.setString(1, accno);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("accno", accno);
                response.sendRedirect("dashboard.jsp");
            } else {
                out.println("<h3 style='color:red;text-align:center;margin-left:400px'>Invalid Account or Password</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("login.html");
                rd.include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
