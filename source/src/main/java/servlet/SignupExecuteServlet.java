package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dao.MasterTaskDAO;
import dao.TasksDAO;
import dto.Account;
import dto.MasterTask;
import dto.Task;

/**
 * アカウントの新規登録の実行処理をするサーブレット
 * login.jspへリダイレクトする
 */
@WebServlet("/SignupExecuteServlet")
public class SignupExecuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 /**
	  * POSTリクエスト時の処理
	  * セッションの情報を基にDBにアカウントを新規登録する。
	  */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // セッションスコープから新規登録アカウントデータを取得 
		HttpSession session = request.getSession();
		Account signupAccount = (Account) session.getAttribute("signup_user");
		
		// アカウントDAOの生成
		AccountDAO accountDAO = new AccountDAO();
		int result = 0;

	    try {
	    	// アカウント情報をDBに登録
	    	result = accountDAO.registAccount(signupAccount);
	    	
			if (result != 0) {
				// マスタータスクを取得
				MasterTaskDAO masterTaskDAO = new MasterTaskDAO();
				List<MasterTask> masterTasks = masterTaskDAO.selectAll();

				// タスクDAOを使ってタスクをアカウント用に登録
				TasksDAO taskDAO = new TasksDAO();
				for (MasterTask masterTask : masterTasks) {
					// 複製用のタスクインスタンスを作成
					Task userTask = new Task();
					userTask.setAccountId(result);
					userTask.setTitle(masterTask.getTitle());
					userTask.setTimeSpan(masterTask.getTimeSpan());
					userTask.setMoodId(masterTask.getMoodId());
					userTask.setCategoryId(masterTask.getCategoryId());
					userTask.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
					userTask.setIsPrivate(false);

					// 登録処理
					taskDAO.registTask(userTask);
				}
				
				// 登録成功：セッション情報の削除・LoginServletへリダイレクト
				session.removeAttribute("signup_user");
				response.sendRedirect("LoginServlet");
			} else {
				// 登録失敗:エラー画面
				request.setAttribute("errorMessage", "登録に失敗しました。もう一度お試しください。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
            request.setAttribute("errorMessage", "アカウント情報の登録中にエラーが発生しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
            return;
		}
	    

	}

}
