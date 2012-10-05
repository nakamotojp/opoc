/**
 * 面接試験報告書・状態の変更
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
import jp.bemax.se.graduation2011.model.BeansCourse;
import jp.bemax.se.graduation2011.model.BeansReportOralExamination;
import jp.bemax.se.graduation2011.model.BeansSubject;
import jp.bemax.se.graduation2011.model.BeansUser;

/**
 * Servlet implementation class OralExaminationChangeStatus
 */
public class OralExaminationChangeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OralExaminationChangeStatus() {
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
		String roe_id = request.getParameter("roe_id");
		BeansReportOralExamination data = null;
		String roe_status = request.getParameter("roe_status");
		
		try{
			
			data = BeansReportOralExamination.detailReportOralExamination(Integer.parseInt(roe_id));
			
			//状態を変更
			int check = BeansReportOralExamination.changeStatus(Integer.parseInt(roe_id), roe_status);
			if(data.getRoe_id() == -1 || check == -1){
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
		
		//再提出の場合は、報告者にメール送信
		if(roe_status.equals("again")){
			
			//登録者名,IDを取得する
			BeansReportOralExamination broe = 
				BeansReportOralExamination.detailReportOralExamination(Integer.parseInt(roe_id));
			String l_id = broe.getL_id();
			BeansUser bu = BeansUser.detailUser(l_id);
			String u_name = bu.getU_name();
			
			//企業名を取得する
			String comp_name = null;
			int comp_id = broe.getComp_id();
			BeansCompany bc = BeansCompany.detailCompany(comp_id);
			int compt_id = bc.getCompt_id();
			BeansCompanyTrade bct = BeansCompanyTrade.detailCompanyTrade(compt_id);
			String compt_name = bct.getCompt_name();
			String compt_position = bc.getCompt_position();
			
			//商号の位置を判定
			if(compt_position.equals("first")){
				comp_name = compt_name + bc.getComp_name();
			}else{
				comp_name = bc.getComp_name() + compt_name;
			}
			
			//提出日を取得する
			String s = broe.getRoe_introduction();
			String[] roe_introduction = s.split("-");
			 
			//何次試験か取得する
			String roe_stage = broe.getRoe_stage();
			
			//教師情報を取得
			bu = BeansUser.detailUser(teacher_id);
			String teacher_name = bu.getU_name();
			BeansCourse course = BeansCourse.detailCourse(bu.getC_id());
			BeansSubject bs = BeansSubject.detailSubject(course.getS_id());
			String s_name = bs.getS_name();
			
			//メール送信
			GMail mail = new GMail();
			mail.setMailTo(l_id + "@bemax.jp");
			mail.setMailSubject("Please Report Submission");
			mail.setMailBody(u_name + "さんが" + roe_introduction[0] + "年" + 
					roe_introduction[1] + "月" + roe_introduction[2] + 
					"日に提出した、" + comp_name + "の" + roe_stage + "面接試験報告書が承認されませんでした。" + "\n"
					+ "下記のログインページからログインして報告書を再提出して下さい。" 
					+ "\n" + "\n"
					+ "******************************************************" + "\n"
					+ "専門学校ビーマックス" + "\n"
					+ s_name + "担当" + "   " + teacher_name + "\n"
					+ "opocログインページ：" + "https://opoc.bemax.jp/" + "\n"
					+ "******************************************************");
			
			mail.start();
			
		}
		
		//セッションを取得
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
			response.sendRedirect("OralExaminationList");
			return;
			
		}
	}
}
