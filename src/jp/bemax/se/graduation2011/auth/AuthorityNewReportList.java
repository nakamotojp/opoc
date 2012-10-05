/**
 * 教員ページのトップ<br>
 * 自分の担当コースの新着３件、登録済みの各報告書３件表示する<br>
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
/**
 * Servlet implementation class AuthorityNewReportList
 */
public class AuthorityNewReportList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorityNewReportList() {
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
		String l_id = (String)session.getAttribute("l_id");
		
		//セッション情報をセット
		session.setAttribute("report", "new");
		
		String u_name = (String)session.getAttribute("u_name");
		if(u_name == null){
			BeansUser bu = BeansUser.detailUser(l_id);
			session.setAttribute("u_name" , bu.getU_name());
		}
		
		//権限チェック
		if(role == null){
			
			//権限がない場合、ログインページへ
			response.sendRedirect("./o_user_login.jsp");
    		return;
			
		}
		
		//権限チェック・教員の場合
		if(role.equals("teacher")){
			
			//IDからユーザ情報を取得
			BeansUser bu = BeansUser.detailUser(l_id);
			//コースID、学年を取得
			int c_id = bu.getC_id();
			int u_year = bu.getU_year();
			
			//初期化
			ArrayList<BeansReportJoinMeeting> array1 = new ArrayList<BeansReportJoinMeeting>();
			ArrayList<BeansReportWrittenExamination> array2 = new ArrayList<BeansReportWrittenExamination>();
			ArrayList<BeansReportOralExamination> array3 = new ArrayList<BeansReportOralExamination>();

			
			//コースIDが就職部でない場合
			if(c_id != 14){
			
				//コースID、学年を元に各報告書のリスト取得
				array1 = BeansReportJoinMeeting.searchReportJoinMeeting(c_id, u_year);
				array2 = BeansReportWrittenExamination.searchReportWrittenExamination(c_id, u_year);
				array3 = BeansReportOralExamination.searchReportOralExamination(c_id, u_year);
				
				
			}else{
				
				//就職部である場合は各報告書の全リスト取得
				array1 = BeansReportJoinMeeting.listReportJoinMeeting();
				array2 = BeansReportWrittenExamination.listReportWrittenExamination();
				array3 = BeansReportOralExamination.listReportOralExamination();
				
			}
			
			//状態がendかつ新着３件の報告書を取得
			ArrayList<JoinMeetingInfo> list1 = JoinMeetingInfo.newListJoinMeetingInfo(array1);
			ArrayList<WrittenExaminationInfo> list2 = WrittenExaminationInfo.newListWrittenExaminationInfo(array2);
			ArrayList<OralExaminationInfo> list3 = OralExaminationInfo.newListOralExaminationInfo(array3);
			
			request.setAttribute("list1", list1);
			request.setAttribute("list2", list2);
			request.setAttribute("list3", list3);
			
			ReportInfo bi = new ReportInfo();
			ReportNewInfo bni = new ReportNewInfo();
			//３つの報告書を一つにする
			bni.authorityNewListReportNewInfo(array1, array2, array3);
			
			bi.authorityListReportInfo(array1, array2, array3);

			//リストをセット
			request.setAttribute("list4", bni.getList());
			//カウンタセット
			request.setAttribute("n_count", bi.getN_count());
			request.setAttribute("r_count", bi.getR_count());
			request.setAttribute("a_count", bi.getA_count());
			
			//教員用トップページに飛ぶ
			getServletContext().getRequestDispatcher("/teacher/authority_o_jub_hunting_top.jsp").forward(request, response);
			return;
			
		}else{
			
			//それ以外・ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
			
		}
		
	}
	
}