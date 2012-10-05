/**
 * 合同企業説明会・報告書・変更前詳細ページ
 * @author 上月将也
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

import jp.bemax.se.graduation2011.model.BeansReportJoinMeeting;
import jp.bemax.se.graduation2011.model.BeansReportJoinMeetingQuestion;
import jp.bemax.se.graduation2011.model.BeansReportJoinMeetingReply;

/**
 * Servlet implementation class JoinMeetingChangeBefore
 */
public class JoinMeetingChangeBefore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinMeetingChangeBefore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// コンテンツタイプ指定
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
		
		// データ代入・チェック
		String rjm_id = request.getParameter("rjm_id");
		BeansReportJoinMeeting data = null;
		
		//例外・エラーが出た場合は処理を中断してリダイレクト
		try{
			
			data = BeansReportJoinMeeting.detailReportJoinMeeting(Integer.parseInt(rjm_id));
			if(data.getRjm_id() == -1 || !l_id.equals(data.getL_id()) 
					|| data.getRjm_status().equals("end")){
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

		if(role.equals("student")){
			
			// データ取得
			BeansCompany cdata = BeansCompany.detailCompany(data.getComp_id());
			BeansCompanyTrade ctdata = BeansCompanyTrade.detailCompanyTrade(cdata.getCompt_id());
				
			// データセット
			request.setAttribute("l_id", data.getL_id());
			
			//企業情報
			request.setAttribute("comp_id", cdata.getComp_id());
			request.setAttribute("comp_name", cdata.getComp_name());
			request.setAttribute("compt_id", cdata.getCompt_id());
			request.setAttribute("compt_name", ctdata.getCompt_name());
			request.setAttribute("compt_position", cdata.getCompt_position());
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
		
			//質問した内容・こたえ
			ArrayList<BeansReportJoinMeetingReply> list = 
				BeansReportJoinMeetingReply.listReportJoinMeetingReply(Integer.parseInt(rjm_id));
			request.setAttribute("list", list);
			//質問された内容・こたえ
			ArrayList<BeansReportJoinMeetingQuestion> list2 = 
				BeansReportJoinMeetingQuestion.listReportJoinMeetingQuestion(Integer.parseInt(rjm_id));
			request.setAttribute("list2", list2);
			
			// リダイレクト
			getServletContext().getRequestDispatcher("/student/o_report_change.jsp").forward(request, response);
			return;
			
		}else{
			
			//権限が学生以外の場合はログインページへ
			response.sendRedirect("./o_user_login.jsp");
			return;
			
		}
		
	}
}
