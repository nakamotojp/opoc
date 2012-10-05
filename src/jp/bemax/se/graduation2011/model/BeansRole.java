/**
 * MVC Model Beansパッケージ
 */
package jp.bemax.se.graduation2011.model;

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
 * 権限に関するデータベース処理を行うクラス
 * @author Takenaka
 * @see java.io.Serializable
 * @see java.sql.Connection
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 * @see java.sql.SQLException
 * @see javax.naming.Context
 * @see javax.naming.InitialContext
 * @see javax.naming.NamingException
 * @see javax.sql.DataSource
 */
public class BeansRole implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Property
	 */
	/**
	 *  権限ID
	 */
	private int r_id = -1;
	
	/**
	 *  権限名
	 */
	private String r_name = null;
	
	
	/*
	 * Constructor
	 */
	/**
	 * Default Constructor
	 */
	public BeansRole() {
		super();
	}
	

	/*
	 * method
	 */
	/**
	 * 権限を追加します。<br>
	 * 作成には、プロパティr_nameを使用します。<br>
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int createRole(){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.r_name, true)){
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
					"SELECT r_name FROM role " +
					"WHERE " +
					"r_name like ?"
			);
			
			// 値のセット
			ps.setString(1, this.r_name);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 権限の重複チェック
			if(!(rs.next())){
				// SQL文の生成
				ps = db.prepareStatement(
						"INSERT INTO role(" +
						"r_name" +
						") VALUES(" +
						"?" +
						")"
				);
				
				// 値のセット
				ps.setString(1, this.r_name);
				
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
	 * 権限名を変更します。<br>
	 * 作成には、プロパティr_id, r_nameを使用します。<br>
	 * プロパティr_id, r_nameに変更したい権限IDと変更後の権限名をセットして呼び出してください。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changeRole(){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(this.r_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.r_name, true)){
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
					"SELECT r_name FROM role " +
					"WHERE " +
					"r_name like ?"
			);
			
			// 値のセット
			ps.setString(1, this.r_name);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 権限の重複チェック
			if(!(rs.next())){
				// SQL文の生成
				ps = db.prepareStatement(
						"UPDATE role " +
						"SET r_name = ? " +
						"WHERE " +
						"r_id = ?"
				);
				
				// 値のセット
				ps.setString(1, this.r_name);
				ps.setInt(2, this.r_id);
				
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
	 * 権限を削除します。<br>
	 * 引数r_idに削除する権限IDをセットして呼び出してください。
	 * @param r_id 削除する権限ID
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int deleteRole(int r_id){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(r_id, true)){
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
					"DELETE from role " +
					"WHERE " +
					"r_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, r_id);
			
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
	 * 権限一覧を返します。
	 * @return BeansRoleのArrayListが戻ります。
	 */
	public static ArrayList<BeansRole> listRole(){
		// SQLクエリ結果を保持
		ArrayList<BeansRole> result = new ArrayList<BeansRole>();

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
					"SELECT * FROM role " +
					"ORDER BY r_id ASC"
			);
			
			// 値のセット
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansRole tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansRole();
				
				// 代入
				tmp.setR_id(rs.getInt("r_id"));
				tmp.setR_name(rs.getString("r_name"));
				
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
	 * 検索に一致する権限の一覧を返します。<br>
	 * 完全一致で検索します。部分一致は「%」を権限名の前後に含めて呼び出してください。
	 * @param r_name 検索する権限名
	 * @return BeansRoleのArrayListが戻ります。
	 */
	public static ArrayList<BeansRole> searchRole(String r_name){
		// SQLクエリ結果を保持
		ArrayList<BeansRole> result = new ArrayList<BeansRole>();
		
		// 入力チェック
		if(!DBCheck.vchar(r_name, true)){
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
					"SELECT * FROM role " +
					"WHERE r_name like ? " +
					"ORDER BY r_id ASC"
			);
			
			// 値のセット
			ps.setString(1, r_name);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansRole tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansRole();
				
				// 代入
				tmp.setR_id(rs.getInt("r_id"));
				tmp.setR_name(rs.getString("r_name"));
				
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
	 * 指定された権限IDの情報を返します。
	 * @return BeansRoleが戻ります。
	 */
	public static BeansRole detailRole(int r_id){
		// SQLクエリ結果を保持
		BeansRole result = new BeansRole();
		
		// 入力チェック
		if(!DBCheck.number(r_id, true)){
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
					"SELECT * FROM role " +
					"WHERE r_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, r_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// データの読み取り
			while(rs.next()){				
				// 代入
				result.setR_id(rs.getInt("r_id"));
				result.setR_name(rs.getString("r_name"));
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


	/**
	 * @return r_name
	 */
	public String getR_name() {
		return r_name;
	}

	/**
	 * @param r_name セットする r_name
	 */
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
}
