/**
 * コースの追加
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

import jp.bemax.se.graduation2011.model.BeansCourse;
import jp.bemax.se.graduation2011.model.BeansSubject;

/**
 * Servlet implementation class InsertCourse
 */
public class InsertCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
           /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCourse() {
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
		BeansCourse bc = new BeansCourse();
		BeansSubject bs = new BeansSubject();
		//xss対策のクラスをインスタンス化
		Xss xss = new Xss();
		
		//セッション情報を取得
		HttpSession session = request.getSession(true);
		String role = (String)session.getAttribute("role");
		
		/**
		 * ログイン時の権限のセッションとmanager（管理者）が同じかどうか比較<BR>
		 * 等しくない場合はJSPのページへ<BR>
		 * 等しい場合は新しいコース名、何年制かをセット、データベースへ追加する
		 */
		if(!role.equals("manager")){
			
			//管理者権限がない場合
			response.sendRedirect("");
			return;
			
		}else{
			
			//新しいコース名
			String c_name = xss.escape(request.getParameter("c_name"));
			//新しいコースが既に存在しているかどうか検索
			ArrayList<BeansCourse> list = BeansCourse.searchCourse(c_name);
			ListIterator<BeansCourse> iterator = list.listIterator();
			
			//コース一覧の中に当てはまるコース名がない場合、コースを追加する。
			if(!iterator.hasNext()){
				
				// コース名セット
				bc.setC_name(c_name);
				//新しいコース名をセットする
				bc.setC_name(xss.escape(request.getParameter("c_name")));
				//何年制かをセット
				bc.setC_year(Integer.parseInt(request.getParameter("c_year")));
				//学科名
				bs.setS_name(xss.escape(request.getParameter("s_name")));
				bs.getS_id();
				
				//データベースへ追加
				//コース名を追加
				bc.createCourse();
		
				
			}else{
				
				while(iterator.hasNext()){
					
					BeansCourse cc = iterator.next();
					bc.setC_id(cc.getC_id());
					break;
					
				}
			}
		}

		//リダイレクト
		response.sendRedirect("");
		
	}
	
}
