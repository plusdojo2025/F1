package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dto.Account;

/**
 * アカウントの新規登録の実行処理をするサーブレット
 * login.jspへリダイレクトする
 */
@WebServlet("/SignupExecuteServlet")
public class SignupExecuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 /**
	  * POSTリクエスト時の処理
	  * セッションの情報を基にDBにアカウントを新規登録する。
	  */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // セッションスコープから新規登録アカウントデータを取得 
		HttpSession session = request.getSession();
		Account signupAccount = (Account) session.getAttribute("signup_user");
		
		// アカウントDAOの生成
		AccountDAO accountDAO = new AccountDAO();
		boolean result = false;

	    try {
	    	result = accountDAO.registAccount(signupAccount);
		} catch (Exception e) {
			e.printStackTrace();
            request.setAttribute("errorMessage", "アカウント情報の登録中にエラーが発生しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
            return;
		}
	    
		if (result) {
			// 登録成功：セッション情報の削除・LoginServletへリダイレクト
			session.removeAttribute("signup_user");
			response.sendRedirect("LoginServlet");
		} else {
			// 登録失敗:エラー画面
			request.setAttribute("errorMessage", "登録に失敗しました。もう一度お試しください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
