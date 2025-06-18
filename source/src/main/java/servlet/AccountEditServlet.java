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
 * Servlet implementation class AccountEditServlet
 */
@WebServlet("/AccountEditServlet")
public class AccountEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String flagPrm =  request.getParameter("flag");
		
		int flag = 0;
		
		if (flagPrm != null) {
			flag =  Integer.parseInt(flagPrm);
		}
		
		if(flag == 1) {
			HttpSession session = request.getSession();
			Account account = (Account)session.getAttribute("login_user");	
			session.setAttribute("account", account);
		}
		//アカウント情報変更画面へフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account_edit.jsp");
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
