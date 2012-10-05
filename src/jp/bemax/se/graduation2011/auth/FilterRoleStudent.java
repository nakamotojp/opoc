/**
 * 認証 Authパッケージ
 */
package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FilterRole
 */
public class FilterRoleStudent implements Filter {

    /**
     * Default constructor. 
     */
    public FilterRoleStudent() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		String target = ((HttpServletRequest)request).getRequestURI();
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		if(session == null){
			session = ((HttpServletRequest)request).getSession(true);
			session.setAttribute("target", target);
			
			((HttpServletResponse)response).sendRedirect("./../o_user_login.jsp");
			return;
		}else{
			Object loginCheck = session.getAttribute("login");
			if(loginCheck == null){

				session.setAttribute("target", target);
				
				((HttpServletResponse)response).sendRedirect("./../o_user_login.jsp");
				return;
			} else {
				String role = (String)session.getAttribute("role");
				
				if(role == null){
					((HttpServletResponse)response).sendRedirect("./../grobal.jsp");
					return;
				}else if(!role.equals("student")){
					((HttpServletResponse)response).sendRedirect("./../grobal.jsp");
					return;
				}
			}
		}

		// pass the request along the filter chain続きを実行する
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		
	}

}
