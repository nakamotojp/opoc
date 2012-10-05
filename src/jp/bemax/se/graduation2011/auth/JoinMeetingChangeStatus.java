/**
 * 合同企業説明会・報告書・状態の変更
 * @author 信原美希
 */
package jp.bemax.se.graduation2011.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansCourse;
import jp.bemax.se.graduation2011.model.BeansReportJoinMeeting;
import jp.bemax.se.graduation2011.model.BeansSubject;
import jp.bemax.se.graduation2011.model.BeansUser;

/**
 * Servlet implementation class JoinMeetingChangeStatus
 */
public class JoinMeetingChangeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinMeetingChangeStatus() {
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
		
		//session情報を取得
		HttpSession session = request.getSession(true);
		String role = ((String)session.getAttribute("role"));
		String teacher_id = (String)session.getAttribute("l_id");
		
		//権限check
		if(role != null){
			//権限が先生以外の場合は、元のページへリダイレクト
			if(!role.equals("teacher")){
				response.sendRedirect("ReportNewList");
				return;
			}
		}else{
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
			
		// データ代入・チェック
		String rjm_id = request.getParameter("rjm_id");
		BeansReportJoinMeeting data = null;
		String rjm_status = request.getParameter("rjm_status");
		
		//例外・エラーが出た場合は処理を中断してリダイレクト
		try{
			
			data = BeansReportJoinMeeting.detailReportJoinMeeting(Integer.parseInt(rjm_id));
			
			//状態を変更
			int check = BeansReportJoinMeeting.changeStatus(Integer.parseInt(rjm_id), rjm_status);
			if(data.getRjm_id() == -1 || check == -1){
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
		
		//再提出の場合は、報告者にメール送信
		if(rjm_status.equals("again")){
			
			//登録者名,IDを取得する
			BeansReportJoinMeeting brjm = BeansReportJoinMeeting.detailReportJoinMeeting(Integer.parseInt(rjm_id));
			String l_id = brjm.getL_id();
			BeansUser bu = BeansUser.detailUser(l_id);
			String u_name = bu.getU_name();
			
			//説明会名・提出日を取得する
			String rjm_name = brjm.getRjm_name();
			String s = brjm.getRjm_introduction();
			String[] rjm_introduction = s.split("-");
			
			//教師情報を取得
			bu = BeansUser.detailUser(teacher_id);
			String teacher_name = bu.getU_name();
			BeansCourse bc = BeansCourse.detailCourse(bu.getC_id());
			BeansSubject bs = BeansSubject.detailSubject(bc.getS_id());
			String s_name = bs.getS_name();
			 
			//メール送信
			GMail mail = new GMail();
			mail.setMailTo(l_id + "@bemax.jp");
			mail.setMailSubject("Please Report Submission");
			mail.setMailBody(u_name + "さんが" + rjm_introduction[0] + "年" + 
					rjm_introduction[1] + "月" + rjm_introduction[2] + 
					"日に提出した、" + rjm_name + "の報告書が承認されませんでした。" + "\n"
					+ "下記のログインページからログインして報告書を再提出して下さい。"
					+ "\n" + "\n"
					+ "******************************************************" + "\n"
					+ "専門学校ビーマックス" + "\n"
					+ s_name + "担当" + "   " + teacher_name + "\n"
					+ "opocログインページ：" + "https://opoc.bemax.jp/" + "\n"
					+ "******************************************************");
			
			mail.start();
			
		}
		
		//セッション情報を取得
		String report = (String)session.getAttribute("report");
		
		if(report.equals("mix")){
			
			// リダイレクト・全報告書一覧
			response.sendRedirect("AuthorityReportList");
			return;
			
		}else if(report.equals("new")){
			
			// リダイレクト・トップページ
			response.sendRedirect("AuthorityNewReportList");
			return;
			
		}else{
			
			// リダイレクト・企業報告書一覧
			response.sendRedirect("JoinMeetingList");
			return;
			
		}
	}
}
