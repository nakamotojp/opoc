package jp.bemax.se.graduation2011.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.bemax.se.graduation2011.model.BeansLogin;


/**
 * Servlet implementation class SubjectAjax
 */
public class LoginAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAjax() {
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
		
		PrintWriter out = response.getWriter();
		
		String l_id = null;
		String message = null;
		
		if(request.getParameter("l_id") != null){
			l_id = request.getParameter("l_id");
		}
		
		if(BeansLogin.checkUser(l_id)){
			message =
				"{\n" +
				"\t\"l_id\":\"true\"\n" +
				"}";
		} else {
			message =
				"{\n" +
				"\t\"l_id\":\"false\"\n" +
				"}";
		}
		
		out.println(message);
	}

}
