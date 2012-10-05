/**
 * 
 */
package jp.bemax.se.graduation2011.auth;

import java.util.ArrayList;
import java.util.ListIterator;

import jp.bemax.se.graduation2011.model.BeansCompany;
import jp.bemax.se.graduation2011.model.BeansCompanyTrade;
import jp.bemax.se.graduation2011.model.BeansCourse;
import jp.bemax.se.graduation2011.model.BeansReportJoinMeeting;
import jp.bemax.se.graduation2011.model.BeansUser;

/**
 * @author masaya0909
 *
 */
public class JoinMeetingInfo {
	
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
	 * 企業名
	 */
	private String comp_name = null;
	
	/**
	 * 状態
	 */
	private String rjm_status = null;
	
	/**
	 * 報告書番号
	 */
	private int rjm_num = -1;
	
	/**
	 * 登録者名
	 */
	private String u_name = null;

	/**
	 * コース名
	 */
	private String c_name = null;
	
	/**
	 * リスト(状態がend)
	 */
	private ArrayList<JoinMeetingInfo> list = null;
	
	/**
	 * リスト(状態がend以外)
	 */
	private ArrayList<JoinMeetingInfo> list2 = null;
	
	/**
	 * 状態がnewのもののみのカウンタ
	 */
	private int n_count = 0;
	
	/**
	 * 状態がrenewのもののみのカウンタ
	 */
	private int r_count = 0;
	
	/**
	 * 状態がagainのもののみのカウンタ
	 */
	private int a_count = 0;
	
	
	/**
	 * コンストラクタ
	 */
	public JoinMeetingInfo(){
		super();
	}
	
	/**
	 * 合同説明会報告書の一覧ページに必要な情報を返します。
	 * 学生には報告書がendのもののみ見れるようにする。
	 * @param ArrayList<BeansReportJoinMeeting> 合同説明会報告書一覧
	 * @return ArrayList<JoinMeetingInfo> 合同説明会報告書一覧（企業名入）
	 */
	public static ArrayList<JoinMeetingInfo> listJoinMeetingInfo(ArrayList<BeansReportJoinMeeting> array){
	
		ListIterator<BeansReportJoinMeeting> iterator = array.listIterator();
		ArrayList<JoinMeetingInfo> list = new ArrayList<JoinMeetingInfo>();
		
		// 一時クラス
		JoinMeetingInfo tmp = null;
		BeansCompany bc = null;
		BeansCompanyTrade ct = null;
		
		// データの読み取り
		while(iterator.hasNext()){
			// データを生成
			BeansReportJoinMeeting data = iterator.next();
			bc = BeansCompany.detailCompany(data.getComp_id());
			ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
			tmp = new JoinMeetingInfo();
			
			if(data.getRjm_status().equals("end")){
			
				// 代入
				tmp.setRjm_id(data.getRjm_id());
				tmp.setRjm_introduction(data.getRjm_introduction());
				tmp.setRjm_participate(data.getRjm_participate());
				tmp.setRjm_name(data.getRjm_name());
				
				//商号を追加
				if(bc.getCompt_position().equals("first")){
					tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
				}else{
					tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
				}
				// データをリストに追加
				list.add(tmp);
			}
		}
		return list;
	}
		
	/**
	 * 報告書がendのもののみ新着３件表示。
	 * @param ArrayList<BeansReportJoinMeeting> 合同説明会報告書一覧
	 * @return ArrayList<JoinMeetingInfo> 合同説明会報告書一覧（企業名入）
	 */
	public static ArrayList<JoinMeetingInfo> newListJoinMeetingInfo(ArrayList<BeansReportJoinMeeting> array){
	
		ListIterator<BeansReportJoinMeeting> iterator = array.listIterator();
		ArrayList<JoinMeetingInfo> list = new ArrayList<JoinMeetingInfo>();
		
		// 一時クラス
		JoinMeetingInfo tmp = null;
		BeansCompany bc = null;
		BeansCompanyTrade ct = null;
		int count = 0;
		
			
		// データの読み取り
		while(iterator.hasNext()){
			
			if(count < 3){
				
				// データを生成
				BeansReportJoinMeeting data = iterator.next();
				bc = BeansCompany.detailCompany(data.getComp_id());
				ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
				BeansUser bu = BeansUser.detailUser(data.getL_id());
				BeansCourse course = BeansCourse.detailCourse(bu.getC_id());
				tmp = new JoinMeetingInfo();
	
				if(data.getRjm_status().equals("end")){
					// 代入
					tmp.setRjm_id(data.getRjm_id());
					tmp.setRjm_introduction(data.getRjm_introduction());
					tmp.setRjm_participate(data.getRjm_participate());
					tmp.setRjm_name(data.getRjm_name());
					tmp.setU_name(bu.getU_name());
					tmp.setC_name(course.getC_name());
					tmp.setRjm_status(data.getRjm_status());
					count++;
					
					//商号を追加
					if(bc.getCompt_position().equals("first")){
						tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
					}else{
						tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
					}	
					// データをリストに追加
					list.add(tmp);
				}
			}
			
			if(count == 3){
				break;
			}
		}
		return list;
	}
	

