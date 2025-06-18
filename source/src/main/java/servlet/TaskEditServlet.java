package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TasksDAO;
import dto.Account;

/**
 * Servlet implementation class TaskEditServlet
 */
@WebServlet("/TaskEditServlet")
public class TaskEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
	     
	     //更新処理内容
	     TasksDAO tasksDAO = new TasksDAO();
	     
	     boolean result = false;
	     
	}

}
