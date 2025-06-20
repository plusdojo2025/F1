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
import dto.Task;

/**
 * タスク情報の変更実行サーブレット
 */
@WebServlet("/TaskEditServlet")
public class TaskEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//UTF-8設定
	     request.setCharacterEncoding("UTF-8");
	     response.setContentType("text/html; charset=UTF-8");

	     // 未ログインユーザー進入時にログイン画面に飛ばす処理
	     HttpSession session = request.getSession();
	     Account login_user = (Account) session.getAttribute("login_user");
	     if (login_user == null) {
           response.sendRedirect("LoginServlet");
           return;
	     }
	     
	     // リクエストスコープからパラメーターを取得
	     String title = request.getParameter("title");
	     int timeSpan = Integer.parseInt(request.getParameter("timeSpan"));
	     int moodId = Integer.parseInt(request.getParameter("moodId"));
	     int categoryId = Integer.parseInt(request.getParameter("categoryId"));
	     Boolean isPrivate = Boolean.parseBoolean(request.getParameter("isPrivate"));
	     int taskId = Integer.parseInt(request.getParameter("taskId"));

	     // 変更内容用のオブジェクト生成
	     Task editTask = new Task();
	     
	     // 引数をオブジェクトにセット
	     editTask.setTaskId(taskId);
	     editTask.setTitle(title);
	     editTask.setTimeSpan(timeSpan);
	     editTask.setCategoryId(moodId);
	     editTask.setMoodId(categoryId);
	     editTask.setIsPrivate(isPrivate);
	     
	     // DAOの生成
	     TasksDAO tasksDAO = new TasksDAO();
	     
	     // 更新処理結果の判定用変数
	     boolean result = false;
	     
	     try{
	    	 // 更新後のタスク情報をDBに登録
	    	result = tasksDAO.updateTask(editTask);
	    	
	    	if(result == true) {
	    		// 更新成功：タスク一覧に戻る	
	    		response.sendRedirect("TaskViewServlet");
	    	}else {
	    		// 更新失敗：エラー画面へ
	    		request.setAttribute("errorMessage", "登録に失敗しました。もう一度お試しください。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
	    	}
	     } catch (Exception e) {		//コピペの為詳しい挙動は不明（コピー元：TaskRegistServlet）
			e.printStackTrace();
	        request.setAttribute("errorMessage", "情報の登録中にエラーが発生しました。");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
	        dispatcher.forward(request, response);
	        return;
		}
	     
	}

}
