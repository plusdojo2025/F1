package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 新規登録画面を表示するサーブレット
 * GETでアクセスされた場合にsignup.jspへフォワードする
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * GETリクエスト時の処理
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        // 文字コード・コンテンツタイプを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 新規登録ページへフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
	}
	
}