	/**
	 * 合同説明会報告書の一覧ページに必要な個人の情報を返します。
	 * @param ArrayList<BeansReportJoinMeeting> 合同説明会報告書一覧
	 * @return ArrayList<JoinMeetingInfo> 合同説明会報告書一覧（企業名入）
	 */
	public static ArrayList<JoinMeetingInfo> listJoinMeetingMyInfo(ArrayList<BeansReportJoinMeeting> array , String l_id){
	
		ListIterator<BeansReportJoinMeeting> iterator = array.listIterator();
		ArrayList<JoinMeetingInfo> list = new ArrayList<JoinMeetingInfo>();
		
		// 一時クラス
		JoinMeetingInfo tmp = null;
		BeansCompany bc = null;
		BeansCompanyTrade ct = null;
		
		// データの読み取り
		while(iterator.hasNext()){
			// データを生成
			BeansReportJoinMeeting data = iterator.next();
			String id = data.getL_id();
			
			if(id.equals(l_id)){
			
				bc = BeansCompany.detailCompany(data.getComp_id());
				ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
				tmp = new JoinMeetingInfo();
			
				// 代入
				tmp.setRjm_id(data.getRjm_id());
				tmp.setRjm_introduction(data.getRjm_introduction());
				tmp.setRjm_participate(data.getRjm_participate());
				tmp.setRjm_name(data.getRjm_name());
				tmp.setRjm_num(1);
				tmp.setRjm_status(data.getRjm_status());
			
				//商号を追加
				if(bc.getCompt_position().equals("first")){
					tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
				}else{
					tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
				}
				// データをリストに追加
				list.add(tmp);
			}
			
		}
		return list;
	}
	
	/**
	 * 企業説明会報告書の先生用の一覧ページに必要なlistを作成します<br>
	 * プロパティにlistを２つセットします。
	 * @param ArrayList<BeansReportJoinMeeting> 企業説明会報告書一覧
	 */
	public void authorityListJoinMeetingInfo(ArrayList<BeansReportJoinMeeting> array){
		
		ListIterator<BeansReportJoinMeeting> iterator = array.listIterator();
		list = new ArrayList<JoinMeetingInfo>();
		list2 = new ArrayList<JoinMeetingInfo>();
		
		// 一時クラス
		JoinMeetingInfo tmp;
		BeansCompany bc;
		BeansCompanyTrade ct;
		
		// データの読み取り
		while(iterator.hasNext()){
			
			// データを生成
			BeansReportJoinMeeting data = iterator.next();
			bc = BeansCompany.detailCompany(data.getComp_id());
			ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
			tmp = new JoinMeetingInfo();
			
			//代入
			String id = data.getL_id();
			BeansUser user = BeansUser.detailUser(id);
			BeansCourse course = BeansCourse.detailCourse(user.getC_id());
			
			tmp.setRjm_status(data.getRjm_status());
			tmp.setU_name(user.getU_name());
			tmp.setC_name(course.getC_name());
			tmp.setRjm_id(data.getRjm_id());
			tmp.setRjm_introduction(data.getRjm_introduction());
			tmp.setRjm_participate(data.getRjm_participate());
			tmp.setRjm_name(data.getRjm_name());
			
			//商号を追加
			if(bc.getCompt_position().equals("first")){
				tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
			}else{
				tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
			}
			
			//listを二つに分ける
			//状態が登録完了endでない時
			if(!data.getRjm_status().equals("end")){
				
				//状態が提出中newの時n_countカウントアップ
				if(data.getRjm_status().equals("new")){
					
					n_count++;
					
				//状態が修正済renewの時r_countカウントアップ
				}else if(data.getRjm_status().equals("renew")){
					
					r_count++;
					
				//状態が再提出againの時a_countカウントアップ
				}else if(data.getRjm_status().equals("again")){
					
					a_count++;
					
				}
				
				list.add(tmp);
			
			}else{
			
				list2.add(tmp);
		
			}
		}
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
	 * @return rjm_num
	 */
	public int getRjm_num() {
		return rjm_num;
	}

	/**
	 * @param rjm_num セットする rjm_num
	 */
	public void setRjm_num(int rjm_num) {
		this.rjm_num = rjm_num;
	}

	/**
	 * @return i_name
	 */
	public String getU_name() {
		return u_name;
	}

	/**
	 * @param i_name セットする i_name
	 */
	public void setU_name(String u_name) {
		this.u_name = u_name;
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
	 * @return n_count
	 */
	public int getN_count() {
		return n_count;
	}

	/**
	 * @param n_count
	 */
	public void setN_count(int n_count) {
		this.n_count = n_count;
	}

	/**
	 * @return a_count
	 */
	public int getA_count() {
		return a_count;
	}

	/**
	 * @param a_count
	 */
	public void setA_count(int a_count) {
		this.a_count = a_count;
	}

	/**
	 * @return r_count
	 */
	public int getR_count() {
		return r_count;
	}

	/**
	 * @param r_count
	 */
	public void setR_count(int r_count) {
		this.r_count = r_count;
	}

	/**
	 * @return list
	 */
	public ArrayList<JoinMeetingInfo> getList() {
		return list;
	}

	/**
	 * @param list セットする list
	 */
	public void setList(ArrayList<JoinMeetingInfo> list) {
		this.list = list;
	}

	/**
	 * @return list2
	 */
	public ArrayList<JoinMeetingInfo> getList2() {
		return list2;
	}

	/**
	 * @param list2 セットする list2
	 */
	public void setList2(ArrayList<JoinMeetingInfo> list2) {
		this.list2 = list2;
	}
	

}
