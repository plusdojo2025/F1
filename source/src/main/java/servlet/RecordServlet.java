package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LogDAO;
import dto.Account;
import dto.Category;
import dto.LogList;
import dto.Mood;

/**
 * Servlet implementation class RecordServlet
 */
@WebServlet("/RecordServlet")
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("login_user") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return; // ここで処理終了
		}
		
		//文字コード設定
		request.setCharacterEncoding("UTF-8");
		Account account = (Account)session.getAttribute("login_user");
		
		//集計処理を行う
		LogDAO ldao = new LogDAO();
		String durationSum = ldao.setDuration(ldao.sumDuration(account.getAccountId()));
		Category mostCategory = ldao.getMaxCategory(account.getAccountId());
		Mood mostMood = ldao.getMaxMood(account.getAccountId());
		
		// JSPに渡すlogList変数の定義
		List<LogList> logList = null;
		try {
			logList = ldao.selectLogs(account.getAccountId());
		} catch (Exception e) {
			e.printStackTrace();
            request.setAttribute("errorMessage", "データベース接続中にエラーが発生しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
            return;
		}
		
		//リクエストスコープにそれぞれデータを格納
		request.setAttribute("durationSum",durationSum);
		request.setAttribute("mostCategory",mostCategory);
		request.setAttribute("mostMood",mostMood);
		request.setAttribute("history", logList);
		
		//実績画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/record.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
