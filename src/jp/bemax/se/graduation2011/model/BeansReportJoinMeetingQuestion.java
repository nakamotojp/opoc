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
 * 合同説明会報告書の質問した内容に関するデータベース処理を行うクラス
 * @author Takenaka
 */
public class BeansReportJoinMeetingQuestion implements Serializable {
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
	private int rjmq_id = -1;
	
	/**
	 * 質問
	 */
	private String rjmq_question = null;
	
	/**
	 * 回答
	 */
	private String rjmq_answer = null;
	
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
	public BeansReportJoinMeetingQuestion() {
		super();
	}
	

	/*
	 * method
	 */
	/**
	 * 合同説明会報告書の質問内容を追加します。<br>
	 * 作成には、プロパティrjmq_question, rjmq_answer, rjm_idを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int createReportJoinMeetingQuestion(){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.rjmq_question, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjmq_answer, true)){
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
					"INSERT INTO reportjoinmeetingquestion(" +
					"rjmq_question, rjmq_answer, rjm_id" +
					") VALUES(" +
					"?, ?, ?" +
					")"
			);
			
			// 値のセット
			ps.setString(1, this.rjmq_question);
			ps.setString(2, this.rjmq_answer);
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
	 * 作成には、プロパティrjmq_id, rjmq_question, rjmq_answer, rjm_idを使用します。<br>
	 * 変更しない情報は現在の情報をセットして呼び出してください。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changeReportJoinMeetingQuestion(){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(this.rjmq_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjmq_question, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rjmq_answer, true)){
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
					"UPDATE reportjoinmeetingquestion " +
					"SET rjmq_question = ?, rjmq_answer = ?, rjm_id = ? " +
					"WHERE rjmq_id = ?"
			);
			
			// 値のセット
			ps.setString(1, this.rjmq_question);
			ps.setString(2, this.rjmq_answer);
			ps.setInt(3, this.rjm_id);
			ps.setInt(4, this.rjmq_id);
			
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
	 * @param rjmq_id 削除する報告書の質問ID
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int deleteReportJoinMeetingQuestion(int rjmq_id){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(rjmq_id, true)){
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
					"DELETE FROM reportjoinmeetingquestion " +
					"WHERE rjmq_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, rjmq_id);
			
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
	 * @return 成功すれば、BeansReportJoinMeetingQuestionのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportJoinMeetingQuestion> listReportJoinMeetingQuestion(int rjm_id){
		// 戻り値
		ArrayList<BeansReportJoinMeetingQuestion> result = new ArrayList<BeansReportJoinMeetingQuestion>();
		
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
					"SELECT * FROM reportjoinmeetingquestion " +
					"WHERE rjm_id = ? " +
					"ORDER BY rjmq_id ASC"
			);
			
			// 値のセット
			ps.setInt(1, rjm_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportJoinMeetingQuestion tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportJoinMeetingQuestion();
				
				// 代入
				tmp.setRjmq_id(rs.getInt("rjmq_id"));
				tmp.setRjmq_question(rs.getString("rjmq_question"));
				tmp.setRjmq_answer(rs.getString("rjmq_answer"));
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
	 * @return rjmq_id
	 */
	public int getRjmq_id() {
		return rjmq_id;
	}

	/**
	 * @param rjmq_id セットする rjmq_id
	 */
	public void setRjmq_id(int rjmq_id) {
		this.rjmq_id = rjmq_id;
	}


	/**
	 * @return rjmq_question
	 */
	public String getRjmq_question() {
		return rjmq_question;
	}

	/**
	 * @param rjmq_question セットする rjmq_question
	 */
	public void setRjmq_question(String rjmq_question) {
		this.rjmq_question = rjmq_question;
	}


	/**
	 * @return rjmq_answer
	 */
	public String getRjmq_answer() {
		return rjmq_answer;
	}

	/**
	 * @param rjmq_answer セットする rjmq_answer
	 */
	public void setRjmq_answer(String rjmq_answer) {
		this.rjmq_answer = rjmq_answer;
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
