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
@WebServlet("/update")
public class update  extends HttpServlet{

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		
		
		int pprice=Integer.parseInt(req.getParameter("productprice"));
	
		
		String pid= req.getParameter("productid");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/amazon";
			String user="root";
			String pass="Akshu4";
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement stmt = con.createStatement();
			String query="update product set price ='"+pprice+"' where  id='"+pid+"'" ;
			      int row = stmt.executeUpdate(query);
			      
			      if(row>0) {
			    	  System.out.println("record is updated");
			    	  RequestDispatcher rd = req.getRequestDispatcher("homepage.html");
						rd.forward(req, res);
						
			      }else {
			    	  System.out.println("record is not updated");
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