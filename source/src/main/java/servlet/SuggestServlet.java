package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * 入力されたデータを基に自身のタスクリスト・他の人のタスクリストを取得。
 * 提案実行画面へリダイレクト。
 */
@WebServlet("/SuggestServlet")
public class SuggestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        // 文字コード・コンテンツタイプを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // ログインページへフォワード
        response.sendRedirect(request.getContextPath() + "/LoginServlet");
	}
	
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
	    
	    // 変数定義
	    int spanTime;
	    int moodId;
	    int categoryId;
	    
	    try {
	    	// リクエストパラメーターの取得
	        spanTime = Integer.parseInt(request.getParameter("spanTime"));
	        moodId = Integer.parseInt(request.getParameter("moodId"));
		    categoryId = Integer.parseInt(request.getParameter("categoryId"));
	    } catch (NumberFormatException e) {
	        request.setAttribute("errorMessage", "数値の形式が正しくありません。");
	        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
	        return;
	    }
        // リストの生成
        List<Task> suggestTaskList = new ArrayList<>();
        List<Task> suggestOtherTaskList = new ArrayList<>();
	     
		// DAOの生成
		TasksDAO tasksDAO = new TasksDAO();
		
		try {
			suggestTaskList = tasksDAO.suggestTask(loginAccount.getAccountId(), spanTime, moodId, categoryId);
			suggestOtherTaskList = tasksDAO.suggestOtherTask(loginAccount.getAccountId(), spanTime, moodId, categoryId);
			
		} catch (Exception e) {
			e.printStackTrace();
            request.setAttribute("errorMessage", "エラーが発生しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
            return;
		}
		
	    // リクエストスコープにセット
	    request.setAttribute("suggestTaskList", suggestTaskList);
	    request.setAttribute("suggestOtherTaskList", suggestOtherTaskList);

	    // 提案実行画面へフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/suggest.jsp").forward(request, response);
	}

}
