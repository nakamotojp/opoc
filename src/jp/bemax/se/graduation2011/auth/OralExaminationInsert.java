/**
 * 面接試験報告書・登録<BR>
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

import jp.bemax.se.graduation2011.model.BeansReportOralExamination;
import jp.bemax.se.graduation2011.model.BeansReportOralExaminationQuestion;

/**
 * Servlet implementation class OralExaminationInsert
 */
public class OralExaminationInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//カウンタ
	private int i = 0;
	//カウンタ２
	private int a = 0;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OralExaminationInsert() {
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
		
		//セッション情報を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		//権限check
		if(role != null){
			if(role.equals("student")){
				//報告書登録ページにリダイレクト		
				getServletContext().getRequestDispatcher("/student/o_oral_e.jsp").forward(request, response);
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
		// エンコード・コンテンツタイプ指定
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		//セッション情報を取得
		HttpSession session = request.getSession(true);		
		String role = (String)session.getAttribute("role");
		
		//権限check
		if(role == null){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
		
		// Beansのインスタンス化
		BeansReportOralExamination boe = new BeansReportOralExamination();
		//xss対策のクラスをインスタンス化
		Xss xss = new Xss();

		//企業IDセット
		boe.setComp_id(Integer.parseInt(request.getParameter("comp_id")));
		
		// 面接試験報告書追加
		//ログインID
		boe.setL_id((String)session.getAttribute("l_id"));
		// 参加日
		boe.setRoe_participate(request.getParameter("roe_participate_year") + request.getParameter("roe_participate_month") + request.getParameter("roe_participate_day"));
		// 提出日
		boe.setRoe_introduction(request.getParameter("roe_introduction_year") + request.getParameter("roe_introduction_month") + request.getParameter("roe_introduction_day"));
		//　何次試験
		boe.setRoe_stage(xss.escape(request.getParameter("roe_stage")));
		// 面接官人数
		boe.setRoe_interviewer(Integer.parseInt(request.getParameter("roe_interviewer")));
		// 他校受験人数
		boe.setRoe_examother(Integer.parseInt(request.getParameter("roe_examother")));
		// 当校受験人数
		boe.setRoe_exam(Integer.parseInt(request.getParameter("roe_exam")));
		// 受験者出身校
		boe.setRoe_examotherschool(xss.escape(request.getParameter("roe_examotherschool")));
		// 面接担当者の氏名、役職
		boe.setRoe_nameortitle(xss.escape(request.getParameter("roe_nameortitle")));
		//状態
		boe.setRoe_status("new");
		
		//グループディスカッションの有無
		boe.setRoe_discussion(request.getParameter("roe_discussion"));
		
		//ディスカッションがある場合は、値をセットする
		if(request.getParameter("roe_discussion").equals("yes")){
			
			// 人数
			boe.setRoe_discussionnum(Integer.parseInt(request.getParameter("roe_discussionnum")));
			// 時間
			boe.setRoe_discussiontime(Integer.parseInt(request.getParameter("roe_discussiontime")));
			// テーマ
			boe.setRoe_discussiontheme(xss.escape(request.getParameter("roe_discussiontheme")));

		}
		
		// 合否連絡
		boe.setRoe_accept(request.getParameter("roe_accept_year") + "-" + request.getParameter("roe_accept_month") + "-" + request.getParameter("roe_accept_day"));
		
		/**
		 * 次回受験がある場合は、受験予定日、次回試験内容、準備知識・情報を入れる
		 * なしの場合はno。
		 */	
		if(request.getParameter("roe_nexttime_check").equals("有る")){
			
			// 次回受験
			String roe_nexttime = request.getParameter("roe_nexttime_year") + "-";
			roe_nexttime += request.getParameter("roe_nexttime_month") + "-";
			roe_nexttime += request.getParameter("roe_nexttime_day");
			boe.setRoe_nexttime(roe_nexttime);
			// 次回試験内容
			boe.setRoe_nextexam(xss.escape(request.getParameter("roe_nextexam")));
			// 準備知識・情報
			boe.setRoe_preparation(xss.escape(request.getParameter("roe_preparation")));
				
		}else if(request.getParameter("roe_nexttime_check").equals("有るが未定")){
			
			// 次回受験
			boe.setRoe_nexttime(request.getParameter("roe_nexttime_check"));
			// 次回試験内容
			boe.setRoe_nextexam(xss.escape(request.getParameter("roe_nextexam")));
			// 準備知識・情報
			boe.setRoe_preparation(xss.escape(request.getParameter("roe_preparation")));
				
		}else if(request.getParameter("roe_nexttime_check").equals("無し")){
			
			// 次回受験
			boe.setRoe_nexttime(request.getParameter("roe_nexttime_check"));
		}
		
		//登録処理,正常に登録された場合処理を続行
		if(boe.createReportOralExamination() == -1){
			//エラーページにリダイレクト
			response.sendRedirect("./err.jsp");
			return;
		}
			
		//質問がいくつあるか
		//無限ループ
		for(i = 0 ;; i++){
			
			String s = (xss.escape(request.getParameter("roeq_question" + i)));

			if(s == null){
				
				if(i == 0){
					//質問なし・リダイレクトで終了
					response.sendRedirect("./student/roe_registered.jsp");
					return;
				}
				break;
			}
		}
		
		int roe_id = boe.getRoe_id();
		
		BeansReportOralExaminationQuestion tmp;	
		
		// Beansをnewしてaddする
		for(int j = 0 ; j < i ; j++){

			tmp = new BeansReportOralExaminationQuestion();
			tmp.setRoeq_question(xss.escape(request.getParameter("roeq_question" + a)));
			
			String roeq_answer = xss.escape(request.getParameter("roeq_answer" + a));
			if(roeq_answer == null || roeq_answer.equals("")){
				roeq_answer = "なし";
			}
			tmp.setRoeq_answer(roeq_answer);
			
			tmp.setRoe_id(roe_id);
			
			//登録処理,正常に登録された場合処理を続行
			if(tmp.createReportOralExaminationQuestion() == -1){
				
				//報告書・質問を削除
				ArrayList<BeansReportOralExaminationQuestion> list = 
				BeansReportOralExaminationQuestion.listReportOralExaminationQuestion(roe_id);
				ListIterator<BeansReportOralExaminationQuestion> iterator = list.listIterator();
				
				while(iterator.hasNext()){
					BeansReportOralExaminationQuestion oeq = iterator.next();
					//削除処理,-1以外が返って来るまでループ処理
					for(;BeansReportOralExaminationQuestion.
					deleteReportOralExaminationQuestion(oeq.getRoeq_id()) == -1;);
				}
				//削除処理,-1以外が返って来るまでループ処理
				for(;BeansReportOralExamination.deleteReportOralExamination(roe_id) == -1;);
				
				// エラーページにリダイレクト
				response.sendRedirect("./err.jsp");
				return;
			}
			a++;
		}
		
		//フィールドを初期値に戻す
		i = 0;
		a = 0;
		
		// リダイレクト
		response.sendRedirect("./student/roe_registered.jsp");
	}
}
