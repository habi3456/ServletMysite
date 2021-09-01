package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		try {
			var ctx = new InitialContext();
			var ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mysite_db"); 
			con = ds.getConnection();
			System.out.println("接続");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("ミス");
		}
		finally {
			try {
				if(con != null) {
					con.close();
					System.out.println("切断");
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}