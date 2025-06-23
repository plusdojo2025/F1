package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ログアウト処理用サーブレット
 * POSTでアクセスされた場合にログインページへリダイレクトする
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// セッション情報を削除
		HttpSession session = request.getSession();
		session.removeAttribute("login_user");
		
        // ログインページへリダイレクト
        response.sendRedirect(request.getContextPath()+"/LoginServlet");
	}

}
