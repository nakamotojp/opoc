/**
 * MVC Model Beansパッケージ
 */
package jp.bemax.se.graduation2011.model;

/**
 * Import
 */
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * ログインに関するデータベース処理を行うクラス
 * @author Takenaka
 * @see java.io.Serializable
 * @see java.security.MessageDigest
 * @see java.security.NoSuchAlgorithmException
 * @see java.sql.Connection
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 * @see java.sql.SQLException
 * @see javax.naming.Context
 * @see javax.naming.InitialContext
 * @see javax.naming.NamingException
 * @see javax.sql.DataSource
 */
public class BeansLogin implements Serializable {
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
	 *  パスワード
	 */
	private String l_pass = null;
	
	/**
	 *  権限
	 */
	private int r_id = -1;
	
	
	/*
	 * Constructor
	 */
	/**
	 * Default Constructor
	 */
	public BeansLogin(){
		super();
	}
	
	
	/*
	 * method
	 */
	/**
	 * ログインユーザを追加します。<br>
	 * 作成には、プロパティl_id, l_pass, r_idを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int createLogin(){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.l_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.l_pass, true)){
			return result;
		}
		
		if(!DBCheck.number(this.r_id, true)){
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
					"SELECT l_id FROM login " +
					"WHERE " +
					"l_id like ?"
			);
			
			// 値のセット
			ps.setString(1, this.l_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 権限の重複チェック
			if(!(rs.next())){
				// SQL文の生成
				ps = db.prepareStatement(
						"INSERT INTO login(" +
						"l_id, l_pass, r_id" +
						") VALUES(" +
						"?, ?, ?" +
						")"
				);
				
				this.l_pass = hash(this.l_pass);
				
				if(this.l_pass.length() < 1){
					return result;
				}
				
				// 値のセット
				ps.setString(1, this.l_id);
				ps.setString(2, this.l_pass);
				ps.setInt(3, this.r_id);
				
				// クエリの実行
				result = ps.executeUpdate();
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
	 * ユーザの認証チェックを行います。
	 * @param l_id ログインID
	 * @param l_pass ログインパスワード
	 * @return 成功すれば、trueが戻ります。
	 */
	public static boolean checkLogin(String l_id, String l_pass){
		// SQLクエリ結果を保持
		boolean result = false;
		
		// 入力チェック
		if(!DBCheck.vchar(l_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(l_pass, true)){
			return result;
		}
		
		l_pass = hash(l_pass);

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
					"SELECT * FROM login " +
					"WHERE " +
					"l_id = ? and l_pass = ?"
			);
			
			// 値のセット
			ps.setString(1, l_id);
			ps.setString(2, l_pass);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// データの読み取り
			if(rs.next()){
				// データを生成
				result = true;
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
	 * ログインパスワードを変更します。<br>
	 * 変更には、プロパティl_id, l_pass, 引数l_passを使用します。<br>
	 * プロパティl_id, l_passにログインID, 新しいログインパスワードをセットして、引数l_passに現在のログインパスワードをセットして呼び出してください。
	 * @param l_pass 現在のパスワード
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changePassword(String l_pass){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.l_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.l_pass, true)){
			return result;
		}
		
		if(!DBCheck.vchar(l_pass, true)){
			return result;
		}
		
		l_pass = hash(l_pass);

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
					"UPDATE login " +
					"SET l_pass = ? " +
					"WHERE " +
					"l_id = ? and l_pass = ?"
			);
			
			this.l_pass = hash(this.l_pass);
			
			// 値のセット
			ps.setString(1, this.l_pass);
			ps.setString(2, this.l_id);
			ps.setString(3, l_pass);
			
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
	 * ログインパスワードを設定します。<br>
	 * 現在のパスワードを無視して、新しいパスワードを設定します。<br>
	 * 設定には、プロパティl_id, l_passを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int createPassword(){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.l_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.l_pass, true)){
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
					"UPDATE login " +
					"SET l_pass = ? " +
					"WHERE " +
					"l_id = ?"
			);
			
			this.l_pass = hash(this.l_pass);
			
			// 値のセット
			ps.setString(1, this.l_pass);
			ps.setString(2, this.l_id);
			
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
	 * ログインIDを変更します。<br>
	 * 変更には、プロパティl_idと引数l_idを使用します。<br>
	 * プロパティl_idに新しいログインIDをセットして、引数l_idに現在のログインIDをセットして呼び出してください。
	 * @param l_id 現在のログインID
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changeLoginID(String l_id){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.l_id, true)){
			return result;
		}
		
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
					"UPDATE login " +
					"SET l_id = ? " +
					"WHERE " +
					"l_id = ?"
			);
			
			// 値のセット
			ps.setString(1, this.l_id);
			ps.setString(2, l_id);
			
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
	 * ログインIDを削除します。<br>
	 * 削除には、プロパティl_idを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int deleteLogin(String l_id){
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
					"DELETE from login " +
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
	 * ユーザの権限チェックを行います。
	 * @param l_id ログインID
	 * @return 成功すれば、権限ID(-1以外)が戻ります。
	 */
	public static int checkRole(String l_id){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 文字数チェック
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
					"SELECT r_id FROM login " +
					"WHERE " +
					"l_id = ?"
			);
			
			// 値のセット
			ps.setString(1, l_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// データの読み取り
			if(rs.next()){
				// データを生成
				result = rs.getInt("r_id");
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
	 * ユーザの有無チェックを行います。
	 * @param l_id ログインID
	 * @return 存在すればtrueが戻ります。
	 */
	public static boolean checkUser(String l_id){
		// SQLクエリ結果を保持
		boolean result = false;
		
		// 文字数チェック
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
					"SELECT l_id FROM login " +
					"WHERE " +
					"l_id = ?"
			);
			
			// 値のセット
			ps.setString(1, l_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// データの読み取り
			if(rs.next()){
				// データを生成
				result = true;
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
	 * 文字列のハッシュ(SHA-256)化を行います。
	 * @param str ハッシュ化する文字列
	 * @return ハッシュ化された文字列(0文字以外)が戻ります。
	 */
	protected static String hash(String str){
		String hash = new String();
		
		// 入力チェック
		if(!DBCheck.vchar(str, true)){
			return hash;
		}
		
		byte[] data = null;
		int tmp = -1;
		
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			data = str.getBytes();
			
			md.update(data);
			
			data = md.digest();
		}catch (NoSuchAlgorithmException e) {
			System.err.println(e);
		}
		
		for(int i = 0; i < data.length; i++){
			tmp = data[i];
			if(tmp < 0){
				tmp += 256;
			}
			if(tmp < 16){
				hash += "0";
			}
			hash += Integer.toString(tmp, 16);
		}
		
		return hash;
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
	 * @return l_pass
	 */
	public String getL_pass() {
		return l_pass;
	}

	/**
	 * @param l_pass セットする l_pass
	 */
	public void setL_pass(String l_pass) {
		this.l_pass = l_pass;
	}


	/**
	 * @return r_id
	 */
	public int getR_id() {
		return r_id;
	}

	/**
	 * @param r_id セットする r_id
	 */
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
}