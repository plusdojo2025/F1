package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CategoryDAO;
import dao.LogDAO;
import dao.MoodDAO;
import dto.Account;
import dto.Category;
import dto.Log;
import dto.Mood;

/**
 * チュートリアル画面表示用サーブレット
 */
@WebServlet("/TutorialServlet")
public class TutorialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 文字コード・コンテンツタイプを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // セッション取得・セッション内のデータを取得
        HttpSession session = request.getSession();
        Account login_user = (Account) session.getAttribute("login_user");
	    Log currentLog = (Log) session.getAttribute("currentLog");
	    
        if (login_user == null) {
            // 未ログインはログイン画面へリダイレクト
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return; // ここで処理終了
        }

	    // セッションに実行中ログ情報がある場合
	    if (currentLog != null) {
	    	session.removeAttribute("currentLog");
	    }
	    
        // リストの生成
        List<Mood> moodList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        
		// DAOの生成
		LogDAO logDAO = new LogDAO();
		MoodDAO moodDAO = new MoodDAO();
		CategoryDAO categoryDAO = new CategoryDAO();
		
		String sumDuration;
		
	    try {
	    	// １日の総合計すきま活用時間の取得
	    	// sumDurationのnullチェック（nullだったら0を代入）
	    	sumDuration = logDAO.setDuration(Optional.ofNullable(logDAO.sumDayDuration(login_user.getAccountId())).orElse(0));
	    	moodList = moodDAO.getMoodList();
	    	categoryList = categoryDAO.getCategoryList();
	    	
		} catch (Exception e) {
			e.printStackTrace();
            request.setAttribute("errorMessage", "アカウント情報の取得中にエラーが発生しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
            return;
		}
	    
	    // リクエストスコープにセット
	    session.setAttribute("sumDuration", sumDuration);
	    session.setAttribute("moodList", moodList);
	    session.setAttribute("categoryList", categoryList);
        
        // チュートリアルフラグONを渡す
        session.setAttribute("tutorial", "on");

        // チュートリアルページへフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/top_page.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("tutorial", "off");
        response.setStatus(HttpServletResponse.SC_OK); // 200でOK返すだけ
    }


}
