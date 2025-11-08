package com.pack1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reg")
public class RegisterServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	   res.setContentType("text/html");
	   
	   PrintWriter out =res.getWriter();
	   
	   String accno = req.getParameter("accno");
	   String cname = req.getParameter("cname");
	   String password = req.getParameter("pass");
	   String email = req.getParameter("email");
	   String phone = req.getParameter("phone");
	   String balance = req.getParameter("balance");
	   
	   try {
		   Connection con = DBConnection.getConnection();
		   PreparedStatement p = con.prepareStatement("INSERT into bank_customer values(?,?,?,?,?,?)");
		   p.setString(1, accno);
		   p.setString(2, cname);
		   p.setString(3, password);
		   p.setString(4, email);
		   p.setString(5, phone);
		   p.setString(6, balance);
		   
		   int row =p.executeUpdate();
		   
		   if(row>0) {
			   req.setAttribute("msg",cname);
			   RequestDispatcher rd = req.getRequestDispatcher("Register.jsp");
			   rd.forward(req, res);
		   }
		   else {
			   out.println("<center><h2>Try again</h2></center>");
		   }
		  
	   }
	   catch(Exception e) {
		   
	   }
	   
  }
}
