/**
 * 面接試験報告書・変更・・・jspへ値を飛ばす<BR>
 * @author 信原美希
 */
package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansCompany;
import jp.bemax.se.graduation2011.model.BeansCompanyTrade;

import jp.bemax.se.graduation2011.model.BeansReportOralExaminationQuestion;
import jp.bemax.se.graduation2011.model.BeansReportOralExamination;

/**
 * Servlet implementation class OralExaminationChangeBefore
 */
public class OralExaminationChangeBefore extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OralExaminationChangeBefore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード・コンテンツタイプ指定
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//session情報を取得
    	HttpSession session = request.getSession(true);
    	String role = (String)session.getAttribute("role");
    	String l_id = (String)session.getAttribute("l_id");
			
    	//権限check
		if(role == null){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
		
		// データ代入・チェック
		String roe_id = request.getParameter("roe_id");
		BeansReportOralExamination data = null;
		
		//例外・エラーが出た場合は処理を中断してリダイレクト
		try{
			
			data = BeansReportOralExamination.
			detailReportOralExamination(Integer.parseInt(roe_id));
			if(data.getRoe_id() == -1 || !l_id.equals(data.getL_id())
					|| data.getRoe_status().equals("end")){
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
					response.sendRedirect("OralExaminationList");
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
					response.sendRedirect("OralExaminationList");
					return;
				}
			}
		}
		
		//権限が学生の時のみ処理する
		if(role.equals("student")){
			
			// データ取得
			BeansCompany company = BeansCompany.detailCompany(data.getComp_id());
			BeansCompanyTrade ctdata = BeansCompanyTrade.detailCompanyTrade(company.getCompt_id());
			
			// データセット
			request.setAttribute("u_id", data.getL_id());

			request.setAttribute("comp_id", company.getComp_id());
			request.setAttribute("comp_name", company.getComp_name());
			request.setAttribute("compt_id", company.getCompt_id());
			request.setAttribute("compt_position", company.getCompt_position());
			request.setAttribute("comp_zip", company.getComp_zip());
			request.setAttribute("comp_address", company.getComp_address());
			request.setAttribute("comp_phone", company.getComp_phone());
			
			request.setAttribute("compt_name", ctdata.getCompt_name());
			
			request.setAttribute("roe_id", data.getRoe_id());
			request.setAttribute("roe_participate", data.getRoe_participate());
			request.setAttribute("roe_introduction", data.getRoe_introduction());
			request.setAttribute("roe_stage", data.getRoe_stage());
			request.setAttribute("roe_interviewer", data.getRoe_interviewer());
			request.setAttribute("roe_examother", data.getRoe_examother());
			request.setAttribute("roe_exam", data.getRoe_exam());
			request.setAttribute("roe_examotherschool", data.getRoe_examotherschool());
			request.setAttribute("roe_nameortitle", data.getRoe_nameortitle());
			
			request.setAttribute("roe_accept", data.getRoe_accept());
			request.setAttribute("roe_nexttime", data.getRoe_nexttime());
			
			if(data.getRoe_nextexam() != null){
				request.setAttribute("roe_nextexam", data.getRoe_nextexam());
				
			}else{
				request.setAttribute("roe_nextexam", "");
			}
			
			if(data.getRoe_preparation() != null){
				request.setAttribute("roe_preparation", data.getRoe_preparation());
			}else{
				request.setAttribute("roe_preparation", "");
			}			
			
			//ディスカッション
			String roe_discussion = data.getRoe_discussion();
			request.setAttribute("roe_discussion", roe_discussion);
			
			//ディスカッションがなかった場合、空白を送る
			if(roe_discussion.equals("yes")){
				
				request.setAttribute("roe_discussionnum", data.getRoe_discussionnum());
				request.setAttribute("roe_discussiontime", data.getRoe_discussiontime());
				request.setAttribute("roe_discussiontheme", data.getRoe_discussiontheme());
				
			}else{
				
				request.setAttribute("roe_discussionnum", "");
				request.setAttribute("roe_discussiontime", "");
				request.setAttribute("roe_discussiontheme", "");
				
			}			
			
			ArrayList<BeansReportOralExaminationQuestion> list = 
				BeansReportOralExaminationQuestion.listReportOralExaminationQuestion(Integer.parseInt(roe_id));
			request.setAttribute("list", list);
			
			// リダイレクト
			getServletContext().getRequestDispatcher("/student/o_oral_e_change.jsp").forward(request, response);
			return;
			
			
		}else{
			
			//権限が学生以外の場合はログインページへ戻す
			response.sendRedirect("./o_user_login.jsp");
			return;
			
		}
		
	}
}