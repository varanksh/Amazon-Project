package Amazonjava;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/signup")
public class SignUpServlet  extends HttpServlet{

	String url="jdbc:mysql://localhost:3306/amazon";

	String user="root";
	String pass="Akshu4";
	Connection con=null;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, IOException {
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String mobileno = req.getParameter("mobileno");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			PreparedStatement stmt = con.prepareStatement("insert into signup (firstname,lastname,mobileno,username,password) values(?,?,?,?,?)");
			stmt.setString(1, firstname);
			stmt.setString(2, lastname);
			stmt.setString(3, mobileno);
			stmt.setString(4, username);
			stmt.setString(5, password);
			int rs = stmt.executeUpdate();
		
		
			if(rs>0) {
				RequestDispatcher rd = req.getRequestDispatcher("login.html");
				rd.forward(req, res);
			}else {
				System.out.println("data is not inserted");
			}
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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