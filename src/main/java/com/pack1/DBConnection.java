package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static String driverName="oracle.jdbc.driver.OracleDriver";
	 private static String url="jdbc:oracle:thin:@localhost:1521:xe";
	 private static String username="system";
	 private static String password="tiger";
	 
	  private static Connection con=null;
	 
	  private   DBConnection() {
		// TODO Auto-generated constructor stub
      
		   
	  }
	  
	    public static Connection getConnection() {
	    	 if(con==null) {
	    		try {
					Class.forName(driverName);
					con=(Connection) DriverManager.getConnection(url, username, password);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	 }
	    	 return con;
	    }
}
