package jp.bemax.se.graduation2011.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.bemax.se.graduation2011.model.BeansCourse;

/**
 * Servlet implementation class SubjectAjax
 */
public class CourseAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseAjax() {
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
		
		int s_id = -1;
		String message = null;
		
		if(request.getParameter("s_id") != null){
			s_id = Integer.parseInt(request.getParameter("s_id"));
		}
		
		message = "{\n\t\"course\":[\n";
		
		ArrayList<BeansCourse> course = BeansCourse.searchCourse(s_id);
		
		ListIterator<BeansCourse> iterator = course.listIterator();
		
		BeansCourse tmp = null;
		
		while(iterator.hasNext()){
			if(tmp != null){
				message += ",\n";
			}
			
			tmp = iterator.next();
			
			message += "\t\t{\n" +
					"\t\t\t\"c_id\":" + tmp.getC_id() + ",\n" +
					"\t\t\t\"c_name\":\"" + tmp.getC_name() + "\",\n" +
					"\t\t\t\"c_year\":\"" + tmp.getC_year() + "\",\n" +
					"\t\t\t\"s_id\":\"" + tmp.getS_id() + "\"\n" +
					"\t\t}";
		}
		
		message += "\n\t]\n}";
		
		out.println(message);
	}

}
