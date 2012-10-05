/**
 * 権限の追加
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

import jp.bemax.se.graduation2011.model.BeansRole;

/**
 * Servlet implementation class InsertRole
 */
public class InsertRole extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertRole() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//リダイレクト
		response.sendRedirect("");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// エンコード指定
		request.setCharacterEncoding("utf-8");

		// コンテンツタイプ指定
		response.setContentType("text/html; charset=utf-8");
		
		
		//Beansのインスタンス化
		BeansRole br = new BeansRole();
		//xss対策のクラスをインスタンス化
		Xss xss = new Xss();
		
		//セッション情報を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		
		/**
		 * ログイン時の権限のセッションとmanager（管理者）が同じかどうか比較<BR>
		 * 等しくない場合はJSPのページへ<BR>
		 * 等しい場合は新しい権限名をセット、データベースへ追加する
		 */
		if(!role.equals("manager")){
			
			//管理者権限がない場合
			response.sendRedirect("");
			return;
			
		}else{
			
			//新しい権限名
			String r_name = xss.escape(request.getParameter("r_name"));
			//新しい権限名が既に存在しているかどうか検索
			ArrayList<BeansRole> list = BeansRole.searchRole(r_name);
			ListIterator<BeansRole> iterator = list.listIterator();
			
			//コース一覧の中に当てはまるコース名がない場合、コースを追加する。
			if(!iterator.hasNext()){
				
				// 権限名追加
				br.setR_name(r_name);
				
				// データベースに追加
				br.createRole();
				
			}else{
				
				while(iterator.hasNext()){
					
					BeansRole rr = iterator.next();
					br.setR_id(rr.getR_id());
					break;
					
				}
			}
				
		}

		//リダイレクト
		response.sendRedirect("");
		
	}
	
}