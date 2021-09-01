package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.UserDao;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Daoを使いユーザ一覧を取得
		// ⇒リクエストスコープに格納
		UserDao dao = DaoFactory.createUserDao();
		request.setAttribute("list", dao.findAll());
		// JSPへフォワード
		request.getRequestDispatcher("/WEB-INF/view/user.jsp").forward(request, response);
	}

}