package Amazonjava;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/delete")
public class delete extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		
	
	
		String pid= req.getParameter("productid");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/amazon";
			String user="root";
			String pass="Akshu4";
			Connection con = DriverManager.getConnection(url,user,pass);
			

			Statement smt = con.createStatement();
			
		    String query="delete from product where id='"+pid+"'"; 
		    int row = smt.executeUpdate(query);
		    
		    if(row>0) {
		    	System.out.println("deleted data");
		    	RequestDispatcher rd = req.getRequestDispatcher("homepage.html");
				rd.forward(req, res);
		    }else {
		    	System.out.println("not deleted");
		    	RequestDispatcher rd = req.getRequestDispatcher("homepage.html");
				rd.forward(req, res);
		    }
			
			
		
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
