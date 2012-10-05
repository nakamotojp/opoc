/**
 * 筆記試験報告書・状態の変更
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
import jp.bemax.se.graduation2011.model.BeansReportWrittenExamination;
import jp.bemax.se.graduation2011.model.BeansSubject;
import jp.bemax.se.graduation2011.model.BeansUser;

/**
 * Servlet implementation class WrittenExaminationChangeStatus
 */
public class WrittenExaminationChangeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WrittenExaminationChangeStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッション情報で権限を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		String teacher_id = (String)session.getAttribute("l_id");
		
		//権限がない場合
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
		String rwe_id = request.getParameter("rwe_id");
		BeansReportWrittenExamination data = null;
		String rwe_status = request.getParameter("rwe_status");
		
		//例外・エラーが出た場合は処理を中断してリダイレクト
		try{
			
			data = BeansReportWrittenExamination.detailReportWrittenExamination(Integer.parseInt(rwe_id));
			
			//状態を変更
			int check = BeansReportWrittenExamination.changeStatus(Integer.parseInt(rwe_id), rwe_status);
			if(data.getRwe_id() == -1 || check == -1){
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
		
		//再提出の場合は、報告者にメール送信
		if(rwe_status.equals("again")){
			
			//登録者名,IDを取得する
			BeansReportWrittenExamination brwe = BeansReportWrittenExamination.detailReportWrittenExamination(Integer.parseInt(rwe_id));
			String l_id = brwe.getL_id();
			BeansUser bu = BeansUser.detailUser(l_id);
			String u_name = bu.getU_name();
			
			//企業情報を取得する
			String comp_name = null;
			int comp_id = brwe.getComp_id();
			BeansCompany bc = BeansCompany.detailCompany(comp_id);
			int compt_id = bc.getCompt_id();
			BeansCompanyTrade bct = BeansCompanyTrade.detailCompanyTrade(compt_id);
			String compt_name = bct.getCompt_name();
			String compt_position = bc.getCompt_position();
			
			
			//メッセージを取得(jsp側ができたら)
			//String message = null;
			
			
			//商号の位置を判定
			if(compt_position.equals("first")){
				comp_name = compt_name + bc.getComp_name();
			}else{
				comp_name = bc.getComp_name() + compt_name;
			}
			
			//提出日を取得する
			String s = brwe.getRwe_introduction();
			String[] rwe_introduction = s.split("-");
			 
			//何次試験か取得する
			String rwe_stage = brwe.getRwe_stage();
			
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
			mail.setMailBody(u_name + "さんが" + rwe_introduction[0] + "年" + 
					rwe_introduction[1] + "月" + rwe_introduction[2] + 
					"日に提出した、" + comp_name + "の" + rwe_stage + "筆記試験報告書が承認されませんでした。" + "\n"
					+ "下記のログインページからログインして報告書を再提出して下さい。" 
					+ "\n" + "\n"
					+ "******************************************************" + "\n"
					+ "専門学校ビーマックス" + "\n"
					+ s_name + "担当" + "   " + teacher_name + "\n"
					+ "opocログインページ：" + "https://opoc.bemax.jp/" + "\n"
					+ "******************************************************");

			mail.start();
			
		}
		
		//セッション取得
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
			response.sendRedirect("WrittenExaminationList");
			return;
			
		}		
	}
}