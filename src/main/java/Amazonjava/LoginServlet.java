package Amazonjava;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
      
	 String url="jdbc:mysql://localhost:3306/productmanagementsystem";
	 String user="root";
	 String pass="Akshu4";
	private Connection con=null;
	
	
	 //doget()
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username=req.getParameter("email");
		String password=req.getParameter("password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			Statement stmt = con.createStatement();
            String query="select * from signup";
			ResultSet rs = stmt.executeQuery(query);
			int count=0;
			PrintWriter out = res.getWriter();
         while(rs.next()) {
        	 
 if(rs.getString("username").equals(username)&& rs.getString("password").equals(password)) {
        		 count++;
        	 }
         }
         if(count==1) {
        	 RequestDispatcher rd1 = req.getRequestDispatcher("homepage.html");
        	 rd1.forward(req,res);
        	
        	 
         }else {
        	 RequestDispatcher rd1 = req.getRequestDispatcher("Signup.html");
        	 rd1.forward(req,res);
         }
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}