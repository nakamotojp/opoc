package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansReportWrittenExamination;
import jp.bemax.se.graduation2011.model.BeansUser;

/**
 * Servlet implementation class WrittenExaminationList
 * 筆記試験報告書・一覧
 */
public class WrittenExaminationList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WrittenExaminationList() {
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
		WrittenExaminationInfo wei = new WrittenExaminationInfo();
		ArrayList<BeansReportWrittenExamination> list1 = new ArrayList<BeansReportWrittenExamination>();
    	
		/**
		 * 学年が送られてきているか判定
		 * 学年の中身が-1でない場合はそれぞれの学年の一覧を表示する
		 */
		if(Integer.parseInt(u_year) != -1){

			//コースID、学年をもとに筆記報告書を出す
			list1= BeansReportWrittenExamination.searchReportWrittenExamination(Integer.parseInt(c_id) , Integer.parseInt(u_year));
			wei.authorityListWrittenExaminationInfo(list1);
			
			request.setAttribute("list", wei.getList());
			request.setAttribute("list2", wei.getList2());
			//カウンタセット
			request.setAttribute("n_count", wei.getN_count());
			request.setAttribute("r_count", wei.getR_count());
			request.setAttribute("a_count", wei.getA_count());
			getServletContext().getRequestDispatcher("/teacher/authority_o_written_e_list.jsp").forward(request, response);
			
		}else{
			
			/**
			 * 学年が選択されていない場合、コースの全学年の一覧を表示する
			 */
			//一覧取得
			ArrayList<BeansReportWrittenExamination> array = BeansReportWrittenExamination.listReportWrittenExamination();
			
			//取得した一覧のiterator作成
			ListIterator<BeansReportWrittenExamination> iterator = array.listIterator();
			
			BeansReportWrittenExamination tmp1 = null;
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
			
			wei.authorityListWrittenExaminationInfo(list1);
			request.setAttribute("list", wei.getList());
			request.setAttribute("list2", wei.getList2());
			//カウンタセット
			request.setAttribute("n_count", wei.getN_count());
			request.setAttribute("r_count", wei.getR_count());
			request.setAttribute("a_count", wei.getA_count());
			
		}

		getServletContext().getRequestDispatcher("/teacher/authority_o_written_e_list.jsp").forward(request, response);
		return;				
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

		//権限チェック
		if(role == null){
			
			//権限がない場合、ログインページへ戻す
	   		response.sendRedirect("./o_user_login.jsp");
    		return;
    		
    	}
		
		//権限を判断してlistを作成
		if(role.equals("teacher")){			
			
			//セッションで教員のIDを取得
			String l_id = (String)session.getAttribute("l_id");
			BeansUser user = BeansUser.detailUser(l_id);
			//コースID、学年を取得
			int c_id = user.getC_id();
			int u_year = user.getU_year();
			
			ArrayList<BeansReportWrittenExamination> array = null;
			
			//就職部の先生の場合は全件リストを生成
			if(c_id != 14){
						
				//教員のコースID、学年で該当する報告書を検索
				array = BeansReportWrittenExamination.searchReportWrittenExamination(c_id, u_year);
			
			}else{
				
				//報告書を全件検索
				array = BeansReportWrittenExamination.listReportWrittenExamination();
				
			}
			
			WrittenExaminationInfo wei = new WrittenExaminationInfo();
			
			wei.authorityListWrittenExaminationInfo(array);
			request.setAttribute("list", wei.getList());
			request.setAttribute("list2", wei.getList2());
			//カウンタセット
			request.setAttribute("n_count", wei.getN_count());
			request.setAttribute("r_count", wei.getR_count());
			request.setAttribute("a_count", wei.getA_count());
			getServletContext().getRequestDispatcher("/teacher/authority_o_written_e_list.jsp").forward(request, response);
			return;
			
		//権限が学生の場合
		}else if(role.equals("student")){
			
			//筆記試験データをとってくる
			ArrayList<BeansReportWrittenExamination> array = BeansReportWrittenExamination.listReportWrittenExamination();
			
			ArrayList<WrittenExaminationInfo> list = WrittenExaminationInfo.listWrittenExaminationInfo(array);
			request.setAttribute("list", list);
			getServletContext().getRequestDispatcher("/student/o_written_e_list.jsp").forward(request, response);
			return;
			
		}else{
			
			//それ以外の場合・ログインページへ戻す
	   		response.sendRedirect("./o_user_login.jsp");
    		return;
    		
		}

	}

}
