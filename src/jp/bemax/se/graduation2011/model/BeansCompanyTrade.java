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
 * 企業の商号に関するデータベース処理を行うクラス
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
public class BeansCompanyTrade implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Property
	 */
	/**
	 *  商号ID
	 */
	private int compt_id = -1;
	
	/**
	 *  商号名
	 */
	private String compt_name = null;
	
	
	/*
	 * Constructor
	 */
	/**
	 * Default Constructor
	 */
	public BeansCompanyTrade() {
		super();
	}
	

	/*
	 * method
	 */
	/**
	 * 商号を追加します。<br>
	 * 作成には、プロパティcompt_nameを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int createCompanyTrade(){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.compt_name, true)){
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
					"SELECT compt_name FROM companytrade " +
					"WHERE " +
					"compt_name like ?"
			);
			
			// 値のセット
			ps.setString(1, this.compt_name);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 企業の重複チェック
			if(!(rs.next())){
				// SQL文の生成
				ps = db.prepareStatement(
						"INSERT INTO companytrade(" +
						"compt_name" +
						") VALUES(" +
						"?" +
						")"
				);
				
				// 値のセット
				ps.setString(1, this.compt_name);
				
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
	 * 商号情報を変更します。<br>
	 * 作成には、プロパティcompt_id, compt_nameを使用します。<br>
	 * プロパティcompt_id, compt_nameに値をセットして呼び出してください。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changeCompanyTrade(){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(this.compt_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.compt_name, true)){
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
					"SELECT compt_name FROM companytrade " +
					"WHERE " +
					"compt_name like ?"
			);
			
			// 値のセット
			ps.setString(1, this.compt_name);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 企業の重複チェック
			if(!(rs.next())){
				// SQL文の生成
				ps = db.prepareStatement(
						"UPDATE companytrade " +
						"SET compt_name = ? " +
						"WHERE " +
						"compt_id = ?"
				);
				
				// 値のセット
				ps.setString(1, this.compt_name);
				ps.setInt(2, this.compt_id);
				
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
	 * 商号を削除します。
	 * @param compt_id 削除する商号ID
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int deleteCompanyTrade(int compt_id){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(compt_id, true)){
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
					"DELETE from companytrade " +
					"WHERE " +
					"compt_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, compt_id);
			
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
	 * 商号一覧を返します。
	 * @return 成功すれば、BeansCompanyTradeのArrayListが戻ります。
	 */
	public static ArrayList<BeansCompanyTrade> listCompanyTrade(){
		// SQLクエリ結果を保持
		ArrayList<BeansCompanyTrade> result = new ArrayList<BeansCompanyTrade>();

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
					"SELECT * FROM companytrade " +
					"ORDER BY compt_id ASC"
			);
			
			// 値のセット
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansCompanyTrade tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansCompanyTrade();
				
				// 代入
				tmp.setCompt_id(rs.getInt("compt_id"));
				tmp.setCompt_name(rs.getString("compt_name"));
				
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
	 * 指定された商号IDの情報を返します。
	 * @param compt_id 詳細を取得する企業ID
	 * @return 成功すれば、BeansCompanyTradeが戻ります。
	 */
	public static BeansCompanyTrade detailCompanyTrade(int compt_id){
		// SQLクエリ結果を保持
		BeansCompanyTrade result = new BeansCompanyTrade();
		
		// 入力チェック
		if(!DBCheck.number(compt_id, true)){
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
					"SELECT * FROM companytrade " +
					"WHERE compt_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, compt_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// データの読み取り
			while(rs.next()){				
				// 代入
				result.setCompt_id(rs.getInt("compt_id"));
				result.setCompt_name(rs.getString("compt_name"));
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
	 * @return compt_id
	 */
	public int getCompt_id() {
		return compt_id;
	}

	/**
	 * @param compt_id セットする compt_id
	 */
	public void setCompt_id(int compt_id) {
		this.compt_id = compt_id;
	}


	/**
	 * @return compt_name
	 */
	public String getCompt_name() {
		return compt_name;
	}

	/**
	 * @param compt_name セットする compt_name
	 */
	public void setCompt_name(String compt_name) {
		this.compt_name = compt_name;
	}
}
