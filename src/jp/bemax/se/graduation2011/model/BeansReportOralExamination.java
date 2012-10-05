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
public class BeansReportOralExamination implements Serializable {
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
	private int roe_id = -1;
	
	/**
	 * 何次試験
	 */
	private String roe_stage = null;
	
	/**
	 * 参加日
	 */
	private String roe_participate = null;
	
	/**
	 * 提出日
	 */
	private String roe_introduction = null;
	
	/**
	 * 面接官人数
	 */
	private int roe_interviewer = -1;
	
	/**
	 * 面接官の名前又は役職
	 */
	private String roe_nameortitle = null;
	
	/**
	 * 他校受験者数
	 */
	private int roe_examother = -1;
	
	/**
	 * 他校受験者学校名
	 */
	private String roe_examotherschool = null;
	
	/**
	 * 当校受験者数
	 */
	private int roe_exam = -1;
	
	/**
	 * ディスカッション
	 */
	private String roe_discussion = null;
	
	/**
	 * ディスカッション人数
	 */
	private int roe_discussionnum = -1;
	
	/**
	 * ディスカッション時間
	 */
	private int roe_discussiontime = -1;
	
	/**
	 * ディスカッションテーマ
	 */
	private String roe_discussiontheme = null;
	
	/**
	 * 合否連絡
	 */
	private String roe_accept = null;
	
	/**
	 * 次回
	 */
	private String roe_nexttime = null;
	
	/**
	 * 次回試験内容
	 */
	private String roe_nextexam = null;
	
	/**
	 * 準備知識・情報
	 */
	private String roe_preparation = null;
	
	/**
	 * 状態
	 */
	private String roe_status = null;
	
	/**
	 * ログインID
	 */
	private String l_id = null;
	
	/**
	 * 企業ID
	 */
	private int comp_id = -1;
	
	/**
	 * 質問内容
	 */
	private ArrayList<BeansReportOralExaminationQuestion> ReportOralExaminationQuestion = null;
	
	
	/*
	 * Constructor
	 */
	/**
	 * Default Constructor
	 */
	public BeansReportOralExamination() {
		super();
	}
	

