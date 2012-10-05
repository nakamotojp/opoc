/**
 * controller ユーザID変更
 */
package jp.bemax.se.graduation2011.auth;

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
 * 
 */
public class ChangeUserID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUserID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		//xss対策クラスをインスタンス化
		Xss xss = new Xss();
		//BeansLoginをインスタンス化
		BeansLogin bl = new BeansLogin();
		//セッション情報を取得する
		HttpSession session = request.getSession(true);
		
		
		//セッション情報から権限名を取得する
		String role = (String)session.getAttribute("role");
		
		if(!role.equals("manager")){
			//管理権限がない
			
		}else{
			
			String c_l_id = xss.escape(request.getParameter("user"));
			String l_id = xss.escape(request.getParameter("new_ID"));
				
			bl.setL_id(c_l_id);
				
			if(bl.changeLoginID(l_id) == -1){
				//ID変更成功ページに飛ばす
				response.sendRedirect("");
					
			}else{
				//ID変更失敗ページに飛ばす
				response.sendRedirect("");
					
			}
		}		
	}
}
