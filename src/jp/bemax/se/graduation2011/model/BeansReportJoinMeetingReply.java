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
 * 合同説明会報告書の質問された内容に関するデータベース処理を行うクラス
 * @author Takenaka
 */
public class BeansReportJoinMeetingReply implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Property
	 */
	/**
	 * 質問ID
	 */
	private int rjmr_id = -1;
	
	/**
	 * 質問
	 */
	private String rjmr_question = null;
	
	/**
	 * 回答
	 */
	private String rjmr_answer = null;
	
	/**
	 * 報告書ID
	 */
	private int rjm_id = -1;
	
	
	/*
	 * Constructor
	 */
	/**
	 * Default Constructor
	 */
	public BeansReportJoinMeetingReply() {
		super();
	}
	

	/*
	 * method
	 */
	/**
	 * 合同説明会報告書の質問内容を追加します。<br>
	 * 作成には、プロパティrjmr_question, rjmr_answer, rjm_idを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int createReportJoinMeetingReply(){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.rjmr_question, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjmr_answer, true)){
			return result;
		}
		
		if(!DBCheck.number(this.rjm_id, true)){
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
					"INSERT INTO reportjoinmeetingreply(" +
					"rjmr_question, rjmr_answer, rjm_id" +
					") VALUES(" +
					"?, ?, ?" +
					")"
			);
			
			// 値のセット
			ps.setString(1, this.rjmr_question);
			ps.setString(2, this.rjmr_answer);
			ps.setInt(3, this.rjm_id);
			
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
	 * 合同説明会報告書の質問内容を変更します。<br>
	 * 作成には、プロパティrjmr_id, rjmr_question, rjmr_answer, rjm_idを使用します。<br>
	 * 変更しない情報は現在の情報をセットして呼び出してください。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changeReportJoinMeetingReply(){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.rjmr_question, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjmr_answer, true)){
			return result;
		}
		
		if(!DBCheck.number(this.rjm_id, true)){
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
					"UPDATE reportjoinmeetingreply " +
					"SET rjmr_question = ?, rjmr_answer = ?, rjm_id = ? " +
					"WHERE rjmr_id = ?"
			);
			
			// 値のセット
			ps.setString(1, this.rjmr_question);
			ps.setString(2, this.rjmr_answer);
			ps.setInt(3, this.rjm_id);
			ps.setInt(4, this.rjmr_id);
			
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
	 * 合同説明会報告書の質問内容を削除します。
	 * @param rjmr_id 削除する報告書の質問ID
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int deleteReportJoinMeetingReply(int rjmr_id){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(rjmr_id, true)){
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
					"DELETE FROM reportjoinmeetingreply " +
					"WHERE rjmr_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, rjmr_id);
			
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
	 * 指定IDの合同説明会報告書の質問内容一覧を返します。
	 * @param rjm_id 合同説明会報告書ID
	 * @return 成功すれば、BeansReportJoinMeetingReplyのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportJoinMeetingReply> listReportJoinMeetingReply(int rjm_id){
		// 戻り値
		ArrayList<BeansReportJoinMeetingReply> result = new ArrayList<BeansReportJoinMeetingReply>();
		
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
					"SELECT * FROM reportjoinmeetingreply " +
					"WHERE rjm_id = ? " +
					"ORDER BY rjmr_id ASC"
			);
			
			// 値のセット
			ps.setInt(1, rjm_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportJoinMeetingReply tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportJoinMeetingReply();
				
				// 代入
				tmp.setRjmr_id(rs.getInt("rjmr_id"));
				tmp.setRjmr_question(rs.getString("rjmr_question"));
				tmp.setRjmr_answer(rs.getString("rjmr_answer"));
				tmp.setRjm_id(rs.getInt("rjm_id"));
				
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
	
	
	/*
	 * Accessor
	 */
	/**
	 * @return rjmr_id
	 */
	public int getRjmr_id() {
		return rjmr_id;
	}

	/**
	 * @param rjmr_id セットする rjmr_id
	 */
	public void setRjmr_id(int rjmr_id) {
		this.rjmr_id = rjmr_id;
	}


	/**
	 * @return rjmr_question
	 */
	public String getRjmr_question() {
		return rjmr_question;
	}

	/**
	 * @param rjmr_question セットする rjmr_question
	 */
	public void setRjmr_question(String rjmr_question) {
		this.rjmr_question = rjmr_question;
	}


	/**
	 * @return rjmr_answer
	 */
	public String getRjmr_answer() {
		return rjmr_answer;
	}

	/**
	 * @param rjmr_answer セットする rjmr_answer
	 */
	public void setRjmr_answer(String rjmr_answer) {
		this.rjmr_answer = rjmr_answer;
	}


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
}
