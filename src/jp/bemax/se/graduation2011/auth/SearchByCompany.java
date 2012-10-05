/**
 * 検索
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

import jp.bemax.se.graduation2011.model.BeansCourse;
import jp.bemax.se.graduation2011.model.BeansReportJoinMeeting;
import jp.bemax.se.graduation2011.model.BeansReportOralExamination;
import jp.bemax.se.graduation2011.model.BeansReportWrittenExamination;
import jp.bemax.se.graduation2011.model.BeansUser;

/**
 * Servlet implementation class SearchByCompany
 */
public class SearchByCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByCompany() {
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
		
		//権限チェック
		if(role == null){
			
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}else if(role.equals("teacher")){
			
			//教師用トップページに飛ばす
			response.sendRedirect("AuthorityNewReportList");
			return;
			
		}else if((!role.equals("student")) && (!role.equals("teacher"))){
			
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
			
		}
		
		if(request.getParameter("comp_id") == null || request.getParameter("comp_id") == ""){
			//リダイレクト
			getServletContext().getRequestDispatcher("/student/o_jub_hunting_search.jsp").forward(request, response);
			return;
		}
		
		//パラメータ取得
		int comp_id;
		int s_id;
		int report_type;
		String comp_name;
		
		//例外・エラーが出た場合は処理を中断してリダイレクト
		try{
			
			comp_id = Integer.parseInt(request.getParameter("comp_id"));
			s_id = Integer.parseInt(request.getParameter("s_id"));
			report_type = Integer.parseInt(request.getParameter("Num"));
			comp_name = new String(request.getParameter("comp_name").getBytes("iso-8859-1"), "utf-8");
		
		}catch(Exception e){
			
			//リダイレクト
			getServletContext().getRequestDispatcher("/student/o_jub_hunting_search.jsp").forward(request, response);
			return;
	
		}
		
		request.setAttribute("comp_id", request.getParameter("comp_id"));
		request.setAttribute("comp_name", comp_name);
		request.setAttribute("Num", request.getParameter("Num"));
		request.setAttribute("s_id", request.getParameter("s_id"));
		
		//変数を初期化
		ArrayList<BeansReportJoinMeeting> array1 = null;
		ArrayList<BeansReportWrittenExamination> array2 = null;
		ArrayList<BeansReportOralExamination> array3 = null;
		
		ArrayList<BeansReportJoinMeeting> list1 = new ArrayList<BeansReportJoinMeeting>();
		ArrayList<BeansReportWrittenExamination> list2 = new ArrayList<BeansReportWrittenExamination>();
		ArrayList<BeansReportOralExamination> list3 = new ArrayList<BeansReportOralExamination>();
		
		//検索したい報告書が全報告書の場合・または1~3以外の数字を受け取った場合
		if(report_type == -1 || report_type < -1 || report_type > 3){
			
			array1 = BeansReportJoinMeeting.searchReportJoinMeeting(comp_id);
			array2 = BeansReportWrittenExamination.searchReportWrittenExamination(comp_id);
			array3 = BeansReportOralExamination.searchReportOralExamination(comp_id);
			
			ListIterator<BeansReportJoinMeeting> iterator1 = array1.listIterator();
			ListIterator<BeansReportWrittenExamination> iterator2 = array2.listIterator();
			ListIterator<BeansReportOralExamination> iterator3 = array3.listIterator();
			
			//一時ファイル
			BeansReportJoinMeeting tmp1 = null;
			BeansReportWrittenExamination tmp2 = null;
			BeansReportOralExamination tmp3 = null;
			BeansUser bu = null;
			BeansCourse bc = null;
				
			//s_idが送られてきた時だけ検索
			if(s_id != -1){
				
				while(iterator1.hasNext()){
					
					tmp1 = iterator1.next();
					String l_id = tmp1.getL_id();
					bu = BeansUser.detailUser(l_id);
					bc = BeansCourse.detailCourse(bu.getC_id());
					
					//検索したい学科と報告書登録者の学科のs_idが一致した場合、listに格納
					if(bc.getS_id() == s_id){
						list1.add(tmp1);
					}
				}
				
				while(iterator2.hasNext()){
					
					tmp2 = iterator2.next();
					String l_id = tmp2.getL_id();
					bu = BeansUser.detailUser(l_id);
					bc = BeansCourse.detailCourse(bu.getC_id());
					
					//検索したい学科と報告書登録者の学科のs_idが一致した場合、listに格納
					if(bc.getS_id() == s_id){
						list2.add(tmp2);
					}
				}
				
				while(iterator3.hasNext()){
					
					tmp3 = iterator3.next();
					String l_id = tmp3.getL_id();
					bu = BeansUser.detailUser(l_id);
					bc = BeansCourse.detailCourse(bu.getC_id());
					
					//検索したい学科と報告書登録者の学科のs_idが一致した場合、listに格納
					if(bc.getS_id() == s_id){
						list3.add(tmp3);
					}
				}
			}else{
				list1 = array1;
				list2 = array2;
				list3 = array3;
			}
			
			ReportInfo ri = new ReportInfo();
			ri.listSearchCompaniesReport(list1, list2, list3);
			request.setAttribute("list", ri.getList());
			
			//リダイレクト
			getServletContext().getRequestDispatcher("/student/o_jub_hunting_search.jsp").forward(request, response);
			return;
		}
		
