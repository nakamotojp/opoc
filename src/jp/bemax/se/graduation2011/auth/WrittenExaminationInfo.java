package jp.bemax.se.graduation2011.auth;

import java.util.ArrayList;
import java.util.ListIterator;

import jp.bemax.se.graduation2011.model.BeansCompany;
import jp.bemax.se.graduation2011.model.BeansCompanyTrade;
import jp.bemax.se.graduation2011.model.BeansCourse;
import jp.bemax.se.graduation2011.model.BeansReportWrittenExamination;
import jp.bemax.se.graduation2011.model.BeansUser;

public class WrittenExaminationInfo {
	
	/**
	 * 報告書ID
	 */
	private int rwe_id = -1;
	
	/**
	 * 参加日
	 */
	private String rwe_participate = null;
	
	/**
	 * 提出日
	 */
	private String rwe_introduction = null;
	
	/**
	 * 企業名
	 */
	private String comp_name = null;
	
	/**
	 * 状態
	 */
	private String rwe_status = null;
	
	/**
	 * 番号
	 */
	private int rwe_num = -1;
	
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
	private ArrayList<WrittenExaminationInfo> list = null;
	
	/**
	 * リスト(状態がend以外)
	 */
	private ArrayList<WrittenExaminationInfo> list2 = null;
	
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
	public WrittenExaminationInfo(){
		super();
	}
	
