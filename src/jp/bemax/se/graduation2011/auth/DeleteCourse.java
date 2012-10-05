package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansCourse;

/**
 * Servlet implementation class DeleteCourse
 */
public class DeleteCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCourse() {
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
		String c_id = request.getParameter("c_id");
		
		if(c_id.equals(null)){
		
			// リダイレクト
			response.sendRedirect("");
			return;
		}
		
		BeansCourse.deleteCourse(Integer.parseInt(c_id));
		// リダイレクト
		response.sendRedirect("");
	}
}
