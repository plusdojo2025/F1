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

import dao.CategoryDAO;
import dao.MoodDAO;
import dao.TasksDAO;
import dto.Account;
import dto.Category;
import dto.Mood;
import dto.Task;

/**
 * タスク新規登録画面を表示するサーブレット
 * GETでアクセスされた場合に、task_regist.jspへフォワードする
 */
@WebServlet("/TaskRegistServlet")
public class TaskRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * GETリクエスト時の処理
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コード・コンテンツタイプを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // セッション取得・セッション内のデータを取得
        HttpSession session = request.getSession();
        Account login_user = (Account) session.getAttribute("login_user");
        
        // 未ログインはログイン画面へリダイレクト
        if (login_user == null) {
            response.sendRedirect("LoginServlet");
            return; // ここで処理終了
        }
        
        // リストの生成
        List<Mood> moodList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        
        // DAOの生成
        MoodDAO moodDAO = new MoodDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        
	    try {
	    	moodList = moodDAO.getMoodList();
	    	categoryList = categoryDAO.getCategoryList();	    	
		} catch (Exception e) {
			e.printStackTrace();
            request.setAttribute("errorMessage", "情報の取得中にエラーが発生しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
            return;
		}
	    
	    // リクエストスコープにセット
	    request.setAttribute("moodList", moodList);
	    request.setAttribute("categoryList", categoryList);
        // タスク一覧ページへフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/task_regist.jsp").forward(request, response);
	   }

    /**
     * 新規タスク登録実行処理
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 文字コード・コンテンツタイプを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // セッション取得・セッション内のデータを取得
        HttpSession session = request.getSession();
        Account login_user = (Account) session.getAttribute("login_user");
        
        // 未ログインはログイン画面へリダイレクト
        if (login_user == null) {
            response.sendRedirect("LoginServlet");
            return; // ここで処理終了
        }
		
        // リクエストパラメーターの取得
		String title = request.getParameter("title");
		int timeSpan  = Integer.parseInt(request.getParameter("timeSpan"));
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		int moodId = Integer.parseInt(request.getParameter("moodId"));
		Boolean isPrivate = Boolean.parseBoolean(request.getParameter("isPrivate"));

		// Taskオブジェクトの生成
		Task newTask = new Task();
		
		// 情報をTaskオブジェクトにセット
		newTask.setAccountId(login_user.getAccountId());
		newTask.setTitle(title);
		newTask.setTimeSpan(timeSpan);
		newTask.setCategoryId(categoryId);
		newTask.setMoodId(moodId);
		newTask.setIsPrivate(isPrivate);
		
		// TasksDAOの生成
		TasksDAO tasksDAO = new TasksDAO();
		
		// DB登録判定フラグ
		boolean result = false;
		
		try {
	    	// アカウント情報をDBに登録
			result = tasksDAO.registTask(newTask);
	    
			if (result) {
				// 登録成功：TaskViewServletへリダイレクト
				response.sendRedirect("TaskViewServlet");
			} else {
				// 登録失敗:エラー画面
				request.setAttribute("errorMessage", "登録に失敗しました。もう一度お試しください。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
            request.setAttribute("errorMessage", "情報の登録中にエラーが発生しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
            return;
		}
	}

}