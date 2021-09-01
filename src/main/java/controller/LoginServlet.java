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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 入力値の取得
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		boolean isValidated = true;
		User user = null;

		if(email.isBlank() || pass.isBlank()) {
			// 未入力チェック
			isValidated = false;
			request.setAttribute("error", "※入力に不備あり");
		}
		else {
			// 正しいemail/passか？
			UserDao dao = DaoFactory.createUserDao();
			user = dao.findLoginUser(email, pass);
			if(user == null) {
				// ログイン失敗
				isValidated = false;
				request.setAttribute("error", "※メールまたはパスワードが違います");
			}
		}

		if(isValidated) {
			// ログイン済みの証明
			request.getSession().setAttribute("user", user);
			// ログイン完了画面を表示
			request.getRequestDispatcher("/WEB-INF/view/loginDone.jsp").forward(request, response);
		}
		else {
			// フォームを再表示
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		}
	}

}