	/**
	 * 筆記試験報告書の一覧ページに必要な情報を返します。
	 * @param ArrayList<BeansReportWrittenExamination> 筆記試験報告書一覧
	 * @return ArrayList<WrittenExaminationInfo> 筆記試験報告書一覧（企業名入り）
	 */
	public static ArrayList<WrittenExaminationInfo> listWrittenExaminationInfo(ArrayList<BeansReportWrittenExamination> array){
	
		ListIterator<BeansReportWrittenExamination> iterator = array.listIterator();
		ArrayList<WrittenExaminationInfo> list = new ArrayList<WrittenExaminationInfo>();
		
		// 一時クラス
		WrittenExaminationInfo tmp;
		BeansCompany bc;
		BeansCompanyTrade ct;
		
		// データの読み取り
		while(iterator.hasNext()){

			// データを生成
			BeansReportWrittenExamination data = iterator.next();
			bc = BeansCompany.detailCompany(data.getComp_id());
			ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
			tmp = new WrittenExaminationInfo();
			
			if(data.getRwe_status().equals("end")){
			
				// 代入
				tmp.setRwe_id(data.getRwe_id());
				tmp.setRwe_introduction(data.getRwe_introduction());
				tmp.setRwe_participate(data.getRwe_participate());
				
					
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
	 * @param ArrayList<BeansReportWrittenExamination> 合同説明会報告書一覧
	 * @return ArrayList<WrittenExaminationInfo> 合同説明会報告書一覧（企業名入）
	 */
	public static ArrayList<WrittenExaminationInfo> newListWrittenExaminationInfo(ArrayList<BeansReportWrittenExamination> array){
	
		ListIterator<BeansReportWrittenExamination> iterator = array.listIterator();
		ArrayList<WrittenExaminationInfo> list = new ArrayList<WrittenExaminationInfo>();
		
		// 一時クラス
		WrittenExaminationInfo tmp = null;
		BeansCompany bc = null;
		BeansCompanyTrade ct = null;
		int count = 0;
		
			
		// データの読み取り
		while(iterator.hasNext()){
			
			//３件表示
			if(count < 3){
				
				// データを生成
				BeansReportWrittenExamination data = iterator.next();
				bc = BeansCompany.detailCompany(data.getComp_id());
				ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
				BeansUser bu = BeansUser.detailUser(data.getL_id());
				BeansCourse course = BeansCourse.detailCourse(bu.getC_id());
				tmp = new WrittenExaminationInfo();
				
				if(data.getRwe_status().equals("end")){
				
					// 代入
					tmp.setRwe_id(data.getRwe_id());
					tmp.setRwe_introduction(data.getRwe_introduction());
					tmp.setRwe_participate(data.getRwe_participate());
					tmp.setC_name(course.getC_name());
					tmp.setU_name(bu.getU_name());
					tmp.setRwe_status(data.getRwe_status());
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
			
			//３になったらbreak
			if(count == 3){
				break;
			}
		}
		return list;
	}
	
	
	/**
	 * 筆記試験報告書の一覧ページに必要な個人の情報を返します。
	 * @param ArrayList<BeansReportWrittenExamination> 筆記試験報告書一覧
	 * @return ArrayList<WrittenExaminationInfo> 筆記試験報告書一覧（企業名入）
	 */
	public static ArrayList<WrittenExaminationInfo> listWrittenExaminationMyInfo(ArrayList<BeansReportWrittenExamination> array , String l_id){
	
		ListIterator<BeansReportWrittenExamination> iterator = array.listIterator();
		ArrayList<WrittenExaminationInfo> list = new ArrayList<WrittenExaminationInfo>();
		
		// 一時クラス
		WrittenExaminationInfo tmp = null;
		BeansCompany bc = null;
		BeansCompanyTrade ct = null;
		
		// データの読み取り
		while(iterator.hasNext()){
			// データを生成
			BeansReportWrittenExamination data = iterator.next();
			String id = data.getL_id();
			
			if(id.equals(l_id)){
				
				bc = BeansCompany.detailCompany(data.getComp_id());
				ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
				tmp = new WrittenExaminationInfo();
			
				// 代入
				tmp.setRwe_id(data.getRwe_id());
				tmp.setRwe_introduction(data.getRwe_introduction());
				tmp.setRwe_participate(data.getRwe_participate());
				tmp.setRwe_num(2);
				tmp.setRwe_status(data.getRwe_status());

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
	 * 筆記試験報告書の先生用の一覧ページに必要なlistを作成します<br>
	 * プロパティにlistを２つセットします。
	 * @param ArrayList<BeansReportWrittenExamination> 面接試験報告書一覧
	 */
	public void authorityListWrittenExaminationInfo(ArrayList<BeansReportWrittenExamination> array){
		
		ListIterator<BeansReportWrittenExamination> iterator = array.listIterator();
		list = new ArrayList<WrittenExaminationInfo>();
		list2 = new ArrayList<WrittenExaminationInfo>();
		
		// 一時クラス
		WrittenExaminationInfo tmp;
		BeansCompany bc;
		BeansCompanyTrade ct;
		
		// データの読み取り
		while(iterator.hasNext()){
			// データを生成
			BeansReportWrittenExamination data = iterator.next();
			bc = BeansCompany.detailCompany(data.getComp_id());
			ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
			tmp = new WrittenExaminationInfo();
		
			//代入
			String l_id = data.getL_id();
			BeansUser user = BeansUser.detailUser(l_id);
			BeansCourse course = BeansCourse.detailCourse(user.getC_id());
			
			tmp.setRwe_status(data.getRwe_status());
			tmp.setU_name(user.getU_name());
			tmp.setC_name(course.getC_name());
			tmp.setRwe_id(data.getRwe_id());
			tmp.setRwe_introduction(data.getRwe_introduction());
			tmp.setRwe_participate(data.getRwe_participate());
			
			//商号を追加
			if(bc.getCompt_position().equals("first")){
				tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
			}else{
				tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
			}
			
			//listを二つに分ける
			//状態が登録完了endでない時
			if(!data.getRwe_status().equals("end")){
				
				//状態が提出中newの時n_countカウントアップ
				if(data.getRwe_status().equals("new")){
					
					n_count++;
					
				//状態が修正済renewである時r_countカウントアップ
				}else if(data.getRwe_status().equals("renew")){
					
					r_count++;
				
				//状態が再提出againの時a_countカウントアップ
				}else if(data.getRwe_status().equals("again")){
					
					a_count++;
					
				}
				
				list.add(tmp);
				
			}else{
				
				list2.add(tmp);
			
			}
		}
	}

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
	 * @return rwe_num
	 */
	public int getRwe_num() {
		return rwe_num;
	}

	/**
	 * @param rwe_num セットする rwe_num
	 */
	public void setRwe_num(int rwe_num) {
		this.rwe_num = rwe_num;
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
	 * @return list
	 */
	public ArrayList<WrittenExaminationInfo> getList() {
		return list;
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
	 * @param list セットする list
	 */
	public void setList(ArrayList<WrittenExaminationInfo> list) {
		this.list = list;
	}

	/**
	 * @return list2
	 */
	public ArrayList<WrittenExaminationInfo> getList2() {
		return list2;
	}

	/**
	 * @param list2 セットする list2
	 */
	public void setList2(ArrayList<WrittenExaminationInfo> list2) {
		this.list2 = list2;
	}


}
