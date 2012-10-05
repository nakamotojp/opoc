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
import jp.bemax.se.graduation2011.model.BeansReportOralExamination;
import jp.bemax.se.graduation2011.model.BeansReportWrittenExamination;
import jp.bemax.se.graduation2011.model.BeansUser;

/**
 * Servlet implementation class AuthorityReportList
 */
public class AuthorityReportList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorityReportList() {
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
		
		//権限チェック
		if(role == null){
			
			//権限がない場合ログインページへ
			response.sendRedirect("./o_user_login.jsp");
    		return;
			
		}
		
		//権限が教員の場合
		if(role.equals("teacher")){
			
			//セッション情報をセット
			session.setAttribute("report", "mix");
			
			String l_id = (String)session.getAttribute("l_id");
			BeansUser bu = BeansUser.detailUser(l_id);
			int c_id = bu.getC_id();
			int u_year = bu.getU_year();
			
			ArrayList<BeansReportJoinMeeting> list1 = null;
			ArrayList<BeansReportWrittenExamination> list2 = null;
			ArrayList<BeansReportOralExamination> list3 = null;
			
			//就職部の先生の場合は全件セット
			if(c_id != 14){
				
				//各報告書のリスト取得
				list1 = BeansReportJoinMeeting.searchReportJoinMeeting(c_id, u_year);
				list2 = BeansReportWrittenExamination.searchReportWrittenExamination(c_id, u_year);
				list3 = BeansReportOralExamination.searchReportOralExamination(c_id, u_year);

				
			}else{
				
				//各報告書のリスト取得
				list1 = BeansReportJoinMeeting.listReportJoinMeeting();
				list2 = BeansReportWrittenExamination.listReportWrittenExamination();
				list3 = BeansReportOralExamination.listReportOralExamination();

			}
			
			ReportInfo bi = new ReportInfo();
			bi.authorityListReportInfo(list1, list2, list3);
			
			//リストをセット
			request.setAttribute("list", bi.getList());
			request.setAttribute("list2", bi.getE_list());
			//カウンタセット
			request.setAttribute("n_count", bi.getN_count());
			request.setAttribute("r_count", bi.getR_count());
			request.setAttribute("a_count", bi.getA_count());
		
			//個人専用リストページに飛ぶ
			getServletContext().getRequestDispatcher("/teacher/authority_o_full_list.jsp").forward(request, response);
			return;
    		
		}else{
			
			//それ以外・ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッション情報を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		
		//権限check
		if(role == null || !role.equals("teacher")){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
				
		//コースID、学年を受け取る
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		int u_year = Integer.parseInt(request.getParameter("u_year"));
		
		//変数初期化
		ArrayList<BeansReportJoinMeeting> list1 = new ArrayList<BeansReportJoinMeeting>();
		ArrayList<BeansReportWrittenExamination> list2 = new ArrayList<BeansReportWrittenExamination>();
		ArrayList<BeansReportOralExamination> list3 = new ArrayList<BeansReportOralExamination>();
		
		//学年が送られてきているか判定
		if(u_year != -1){
			
			//各報告書のリスト取得
			list1 = BeansReportJoinMeeting.searchReportJoinMeeting(c_id, u_year);
			list2 = BeansReportWrittenExamination.searchReportWrittenExamination(c_id,u_year);
			list3 = BeansReportOralExamination.searchReportOralExamination(c_id,u_year);
	
			
		}else{			

			/**
			 * 学年が選択されていない場合(u_year == null)
			 */
			//各報告書のリスト取得
			ArrayList<BeansReportJoinMeeting> array1 = BeansReportJoinMeeting.listReportJoinMeeting();
			ArrayList<BeansReportWrittenExamination> array2 = BeansReportWrittenExamination.listReportWrittenExamination();
			ArrayList<BeansReportOralExamination> array3 = BeansReportOralExamination.listReportOralExamination();
			

			//取得したリストのiterator作成
			ListIterator<BeansReportJoinMeeting> iterator1 = array1.listIterator();
			ListIterator<BeansReportWrittenExamination> iterator2 = array2.listIterator();
			ListIterator<BeansReportOralExamination> iterator3 = array3.listIterator();			
			
			BeansReportJoinMeeting tmp1 = null;
			BeansReportWrittenExamination tmp2 = null;
			BeansReportOralExamination tmp3 = null;
			BeansUser bu = null;
			//学籍番号
			String l_id = null;
			
			//合説の内容をリストに入れる
			while(iterator1.hasNext()){
				
				tmp1 = iterator1.next();
				l_id = tmp1.getL_id();
				bu = BeansUser.detailUser(l_id);
				
				if(c_id == bu.getC_id()){
					
					list1.add(tmp1);
					
				}
			}
			
			//筆記の内容をリストに入れる
			while(iterator2.hasNext()){
				
				tmp2 = iterator2.next();
				l_id = tmp2.getL_id();
				bu = BeansUser.detailUser(l_id);
				
				if(c_id == bu.getC_id()){
					
					list2.add(tmp2);
				}
			}
			
			//面接の内容をリストに入れる
			while(iterator3.hasNext()){
				
				tmp3 = iterator3.next();
				l_id = tmp3.getL_id();
				bu = BeansUser.detailUser(l_id);
				
				if(c_id == bu.getC_id()){
					
					list3.add(tmp3);
				}
			}
		}
			
		//３つの報告書を１つにまとめる
		ReportInfo bi = new ReportInfo();
		bi.authorityListReportInfo(list1,list2,list3);
		//リストをセット
		request.setAttribute("list", bi.getList());
		request.setAttribute("list2", bi.getE_list());
		//カウンタセット
		request.setAttribute("n_count", bi.getN_count());
		request.setAttribute("r_count", bi.getR_count());
		request.setAttribute("a_count", bi.getA_count());
		
		//教員リストページに飛ぶ
		getServletContext().getRequestDispatcher("/teacher/authority_o_full_list.jsp").forward(request, response);
	}
}
