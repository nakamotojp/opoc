package jp.bemax.se.graduation2011.auth;

import java.util.ArrayList;
import java.util.ListIterator;

import jp.bemax.se.graduation2011.model.BeansCompany;
import jp.bemax.se.graduation2011.model.BeansCompanyTrade;
import jp.bemax.se.graduation2011.model.BeansCourse;
import jp.bemax.se.graduation2011.model.BeansReportOralExamination;
import jp.bemax.se.graduation2011.model.BeansUser;

public class OralExaminationInfo {
	
	/**
	 * 報告書ID
	 */
	private int roe_id = -1;
	
	/**
	 * 参加日
	 */
	private String roe_participate = null;
	
	/**
	 * 提出日
	 */
	private String roe_introduction = null;
	
	/**
	 * 企業名
	 */
	private String comp_name = null;
	
	/**
	 * 状態
	 */
	private String roe_status = null;
	
	/**
	 * 番号
	 */
	private int roe_num = -1;
	
	/**
	 * 登録者名
	 */
	private String u_name = null;
	
	/**
	 * コース名
	 */
	private String c_name = null;
	
	/**
	 * 先生用一覧(状態がend)
	 */
	private ArrayList<OralExaminationInfo> list = null;
	
	/**
	 * 先生用一覧(状態がend以外)
	 */
	private ArrayList<OralExaminationInfo> list2 = null;
	
