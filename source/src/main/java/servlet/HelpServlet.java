package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HelpServlet
 */
@WebServlet("/HelpServlet")
public class HelpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// もしもログインしていなかったらログインサーブレットにリダイレクトする
    	HttpSession session = request.getSession();
    		if (session.getAttribute("login_user") == null) {
    			response.sendRedirect(request.getContextPath() + "/LoginServlet");
    			return; // ここで処理終了
    		}
    			
        // JSPに渡すヘルプメッセージ
        request.setAttribute("helpMessage", "アプリケーションの説明");

        // help.jsp へフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/help.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}