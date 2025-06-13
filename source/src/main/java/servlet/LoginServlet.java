package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
        
        String loginBtn = request.getParameter("login");
        String signupBtn = request.getParameter("signup");

        
        if(signupBtn != null) {
        	response.sendRedirect(request.getContextPath() + "/SignupServlet");
        	return;
        }
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 未入力チェック処理
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            if (email == null || email.trim().isEmpty()) {
                request.setAttribute("emailErrorMessage", "この項目を入力してください。");
            }
            if (password == null || password.trim().isEmpty()) {
                request.setAttribute("passwordErrorMessage", "この項目を入力してください。");
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
                // 今日の日付を取得
                LocalDate today = LocalDate.now();
            	
                // 前回ログイン日時（DBに保存されていた日時）を取得
                Timestamp lastLoginTimestamp = account.getLoginAt();
                LocalDate lastLoginDate = null;
                
                if (lastLoginTimestamp != null) {
                	// Timestamp型をLocalDate型に変換
                    lastLoginDate = lastLoginTimestamp.toLocalDateTime().toLocalDate();
                }
                
                // 差分を計算
                if (lastLoginDate != null) {
                	// 何日ぶりのログインかの計算処理
                    long daysBetween = ChronoUnit.DAYS.between(lastLoginDate, today);
                    
                    // 昨日の場合
                    if (daysBetween == 1) {
                        account.setConsecutiveLogins(account.getConsecutiveLogins() + 1);
                    } else if (daysBetween > 1) {
                        account.setConsecutiveLogins(1);	// 再スタート
                    }
                } else {
                    account.setConsecutiveLogins(1); 		// 初回ログイン
                }

                // ログイン日時を現在に更新
                account.setLoginAt(Timestamp.valueOf(LocalDateTime.now()));
                
                // 初回ログイン判定（前回ログイン日時がnullなら初回）
                boolean isFirstLogin = (lastLoginTimestamp == null);
                
                // DBにアカウント情報を反映
                accountDAO.updateAccount(account);
                
                // セッション登録
                HttpSession session = request.getSession();
                session.setAttribute("login_user", account);
                
                // 初回ログインならチュートリアル画面へ
                if (isFirstLogin) {
                    session.setAttribute("show_tutorial", true);
                    response.sendRedirect("TutorialServlet");
                } else {
                    response.sendRedirect("TopPageServlet");
                }
            } else {
                // ログイン失敗（ユーザー情報がnull）
                request.setAttribute("loginErrorMessage", "入力内容が間違っています。");
                request.setAttribute("beforeEmail", email);
                request.setAttribute("beforePassword", password);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            // DB例外・その他予期しないエラー時
            request.setAttribute("loginErrorMessage", "ユーザー情報の確認中にエラーが発生しました");
            request.setAttribute("beforeEmail", email);
            request.setAttribute("beforePassword", password);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
