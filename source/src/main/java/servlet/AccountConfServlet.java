package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Account;

/**
 * Servlet implementation class AccountConfServlet
 */
@WebServlet("/AccountConfServlet")
public class AccountConfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountConfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメーターを取得する
		request.setCharacterEncoding("UTF-8");
		int accountId  = Integer.parseInt(request.getParameter("accountId "));
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String goalDetail = request.getParameter("goalDetail");
		
							 
		// 未入力チェック処理
		if (nickname == null || nickname.trim().isEmpty() || email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			if (nickname == null || nickname.trim().isEmpty()) {
				request.setAttribute("nicknameErrorMassage", "この項目を入力してください。");
		            }
		   	if (email == null || email.trim().isEmpty()) {
		   		request.setAttribute("emailErrorMassage", "この項目を入力してください。");
		   	}
		   	if (password == null || password.trim().isEmpty()) {
		   		request.setAttribute("passwordErrorMassage", "この項目を入力してください。");
		   	}
		   	
		        }
		        
		// アカウントオブジェクトをインスタンス化
		Account account = new Account();
		account.setAccountId(accountId); 
		account.setEmail(email);
		account.setPassword(password);
		account.setNickname(nickname);
		account.setCategoryId(categoryId);
		account.setGoalDetail(goalDetail);
		
		
		         
		//更新内容をリクエストスコープに格納する
		HttpSession session = request.getSession();
		session.setAttribute("account", account);
		
		//確認画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account_conf.jsp");
		dispatcher.forward(request, response);	}
	
}
