/**
*Beansの変更を予測したサーブレット変更
*
*/



/**
 * 同号企業説明会・報告書・変更
 * @author 信原美希
 */
package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansReportJoinMeeting;
import jp.bemax.se.graduation2011.model.BeansReportJoinMeetingQuestion;
import jp.bemax.se.graduation2011.model.BeansReportJoinMeetingReply;
/**
 * Servlet implementation class JoinMeetingChange
 */
public class JoinMeetingChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinMeetingChange() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード指定・コンテンツタイプ指定
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//Beansをインスタンス化
		BeansReportJoinMeeting bjm = new BeansReportJoinMeeting();
		//xss対策のクラスをインスタンス化
		Xss xss = new Xss();
		
		//セッション情報を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		
		//権限check
		if(role == null){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
	
		//データ代入
		int rjm_id = Integer.parseInt(request.getParameter("rjm_id"));
		//変更前の情報を取得
		BeansReportJoinMeeting report = BeansReportJoinMeeting.detailReportJoinMeeting(rjm_id);
		String status = report.getRjm_status();
		if(status.equals("end")){
			response.sendRedirect("JoinMeetingList");
			return;
		}
		
		//ログインID（学籍番号）をセッションから取得、セットする
		bjm.setL_id((String)session.getAttribute("l_id"));
		
		//合説ID
		bjm.setRjm_id(rjm_id);
		
		//説明会名
		String rjm_name = (xss.escape(request.getParameter("rjm_name")));
		if(rjm_name == null || rjm_name.equals(report.getRjm_name())){
			bjm.setRjm_name(report.getRjm_name());
		}else{
			bjm.setRjm_name(rjm_name);
		}

		//参加日
		String rjm_participate = (xss.escape(request.getParameter("rjm_participate_year") + request.getParameter("rjm_participate_month") + request.getParameter("rjm_participate_day")));
		if(rjm_participate == null){
			
			bjm.setRjm_participate(report.getRjm_participate());
		}else{
			bjm.setRjm_participate(rjm_participate);
		}
		
		//提出日
		String rjm_introduction = (xss.escape(request.getParameter("rjm_introduction_year") + request.getParameter("rjm_introduction_month") +request.getParameter("rjm_introduction_day")));
		if(rjm_introduction == null){
			bjm.setRjm_introduction(report.getRjm_introduction());
		}else{
			bjm.setRjm_introduction(rjm_introduction);
		}

		
		//企業情報・企業IDセット
		bjm.setComp_id(Integer.parseInt(request.getParameter("comp_id")));

		
				
		//担当者名
		String rjm_owner = (xss.escape(request.getParameter("rjm_owner")));
		if(rjm_owner == null){
			bjm.setRjm_owner(report.getRjm_owner());
		}else{
			bjm.setRjm_owner(rjm_owner);
		}
		
		//内容
		String rjm_content = (xss.escape(request.getParameter("rjm_content")));
		if(rjm_content == null){
			bjm.setRjm_content(report.getRjm_content());
		}else{
			bjm.setRjm_content(rjm_content);
		}
			
//<---------質問した内容に関する処理------>
		//質問した内容・答え
		// フォームの値を保持
		ArrayList<BeansReportJoinMeetingQuestion> qform = new ArrayList<BeansReportJoinMeetingQuestion>();
		// 一時領域
		BeansReportJoinMeetingQuestion qtmp = null;
		
		// フォームの値を取得
		for(int qi = 0; request.getParameter("rjmq_question" + qi) != null; qi++){
			qtmp = new BeansReportJoinMeetingQuestion();
			
			qtmp.setRjmq_question(xss.escape(request.getParameter("rjmq_question" + qi)));
			String rjmq_answer = xss.escape(request.getParameter("rjmq_answer" + qi));
			if(rjmq_answer.equals("") || rjmq_answer == null){
				rjmq_answer = "なし";
			}
			qtmp.setRjmq_answer(rjmq_answer);
			qtmp.setRjm_id(bjm.getRjm_id());
			
			qform.add(qtmp);
		}
		
		// データベースのレコードを取得
		ArrayList<BeansReportJoinMeetingQuestion> qdatabase = 
			BeansReportJoinMeetingQuestion.listReportJoinMeetingQuestion(bjm.getRjm_id());
		
		// イテレータの生成
		ListIterator<BeansReportJoinMeetingQuestion> qformIte = qform.listIterator();
		ListIterator<BeansReportJoinMeetingQuestion> qdataBaseIte = qdatabase.listIterator(); 
		
		while(qdataBaseIte.hasNext()){
			if(qformIte.hasNext()){
				qformIte.next().setRjmq_id(qdataBaseIte.next().getRjmq_id());
			}else{
				//削除処理,-1以外が返って来るまでループ処理
				for(;BeansReportJoinMeetingQuestion.
				deleteReportJoinMeetingQuestion(qdataBaseIte.next().getRjmq_id()) == -1;);
			}
		}
		
		qformIte = qform.listIterator();
		
		while(qformIte.hasNext()){
			qtmp = qformIte.next();
			if(qtmp.getRjmq_id() != -1){
				qtmp.changeReportJoinMeetingQuestion();
			}else{
				qtmp.createReportJoinMeetingQuestion();
			}
		}
		//<---------質問した内容に関する処理------>	
		
		
		//---------ここから質問された内容---------->
		//質問された内容・答え

		// フォームの値を保持
		ArrayList<BeansReportJoinMeetingReply> rform = new ArrayList<BeansReportJoinMeetingReply>();
		// 一時領域
		BeansReportJoinMeetingReply rtmp = null;
		
		// フォームの値を取得
		for(int ri = 0; request.getParameter("rjmr_rquestion" + ri) != null; ri++){
			rtmp = new BeansReportJoinMeetingReply();
			
			rtmp.setRjmr_question(xss.escape(request.getParameter("rjmr_rquestion" + ri)));
			String rjmr_answer = xss.escape(request.getParameter("rjmr_answer" + ri));
			if(rjmr_answer.equals("") || rjmr_answer == null){
				rjmr_answer = "なし";
			}
			rtmp.setRjmr_answer(rjmr_answer);
			rtmp.setRjm_id(bjm.getRjm_id());
			
			rform.add(rtmp);
		}
		
		// データベースのレコードを取得
		ArrayList<BeansReportJoinMeetingReply> rdatabase = 
			BeansReportJoinMeetingReply.listReportJoinMeetingReply(bjm.getRjm_id());
		
		// イテレータの生成
		ListIterator<BeansReportJoinMeetingReply> rformIte = rform.listIterator();
		ListIterator<BeansReportJoinMeetingReply> rdataBaseIte = rdatabase.listIterator(); 
		
		while(rdataBaseIte.hasNext()){
			if(rformIte.hasNext()){
				rformIte.next().setRjmr_id(rdataBaseIte.next().getRjmr_id());
			}else{
				//削除処理,-1以外が返って来るまでループ処理
				for(;BeansReportJoinMeetingReply.
				deleteReportJoinMeetingReply(rdataBaseIte.next().getRjmr_id()) == -1;);
			}
		}
		
		rformIte = rform.listIterator();
		
		while(rformIte.hasNext()){
			rtmp = rformIte.next();
			if(rtmp.getRjmr_id() != -1){
				rtmp.changeReportJoinMeetingReply();
			}else{
				rtmp.createReportJoinMeetingReply();
			}
		}
		
		//次回
		String rjm_nexttime = (xss.escape(request.getParameter("rjm_nexttime")));
		
		if(rjm_nexttime.equals("会社別訪問（個別セミナー）予定")
     	 		|| rjm_nexttime.equals("受験予定")){
			bjm.setRjm_nexttime(rjm_nexttime + "-" + request.getParameter("rjm_nexttime_year") + "-" + request.getParameter("rjm_nexttime_month") + "-" + request.getParameter("rjm_nexttime_day"));
		}else{
			bjm.setRjm_nexttime(rjm_nexttime);
		}
		
		//その他・特記事項
		String rjm_particular = (xss.escape(request.getParameter("rjm_particular")));
		if(rjm_particular == null){
			bjm.setRjm_particular(report.getRjm_particular());
		}else{
			bjm.setRjm_particular(rjm_particular);
		}
		
		//所感
		String rjm_impression = (xss.escape(request.getParameter("rjm_impression")));
		if(rjm_impression == null){
			bjm.setRjm_impression(report.getRjm_impression());
		}else{
			bjm.setRjm_impression(rjm_impression);
		}
		
		
		/*
		 * 状態がagainの時のみrenewに変更する。
		 * それ以外はnewのまま
		 */
		String rjm_status = report.getRjm_status();
		if(rjm_status.equals("again")){
			
			bjm.setRjm_status("renew");
			
		}else if(rjm_status.equals("renew")){
			
			bjm.setRjm_status("renew");
		
		}else{
			
			bjm.setRjm_status("new");
			
		}
		
		//変更
		if(bjm.changeReportJoinMeeting() == -1){
			// エラーページにリダイレクト
			response.sendRedirect("./err.jsp");
			return;
		}
		
		 //リダイレクト・JSPに戻る
		response.sendRedirect("./student/change_registered.jsp");
	}
}
		
		