		//検索したい報告書が企業説明会の場合
		if(report_type == 1){
			
			array1 = BeansReportJoinMeeting.searchReportJoinMeeting(comp_id);
			ListIterator<BeansReportJoinMeeting> iterator1 = array1.listIterator();
			
			//一時ファイル
			BeansReportJoinMeeting tmp = null;
			BeansUser bu = null;
			BeansCourse bc = null;
			
			//s_idが送られてきた時だけ検索
			if(s_id != -1){
				
				while(iterator1.hasNext()){
					
					tmp = iterator1.next();
					String l_id = tmp.getL_id();
					bu = BeansUser.detailUser(l_id);
					bc = BeansCourse.detailCourse(bu.getC_id());
					
					//検索したい学科と報告書登録者の学科のs_idが一致した場合、listに格納
					if(bc.getS_id() == s_id){
						list1.add(tmp);
					}
				}
			}else{
				list1 = array1;
			}
			ReportInfo rf = new ReportInfo();
			rf.listSearchCompaniesJoinReport(list1);
			request.setAttribute("list", rf.getList());
			
			//リダイレクト
			getServletContext().getRequestDispatcher("/student/o_jub_hunting_search.jsp").forward(request, response);
			return;
		}
		
		//検索したい報告書が筆記試験の場合
		if(report_type == 2){
			
			array2 = BeansReportWrittenExamination.searchReportWrittenExamination(comp_id);
			ListIterator<BeansReportWrittenExamination> iterator2 = array2.listIterator();
			
			//一時ファイル
			BeansReportWrittenExamination tmp = null;
			BeansUser bu = null;
			BeansCourse bc = null;
			
			//s_nameが送られてきた時だけ検索
			if(s_id != -1){
				
				while(iterator2.hasNext()){
					
					tmp = iterator2.next();
					String l_id = tmp.getL_id();
					bu = BeansUser.detailUser(l_id);
					bc = BeansCourse.detailCourse(bu.getC_id());
					
					//検索したい学科と報告書登録者の学科のs_idが一致した場合、listに格納
					if(bc.getS_id() == s_id){
						list2.add(tmp);
					}
				}
			}else{
				list2 = array2;
			}
			ReportInfo rf = new ReportInfo();
			rf.listSearchCompaniesWrittenReport(list2);
			request.setAttribute("list", rf.getList());
			
			//リダイレクト
			getServletContext().getRequestDispatcher("/student/o_jub_hunting_search.jsp").forward(request, response);
			return;
		}
		
		//検索したい報告書が面接試験の場合
		if(report_type == 3){
			
			array3 = BeansReportOralExamination.searchReportOralExamination(comp_id);
			ListIterator<BeansReportOralExamination> iterator3 = array3.listIterator();
			
			BeansReportOralExamination tmp = null;
			BeansUser bu = null;
			BeansCourse bc = null;
			
			//s_nameが送られてきた時だけ検索
			if(s_id != -1){
				
				while(iterator3.hasNext()){
					
					tmp = iterator3.next();
					String l_id = tmp.getL_id();
					bu = BeansUser.detailUser(l_id);
					bc = BeansCourse.detailCourse(bu.getC_id());
					
					//検索したい学科と報告書登録者の学科のs_idが一致した場合、listに格納
					if(bc.getS_id() == s_id){
						list3.add(tmp);
					}
				}
			}else{
				list3 = array3;
			}
			ReportInfo rf = new ReportInfo();
			rf.listSearchCompaniesOralReport(list3);
			request.setAttribute("list", rf.getList());
			
			//リダイレクト
			getServletContext().getRequestDispatcher("/student/o_jub_hunting_search.jsp").forward(request, response);
			return;
		}
	}
}
