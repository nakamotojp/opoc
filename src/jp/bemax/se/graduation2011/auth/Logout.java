package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Cookie[] cookary = request.getCookies();
		if(cookary != null){
			for(int i =0; i < cookary.length; i++){
				if(cookary[i].getName().equals("session_id")){
					cookary[i].setMaxAge(0);
					response.addCookie(cookary[i]);
					break;
				}
			}
			
			for(int i =0; i < cookary.length; i++){
				if(cookary[i].getName().equals("role")){
					cookary[i].setMaxAge(0);
					response.addCookie(cookary[i]);
					break;
				}
			}
			
			for(int i =0; i < cookary.length; i++){
				if(cookary[i].getName().equals("l_id")){
					cookary[i].setMaxAge(0);
					response.addCookie(cookary[i]);
					break;
				}
			}
			
			for(int i =0; i < cookary.length; i++){
				if(cookary[i].getName().equals("login")){
					cookary[i].setMaxAge(0);
					response.addCookie(cookary[i]);
					break;
				}
			}
		}
		session.removeAttribute("login");
		session.removeAttribute("role");
		session.removeAttribute("l_id");
		session.invalidate();
		
		response.sendRedirect("o_user_login.jsp");
	}
}
