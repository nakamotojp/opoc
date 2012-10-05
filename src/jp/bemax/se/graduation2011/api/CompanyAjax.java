package jp.bemax.se.graduation2011.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.bemax.se.graduation2011.auth.Xss;
import jp.bemax.se.graduation2011.model.BeansCompany;
import jp.bemax.se.graduation2011.model.BeansCompanyTrade;

/**
 * Servlet implementation class SubjectAjax
 */
public class CompanyAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyAjax() {
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
		
		String comp_name = null;
		String message = null;
		Xss xss = new Xss();
		
		if(request.getParameter("comp_name") != null){
			comp_name = new String(request.getParameter("comp_name").getBytes("iso-8859-1"), "utf-8");
		}
		
		message = "{\n\t\"company\":[\n";
		
		ArrayList<BeansCompany> company = BeansCompany.searchCompany('%' + comp_name + '%');
		
		ListIterator<BeansCompany> iterator = company.listIterator();
		
		BeansCompany tmp = null;
		BeansCompanyTrade t_tmp = null;
		
		while(iterator.hasNext()){
			if(tmp != null){
				message += ",";
			}
			
			tmp = iterator.next();
			t_tmp = BeansCompanyTrade.detailCompanyTrade(tmp.getCompt_id());
			
			message += "\t\t{\n" +
					"\t\t\t\"comp_id\":" + tmp.getComp_id() + ",\n" +
					"\t\t\t\"compt_id\":\"" + tmp.getCompt_id() + "\",\n" +
					"\t\t\t\"compt_position\":\"" + tmp.getCompt_position() + "\",\n" +
					"\t\t\t\"compt_name\":\"" + t_tmp.getCompt_name() + "\",\n" +
					"\t\t\t\"comp_name\":\"" + xss.escape(tmp.getComp_name()) + "\",\n" +
					"\t\t\t\"comp_zip\":\"" + tmp.getComp_zip() + "\",\n" +
					"\t\t\t\"comp_address\":\"" + tmp.getComp_address() + "\",\n" +
					"\t\t\t\"comp_phone\":\"" + tmp.getComp_phone() + "\"\n" +
					"\t\t}";
		}
		
		message += "\n\t]\n}";
		
		out.println(message);
	}

}
