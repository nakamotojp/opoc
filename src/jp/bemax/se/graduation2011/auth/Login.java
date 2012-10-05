/**
 * 認証 パッケージ
 */
package jp.bemax.se.graduation2011.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansUser;

/**
 * @author kouzuki
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServlet;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @see javax.servlet.http.HttpSession;
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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
		
		//xss対策クラスのインスタンス化
		Xss xss = new Xss();
		
		String l_id = xss.escape(request.getParameter("l_id"));
		String l_pass = request.getParameter("l_pass");
		String checkCookie = request.getParameter("checkCookie");
		
		HttpSession session = request.getSession();
				
		session.removeAttribute("login");
		session.removeAttribute("role");
		session = request.getSession();
		
		if(session == null){
			
			session = request.getSession(true);
			
			Auth auth = new Auth();
			if(auth.checkLogin(l_id, l_pass, session.getAttribute("login"))){
				
				session.setAttribute("login", "OK");
				session.setAttribute("l_id", l_id);
				BeansUser user = BeansUser.detailUser(l_id);
				session.setAttribute("u_name", user.getU_name());
				
				if(auth.getUrl().equals(null)){
					((HttpServletResponse)response).sendRedirect("./../error_role.html");
				}else{
					
					//cookieを保持させる
					if(checkCookie != null){
						if(checkCookie.equals("true")){
							Cookie cookie = new Cookie("session_id", session.getId());
							cookie.setMaxAge(60*60*24*7);
							response.addCookie(cookie);
							Cookie cookie2 = new Cookie("role", auth.getRole());
							cookie2.setMaxAge(60*60*24*7);
							response.addCookie(cookie2);
							Cookie cookie3 = new Cookie("l_id", l_id);
							cookie3.setMaxAge(60*60*24*7);
							response.addCookie(cookie3);
							Cookie cookie4 = new Cookie("login", "OK");
							cookie4.setMaxAge(60*60*24*7);
							response.addCookie(cookie4);
						}
					}
					
					session.setAttribute("pageback", "OK");
					session.setAttribute("role", auth.getRole());
					response.sendRedirect(auth.getUrl());
				}			
			}else{
				session.setAttribute("status", "Not Auth");
				//認証ページへ（ユーザ情報不一致）
				request.setAttribute("fail_login", "true");
				getServletContext().getRequestDispatcher("/o_user_login.jsp").forward(request, response);
			}
		}else{
			Object loginCheck = session.getAttribute("login");
			Auth auth = new Auth();
			if(loginCheck == null){
			
				if(auth.checkLogin(l_id, l_pass, loginCheck)){
					
					session.setAttribute("login", "OK");
					session.setAttribute("role", auth.getRole());
					session.setAttribute("l_id", l_id);
					BeansUser user = BeansUser.detailUser(l_id);
					session.setAttribute("u_name", user.getU_name());
					if(auth.getUrl().equals(null)){
						((HttpServletResponse)response).sendRedirect("./../error_role.html");
					}else{
						//cookieを保持させる
						if(checkCookie != null){
							if(checkCookie.equals("true")){
								Cookie cookie = new Cookie("session_id", session.getId());
								cookie.setMaxAge(60*60*24*7);
								response.addCookie(cookie);
								Cookie cookie2 = new Cookie("role", auth.getRole());
								cookie2.setMaxAge(60*60*24*7);
								response.addCookie(cookie2);
								Cookie cookie3 = new Cookie("l_id", l_id);
								cookie3.setMaxAge(60*60*24*7);
								response.addCookie(cookie3);
								Cookie cookie4 = new Cookie("login", "OK");
								cookie4.setMaxAge(60*60*24*7);
								response.addCookie(cookie4);
							}
						}
						
						session.setAttribute("pageback", "OK");
						response.sendRedirect(auth.getUrl());
					}	
				}else{
					
					session.setAttribute("status", "Not Auth");
					//認証ページへ（ユーザ情報不一致）
					request.setAttribute("fail_login", "true");
					getServletContext().getRequestDispatcher("/o_user_login.jsp").forward(request, response);
					
				}
				
			} else {

				if(auth.checkLogin(l_id, l_pass, loginCheck)){
				
					if(auth.getUrl() == null){
						
						request.setAttribute("role", auth.getRole());
						((HttpServletResponse)response).sendRedirect(auth.getUrl());
						
					}else{
						
						//cookieを保持させる
						if(checkCookie != null){
							if(checkCookie.equals("true")){
								Cookie cookie = new Cookie("session_id", session.getId());
								cookie.setMaxAge(60*60*24*7);
								response.addCookie(cookie);
								Cookie cookie2 = new Cookie("role", auth.getRole());
								cookie2.setMaxAge(60*60*24*7);
								response.addCookie(cookie2);
								Cookie cookie3 = new Cookie("l_id", l_id);
								cookie3.setMaxAge(60*60*24*7);
								response.addCookie(cookie3);
								Cookie cookie4 = new Cookie("login", "OK");
								cookie4.setMaxAge(60*60*24*7);
								response.addCookie(cookie4);
							}
						}
						
						session.setAttribute("pageback", "OK");
						session.setAttribute("login", "OK");
						session.setAttribute("role", auth.getRole());
						session.setAttribute("l_id", l_id);
						BeansUser user = BeansUser.detailUser(l_id);
						session.setAttribute("u_name", user.getU_name());
						response.sendRedirect(auth.getUrl());
						
					}
				}else{
					//認証ページへ（ユーザ情報不一致）
					request.setAttribute("fail_login", "true");
					getServletContext().getRequestDispatcher("/" + auth.getUrl()).forward(request, response);
				}
			}
		}
	}
}