	/*
	 * method
	 */
	/**
	 * 面接試験報告書を追加します。<br>
	 * 作成には、プロパティroe_stage, roe_participate, roe_introduction, roe_interviewer,
	 * roe_nameortitle, roe_examother, roe_examotherschool, roe_exam, roe_discussion,
	 * roe_discussionnum, roe_discussiontime, roe_discussiontheme, roe_accept,
	 * roe_nexttime, roe_nextexam, roe_preparation, roe_status, l_id, comp_idを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int createReportOralExamination(){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.roe_stage, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_participate, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_introduction, true)){
			return result;
		}
		
		if(!DBCheck.number(this.roe_interviewer, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_nameortitle, false)){
			return result;
		}
		
		if(!DBCheck.number(this.roe_examother, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_examotherschool, false)){
			return result;
		}
		
		if(!DBCheck.number(this.roe_exam, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_discussion, true)){
			return result;
		}
		
		if(!DBCheck.number(this.roe_discussionnum, false)){
			return result;
		}
		
		if(!DBCheck.number(this.roe_discussiontime, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_discussiontheme, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_accept, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_nexttime, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_nextexam, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_preparation, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_status, true)){
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
					"INSERT INTO reportoralexamination(" +
					"roe_stage, roe_participate, roe_introduction, roe_interviewer, roe_nameortitle, " +
					"roe_examother, roe_examotherschool, roe_exam, roe_discussion, roe_discussionnum, " +
					"roe_discussiontime, roe_discussiontheme, roe_accept, roe_nexttime, roe_nextexam, " +
					"roe_preparation, roe_status, l_id, comp_id" +
					") VALUES(" +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
					")"
			);
			
			// 値のセット
			ps.setString(1, this.roe_stage);
			ps.setString(2, this.roe_participate);
			ps.setString(3, this.roe_introduction);
			ps.setInt(4, this.roe_interviewer);
			ps.setString(5, this.roe_nameortitle);
			ps.setInt(6, this.roe_examother);
			ps.setString(7, this.roe_examotherschool);
			ps.setInt(8, this.roe_exam);
			ps.setString(9, this.roe_discussion);
			ps.setInt(10, this.roe_discussionnum);
			ps.setInt(11, this.roe_discussiontime);
			ps.setString(12, this.roe_discussiontheme);
			ps.setString(13, this.roe_accept);
			ps.setString(14, this.roe_nexttime);
			ps.setString(15, this.roe_nextexam);
			ps.setString(16, this.roe_preparation);
			ps.setString(17, this.roe_status);
			ps.setString(18, this.l_id);
			ps.setInt(19, this.comp_id);
			
			// クエリの実行
			result = ps.executeUpdate();
			
			// roe_idの取得
			if(result != -1){
				// 結果セットを保持
				ResultSet rs = null;
				
				ps = db.prepareStatement(
						"SELECT LAST_INSERT_ID() from reportoralexamination"
				);
				
				rs = ps.executeQuery();
				
				if(rs.next()){
					this.roe_id = rs.getInt("LAST_INSERT_ID()");
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
	 * 面接試験報告書を変更します。<br>
	 * 作成には、プロパティroe_id, roe_stage, roe_participate, roe_introduction, roe_interviewer,
	 * roe_nameortitle, roe_examother, roe_examotherschool, roe_exam, roe_discussion,
	 * roe_discussionnum, roe_discussiontime, roe_discussiontheme, roe_accept,
	 * roe_nexttime, roe_nextexam, roe_preparation, roe_status, l_id, comp_idを使用します。
	 * 変更しない情報は現在の情報をセットして呼び出してください。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changeReportOralExamination(){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(this.roe_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_stage, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_participate, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_introduction, true)){
			return result;
		}
		
		if(!DBCheck.number(this.roe_interviewer, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_nameortitle, false)){
			return result;
		}
		
		if(!DBCheck.number(this.roe_examother, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_examotherschool, false)){
			return result;
		}
		
		if(!DBCheck.number(this.roe_exam, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_discussion, true)){
			return result;
		}
		
		if(!DBCheck.number(this.roe_discussionnum, false)){
			return result;
		}
		
		if(!DBCheck.number(this.roe_discussiontime, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_discussiontheme, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_accept, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_nexttime, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_nextexam, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_preparation, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.roe_status, true)){
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
					"UPDATE reportoralexamination " +
					"SET roe_stage = ?, roe_participate = ?, roe_introduction = ?, " +
					"roe_interviewer = ?, roe_nameortitle = ?, roe_examother = ?, " +
					"roe_examotherschool = ?, roe_exam = ?, roe_discussion = ?, " +
					"roe_discussionnum = ?, roe_discussiontime = ?, roe_discussiontheme = ?, " +
					"roe_accept = ?, roe_nexttime = ?, roe_nextexam = ?, roe_preparation = ?, " +
					"roe_status = ?, l_id = ?, comp_id = ? " +
					"WHERE roe_id = ?"
			);
			
			// 値のセット
			ps.setString(1, this.roe_stage);
			ps.setString(2, this.roe_participate);
			ps.setString(3, this.roe_introduction);
			ps.setInt(4, this.roe_interviewer);
			ps.setString(5, this.roe_nameortitle);
			ps.setInt(6, this.roe_examother);
			ps.setString(7, this.roe_examotherschool);
			ps.setInt(8, this.roe_exam);
			ps.setString(9, this.roe_discussion);
			ps.setInt(10, this.roe_discussionnum);
			ps.setInt(11, this.roe_discussiontime);
			ps.setString(12, this.roe_discussiontheme);
			ps.setString(13, this.roe_accept);
			ps.setString(14, this.roe_nexttime);
			ps.setString(15, this.roe_nextexam);
			ps.setString(16, this.roe_preparation);
			ps.setString(17, this.roe_status);
			ps.setString(18, this.l_id);
			ps.setInt(19, this.comp_id);
			ps.setInt(20, this.roe_id);
			
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
	 * 面接試験報告書の状態を変更します。<br>
	 * 作成には、引数roe_id, roe_statusを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int changeStatus(int roe_id, String roe_status){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(roe_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(roe_status, true)){
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
					"UPDATE reportoralexamination " +
					"SET roe_status = ? " +
					"WHERE roe_id = ?"
			);
			
			// 値のセット
			ps.setString(1, roe_status);
			ps.setInt(2, roe_id);
			
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
	 * 面接試験報告書を削除します。
	 * @param roe_id 削除する報告書ID
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int deleteReportOralExamination(int roe_id){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(roe_id, true)){
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
					"DELETE FROM reportoralexamination " +
					"WHERE roe_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, roe_id);
			
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
	 * 面接試験報告書一覧を返します。
	 * @return 成功すれば、BeansReportOralExaminationのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportOralExamination> listReportOralExamination(){
		// 戻り値
		ArrayList<BeansReportOralExamination> result = new ArrayList<BeansReportOralExamination>();

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
					"SELECT * FROM reportoralexamination " +
					"ORDER BY roe_introduction DESC, roe_id DESC"
			);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportOralExamination tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportOralExamination();
				
				// 代入
				tmp.setRoe_id(rs.getInt("roe_id"));
				tmp.setRoe_stage(rs.getString("roe_stage"));
				tmp.setRoe_participate(rs.getString("roe_participate"));
				tmp.setRoe_introduction(rs.getString("roe_introduction"));
				tmp.setRoe_interviewer(rs.getInt("roe_interviewer"));
				tmp.setRoe_nameortitle(rs.getString("roe_nameortitle"));
				tmp.setRoe_examother(rs.getInt("roe_examother"));
				tmp.setRoe_examotherschool(rs.getString("roe_examotherschool"));
				tmp.setRoe_exam(rs.getInt("roe_exam"));
				tmp.setRoe_discussion(rs.getString("roe_discussion"));
				tmp.setRoe_discussionnum(rs.getInt("roe_discussionnum"));
				tmp.setRoe_discussiontime(rs.getInt("roe_discussiontime"));
				tmp.setRoe_discussiontheme(rs.getString("roe_discussiontheme"));
				tmp.setRoe_accept(rs.getString("roe_accept"));
				tmp.setRoe_nexttime(rs.getString("roe_nexttime"));
				tmp.setRoe_nextexam(rs.getString("roe_nextexam"));
				tmp.setRoe_preparation(rs.getString("roe_preparation"));
				tmp.setRoe_status(rs.getString("roe_status"));
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
	 * 検索に一致する面接試験報告書の一覧を返します。
	 * @param roe_status 検索する状態
	 * @return 成功すれば、BeansReportOralExaminationのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportOralExamination> searchReportOralExamination(String roe_status){
		// 戻り値
		ArrayList<BeansReportOralExamination> result = new ArrayList<BeansReportOralExamination>();
		
		// 入力チェック
		if(!DBCheck.vchar(roe_status, true)){
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
					"SELECT * FROM reportoralexamination " +
					"WHERE roe_status like ? " +
					"ORDER BY roe_introduction DESC, roe_id DESC"
			);
			
			// 値のセット
			ps.setString(1, roe_status);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportOralExamination tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportOralExamination();
				
				// 代入
				tmp.setRoe_id(rs.getInt("roe_id"));
				tmp.setRoe_stage(rs.getString("roe_stage"));
				tmp.setRoe_participate(rs.getString("roe_participate"));
				tmp.setRoe_introduction(rs.getString("roe_introduction"));
				tmp.setRoe_interviewer(rs.getInt("roe_interviewer"));
				tmp.setRoe_nameortitle(rs.getString("roe_nameortitle"));
				tmp.setRoe_examother(rs.getInt("roe_examother"));
				tmp.setRoe_examotherschool(rs.getString("roe_examotherschool"));
				tmp.setRoe_exam(rs.getInt("roe_exam"));
				tmp.setRoe_discussion(rs.getString("roe_discussion"));
				tmp.setRoe_discussionnum(rs.getInt("roe_discussionnum"));
				tmp.setRoe_discussiontime(rs.getInt("roe_discussiontime"));
				tmp.setRoe_discussiontheme(rs.getString("roe_discussiontheme"));
				tmp.setRoe_accept(rs.getString("roe_accept"));
				tmp.setRoe_nexttime(rs.getString("roe_nexttime"));
				tmp.setRoe_nextexam(rs.getString("roe_nextexam"));
				tmp.setRoe_preparation(rs.getString("roe_preparation"));
				tmp.setRoe_status(rs.getString("roe_status"));
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
	 * 検索に一致する面接試験報告書の一覧を返します。
	 * @param roe_status 検索する状態
	 * @param comp_id 検索する企業
	 * @param s_id 検索する学科ID
	 * @return 成功すれば、BeansReportOralExaminationのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportOralExamination> searchReportOralExamination(String roe_status, int comp_id, int s_id){
		// 戻り値
		ArrayList<BeansReportOralExamination> result = new ArrayList<BeansReportOralExamination>();
		
		// 入力チェック
		if(!DBCheck.vchar(roe_status, true)){
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
					"SELECT * FROM reportoralexamination " +
					"left join user on reportoralexamination.l_id = user.l_id " +
					"left join course on user.c_id = course.c_id " +
					"left join subject on course.s_id = subject.s_id " +
					"WHERE roe_status like ? and comp_id = ? and subject.s_id = ? " +
					"ORDER BY roe_introduction DESC, roe_id DESC"
			);
			
			// 値のセット
			ps.setString(1, roe_status);
			ps.setInt(2, comp_id);
			ps.setInt(3, s_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportOralExamination tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportOralExamination();
				
				// 代入
				tmp.setRoe_id(rs.getInt("roe_id"));
				tmp.setRoe_stage(rs.getString("roe_stage"));
				tmp.setRoe_participate(rs.getString("roe_participate"));
				tmp.setRoe_introduction(rs.getString("roe_introduction"));
				tmp.setRoe_interviewer(rs.getInt("roe_interviewer"));
				tmp.setRoe_nameortitle(rs.getString("roe_nameortitle"));
				tmp.setRoe_examother(rs.getInt("roe_examother"));
				tmp.setRoe_examotherschool(rs.getString("roe_examotherschool"));
				tmp.setRoe_exam(rs.getInt("roe_exam"));
				tmp.setRoe_discussion(rs.getString("roe_discussion"));
				tmp.setRoe_discussionnum(rs.getInt("roe_discussionnum"));
				tmp.setRoe_discussiontime(rs.getInt("roe_discussiontime"));
				tmp.setRoe_discussiontheme(rs.getString("roe_discussiontheme"));
				tmp.setRoe_accept(rs.getString("roe_accept"));
				tmp.setRoe_nexttime(rs.getString("roe_nexttime"));
				tmp.setRoe_nextexam(rs.getString("roe_nextexam"));
				tmp.setRoe_preparation(rs.getString("roe_preparation"));
				tmp.setRoe_status(rs.getString("roe_status"));
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
	 * 検索に一致する面接試験報告書の一覧を返します。
	 * @param c_id 検索するコースID
	 * @param u_year 検索する学年
	 * @return 成功すれば、BeansReportOralExaminationのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportOralExamination> searchReportOralExamination(int c_id, int u_year){
		// 戻り値
		ArrayList<BeansReportOralExamination> result = new ArrayList<BeansReportOralExamination>();
		
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
					"SELECT * FROM reportoralexamination  left join user " +
					"on reportoralexamination.l_id = user.l_id " +
					"WHERE c_id = ? AND u_year = ? " +
					"ORDER BY roe_introduction DESC, roe_id DESC"
			);
			
			// 値のセット
			ps.setInt(1, c_id);
			ps.setInt(2, u_year);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportOralExamination tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportOralExamination();
				
				// 代入
				tmp.setRoe_id(rs.getInt("roe_id"));
				tmp.setRoe_stage(rs.getString("roe_stage"));
				tmp.setRoe_participate(rs.getString("roe_participate"));
				tmp.setRoe_introduction(rs.getString("roe_introduction"));
				tmp.setRoe_interviewer(rs.getInt("roe_interviewer"));
				tmp.setRoe_nameortitle(rs.getString("roe_nameortitle"));
				tmp.setRoe_examother(rs.getInt("roe_examother"));
				tmp.setRoe_examotherschool(rs.getString("roe_examotherschool"));
				tmp.setRoe_exam(rs.getInt("roe_exam"));
				tmp.setRoe_discussion(rs.getString("roe_discussion"));
				tmp.setRoe_discussionnum(rs.getInt("roe_discussionnum"));
				tmp.setRoe_discussiontime(rs.getInt("roe_discussiontime"));
				tmp.setRoe_discussiontheme(rs.getString("roe_discussiontheme"));
				tmp.setRoe_accept(rs.getString("roe_accept"));
				tmp.setRoe_nexttime(rs.getString("roe_nexttime"));
				tmp.setRoe_nextexam(rs.getString("roe_nextexam"));
				tmp.setRoe_preparation(rs.getString("roe_preparation"));
				tmp.setRoe_status(rs.getString("roe_status"));
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
	 * 検索に一致する面接試験報告書の一覧を返します。
	 * @param c_id 検索する企業ID
	 * @return 成功すれば、BeansReportOralExaminationのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportOralExamination> searchReportOralExamination(int comp_id){
		// 戻り値
		ArrayList<BeansReportOralExamination> result = new ArrayList<BeansReportOralExamination>();
		
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
					"SELECT * FROM reportoralexamination  left join user " +
					"on reportoralexamination.l_id = user.l_id " +
					"WHERE comp_id = ? " +
					"ORDER BY roe_introduction DESC, roe_id DESC"
			);
			
			// 値のセット
			ps.setInt(1, comp_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportOralExamination tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportOralExamination();
				
				// 代入
				tmp.setRoe_id(rs.getInt("roe_id"));
				tmp.setRoe_stage(rs.getString("roe_stage"));
				tmp.setRoe_participate(rs.getString("roe_participate"));
				tmp.setRoe_introduction(rs.getString("roe_introduction"));
				tmp.setRoe_interviewer(rs.getInt("roe_interviewer"));
				tmp.setRoe_nameortitle(rs.getString("roe_nameortitle"));
				tmp.setRoe_examother(rs.getInt("roe_examother"));
				tmp.setRoe_examotherschool(rs.getString("roe_examotherschool"));
				tmp.setRoe_exam(rs.getInt("roe_exam"));
				tmp.setRoe_discussion(rs.getString("roe_discussion"));
				tmp.setRoe_discussionnum(rs.getInt("roe_discussionnum"));
				tmp.setRoe_discussiontime(rs.getInt("roe_discussiontime"));
				tmp.setRoe_discussiontheme(rs.getString("roe_discussiontheme"));
				tmp.setRoe_accept(rs.getString("roe_accept"));
				tmp.setRoe_nexttime(rs.getString("roe_nexttime"));
				tmp.setRoe_nextexam(rs.getString("roe_nextexam"));
				tmp.setRoe_preparation(rs.getString("roe_preparation"));
				tmp.setRoe_status(rs.getString("roe_status"));
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
	 * 指定された面接試験報告書IDの情報を返します。
	 * @param roe_id 詳細を取得する報告書ID
	 * @return 成功すれば、BeansReportOralExaminationが戻ります。
	 */
	public static BeansReportOralExamination detailReportOralExamination(int roe_id){
		// 戻り値
		BeansReportOralExamination result = new BeansReportOralExamination();
		
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
					"SELECT * FROM reportoralexamination " +
					"WHERE roe_id = ? " +
					"ORDER BY roe_introduction DESC, roe_id DESC"
			);
			
