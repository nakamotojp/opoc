/**
 * 面接試験報告書・削除<BR>
 * @author 信原美希
 */
 package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansReportOralExamination;

/**
 * Servlet implementation class OralExaminationDelete
 */
public class OralExaminationDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OralExaminationDelete() {
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
		
		//権限check
		if(role == null){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
		
		// データ代入・チェック
		String roe_id = request.getParameter("roe_id");
		BeansReportOralExamination data = null;
		
		//例外・エラーが出た場合は処理を中断してリダイレクト
		try{
			
			data = BeansReportOralExamination.
			detailReportOralExamination(Integer.parseInt(roe_id));
			if(data.getRoe_id() == -1){
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
		
		if(role.equals("student")){
			
			BeansReportOralExamination broe = 
				BeansReportOralExamination.detailReportOralExamination(Integer.parseInt(roe_id));
			if(broe.getRoe_status().equals("new")){
				// データ削除
				BeansReportOralExamination.deleteReportOralExamination(Integer.parseInt(roe_id));
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
				response.sendRedirect("OralExaminationList");
				return;
			}
		}else if(role.equals("teacher")){

			BeansReportOralExamination broe = 
				BeansReportOralExamination.detailReportOralExamination(Integer.parseInt(roe_id));
			if(!broe.getRoe_status().equals("end")){
				// データ削除
				BeansReportOralExamination.deleteReportOralExamination(Integer.parseInt(roe_id));
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
				// リダイレクト
				response.sendRedirect("OralExaminationList");
				return;
			}
			
		}else{
			
			// それ以外・ログインページへリダイレクト
			response.sendRedirect("./o_user_login.jsp");
			return;
			
		}
	}
}
