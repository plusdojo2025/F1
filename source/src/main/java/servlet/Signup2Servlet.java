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

import dao.AccountDAO;
import dao.CategoryDAO;
import dto.Account;
import dto.Category;


/**
 * 新規登録２画面を表示するサーブレット
 * GETでアクセスされた場合にsignup2.jspへフォワードする
 */
@WebServlet("/Signup2Servlet")
public class Signup2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	    // GETメソッド：戻るボタンでここに来る
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	        request.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html; charset=UTF-8");
	        
	        HttpSession session = request.getSession();
	        Account signupAccount = (Account) session.getAttribute("signup_user");
	        
	        if(signupAccount !=null) {
	        	request.setAttribute("beforeName", signupAccount.getNickname());
	        	request.setAttribute("beforeCategory", signupAccount.getCategoryId());
	        	request.setAttribute("beforeGoalDetail", signupAccount.getGoalDetail());
	        }
	        
	        CategoryDAO categoryDAO = new CategoryDAO();
	        request.setAttribute("categoryList", categoryDAO.getCategoryList());
	        
	        // 前回入力された情報をセッションなどから渡すならここで行う
	        request.getRequestDispatcher("/WEB-INF/jsp/signup2.jsp").forward(request, response);
	    }


    /**
     * POSTリクエスト時の処理
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        // 文字コード設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConf = request.getParameter("passwordConf");
        
        //メールアドレスの重複チェック
        AccountDAO aDao = new AccountDAO();
        if(!aDao.existsAccount(email)) {
        	request.setAttribute("sameEmail", "すでに使用されているメールアドレスです。");
        	
        	request.setAttribute("beforeEmail", email);
            request.setAttribute("beforePassword", password);
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        }
        
        // 未入力チェック処理
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty() || passwordConf == null || passwordConf.trim().isEmpty()) {
            request.setAttribute("errorMassage", "全ての項目を入力してください。");

            request.setAttribute("beforeEmail", email);
            request.setAttribute("beforePassword", password);
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        }
        
        // passwordとpasswordConfが一致しない場合の処理
        if (!password.equals(passwordConf)) {
            request.setAttribute("notEqualsErrorMassage", "パスワードが一致していません。");

            request.setAttribute("beforeEmail", email);
            request.setAttribute("beforePassword", password);
            request.getRequestDispatcher("/WEB-INF/jsp/signup2.jsp").forward(request, response);
            return;
        }

        // アカウントオブジェクトをインスタンス化
        Account account = new Account();
        // 入力情報をセット
        account.setEmail(email);
        account.setPassword(password);
        
        // リストの生成
        List<Category> categoryList = new ArrayList<>();
        // DAOの生成
        CategoryDAO categoryDAO = new CategoryDAO();
	    try {
	    	categoryList = categoryDAO.getCategoryList();
	    	
		} catch (Exception e) {
			e.printStackTrace();
            request.setAttribute("errorMessage", "アカウント情報の取得中にエラーが発生しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
            return;
		}

        HttpSession session = request.getSession();
        session.setAttribute("signup_user", account);
        request.setAttribute("categoryList", categoryList);
        // JSPへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signup2.jsp");
        dispatcher.forward(request, response);
            
    }
        
}
