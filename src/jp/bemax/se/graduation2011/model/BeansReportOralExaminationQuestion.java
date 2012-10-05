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
 * 面接試験報告書に関するデータベース処理を行うクラス
 * @author Takenaka
 */
public class BeansReportOralExaminationQuestion implements Serializable {
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
	private int roeq_id = -1;
	
	/**
	 * 質問
	 */
	private String roeq_question = null;
	
	/**
	 * 回答
	 */
	private String roeq_answer = null;
	
	/**
	 * 報告書ID
	 */
	private int roe_id = -1;
	
	
	/*
	 * Constructor
	 */
	/**
	 * Default Constructor
	 */
	public BeansReportOralExaminationQuestion() {
		super();
	}
	

	/*
	 * method
	 */
	/**
	 * 面接試験報告書の質問内容を追加します。<br>
	 * 作成には、プロパティroeq_question, roeq_answer, roe_idを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int createReportOralExaminationQuestion(){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.roeq_question, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roeq_answer, true)){
			return result;
		}
		
		if(!DBCheck.number(this.roe_id, true)){
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
					"INSERT INTO reportoralexaminationquestion(" +
					"roeq_question, roeq_answer, roe_id" +
					") VALUES(" +
					"?, ?, ?" +
					")"
			);
			
			// 値のセット
			ps.setString(1, this.roeq_question);
			ps.setString(2, this.roeq_answer);
			ps.setInt(3, this.roe_id);
			
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
	 * 面接試験報告書の質問内容を変更します。<br>
	 * 作成には、プロパティroeq_id, roeq_question, roeq_answer, roe_idを使用します。<br>
	 * 変更しない情報は現在の情報をセットして呼び出してください。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changeReportOralExaminationQuestion(){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(this.roeq_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roeq_question, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roeq_answer, true)){
			return result;
		}
		
		if(!DBCheck.number(this.roe_id, true)){
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
					"UPDATE reportoralexaminationquestion " +
					"SET roeq_question = ?, roeq_answer = ?, roe_id = ? " +
					"WHERE roeq_id = ?"
			);
			
			// 値のセット
			ps.setString(1, this.roeq_question);
			ps.setString(2, this.roeq_answer);
			ps.setInt(3, this.roe_id);
			ps.setInt(4, this.roeq_id);
			
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
	 * 面接試験報告書の質問内容を削除します。
	 * @param roeq_id 削除する報告書の質問ID
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int deleteReportOralExaminationQuestion(int roeq_id){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(roeq_id, true)){
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
					"DELETE FROM reportoralexaminationquestion " +
					"WHERE roeq_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, roeq_id);
			
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
	 * 指定IDの面接試験報告書の質問内容一覧を返します。
	 * @param roe_id 面接試験報告書ID
	 * @return 成功すれば、BeansReportOralExaminationQuestionのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportOralExaminationQuestion> listReportOralExaminationQuestion(int roe_id){
		// 戻り値
		ArrayList<BeansReportOralExaminationQuestion> result = new ArrayList<BeansReportOralExaminationQuestion>();
		
		// 入力チェック
		if(!DBCheck.number(roe_id, true)){
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
					"SELECT * FROM reportoralexaminationquestion " +
					"WHERE roe_id = ? " +
					"ORDER BY roeq_id ASC"
			);
			
			// 値のセット
			ps.setInt(1, roe_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportOralExaminationQuestion tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportOralExaminationQuestion();
				
				// 代入
				tmp.setRoeq_id(rs.getInt("roeq_id"));
				tmp.setRoeq_question(rs.getString("roeq_question"));
				tmp.setRoeq_answer(rs.getString("roeq_answer"));
				tmp.setRoe_id(rs.getInt("roe_id"));
				
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
	 * @return roeq_id
	 */
	public int getRoeq_id() {
		return roeq_id;
	}

	/**
	 * @param roeq_id セットする roeq_id
	 */
	public void setRoeq_id(int roeq_id) {
		this.roeq_id = roeq_id;
	}


	/**
	 * @return roeq_question
	 */
	public String getRoeq_question() {
		return roeq_question;
	}

	/**
	 * @param roeq_question セットする roeq_question
	 */
	public void setRoeq_question(String roeq_question) {
		this.roeq_question = roeq_question;
	}


	/**
	 * @return roeq_answer
	 */
	public String getRoeq_answer() {
		return roeq_answer;
	}

	/**
	 * @param roeq_answer セットする roeq_answer
	 */
	public void setRoeq_answer(String roeq_answer) {
		this.roeq_answer = roeq_answer;
	}


	/**
	 * @return roe_id
	 */
	public int getRoe_id() {
		return roe_id;
	}

	/**
	 * @param roe_id セットする roe_id
	 */
	public void setRoe_id(int roe_id) {
		this.roe_id = roe_id;
	}
}
