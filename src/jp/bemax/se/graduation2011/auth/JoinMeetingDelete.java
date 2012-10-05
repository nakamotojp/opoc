/**
 * 合同企業説明会・報告書・削除
 * @author 信原美希
 */
package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansReportJoinMeeting;

/**
 * Servlet implementation class JoinMeetingDelete
 */
public class JoinMeetingDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinMeetingDelete() {
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
		
		if(role.equals("student")){
				
			// データ削除
			BeansReportJoinMeeting.deleteReportJoinMeeting(Integer.parseInt(rjm_id));
			String report = (String)session.getAttribute("report");
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
			
		}else if(role.equals("teacher")){

			// データ削除
			BeansReportJoinMeeting.deleteReportJoinMeeting(Integer.parseInt(rjm_id));
			String report = (String)session.getAttribute("report");
			if(report.equals("mix")){
				
				// リダイレクト・全報告書一覧
				response.sendRedirect("AuthorityReportList");

			}else if(report.equals("new")){
				
				// リダイレクト・トップページ
				response.sendRedirect("AuthorityNewReportList");
				
			}else{
				// リダイレクト
				response.sendRedirect("JoinMeetingList");
			}
			
		}else{
			
			//それ以外・ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
			
		}
	}
}
