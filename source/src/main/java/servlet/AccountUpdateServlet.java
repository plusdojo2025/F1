package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dto.Account;

/**
 * Servlet implementation class AccountUpdateServlet
 */
@WebServlet("/AccountUpdateServlet")
public class AccountUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountUpdateServlet() {
        super();
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
		HttpSession session = request.getSession();
		
		try {
			

		//更新を行う
		AccountDAO aDao = new AccountDAO();
		Account account = (Account)session.getAttribute("account");
		if(aDao.updateAccount(account)) {
			// 更新成功
			
			session.setAttribute("login_user", account);
			session.removeAttribute("account");
			response.sendRedirect("AccountServlet");
		}
		
		}catch (Exception e) {
            // DB例外・その他予期しないエラー時
            request.setAttribute("AccountUpdateErrorMassage", "ユーザー情報の変更中にエラーが発生しました");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            dispatcher.forward(request, response);
        }
		
	}

}
