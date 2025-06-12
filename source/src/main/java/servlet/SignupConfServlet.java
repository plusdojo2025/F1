package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Account;

/**
 * アカウント新規登録時の登録内容確認画面を表示するサーブレット
 * GETでアクセスされた場合にsignup_conf.jspへフォワードする
 */
@WebServlet("/SignupConfServlet")
public class SignupConfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * GETリクエスト時の処理
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 文字コード・コンテンツタイプを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 新規登録アカウント情報確認ページへフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/signup_conf.jsp").forward(request, response);
    }

    
	 /**
	  * POSTリクエスト時の処理
	  */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	     // 文字コード設定
	     request.setCharacterEncoding("UTF-8");
	     response.setContentType("text/html; charset=UTF-8");
 
	     // セッション取得・新規作成途中アカウント情報の取得
	     HttpSession session = request.getSession();
	     Account signupAccount = (Account) session.getAttribute("signup_user");
	     
	     // セッションに新規登録中アカウント情報が無い場合
	     if (signupAccount == null) {
	    	    response.sendRedirect("SignupServlet");
	    	    return;
	     }
	
	     String nickname = request.getParameter("nickname");
	     int categoryId = Integer.parseInt(request.getParameter("categoryId"));
	     String goalDetail = request.getParameter("goalDetail");
	     
	     // 未入力チェック処理
	     if (nickname == null || nickname.trim().isEmpty()) {
	         request.setAttribute("errorMassage", "全ての項目を入力してください。");
	
	         request.setAttribute("beforeNickname", nickname);
	         request.setAttribute("beforeCategoryId", categoryId);
	         request.setAttribute("beforeGoalDetail", goalDetail);
	         request.getRequestDispatcher("/WEB-INF/jsp/signup2.jsp").forward(request, response);
	         return;
	     }
	     
	
	     // 入力情報を更新
	     signupAccount.setNickname(nickname);
	     signupAccount.setCategoryId(categoryId);
	     signupAccount.setGoalDetail(goalDetail);
	     // 自身のサーブレットにリダイレクト
	     session.setAttribute("signup_user", signupAccount);
	     response.sendRedirect("SignupConfServlet");
	}
     
}
