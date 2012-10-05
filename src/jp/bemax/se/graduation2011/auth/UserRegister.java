/**
 * ユーザ登録<BR>
 * @author 信原美希
 */
package jp.bemax.se.graduation2011.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.bemax.se.graduation2011.model.BeansLogin;
import jp.bemax.se.graduation2011.model.BeansUser;


/**
 * Servlet implementation class UserRegister
 */
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
           /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//リダイレクト
		//ユーザ登録・確認画面
		response.sendRedirect("./o_newly_st_registration.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード・コンテンツタイプ指定
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		
		//Beansのインスタンス化
		BeansLogin bl = new BeansLogin();
		BeansUser bu = new BeansUser();
		//xss対策のクラスをインスタンス化
		Xss xss = new Xss();
		
		//リクエスト情報を取得する
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		String l_id = xss.escape(request.getParameter("l_id"));
		
		//既にDBに登録されているか判定する
		BeansUser user = BeansUser.detailUser(l_id);
		if(user.getL_id() != null){
			
			//リダイレクト・登録失敗ページ
			response.sendRedirect("./registration_failed.jsp");
			return;
			
		}
		
		//氏名・コースID・ログインID・パスワード・生徒用の権限ＩＤをセットする
		String u_name = xss.escape(request.getParameter("u_name"));
		bl.setL_id(l_id);
		bl.setL_pass((String)(session.getAttribute("l_pass")));
		bl.setR_id(2);
		
		//ログインユーザ登録
		bl.createLogin();
			
		//ログインID、氏名、学年、メールアドレス、コースIDをセット
		bu.setL_id(bl.getL_id());
		bu.setU_name(u_name);
		bu.setU_year(Integer.parseInt(request.getParameter("u_year"))); 
		bu.setU_mail(xss.escape(bu.getL_id()) + "@bemax.jp");
		bu.setC_id(c_id);

		//データベースへユーザ情報追加・登録できたかチェック	
		if(bu.createUser() != -1){
			
			//リダイレクト・登録成功ページ
			response.sendRedirect("./registration_success.jsp");
			return;
			
		}else{
			
			//リダイレクト・登録失敗ページ
			response.sendRedirect("./registration_failed.jsp");
			return;
			
		}
	}
}
