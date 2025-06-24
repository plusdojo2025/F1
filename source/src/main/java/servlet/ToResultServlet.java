package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Account;
import dto.Log;

/**
 * 結果画面表示用。
 * タスク結果画面(タスク名、所要時間を表示)へリダイレクト。
 */
@WebServlet("/ToResultServlet")
public class ToResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// もしもログインしていなかったらログインサーブレットにリダイレクトする
	HttpSession session = request.getSession();
		if (session.getAttribute("login_user") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
		     return; // ここで処理終了
		}
	}
	
	/**
	 * POSTリクエスト時の処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 文字コード設定
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    // セッション取得・アカウント情報・実行中ログ情報の取得
	    HttpSession session = request.getSession();
	    Account loginAccount = (Account) session.getAttribute("login_user"); 
	    Log currentLog = (Log) session.getAttribute("currentLog");
	     
	    // セッションにアカウント情報が無い場合
	    if (loginAccount == null) {
	    	response.sendRedirect(request.getContextPath() + "/LoginServlet");
	   	    return;
	    }
	    // セッションに実行中ログ情報が無い場合
	    if (currentLog == null) {
	        request.setAttribute("errorMessage", "実行中ログがセッション切れです。");
	        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
	        return;
	    }
	    
	    // リクエストパラメーターの取得	    
	    int duration;
	    try {
	    	duration = Integer.parseInt(request.getParameter("duration"));
	    } catch (NumberFormatException e) {
	        // 数字でなかった場合の処理
	    	e.printStackTrace();
	        request.setAttribute("errorMessage", "不正なデータが送信されました。");
	        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
	        return;
	    }
	    
		// データをセット
 		currentLog.setDuration(duration);

	    // セッションスコープのLogオブジェクトを更新
		session.setAttribute("currentLog", currentLog);
		
		// 提案実行画面へフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
	
    }

}
