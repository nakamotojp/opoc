/**
 * 合同企業説明会・報告書・登録
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
 * Servlet implementation class JoinMeetingInsert
 */
public class JoinMeetingInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//質問・カウンタ
	private int i = 0;
	private int a = 0;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinMeetingInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッション情報を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		//権限check
		if(role != null){
			if(role.equals("student")){
				//権限が学生・報告書登録ページにリダイレクト		
				getServletContext().getRequestDispatcher("/student/o_report.jsp").forward(request, response);
				return;
			}
		}
		//それ以外・ログインページに飛ばす
		response.sendRedirect("./o_user_login.jsp");
		return;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード指定
		request.setCharacterEncoding("utf-8");
		// コンテンツタイプ指定
		response.setContentType("text/html; charset=utf-8");
		//セッション情報取得
		HttpSession session = request.getSession(true);
		String l_id = (String)session.getAttribute("l_id");
		String role = (String)session.getAttribute("role");
		
		//権限check
		if(role == null){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
		
		// Beansのインスタンス化
		BeansReportJoinMeeting bjm = new BeansReportJoinMeeting();
		//xss対策のクラスをインスタンス化
		Xss xss = new Xss();
		
		// データベースに追加,comp_idをセット
		int comp_id = Integer.parseInt(request.getParameter("comp_id"));
		bjm.setComp_id(comp_id);
			
		// 報告書追加
		//ログインID(学籍番号)
		bjm.setL_id(l_id);
		//説明会名
		bjm.setRjm_name(request.getParameter("rjm_name"));
		//参加日
		bjm.setRjm_participate(request.getParameter("rjm_participate_year") + request.getParameter("rjm_participate_month") + request.getParameter("rjm_participate_day"));
		//提出日
		bjm.setRjm_introduction(request.getParameter("rjm_introduction_year") + request.getParameter("rjm_introduction_month") + request.getParameter("rjm_introduction_day"));
		//担当者名
		bjm.setRjm_owner(xss.escape(request.getParameter("rjm_owner")));
		//内容
		bjm.setRjm_content(xss.escape(request.getParameter("rjm_content")));
		//その他・特記事項
		bjm.setRjm_particular(xss.escape(request.getParameter("rjm_particular")));
		//所感
		bjm.setRjm_impression(xss.escape(request.getParameter("rjm_impression")));
		//状態
		bjm.setRjm_status("new");
	
		/**
		 * 次回<BR>
		 * 次回のところが「受験予定」または「会社別訪問（個別セミナー）予定」である場合、<BR>
		 * 「受験予定」「会社別訪問（個別セミナー）予定」と該当する日付を入れる。<BR>
		 * 次回のところが「受験予定なし」の場合は「受験予定なし」だけ入れる。
		 */
		if(request.getParameter("rjm_nexttime").equals("会社別訪問（個別セミナー）予定")
     	 		|| request.getParameter("rjm_nexttime").equals("受験予定")) { 
			//受験予定あり
			bjm.setRjm_nexttime(request.getParameter("rjm_nexttime") + "-" + request.getParameter("rjm_nexttime_year") + "-" + request.getParameter("rjm_nexttime_month") + "-" + request.getParameter("rjm_nexttime_day"));
		}else{
			//受験予定なし
			bjm.setRjm_nexttime(request.getParameter("rjm_nexttime"));
		}
		//登録処理,正常に登録された場合処理を続行
		if(bjm.createReportJoinMeeting() == -1){
			// エラーページにリダイレクト
			response.sendRedirect("./err.jsp");
			return;
		}
	
		int rjm_id = bjm.getRjm_id();	
		
		//質問した内容・答え
		for(i = 0 ;; i++){
			//質問した回数をチェック
			String s = (xss.escape(request.getParameter("rjmq_question" + i)));
			if(s == null){
				break;
			}
		}
		
		for(int j = 0 ; j < i ; j++){

			BeansReportJoinMeetingQuestion tmp = new BeansReportJoinMeetingQuestion();
			//質問した内容
			tmp.setRjmq_question(xss.escape(request.getParameter("rjmq_question" + a)));
			//質問した答え
			String rjmq_answer = xss.escape(request.getParameter("rjmq_answer" + a));
			if(rjmq_answer.equals("") || rjmq_answer == null){
				rjmq_answer = "なし";
			}
			tmp.setRjmq_answer(rjmq_answer);
			
			//合説ID
			tmp.setRjm_id(rjm_id);
			//登録処理,正常に登録された場合処理を続行
			if(tmp.createReportJoinMeetingQuestion() == -1){
				
				//報告書・質問を削除
				ArrayList<BeansReportJoinMeetingQuestion> list = 
					BeansReportJoinMeetingQuestion.listReportJoinMeetingQuestion(rjm_id);
				ListIterator<BeansReportJoinMeetingQuestion> iterator = list.listIterator();
				
				while(iterator.hasNext()){
					BeansReportJoinMeetingQuestion jmq = iterator.next();
					//削除処理,-1以外が返って来るまでループ処理
					for(;BeansReportJoinMeetingQuestion.deleteReportJoinMeetingQuestion(jmq.getRjmq_id()) == -1;);
				}
				//削除処理,-1以外が返って来るまでループ処理
				for(;BeansReportJoinMeeting.deleteReportJoinMeeting(rjm_id) == -1;);
				
				// エラーページにリダイレクト
				response.sendRedirect("./err.jsp");
				return;
			}
			a++;
		}
		
		//フィールドを初期値に戻す
		i = 0;
		a = 0;
		
		//質問された内容・答え
		for(i = 0 ;; i++){
			//質問した回数をチェック
			String s = (xss.escape(request.getParameter("rjmr_rquestion" + i)));
			if(s == null){
				break;
			}
		}
		
		for(int j = 0 ; j < i ; j++){

			BeansReportJoinMeetingReply tmp = new BeansReportJoinMeetingReply();
			//質問された内容
			tmp.setRjmr_question(xss.escape(request.getParameter("rjmr_rquestion" + a)));
			//質問された答え
			String rjmr_answer = xss.escape(request.getParameter("rjmr_answer" + a));
			if(rjmr_answer.equals("") || rjmr_answer == null){
				rjmr_answer = "なし";
			}
			tmp.setRjmr_answer(rjmr_answer);
			
			//合説ID
			tmp.setRjm_id(rjm_id);
			//登録処理,正常に登録された場合処理を続行
			if(tmp.createReportJoinMeetingReply() == -1){
				//報告書・質問・解答を削除
				ArrayList<BeansReportJoinMeetingQuestion> list = 
					BeansReportJoinMeetingQuestion.listReportJoinMeetingQuestion(rjm_id);
				ListIterator<BeansReportJoinMeetingQuestion> iterator = list.listIterator();
				
				ArrayList<BeansReportJoinMeetingReply> list2 = 
					BeansReportJoinMeetingReply.listReportJoinMeetingReply(rjm_id);
				ListIterator<BeansReportJoinMeetingReply> iterator2 = list2.listIterator();
				
				while(iterator.hasNext()){
					BeansReportJoinMeetingQuestion jmq = iterator.next();
					//削除処理,-1以外が返って来るまでループ処理
					for(;BeansReportJoinMeetingQuestion.deleteReportJoinMeetingQuestion(jmq.getRjmq_id()) == -1;);
				}
				
				while(iterator2.hasNext()){
					BeansReportJoinMeetingReply jmr = iterator2.next();
					//削除処理,-1以外が返って来るまでループ処理
					for(;BeansReportJoinMeetingReply.deleteReportJoinMeetingReply(jmr.getRjmr_id()) == -1;);
				}
				//削除処理,-1以外が返って来るまでループ処理
				for(;BeansReportJoinMeeting.deleteReportJoinMeeting(rjm_id) == -1;);
				
				// エラーページにリダイレクト
				response.sendRedirect("./err.jsp");
				return;
			}
			a++;
			
		}
		
		// フィールドを初期値に戻す
		i = 0;
		a = 0;		
	
		// リダイレクト
		response.sendRedirect("./student/rjm_registered.jsp");
	}
}
