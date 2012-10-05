package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansReportJoinMeeting;
import jp.bemax.se.graduation2011.model.BeansReportOralExamination;
import jp.bemax.se.graduation2011.model.BeansReportWrittenExamination;


/**
 * Servlet implementation class ReportMyList
 */
public class ReportMyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportMyList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッション情報を取得・セット
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		session.setAttribute("report", "mix");
		
		//権限チェック
		if(role == null){
			
			//権限がない場合ログインページへ戻す
	   		response.sendRedirect("./o_user_login.jsp");
    		return;
			
		}
		
		//権限check
		if(role.equals("student")){
			
			//各報告書の全一覧取得
			ArrayList<BeansReportJoinMeeting> array1 = BeansReportJoinMeeting.listReportJoinMeeting();
			ArrayList<BeansReportWrittenExamination> array2 = BeansReportWrittenExamination.listReportWrittenExamination();
			ArrayList<BeansReportOralExamination> array3 = BeansReportOralExamination.listReportOralExamination();
			String l_id = (String) session.getAttribute("l_id");
			
			//全一覧、ログインIDを元に自分の一覧を取得
			ArrayList<JoinMeetingInfo> list1 = JoinMeetingInfo.listJoinMeetingMyInfo(array1 , l_id);
			ArrayList<WrittenExaminationInfo> list2 = WrittenExaminationInfo.listWrittenExaminationMyInfo(array2, l_id);
			ArrayList<OralExaminationInfo> list3 = OralExaminationInfo.listOralExaminationMyInfo(array3, l_id);
			
			ReportInfo bi = new ReportInfo();
			bi.listReportInfo(list1 , list2 , list3);
			
			//カウンタ、リストをセット
			request.setAttribute("n_count", bi.getN_count());
			request.setAttribute("r_count", bi.getR_count());
			request.setAttribute("a_count", bi.getA_count());
			request.setAttribute("list", bi.getE_list());
			request.setAttribute("list2", bi.getList());
			
			//個人専用リストページに飛ぶ
			getServletContext().getRequestDispatcher("/student/o_user_myList.jsp").forward(request, response);
			
		}else{
			
			//権限が学生以外ならログインページへリダイレクト
			response.sendRedirect("./o_user_login.jsp");
    		return;
    		
		}
	}
}
