/**
 * 面接試験報告書・一覧<BR>
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
import jp.bemax.se.graduation2011.model.BeansUser;

/**
 * Servlet implementation class OralExaminationList
 */
public class OralExaminationList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OralExaminationList() {
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
		String c_id = request.getParameter("c_id");
		String u_year = request.getParameter("u_year");
		
		
		//変数・番号初期化
		String l_id = null;
		OralExaminationInfo oei = new OralExaminationInfo();
		ArrayList<BeansReportOralExamination> list1 = new ArrayList<BeansReportOralExamination>();
			
		/**
		 * 学年が送られてきているか判定
		 * 学年の中身が-1でない場合はそれぞれの学年の一覧を表示する
		 */
		if(Integer.parseInt(u_year) != -1){
			
			//コースID、学年を元に一覧取得
			list1 = BeansReportOralExamination.searchReportOralExamination(Integer.parseInt(c_id) , Integer.parseInt(u_year));
			oei.authorityListOralExaminationInfo(list1);
			
			request.setAttribute("list", oei.getList());
			request.setAttribute("list2", oei.getList2());
			//カウンタセット
			request.setAttribute("n_count", oei.getN_count());
			request.setAttribute("r_count", oei.getR_count());
			request.setAttribute("a_count", oei.getA_count());
			getServletContext().getRequestDispatcher("/teacher/authority_o_oral_e_list.jsp").forward(request, response);
			
		}else{
			
			/**
			 * 学年が選択されていない場合、コースの全学年の一覧を表示する
			 */
			//一覧取得
			ArrayList<BeansReportOralExamination> array = BeansReportOralExamination.listReportOralExamination();
			
			//取得した一覧のiterator作成
			ListIterator<BeansReportOralExamination> iterator = array.listIterator();
			
			BeansReportOralExamination tmp1 = null;
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
			
			oei.authorityListOralExaminationInfo(list1);
			request.setAttribute("list", oei.getList());
			request.setAttribute("list2", oei.getList2());
			//カウンタセット
			request.setAttribute("n_count", oei.getN_count());
			request.setAttribute("r_count", oei.getR_count());
			request.setAttribute("a_count", oei.getA_count());
			
		}

		getServletContext().getRequestDispatcher("/teacher/authority_o_oral_e_list.jsp").forward(request, response);
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
			
		//権限を判断してlistを作成
		if(role.equals("teacher")){
			
			//セッションで教員のIDを取得
			String l_id = (String)session.getAttribute("l_id");

			//コースID、学年を取得
			BeansUser user = BeansUser.detailUser(l_id);
			int c_id = user.getC_id();
			int u_year = user.getU_year();
			
			ArrayList<BeansReportOralExamination> array = null;
			
			//就職部の先生の場合は全件リストを生成
			if(c_id != 14){
						
				//教員のコースID、学年で該当する報告書を検索
				array = BeansReportOralExamination.searchReportOralExamination(c_id, u_year);
			
			}else{
				
				//報告書を全件検索
				array = BeansReportOralExamination.listReportOralExamination();
				
			}
			
			OralExaminationInfo oei = new OralExaminationInfo();

			oei.authorityListOralExaminationInfo(array);
			request.setAttribute("list", oei.getList());
			request.setAttribute("list2", oei.getList2());
			//カウンタセット
			request.setAttribute("n_count", oei.getN_count());
			request.setAttribute("r_count", oei.getR_count());
			request.setAttribute("a_count", oei.getA_count());
			getServletContext().getRequestDispatcher("/teacher/authority_o_oral_e_list.jsp").forward(request, response);
			return;
		
		}else if(role.equals("student")){
			
			ArrayList<BeansReportOralExamination> array = BeansReportOralExamination.listReportOralExamination();
			ArrayList<OralExaminationInfo> list = OralExaminationInfo.listOralExaminationInfo(array);
			request.setAttribute("list", list);
			getServletContext().getRequestDispatcher("/student/o_oral_e_list.jsp").forward(request, response);
			return;
			
		}else{
			
			//それ以外の場合・ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
			
		}
	}
}
