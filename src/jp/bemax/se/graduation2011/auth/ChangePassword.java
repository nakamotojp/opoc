 /**
  * controller パスワード変更クラス
  */
package jp.bemax.se.graduation2011.auth;

/**
 * Import
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansLogin;

/**
 * @author kouzuki
 * @see java.io.IOException;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServlet;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @see jp.bemax.se.graduation2011.model.BeansLogin;
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//セッション情報を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		
		//権限がない場合
		if(role == null){
			
			//ログインページへ
			response.sendRedirect("./o_user_login.jsp");
			return;
		}
		
		//権限が学生
		if(role.equals("student")){
			
			// パスワードページへ
			getServletContext().getRequestDispatcher("/student/o_user_pass.jsp").forward(request, response);
			return;
			
		//それ以外
		}else{
			
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード指定
		response.setContentType("text/html; charset=utf-8");
		// コンテンツタイプ指定
		response.setCharacterEncoding("utf-8");
		//セッション情報を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		
		//権限check
		if(role == null || !role.equals("student")){
			//ログインページに飛ばす
			response.sendRedirect("./o_user_login.jsp");
			return;
		}

		//xss対策クラスをインスタンス化
//		Xss xss = new Xss();
		//BeansLoginをインスタンス化
		BeansLogin bl = new BeansLogin();
		
		//パラメータを取得
		String l_id = (String)session.getAttribute("l_id");
		String old_pass = request.getParameter("old_pass");
		String new_pass = request.getParameter("l_pass");
		
		//パスワードがDBと一致しているか判定
		if(!BeansLogin.checkLogin(l_id, old_pass)){
			
			request.setAttribute("change_password", "fail");
			// パスワードページへ
			getServletContext().getRequestDispatcher("/student/o_user_pass.jsp").forward(request, response);
			return;
			
		}
		
		//ログインID,新しいパスワードをセット
		bl.setL_id(l_id);
		bl.setL_pass(new_pass);
		
		//旧パスを基に変更
		if(bl.changePassword(old_pass) == 1){
			// 成功すればuser index へ
			response.sendRedirect("ReportNewList");
			return;
		}else{
			// 失敗すればログアウト
			response.sendRedirect("Logout");
			return;
		}
	}
}