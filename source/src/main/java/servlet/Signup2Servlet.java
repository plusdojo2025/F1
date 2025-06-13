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
 * 新規登録２画面を表示するサーブレット
 * GETでアクセスされた場合にsignup2.jspへフォワードする
 */
@WebServlet("/Signup2Servlet")
public class Signup2Servlet extends HttpServlet {
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

        // ログインページへフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/signup2.jsp").forward(request, response);
    }
       
    /**
     * POSTリクエスト時の処理
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        // 文字コード設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConf = request.getParameter("passwordConfirm");
        
        // 未入力チェック処理
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty() || passwordConf == null || passwordConf.trim().isEmpty()) {
            request.setAttribute("errorMassage", "全ての項目を入力してください。");

            request.setAttribute("beforeEmail", email);
            request.setAttribute("beforePassword", password);
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        }
        
        // passwordとpasswordConfが一致しない場合の処理
        if (!password.equals(passwordConf)) {
            request.setAttribute("notEqualsErrorMassage", "パスワードが一致していません。");

            request.setAttribute("beforeEmail", email);
            request.setAttribute("beforePassword", password);
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        }

        // アカウントオブジェクトをインスタンス化
        Account account = new Account();
        // 入力情報をセット
        account.setEmail(email);
        account.setPassword(password);

        HttpSession session = request.getSession();
        session.setAttribute("signup_user", account);
        response.sendRedirect("Signup2Servlet");
            
    }
        
}