	/**
	 * 状態がnewのみのカウンタ
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
	public OralExaminationInfo(){
		super();
	}
	
	/**
	 * 面接試験報告書の一覧ページに必要な情報を返します。
	 * @param ArrayList<BeansReportOralExamination> 面接試験報告書一覧
	 * @return ArrayList<OralExaminationInfo> 面接試験報告書一覧（企業名入り）
	 */
	public static ArrayList<OralExaminationInfo> listOralExaminationInfo(ArrayList<BeansReportOralExamination> array){
	
		ListIterator<BeansReportOralExamination> iterator = array.listIterator();
		ArrayList<OralExaminationInfo> list = new ArrayList<OralExaminationInfo>();
		
		// 一時クラス
		OralExaminationInfo tmp;
		BeansCompany bc;
		BeansCompanyTrade ct;
		
		// データの読み取り
		while(iterator.hasNext()){
			// データを生成
			BeansReportOralExamination data = iterator.next();
			bc = BeansCompany.detailCompany(data.getComp_id());
			ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
			tmp = new OralExaminationInfo();
			
			if(data.getRoe_status().equals("end")){
				
				// 代入
				tmp.setRoe_id(data.getRoe_id());
				tmp.setRoe_introduction(data.getRoe_introduction());
				tmp.setRoe_participate(data.getRoe_participate());
				
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
	 * @param ArrayList<BeansReportOralExamination> 合同説明会報告書一覧
	 * @return ArrayList<OralExaminationInfo> 合同説明会報告書一覧（企業名入）
	 */
	public static ArrayList<OralExaminationInfo> newListOralExaminationInfo(ArrayList<BeansReportOralExamination> array){
	
		ListIterator<BeansReportOralExamination> iterator = array.listIterator();
		ArrayList<OralExaminationInfo> list = new ArrayList<OralExaminationInfo>();
		
		// 一時クラス
		OralExaminationInfo tmp = null;
		BeansCompany bc = null;
		BeansCompanyTrade ct = null;
		int count = 0;
		
			
		// データの読み取り
		while(iterator.hasNext()){
			
			if(count < 3){
				
				// データを生成
				BeansReportOralExamination data = iterator.next();
				bc = BeansCompany.detailCompany(data.getComp_id());
				ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
				BeansUser bu = BeansUser.detailUser(data.getL_id());
				BeansCourse course = BeansCourse.detailCourse(bu.getC_id());
				tmp = new OralExaminationInfo();
				
				if(data.getRoe_status().equals("end")){
				
					// 代入
					tmp.setRoe_id(data.getRoe_id());
					tmp.setRoe_introduction(data.getRoe_introduction());
					tmp.setRoe_participate(data.getRoe_participate());
					tmp.setC_name(course.getC_name());
					tmp.setU_name(bu.getU_name());
					tmp.setRoe_status(data.getRoe_status());
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
	 * 面接試験報告書の一覧ページに必要な個人の情報を返します。
	 * @param ArrayList<BeansReportOralExamination> 面接試験報告書一覧
	 * @return ArrayList<OralExaminationInfo> 面接試験報告書一覧（企業名入）
	 */
	public static ArrayList<OralExaminationInfo> listOralExaminationMyInfo(ArrayList<BeansReportOralExamination> array , String l_id){
	
		ListIterator<BeansReportOralExamination> iterator = array.listIterator();
		ArrayList<OralExaminationInfo> list = new ArrayList<OralExaminationInfo>();
		
		// 一時クラス
		OralExaminationInfo tmp = null;
		BeansCompany bc = null;
		BeansCompanyTrade ct = null;
		
		// データの読み取り
		while(iterator.hasNext()){
			// データを生成
			BeansReportOralExamination data = iterator.next();
			String id = data.getL_id();
			
			if(id.equals(l_id)){
			
				bc = BeansCompany.detailCompany(data.getComp_id());
				ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
				tmp = new OralExaminationInfo();
			
				// 代入
				tmp.setRoe_id(data.getRoe_id());
				tmp.setRoe_introduction(data.getRoe_introduction());
				tmp.setRoe_participate(data.getRoe_participate());
				tmp.setRoe_num(3);
				tmp.setRoe_status(data.getRoe_status());
			
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
	 * 面接試験報告書の先生用の一覧ページに必要なlistを作成します<br>
	 * プロパティにlistを２つセットします。
	 * @param ArrayList<BeansReportOralExamination> 面接試験報告書一覧
	 */
	
	public void authorityListOralExaminationInfo(ArrayList<BeansReportOralExamination> array){
		
		ListIterator<BeansReportOralExamination> iterator = array.listIterator();
		 list = new ArrayList<OralExaminationInfo>();
		 list2 = new ArrayList<OralExaminationInfo>();
		
		// 一時クラス
		OralExaminationInfo tmp;
		BeansCompany bc;
		BeansCompanyTrade ct;
		
		// データの読み取り
		while(iterator.hasNext()){
			// データを生成
			BeansReportOralExamination data = iterator.next();
			bc = BeansCompany.detailCompany(data.getComp_id());
			ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
			tmp = new OralExaminationInfo();
				
			//代入
			String l_id = data.getL_id();
			BeansUser user = BeansUser.detailUser(l_id);
			BeansCourse course = BeansCourse.detailCourse(user.getC_id());

			tmp.setRoe_status(data.getRoe_status());
			tmp.setU_name(user.getU_name());
			tmp.setC_name(course.getC_name());
			tmp.setRoe_id(data.getRoe_id());
			tmp.setRoe_introduction(data.getRoe_introduction());
			tmp.setRoe_participate(data.getRoe_participate());
			
			//商号を追加
			if(bc.getCompt_position().equals("first")){
				tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
			}else{
				tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
			}
			
			//listを二つに分ける
			//状態が登録済endでない時
			if(!data.getRoe_status().equals("end")){
				
				//状態が提出中newの時はn_countカウントアップ
				if(data.getRoe_status().equals("new")){
					
					n_count++;
					
				//状態が修正済renewの時はr_countカウントアップ
				}else if(data.getRoe_status().equals("renew")){
					
					r_count++;
					
				//状態が再提出againの時はa_countカウントアップ
				}else if(data.getRoe_status().equals("again")){
					
					a_count++;
					
				}
				
				list.add(tmp);
			
			}else{
			
				list2.add(tmp);
			
			}
		}
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
	 * @return roe_num
	 */
	public int getRoe_num() {
		return roe_num;
	}

	/**
	 * @param roe_num セットする roe_num
	 */
	public void setRoe_num(int roe_num) {
		this.roe_num = roe_num;
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
	 * @return list
	 */
	public ArrayList<OralExaminationInfo> getList() {
		return list;
	}

	/**
	 * @param list セットする list
	 */
	public void setList(ArrayList<OralExaminationInfo> list) {
		this.list = list;
	}

	/**
	 * @return list2
	 */
	public ArrayList<OralExaminationInfo> getList2() {
		return list2;
	}

	/**
	 * @param list2 セットする list2
	 */
	public void setList2(ArrayList<OralExaminationInfo> list2) {
		this.list2 = list2;
	}

}
