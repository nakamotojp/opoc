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
 * コースに関するデータベース処理を行うクラス
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
public class BeansCourse implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Property
	 */
	/**
	 *  コースID
	 */
	private int c_id = -1;
	
	/**
	 *  コース名
	 */
	private String c_name = null;
	
	/**
	 *  コース学年
	 */
	private int c_year = -1;
	
	/**
	 *  学科ID
	 */
	private int s_id = -1;
	
	
	/*
	 * Constructor
	 */
	/**
	 * Default Constructor
	 */
	public BeansCourse() {
		super();
	}
	

	/*
	 * method
	 */
	/**
	 * コースを追加します。<br>
	 * 作成には、プロパティc_name, c_year, s_idを使用します。
	 * プロパティc_name, c_year, s_idに追加するコースID, 学年, 学科IDをセットして呼び出してください。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int createCourse(){
		// SQLクエリ結果を保持
		int result = -1;
		
		if(!DBCheck.vchar(this.c_name, true)){
			return result;
		}
		
		if(!DBCheck.number(this.c_year, true)){
			return result;
		}
		
		if(!DBCheck.number(this.s_id, true)){
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
					"SELECT c_name FROM course " +
					"WHERE " +
					"c_name like ? and s_id = ?"
			);
			
			// 値のセット
			ps.setString(1, this.c_name);
			ps.setInt(2, this.s_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// コースの重複チェック
			if(!(rs.next())){
				// SQL文の生成
				ps = db.prepareStatement(
						"INSERT INTO course(" +
						"c_name, c_year, s_id" +
						") VALUES(" +
						"?, ?, ?" +
						")"
				);
				
				// 値のセット
				ps.setString(1, this.c_name);
				ps.setInt(2, this.c_year);
				ps.setInt(3, this.s_id);
				
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
	 * コース情報を変更します。<br>
	 * 変更には、プロパティc_id, c_name, c_year, s_idを使用します。<br>
	 * 変更しない情報は現在の情報をセットして呼び出してください。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changeCourse(){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(this.c_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.c_name, true)){
			return result;
		}
		
		if(!DBCheck.number(this.c_year, true)){
			return result;
		}
		
		if(!DBCheck.number(this.s_id, true)){
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
					"SELECT c_name FROM course " +
					"WHERE " +
					"c_name like ? and s_id = ?"
			);
			
			// 値のセット
			ps.setString(1, this.c_name);
			ps.setInt(2, this.s_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// コースの重複チェック
			if(!(rs.next())){
				// SQL文の生成
				ps = db.prepareStatement(
						"UPDATE course " +
						"SET c_name = ?, c_year = ? " +
						"WHERE " +
						"c_id = ? and s_id = ?"
				);
				
				// 値のセット
				ps.setString(1, this.c_name);
				ps.setInt(2, this.c_year);
				ps.setInt(3, this.c_id);
				ps.setInt(4, this.s_id);
				
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
	 * コースを削除します。
	 * @param c_id 削除するコースID
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int deleteCourse(int c_id){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(c_id, true)){
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
					"DELETE from course " +
					"WHERE " +
					"c_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, c_id);
			
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
	 * コース一覧を返します。
	 * @return 成功すれば、BeansCourseのArrayListが戻ります。
	 */
	public static ArrayList<BeansCourse> listCourse(){
		// SQLクエリ結果を保持
		ArrayList<BeansCourse> result = new ArrayList<BeansCourse>();

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
					"SELECT * FROM course " +
					"ORDER BY c_id ASC"
			);
			
			// 値のセット
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansCourse tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansCourse();
				
				// 代入
				tmp.setC_id(rs.getInt("c_id"));
				tmp.setC_name(rs.getString("c_name"));
				tmp.setC_year(rs.getInt("c_year"));
				tmp.setS_id(rs.getInt("s_id"));
				
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
	 * 検索に一致するコースの一覧を返します。<br>
	 * 完全一致で検索します。部分一致は「%」をコース名の前後に含めて呼び出してください。
	 * @param c_name 検索するコース名
	 * @return BeansCourseのArrayListが戻ります。
	 */
	public static ArrayList<BeansCourse> searchCourse(String c_name){
		// SQLクエリ結果を保持
		ArrayList<BeansCourse> result = new ArrayList<BeansCourse>();
		
		// 入力チェック
		if(!DBCheck.vchar(c_name, true)){
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
					"SELECT * FROM course " +
					"WHERE c_name like ? " +
					"ORDER BY c_id ASC"
			);
			
			// 値のセット
			ps.setString(1, c_name);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansCourse tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansCourse();
				
				// 代入
				tmp.setC_id(rs.getInt("c_id"));
				tmp.setC_name(rs.getString("c_name"));
				tmp.setC_year(rs.getInt("c_year"));
				tmp.setS_id(rs.getInt("s_id"));
				
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
	 * 検索に一致するコースの一覧を返します。<br>
	 * 完全一致で検索します。部分一致は「%」をコース名の前後に含めて呼び出してください。
	 * @param s_id 検索する学科ID
	 * @return BeansCourseのArrayListが戻ります。
	 */
	public static ArrayList<BeansCourse> searchCourse(int s_id){
		// SQLクエリ結果を保持
		ArrayList<BeansCourse> result = new ArrayList<BeansCourse>();
		
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
					"SELECT * FROM course " +
					"WHERE s_id = ? " +
					"ORDER BY c_id ASC"
			);
			
			// 値のセット
			ps.setInt(1, s_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansCourse tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansCourse();
				
				// 代入
				tmp.setC_id(rs.getInt("c_id"));
				tmp.setC_name(rs.getString("c_name"));
				tmp.setC_year(rs.getInt("c_year"));
				tmp.setS_id(rs.getInt("s_id"));
				
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
	 * 検索に一致するコースの一覧を返します。<br>
	 * 完全一致で検索します。部分一致は「%」をコース名の前後に含めて呼び出してください。
	 * @param c_name 検索するコース名
	 * @param s_id 検索する学科ID
	 * @return BeansCourseのArrayListが戻ります。
	 */
	public static ArrayList<BeansCourse> searchCourse(String c_name, int s_id){
		// SQLクエリ結果を保持
		ArrayList<BeansCourse> result = new ArrayList<BeansCourse>();
		
		// 入力チェック
		if(!DBCheck.vchar(c_name, true)){
			return result;
		}
		
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
					"SELECT * FROM course " +
					"WHERE c_name like ? and s_id = ? " +
					"ORDER BY c_id ASC"
			);
			
			// 値のセット
			ps.setString(1, c_name);
			ps.setInt(2, s_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansCourse tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansCourse();
				
				// 代入
				tmp.setC_id(rs.getInt("c_id"));
				tmp.setC_name(rs.getString("c_name"));
				tmp.setC_year(rs.getInt("c_year"));
				tmp.setS_id(rs.getInt("s_id"));
				
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
	 * 指定されたコースIDの情報を返します。
	 * @return BeansCourseが戻ります。
	 */
	public static BeansCourse detailCourse(int c_id){
		// SQLクエリ結果を保持
		BeansCourse result = new BeansCourse();
		
		// 入力チェック
		if(!DBCheck.number(c_id, true)){
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
					"SELECT * FROM course " +
					"WHERE c_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, c_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// データの読み取り
			while(rs.next()){				
				// 代入
				result.setC_id(rs.getInt("c_id"));
				result.setC_name(rs.getString("c_name"));
				result.setC_year(rs.getInt("c_year"));
				result.setS_id(rs.getInt("s_id"));
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


	/**
	 * @return c_name
	 */
	public String getC_name() {
		return c_name;
	}

	/**
	 * @param c_name セットする c_name
	 */
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}


	/**
	 * @return c_year
	 */
	public int getC_year() {
		return c_year;
	}


	/**
	 * @param c_year セットする c_year
	 */
	public void setC_year(int c_year) {
		this.c_year = c_year;
	}


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
}
