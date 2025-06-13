package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LogDAO;
import dto.Account;
import dto.Log;

/**
 * 結果画面表示、タスク完了処理用。
 * タスク結果画面(タスク名、所要時間を表示)へリダイレクト。
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * POSTリクエスト時の処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    // 文字コード設定
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    // セッション取得・アカウント情報・実行中ログ情報の取得
	    HttpSession session = request.getSession();
	    Account loginAccount = (Account) session.getAttribute("login_user"); 
	    Log currentLog = (Log) session.getAttribute("currentLog");
	     
	    // セッションにアカウント情報が無い場合
	    if (loginAccount == null) {
	   	    response.sendRedirect("LoginServlet");
	   	    return;
	    }
	    // セッションに実行中ログ情報が無い場合
	    if (currentLog == null) {
	        request.setAttribute("errorMessage", "実行中ログがセッション切れです。");
	        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
	        return;
	    }
	    
	    // リクエストパラメーターの取得	    
	    int satisfactionLevel;
	    try {
	    	satisfactionLevel = Integer.parseInt(request.getParameter("satisfactionLevel"));
	    } catch (NumberFormatException e) {
	        // 数字でなかった場合の処理
	        request.setAttribute("errorMessage", "不正なデータが送信されました。");
	        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
	        return;
	    }
	    
	    // DAOの生成
 		LogDAO logDAO = new LogDAO();
	    
 		// データをセット
 		currentLog.setSatisfactionLevel(satisfactionLevel);
	    
	    // 結果用の変数定義
	    boolean result = false;
	    
	    // DBに内容を反映
		try {
			// 実行中ログ情報をDBに登録
			result = logDAO.registLog(currentLog);
		} catch (Exception e) {
			e.printStackTrace();
            request.setAttribute("errorMessage", "エラーが発生しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
            return;
		}
		
		if (result) {
		    // 登録成功：TOPページへリダイレクト
		    response.sendRedirect("TopPageServlet");
		} else {
			// 登録失敗:エラー画面
			request.setAttribute("errorMessage", "登録に失敗しました。もう一度お試しください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
