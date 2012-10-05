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
 * 筆記試験報告書に関するデータベース処理を行うクラス
 * @author Takenaka
 */
public class BeansReportWrittenExamination implements Serializable {
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
	private int rwe_id = -1;
	
	/**
	 * 何次試験
	 */
	private String rwe_stage = null;
	
	/**
	 * 参加日
	 */
	private String rwe_participate = null;
	
	/**
	 * 提出日
	 */
	private String rwe_introduction = null;
	
	/**
	 * 他校受験者人数
	 */
	private int rwe_examother = -1;
	
	/**
	 * 他校受験者学校名
	 */
	private String rwe_examotherschool = null;
	
	/**
	 * 当校受験者人数
	 */
	private int rwe_exam = -1;
	
	/**
	 * 適性試験時間
	 */
	private int rwe_aptitudetime = -1;
	
	/**
	 * 筆記試験時間
	 */
	private int rwe_writtentime = -1;
	
	/**
	 * 作文・論文時間
	 */
	private int rwe_papertime = -1;
	
	/**
	 * 	適性検査内容
	 */
	private String rwe_aptitude = null;
	
	/**
	 * 国語問題内容
	 */
	private String rwe_lang = null;
	
	/**
	 * 数学問題内容
	 */
	private String rwe_math = null;
	
	/**
	 * 英語問題内容
	 */
	private String rwe_english = null;
	
	/**
	 * 理科問題内容
	 */
	private String rwe_science = null;
	
	/**
	 * 社会問題内容
	 */
	private String rwe_society = null;
	
	/**
	 * 政治・経済問題内容
	 */
	private String rwe_politics = null;
	
	/**
	 * 専門的問題内容
	 */
	private String rwe_expert = null;
	
	/**
	 * 作文・論文問題内容
	 */
	private String rwe_paper = null;
	
	/**
	 * その他問題内容
	 */
	private String rwe_other = null;
	
	/**
	 * 合否連絡
	 */
	private String rwe_accept = null;
	
	/**
	 * 次回
	 */
	private String rwe_nexttime = null;
	
	/**
	 * 次回試験内容
	 */
	private String rwe_nextexam = null;
	
	/**
	 * 準備知識・情報
	 */
	private String rwe_preparation = null;
	
	/**
	 * 状態
	 */
	private String rwe_status = null;
	
	/**
	 * ログインID
	 */
	private String l_id = null;
	
	/**
	 * 企業ID
	 */
	private int comp_id = -1;
	
	
	/*
	 * Constructor
	 */
	/**
	 * Default Constructor
	 */
	public BeansReportWrittenExamination() {
		super();
	}
	

