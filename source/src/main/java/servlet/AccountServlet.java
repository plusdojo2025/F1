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
import dao.CategoryDAO;
import dto.Account;
import dto.Category;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        // 文字コード・コンテンツタイプを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
		
		// セッション取得・もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		Account login_user = (Account) session.getAttribute("login_user");
		
		// アクセスユーザー取得のためのアカウントオブジェクト
		Account accountAccessUser = new Account();
		
		if (login_user == null) {
			response.sendRedirect("LoginServlet");
			return;
		} else {
			// アカウントの情報を再度取得
			try {
				AccountDAO accountDAO = new AccountDAO();
				accountAccessUser = accountDAO.getAccount(login_user.getEmail(), login_user.getPassword());
			} catch (Exception e) {
		        // エラー画面へ
		        request.setAttribute("errorMassage", "ユーザー情報の変更中にエラーが発生しました");
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
		        dispatcher.forward(request, response);
			}
		}
		
		String beforeEmail = (String) session.getAttribute("beforeEmail");
		if (beforeEmail != null) {
			session.removeAttribute("beforeEmail");
		}
		
		// リクエストパラメータを取得する
		String flagPrm = request.getParameter("flag");
		int categoryId = login_user.getCategoryId();
		
		int flag = 0;
		
		if (flagPrm != null) {
			flag =  Integer.parseInt(flagPrm);
		}
		
		//変更画面から遷移した場合、accountのセッションスコープを消去する
		if(flag == 2) {
			session.removeAttribute("account");
		}		
		
		//リクエストスコープにカテゴリーを格納する
		CategoryDAO cDao = new CategoryDAO();
		Category category = cDao.getCategory(categoryId);
		accountAccessUser.setCategory(category);
		
		// この時点でのlogin_userのEmailを取得・セッションに格納
		beforeEmail = login_user.getEmail();
		session.setAttribute("beforeEmail", beforeEmail);
		// この時点でのオブジェクトをセッションに格納（login_userの更新）
		session.setAttribute("login_user", accountAccessUser);
		
		// アカウント管理画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account.jsp");
		dispatcher.forward(request, response);
	}

}
