package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansLogin;

/**
 * Servlet implementation class DeleteUser
 */
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
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
			
		String l_id = request.getParameter("l_id");
		
		if(l_id.equals(null)){
		
			// リダイレクト
			response.sendRedirect("");
			return;
		}
				
		BeansLogin.deleteLogin(l_id);
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
