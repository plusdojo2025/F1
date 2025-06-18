package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        
        if (login_user == null) {
            // 未ログインはログイン画面へリダイレクト
            response.sendRedirect("LoginServlet");
            return; // ここで処理終了
        }
		
		//リクエストパラメーターを取得する
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String goalDetail = request.getParameter("goalDetail");
		
 
		// 未入力チェック処理
		if (nickname == null || nickname.trim().isEmpty() || email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			if (nickname == null || nickname.trim().isEmpty()) {
				request.setAttribute("nicknameErrorMassage", "この項目を入力してください。");
		            }
		   	if (email == null || email.trim().isEmpty()) {
		   		request.setAttribute("emailErrorMassage", "この項目を入力してください。");
		   	}
		   	if (password == null || password.trim().isEmpty()) {
		   		request.setAttribute("passwordErrorMassage", "この項目を入力してください。");
		   	}
		   	
		}
		
		// アカウント管理にアクセス時のメールアドレスを取得
		String beforeEmail = (String) session.getAttribute("beforeEmail");
		
		// メールアドレス変更状況の確認
		Boolean emailCheck = false;
		if (!email.equals(beforeEmail)) {
			emailCheck = true;
		}
		
		// オブジェクトの内容更新
		login_user.setEmail(email);
		login_user.setPassword(password);
		login_user.setNickname(nickname);
		login_user.setCategoryId(categoryId);
		login_user.setGoalDetail(goalDetail);
		// カテゴリーを格納する
		CategoryDAO cDao = new CategoryDAO();
		Category category = cDao.getCategory(categoryId);
		login_user.setCategory(category);
		         
		//更新内容をリクエストスコープに格納する
		session.setAttribute("login_user", login_user);
		session.setAttribute("emailCheck", emailCheck);
		
		//確認画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account_conf.jsp");
		dispatcher.forward(request, response);	}
	
}
