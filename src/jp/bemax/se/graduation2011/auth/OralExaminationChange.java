package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansReportOralExamination;
import jp.bemax.se.graduation2011.model.BeansReportOralExaminationQuestion;

/**
 * Servlet implementation class OralExaminationChange
 */
public class OralExaminationChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OralExaminationChange() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// エンコード・コンテンツタイプ指定
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// データ代入・チェック
		String roe_id = request.getParameter("roe_id");
		if(roe_id == null){
			
			// リダイレクト
			response.sendRedirect("OralExaminationList");
			return;
		}
		
		//Beans・xss対策のクラスをインスタンス化
		BeansReportOralExamination boe = new BeansReportOralExamination();
		Xss xss = new Xss();
		
		//session情報を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		
		//権限check
		if(role == null){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
	
		//変更前の情報
		BeansReportOralExamination report = 
			BeansReportOralExamination.detailReportOralExamination(Integer.parseInt(roe_id));
		
		/**
		 * 報告書変更
		 */
		//企業IDをセット
		boe.setComp_id(Integer.parseInt(request.getParameter("comp_id")));

		//報告書ID
		boe.setRoe_id(Integer.parseInt(roe_id));
		
		//ログインID（学籍番号）
		boe.setL_id((String)session.getAttribute("l_id"));
		
		//提出日
		String roe_introduction = request.getParameter("roe_introduction_year") + "-";
		roe_introduction += request.getParameter("roe_introduction_month") + "-";
		roe_introduction += request.getParameter("roe_introduction_day");
		
		boe.setRoe_introduction(roe_introduction);
		
		//参加日
		String roe_participate = request.getParameter("roe_participate_year") + "-";
		roe_participate += request.getParameter("roe_participate_month") + "-";
		roe_participate += request.getParameter("roe_participate_day");
		
		boe.setRoe_participate(roe_participate);
		
		//何次試験
		String roe_stage = (xss.escape(request.getParameter("roe_stage")));
		if(roe_stage == null){
			
			boe.setRoe_stage(report.getRoe_stage());
		}else{
			
			boe.setRoe_stage(roe_stage);
		}
				
		//面接官人数
		String roe_interviewer = request.getParameter("roe_interviewer");
		if(roe_interviewer.equals(0)){
			
			boe.setRoe_interviewer(report.getRoe_interviewer());
		}else{
			
			boe.setRoe_interviewer(Integer.parseInt(roe_interviewer));
		}
		
		//面接官の名前又は役職
		String roe_nameortitle = (xss.escape(request.getParameter("roe_nameortitle")));
		if(roe_nameortitle == null){
			
			boe.setRoe_nameortitle(report.getRoe_nameortitle());
		}else{
			
			boe.setRoe_nameortitle(roe_nameortitle);
		}
		
		//他校受験者人数
		String roe_examother = request.getParameter("roe_examother");
		if(roe_examother.equals(-1)){
			
			boe.setRoe_examother(report.getRoe_examother());
		}else{
			
			boe.setRoe_examother(Integer.parseInt(roe_examother));
		}
		
		//他校受験者出身校
		String roe_examotherschool = (xss.escape(request.getParameter("roe_examotherschool")));
		if(roe_examotherschool == null){
			
			boe.setRoe_examotherschool(report.getRoe_examotherschool());
		}else{
			
			boe.setRoe_examotherschool(roe_examotherschool);
		}
		
		//当校受験者人数
		String roe_exam = request.getParameter("roe_exam");
		if(roe_exam.equals(-1)){
			
			boe.setRoe_exam(report.getRoe_exam());
		}else{
			
			boe.setRoe_exam(Integer.parseInt(roe_exam));
		}
		
		//グループディスカッションの有無
		String roe_discussion = request.getParameter("roe_discussion");
		
		if(roe_discussion.equals("yes")){
			
				
			//ディスカッションの有無
			boe.setRoe_discussion(roe_discussion);
			
			//グループディスカッションの人数
			String roe_discussionnum = request.getParameter("roe_discussionnum");
			if(roe_discussionnum.equals(0)){
				
				boe.setRoe_discussionnum(report.getRoe_discussionnum());
			}else{
				
				boe.setRoe_discussionnum(Integer.parseInt(roe_discussionnum));
			}
				
			//グループディスカッションの時間
			String roe_discussiontime = request.getParameter("roe_discussiontime");
			if(roe_discussiontime.equals(0)){
				
				boe.setRoe_discussiontime(report.getRoe_discussiontime());
			}else{
				
				boe.setRoe_discussiontime(Integer.parseInt(roe_discussiontime));
			}
				
			//グループディスカッションのテーマ
			String roe_discussiontheme = (xss.escape(request.getParameter("roe_discussiontheme")));
			if(roe_discussiontheme == null){
				
				boe.setRoe_discussiontheme(report.getRoe_discussiontheme());
			}else{
				
				boe.setRoe_discussiontheme(roe_discussiontheme);
			}
			
		}else{
			
			boe.setRoe_discussion(roe_discussion);
		}
		
		//合否連絡
		String roe_accept = request.getParameter("roe_accept_year") + "-";
		roe_accept += request.getParameter("roe_accept_month") + "-";
		roe_accept += request.getParameter("roe_accept_day");
		
		
		boe.setRoe_accept(roe_accept);
		
		//次回受験があるかないか
		String roe_nexttime_check = request.getParameter("roe_nexttime_check");
		if(roe_nexttime_check.equals("有る")){
			
			//次回受験日
			String roe_nexttime = request.getParameter("roe_nexttime_year") + "-";
			roe_nexttime += request.getParameter("roe_nexttime_month") + "-";
			roe_nexttime += request.getParameter("roe_nexttime_day");
			
			boe.setRoe_nexttime(roe_nexttime);
			
			//次回受験内容
			String roe_nextexam = xss.escape(request.getParameter("roe_nextexam"));
			if(roe_nextexam == null){
				
				boe.setRoe_nextexam(report.getRoe_nextexam());
			}else{
				
				boe.setRoe_nextexam(roe_nextexam);
			}
			
			//準備知識・情報
			String roe_preparation = (xss.escape(request.getParameter("roe_preparation")));
			if(roe_preparation == null){
				
				boe.setRoe_preparation(report.getRoe_preparation());
			}else{
				
				boe.setRoe_preparation(roe_preparation);
			}
			
		}if(roe_nexttime_check.equals("有るが未定")){
			
			//次回受験日
			boe.setRoe_nexttime(request.getParameter("roe_nexttime_check"));
			
			//次回受験内容
			String roe_nextexam = xss.escape(request.getParameter("roe_nextexam"));
			if(roe_nextexam == null){
				
				boe.setRoe_nextexam(report.getRoe_nextexam());
			}else{
				
				boe.setRoe_nextexam(roe_nextexam);
			}
			
			//準備知識・情報
			String roe_preparation = (xss.escape(request.getParameter("roe_preparation")));
			if(roe_preparation == null){
				
				boe.setRoe_preparation(report.getRoe_preparation());
			}else{
				
				boe.setRoe_preparation(roe_preparation);
			}
			
		}if(roe_nexttime_check.equals("無し")){
			
			//次回受験日
			boe.setRoe_nexttime(request.getParameter("roe_nexttime_check"));
			
		}
		
		/*
		 * 状態がagainの時のみrenewに変更する。
		 * 状態がrenewのときはrenewのまま変更しない。
		 * それ以外はnewのまま
		 */
		String roe_status = report.getRoe_status();
		if(roe_status.equals("again")){
			
			boe.setRoe_status("renew");
			
		}else if(roe_status.equals("renew")){
			
			boe.setRoe_status("renew");
		
		}else{
			
			boe.setRoe_status("new");
			
		}
		
		//変更する
		if(boe.changeReportOralExamination() == -1){
			// エラーページにリダイレクト
			response.sendRedirect("./err.jsp");
			return;
		}
		
		/**
		 * 質問を変更
		 */
		ArrayList<BeansReportOralExaminationQuestion> qform = new ArrayList<BeansReportOralExaminationQuestion>();
		// 一時領域
		BeansReportOralExaminationQuestion qtmp = null;
		
		// フォームの値を取得
		for(int qi = 0; request.getParameter("roeq_question" + qi) != null; qi++){
			qtmp = new BeansReportOralExaminationQuestion();
			
			qtmp.setRoeq_question(xss.escape(request.getParameter("roeq_question" + qi)));
			
			String roeq_answer = xss.escape(request.getParameter("roeq_answer" + qi));
			if(roeq_answer == null || roeq_answer.equals("")){
				roeq_answer = "なし";
			}
			qtmp.setRoeq_answer(roeq_answer);
			
			qtmp.setRoe_id(boe.getRoe_id());
			
			qform.add(qtmp);
		}
		
		// データベースのレコードを取得
		ArrayList<BeansReportOralExaminationQuestion> qdatabase = 
			BeansReportOralExaminationQuestion.listReportOralExaminationQuestion(boe.getRoe_id());
		
		// イテレータの生成
		ListIterator<BeansReportOralExaminationQuestion> qformIte = qform.listIterator();
		ListIterator<BeansReportOralExaminationQuestion> qdataBaseIte = qdatabase.listIterator(); 
		
		while(qdataBaseIte.hasNext()){
			if(qformIte.hasNext()){
				qformIte.next().setRoeq_id(qdataBaseIte.next().getRoeq_id());
	
			}else{
				BeansReportOralExaminationQuestion.deleteReportOralExaminationQuestion(qdataBaseIte.next().getRoeq_id());
			}
		}
		
		qformIte = qform.listIterator();
		
		while(qformIte.hasNext()){
			
			qtmp = qformIte.next();
			
			if(qtmp.getRoeq_id() != -1){
				
				qtmp.changeReportOralExaminationQuestion();
				
			}else{
				
				qtmp.createReportOralExaminationQuestion();
				
			}
		}
		//listページに飛ばす
		response.sendRedirect("./student/change_registered.jsp");
	}
}
