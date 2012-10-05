/**
 * 合同企業説明会・報告書・一覧
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
import jp.bemax.se.graduation2011.model.BeansUser;

/**
 * Servlet implementation class JoinMeetingList
 */
public class JoinMeetingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinMeetingList() {
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
		//セッション情報取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		
		//権限check
		if(role == null || !role.equals("teacher")){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
		
		//パラメータ取得
		String c_id = request.getParameter("c_id");
		String u_year = request.getParameter("u_year");		
		
		//変数・番号初期化
		String l_id = null;
		JoinMeetingInfo jmi = new JoinMeetingInfo();
		ArrayList<BeansReportJoinMeeting> list1 = new ArrayList<BeansReportJoinMeeting>();
				
		/**
		 * 学年が送られてきているか判定
		 * 学年の中身が-1でない場合はそれぞれの学年の一覧を表示する
		 */
		if(Integer.parseInt(u_year) != -1){
			
			//コースID、学年を元に一覧取得
			list1 = BeansReportJoinMeeting.searchReportJoinMeeting(Integer.parseInt(c_id) , Integer.parseInt(u_year));
			
			jmi.authorityListJoinMeetingInfo(list1);
			request.setAttribute("list", jmi.getList());
			request.setAttribute("list2", jmi.getList2());
			//カウンタセット
			request.setAttribute("n_count", jmi.getN_count());
			request.setAttribute("r_count", jmi.getR_count());
			request.setAttribute("a_count", jmi.getA_count());
			
		}else{
			
			/**
			 * 学年が選択されていない場合、コースの全学年の一覧を表示する
			 */
			//一覧取得
			ArrayList<BeansReportJoinMeeting> array = BeansReportJoinMeeting.listReportJoinMeeting();
			
			//取得したリストのiterator作成
			ListIterator<BeansReportJoinMeeting> iterator = array.listIterator();
			
			BeansReportJoinMeeting tmp1 = null;
			BeansUser bu = null;
			
			//合説の内容をリストに入れる
			while(iterator.hasNext()){
				
				tmp1 = iterator.next();
				l_id = tmp1.getL_id();
				bu = BeansUser.detailUser(l_id);
				
				if(Integer.parseInt(c_id) == bu.getC_id()){
					
					list1.add(tmp1);
					
				}
			}
			
			jmi.authorityListJoinMeetingInfo(list1);
			request.setAttribute("list", jmi.getList());
			request.setAttribute("list2", jmi.getList2());
			//カウンタセット
			request.setAttribute("n_count", jmi.getN_count());
			request.setAttribute("r_count", jmi.getR_count());
			request.setAttribute("a_count", jmi.getA_count());
			
		}
		getServletContext().getRequestDispatcher("/teacher/authority_o_report_list.jsp").forward(request, response);				
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード・コンテンツタイプ指定
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//セッション情報を取得・セット
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		session.setAttribute("report", "simple");
			
		//権限check
		if(role == null){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
		
		if(role.equals("teacher")){			
			
			String l_id = (String)session.getAttribute("l_id");
			BeansUser bu = BeansUser.detailUser(l_id);
			int c_id = bu.getC_id();
			int u_year = bu.getU_year();
			
			ArrayList<BeansReportJoinMeeting> array = null;
			
			//就職部の先生の場合は全件リストを生成
			if(c_id != 14){
						
				//教員のコースID、学年で該当する報告書を検索
				array = BeansReportJoinMeeting.searchReportJoinMeeting(c_id, u_year);
			
			}else{
				
				//報告書を全件検索
				array = BeansReportJoinMeeting.listReportJoinMeeting();
				
			}
			
			JoinMeetingInfo jmi = new JoinMeetingInfo();
			
			jmi.authorityListJoinMeetingInfo(array);
			request.setAttribute("list", jmi.getList());
			request.setAttribute("list2", jmi.getList2());
			//カウンタセット
			request.setAttribute("n_count", jmi.getN_count());
			request.setAttribute("r_count", jmi.getR_count());
			request.setAttribute("a_count", jmi.getA_count());
			getServletContext().getRequestDispatcher("/teacher/authority_o_report_list.jsp").forward(request, response);
			return;

		}else if(role.equals("student")){
			
			ArrayList<BeansReportJoinMeeting> array = BeansReportJoinMeeting.listReportJoinMeeting();
			ArrayList<JoinMeetingInfo> list = JoinMeetingInfo.listJoinMeetingInfo(array);
			request.setAttribute("list", list);
			getServletContext().getRequestDispatcher("/student/o_report_list.jsp").forward(request, response);
			return;
			
		}else{
			
			//それ以外の場合・ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
			
		}
	}
}
