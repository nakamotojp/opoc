package jp.bemax.se.graduation2011.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.bemax.se.graduation2011.model.BeansCompanyTrade;

/**
 * Servlet implementation class SubjectAjax
 */
public class CompanyTradeAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyTradeAjax() {
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
		
		message = "{\n\t\"companytrade\":[\n";
		
		ArrayList<BeansCompanyTrade> companytrade = BeansCompanyTrade.listCompanyTrade();
		
		ListIterator<BeansCompanyTrade> iterator = companytrade.listIterator();
		
		BeansCompanyTrade tmp = null;
		
		while(iterator.hasNext()){
			if(tmp != null){
				message += ",";
			}
			
			tmp = iterator.next();
			
			message += "\t\t{\n" +
					"\t\t\t\"compt_id\":\"" + tmp.getCompt_id() + "\",\n" +
					"\t\t\t\"compt_name\":\"" + tmp.getCompt_name() + "\"\n" +
					"\t\t}";
		}
		
		message += "\n\t]\n}";
		
		out.println(message);
	}

}
