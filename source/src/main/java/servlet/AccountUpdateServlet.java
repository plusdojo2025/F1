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

/**
 * アカウント情報の更新を実行するサーブレット
 */
@WebServlet("/AccountUpdateServlet")
public class AccountUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 文字コード・コンテンツタイプを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
		
		// セッション取得・もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		Account login_user = (Account) session.getAttribute("login_user");
		if (login_user == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}
		
		// メールアドレス変更状況のフラグを取得
		Boolean emailCheck = (Boolean) session.getAttribute("emailCheck");
		
		// パスワード変更状況のフラグを取得
		Boolean passwordCheck = (Boolean) session.getAttribute("passwordCheck");		
		
		try {
			// アカウント情報の更新を行う
			AccountDAO aDao = new AccountDAO();
			if(aDao.updateAccount(login_user, emailCheck, passwordCheck)) {
				// 更新成功
				session.setAttribute("login_user", login_user);
				
				response.sendRedirect(request.getContextPath() + "/AccountServlet");
			}
			else {
			    // メールアドレスが重複していた場合のエラー処理
			    if (emailCheck) {
			        request.setAttribute("emailErrorMessage", "すでに使用されているメールアドレスです。");
			        
			        // セッションのフラグを初期化する
			        session.removeAttribute("emailCheck");
			        session.removeAttribute("passwordCheck");
			        
			        // カテゴリ一覧
			        CategoryDAO categoryDao = new CategoryDAO();
			        request.setAttribute("categoryList", categoryDao.getCategoryList());

			        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account_edit.jsp");
			        dispatcher.forward(request, response);
			    } else {
			        // それ以外のエラー：エラー画面へ
			        request.setAttribute("errorMassage", "ユーザー情報の変更中にエラーが発生しました");
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			        dispatcher.forward(request, response);
			    }
			}
		} catch (Exception e) {
            // DB例外・その他予期しないエラー時
            request.setAttribute("errorMassage", "ユーザー情報の変更中にエラーが発生しました");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
        }
		
	}

}
