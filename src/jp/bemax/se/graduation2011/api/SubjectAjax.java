package jp.bemax.se.graduation2011.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.bemax.se.graduation2011.model.BeansSubject;

/**
 * Servlet implementation class SubjectAjax
 */
public class SubjectAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectAjax() {
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
		
		String message = null;
		
		message = "{\n\t\"subject\":[\n";
		
		ArrayList<BeansSubject> subject = BeansSubject.listSubject();
		
		ListIterator<BeansSubject> iterator = subject.listIterator();
		
		BeansSubject tmp = null;
		
		while(iterator.hasNext()){
			
			if(tmp != null && tmp.getS_id() != 6){
				message += ",";
			}
				
			tmp = iterator.next();
	
			if(tmp.getS_id() != 6){
				message += "\t\t{\n" +
						"\t\t\t\"s_id\":" + tmp.getS_id() + ",\n" +
						"\t\t\t\"s_name\":\"" + tmp.getS_name() + "\"\n" +
						"\t\t}";
			}
		}
		
		message += "\n\t]\n}";
		
		out.println(message);
	}

}
