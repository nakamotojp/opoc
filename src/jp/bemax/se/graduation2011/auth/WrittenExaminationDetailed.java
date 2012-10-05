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
 * Servlet implementation class WrittenExaminationDetailed
 * 筆記試験報告書・詳細
 */
public class WrittenExaminationDetailed extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WrittenExaminationDetailed() {
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
    	String id = (String)session.getAttribute("l_id");
    	
    	if(role == null){
    		
    		//権限がない場合はログインページへリダイレクト
    		response.sendRedirect("./o_user_login.jsp");
			return;
    	}

		// データ代入・チェック
		String rwe_id = request.getParameter("rwe_id");
		BeansReportWrittenExamination data = null;
		
		//例外・エラーが出た場合は処理を中断してリダイレクト
		try{
			
			data = BeansReportWrittenExamination.
			detailReportWrittenExamination(Integer.parseInt(rwe_id));
			if(data.getRwe_id() == -1){
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
		
		//データ取得
		BeansCompany company = BeansCompany.detailCompany(data.getComp_id());
		BeansCompanyTrade ct = BeansCompanyTrade.detailCompanyTrade(company.getCompt_id());
		BeansUser user = BeansUser.detailUser(data.getL_id());
		BeansCourse course = BeansCourse.detailCourse(user.getC_id());
		BeansSubject subject = BeansSubject.detailSubject(course.getS_id());
		
		//印刷用のパラメータを取得
		String ppp = request.getParameter("ppp");
		
		
		
		//変更できるユーザかどうか判断
    	String edit = "false";
    	
		//学籍番号とセッションで取得したIDの比較、状態がend以外であるかどうか
		if(data.getL_id().equals(id) && !data.getRwe_status().equals("end")){
			
			edit = "true";
			
		}else if(!role.equals("teacher") && !data.getL_id().equals(id) && !data.getRwe_status().equals("end")){
			response.sendRedirect("WrittenExaminationList");
			return;
		}
		
		//削除できるユーザかどうか判断
		String del = "false";
		//学籍番号とセッションで取得したIDの比較、状態がnewであるかどうか
		if(data.getL_id().equals(id) && data.getRwe_status().equals("new")){
			
			del = "true";
		}
		
		// データセット
		request.setAttribute("l_id", data.getL_id());
		request.setAttribute("u_name", user.getU_name());
		request.setAttribute("c_name", course.getC_name());
		request.setAttribute("s_name", subject.getS_name());
		
		request.setAttribute("edit", edit);
		request.setAttribute("del", del);

		request.setAttribute("comp_id", company.getComp_id());
		
		if(company.getCompt_position().equals("first")){
			request.setAttribute("comp_name", ct.getCompt_name() + company.getComp_name());	
		}else{
			request.setAttribute("comp_name", company.getComp_name() + ct.getCompt_name());
		}
		
		request.setAttribute("comp_zip", company.getComp_zip());
		request.setAttribute("comp_address", company.getComp_address());
		request.setAttribute("comp_phone", company.getComp_phone());

		request.setAttribute("rwe_id", data.getRwe_id());
		request.setAttribute("rwe_stage", data.getRwe_stage());
		request.setAttribute("rwe_participate", data.getRwe_participate());
		request.setAttribute("rwe_introduction", data.getRwe_introduction());
		request.setAttribute("rwe_examother", data.getRwe_examother());
		request.setAttribute("rwe_exam", data.getRwe_exam());
		request.setAttribute("rwe_examotherschool", data.getRwe_examotherschool());
		request.setAttribute("rwe_aptitude", data.getRwe_aptitude());
		request.setAttribute("rwe_aptitudetime", data.getRwe_aptitudetime());
		request.setAttribute("rwe_writtentime", data.getRwe_writtentime());
		request.setAttribute("rwe_papertime", data.getRwe_papertime());
		request.setAttribute("rwe_lang", data.getRwe_lang());
		request.setAttribute("rwe_math", data.getRwe_math());
		request.setAttribute("rwe_english", data.getRwe_english());
		request.setAttribute("rwe_science", data.getRwe_science());
		request.setAttribute("rwe_society", data.getRwe_society());
		request.setAttribute("rwe_politics", data.getRwe_politics());
		request.setAttribute("rwe_expert", data.getRwe_expert());
		request.setAttribute("rwe_paper", data.getRwe_paper());
		request.setAttribute("rwe_other", data.getRwe_other());
		request.setAttribute("rwe_accept", data.getRwe_accept());
		request.setAttribute("rwe_nexttime", data.getRwe_nexttime());
		request.setAttribute("rwe_nextexam", data.getRwe_nextexam());
		request.setAttribute("rwe_preparation", data.getRwe_preparation());
		request.setAttribute("rwe_status", data.getRwe_status());
				
		
		//権限が教員
		if(role.equals("teacher")){
			
			/**
			 * 印刷のボタンが押されていない場合(null)の時は詳細ページを表示。
			 * 印刷のボタンが押されている場合(nullでない)の時は印刷用のページを表示。
			 */
			if(ppp != null){
				
				//印刷用のｊｓｐにリダイレクト
				getServletContext().getRequestDispatcher("/teacher/authority_o_written_e_print.jsp").forward(request, response);
				return;
				
			}else{
				
				//詳細ページにリダイレクト
				getServletContext().getRequestDispatcher("/teacher/authority_o_written_e_op.jsp").forward(request, response);
				return;
				
			}
			
		//権限が学生
		}else if(role.equals("student")){
			
			/**
			 * 印刷のボタンが押されていない場合(null)の時は詳細ページを表示。
			 * 印刷のボタンが押されている場合(nullでない)の時は印刷用のページを表示。
			 */
			if(ppp != null){
				
				//印刷用のｊｓｐにリダイレクト
				getServletContext().getRequestDispatcher("/student/o_written_e_print.jsp").forward(request, response);
				return;
				
			}else{
				
				//詳細ページにリダイレクト
				getServletContext().getRequestDispatcher("/student/o_written_e_op.jsp").forward(request, response);
				return;
				
			}
			
			
		}else{
			
			//それ以外の場合・ログインページへ戻す
			response.sendRedirect("./o_user_login.jsp");
			return;
			
		}
	
	}

}
