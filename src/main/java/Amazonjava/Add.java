package Amazonjava;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/add")

public class Add extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		int  pid= Integer.parseInt(req.getParameter("productid"));
		
		System.out.println("Product id is :"+pid);
		String pname=req.getParameter("productname");
		int pprice=Integer.parseInt( req.getParameter("productprice"));
		String pcom=req.getParameter("productcompany");
		String pdet=req.getParameter("productdelete");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/amazon";
			String user="root";
			String pass="Akshu4";
			Connection con = DriverManager.getConnection(url,user,pass);
			PreparedStatement ps = con.prepareStatement("insert into product values(?,?,?,?,?)");
			ps.setInt(1, pid);
			ps.setString(2, pname);
			ps.setInt(3, pprice);
			ps.setString(4, pcom);
			ps.setString(5, pdet);
			
			int rs = ps.executeUpdate();
			if(rs>0)
			{
				System.out.println("inserted data");
				RequestDispatcher rd = req.getRequestDispatcher("homepage.html");
				rd.forward(req, res);
				
			}
			else
			{
				System.out.println("not inserted data");
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