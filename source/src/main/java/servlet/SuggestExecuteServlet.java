package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TasksDAO;
import dto.Account;
import dto.Log;
import dto.Task;

/**
 * 提案実行画面の初期表示、提案データ選択時の処理用。
 * 選択されたタスクを取得し、実行画面(選択したタスクタイトルと、カウントアップタイマーを表示)へリダイレクト。
 */
@WebServlet("/SuggestExecuteServlet")
public class SuggestExecuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 /**
	  * POSTリクエスト時の処理
	  */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 文字コード設定
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    // セッション取得・アカウント情報の取得
	    HttpSession session = request.getSession();
	    Account loginAccount = (Account) session.getAttribute("login_user");
	     
	    // セッションにアカウント情報が無い場合
	    if (loginAccount == null) {
	   	    response.sendRedirect(request.getContextPath() + "/LoginServlet");
	   	    return;
	    }
	    
	    // リクエストパラメーターの取得
	    int taskId;
	    try {
	        taskId = Integer.parseInt(request.getParameter("taskId"));
	    } catch (NumberFormatException e) {
	        // 数字でなかった場合の処理
	        request.setAttribute("errorMessage", "不正なタスクIDが送信されました。");
	        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
	        return;
	    }
	    
        // タスクオブジェクト・実行中ログオブジェクトの生成
	    Task task = new Task();
	    Log currentLog = new Log();
        
		// DAOの生成
		TasksDAO tasksDAO = new TasksDAO();
		
		try {
			// 該当タスクの取得
			task = tasksDAO.getTask(taskId);
		} catch (Exception e) {
			e.printStackTrace();
            request.setAttribute("errorMessage", "エラーが発生しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
            return;
		}
		
		// logオブジェクトにデータをセット
		currentLog.setAccountId(loginAccount.getAccountId());
		currentLog.setTaskTitle(task.getTitle());
		currentLog.setMoodId(task.getMoodId());
		currentLog.setCategoryId(task.getCategoryId());
		currentLog.setLogTime(Timestamp.valueOf(LocalDateTime.now()));
		currentLog.setTask(task);
		
	    // セッションスコープにLogオブジェクトをセット
		session.setAttribute("currentLog", currentLog);

	    // 提案実行画面へフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/execute.jsp").forward(request, response);
	
	}

}
