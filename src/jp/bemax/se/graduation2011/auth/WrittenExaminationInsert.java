package jp.bemax.se.graduation2011.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansReportWrittenExamination;

/**
 * Servlet implementation class WrittenExaminationInsert
 * 筆記試験報告書・追加
 */
public class WrittenExaminationInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WrittenExaminationInsert() {
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
				getServletContext().getRequestDispatcher("/student/o_written_e.jsp").forward(request, response);
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
		
		//セッション情報,企業名を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		
		//権限check
		if(role == null){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}

		if(!role.equals("student")){
			
			//学生ではないので、書き込み禁止
			//ログインページへ戻す
			response.sendRedirect("./o_user_login.jsp");
			return;
		
		}
		
		// Beansのインスタンス化
		BeansReportWrittenExamination bwe = new BeansReportWrittenExamination();
		//xss対策のクラスをインスタンス化
		Xss xss = new Xss();


		//企業登録・企業IDをセット
		bwe.setComp_id(Integer.parseInt(request.getParameter("comp_id")));

		// 筆記試験報告書追加
		bwe.setL_id((String)session.getAttribute("l_id"));
		bwe.setRwe_stage(xss.escape(request.getParameter("rwe_stage")));
		bwe.setRwe_participate(request.getParameter("rwe_participate_year") + "-" + request.getParameter("rwe_participate_month") + "-" + request.getParameter("rwe_participate_day"));
		bwe.setRwe_introduction(request.getParameter("rwe_introduction_year") + "-" + request.getParameter("rwe_introduction_month") + "-" + request.getParameter("rwe_introduction_day"));
		bwe.setRwe_examother(Integer.parseInt(request.getParameter("rwe_examother")));
		bwe.setRwe_exam(Integer.parseInt(request.getParameter("rwe_exam")));
		bwe.setRwe_examotherschool(xss.escape(request.getParameter("rwe_examotherschool")));
		bwe.setRwe_aptitude(xss.escape(request.getParameter("rwe_aptitude")));
		bwe.setRwe_aptitudetime(Integer.parseInt(request.getParameter("rwe_aptitudetime")));
		bwe.setRwe_writtentime(Integer.parseInt(request.getParameter("rwe_writtentime")));
		bwe.setRwe_papertime(Integer.parseInt(request.getParameter("rwe_papertime")));
		bwe.setRwe_lang(xss.escape(request.getParameter("rwe_lang")));
		bwe.setRwe_math(xss.escape(request.getParameter("rwe_math")));
		bwe.setRwe_english(xss.escape(request.getParameter("rwe_english")));
		bwe.setRwe_science(xss.escape(request.getParameter("rwe_science")));
		bwe.setRwe_society(xss.escape(request.getParameter("rwe_society")));
		bwe.setRwe_politics(xss.escape(request.getParameter("rwe_politics")));
		bwe.setRwe_expert(xss.escape(request.getParameter("rwe_expert")));
		bwe.setRwe_paper(xss.escape(request.getParameter("rwe_paper")));
		bwe.setRwe_other(xss.escape(request.getParameter("rwe_other")));
		bwe.setRwe_accept(xss.escape(request.getParameter("rwe_accept_year") + "-" + request.getParameter("rwe_accept_month") + "-" + request.getParameter("rwe_accept_day")));
		bwe.setRwe_status("new");

		// 次回受験
		/**
		 * 次回受験がありなら次回受験予定日、次回受験内容、準備知識・情報を入れる。<BR>
		 * 次回受験がなしなら「no」。<BR>
		 */
		if(request.getParameter("rwe_nexttime_check").equals("有る")){
			
			bwe.setRwe_nexttime(xss.escape(request.getParameter("rwe_nexttime_year") + "-" + request.getParameter("rwe_nexttime_month") + "-" + request.getParameter("rwe_nexttime_day")));
			bwe.setRwe_nextexam(xss.escape(request.getParameter("rwe_nextexam")));
			bwe.setRwe_preparation(xss.escape(request.getParameter("rwe_preparation")));
			
		}else if(request.getParameter("rwe_nexttime_check").equals("有る") || request.getParameter("rwe_nexttime_check").equals("有るが未定")){
			bwe.setRwe_nexttime(xss.escape(request.getParameter("rwe_nexttime_check")));
			bwe.setRwe_nextexam(xss.escape(request.getParameter("rwe_nextexam")));
			bwe.setRwe_preparation(xss.escape(request.getParameter("rwe_preparation")));
			
		}else if(request.getParameter("rwe_nexttime_check").equals("無し")){
			bwe.setRwe_nexttime(xss.escape(request.getParameter("rwe_nexttime_check")));
			
		}
		
		//登録処理,正常に登録された場合処理を続行
		if(bwe.createReportWrittenExamination() == -1){
			// エラーページにリダイレクト
			response.sendRedirect("./err.jsp");
			return;
		}

		// リダイレクト
		response.sendRedirect("./student/rwe_registered.jsp");
		
	}
}
