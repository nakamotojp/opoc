package jp.bemax.se.graduation2011.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansCourse;

/**
 * Servlet implementation class ChangeCourse
 */
public class ChangeCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//リダイレクト
		response.sendRedirect("");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		//xss対策クラスをインスタンス化
		Xss xss = new Xss();
		//BeansCourseをインスタンス化
		BeansCourse bc = new BeansCourse(); 
		
		
		//セッション情報を取得する
		HttpSession session = request.getSession(true);
		//セッション情報から権限名を取得する
		String role = (String)session.getAttribute("role");

		
		//権限があるか判定する
		if(!role.equals("manager")){
			//権限がない
			
		}else{
			
			bc.setC_id(Integer.parseInt(request.getParameter("c_id")));
			bc.setC_name(xss.escape(request.getParameter("c_name")));
			bc.setC_year(Integer.parseInt(request.getParameter("c_year")));
			bc.setS_id(Integer.parseInt(request.getParameter("s_id")));
				
			bc.changeCourse();
				
			response.sendRedirect("");
				
		}
		
	}
	
}