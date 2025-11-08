package com.pack1;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@WebServlet("/PDFDownloadServlet")
public class PDFDownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=TransactionHistory.pdf");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("accno") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String accno = (String) session.getAttribute("accno");

        try (OutputStream out = response.getOutputStream()) {

           
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

          
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
            document.add(new Paragraph(" Bank Transaction History", titleFont));
            document.add(new Paragraph("Account No: " + accno));
            document.add(new Paragraph(" "));
            
            
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.addCell("Transaction ID");
            table.addCell("Type");
            table.addCell("Amount (₹)");
            table.addCell("Date & Time");

          
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM bank_transactions WHERE account_no=? ORDER BY trans_date DESC")) {
                
                ps.setString(1, accno);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    table.addCell(String.valueOf(rs.getInt("trans_id")));
                    table.addCell(rs.getString("trans_type"));
                    table.addCell(String.valueOf(rs.getDouble("amount")));
                    table.addCell(String.valueOf(rs.getTimestamp("trans_date")));
                }

                document.add(table);
            }

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error generating PDF: " + e.getMessage());
        }
    }
}
