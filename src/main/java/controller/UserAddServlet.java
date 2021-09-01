package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.UserDao;
import domain.User;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/userAdd")
public class UserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/userAdd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 入力を受け取る
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String strAge = request.getParameter("age");

		// 年齢の型変換
		Integer age;
		if(strAge == null || strAge.isBlank()) {
			age = null;
		}
		else {
			age = Integer.parseInt(strAge);
		}
		
		// Daoを利用してユーザを追加
		UserDao dao = DaoFactory.createUserDao();
		dao.add(new User(null, name, email, pass, age));

		// リダイレクト
		response.sendRedirect("user");
	}

}