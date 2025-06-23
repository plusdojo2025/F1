package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TasksDAO;
import dto.Account;

/**
 * タスク削除処理をするサーブレット
 * POSTでアクセスされた場合にtask_idを基にタスクを削除、TaskViewServletへリダイレクトする
 */
@WebServlet("/TaskDeleteServlet")
public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 /**
	  * POSTリクエスト時の処理
	  */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     // 文字コード設定
	     request.setCharacterEncoding("UTF-8");
	     response.setContentType("text/html; charset=UTF-8");

	     // セッション取得・新規作成途中アカウント情報の取得
	     HttpSession session = request.getSession();
	     Account login_user = (Account) session.getAttribute("login_user");
	     
	     // 未ログインはログイン画面へリダイレクト
	     if (login_user == null) {
            response.sendRedirect("LoginServlet");
            return; // ここで処理終了
	     }
	     
        // リクエストパラメーターの取得
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		
		// TasksDAOの生成
		TasksDAO tasksDAO = new TasksDAO();
		
		// DB登録判定フラグ
		boolean result = false;
		
		try {
	    	// タスクIDを基にDBにのレコードを削除
			result = tasksDAO.deleteTask(taskId);
	    
			if (result) {
				// 登録成功：TaskViewServletへリダイレクト
				response.sendRedirect(request.getContextPath() + "/TaskViewServlet");
			} else {
				// 登録失敗:エラー画面
				request.setAttribute("errorMessage", "削除に失敗しました。もう一度お試しください。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
            request.setAttribute("errorMessage", "情報の更新中にエラーが発生しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
            return;
		}
	}

}