			// 値のセット
			ps.setInt(1, roe_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// データの読み取り
			while(rs.next()){
				// 代入
				result.setRoe_id(rs.getInt("roe_id"));
				result.setRoe_stage(rs.getString("roe_stage"));
				result.setRoe_participate(rs.getString("roe_participate"));
				result.setRoe_introduction(rs.getString("roe_introduction"));
				result.setRoe_interviewer(rs.getInt("roe_interviewer"));
				result.setRoe_nameortitle(rs.getString("roe_nameortitle"));
				result.setRoe_examother(rs.getInt("roe_examother"));
				result.setRoe_examotherschool(rs.getString("roe_examotherschool"));
				result.setRoe_exam(rs.getInt("roe_exam"));
				result.setRoe_discussion(rs.getString("roe_discussion"));
				result.setRoe_discussionnum(rs.getInt("roe_discussionnum"));
				result.setRoe_discussiontime(rs.getInt("roe_discussiontime"));
				result.setRoe_discussiontheme(rs.getString("roe_discussiontheme"));
				result.setRoe_accept(rs.getString("roe_accept"));
				result.setRoe_nexttime(rs.getString("roe_nexttime"));
				result.setRoe_nextexam(rs.getString("roe_nextexam"));
				result.setRoe_preparation(rs.getString("roe_preparation"));
				result.setRoe_status(rs.getString("roe_status"));
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


	/**
	 * @return roe_stage
	 */
	public String getRoe_stage() {
		return roe_stage;
	}

	/**
	 * @param roe_stage セットする roe_stage
	 */
	public void setRoe_stage(String roe_stage) {
		this.roe_stage = roe_stage;
	}


	/**
	 * @return roe_participate
	 */
	public String getRoe_participate() {
		return roe_participate;
	}

	/**
	 * @param roe_participate セットする roe_participate
	 */
	public void setRoe_participate(String roe_participate) {
		this.roe_participate = roe_participate;
	}


	/**
	 * @return roe_introduction
	 */
	public String getRoe_introduction() {
		return roe_introduction;
	}

	/**
	 * @param roe_introduction セットする roe_introduction
	 */
	public void setRoe_introduction(String roe_introduction) {
		this.roe_introduction = roe_introduction;
	}


	/**
	 * @return roe_interviewer
	 */
	public int getRoe_interviewer() {
		return roe_interviewer;
	}

	/**
	 * @param roe_interviewer セットする roe_interviewer
	 */
	public void setRoe_interviewer(int roe_interviewer) {
		this.roe_interviewer = roe_interviewer;
	}


	/**
	 * @return roe_nameortitle
	 */
	public String getRoe_nameortitle() {
		return roe_nameortitle;
	}

	/**
	 * @param roe_nameortitle セットする roe_nameortitle
	 */
	public void setRoe_nameortitle(String roe_nameortitle) {
		this.roe_nameortitle = roe_nameortitle;
	}


	/**
	 * @return roe_examother
	 */
	public int getRoe_examother() {
		return roe_examother;
	}

	/**
	 * @param roe_examother セットする roe_examother
	 */
	public void setRoe_examother(int roe_examother) {
		this.roe_examother = roe_examother;
	}


	/**
	 * @return roe_examotherschool
	 */
	public String getRoe_examotherschool() {
		return roe_examotherschool;
	}

	/**
	 * @param roe_examotherschool セットする roe_examotherschool
	 */
	public void setRoe_examotherschool(String roe_examotherschool) {
		this.roe_examotherschool = roe_examotherschool;
	}


	/**
	 * @return roe_exam
	 */
	public int getRoe_exam() {
		return roe_exam;
	}

	/**
	 * @param roe_exam セットする roe_exam
	 */
	public void setRoe_exam(int roe_exam) {
		this.roe_exam = roe_exam;
	}


	/**
	 * @return roe_discussion
	 */
	public String getRoe_discussion() {
		return roe_discussion;
	}

	/**
	 * @param roe_discussion セットする roe_discussion
	 */
	public void setRoe_discussion(String roe_discussion) {
		this.roe_discussion = roe_discussion;
	}


	/**
	 * @return roe_discussionnum
	 */
	public int getRoe_discussionnum() {
		return roe_discussionnum;
	}

	/**
	 * @param roe_discussionnum セットする roe_discussionnum
	 */
	public void setRoe_discussionnum(int roe_discussionnum) {
		this.roe_discussionnum = roe_discussionnum;
	}


	/**
	 * @return roe_discussiontime
	 */
	public int getRoe_discussiontime() {
		return roe_discussiontime;
	}

	/**
	 * @param roe_discussiontime セットする roe_discussiontime
	 */
	public void setRoe_discussiontime(int roe_discussiontime) {
		this.roe_discussiontime = roe_discussiontime;
	}


	/**
	 * @return roe_discussiontheme
	 */
	public String getRoe_discussiontheme() {
		return roe_discussiontheme;
	}

	/**
	 * @param roe_discussiontheme セットする roe_discussiontheme
	 */
	public void setRoe_discussiontheme(String roe_discussiontheme) {
		this.roe_discussiontheme = roe_discussiontheme;
	}


	/**
	 * @return roe_accept
	 */
	public String getRoe_accept() {
		return roe_accept;
	}

	/**
	 * @param roe_accept セットする roe_accept
	 */
	public void setRoe_accept(String roe_accept) {
		this.roe_accept = roe_accept;
	}


	/**
	 * @return roe_nexttime
	 */
	public String getRoe_nexttime() {
		return roe_nexttime;
	}

	/**
	 * @param roe_nexttime セットする roe_nexttime
	 */
	public void setRoe_nexttime(String roe_nexttime) {
		this.roe_nexttime = roe_nexttime;
	}


	/**
	 * @return roe_nextexam
	 */
	public String getRoe_nextexam() {
		return roe_nextexam;
	}

	/**
	 * @param roe_nextexam セットする roe_nextexam
	 */
	public void setRoe_nextexam(String roe_nextexam) {
		this.roe_nextexam = roe_nextexam;
	}


	/**
	 * @return roe_preparation
	 */
	public String getRoe_preparation() {
		return roe_preparation;
	}

	/**
	 * @param roe_preparation セットする roe_preparation
	 */
	public void setRoe_preparation(String roe_preparation) {
		this.roe_preparation = roe_preparation;
	}


	/**
	 * @return roe_status
	 */
	public String getRoe_status() {
		return roe_status;
	}

	/**
	 * @param roe_status セットする roe_status
	 */
	public void setRoe_status(String roe_status) {
		this.roe_status = roe_status;
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
	 * @return reportOralExaminationQuestion
	 */
	public ArrayList<BeansReportOralExaminationQuestion> getReportOralExaminationQuestion() {
		return ReportOralExaminationQuestion;
	}

	/**
	 * @param reportOralExaminationQuestion セットする reportOralExaminationQuestion
	 */
	public void setReportOralExaminationQuestion(
			ArrayList<BeansReportOralExaminationQuestion> reportOralExaminationQuestion) {
		ReportOralExaminationQuestion = reportOralExaminationQuestion;
	}
}