	/*
	 * method
	 */
	/**
	 * 筆記試験報告書を追加します。<br>
	 * 作成には、プロパティrwe_stage, rwe_participate, rwe_introduction, rwe_examother,
	 * rwe_examotherschool, rwe_exam, rwe_aptitudetime, rwe_writtentime, rwe_papertime,
	 * rwe_aptitude, rwe_lang, rwe_math, rwe_english, rwe_science, rwe_society, rwe_politics,
	 * rwe_expert, rwe_paper, rwe_other, rwe_accept, rwe_nexttime, rwe_nextexam,
	 * rwe_preparation, rwe_status, l_id, comp_idを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int createReportWrittenExamination(){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.vchar(this.rwe_stage, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_participate, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_introduction, true)){
			return result;
		}
		
		if(!DBCheck.number(this.rwe_examother, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_examotherschool, false)){
			return result;
		}
		
		if(!DBCheck.number(this.rwe_exam, true)){
			return result;
		}
		
		if(!DBCheck.number(this.rwe_aptitudetime, true)){
			return result;
		}
		
		if(!DBCheck.number(this.rwe_writtentime, true)){
			return result;
		}
		
		if(!DBCheck.number(this.rwe_papertime, true)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_aptitude, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_lang, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_math, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_english, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_science, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_society, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_politics, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_expert, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_paper, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_other, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_accept, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_nexttime, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_nextexam, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_preparation, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_status, true)){
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
					"INSERT INTO reportwrittenexamination(" +
					"rwe_stage, rwe_participate, rwe_introduction, rwe_examother, " +
					"rwe_examotherschool, rwe_exam, rwe_aptitudetime, rwe_writtentime, " +
					"rwe_papertime, rwe_aptitude, rwe_lang, rwe_math, rwe_english, " +
					"rwe_science, rwe_society, rwe_politics, rwe_expert, rwe_paper, " +
					"rwe_other, rwe_accept, rwe_nexttime, rwe_nextexam, rwe_preparation, " +
					"rwe_status, l_id, comp_id" +
					") VALUES(" +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
					")"
			);
			
			// 値のセット
			ps.setString(1, this.rwe_stage);
			ps.setString(2, this.rwe_participate);
			ps.setString(3, this.rwe_introduction);
			ps.setInt(4, this.rwe_examother);
			ps.setString(5, this.rwe_examotherschool);
			ps.setInt(6, this.rwe_exam);
			ps.setInt(7, this.rwe_aptitudetime);
			ps.setInt(8, this.rwe_writtentime);
			ps.setInt(9, this.rwe_papertime);
			ps.setString(10, this.rwe_aptitude);
			ps.setString(11, this.rwe_lang);
			ps.setString(12, this.rwe_math);
			ps.setString(13, this.rwe_english);
			ps.setString(14, this.rwe_science);
			ps.setString(15, this.rwe_society);
			ps.setString(16, this.rwe_politics);
			ps.setString(17, this.rwe_expert);
			ps.setString(18, this.rwe_paper);
			ps.setString(19, this.rwe_other);
			ps.setString(20, this.rwe_accept);
			ps.setString(21, this.rwe_nexttime);
			ps.setString(22, this.rwe_nextexam);
			ps.setString(23, this.rwe_preparation);
			ps.setString(24, this.rwe_status);
			ps.setString(25, this.l_id);
			ps.setInt(26, this.comp_id);
			
			// クエリの実行
			result = ps.executeUpdate();
			
			// rwe_idの取得
			if(result != -1){
				// 結果セットを保持
				ResultSet rs = null;
				
				ps = db.prepareStatement(
						"SELECT LAST_INSERT_ID() from reportwrittenexamination"
				);
				
				rs = ps.executeQuery();
				
				if(rs.next()){
					this.rwe_id = rs.getInt("LAST_INSERT_ID()");
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
	 * 筆記試験報告書を変更します。<br>
	 * 作成には、プロパティrwe_id, rwe_stage, rwe_participate, rwe_introduction, rwe_examother,
	 * rwe_examotherschool, rwe_exam, rwe_aptitudetime, rwe_writtentime, rwe_papertime,
	 * rwe_aptitude, rwe_lang, rwe_math, rwe_english, rwe_science, rwe_society, rwe_politics,
	 * rwe_expert, rwe_paper, rwe_other, rwe_accept, rwe_nexttime, rwe_nextexam,
	 * rwe_preparation, rwe_status, l_id, comp_idを使用します。<br>
	 * 変更しない情報は現在の情報をセットして呼び出してください。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public int changeReportWrittenExamination(){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(this.rwe_id, true)){
			return result;
		}
		if(!DBCheck.vchar(this.rwe_stage, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_preparation, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_introduction, true)){
			return result;
		}
		
		if(!DBCheck.number(this.rwe_examother, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_examotherschool, false)){
			return result;
		}
		
		if(!DBCheck.number(this.rwe_exam, true)){
			return result;
		}
		
		if(!DBCheck.number(this.rwe_aptitudetime, true)){
			return result;
		}
		
		if(!DBCheck.number(this.rwe_writtentime, true)){
			return result;
		}
		
		if(!DBCheck.number(this.rwe_papertime, true)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_aptitude, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_lang, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_math, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_english, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_science, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_society, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_politics, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_expert, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_paper, false)){
			return result;
		}
		
		if(!DBCheck.text(this.rwe_other, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_accept, true)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_nexttime, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_nextexam, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_participate, false)){
			return result;
		}
		
		if(!DBCheck.vchar(this.rwe_status, true)){
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
					"UPDATE reportwrittenexamination " +
					"SET rwe_stage = ?, rwe_participate = ?, rwe_introduction = ?, " +
					"rwe_examother = ?, rwe_examotherschool = ?, rwe_exam = ?, " +
					"rwe_aptitudetime = ?, rwe_writtentime = ?, rwe_papertime = ?, " +
					"rwe_aptitude = ?, rwe_lang = ?, rwe_math = ?, rwe_english = ?, " +
					"rwe_science = ?, rwe_society = ?, rwe_politics = ?, rwe_expert = ?, " +
					"rwe_paper = ?, rwe_other = ?, rwe_accept = ?, rwe_nexttime = ?, " +
					"rwe_nextexam = ?, rwe_preparation = ?, rwe_status = ?, l_id = ?, " +
					"comp_id = ? " +
					"WHERE rwe_id = ?"
			);
			
			// 値のセット
			ps.setString(1, this.rwe_stage);
			ps.setString(2, this.rwe_participate);
			ps.setString(3, this.rwe_introduction);
			ps.setInt(4, this.rwe_examother);
			ps.setString(5, this.rwe_examotherschool);
			ps.setInt(6, this.rwe_exam);
			ps.setInt(7, this.rwe_aptitudetime);
			ps.setInt(8, this.rwe_writtentime);
			ps.setInt(9, this.rwe_papertime);
			ps.setString(10, this.rwe_aptitude);
			ps.setString(11, this.rwe_lang);
			ps.setString(12, this.rwe_math);
			ps.setString(13, this.rwe_english);
			ps.setString(14, this.rwe_science);
			ps.setString(15, this.rwe_society);
			ps.setString(16, this.rwe_politics);
			ps.setString(17, this.rwe_expert);
			ps.setString(18, this.rwe_paper);
			ps.setString(19, this.rwe_other);
			ps.setString(20, this.rwe_accept);
			ps.setString(21, this.rwe_nexttime);
			ps.setString(22, this.rwe_nextexam);
			ps.setString(23, this.rwe_preparation);
			ps.setString(24, this.rwe_status);
			ps.setString(25, this.l_id);
			ps.setInt(26, this.comp_id);
			ps.setInt(27, this.rwe_id);
			
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
	 * 筆記試験報告書の状態を変更します。<br>
	 * 作成には、引数rwe_id, rwe_statusを使用します。
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int changeStatus(int rwe_id, String rwe_status){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(rwe_id, true)){
			return result;
		}
		
		if(!DBCheck.vchar(rwe_status, true)){
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
					"UPDATE reportwrittenexamination " +
					"SET rwe_status = ? " +
					"WHERE rwe_id = ?"
			);
			
			// 値のセット
			ps.setString(1, rwe_status);
			ps.setInt(2, rwe_id);
			
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
	 * 筆記試験報告書を削除します。
	 * @param rwe_id 削除する報告書ID
	 * @return 成功すれば、-1以外が戻ります。
	 */
	public static int deleteReportWrittenExamination(int rwe_id){
		// 戻り値
		int result = -1;
		
		// 入力チェック
		if(!DBCheck.number(rwe_id, true)){
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
					"DELETE FROM reportwrittenexamination " +
					"WHERE rwe_id = ?"
			);
			
			// 値のセット
			ps.setInt(1, rwe_id);
			
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
	 * 筆記試験報告書一覧を返します。
	 * @return 成功すれば、BeansReportWrittenExaminationのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportWrittenExamination> listReportWrittenExamination(){
		// 戻り値
		ArrayList<BeansReportWrittenExamination> result = new ArrayList<BeansReportWrittenExamination>();

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
					"SELECT * FROM reportwrittenexamination " +
					"ORDER BY rwe_introduction DESC, rwe_id DESC"
			);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportWrittenExamination tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportWrittenExamination();
				
				// 代入
				tmp.setRwe_id(rs.getInt("rwe_id"));
				tmp.setRwe_stage(rs.getString("rwe_stage"));
				tmp.setRwe_participate(rs.getString("rwe_participate"));
				tmp.setRwe_introduction(rs.getString("rwe_introduction"));
				tmp.setRwe_examother(rs.getInt("rwe_examother"));
				tmp.setRwe_examotherschool(rs.getString("rwe_examotherschool"));
				tmp.setRwe_exam(rs.getInt("rwe_exam"));
				tmp.setRwe_aptitudetime(rs.getInt("rwe_aptitudetime"));
				tmp.setRwe_writtentime(rs.getInt("rwe_writtentime"));
				tmp.setRwe_papertime(rs.getInt("rwe_papertime"));
				tmp.setRwe_aptitude(rs.getString("rwe_aptitude"));
				tmp.setRwe_lang(rs.getString("rwe_lang"));
				tmp.setRwe_math(rs.getString("rwe_math"));
				tmp.setRwe_english(rs.getString("rwe_english"));
				tmp.setRwe_science(rs.getString("rwe_science"));
				tmp.setRwe_society(rs.getString("rwe_society"));
				tmp.setRwe_politics(rs.getString("rwe_politics"));
				tmp.setRwe_expert(rs.getString("rwe_expert"));
				tmp.setRwe_paper(rs.getString("rwe_paper"));
				tmp.setRwe_other(rs.getString("rwe_other"));
				tmp.setRwe_accept(rs.getString("rwe_accept"));
				tmp.setRwe_nexttime(rs.getString("rwe_nexttime"));
				tmp.setRwe_nextexam(rs.getString("rwe_nextexam"));
				tmp.setRwe_preparation(rs.getString("rwe_preparation"));
				tmp.setRwe_status(rs.getString("rwe_status"));
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
	 * 検索に一致する筆記試験報告書の一覧を返します。
	 * @param rwe_status 検索する状態
	 * @return 成功すれば、BeansReportWrittenExaminationのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportWrittenExamination> searchReportWrittenExamination(String rwe_status){
		// 戻り値
		ArrayList<BeansReportWrittenExamination> result = new ArrayList<BeansReportWrittenExamination>();
		
		// 入力チェック
		if(!DBCheck.vchar(rwe_status, true)){
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
					"SELECT * FROM reportwrittenexamination " +
					"WHERE rwe_status = ? " +
					"ORDER BY rwe_introduction DESC, rwe_id DESC"
			);
			
			// 値のセット
			ps.setString(1, rwe_status);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportWrittenExamination tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportWrittenExamination();
				
				// 代入
				tmp.setRwe_id(rs.getInt("rwe_id"));
				tmp.setRwe_stage(rs.getString("rwe_stage"));
				tmp.setRwe_participate(rs.getString("rwe_participate"));
				tmp.setRwe_introduction(rs.getString("rwe_introduction"));
				tmp.setRwe_examother(rs.getInt("rwe_examother"));
				tmp.setRwe_examotherschool(rs.getString("rwe_examotherschool"));
				tmp.setRwe_exam(rs.getInt("rwe_exam"));
				tmp.setRwe_aptitudetime(rs.getInt("rwe_aptitudetime"));
				tmp.setRwe_writtentime(rs.getInt("rwe_writtentime"));
				tmp.setRwe_papertime(rs.getInt("rwe_papertime"));
				tmp.setRwe_aptitude(rs.getString("rwe_aptitude"));
				tmp.setRwe_lang(rs.getString("rwe_lang"));
				tmp.setRwe_math(rs.getString("rwe_math"));
				tmp.setRwe_english(rs.getString("rwe_english"));
				tmp.setRwe_science(rs.getString("rwe_science"));
				tmp.setRwe_society(rs.getString("rwe_society"));
				tmp.setRwe_politics(rs.getString("rwe_politics"));
				tmp.setRwe_expert(rs.getString("rwe_expert"));
				tmp.setRwe_paper(rs.getString("rwe_paper"));
				tmp.setRwe_other(rs.getString("rwe_other"));
				tmp.setRwe_accept(rs.getString("rwe_accept"));
				tmp.setRwe_nexttime(rs.getString("rwe_nexttime"));
				tmp.setRwe_nextexam(rs.getString("rwe_nextexam"));
				tmp.setRwe_preparation(rs.getString("rwe_preparation"));
				tmp.setRwe_status(rs.getString("rwe_status"));
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
	 * 検索に一致する筆記試験報告書の一覧を返します。
	 * @param rwe_status 検索する状態
	 * @param comp_id 検索する企業
	 * @param s_id 検索する学科ID
	 * @return 成功すれば、BeansReportWrittenExaminationのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportWrittenExamination> searchReportWrittenExamination(String rwe_status, int comp_id, int s_id){
		// 戻り値
		ArrayList<BeansReportWrittenExamination> result = new ArrayList<BeansReportWrittenExamination>();
		
		// 入力チェック
		if(!DBCheck.vchar(rwe_status, true)){
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
					"SELECT * FROM reportwrittenexamination " +
					"left join user on reportwrittenexamination.l_id = user.l_id " +
					"left join course on user.c_id = course.c_id " +
					"left join subject on course.s_id = subject.s_id " +
					"WHERE rwe_status = ? and comp_id = ? and subject.s_id = ? " +
					"ORDER BY rwe_introduction DESC, rwe_id DESC"
			);
			
			// 値のセット
			ps.setString(1, rwe_status);
			ps.setInt(2, comp_id);
			ps.setInt(3, s_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportWrittenExamination tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportWrittenExamination();
				
				// 代入
				tmp.setRwe_id(rs.getInt("rwe_id"));
				tmp.setRwe_stage(rs.getString("rwe_stage"));
				tmp.setRwe_participate(rs.getString("rwe_participate"));
				tmp.setRwe_introduction(rs.getString("rwe_introduction"));
				tmp.setRwe_examother(rs.getInt("rwe_examother"));
				tmp.setRwe_examotherschool(rs.getString("rwe_examotherschool"));
				tmp.setRwe_exam(rs.getInt("rwe_exam"));
				tmp.setRwe_aptitudetime(rs.getInt("rwe_aptitudetime"));
				tmp.setRwe_writtentime(rs.getInt("rwe_writtentime"));
				tmp.setRwe_papertime(rs.getInt("rwe_papertime"));
				tmp.setRwe_aptitude(rs.getString("rwe_aptitude"));
				tmp.setRwe_lang(rs.getString("rwe_lang"));
				tmp.setRwe_math(rs.getString("rwe_math"));
				tmp.setRwe_english(rs.getString("rwe_english"));
				tmp.setRwe_science(rs.getString("rwe_science"));
				tmp.setRwe_society(rs.getString("rwe_society"));
				tmp.setRwe_politics(rs.getString("rwe_politics"));
				tmp.setRwe_expert(rs.getString("rwe_expert"));
				tmp.setRwe_paper(rs.getString("rwe_paper"));
				tmp.setRwe_other(rs.getString("rwe_other"));
				tmp.setRwe_accept(rs.getString("rwe_accept"));
				tmp.setRwe_nexttime(rs.getString("rwe_nexttime"));
				tmp.setRwe_nextexam(rs.getString("rwe_nextexam"));
				tmp.setRwe_preparation(rs.getString("rwe_preparation"));
				tmp.setRwe_status(rs.getString("rwe_status"));
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
	 * 検索に一致する筆記試験報告書の一覧を返します。
	 * @param c_id 検索するコースID
	 * @param u_year 検索する学年
	 * @return 成功すれば、BeansReportWrittenExaminationのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportWrittenExamination> searchReportWrittenExamination(int c_id, int u_year){
		// 戻り値
		ArrayList<BeansReportWrittenExamination> result = new ArrayList<BeansReportWrittenExamination>();
		
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
					"SELECT * FROM reportwrittenexamination  left join user " +
					"on reportwrittenexamination.l_id = user.l_id " +
					"WHERE c_id = ? AND u_year = ? " +
					"ORDER BY rwe_introduction DESC, rwe_id DESC"
			);
			
			// 値のセット
			ps.setInt(1, c_id);
			ps.setInt(2, u_year);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportWrittenExamination tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportWrittenExamination();
				
				// 代入
				tmp.setRwe_id(rs.getInt("rwe_id"));
				tmp.setRwe_stage(rs.getString("rwe_stage"));
				tmp.setRwe_participate(rs.getString("rwe_participate"));
				tmp.setRwe_introduction(rs.getString("rwe_introduction"));
				tmp.setRwe_examother(rs.getInt("rwe_examother"));
				tmp.setRwe_examotherschool(rs.getString("rwe_examotherschool"));
				tmp.setRwe_exam(rs.getInt("rwe_exam"));
				tmp.setRwe_aptitudetime(rs.getInt("rwe_aptitudetime"));
				tmp.setRwe_writtentime(rs.getInt("rwe_writtentime"));
				tmp.setRwe_papertime(rs.getInt("rwe_papertime"));
				tmp.setRwe_aptitude(rs.getString("rwe_aptitude"));
				tmp.setRwe_lang(rs.getString("rwe_lang"));
				tmp.setRwe_math(rs.getString("rwe_math"));
				tmp.setRwe_english(rs.getString("rwe_english"));
				tmp.setRwe_science(rs.getString("rwe_science"));
				tmp.setRwe_society(rs.getString("rwe_society"));
				tmp.setRwe_politics(rs.getString("rwe_politics"));
				tmp.setRwe_expert(rs.getString("rwe_expert"));
				tmp.setRwe_paper(rs.getString("rwe_paper"));
				tmp.setRwe_other(rs.getString("rwe_other"));
				tmp.setRwe_accept(rs.getString("rwe_accept"));
				tmp.setRwe_nexttime(rs.getString("rwe_nexttime"));
				tmp.setRwe_nextexam(rs.getString("rwe_nextexam"));
				tmp.setRwe_preparation(rs.getString("rwe_preparation"));
				tmp.setRwe_status(rs.getString("rwe_status"));
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
	 * 検索に一致する筆記試験報告書の一覧を返します。
	 * @param c_id 検索する企業ID
	 * @return 成功すれば、BeansReportWrittenExaminationのArrayListが戻ります。
	 */
	public static ArrayList<BeansReportWrittenExamination> searchReportWrittenExamination(int comp_id){
		// 戻り値
		ArrayList<BeansReportWrittenExamination> result = new ArrayList<BeansReportWrittenExamination>();
		
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
					"SELECT * FROM reportwrittenexamination  left join user " +
					"on reportwrittenexamination.l_id = user.l_id " +
					"WHERE comp_id = ? " +
					"ORDER BY rwe_introduction DESC, rwe_id DESC"
			);
			
			// 値のセット
			ps.setInt(1, comp_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// 一時クラス
			BeansReportWrittenExamination tmp;
			
			// データの読み取り
			while(rs.next()){
				// データを生成
				tmp = new BeansReportWrittenExamination();
				
				// 代入
				tmp.setRwe_id(rs.getInt("rwe_id"));
				tmp.setRwe_stage(rs.getString("rwe_stage"));
				tmp.setRwe_participate(rs.getString("rwe_participate"));
				tmp.setRwe_introduction(rs.getString("rwe_introduction"));
				tmp.setRwe_examother(rs.getInt("rwe_examother"));
				tmp.setRwe_examotherschool(rs.getString("rwe_examotherschool"));
				tmp.setRwe_exam(rs.getInt("rwe_exam"));
				tmp.setRwe_aptitudetime(rs.getInt("rwe_aptitudetime"));
				tmp.setRwe_writtentime(rs.getInt("rwe_writtentime"));
				tmp.setRwe_papertime(rs.getInt("rwe_papertime"));
				tmp.setRwe_aptitude(rs.getString("rwe_aptitude"));
				tmp.setRwe_lang(rs.getString("rwe_lang"));
				tmp.setRwe_math(rs.getString("rwe_math"));
				tmp.setRwe_english(rs.getString("rwe_english"));
				tmp.setRwe_science(rs.getString("rwe_science"));
				tmp.setRwe_society(rs.getString("rwe_society"));
				tmp.setRwe_politics(rs.getString("rwe_politics"));
				tmp.setRwe_expert(rs.getString("rwe_expert"));
				tmp.setRwe_paper(rs.getString("rwe_paper"));
				tmp.setRwe_other(rs.getString("rwe_other"));
				tmp.setRwe_accept(rs.getString("rwe_accept"));
				tmp.setRwe_nexttime(rs.getString("rwe_nexttime"));
				tmp.setRwe_nextexam(rs.getString("rwe_nextexam"));
				tmp.setRwe_preparation(rs.getString("rwe_preparation"));
				tmp.setRwe_status(rs.getString("rwe_status"));
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
	 * 指定された筆記試験報告書IDの情報を返します。
	 * @param rwe_id 詳細を取得する報告書ID
	 * @return 成功すれば、BeansReportWrittenExaminationが戻ります。
	 */
	public static BeansReportWrittenExamination detailReportWrittenExamination(int rwe_id){
		// 戻り値
		BeansReportWrittenExamination result = new BeansReportWrittenExamination();
		
		// 入力チェック
		if(!DBCheck.number(rwe_id, true)){
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
					"SELECT * FROM reportwrittenexamination " +
					"WHERE rwe_id = ? " +
					"ORDER BY rwe_introduction DESC, rwe_id DESC"
			);
			
			// 値のセット
			ps.setInt(1, rwe_id);
			
			// クエリの実行
			rs = ps.executeQuery();
			
			// データの読み取り
			while(rs.next()){
				// 代入
				result.setRwe_id(rs.getInt("rwe_id"));
				result.setRwe_stage(rs.getString("rwe_stage"));
				result.setRwe_participate(rs.getString("rwe_participate"));
				result.setRwe_introduction(rs.getString("rwe_introduction"));
				result.setRwe_examother(rs.getInt("rwe_examother"));
				result.setRwe_examotherschool(rs.getString("rwe_examotherschool"));
				result.setRwe_exam(rs.getInt("rwe_exam"));
				result.setRwe_aptitudetime(rs.getInt("rwe_aptitudetime"));
				result.setRwe_writtentime(rs.getInt("rwe_writtentime"));
				result.setRwe_papertime(rs.getInt("rwe_papertime"));
				result.setRwe_aptitude(rs.getString("rwe_aptitude"));
				result.setRwe_lang(rs.getString("rwe_lang"));
				result.setRwe_math(rs.getString("rwe_math"));
				result.setRwe_english(rs.getString("rwe_english"));
				result.setRwe_science(rs.getString("rwe_science"));
				result.setRwe_society(rs.getString("rwe_society"));
				result.setRwe_politics(rs.getString("rwe_politics"));
				result.setRwe_expert(rs.getString("rwe_expert"));
				result.setRwe_paper(rs.getString("rwe_paper"));
				result.setRwe_other(rs.getString("rwe_other"));
				result.setRwe_accept(rs.getString("rwe_accept"));
				result.setRwe_nexttime(rs.getString("rwe_nexttime"));
				result.setRwe_nextexam(rs.getString("rwe_nextexam"));
				result.setRwe_preparation(rs.getString("rwe_preparation"));
				result.setRwe_status(rs.getString("rwe_status"));
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
	 * @return rwe_id
	 */
	public int getRwe_id() {
		return rwe_id;
	}

	/**
	 * @param rwe_id セットする rwe_id
	 */
	public void setRwe_id(int rwe_id) {
		this.rwe_id = rwe_id;
	}


	/**
	 * @return rwe_stage
	 */
	public String getRwe_stage() {
		return rwe_stage;
	}

	/**
	 * @param rwe_stage セットする rwe_stage
	 */
	public void setRwe_stage(String rwe_stage) {
		this.rwe_stage = rwe_stage;
	}


	/**
	 * @return rwe_participate
	 */
	public String getRwe_participate() {
		return rwe_participate;
	}

	/**
	 * @param rwe_participate セットする rwe_participate
	 */
	public void setRwe_participate(String rwe_participate) {
		this.rwe_participate = rwe_participate;
	}


	/**
	 * @return rwe_introduction
	 */
	public String getRwe_introduction() {
		return rwe_introduction;
	}

	/**
	 * @param rwe_introduction セットする rwe_introduction
	 */
	public void setRwe_introduction(String rwe_introduction) {
		this.rwe_introduction = rwe_introduction;
	}


	/**
	 * @return rwe_examother
	 */
	public int getRwe_examother() {
		return rwe_examother;
	}

	/**
	 * @param rwe_examother セットする rwe_examother
	 */
	public void setRwe_examother(int rwe_examother) {
		this.rwe_examother = rwe_examother;
	}


	/**
	 * @return rwe_examotherschool
	 */
	public String getRwe_examotherschool() {
		return rwe_examotherschool;
	}

	/**
	 * @param rwe_examotherschool セットする rwe_examotherschool
	 */
	public void setRwe_examotherschool(String rwe_examotherschool) {
		this.rwe_examotherschool = rwe_examotherschool;
	}


	/**
	 * @return rwe_exam
	 */
	public int getRwe_exam() {
		return rwe_exam;
	}

	/**
	 * @param rwe_exam セットする rwe_exam
	 */
	public void setRwe_exam(int rwe_exam) {
		this.rwe_exam = rwe_exam;
	}


	/**
	 * @return rwe_aptitudetime
	 */
	public int getRwe_aptitudetime() {
		return rwe_aptitudetime;
	}

	/**
	 * @param rwe_aptitudetime セットする rwe_aptitudetime
	 */
	public void setRwe_aptitudetime(int rwe_aptitudetime) {
		this.rwe_aptitudetime = rwe_aptitudetime;
	}


	/**
	 * @return rwe_writtentime
	 */
	public int getRwe_writtentime() {
		return rwe_writtentime;
	}

	/**
	 * @param rwe_writtentime セットする rwe_writtentime
	 */
	public void setRwe_writtentime(int rwe_writtentime) {
		this.rwe_writtentime = rwe_writtentime;
	}


	/**
	 * @return rwe_papertime
	 */
	public int getRwe_papertime() {
		return rwe_papertime;
	}

	/**
	 * @param rwe_papertime セットする rwe_papertime
	 */
	public void setRwe_papertime(int rwe_papertime) {
		this.rwe_papertime = rwe_papertime;
	}


	/**
	 * @return rwe_aptitude
	 */
	public String getRwe_aptitude() {
		return rwe_aptitude;
	}

	/**
	 * @param rwe_aptitude セットする rwe_aptitude
	 */
	public void setRwe_aptitude(String rwe_aptitude) {
		this.rwe_aptitude = rwe_aptitude;
	}


	/**
	 * @return rwe_lang
	 */
	public String getRwe_lang() {
		return rwe_lang;
	}

	/**
	 * @param rwe_lang セットする rwe_lang
	 */
	public void setRwe_lang(String rwe_lang) {
		this.rwe_lang = rwe_lang;
	}


	/**
	 * @return rwe_math
	 */
	public String getRwe_math() {
		return rwe_math;
	}

	/**
	 * @param rwe_math セットする rwe_math
	 */
	public void setRwe_math(String rwe_math) {
		this.rwe_math = rwe_math;
	}


	/**
	 * @return rwe_english
	 */
	public String getRwe_english() {
		return rwe_english;
	}

	/**
	 * @param rwe_english セットする rwe_english
	 */
	public void setRwe_english(String rwe_english) {
		this.rwe_english = rwe_english;
	}


	/**
	 * @return rwe_science
	 */
	public String getRwe_science() {
		return rwe_science;
	}

	/**
	 * @param rwe_science セットする rwe_science
	 */
	public void setRwe_science(String rwe_science) {
		this.rwe_science = rwe_science;
	}


	/**
	 * @return rwe_society
	 */
	public String getRwe_society() {
		return rwe_society;
	}

	/**
	 * @param rwe_society セットする rwe_society
	 */
	public void setRwe_society(String rwe_society) {
		this.rwe_society = rwe_society;
	}


	/**
	 * @return rwe_politics
	 */
	public String getRwe_politics() {
		return rwe_politics;
	}

	/**
	 * @param rwe_politics セットする rwe_politics
	 */
	public void setRwe_politics(String rwe_politics) {
		this.rwe_politics = rwe_politics;
	}


	/**
	 * @return rwe_expert
	 */
	public String getRwe_expert() {
		return rwe_expert;
	}

	/**
	 * @param rwe_expert セットする rwe_expert
	 */
	public void setRwe_expert(String rwe_expert) {
		this.rwe_expert = rwe_expert;
	}


	/**
	 * @return rwe_paper
	 */
	public String getRwe_paper() {
		return rwe_paper;
	}

	/**
	 * @param rwe_paper セットする rwe_paper
	 */
	public void setRwe_paper(String rwe_paper) {
		this.rwe_paper = rwe_paper;
	}


	/**
	 * @return rwe_other
	 */
	public String getRwe_other() {
		return rwe_other;
	}

	/**
	 * @param rwe_other セットする rwe_other
	 */
	public void setRwe_other(String rwe_other) {
		this.rwe_other = rwe_other;
	}


	/**
	 * @return rwe_accept
	 */
	public String getRwe_accept() {
		return rwe_accept;
	}

	/**
	 * @param rwe_accept セットする rwe_accept
	 */
	public void setRwe_accept(String rwe_accept) {
		this.rwe_accept = rwe_accept;
	}


	/**
	 * @return rwe_nexttime
	 */
	public String getRwe_nexttime() {
		return rwe_nexttime;
	}

	/**
	 * @param rwe_nexttime セットする rwe_nexttime
	 */
	public void setRwe_nexttime(String rwe_nexttime) {
		this.rwe_nexttime = rwe_nexttime;
	}


	/**
	 * @return rwe_nextexam
	 */
	public String getRwe_nextexam() {
		return rwe_nextexam;
	}

	/**
	 * @param rwe_nextexam セットする rwe_nextexam
	 */
	public void setRwe_nextexam(String rwe_nextexam) {
		this.rwe_nextexam = rwe_nextexam;
	}


	/**
	 * @return rwe_preparation
	 */
	public String getRwe_preparation() {
		return rwe_preparation;
	}

	/**
	 * @param rwe_preparation セットする rwe_preparation
	 */
	public void setRwe_preparation(String rwe_preparation) {
		this.rwe_preparation = rwe_preparation;
	}


	/**
	 * @return rwe_status
	 */
	public String getRwe_status() {
		return rwe_status;
	}

	/**
	 * @param rwe_status セットする rwe_status
	 */
	public void setRwe_status(String rwe_status) {
		this.rwe_status = rwe_status;
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
}
