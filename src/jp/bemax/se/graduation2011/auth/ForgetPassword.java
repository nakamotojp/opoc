package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansCourse;
import jp.bemax.se.graduation2011.model.BeansLogin;
import jp.bemax.se.graduation2011.model.BeansSubject;
import jp.bemax.se.graduation2011.model.BeansUser;
/**
 * Servlet implementation class ForgetPassword
 */
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// エンコード指定
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//セッション情報を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		//権限check
		if(role != null){		
			if(role.equals("teacher")){
				//報告書登録ページにリダイレクト		
				getServletContext().getRequestDispatcher("/teacher/authority_o_user_pass.jsp").forward(request, response);
				return;
			}
		}
		//それ以外・ログインページに飛ばす
		response.sendRedirect("./o_user_login.jsp");
		return;
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード指定
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//セッション情報を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		String teacher_id = (String)session.getAttribute("l_id");
		
		//権限check
		if(role == null){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
		
		String l_id = request.getParameter("l_id");
		//権限を判定
		if(role.equals("teacher")){
			
			if(BeansLogin.checkUser(l_id)){
				
				//ランダムな文字列を生成
				String random = escape(UUID.randomUUID().toString());
				String r_str = random.substring(0, 9);
				
				BeansLogin bl = new BeansLogin();
				bl.setL_id(l_id);
				bl.setL_pass(r_str);
				bl.createPassword();
				
				//教師情報を取得
				BeansUser bu = BeansUser.detailUser(teacher_id);
				String teacher_name = bu.getU_name();
				BeansCourse bc = BeansCourse.detailCourse(bu.getC_id());
				BeansSubject bs = BeansSubject.detailSubject(bc.getS_id());
				String s_name = bs.getS_name();
	
				//メール送信
				GMail mail = new GMail();
				mail.setMailTo(request.getParameter("l_id") + "@bemax.jp");
				mail.setMailSubject("opoc Change Password");
				mail.setMailBody(r_str + "があなたの新しいパスワードです。" + "\n"
						+ "下記のログインページから新しいパスワードでログインして下さい。"
						+ "\n" + "\n"
						+ "******************************************************" + "\n"
						+ "専門学校ビーマックス" + "\n"
						+ s_name + "担当" + "   " + teacher_name + "\n"
						+ "opocログインページ：" + "https://opoc.bemax.jp/" + "\n"
						+ "******************************************************");
				mail.run();
				
				if(mail.getResult() != -1){
					//パスワード変更成功
					request.setAttribute("flg", "true");
					getServletContext().getRequestDispatcher("/teacher/authority_o_user_pass.jsp").forward(request, response);
				}else{
					//パスワード変更失敗
					request.setAttribute("flg", "false2");
					getServletContext().getRequestDispatcher("/teacher/authority_o_user_pass.jsp").forward(request, response);
				}
			}else{
				//IDが違う場合の処理
				request.setAttribute("flg", "false");
				getServletContext().getRequestDispatcher("/teacher/authority_o_user_pass.jsp").forward(request, response);
			}
		}
	}

	/**
	 * 文字列から"-"を抜き取ります
	 * @param value 文字列
	 * @return result 文字列(-抜き)
	 */
	public String escape(String value){
		
		StringBuffer result = new StringBuffer();
		//取り出した値を一文字ずつ変換"
		if(value != null){
			for(int j = 0 ; j < value.length() ; j++){
					
				switch(value.charAt(j)){
						
				case '-' :
					break;
					
				//変換しなかった場合は、元の文字を格納
				default :
					result.append(value.charAt(j));
					break;	
					
				}
			}
			return result.toString();
		}else{
			return null;
		}
	}
}