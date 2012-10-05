/**
 * 合同企業説明会・報告書・詳細
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
import jp.bemax.se.graduation2011.model.BeansReportJoinMeeting;
import jp.bemax.se.graduation2011.model.BeansReportJoinMeetingQuestion;
import jp.bemax.se.graduation2011.model.BeansReportJoinMeetingReply;
import jp.bemax.se.graduation2011.model.BeansSubject;
import jp.bemax.se.graduation2011.model.BeansUser;

/**
 * Servlet implementation class JoinMeetingDetailed
 */
public class JoinMeetingDetailed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinMeetingDetailed() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// エンコード・コンテンツタイプ指定
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//セッション情報を取得
		HttpSession session = request.getSession(true);
		String role = ((String)session.getAttribute("role"));
		String l_id = (String)session.getAttribute("l_id");
		
		//権限check
		if(role == null){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
		
		// データ取得・チェック
		String rjm_id = request.getParameter("rjm_id");
		BeansReportJoinMeeting data;
		try{
			
			data = BeansReportJoinMeeting.detailReportJoinMeeting(Integer.parseInt(rjm_id));
			if(data.getRjm_id() == -1){
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
					response.sendRedirect("JoinMeetingList");
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
					response.sendRedirect("JoinMeetingList");
					return;
				}
			}
		}
	
		//データ取得
		BeansCompany cdata = BeansCompany.detailCompany(data.getComp_id());
		BeansCompanyTrade ct = BeansCompanyTrade.detailCompanyTrade(cdata.getCompt_id());
		BeansUser user = BeansUser.detailUser(data.getL_id());
		BeansCourse course = BeansCourse.detailCourse(user.getC_id());
		BeansSubject subject = BeansSubject.detailSubject(course.getS_id());
		
		//印刷用パラメータ取得
		String ppp = request.getParameter("ppp");	
		
		// データセット
		/*
		 * 権限名が教師(teacher)ならユーザ情報も表示。<BR>
		 * 権限名が生徒(student)ならユーザ情報は表示しない
		 */
		request.setAttribute("l_id", user.getL_id());
		request.setAttribute("u_name", user.getU_name());
		request.setAttribute("u_year", user.getU_year());
		request.setAttribute("c_name", course.getC_name());
		request.setAttribute("s_name", subject.getS_name());
	
		//企業情報
		request.setAttribute("comp_id", cdata.getComp_id());
		if(cdata.getCompt_position().equals("first")){
			request.setAttribute("comp_name", ct.getCompt_name()+cdata.getComp_name());	
		}else{
			request.setAttribute("comp_name", cdata.getComp_name() + ct.getCompt_name());
		}
		
		request.setAttribute("comp_zip", cdata.getComp_zip());
		request.setAttribute("comp_address", cdata.getComp_address());
		request.setAttribute("comp_phone", cdata.getComp_phone());
		
	
		//合同企業説明会報告書・情報
		request.setAttribute("rjm_id", data.getRjm_id());
		request.setAttribute("rjm_name", data.getRjm_name());
		request.setAttribute("rjm_owner", data.getRjm_owner());
		request.setAttribute("rjm_participate", data.getRjm_participate());
		request.setAttribute("rjm_introduction", data.getRjm_introduction());
		request.setAttribute("rjm_content", data.getRjm_content());
		request.setAttribute("rjm_particular", data.getRjm_particular());
		request.setAttribute("rjm_nexttime", data.getRjm_nexttime());
		request.setAttribute("rjm_impression", data.getRjm_impression());
		request.setAttribute("rjm_status", data.getRjm_status());
		
		//変更できるユーザか判定
		String edit = "false";
		if(data.getL_id().equals(l_id) && !data.getRjm_status().equals("end")){
			edit = "true";
		}else if(!role.equals("teacher") && !data.getL_id().equals(l_id) && !data.getRjm_status().equals("end")){
			// リダイレクト
			response.sendRedirect("JoinMeetingList");
			return;
		}
		request.setAttribute("edit", edit);
		
		//削除できるユーザか判定
		String del = "false";
		if(data.getL_id().equals(l_id) && data.getRjm_status().equals("new")){
			
			del = "true";
			
		}
		request.setAttribute("del", del);
		
		
	
		//質問した内容・こたえ
		ArrayList<BeansReportJoinMeetingReply> list = BeansReportJoinMeetingReply.listReportJoinMeetingReply(Integer.parseInt(rjm_id));
		request.setAttribute("list", list);
		//質問された内容・こたえ
		ArrayList<BeansReportJoinMeetingQuestion> list2 = BeansReportJoinMeetingQuestion.listReportJoinMeetingQuestion(Integer.parseInt(rjm_id));
		request.setAttribute("list2", list2);
		
		//権限が教員の場合
		if(role.equals("teacher")){
			
			//印刷するボタンが押されている場合は印刷用ページに飛ぶ
			if(ppp != null){
				
				//印刷用のｊｓｐにリダイレクト
				getServletContext().getRequestDispatcher("/teacher/authority_o_report_print.jsp").forward(request, response);
				return;
				
			//押されていない場合は通常の詳細
			}else{
				
				//詳細ページにリダイレクト
				getServletContext().getRequestDispatcher("/teacher/authority_o_report_op.jsp").forward(request, response);
				return;
				
			}
			
		}else if(role.equals("student")){
			
			//印刷するボタンが押されている場合は印刷用ページに飛ぶ
			if(ppp != null){
				
				//印刷用のｊｓｐにリダイレクト
				getServletContext().getRequestDispatcher("/student/o_report_print.jsp").forward(request, response);
				return;
				
			//押されていない場合は通常通りの詳細
			}else{
				
				// 学生用詳細ページにリダイレクト
				getServletContext().getRequestDispatcher("/student/o_report_op.jsp").forward(request, response);
				return;
				
			}
			
		}else{
			
			//それ以外・ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
			
		}
	}
}
