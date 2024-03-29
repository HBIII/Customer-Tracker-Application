package testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class TestdbServlet
 */
@WebServlet("/TestdbServlet")
public class TestdbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user="springstudent";
		String pass="springstudent";
		
		String jdbcUrl="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String driver="com.mysql.cj.jdbc.Driver";
		
		
		try {
			PrintWriter out = response.getWriter();
			
			out.println("connecting");
			
			Class.forName(driver);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl,user,pass);
			
			out.println("success");
			
			myConn.close();
		} 
		
		catch (Exception e) {
			e.printStackTrace();
			
			throw new ServletException(e);
		}
	}

}
