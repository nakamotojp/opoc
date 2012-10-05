/**
 * 認証 パッケージ
 */
package jp.bemax.se.graduation2011.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author kouzuki
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServlet;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @see javax.servlet.http.HttpSession;
 */
public class RegisteredConf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisteredConf() {
        super();
        // TODO Auto-generated constructor stub
    }
    //GETで取得するとログインページに差し戻す
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// エンコード・コンテンツタイプ指定
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
	
		response.sendRedirect("o_user_login.jsp");
		return;
    }
    	
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		//受け取ったリクエストをutf-8へ
		request.setCharacterEncoding("utf-8");
		
		String l_pass = request.getParameter("l_pass");
		HttpSession session = request.getSession();
		
		if(session != null){
			
			session = request.getSession(true);
			//passwordをセッションで隠ぺいする
			session.setAttribute("l_pass", l_pass);
			
			getServletContext().getRequestDispatcher("/o_newly_st_registration_conf.jsp").forward(request, response);
		}
	}
}