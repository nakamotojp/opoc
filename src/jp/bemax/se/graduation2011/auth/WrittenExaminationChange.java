package jp.bemax.se.graduation2011.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansReportWrittenExamination;

/**
 * Servlet implementation class WrittenExaminationChange
 */
public class WrittenExaminationChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WrittenExaminationChange() {
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
		
    	//session情報を取得
    	HttpSession session = request.getSession(true);
    	String role = (String)session.getAttribute("role");
		
		//権限check
		if(role == null){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
		
		// データ代入・チェック
		String rwe_id = request.getParameter("rwe_id");
		if(rwe_id == null){
			
			// リダイレクト
			response.sendRedirect("WrittenExaminationList");
			return;
		}

		//Beansをインスタンス化
		BeansReportWrittenExamination bwe = new BeansReportWrittenExamination();
		//xss対策のクラスをインスタンス化
		Xss xss = new Xss();
		
		//変更前の情報
		BeansReportWrittenExamination report = 
			BeansReportWrittenExamination.detailReportWrittenExamination(Integer.parseInt(rwe_id));		
		//企業IDをセット
		bwe.setComp_id(Integer.parseInt(request.getParameter("comp_id")));
	
		/**
		 * 筆記試験報告書を変更
		 */
		
		//ログインID（学籍番号）
		bwe.setL_id((String)session.getAttribute("l_id"));
		
		//報告書ID
		bwe.setRwe_id(Integer.parseInt(rwe_id));
			
		//提出日
		String rwe_introduction = (request.getParameter("rwe_introduction_year") + "-" + request.getParameter("rwe_introduction_month") + "-" + request.getParameter("rwe_introduction_day"));
		
		bwe.setRwe_introduction(rwe_introduction);
	
		//参加日
		String rwe_participate = (request.getParameter("rwe_participate_year") + "-" + request.getParameter("rwe_participate_month") + "-" + request.getParameter("rwe_participate_day"));
		
		bwe.setRwe_participate(rwe_participate);
		
		//何次試験
		String rwe_stage = (xss.escape(request.getParameter("rwe_stage")));
		if(rwe_stage == null){
			
			bwe.setRwe_stage(report.getRwe_stage());
		}else{
			
			bwe.setRwe_stage(rwe_stage);
		}
		
		//他校受験者人数
		String rwe_examother = request.getParameter("rwe_examother");
		if(rwe_examother == null){
			
			bwe.setRwe_examother(report.getRwe_examother());
		}else{
			
			bwe.setRwe_examother(Integer.parseInt(rwe_examother));
		}
		
		//他校受験者出身校
		String rwe_examotherschool = (xss.escape(request.getParameter("rwe_examotherschool")));
		if(rwe_examotherschool == null){
			
			bwe.setRwe_examotherschool(report.getRwe_examotherschool());
		}else{
			
			bwe.setRwe_examotherschool(rwe_examotherschool);
		}
		
		//当校受験者人数
		String rwe_exam = request.getParameter("rwe_exam");
		if(rwe_exam == null){
			
			bwe.setRwe_exam(report.getRwe_exam());
		}else{
			
			bwe.setRwe_exam(Integer.parseInt(rwe_exam));
		}
		
		//適性検査の内容
		String rwe_aptitude = (xss.escape(request.getParameter("rwe_aptitude")));
		if(rwe_aptitude == null){
			
			bwe.setRwe_aptitude(report.getRwe_aptitude());
		}else{
			
			bwe.setRwe_aptitude(rwe_aptitude);
		}
		
		//適性検査の時間
		String rwe_aptitudetime = request.getParameter("rwe_aptitudetime");
		if(rwe_aptitudetime == null){
			
			bwe.setRwe_aptitudetime(report.getRwe_aptitudetime());
		}else{
			
			bwe.setRwe_aptitudetime(Integer.parseInt(rwe_aptitudetime));
		}
		
		//筆記試験の時間
		String rwe_writtentime = request.getParameter("rwe_writtentime");
		if(rwe_writtentime == null){
			
			bwe.setRwe_writtentime(report.getRwe_writtentime());
		}else{
			
			bwe.setRwe_writtentime(Integer.parseInt(rwe_writtentime));
		}
		
		//作文・論文の時間
		String rwe_papertime = request.getParameter("rwe_papertime");
		if(rwe_papertime == null){
			
			bwe.setRwe_papertime(report.getRwe_papertime());
		}else{
			
			bwe.setRwe_papertime(Integer.parseInt(rwe_papertime));
		}
		
		//国語問題の内容
		String rwe_lang = (xss.escape(request.getParameter("rwe_lang")));
		if(rwe_lang == null){
			
			bwe.setRwe_lang(report.getRwe_lang());
		}else{
			
			bwe.setRwe_lang(rwe_lang);
		}
		
		//数学問題の内容
		String rwe_math = (xss.escape(request.getParameter("rwe_math")));
		if(rwe_math == null){
			
			bwe.setRwe_math(report.getRwe_math());
		}else{
			
			bwe.setRwe_math(rwe_math);
		}
		
		//英語問題の内容
		String rwe_english = (xss.escape(request.getParameter("rwe_english")));
		if(rwe_english == null){
			
			bwe.setRwe_english(report.getRwe_english());
		}else{
			
			bwe.setRwe_english(rwe_english);
		}
	
		//理科問題の内容
		String rwe_science = (xss.escape(request.getParameter("rwe_science")));
		if(rwe_science == null){
			
			bwe.setRwe_science(report.getRwe_science());
		}else{
			
			bwe.setRwe_science(rwe_science);
		}
		
		//社会問題の内容
		String rwe_society = (xss.escape(request.getParameter("rwe_society")));
		if(rwe_society == null){
			
			bwe.setRwe_society(report.getRwe_society());
		}else{
			
			bwe.setRwe_society(rwe_society);
		}
		
		//政治・経済問題の内容
		String rwe_politics = (xss.escape(request.getParameter("rwe_politics")));
		if(rwe_politics == null){
			
			bwe.setRwe_politics(report.getRwe_politics());
		}else{
			
			bwe.setRwe_politics(rwe_politics);
		}
		
		//専門知識問題の内容
		String rwe_expert = (xss.escape(request.getParameter("rwe_expert")));
		if(rwe_expert == null){
			
			bwe.setRwe_expert(report.getRwe_expert());
		}else{
			
			bwe.setRwe_expert(rwe_expert);
		}
		
		//作文・論文の内容
		String rwe_paper = (xss.escape(request.getParameter("rwe_paper")));
		if(rwe_paper == null){
			
			bwe.setRwe_paper(report.getRwe_paper());
		}else{
			
			bwe.setRwe_paper(rwe_paper);
		}
		
		//その他の問題内容
		String rwe_other = (xss.escape(request.getParameter("rwe_other")));
		if(rwe_other == null){
			
			bwe.setRwe_other(report.getRwe_other());
		}else{
			
			bwe.setRwe_other(rwe_other);
		}
		
		//合否連絡
		String rwe_accept = (xss.escape(request.getParameter("rwe_accept_year")) + "-" + xss.escape(request.getParameter("rwe_accept_month")) + "-" + xss.escape(request.getParameter("rwe_accept_day")));
		
		bwe.setRwe_accept(rwe_accept);
		
		/**
		 * 次回受験がありなら次回受験予定日、次回受験内容、準備知識・情報を入れる。<BR>
		 * 次回受験がなしなら「no」。<BR>
		 */
		if(request.getParameter("rwe_nexttime_check").equals("有る")){
			
			//次回受験
			bwe.setRwe_nexttime(xss.escape(request.getParameter("rwe_nexttime_year") + "-" + request.getParameter("rwe_nexttime_month") + "-" + request.getParameter("rwe_nexttime_day")));
			//次回受験内容
			bwe.setRwe_nextexam(xss.escape(request.getParameter("rwe_nextexam")));
			//準備知識・情報
			bwe.setRwe_preparation(xss.escape(request.getParameter("rwe_preparation")));
			
		}else if(request.getParameter("rwe_nexttime_check").equals("有るが未定")){
			
			//次回受験
			bwe.setRwe_nexttime(xss.escape(request.getParameter("rwe_nexttime_check")));
			//次回受験内容
			bwe.setRwe_nextexam(xss.escape(request.getParameter("rwe_nextexam")));
			//準備知識・情報
			bwe.setRwe_preparation(xss.escape(request.getParameter("rwe_preparation")));
			
		}else if(request.getParameter("rwe_nexttime_check").equals("無し")){
			
			//次回受験
			bwe.setRwe_nexttime(xss.escape(request.getParameter("rwe_nexttime_check")));
			
		}
	
		/*
		 * 状態がagainの時のみrenewに変更する。
		 * 状態がrenewのときはrenewのまま変更しない。
		 * それ以外はnewのまま
		 */
		String rwe_status = report.getRwe_status();
		if(rwe_status.equals("again")){
			
			bwe.setRwe_status("renew");
			
		}else if(rwe_status.equals("renew")){
			
			bwe.setRwe_status("renew");
		
		}else{
			
			bwe.setRwe_status("new");
			
		}
		
		//変更
		if(bwe.changeReportWrittenExamination() == -1){
			// エラーページにリダイレクト
			response.sendRedirect("./err.jsp");
			return;
		}
		
		//listページに飛ばす
		response.sendRedirect("./student/change_registered.jsp");
		
	}

}
