package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansReportWrittenExamination;

/**
 * Servlet implementation class WrittenExaminationDelete
 * 筆記試験報告書・削除
 */
public class WrittenExaminationDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WrittenExaminationDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		
		if(role == null){
			
			//権限がない場合ログインページへ飛ぶ
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
	
		if(role.equals("student")){
		
			BeansReportWrittenExamination brwe = 
				BeansReportWrittenExamination.detailReportWrittenExamination(Integer.parseInt(rwe_id));
			if(brwe.getRwe_status().equals("new")){
				// データ削除
				BeansReportWrittenExamination.deleteReportWrittenExamination(Integer.parseInt(rwe_id));
			}
			String report = (String)session.getAttribute("report");
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
		}else if(role.equals("teacher")){

			BeansReportWrittenExamination brwe = 
				BeansReportWrittenExamination.detailReportWrittenExamination(Integer.parseInt(rwe_id));
			if(!brwe.getRwe_status().equals("end")){
				// データ削除
				BeansReportWrittenExamination.deleteReportWrittenExamination(Integer.parseInt(rwe_id));
			}
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
				
				// リダイレクト・筆記試験報告書一覧
				response.sendRedirect("WrittenExaminationList");
				return;
			}
			
		}else{
			
			// それ以外・ログインページへリダイレクト
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
	}
}
