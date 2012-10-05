/**
 * 認証 パッケージ
 */
package jp.bemax.se.graduation2011.auth;

import jp.bemax.se.graduation2011.model.BeansLogin;
import jp.bemax.se.graduation2011.model.BeansRole;

/**
 * @author kouzuki
 * @see jp.bemax.se.graduation2011.model.BeansLogin;
 * @see jp.bemax.se.graduation2011.model.BeansRole;
 */
public class Auth{
	
	private String role = null;
	private String url = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
    	super();
    }
    
    /**
	 * checkLogin <br>
	 * ユーザが登録されているか検索する<br>
	 * 権限から飛ばすURLを判断する
	 * @param l_id ログインID
	 * @param　l_pass パスワード
	 * @param loginCheck 認証情報
	 * @return boolean ユーザ情報の有無
	 */
	public boolean checkLogin(String l_id , String l_pass , Object loginCheck){	
		
		if(loginCheck == null){
			
			if(BeansLogin.checkLogin(l_id, l_pass)){
			
				int r_id = BeansLogin.checkRole(l_id);
				BeansRole br = BeansRole.detailRole(r_id);
				role = br.getR_name();
			}else{
				return false;
			}
		}
		
		if(role == null){
			
			if(BeansLogin.checkLogin(l_id, l_pass)){
				
				int r_id = BeansLogin.checkRole(l_id);
				BeansRole br = BeansRole.detailRole(r_id);
				role = br.getR_name();
				
				if(role.equals("student")){
					url = "ReportNewList";
				}else{
					url = "AuthorityNewReportList";
				}
			}else{
				url = "o_user_login.jsp";
				return false;
			}
			
		}else{
			if(role.equals("student")){
				url = "ReportNewList";
			}else{
				url = "AuthorityNewReportList";
			}
		}
		return true;
	}

	/**
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role セットする role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url セットする url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
