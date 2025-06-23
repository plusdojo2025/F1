package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        
        // チュートリアルフラグONを渡す
        HttpSession session = request.getSession();
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
