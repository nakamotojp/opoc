package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansSubject;

/**
 * Servlet implementation class DeleteSubject
 */
public class DeleteSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSubject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//セッション情報を取得する
		HttpSession session = request.getSession(true);
		
		if(!session.getAttribute("role").equals("manager")){
			//管理者権限がない
			response.sendRedirect("");
			return;
		
		}
		
		String s_id = request.getParameter("s_id");
		
		if(s_id.equals(null)){
		
			// リダイレクト
			response.sendRedirect("");
			return;
		}
		
		BeansSubject.deleteSubject(Integer.parseInt(s_id));
		// リダイレクト
		response.sendRedirect("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
