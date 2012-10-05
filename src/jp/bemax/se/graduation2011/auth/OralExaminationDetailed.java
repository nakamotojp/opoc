/**
 * 面接試験報告書・詳細<BR>
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
import jp.bemax.se.graduation2011.model.BeansCourse;
import jp.bemax.se.graduation2011.model.BeansReportOralExaminationQuestion;
import jp.bemax.se.graduation2011.model.BeansReportOralExamination;
import jp.bemax.se.graduation2011.model.BeansSubject;
import jp.bemax.se.graduation2011.model.BeansUser;

/**
 * Servlet implementation class OralExaminationDetailed
 */
public class OralExaminationDetailed extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OralExaminationDetailed() {
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
			if(data.getRoe_id() == -1){
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
		
		//データ取得
		BeansCompany company = BeansCompany.detailCompany(data.getComp_id());
		BeansCompanyTrade ct = BeansCompanyTrade.detailCompanyTrade(company.getCompt_id());
		BeansUser user = BeansUser.detailUser(data.getL_id());
		BeansCourse course = BeansCourse.detailCourse(user.getC_id());
		BeansSubject subject = BeansSubject.detailSubject(course.getS_id());
		
		//印刷用パラメータ取得
		String ppp = request.getParameter("ppp");
		
		
		// データセット
		request.setAttribute("l_id", data.getL_id());
		request.setAttribute("u_name", user.getU_name());
		request.setAttribute("c_name", course.getC_name());
		request.setAttribute("s_name", subject.getS_name());

		request.setAttribute("comp_id", company.getComp_id());
		if(company.getCompt_position().equals("first")){
			request.setAttribute("comp_name", ct.getCompt_name()+company.getComp_name());	
		}else{
			request.setAttribute("comp_name", company.getComp_name() + ct.getCompt_name());
		}
		
		request.setAttribute("comp_zip", company.getComp_zip());
		request.setAttribute("comp_address", company.getComp_address());
		request.setAttribute("comp_phone", company.getComp_phone());

		request.setAttribute("roe_id", data.getRoe_id());
		request.setAttribute("roe_participate", data.getRoe_participate());
		request.setAttribute("roe_introduction", data.getRoe_introduction());
		request.setAttribute("roe_stage", data.getRoe_stage());
		request.setAttribute("roe_interviewer", data.getRoe_interviewer());
		request.setAttribute("roe_examother", data.getRoe_examother());
		request.setAttribute("roe_exam", data.getRoe_exam());
		request.setAttribute("roe_examotherschool", data.getRoe_examotherschool());
		request.setAttribute("roe_nameortitle", data.getRoe_nameortitle());
		
		request.setAttribute("roe_discussion", data.getRoe_discussion());
		request.setAttribute("roe_discussionnum", data.getRoe_discussionnum());
		request.setAttribute("roe_discussiontime", data.getRoe_discussiontime());
		request.setAttribute("roe_discussiontheme", data.getRoe_discussiontheme());
		
		request.setAttribute("roe_accept", data.getRoe_accept());
		request.setAttribute("roe_nexttime", data.getRoe_nexttime());
		request.setAttribute("roe_nextexam", data.getRoe_nextexam());
		request.setAttribute("roe_preparation", data.getRoe_preparation());
		request.setAttribute("roe_status", data.getRoe_status());
		

		//変更できるユーザか判定
		String edit = "false";
		//登録された学籍番号とセッションで取得したIDを比較、登録された状態がend以外であるかどうか判断
		if(data.getL_id().equals(l_id) && !data.getRoe_status().equals("end")){
			edit = "true";
		}else if(!role.equals("teacher") && !data.getL_id().equals(l_id) && !data.getRoe_status().equals("end")){
			//リダイレクト
			response.sendRedirect("OralExaminationList");
			return;
		}
		request.setAttribute("edit", edit);
		
		//削除できるユーザかどうか判断
		String del = "false";
		//登録された学籍番号とセッションで取得したIDを比較、登録された状態がnewであるかどうか判断
		if(data.getL_id().equals(l_id) && data.getRoe_status().equals("new")){
			
			del = "true";
			
		}
		request.setAttribute("del", del);
		
		ArrayList<BeansReportOralExaminationQuestion> list = 
			BeansReportOralExaminationQuestion.listReportOralExaminationQuestion(Integer.parseInt(roe_id));
		request.setAttribute("list", list);
		
		//先生権限の場合は先生用の詳細ページへ飛ばす
		if(role.equals("teacher")){
			
			//印刷するボタンが押されている場合は印刷用ページへ飛ぶ
			if(ppp != null){
				
				//印刷用のｊｓｐにリダイレクト
				getServletContext().getRequestDispatcher("/teacher/authority_o_oral_e_print.jsp").forward(request, response);
				return;
				
			}else{
				
				//詳細ページにリダイレクト
				getServletContext().getRequestDispatcher("/teacher/authority_o_oral_e_op.jsp").forward(request, response);
				return;
				
			}
			
		
		//生徒権限の場合は生徒用の詳細ページへ飛ばす
		}else if(role.equals("student")){
			
			//印刷するボタンが押されている場合は印刷用ページへ飛ぶ
			if(ppp != null){
				
				//印刷用のｊｓｐにリダイレクト
				getServletContext().getRequestDispatcher("/student/o_oral_e_print.jsp").forward(request, response);
				return;
				
			}else{
				
				// 詳細ページにリダイレクト
				getServletContext().getRequestDispatcher("/student/o_oral_e_op.jsp").forward(request, response);
				return;
				
			}
			
			
		}else{
			
			//それ以外の場合・ログインページへ戻す
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
			
		}
	}
}