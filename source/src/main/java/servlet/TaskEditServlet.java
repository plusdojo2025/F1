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
 * Servlet implementation class TaskEditServlet
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
	     
	     //引数取得
	     String getTitle = request.getParameter("getTitle");
	     int getTimeSpan = Integer.parseInt(request.getParameter("getTimeSpan"));
	     int getMoodId = Integer.parseInt(request.getParameter("getMoodId"));
	     int getCategoryId = Integer.parseInt(request.getParameter("getCategoryId"));
	     Boolean getIsPrivate = Boolean.parseBoolean(request.getParameter("getIsPrivate"));
	     int getTaskId = Integer.parseInt(request.getParameter("getTaskId"));

	     //変更内容用のオブジェクト生成
	     Task editTask = new Task();
	     
	     //引数をオブジェクトに
	     editTask.setTitle(getTitle);
	     editTask.setTimeSpan(getTimeSpan);
	     editTask.setCategoryId(getCategoryId);
	     editTask.setMoodId(getMoodId);
	     editTask.setIsPrivate(getIsPrivate);
	     editTask.setTaskId(getTaskId);
	     
	     //更新処理内容
	     TasksDAO tasksDAO = new TasksDAO();
	     
	     boolean result = false;
	     
	     try{
	    	 //更新後のタスク情報をDBに登録
	    	result = tasksDAO.updateTask(editTask);
	    	
	    	if(result == true) {
	    		//更新が正常終了すればタスク一覧に戻る	
	    		response.sendRedirect("TaskViewServlet");
	    	}else {
	    		//何らかの要因で更新処理が行われなかった場合、エラー画面へ
	    		request.setAttribute("errorMessage", "登録に失敗しました。もう一度お試しください。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
	    	}
	     }catch (Exception e) {		//コピペの為詳しい挙動は不明（コピー元：TaskRegistServlet）
			e.printStackTrace();
	        request.setAttribute("errorMessage", "情報の登録中にエラーが発生しました。");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
	        dispatcher.forward(request, response);
	        return;
		}
	     
	}

}
