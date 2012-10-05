/**
 * MVC Model Beansパッケージ
 */
package jp.bemax.se.graduation2011.model;

/**
 * Import
 */
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * ユーザに関するデータベース処理を行うクラス
 * @author Takenaka
 * @see java.io.Serializable
 * @see java.sql.Connection
 * @see java.sql.PreparedStatement
 * @see java.sql.SQLException
 * @see java.util.ArrayList
 * @see javax.naming.Context
 * @see javax.naming.InitialContext
 * @see javax.naming.NamingException
 * @see javax.sql.DataSource
 */
public class BeansUser implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Property
	 */
	/**
	 *  ログインID
	 */
	private String l_id = null;
	
	/**
	 *  氏名
	 */
	private String u_name = null;
	
	/**
	 *  学年
	 */
	private int u_year = -1;
	
	/**
	 *  メールアドレス
	 */
	private String u_mail = null;
	
	/**
	 *  コース
	 */
	private int c_id = -1;
	
	
	/*
	 * Constructor
	 */
	/**
	 * Default Constructor
	 */
	public BeansUser(){
		super();
	}
	
	
	/*
	 * method
	 */
	/**
	 * ログインユーザを追加します。<br>
	 * 作成には、プロパティl_id, u_name, u_year, u_mail, c_idを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int createUser(){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.u_name, true)){
			return result;
		}
		
		if(!DBCheck.number(this.u_year, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.u_mail, true)){
			return result;
		}
		
		if(!DBCheck.number(this.c_id, true)){
			return result;
		}

		// コネクションを保持
		Connection db = null;
		
		// プリコンパイルされたSQL文を保持
		PreparedStatement ps = null;
		
		try{
			// コンテキストの構成
			Context ct = new InitialContext();
			
			// 名前付きオブジェクト取得
			DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/Graduation");
			
			// データベースと接続
			db = ds.getConnection();
			
			// SQL文の生成
			ps = db.prepareStatement(
					"INSERT INTO user(" +
					"l_id, u_name, u_year, u_mail, c_id" +
					") VALUES(" +
					"?, ?, ?, ?, ?" +
					")"
			);
			
			// 値のセット
			ps.setString(1, this.l_id);
			ps.setString(2, this.u_name);
			ps.setInt(3, this.u_year);
			ps.setString(4, this.u_mail);
			ps.setInt(5, this.c_id);
			
			// クエリの実行
			result = ps.executeUpdate();
			
			// psオブジェクトの解放
			ps.close();
			
			// dbオブジェクトの解放
			db.close();
		} catch (NamingException e) {
			// 標準エラーストリームへ出力
			e.printStackTrace();
		} catch (SQLException e) {
			// 標準エラーストリームへ出力
			e.printStackTrace();
		}finally{
			try{
				// psオブジェクトの解放確認
				if(ps != null){
					// psオブジェクトの解放
					ps.close();
				}
				
				// dbオブジェクトの解放確認
				if(db != null){
					// dbオブジェクトの解放
					db.close();
				}
			}catch(SQLException e){
				// 標準エラーストリームへ出力
				e.printStackTrace();
			}
		}
		
		return result;
	}
	

	/**
	 * ユーザ情報を変更します。<br>
	 * 変更には、プロパティl_id, u_name, u_year, u_mail, c_idを使用します。<br>
	 * プロパティl_id, u_name, u_year, u_mail, c_idに
	 * 変更しない情報は現在の情報をセットして呼び出してください。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changeUser(){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.l_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.u_name, true)){
			return result;
		}
		
		if(!DBCheck.number(this.u_year, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.u_mail, true)){
			return result;
		}
		
		if(!DBCheck.number(this.c_id, true)){
			return result;
		}

		// コネクションを保持
		Connection db = null;
		
		// プリコンパイルされたSQL文を保持
		PreparedStatement ps = null;
		
		try{
			// コンテキストの構成
			Context ct = new InitialContext();
			
			// 名前付きオブジェクト取得
			DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/Graduation");
			
			// データベースと接続
			db = ds.getConnection();
			
			// SQL文の生成
			ps = db.prepareStatement(
					"UPDATE user " +
					"SET u_name = ?, u_year = ?, u_mail = ?, c_id = ? " +
					"WHERE " +
					"l_id = ?"
			);
			
			// 値のセット
			ps.setString(1, this.u_name);
			ps.setInt(2, this.u_year);
			ps.setString(3, this.u_mail);
			ps.setInt(4, this.c_id);
			ps.setString(5, this.l_id);
			
			// クエリの実行
			result = ps.executeUpdate();
			
			// psオブジェクトの解放
			ps.close();
			
			// dbオブジェクトの解放
			db.close();
		} catch (NamingException e) {
			// 標準エラーストリームへ出力
			e.printStackTrace();
		} catch (SQLException e) {
			// 標準エラーストリームへ出力
			e.printStackTrace();
		}finally{
			try{
				// psオブジェクトの解放確認
				if(ps != null){
					// psオブジェクトの解放
					ps.close();
				}
				
				// dbオブジェクトの解放確認
				if(db != null){
					// dbオブジェクトの解放
					db.close();
				}
			}catch(SQLException e){
				// 標準エラーストリームへ出力
				e.printStackTrace();
			}
		}
		
		return result;
	}
	

	/**
	 * ユーザ情報を削除します。<br>
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int deleteUser(String l_id){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(l_id, true)){
			return result;
		}

		// コネクションを保持
		Connection db = null;
		
		// プリコンパイルされたSQL文を保持
		PreparedStatement ps = null;
		
		try{
			// コンテキストの構成
			Context ct = new InitialContext();
			
			// 名前付きオブジェクト取得
			DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/Graduation");
			
			// データベースと接続
			db = ds.getConnection();
			
			// SQL文の生成
			ps = db.prepareStatement(
					"DELETE from user " +
					"WHERE " +
					"l_id = ?"
			);
			
			// 値のセット
			ps.setString(1, l_id);
			
			// クエリの実行
			result = ps.executeUpdate();
			
			// psオブジェクトの解放
			ps.close();
			
			// dbオブジェクトの解放
			db.close();
		} catch (NamingException e) {
			// 標準エラーストリームへ出力
			e.printStackTrace();
		} catch (SQLException e) {
			// 標準エラーストリームへ出力
			e.printStackTrace();
		}finally{
			try{
				// psオブジェクトの解放確認
				if(ps != null){
					// psオブジェクトの解放
					ps.close();
				}
				
				// dbオブジェクトの解放確認
				if(db != null){
					// dbオブジェクトの解放
					db.close();
				}
			}catch(SQLException e){
				// 標準エラーストリームへ出力
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	/**
	 * 検索に一致するユーザの一覧を返します。<br>
	 * 完全一致で検索します。部分一致は「%」をユーザ名の前後に含めて呼び出してください。
	 * @param u_name 検索するユーザ名
	 * @return BeansUserのArrayListが戻ります。
	 */
	public static ArrayList<BeansUser> searchUser(String u_name){
		// SQLクエリ結果を保持
		ArrayList<BeansUser> result = new ArrayList<BeansUser>();
		
		// 入力チェック
		if(!DBCheck.vchar(u_name, true)){
			return result;
		}

		// コネクションを保持
		Connection db = null;
		
		// プリコンパイルされたSQL文を保持
		PreparedStatement ps = null;
		
		// 結果セットを保持
		ResultSet rs = null;
		
		try{
			// コンテキストの構成
			Context ct = new InitialContext();
			
			// 名前付きオブジェクト取得
			DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/Graduation");
			
			// データベースと接続
			db = ds.getConnection();
			
			// SQL文の生成
			ps = db.prepareStatement(
					"SELECT * FROM user " +
					"WHERE u_name like ? " +
					"ORDER BY l_id ASC"
			);
			
			// 値のセット
			ps.setString(1, u_name);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansUser tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansUser();
				
				// 代入
				tmp.setL_id(rs.getString("l_id"));
				tmp.setU_name(rs.getString("u_name"));
				tmp.setU_year(rs.getInt("u_year"));
				tmp.setU_mail(rs.getString("u_mail"));
				tmp.setC_id(rs.getInt("c_id"));
				
				// データをリストに追加
				result.add(tmp);
			}
			
			// psオブジェクトの解放
			ps.close();
			
			// dbオブジェクトの解放
			db.close();
		} catch (NamingException e) {
			// 標準エラーストリームへ出力
			e.printStackTrace();
		} catch (SQLException e) {
			// 標準エラーストリームへ出力
			e.printStackTrace();
		}finally{
			try{
				// psオブジェクトの解放確認
				if(ps != null){
					// psオブジェクトの解放
					ps.close();
				}
				
				// dbオブジェクトの解放確認
				if(db != null){
					// dbオブジェクトの解放
					db.close();
				}
			}catch(SQLException e){
				// 標準エラーストリームへ出力
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	/**
	 * 検索に一致するユーザの一覧を返します。<br>
	 * 完全一致で検索します。部分一致は「%」をユーザ名の前後に含めて呼び出してください。
	 * @param c_id ユーザを検索するコースID
	 * @param u_year ユーザを検索する学年
	 * @return BeansUserのArrayListが戻ります。
	 */
	public static ArrayList<BeansUser> searchUser(int c_id, int u_year){
		// SQLクエリ結果を保持
		ArrayList<BeansUser> result = new ArrayList<BeansUser>();
		
		// 入力チェック
		if(!DBCheck.number(c_id, true)){
			return result;
		}
		
		if(!DBCheck.number(u_year, true)){
			return result;
		}
		
		// コネクションを保持
		Connection db = null;
		
		// プリコンパイルされたSQL文を保持
		PreparedStatement ps = null;
		
		// 結果セットを保持
		ResultSet rs = null;
		
		try{
			// コンテキストの構成
			Context ct = new InitialContext();
			
			// 名前付きオブジェクト取得
			DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/Graduation");
			
			// データベースと接続
			db = ds.getConnection();
			
			// SQL文の生成
			ps = db.prepareStatement(
					"SELECT * FROM user " +
					"WHERE c_id = ? and u_year = ? " +
					"ORDER BY l_id ASC"
			);
			
			// 値のセット
			ps.setInt(1, c_id);
			ps.setInt(2, u_year);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansUser tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansUser();
				
				// 代入
				tmp.setL_id(rs.getString("l_id"));
				tmp.setU_name(rs.getString("u_name"));
				tmp.setU_year(rs.getInt("u_year"));
				tmp.setU_mail(rs.getString("u_mail"));
				tmp.setC_id(rs.getInt("c_id"));
				
				// データをリストに追加
				result.add(tmp);
			}
			
			// psオブジェクトの解放
			ps.close();
			
			// dbオブジェクトの解放
			db.close();
		} catch (NamingException e) {
			// 標準エラーストリームへ出力
			e.printStackTrace();
		} catch (SQLException e) {
			// 標準エラーストリームへ出力
			e.printStackTrace();
		}finally{
			try{
				// psオブジェクトの解放確認
				if(ps != null){
					// psオブジェクトの解放
					ps.close();
				}
				
				// dbオブジェクトの解放確認
				if(db != null){
					// dbオブジェクトの解放
					db.close();
				}
			}catch(SQLException e){
				// 標準エラーストリームへ出力
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	/**
	 * 指定されたユーザIDの情報を返します。
	 * @return BeansUserが戻ります。
	 */
	public static BeansUser detailUser(String l_id){
		// SQLクエリ結果を保持
		BeansUser result = new BeansUser();
		
		// 入力チェック
		if(!DBCheck.vchar(l_id, true)){
			return result;
		}

		// コネクションを保持
		Connection db = null;
		
		// プリコンパイルされたSQL文を保持
		PreparedStatement ps = null;
		
		// 結果セットを保持
		ResultSet rs = null;
		
		try{
			// コンテキストの構成
			Context ct = new InitialContext();
			
			// 名前付きオブジェクト取得
			DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/Graduation");
			
			// データベースと接続
			db = ds.getConnection();
			
			// SQL文の生成
			ps = db.prepareStatement(
					"SELECT * FROM user " +
					"WHERE l_id = ?"
			);
			
			// 値のセット
			ps.setString(1, l_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// データの読み取り
			while(rs.next()){				
				// 代入
				result.setL_id(rs.getString("l_id"));
				result.setU_name(rs.getString("u_name"));
				result.setU_year(rs.getInt("u_year"));
				result.setU_mail(rs.getString("u_mail"));
				result.setC_id(rs.getInt("c_id"));
			}
			
			// psオブジェクトの解放
			ps.close();
			
			// dbオブジェクトの解放
			db.close();
		} catch (NamingException e) {
			// 標準エラーストリームへ出力
			e.printStackTrace();
		} catch (SQLException e) {
			// 標準エラーストリームへ出力
			e.printStackTrace();
		}finally{
			try{
				// psオブジェクトの解放確認
				if(ps != null){
					// psオブジェクトの解放
					ps.close();
				}
				
				// dbオブジェクトの解放確認
				if(db != null){
					// dbオブジェクトの解放
					db.close();
				}
			}catch(SQLException e){
				// 標準エラーストリームへ出力
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	/*
	 * Accessor
	 */
	/**
	 * @return l_id
	 */
	public String getL_id() {
		return l_id;
	}


	/**
	 * @param l_id セットする l_id
	 */
	public void setL_id(String l_id) {
		this.l_id = l_id;
	}


	/**
	 * @return u_name
	 */
	public String getU_name() {
		return u_name;
	}


	/**
	 * @param u_name セットする u_name
	 */
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}


	/**
	 * @return u_year
	 */
	public int getU_year() {
		return u_year;
	}


	/**
	 * @param u_year セットする u_year
	 */
	public void setU_year(int u_year) {
		this.u_year = u_year;
	}


	/**
	 * @return u_mail
	 */
	public String getU_mail() {
		return u_mail;
	}


	/**
	 * @param u_mail セットする u_mail
	 */
	public void setU_mail(String u_mail) {
		this.u_mail = u_mail;
	}


	/**
	 * @return c_id
	 */
	public int getC_id() {
		return c_id;
	}


	/**
	 * @param c_id セットする c_id
	 */
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
}