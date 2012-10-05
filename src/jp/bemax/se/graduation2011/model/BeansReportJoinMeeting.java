/**
 * 
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
 * 合同説明会報告書に関するデータベース処理を行うクラス
 * @author Takenaka
 */
public class BeansReportJoinMeeting implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Property
	 */
	/**
	 * 報告書ID
	 */
	private int rjm_id = -1;
	
	/**
	 * 説明会名
	 */
	private String rjm_name = null;
	
	/**
	 * 参加日
	 */
	private String rjm_participate = null;
	
	/**
	 * 提出日
	 */
	private String rjm_introduction = null;
	
	/**
	 * 担当者名
	 */
	private String rjm_owner = null;
	
	/**
	 * 内容
	 */
	private String rjm_content = null;
	
	/**
	 * 特記事項
	 */
	private String rjm_particular = null;
	
	/**
	 * 次回
	 */
	private String rjm_nexttime = null;
	
	/**
	 * 所感
	 */
	private String rjm_impression = null;
	
	/**
	 * 状態
	 */
	private String rjm_status = null;
	
	/**
	 * ログインID
	 */
	private String l_id = null;
	
	/**
	 * 企業ID
	 */
	private int comp_id = -1;
	
	/**
	 * 質問した内容
	 */
	private ArrayList<BeansReportJoinMeetingQuestion> ReportJoinMeetingQuestion = null;
	
	/**
	 * 質問された内容
	 */
	private ArrayList<BeansReportJoinMeetingReply> ReportJoinMeetingReply = null;
	
	
	/*
	 * Constructor
	 */
	/**
	 * Default Constructor
	 */
	public BeansReportJoinMeeting() {
		super();
	}
	

	/*
	 * method
	 */
	/**
	 * 合同説明会報告書を追加します。<br>
	 * 作成には、プロパティrjm_name, rjm_participate, rjm_introduction, rjm_owner, rjm_content,
	 * rjm_particular, rjm_nexttime, rjm_impression, rjm_status, l_id, comp_idを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int createReportJoinMeeting(){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.rjm_name, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjm_participate, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjm_introduction, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjm_owner, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rjm_content, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjm_particular, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjm_nexttime, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rjm_impression, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjm_status, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.l_id, true)){
			return result;
		}
		
		if(!DBCheck.number(this.comp_id, true)){
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
					"INSERT INTO reportjoinmeeting(" +
					"rjm_name, rjm_participate, rjm_introduction, rjm_owner, rjm_content, " +
					"rjm_particular, rjm_nexttime, rjm_impression, rjm_status, l_id, comp_id" +
					") VALUES(" +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
					")"
			);
			
			// 値のセット
			ps.setString(1, this.rjm_name);
			ps.setString(2, this.rjm_participate);
			ps.setString(3, this.rjm_introduction);
			ps.setString(4, this.rjm_owner);
			ps.setString(5, this.rjm_content);
			ps.setString(6, this.rjm_particular);
			ps.setString(7, this.rjm_nexttime);
			ps.setString(8, this.rjm_impression);
			ps.setString(9, this.rjm_status);
			ps.setString(10, this.l_id);
			ps.setInt(11, this.comp_id);
			
			// クエリの実行
			result = ps.executeUpdate();
			
			// rjm_idの取得
			if(result != -1){
				// 結果セットを保持
				ResultSet rs = null;
				
				ps = db.prepareStatement(
						"SELECT LAST_INSERT_ID() from reportjoinmeeting"
				);
				
				rs = ps.executeQuery();
				
				if(rs.next()){
					this.rjm_id = rs.getInt("LAST_INSERT_ID()");
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
	 * 合同説明会報告書を変更します。<br>
	 * 作成には、プロパティrjm_id, rjm_name, rjm_participate, rjm_introduction, rjm_owner,
	 * rjm_content, rjm_particular, rjm_nexttime, rjm_impression, rjm_status,
	 * l_id, comp_idを使用します。<br>
	 * 変更しない情報は現在の情報をセットして呼び出してください。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changeReportJoinMeeting(){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(this.rjm_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjm_name, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjm_participate, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjm_introduction, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjm_owner, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rjm_content, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjm_particular, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjm_nexttime, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rjm_impression, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjm_status, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.l_id, true)){
			return result;
		}
		
		if(!DBCheck.number(this.comp_id, true)){
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
					"UPDATE reportjoinmeeting " +
					"SET rjm_name = ?, rjm_participate = ?, rjm_introduction = ?, rjm_owner = ?, rjm_content = ?, " +
					"rjm_particular = ?, rjm_nexttime = ?, rjm_impression = ?, rjm_status = ?, l_id = ?, comp_id = ? " +
					"WHERE rjm_id = ?"
			);
			
			// 値のセット
			ps.setString(1, this.rjm_name);
			ps.setString(2, this.rjm_participate);
			ps.setString(3, this.rjm_introduction);
			ps.setString(4, this.rjm_owner);
			ps.setString(5, this.rjm_content);
			ps.setString(6, this.rjm_particular);
			ps.setString(7, this.rjm_nexttime);
			ps.setString(8, this.rjm_impression);
			ps.setString(9, this.rjm_status);
			ps.setString(10, this.l_id);
			ps.setInt(11, this.comp_id);
			ps.setInt(12, this.rjm_id);
			
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
	 * 合同説明会報告書の状態を変更します。<br>
	 * 作成には、引数rjm_id, rjm_statusを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int changeStatus(int rjm_id, String rjm_status){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(rjm_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(rjm_status, true)){
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
					"UPDATE reportjoinmeeting " +
					"SET rjm_status = ? " +
					"WHERE rjm_id = ?"
			);
			
			// 値のセット
			ps.setString(1, rjm_status);
			ps.setInt(2, rjm_id);
			
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
	 * 合同説明会報告書を削除します。
	 * @param rjm_id 削除する報告書ID
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int deleteReportJoinMeeting(int rjm_id){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(rjm_id, true)){
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
					"DELETE from reportjoinmeeting " +
					"WHERE rjm_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, rjm_id);
			
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
	 * 合同説明会報告書一覧を返します。
	 * @return 成功すれば、BeansReportJoinMeetingのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportJoinMeeting> listReportJoinMeeting(){
		// 戻り値
		ArrayList<BeansReportJoinMeeting> result = new ArrayList<BeansReportJoinMeeting>();

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
					"SELECT * FROM reportjoinmeeting " +
					"ORDER BY rjm_introduction DESC, rjm_id DESC"
			);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportJoinMeeting tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportJoinMeeting();
				
				// 代入
				tmp.setRjm_id(rs.getInt("rjm_id"));
				tmp.setRjm_name(rs.getString("rjm_name"));
				tmp.setRjm_participate(rs.getString("rjm_participate"));
				tmp.setRjm_introduction(rs.getString("rjm_introduction"));
				tmp.setRjm_owner(rs.getString("rjm_owner"));
				tmp.setRjm_content(rs.getString("rjm_content"));
				tmp.setRjm_particular(rs.getString("rjm_particular"));
				tmp.setRjm_nexttime(rs.getString("rjm_nexttime"));
				tmp.setRjm_impression(rs.getString("rjm_impression"));
				tmp.setRjm_status(rs.getString("rjm_status"));
				tmp.setL_id(rs.getString("l_id"));
				tmp.setComp_id(rs.getInt("comp_id"));
				
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
	 * 検索に一致する合同説明会報告書の一覧を返します。
	 * @param rjm_status 検索する状態
	 * @return 成功すれば、BeansReportJoinMeetingのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportJoinMeeting> searchReportJoinMeeting(String rjm_status){
		// 戻り値
		ArrayList<BeansReportJoinMeeting> result = new ArrayList<BeansReportJoinMeeting>();
		
		// 入力チェック
		if(!DBCheck.vchar(rjm_status, true)){
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
					"SELECT * FROM reportjoinmeeting " +
					"WHERE rjm_status like ? " +
					"ORDER BY rjm_introduction DESC, rjm_id DESC"
			);
			
			// 値のセット
			ps.setString(1, rjm_status);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportJoinMeeting tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportJoinMeeting();
				
				// 代入
				tmp.setRjm_id(rs.getInt("rjm_id"));
				tmp.setRjm_name(rs.getString("rjm_name"));
				tmp.setRjm_participate(rs.getString("rjm_participate"));
				tmp.setRjm_introduction(rs.getString("rjm_introduction"));
				tmp.setRjm_owner(rs.getString("rjm_owner"));
				tmp.setRjm_content(rs.getString("rjm_content"));
				tmp.setRjm_particular(rs.getString("rjm_particular"));
				tmp.setRjm_nexttime(rs.getString("rjm_nexttime"));
				tmp.setRjm_impression(rs.getString("rjm_impression"));
				tmp.setRjm_status(rs.getString("rjm_status"));
				tmp.setL_id(rs.getString("l_id"));
				tmp.setComp_id(rs.getInt("comp_id"));
				
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
	 * 検索に一致する合同説明会報告書の一覧を返します。
	 * @param rjm_status 検索する状態
	 * @param comp_id 検索する企業
	 * @param s_id 検索する学科ID
	 * @return 成功すれば、BeansReportJoinMeetingのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportJoinMeeting> searchReportJoinMeeting(String rjm_status, int comp_id, int s_id){
		// 戻り値
		ArrayList<BeansReportJoinMeeting> result = new ArrayList<BeansReportJoinMeeting>();
		
		// 入力チェック
		if(!DBCheck.vchar(rjm_status, true)){
			return result;
		}
		if(!DBCheck.number(comp_id, true)){
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
					"SELECT * FROM reportjoinmeeting " +
					"left join user on reportjoinmeeting.l_id = user.l_id " +
					"left join course on user.c_id = course.c_id " +
					"left join subject on course.s_id = subject.s_id " +
					"WHERE rjm_status like ? and comp_id = ? and subject.s_id = ? " +
					"ORDER BY rjm_introduction DESC, rjm_id DESC"
			);
			
			// 値のセット
			ps.setString(1, rjm_status);
			ps.setInt(2, comp_id);
			ps.setInt(3, s_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportJoinMeeting tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportJoinMeeting();
				
				// 代入
				tmp.setRjm_id(rs.getInt("rjm_id"));
				tmp.setRjm_name(rs.getString("rjm_name"));
				tmp.setRjm_participate(rs.getString("rjm_participate"));
				tmp.setRjm_introduction(rs.getString("rjm_introduction"));
				tmp.setRjm_owner(rs.getString("rjm_owner"));
				tmp.setRjm_content(rs.getString("rjm_content"));
				tmp.setRjm_particular(rs.getString("rjm_particular"));
				tmp.setRjm_nexttime(rs.getString("rjm_nexttime"));
				tmp.setRjm_impression(rs.getString("rjm_impression"));
				tmp.setRjm_status(rs.getString("rjm_status"));
				tmp.setL_id(rs.getString("l_id"));
				tmp.setComp_id(rs.getInt("comp_id"));
				
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
	 * 検索に一致する合同説明会報告書の一覧を返します。
	 * @param c_id 検索するコースID
	 * @param u_year 検索する学年
	 * @return 成功すれば、BeansReportJoinMeetingのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportJoinMeeting> searchReportJoinMeeting(int c_id, int u_year){
		// 戻り値
		ArrayList<BeansReportJoinMeeting> result = new ArrayList<BeansReportJoinMeeting>();
		
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
					"SELECT * FROM reportjoinmeeting  left join user " +
					"on reportjoinmeeting.l_id = user.l_id " +
					"WHERE c_id = ? AND u_year = ? " +
					"ORDER BY rjm_introduction DESC, rjm_id DESC"
			);
			
			// 値のセット
			ps.setInt(1, c_id);
			ps.setInt(2, u_year);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportJoinMeeting tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportJoinMeeting();
				
				// 代入
				tmp.setRjm_id(rs.getInt("rjm_id"));
				tmp.setRjm_name(rs.getString("rjm_name"));
				tmp.setRjm_participate(rs.getString("rjm_participate"));
				tmp.setRjm_introduction(rs.getString("rjm_introduction"));
				tmp.setRjm_owner(rs.getString("rjm_owner"));
				tmp.setRjm_content(rs.getString("rjm_content"));
				tmp.setRjm_particular(rs.getString("rjm_particular"));
				tmp.setRjm_nexttime(rs.getString("rjm_nexttime"));
				tmp.setRjm_impression(rs.getString("rjm_impression"));
				tmp.setRjm_status(rs.getString("rjm_status"));
				tmp.setL_id(rs.getString("l_id"));
				tmp.setComp_id(rs.getInt("comp_id"));
				
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
	 * 検索に一致する合同説明会報告書の一覧を返します。
	 * @param comp_id 検索する企業ID
	 * @return 成功すれば、BeansReportJoinMeetingのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportJoinMeeting> searchReportJoinMeeting(int comp_id){
		// 戻り値
		ArrayList<BeansReportJoinMeeting> result = new ArrayList<BeansReportJoinMeeting>();
		
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
					"SELECT * FROM reportjoinmeeting  left join user " +
					"on reportjoinmeeting.l_id = user.l_id " +
					"WHERE comp_id = ? " +
					"ORDER BY rjm_introduction DESC, rjm_id DESC"
			);
			
			// 値のセット
			ps.setInt(1, comp_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportJoinMeeting tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportJoinMeeting();
				
				// 代入
				tmp.setRjm_id(rs.getInt("rjm_id"));
				tmp.setRjm_name(rs.getString("rjm_name"));
				tmp.setRjm_participate(rs.getString("rjm_participate"));
				tmp.setRjm_introduction(rs.getString("rjm_introduction"));
				tmp.setRjm_owner(rs.getString("rjm_owner"));
				tmp.setRjm_content(rs.getString("rjm_content"));
				tmp.setRjm_particular(rs.getString("rjm_particular"));
				tmp.setRjm_nexttime(rs.getString("rjm_nexttime"));
				tmp.setRjm_impression(rs.getString("rjm_impression"));
				tmp.setRjm_status(rs.getString("rjm_status"));
				tmp.setL_id(rs.getString("l_id"));
				tmp.setComp_id(rs.getInt("comp_id"));
				
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
	 * 指定された合同説明会報告書IDの情報を返します。
	 * @param rjm_id 詳細を取得する報告書ID
	 * @return 成功すれば、BeansReportJoinMeetingが戻ります。
	 */
	public static BeansReportJoinMeeting detailReportJoinMeeting(int rjm_id){
		// 戻り値
		BeansReportJoinMeeting result = new BeansReportJoinMeeting();
		
		// 入力チェック
		if(!DBCheck.number(rjm_id, true)){
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
					"SELECT * FROM reportjoinmeeting " +
					"WHERE rjm_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, rjm_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// データの読み取り
			while(rs.next()){
				
				// 代入
				result.setRjm_id(rs.getInt("rjm_id"));
				result.setRjm_name(rs.getString("rjm_name"));
				result.setRjm_participate(rs.getString("rjm_participate"));
				result.setRjm_introduction(rs.getString("rjm_introduction"));
				result.setRjm_owner(rs.getString("rjm_owner"));
				result.setRjm_content(rs.getString("rjm_content"));
				result.setRjm_particular(rs.getString("rjm_particular"));
				result.setRjm_nexttime(rs.getString("rjm_nexttime"));
				result.setRjm_impression(rs.getString("rjm_impression"));
				result.setRjm_status(rs.getString("rjm_status"));
				result.setL_id(rs.getString("l_id"));
				result.setComp_id(rs.getInt("comp_id"));
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
	 * @return rjm_id
	 */
	public int getRjm_id() {
		return rjm_id;
	}

	/**
	 * @param rjm_id セットする rjm_id
	 */
	public void setRjm_id(int rjm_id) {
		this.rjm_id = rjm_id;
	}


	/**
	 * @return rjm_name
	 */
	public String getRjm_name() {
		return rjm_name;
	}

	/**
	 * @param rjm_name セットする rjm_name
	 */
	public void setRjm_name(String rjm_name) {
		this.rjm_name = rjm_name;
	}


	/**
	 * @return rjm_participate
	 */
	public String getRjm_participate() {
		return rjm_participate;
	}

	/**
	 * @param rjm_participate セットする rjm_participate
	 */
	public void setRjm_participate(String rjm_participate) {
		this.rjm_participate = rjm_participate;
	}


	/**
	 * @return rjm_introduction
	 */
	public String getRjm_introduction() {
		return rjm_introduction;
	}

	/**
	 * @param rjm_introduction セットする rjm_introduction
	 */
	public void setRjm_introduction(String rjm_introduction) {
		this.rjm_introduction = rjm_introduction;
	}


	/**
	 * @return rjm_owner
	 */
	public String getRjm_owner() {
		return rjm_owner;
	}

	/**
	 * @param rjm_owner セットする rjm_owner
	 */
	public void setRjm_owner(String rjm_owner) {
		this.rjm_owner = rjm_owner;
	}


	/**
	 * @return rjm_content
	 */
	public String getRjm_content() {
		return rjm_content;
	}

	/**
	 * @param rjm_content セットする rjm_content
	 */
	public void setRjm_content(String rjm_content) {
		this.rjm_content = rjm_content;
	}


	/**
	 * @return rjm_particular
	 */
	public String getRjm_particular() {
		return rjm_particular;
	}

	/**
	 * @param rjm_particular セットする rjm_particular
	 */
	public void setRjm_particular(String rjm_particular) {
		this.rjm_particular = rjm_particular;
	}


	/**
	 * @return rjm_nexttime
	 */
	public String getRjm_nexttime() {
		return rjm_nexttime;
	}

	/**
	 * @param rjm_nexttime セットする rjm_nexttime
	 */
	public void setRjm_nexttime(String rjm_nexttime) {
		this.rjm_nexttime = rjm_nexttime;
	}


	/**
	 * @return rjm_impression
	 */
	public String getRjm_impression() {
		return rjm_impression;
	}

	/**
	 * @param rjm_impression セットする rjm_impression
	 */
	public void setRjm_impression(String rjm_impression) {
		this.rjm_impression = rjm_impression;
	}


	/**
	 * @return rjm_status
	 */
	public String getRjm_status() {
		return rjm_status;
	}

	/**
	 * @param rjm_status セットする rjm_status
	 */
	public void setRjm_status(String rjm_status) {
		this.rjm_status = rjm_status;
	}


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
	 * @return reportJoinMeetingQuestion
	 */
	public ArrayList<BeansReportJoinMeetingQuestion> getReportJoinMeetingQuestion() {
		return ReportJoinMeetingQuestion;
	}

	/**
	 * @param reportJoinMeetingQuestion セットする reportJoinMeetingQuestion
	 */
	public void setReportJoinMeetingQuestion(
			ArrayList<BeansReportJoinMeetingQuestion> reportJoinMeetingQuestion) {
		ReportJoinMeetingQuestion = reportJoinMeetingQuestion;
	}


	/**
	 * @return reportJoinMeetingReply
	 */
	public ArrayList<BeansReportJoinMeetingReply> getReportJoinMeetingReply() {
		return ReportJoinMeetingReply;
	}

	/**
	 * @param reportJoinMeetingReply セットする reportJoinMeetingReply
	 */
	public void setReportJoinMeetingReply(
			ArrayList<BeansReportJoinMeetingReply> reportJoinMeetingReply) {
		ReportJoinMeetingReply = reportJoinMeetingReply;
	}
}