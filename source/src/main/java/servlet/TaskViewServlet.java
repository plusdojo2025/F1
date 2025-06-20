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
import dto.TaskList;

/**
 * タスク一覧画面を表示するサーブレット
 * GETでアクセスされた場合にログインユーザーのタスク情報を全件取得し、task_edit.jspへフォワードする
 */
@WebServlet("/TaskViewServlet")
public class TaskViewServlet extends HttpServlet {
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
        List<TaskList> taskList = new ArrayList<>();
        List<Mood> moodList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        
        // DAOの生成
        TasksDAO tasksDAO = new TasksDAO();
		MoodDAO moodDAO = new MoodDAO();
		CategoryDAO categoryDAO = new CategoryDAO();
        
	    try {
	    	taskList = tasksDAO.selectAll(login_user.getAccountId());
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
	    request.setAttribute("taskList", taskList);
	    request.setAttribute("moodList", moodList);
	    session.setAttribute("categoryList", categoryList);
        // タスク一覧ページへフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/task_view.jsp").forward(request, response);
	    
	}

}
