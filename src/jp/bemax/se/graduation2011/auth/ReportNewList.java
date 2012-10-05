/**
 * 各報告書・新着３件を表示
 * @author 信原美希
 */
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
import jp.bemax.se.graduation2011.model.BeansUser;

public class ReportNewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
   /**
    * @see HttpServlet#HttpServlet()
    */
   public ReportNewList() {
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
		String l_id = (String)session.getAttribute("l_id");
		session.setAttribute("report", "new");
		
		String u_name = (String)session.getAttribute("u_name");
		if(u_name == null){
			BeansUser bu = BeansUser.detailUser(l_id);
			session.setAttribute("u_name" , bu.getU_name());
		}
		
		//権限チェック
		if(role == null){
			
			//権限がない場合、ログインページへ戻す
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
		
		
		//権限check・学生の場合
		if(role.equals("student")){
			
			//一覧を取得
			ArrayList<BeansReportJoinMeeting> array1 = BeansReportJoinMeeting.listReportJoinMeeting();
			ArrayList<BeansReportWrittenExamination> array2 = BeansReportWrittenExamination.listReportWrittenExamination();
			ArrayList<BeansReportOralExamination> array3 = BeansReportOralExamination.listReportOralExamination();
					
			//状態がendかつ新着３件の報告書を取得
			ArrayList<JoinMeetingInfo> list1 = JoinMeetingInfo.newListJoinMeetingInfo(array1);
			ArrayList<WrittenExaminationInfo> list2 = WrittenExaminationInfo.newListWrittenExaminationInfo(array2);
			ArrayList<OralExaminationInfo> list3 = OralExaminationInfo.newListOralExaminationInfo(array3);
			
			//新着３件の各種リストをセット
			request.setAttribute("list1", list1);
			request.setAttribute("list2", list2);
			request.setAttribute("list3", list3);
			
			//自分の各報告書の一覧を取得
			ArrayList<JoinMeetingInfo> mylist1 = JoinMeetingInfo.listJoinMeetingMyInfo(array1, l_id);
			ArrayList<WrittenExaminationInfo> mylist2 = WrittenExaminationInfo.listWrittenExaminationMyInfo(array2, l_id);
			ArrayList<OralExaminationInfo> mylist3 = OralExaminationInfo.listOralExaminationMyInfo(array3, l_id);

			ReportNewInfo bni = new ReportNewInfo();
			ReportInfo ri = new ReportInfo();

			bni.newListReportNewInfo(mylist1 , mylist2 , mylist3);
			ri.listReportInfo(mylist1, mylist2, mylist3);
			
			//まとめたリスト、カウンタをセット
			request.setAttribute("list4", bni.getList());
			request.setAttribute("n_count", ri.getN_count());
			request.setAttribute("r_count", ri.getR_count());
			request.setAttribute("a_count", ri.getA_count());

			//トップに戻る
			getServletContext().getRequestDispatcher("/student/o_jub_hunting_top.jsp").forward(request, response);
			return;
			
		}else{
			
			//それ以外の場合・ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
		
	}
   	
	
}
