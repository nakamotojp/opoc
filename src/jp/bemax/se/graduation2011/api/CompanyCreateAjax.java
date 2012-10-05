package jp.bemax.se.graduation2011.api;

import java.io.IOException;
import java.io.PrintWriter;
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
public class CompanyCreateAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyCreateAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		String message = null;
		BeansCompany company = new BeansCompany();
		Xss xss = new Xss();
		
		if(request.getParameter("compt_id") != null && request.getParameter("compt_id").length() > 0){
			company.setCompt_id(Integer.parseInt(request.getParameter("compt_id")));
		}
		company.setCompt_position(request.getParameter("compt_position"));
		company.setComp_name(request.getParameter("comp_name"));
		company.setComp_zip(xss.escape(request.getParameter("comp_zip")));
		company.setComp_address(xss.escape(request.getParameter("comp_address")));
		company.setComp_phone(xss.escape(request.getParameter("comp_phone01") + "-" + request.getParameter("comp_phone02") + "-" + request.getParameter("comp_phone03")));
		
		message = "{\n\t\"company\":[\n";
		
		if(company.createCompany() != -1 && company.getComp_id() != -1){
			
			BeansCompanyTrade t_tmp = BeansCompanyTrade.detailCompanyTrade(company.getCompt_id());
			
			message += "\t\t{\n" +
					"\t\t\t\"comp_id\":" + company.getComp_id() + ",\n" +
					"\t\t\t\"compt_id\":\"" + company.getCompt_id() + "\",\n" +
					"\t\t\t\"compt_position\":\"" + company.getCompt_position() + "\",\n" +
					"\t\t\t\"compt_name\":\"" + t_tmp.getCompt_name() + "\",\n" +
					"\t\t\t\"comp_name\":\"" + xss.escape(company.getComp_name()) + "\",\n" +
					"\t\t\t\"comp_zip\":\"" + company.getComp_zip() + "\",\n" +
					"\t\t\t\"comp_address\":\"" + company.getComp_address() + "\",\n" +
					"\t\t\t\"comp_phone\":\"" + company.getComp_phone() + "\"\n" +
					"\t\t}";
		}
		
		message += "\n\t]\n}";
		
		out.println(message);
	}

}
