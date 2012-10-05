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
 * 学科に関するデータベース処理を行うクラス
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
public class BeansSubject implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Property
	 */
	/**
	 *  学科ID
	 */
	private int s_id = -1;
	
	/**
	 *  学科名
	 */
	private String s_name = null;
	
	
	/*
	 * Constructor
	 */
	/**
	 * Default Constructor
	 */
	public BeansSubject() {
		super();
	}
	

	/*
	 * method
	 */
	/**
	 * 学科を追加します。<br>
	 * 作成には、プロパティs_nameを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int createSubject(){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.s_name, true)){
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
					"SELECT s_name FROM subject " +
					"WHERE " +
					"s_name like ?"
			);
			
			// 値のセット
			ps.setString(1, this.s_name);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 学科の重複チェック
			if(!(rs.next())){
				// SQL文の生成
				ps = db.prepareStatement(
						"INSERT INTO subject(" +
						"s_name" +
						") VALUES(" +
						"?" +
						")"
				);
				
				// 値のセット
				ps.setString(1, this.s_name);
				
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
	 * 学科名を変更します。<br>
	 * 作成には、プロパティs_id, s_nameを使用します。<br>
	 * プロパティs_id, s_nameに変更する学科の学科IDと変更後の学科名をセットして呼び出してください。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changeSubject(){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(this.s_id, true)){
			return result;
		}

		if(!DBCheck.vchar(this.s_name, true)){
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
					"SELECT s_name FROM subject " +
					"WHERE " +
					"s_name like ?"
			);
			
			// 値のセット
			ps.setString(1, this.s_name);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 学科の重複チェック
			if(!(rs.next())){
				// SQL文の生成
				ps = db.prepareStatement(
						"UPDATE subject " +
						"SET s_name = ? " +
						"WHERE " +
						"s_id = ?"
				);
				
				// 値のセット
				ps.setString(1, this.s_name);
				ps.setInt(2, this.s_id);
				
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
	 * 学科を削除します。
	 * @param s_id 削除する学科ID
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int deleteSubject(int s_id){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(s_id, true)){
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
					"DELETE from subject " +
					"WHERE " +
					"s_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, s_id);
			
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
	 * 学科一覧を返します。
	 * @return 成功すれば、BeansSubjectのArrayListが戻ります。
	 */
	public static ArrayList<BeansSubject> listSubject(){
		// SQLクエリ結果を保持
		ArrayList<BeansSubject> result = new ArrayList<BeansSubject>();

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
					"SELECT * FROM subject " +
					"ORDER BY s_id ASC"
			);
			
			// 値のセット
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansSubject tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansSubject();
				
				// 代入
				tmp.setS_id(rs.getInt("s_id"));
				tmp.setS_name(rs.getString("s_name"));
				
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
	 * 検索に一致する学科の一覧を返します。<br>
	 * 完全一致で検索します。部分一致は「%」を学科名の前後に含めて呼び出してください。
	 * @param s_name 検索する学科名
	 * @return BeansSubjectのArrayListが戻ります。
	 */
	public static ArrayList<BeansSubject> searchSubject(String s_name){
		// SQLクエリ結果を保持
		ArrayList<BeansSubject> result = new ArrayList<BeansSubject>();
		
		// 入力チェック
		if(!DBCheck.vchar(s_name, true)){
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
					"SELECT * FROM subject " +
					"WHERE s_name like ? " +
					"ORDER BY s_id ASC"
			);
			
			// 値のセット
			ps.setString(1, s_name);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansSubject tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansSubject();
				
				// 代入
				tmp.setS_id(rs.getInt("s_id"));
				tmp.setS_name(rs.getString("s_name"));
				
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
	 * 指定された学科IDの情報を返します。
	 * @return BeansSubjectが戻ります。
	 */
	public static BeansSubject detailSubject(int s_id){
		// SQLクエリ結果を保持
		BeansSubject result = new BeansSubject();
		
		// 入力チェック
		if(!DBCheck.number(s_id, true)){
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
					"SELECT * FROM subject " +
					"WHERE s_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, s_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// データの読み取り
			while(rs.next()){				
				// 代入
				result.setS_id(rs.getInt("s_id"));
				result.setS_name(rs.getString("s_name"));
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
	 * @return s_id
	 */
	public int getS_id() {
		return s_id;
	}

	/**
	 * @param s_id セットする s_id
	 */
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}


	/**
	 * @return s_name
	 */
	public String getS_name() {
		return s_name;
	}

	/**
	 * @param s_name セットする s_name
	 */
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
}
