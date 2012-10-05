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
 * 企業に関するデータベース処理を行うクラス
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
public class BeansCompany implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Property
	 */
	/**
	 *  企業ID
	 */
	private int comp_id = -1;
	
	/**
	 *  商号の種類
	 */
	private int compt_id = -1;
	
	/**
	 *  商号の位置
	 */
	private String compt_position = null;
	
	/**
	 *  企業名
	 */
	private String comp_name = null;
	
	/**
	 *  郵便番号
	 */
	private String comp_zip = null;
	
	/**
	 *  住所
	 */
	private String comp_address = null;
	
	/**
	 *  電話番号
	 */
	private String comp_phone = null;
	
	
	/*
	 * Constructor
	 */
	/**
	 * Default Constructor
	 */
	public BeansCompany() {
		super();
	}
	

	/*
	 * method
	 */
	/**
	 * 企業を追加します。<br>
	 * 作成には、プロパティcompt_id, compt_position, comp_name, comp_zip, comp_address, comp_phoneを使用します。
	 * @return 成功すれば、-1以外が戻り、プロパティcomp_idに企業IDがセットされます。
	 */
	public int createCompany(){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(this.compt_id, true)){
			return result;
		}
		
		if(!DBCheck.reportPosition(this.compt_position)){
			return result;
		}
		
		if(!DBCheck.vchar(this.comp_name, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.comp_zip, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.comp_address, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.comp_phone, true)){
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
					"SELECT comp_address, comp_name, compt_id, compt_position FROM company " +
					"WHERE " +
					"comp_name = ? and compt_id = ? and compt_position = ?"
			);
			
			// 値のセット
			ps.setString(1, this.comp_name);
			ps.setInt(2, this.compt_id);
			ps.setString(3, this.compt_position);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 企業の重複チェック
			if(rs.next()){
				if(this.comp_address.equals(rs.getString("comp_address"))){
					return result;
				}
			}

			// SQL文の生成
			ps = db.prepareStatement(
					"INSERT INTO company(" +
					"compt_id, compt_position, comp_name, comp_zip, " +
					"comp_address, comp_phone" +
					") VALUES(" +
					"?, ?, ?, ?, ?, ?" +
					")"
			);
			
			// 値のセット
			ps.setInt(1, this.compt_id);
			ps.setString(2, this.compt_position);
			ps.setString(3, this.comp_name);
			ps.setString(4, this.comp_zip);
			ps.setString(5, this.comp_address);
			ps.setString(6, this.comp_phone);
			
			// クエリの実行
			result = ps.executeUpdate();
			
			// corp_idの取得
			if(result != -1){
				ps = db.prepareStatement(
						"SELECT LAST_INSERT_ID() from company"
				);
				
				rs = ps.executeQuery();
				
				if(rs.next()){
					this.comp_id = rs.getInt("LAST_INSERT_ID()");
				}
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
	 * 企業情報を変更します。企業名は変更できません。<br>
	 * 作成には、プロパティcomp_id, compt_id, compt_position, comp_zip, comp_address, comp_phoneを使用します。<br>
	 * プロパティcomp_id, compt_id, compt_position, comp_zip, comp_address, comp_phoneに値をセットして呼び出してください。
	 * 変更しない情報は現在の情報をセットして呼び出してください。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changeCompany(){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(this.comp_id, true)){
			return result;
		}
		
		if(!DBCheck.number(this.compt_id, true)){
			return result;
		}
		
		if(!DBCheck.reportPosition(this.compt_position)){
			return result;
		}
		
		if(!DBCheck.vchar(this.comp_zip, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.comp_address, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.comp_phone, true)){
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
					"UPDATE company " +
					"SET compt_id = ?, compt_position = ?, comp_zip = ?, comp_address = ?, comp_phone = ? " +
					"WHERE " +
					"comp_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, this.compt_id);
			ps.setString(2, this.compt_position);
			ps.setString(3, this.comp_zip);
			ps.setString(4, this.comp_address);
			ps.setString(5, this.comp_phone);
			ps.setInt(6, this.comp_id);
			
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
	 * 企業を削除します。
	 * @param comp_id 削除する企業ID
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int deleteCompany(int comp_id){
		// SQLクエリ結果を保持
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(comp_id, true)){
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
					"DELETE from company " +
					"WHERE " +
					"comp_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, comp_id);
			
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
	 * 企業一覧を返します。
	 * @return 成功すれば、BeansCompanyのArrayListが戻ります。
	 */
	public static ArrayList<BeansCompany> listCompany(){
		// SQLクエリ結果を保持
		ArrayList<BeansCompany> result = new ArrayList<BeansCompany>();

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
					"SELECT * FROM company " +
					"ORDER BY comp_id ASC"
			);
			
			// 値のセット
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansCompany tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansCompany();
				
				// 代入
				tmp.setComp_id(rs.getInt("comp_id"));
				tmp.setCompt_id(rs.getInt("compt_id"));
				tmp.setCompt_position(rs.getString("compt_position"));
				tmp.setComp_name(rs.getString("comp_name"));
				tmp.setComp_zip(rs.getString("comp_zip"));
				tmp.setComp_address(rs.getString("comp_address"));
				tmp.setComp_phone(rs.getString("comp_phone"));
				
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
	 * 検索に一致する企業の一覧を返します。<br>
	 * 完全一致で検索します。部分一致は「%」を企業名の前後に含めて呼び出してください。
	 * @param comp_name 検索する企業名
	 * @return 成功すれば、BeansCompanyのArrayListが戻ります。
	 */
	public static ArrayList<BeansCompany> searchCompany(String comp_name){
		// SQLクエリ結果を保持
		ArrayList<BeansCompany> result = new ArrayList<BeansCompany>();
		
		// 入力チェック
		if(!DBCheck.vchar(comp_name, true)){
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
					"SELECT * FROM company " +
					"WHERE comp_name like ? " +
					"ORDER BY comp_id ASC"
			);
			
			// 値のセット
			ps.setString(1, comp_name);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansCompany tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansCompany();
				
				// 代入
				tmp.setComp_id(rs.getInt("comp_id"));
				tmp.setCompt_id(rs.getInt("compt_id"));
				tmp.setCompt_position(rs.getString("compt_position"));
				tmp.setComp_name(rs.getString("comp_name"));
				tmp.setComp_zip(rs.getString("comp_zip"));
				tmp.setComp_address(rs.getString("comp_address"));
				tmp.setComp_phone(rs.getString("comp_phone"));
				
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
	 * 指定された企業IDの情報を返します。
	 * @param comp_id 詳細を取得する企業ID
	 * @return 成功すれば、BeansCompanyが戻ります。
	 */
	public static BeansCompany detailCompany(int comp_id){
		// SQLクエリ結果を保持
		BeansCompany result = new BeansCompany();
		
		// 入力チェック
		if(!DBCheck.number(comp_id, true)){
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
					"SELECT * FROM company " +
					"WHERE comp_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, comp_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// データの読み取り
			while(rs.next()){				
				// 代入
				result.setComp_id(rs.getInt("comp_id"));
				result.setCompt_id(rs.getInt("compt_id"));
				result.setCompt_position(rs.getString("compt_position"));
				result.setComp_name(rs.getString("comp_name"));
				result.setComp_zip(rs.getString("comp_zip"));
				result.setComp_address(rs.getString("comp_address"));
				result.setComp_phone(rs.getString("comp_phone"));
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
	 * @return comp_id
	 */
	public int getComp_id() {
		return comp_id;
	}

	/**
	 * @param comp_id セットする comp_id
	 */
	public void setComp_id(int comp_id) {
		this.comp_id = comp_id;
	}


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
	 * @return compt_position
	 */
	public String getCompt_position() {
		return compt_position;
	}

	/**
	 * @param compt_position セットする compt_position
	 */
	public void setCompt_position(String compt_position) {
		this.compt_position = compt_position;
	}


	/**
	 * @return comp_name
	 */
	public String getComp_name() {
		return comp_name;
	}

	/**
	 * @param comp_name セットする comp_name
	 */
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}


	/**
	 * @return comp_zip
	 */
	public String getComp_zip() {
		return comp_zip;
	}

	/**
	 * @param comp_zip セットする comp_zip
	 */
	public void setComp_zip(String comp_zip) {
		this.comp_zip = comp_zip;
	}


	/**
	 * @return comp_address
	 */
	public String getComp_address() {
		return comp_address;
	}

	/**
	 * @param comp_address セットする comp_address
	 */
	public void setComp_address(String comp_address) {
		this.comp_address = comp_address;
	}


	/**
	 * @return comp_phone
	 */
	public String getComp_phone() {
		return comp_phone;
	}

	/**
	 * @param comp_phone セットする comp_phone
	 */
	public void setComp_phone(String comp_phone) {
		this.comp_phone = comp_phone;
	}
}
