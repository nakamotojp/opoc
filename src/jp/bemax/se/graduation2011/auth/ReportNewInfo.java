/**
 * 各報告書新着３件表示
 * @author 信原美希
 */
package jp.bemax.se.graduation2011.auth;

import java.util.ArrayList;
import java.util.ListIterator;

import jp.bemax.se.graduation2011.model.BeansCompany;
import jp.bemax.se.graduation2011.model.BeansCompanyTrade;
import jp.bemax.se.graduation2011.model.BeansCourse;
import jp.bemax.se.graduation2011.model.BeansReportJoinMeeting;
import jp.bemax.se.graduation2011.model.BeansReportOralExamination;
import jp.bemax.se.graduation2011.model.BeansReportWrittenExamination;
import jp.bemax.se.graduation2011.model.BeansUser;


public class ReportNewInfo {
	
	
	/**
	 * 企業名
	 */
	private String comp_name = null;
	
	/**
	 * 報告書ID
	 */
	private int id = -1;
	
	/**
	 * 説明会名
	 */
	private String name = null;
	
	/**
	 * 参加日
	 */
	private String participate = null;
	
	/**
	 * 提出日
	 */
	private String introduction = null;

	/**
	 * 番号
	 */
	private int num = -1;	
	
	/**
	 * 合説提出日
	 */
	private String rjm_introduction = null;
	
	/**
	 * 筆記提出日
	 */
	private String rwe_introduction = null;
	
	/**
	 * 面接提出日
	 */
	private String roe_introduction = null;
	
	/**
	 * 状態
	 */
	private String status = null;
	
	/**
	 * 登録者名
	 */
	private String u_name = null;

	/**
	 * コース名
	 */
	private String c_name = null;
	
	/**
	 * 削除用
	 */
	private String del = "false";
	
	/**
	 * 一覧(endのみ)
	 */
	private ArrayList<ReportNewInfo> list = null;
	
	/**
	 * カウンタ
	 */
	private int count = 0;

