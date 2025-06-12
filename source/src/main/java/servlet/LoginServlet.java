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
 * ログイン画面を表示するサーブレット
 * GETでアクセスされた場合にlogin.jspへフォワードする
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    /**
     * ログイン実行処理
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 文字コード設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 未入力チェック処理
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            if (email == null || email.trim().isEmpty()) {
                request.setAttribute("emailErrorMassage", "この項目を入力してください。");
            }
            if (password == null || password.trim().isEmpty()) {
                request.setAttribute("passwordErrorMassage", "この項目を入力してください。");
            }

            request.setAttribute("beforeEmail", email);
            request.setAttribute("beforePassword", password);
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }
        
        try {
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.getAccount(email, password);

            if (account != null) {
                // ログイン成功→セッション登録＆リダイレクト
                HttpSession session = request.getSession();
                session.setAttribute("login_user", account);
                response.sendRedirect("IndexServlet");
            } else {
                // ログイン失敗（ユーザー情報がnull）
                request.setAttribute("loginErrorMassage", "入力内容が間違っています。");
                request.setAttribute("beforeEmail", email);
                request.setAttribute("beforePassword", password);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            // DB例外・その他予期しないエラー時
            request.setAttribute("loginErrorMassage", "ユーザー情報の確認中にエラーが発生しました");
            request.setAttribute("beforeEmail", email);
            request.setAttribute("beforePassword", password);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
