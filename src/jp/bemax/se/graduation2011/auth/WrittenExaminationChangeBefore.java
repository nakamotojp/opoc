/**
 * Servlet implementation class WrittenExaminationChangeBefore
 * 筆記試験報告書・変更・・・jspへ値を飛ばす
 * @author 信原美希
 */
package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansCompany;
import jp.bemax.se.graduation2011.model.BeansCompanyTrade;
import jp.bemax.se.graduation2011.model.BeansReportWrittenExamination;

public class WrittenExaminationChangeBefore extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WrittenExaminationChangeBefore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//session情報を取得
    	HttpSession session = request.getSession(true);
    	String role = (String)session.getAttribute("role");
    	String l_id = (String)session.getAttribute("l_id");
    	
    	//権限チェック
    	if(role == null){
    		
    		//権限がない場合はログインページへ戻す
    		response.sendRedirect("./o_user_login.jsp");
    		return;
    		
    	}
    	
    	// データ代入・チェック
		String rwe_id = request.getParameter("rwe_id");
		BeansReportWrittenExamination data = null;
		
		//例外・エラーが出た場合は処理を中断してリダイレクト
		try{
			
			data = BeansReportWrittenExamination.
			detailReportWrittenExamination(Integer.parseInt(rwe_id));
			if(data.getRwe_id() == -1 || !l_id.equals(data.getL_id()) 
					|| data.getRwe_status().equals("end")){
				throw new Exception();
			}
			
		}catch(Exception e){
		
			//報告書IDがない場合、またはDBと一致しない場合、リストページにリダイレクト
			String report = (String)session.getAttribute("report");
			if(role.equals("student")){
				if(report.equals("new")){
					// リダイレクト・トップページ
					response.sendRedirect("ReportNewList");
					return;
				}else if(report.equals("mix")){
					// リダイレクト・全報告書一覧
					response.sendRedirect("ReportMyList");
					return;
				}else{
					// リダイレクト・一覧ページ
					response.sendRedirect("WrittenExaminationList");
					return;
				}
			}else{
				if(report.equals("mix")){
					// リダイレクト・全報告書一覧
					response.sendRedirect("AuthorityReportList");
					return;
				}else if(report.equals("new")){			
					// リダイレクト・トップページ
					response.sendRedirect("AuthorityNewReportList");	
					return;
				}else{
					// リダイレクト
					response.sendRedirect("WrittenExaminationList");
					return;
				}
			}
		}
    	
    	//権限が学生の場合のみ処理する
		if(role.equals("student")){
			
			// データ取得
			BeansCompany company = BeansCompany.detailCompany(data.getComp_id());
			BeansCompanyTrade comptrade = BeansCompanyTrade.detailCompanyTrade(company.getCompt_id());			
			
			// データセット
			request.setAttribute("l_id", data.getL_id());
	
			request.setAttribute("comp_id", company.getComp_id());
			request.setAttribute("comp_name", company.getComp_name());
			request.setAttribute("compt_id", company.getCompt_id());
			request.setAttribute("compt_position", company.getCompt_position());
			request.setAttribute("comp_zip", company.getComp_zip());
			request.setAttribute("comp_address", company.getComp_address());
			request.setAttribute("comp_phone", company.getComp_phone());
			
			request.setAttribute("compt_name", comptrade.getCompt_name());
	
			request.setAttribute("rwe_id", data.getRwe_id());
			request.setAttribute("rwe_stage", data.getRwe_stage());
			request.setAttribute("rwe_participate", data.getRwe_participate());
			request.setAttribute("rwe_introduction", data.getRwe_introduction());
			request.setAttribute("rwe_examother", data.getRwe_examother());
			request.setAttribute("rwe_exam", data.getRwe_exam());
			request.setAttribute("rwe_examotherschool", data.getRwe_examotherschool());
			request.setAttribute("rwe_aptitude", data.getRwe_aptitude());
			request.setAttribute("rwe_aptitudetime", data.getRwe_aptitudetime());
			request.setAttribute("rwe_writtentime", data.getRwe_writtentime());
			request.setAttribute("rwe_papertime", data.getRwe_papertime());
			request.setAttribute("rwe_lang", data.getRwe_lang());
			request.setAttribute("rwe_math", data.getRwe_math());
			request.setAttribute("rwe_english", data.getRwe_english());
			request.setAttribute("rwe_science", data.getRwe_science());
			request.setAttribute("rwe_society", data.getRwe_society());
			request.setAttribute("rwe_politics", data.getRwe_politics());
			request.setAttribute("rwe_expert", data.getRwe_expert());
			request.setAttribute("rwe_paper", data.getRwe_paper());
			request.setAttribute("rwe_other", data.getRwe_other());
			request.setAttribute("rwe_accept", data.getRwe_accept());
			request.setAttribute("rwe_nexttime", data.getRwe_nexttime());
			request.setAttribute("rwe_nextexam", data.getRwe_nextexam());
			request.setAttribute("rwe_preparation", data.getRwe_preparation());
			request.setAttribute("rwe_status", data.getRwe_status());
					
			// リダイレクト
			getServletContext().getRequestDispatcher("/student/o_written_e_change.jsp").forward(request, response);
		
		}else{
			
			//権限が学生でない場合はログインページへ戻す
			response.sendRedirect("./o_user_login.jsp");
    		return;
    		
		}
		
	}

}