	/**
	 * コンストラクタ
	 */
	public ReportNewInfo(){
		super();
	}
	
	
	/**
	 * 三つの報告書を一つにまとめた個人一覧リストを作成します。<br>
	 * プロパティに状態がendのリストをセットします。
	 * @param ArrayList<JoinMeetingInfo> list1 企業説明会報告書のリスト
	 * @param ArrayList<WrittenExaminationInfo> list2 筆記試験報告書のリスト
	 * @param ArrayList<OralExaminationInfo> list3 面接試験報告書のリスト
	 */
	public void newListReportNewInfo(ArrayList<JoinMeetingInfo> list1 , 
			ArrayList<WrittenExaminationInfo> list2 , ArrayList<OralExaminationInfo> list3){
		
		//3つのlistのiterator生成
		ListIterator<JoinMeetingInfo> iterator1 = list1.listIterator();
		ListIterator<WrittenExaminationInfo> iterator2 = list2.listIterator();
		ListIterator<OralExaminationInfo> iterator3 = list3.listIterator();
		list = new ArrayList<ReportNewInfo>();
		
		JoinMeetingInfo data1 = null;
		WrittenExaminationInfo data2 = null;
		OralExaminationInfo data3 = null;
		// 一時クラス
		ReportNewInfo tmp = null;
		
		if(iterator1.hasNext()){
			data1 = iterator1.next();
		}
		
		if(iterator2.hasNext()){
			data2 = iterator2.next();
		}
		
		if(iterator3.hasNext()){
			data3 = iterator3.next();
		}
		
		while(true){
			
			if(data1 == null && data2 == null && data3 == null){
				break;
			}
			
			if(data1 == null){

				//nullの場合は-1
				rjm_introduction = "-1";
			}else{
				
				//登録済みの場合は提出日を取得する
				rjm_introduction = escape(data1.getRjm_introduction());
			}
			
			if(data2 == null){
				
				//nullの場合は-1
				rwe_introduction = "-1";
			}else{
				
				//登録済みの場合は提出日を取得する
				rwe_introduction = escape(data2.getRwe_introduction());
			}
			
			if(data3 == null){
				
				//nullの場合は-1
				roe_introduction = "-1";
			}else{
				
				//登録済みの場合は提出日を取得する
				roe_introduction = escape(data3.getRoe_introduction());
			}
			
			if(Integer.parseInt(rjm_introduction) < Integer.parseInt(rwe_introduction)){
					
				if(Integer.parseInt(rwe_introduction) < Integer.parseInt(roe_introduction)){
					
					if(count < 3){
						
						//面接試験報告書一覧をlistに格納
						tmp = new ReportNewInfo();
						
						// 代入
						tmp.setId(data3.getRoe_id());
						tmp.setIntroduction(data3.getRoe_introduction());
						tmp.setParticipate(data3.getRoe_participate());
						tmp.setComp_name(data3.getComp_name());
						tmp.setNum(3);
						tmp.setStatus(data3.getRoe_status());
						
						//削除できるかどうか判断・状態がnew
						if(data3.getRoe_status().equals("new")){
							
							 del = "true";
							 tmp.setDel(del);
						}
	
						
						if(!data3.getRoe_status().equals("end")){
							
							//カウントアップ
							count++;
							
							list.add(tmp);
							
						}
						
						if(iterator3.hasNext()){
							data3 = iterator3.next();
						}else{
							data3 = null;
						}
					}else{
						break;
					}
					
				}else{
					
					if(count < 3){
						
						//筆記試験報告書一覧をlistに格納
						tmp = new ReportNewInfo();
							
						//代入
						tmp.setId(data2.getRwe_id());
						tmp.setIntroduction(data2.getRwe_introduction());
						tmp.setParticipate(data2.getRwe_participate());
						tmp.setComp_name(data2.getComp_name());
						tmp.setNum(2);
						tmp.setStatus(data2.getRwe_status());
						
						//削除できるかどうか判断・状態がnew
						if(data2.getRwe_status().equals("new")){
							
							 del = "true";
							 tmp.setDel(del);
						}
						
						if(!data2.getRwe_status().equals("end")){
							
							//カウントアップ
							count++;
							
							list.add(tmp);
							
						}
						
						if(iterator2.hasNext()){
							data2 = iterator2.next();
						}else{
							data2 = null;
						}
					}else{
						break;
					}
				
				}				
			}else if(Integer.parseInt(rjm_introduction) < Integer.parseInt(roe_introduction)){
				
				if(count < 3){
					
					//面接試験報告書一覧をlistに格納
					tmp = new ReportNewInfo();
					
					// 代入
					tmp.setId(data3.getRoe_id());
					tmp.setIntroduction(data3.getRoe_introduction());
					tmp.setParticipate(data3.getRoe_participate());
					tmp.setComp_name(data3.getComp_name());
					tmp.setNum(3);
					tmp.setStatus(data3.getRoe_status());
					
					//削除できるかどうか判断・状態がnew
					if(data3.getRoe_status().equals("new")){
						
						 del = "true";
						 tmp.setDel(del);
					}
					
					if(!data3.getRoe_status().equals("end")){
						
						//カウントアップ
						count++;
						
						list.add(tmp);
					}
					
					if(iterator3.hasNext()){
						data3 = iterator3.next();
					}else{
						data3 = null;
					}
				}else{
					break;
				}
			
			}else{
				
				if(count < 3){
				
					//合同説明会報告書一覧をlistに格納
					tmp = new ReportNewInfo();
					
					// 代入
					tmp.setId(data1.getRjm_id());
					tmp.setIntroduction(data1.getRjm_introduction());
					tmp.setParticipate(data1.getRjm_participate());
					tmp.setName(data1.getRjm_name());
					tmp.setComp_name(data1.getComp_name());
					tmp.setNum(1);
					tmp.setStatus(data1.getRjm_status());
					
					//削除できるかどうか判断・状態がnew
					if(data1.getRjm_status().equals("new")){
						
						 del = "true";
						 tmp.setDel(del);
					}
					
					if(!data1.getRjm_status().equals("end")){
						
						//カウントアップ
						count++;

						list.add(tmp);
					}
					
					if(iterator1.hasNext()){
						data1 = iterator1.next();
					}else{
						data1 = null;
					}	
				}else{
					break;
				}
			}	
		}
		
	}
	
	
	/**
	 * 三つの報告書を一つにまとめた一覧を作成します。（教員用）<br>
	 * プロパティに状態がendのリストをセットします。
	 * @param ArrayList<BeansJoinMeeting> list1 企業説明会報告書のリスト
	 * @param ArrayList<BeansWrittenExamination> list2 筆記試験報告書のリスト
	 * @param ArrayList<BeansOralExamination> list3 面接試験報告書のリスト
	 */
	public void authorityNewListReportNewInfo(ArrayList<BeansReportJoinMeeting> list1 , 
			ArrayList<BeansReportWrittenExamination> list2 , ArrayList<BeansReportOralExamination> list3){
		
		//3つのlistのiterator生成
		ListIterator<BeansReportJoinMeeting> iterator1 = list1.listIterator();
		ListIterator<BeansReportWrittenExamination> iterator2 = list2.listIterator();
		ListIterator<BeansReportOralExamination> iterator3 = list3.listIterator();
		list = new ArrayList<ReportNewInfo>();
		
		BeansReportJoinMeeting data1 = null;
		BeansReportWrittenExamination data2 = null;
		BeansReportOralExamination data3 = null;
		BeansCompanyTrade ct = null;
		
		// 一時クラス
		ReportNewInfo tmp = null;
		
		if(iterator1.hasNext()){
			data1 = iterator1.next();
		}
		
		if(iterator2.hasNext()){
			data2 = iterator2.next();
		}
		
		if(iterator3.hasNext()){
			data3 = iterator3.next();
		}
		
		while(true){
			
			if(data1 == null && data2 == null && data3 == null){
				break;
			}
			
			if(data1 == null){

				//nullの場合は-1
				rjm_introduction = "-1";
			}else{
				
				//登録済みの場合は提出日を取得する
				rjm_introduction = escape(data1.getRjm_introduction());
			}
			
			if(data2 == null){
				
				//nullの場合は-1
				rwe_introduction = "-1";
			}else{
				
				//登録済みの場合は提出日を取得する
				rwe_introduction = escape(data2.getRwe_introduction());
			}
			
			if(data3 == null){
				
				//nullの場合は-1
				roe_introduction = "-1";
			}else{
				
				//登録済みの場合は提出日を取得する
				roe_introduction = escape(data3.getRoe_introduction());
			}
			
			if(Integer.parseInt(rjm_introduction) < Integer.parseInt(rwe_introduction)){
					
				if(Integer.parseInt(rwe_introduction) < Integer.parseInt(roe_introduction)){
					
					if(count < 3){
						
						//面接試験報告書一覧をlistに格納
						tmp = new ReportNewInfo();
						//データを取得
						BeansUser bu = BeansUser.detailUser(data3.getL_id());
						BeansCompany bc = BeansCompany.detailCompany(data3.getComp_id());
						BeansCourse course = BeansCourse.detailCourse(bu.getC_id());
						ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
						
						// 代入
						tmp.setId(data3.getRoe_id());
						tmp.setIntroduction(data3.getRoe_introduction());
						tmp.setParticipate(data3.getRoe_participate());
						tmp.setComp_name(bc.getComp_name());
						tmp.setU_name(bu.getU_name());
						tmp.setC_name(course.getC_name());
						tmp.setNum(3);
						tmp.setStatus(data3.getRoe_status());
						
						/**
						 * 状態がend以外の場合は全体のカウンタcountをカウントアップ
						 */
						if(!data3.getRoe_status().equals("end")){
							
							//カウントアップ
							 count++;
							
							//商号を追加
							if(bc.getCompt_position().equals("first")){
								tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
							}else{
								tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
							}
							
							list.add(tmp);
							
						}
						
						if(iterator3.hasNext()){
							data3 = iterator3.next();
						}else{
							data3 = null;
						}
					}else{
						break;
					}
					
				}else{
					
					if(count < 3){
						
						//筆記試験報告書一覧をlistに格納
						tmp = new ReportNewInfo();
						//データを取得
						BeansUser bu = BeansUser.detailUser(data2.getL_id());
						BeansCompany bc = BeansCompany.detailCompany(data2.getComp_id());
						BeansCourse course = BeansCourse.detailCourse(bu.getC_id());
						ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
							
						//代入
						tmp.setId(data2.getRwe_id());
						tmp.setIntroduction(data2.getRwe_introduction());
						tmp.setParticipate(data2.getRwe_participate());
						tmp.setComp_name(bc.getComp_name());
						tmp.setU_name(bu.getU_name());
						tmp.setC_name(course.getC_name());
						tmp.setNum(2);
						tmp.setStatus(data2.getRwe_status());
						
						/**
						 * 状態がend以外の場合は全体のカウンタcountをカウントアップ
						 */
						if(!data2.getRwe_status().equals("end")){
							
							//カウントアップ
							 count++;
							
							//商号を追加
							if(bc.getCompt_position().equals("first")){
								tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
							}else{
								tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
							}
							
							list.add(tmp);
							
						}
						
						if(iterator2.hasNext()){
							data2 = iterator2.next();
						}else{
							data2 = null;
						}
					}else{
						break;
					}
				
				}				
			}else if(Integer.parseInt(rjm_introduction) < Integer.parseInt(roe_introduction)){
				
				if(count < 3){
					
					//面接試験報告書一覧をlistに格納
					tmp = new ReportNewInfo();
					//データを取得
					BeansUser bu = BeansUser.detailUser(data3.getL_id());
					BeansCompany bc = BeansCompany.detailCompany(data3.getComp_id());
					BeansCourse course = BeansCourse.detailCourse(bu.getC_id());
					ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
					
					// 代入
					tmp.setId(data3.getRoe_id());
					tmp.setIntroduction(data3.getRoe_introduction());
					tmp.setParticipate(data3.getRoe_participate());
					tmp.setComp_name(bc.getComp_name());
					tmp.setU_name(bu.getU_name());
					tmp.setC_name(course.getC_name());
					tmp.setNum(3);
					tmp.setStatus(data3.getRoe_status());
					
					/**
					 * 状態がend以外の場合は全体のカウンタcountをカウントアップ
					 */
					if(!data3.getRoe_status().equals("end")){
						
						//カウントアップ
						 count++;
						
					 
						//商号を追加
						if(bc.getCompt_position().equals("first")){
							tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
						}else{
							tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
						}
						
						list.add(tmp);
						
					}
					
					if(iterator3.hasNext()){
						data3 = iterator3.next();
					}else{
						data3 = null;
					}
				}else{
					break;
				}
			
			}else{
				
				if(count < 3){
				
					//合同説明会報告書一覧をlistに格納
					tmp = new ReportNewInfo();
					//データを取得
					BeansUser bu = BeansUser.detailUser(data1.getL_id());
					BeansCompany bc = BeansCompany.detailCompany(data1.getComp_id());
					BeansCourse course = BeansCourse.detailCourse(bu.getC_id());
					ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
					
					// 代入
					tmp.setId(data1.getRjm_id());
					tmp.setIntroduction(data1.getRjm_introduction());
					tmp.setParticipate(data1.getRjm_participate());
					tmp.setName(data1.getRjm_name());
					tmp.setComp_name(bc.getComp_name());
					tmp.setU_name(bu.getU_name());
					tmp.setC_name(course.getC_name());
					tmp.setNum(1);
					tmp.setStatus(data1.getRjm_status());
					
					/**
					 * 状態がend以外の場合は全体のカウンタcountをカウントアップ
					 */
					if(!data1.getRjm_status().equals("end")){
						
						//カウントアップ
						 count++;
					 
						//商号を追加
						if(bc.getCompt_position().equals("first")){
							tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
						}else{
							tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
						}
						
						list.add(tmp);
						
					}
					
					if(iterator1.hasNext()){
						data1 = iterator1.next();
					}else{
						data1 = null;
					}	
				}else{
					break;
				}
			}	
		}
		
	}
	
	
	
