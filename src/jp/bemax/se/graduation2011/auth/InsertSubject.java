/**
 * 学科の追加
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

import jp.bemax.se.graduation2011.model.BeansSubject;

/**
 * Servlet implementation class InsertSubject
 */
public class InsertSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
           /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertSubject() {
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
		BeansSubject bs = new BeansSubject();
		//xss対策のクラスをインスタンス化
		Xss xss = new Xss();
	
		//セッション情報を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		
		/**
		 * ログイン時の権限のセッションとmanager（管理者）が同じかどうか比較<BR>
		 * 等しくない場合はJSPのページへ<BR>
		 * 等しい場合は新しい学科名をセット、データベースへ追加する
		 */
		if(!role.equals("manager")){
			
			//管理者権限がない場合
			response.sendRedirect("");
			return;
			
		}else{
			
			//新しい学科名
			String s_name = (xss.escape(request.getParameter("s_name")));
			//新しい学科が既に作られていないか検索
			ArrayList<BeansSubject> list = BeansSubject.searchSubject(s_name);
			ListIterator<BeansSubject> iterator = list.listIterator();
			
			//学科名が被っていなければ追加
			if(!iterator.hasNext()){
				
				//新しい学科名をセットする
				bs.setS_name(xss.escape(request.getParameter("s_name")));
				
				//データベースへ追加
				//学科名を追加
				bs.createSubject();
				
				
			}else{
				
				while(iterator.hasNext()){
					
					BeansSubject ss = iterator.next();
					bs.setS_id(ss.getS_id());
					break;
				}
			}
		}
			

		//リダイレクト
		response.sendRedirect("");
		
	}
	
}
