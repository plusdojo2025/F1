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
 * Servlet implementation class AccountConfServlet
 */
@WebServlet("/AccountConfServlet")
public class AccountConfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 文字コード・コンテンツタイプを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // セッション取得・セッション内のデータを取得
        HttpSession session = request.getSession();
        Account login_user = (Account) session.getAttribute("login_user");
        
        // 未ログインはログイン画面へリダイレクト
        if (login_user == null) {
            response.sendRedirect("LoginServlet");
            return; // ここで処理終了
        }
		
		//リクエストパラメーターを取得する
		String nickname = request.getParameter("nickname").trim();
		String email = request.getParameter("email");
		String beforePasswordInput = request.getParameter("beforePassword");
		String newPassword = request.getParameter("newPassword");
		String newPasswordConf = request.getParameter("newPasswordConf");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String goalDetail = request.getParameter("goalDetail").trim();
		
		// メールアドレス変更チェック
		String beforeEmail = (String) session.getAttribute("beforeEmail");
		Boolean emailCheck = false;
		if (!email.equals(beforeEmail)) {
			emailCheck = true;
		}
		
		// パスワード変更チェック（すべて入力されている：true）
		boolean passwordCheck = newPassword != null && !newPassword.isEmpty()
				&& newPasswordConf != null && !newPasswordConf.isEmpty()
				&& beforePasswordInput != null && !beforePasswordInput.isEmpty();
		
		// パスワードの変更有の場合の処理
		if (passwordCheck) {
			// 現在のパスワード（ハッシュ済み）を取得
			String sessionHashedPassword = (String) session.getAttribute("beforePassword");

			// 入力された現在のパスワードをハッシュ化
			String inputHashedPassword = AccountDAO.hashPassword(beforePasswordInput);

			// 一致確認
			if (!sessionHashedPassword.equals(inputHashedPassword)) {
				// エラー処理
				request.setAttribute("notEqualsErrorMessage", "現在のパスワードが正しくありません。");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account_edit.jsp");
				dispatcher.forward(request, response);
				return;
			}

			// 一致：login_user のパスワードを一時的に更新
			login_user.setPassword(newPassword);
		}
		
		// login_userの情報を更新
		login_user.setEmail(email);
		login_user.setNickname(nickname);
		login_user.setCategoryId(categoryId);
		login_user.setGoalDetail(goalDetail);
		
		// login_userオブジェクトにカテゴリーオブジェクトを格納する
		CategoryDAO cDao = new CategoryDAO();
		Category category = cDao.getCategory(categoryId);
		login_user.setCategory(category);
		
		// セッションスコープに更新処理に必要な情報を格納
		session.setAttribute("login_user", login_user);
		session.setAttribute("emailCheck", emailCheck);
		session.setAttribute("passwordCheck", passwordCheck);
		
		//確認画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account_conf.jsp");
		dispatcher.forward(request, response);	}
	
}