	/**
	 * 提出日を数字だけにします。
	 * @param value 提出日
	 * @return result 提出日
	 */
	public String escape(String value){
		
		StringBuffer result = new StringBuffer();
		//取り出した値を一文字ずつ変換"
		if(value != null){
			for(int j = 0 ; j < value.length() ; j++){
					
				switch(value.charAt(j)){
						
				case '-' :
					break;
					
				//変換しなかった場合は、元の文字を格納
				default :
					result.append(value.charAt(j));
					break;	
					
				}
			}
			return result.toString();
		}else{
			return null;
		}
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
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return participate
	 */
	public String getParticipate() {
		return participate;
	}

	/**
	 * @param participate セットする participate
	 */
	public void setParticipate(String participate) {
		this.participate = participate;
	}

	/**
	 * @return introduction
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * @param introduction セットする introduction
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * @return num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num セットする num
	 */
	public void setNum(int num) {
		this.num = num;
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
	 * @param status セット
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return u_name
	 */
	public String getU_name() {
		return u_name;
	}

	/**
	 * @param u_name セットする u_name
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
	 * @return del
	 */
	public String getDel() {
		return del;
	}

	/**
	 * @param del セットする del
	 */
	public void setDel(String del) {
		this.del = del;
	}

	/**
	 * @return count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}


	/**
	 * @return list
	 */
	public ArrayList<ReportNewInfo> getList() {
		return list;
	}

	/**
	 * @param list セットする list
	 */
	public void setList(ArrayList<ReportNewInfo> list) {
		this.list = list;
	}

}
