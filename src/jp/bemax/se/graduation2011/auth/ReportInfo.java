package jp.bemax.se.graduation2011.auth;

import java.util.ArrayList;
import java.util.ListIterator;

import jp.bemax.se.graduation2011.model.BeansCompany;
import jp.bemax.se.graduation2011.model.BeansCompanyTrade;
import jp.bemax.se.graduation2011.model.BeansCourse;
import jp.bemax.se.graduation2011.model.BeansReportJoinMeeting;
import jp.bemax.se.graduation2011.model.BeansReportOralExamination;
import jp.bemax.se.graduation2011.model.BeansReportWrittenExamination;
import jp.bemax.se.graduation2011.model.BeansSubject;
import jp.bemax.se.graduation2011.model.BeansUser;


public class ReportInfo {
	
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
	 * 学科名
	 */
	private String s_name = null;
	
	/**
	 * 削除用
	 */
	private String del = "false";

	/**
	 * 一覧(end)
	 */
	private ArrayList<ReportInfo> e_list = null;

	/**
	 * 一覧(end以外)
	 */
	private ArrayList<ReportInfo> list = null;
	
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
	public ReportInfo(){
		super();
	}
	
	/**
	 * 三つの報告書を一つにまとめた個人一覧リストを作成します。<br>
	 * プロパティに状態がendのリストとend以外のリストをセットします。
	 * @param ArrayList<JoinMeetingInfo> list1 企業説明会報告書のリスト
	 * @param ArrayList<WrittenExaminationInfo> list2 筆記試験報告書のリスト
	 * @param ArrayList<OralExaminationInfo> list3 面接試験報告書のリスト
	 */
	public void listReportInfo(ArrayList<JoinMeetingInfo> list1 , 
			ArrayList<WrittenExaminationInfo> list2 , ArrayList<OralExaminationInfo> list3){
		
		//3つのlistのiterator生成
		ListIterator<JoinMeetingInfo> iterator1 = list1.listIterator();
		ListIterator<WrittenExaminationInfo> iterator2 = list2.listIterator();
		ListIterator<OralExaminationInfo> iterator3 = list3.listIterator();
		e_list = new ArrayList<ReportInfo>();
		list = new ArrayList<ReportInfo>();
		
		JoinMeetingInfo data1 = null;
		WrittenExaminationInfo data2 = null;
		OralExaminationInfo data3 = null;
		// 一時クラス
		ReportInfo tmp = null;
		
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
						
					//面接試験報告書一覧をlistに格納
					tmp = new ReportInfo();
					
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
						
						/*
						 * 状態がnew、またはrenewの場合、n_countをカウントアップ
						 * 状態がagainの場合は、a_countをカウントアップ
						 */
						if(data3.getRoe_status().equals("new") || data3.getRoe_status().equals("renew")){
							
							n_count++;
							
						}else if(data3.getRoe_status().equals("again")){
							
							a_count++;
							
						}
						
						list.add(tmp);
						
					}else{
						
						e_list.add(tmp);
					}
					
					if(iterator3.hasNext()){
						data3 = iterator3.next();
					}else{
						data3 = null;
					}
					
				}else{
						
					//筆記試験報告書一覧をlistに格納
					tmp = new ReportInfo();
						
					//代入
					tmp.setId(data2.getRwe_id());
					tmp.setIntroduction(data2.getRwe_introduction());
					tmp.setParticipate(data2.getRwe_participate());
					tmp.setComp_name(data2.getComp_name());
					tmp.setNum(2);
					tmp.setStatus(data2.getRwe_status());
					
					
					if(!data2.getRwe_status().equals("end")){
						
						//状態が提出中(new)の場合
						if(data2.getRwe_status().equals("new")){
							
							 //削除セット
							 del = "true";
							 tmp.setDel(del);
							 //n_countカウントアップ
							 n_count++;
							 
						//状態が修正済(renew)の場合
						}else if(data2.getRwe_status().equals("renew")){
							
							//カウントアップ
							r_count++;
							
						//状態が再提出(again)の場合
						}else if(data2.getRwe_status().equals("again")){
							
							//カウントアップ
							a_count++;
							
						}
						
						list.add(tmp);
						
					}else{
						
						e_list.add(tmp);
					
					}
					
					if(iterator2.hasNext()){
					
						data2 = iterator2.next();
					
					}else{
					
						data2 = null;
					
					}
				
				}
				
			}else if(Integer.parseInt(rjm_introduction) < Integer.parseInt(roe_introduction)){
				
				//面接試験報告書一覧をlistに格納
				tmp = new ReportInfo();
				
				// 代入
				tmp.setId(data3.getRoe_id());
				tmp.setIntroduction(data3.getRoe_introduction());
				tmp.setParticipate(data3.getRoe_participate());
				tmp.setComp_name(data3.getComp_name());
				tmp.setNum(3);
				tmp.setStatus(data3.getRoe_status());
				
				//状態がend(登録完了)でない場合
				if(!data3.getRoe_status().equals("end")){
					
					//状態がnewの場合
					if(data3.getRoe_status().equals("new")){
						
						//削除セット
						del = "true";
						tmp.setDel(del);
						//n_countカウントアップ
						n_count++;
						 
					//状態が修正済(renew)の場合
					}else if(data3.getRoe_status().equals("renew")){
						
						r_count++;
						
					
					}else if(data3.getRoe_status().equals("again")){
							
						a_count++;
							
					}
						
					list.add(tmp);
					
				}else{
				
					e_list.add(tmp);
				}
			
				if(iterator3.hasNext()){
					
					data3 = iterator3.next();
				
				}else{
				
					data3 = null;
				
				}
		
			}else{
				
				//合同説明会報告書一覧をlistに格納
				tmp = new ReportInfo();
				
				// 代入
				tmp.setId(data1.getRjm_id());
				tmp.setIntroduction(data1.getRjm_introduction());
				tmp.setParticipate(data1.getRjm_participate());
				tmp.setName(data1.getRjm_name());
				tmp.setComp_name(data1.getComp_name());
				tmp.setNum(1);
				tmp.setStatus(data1.getRjm_status());
				
				//状態がend(登録完了)でない場合
				if(!data1.getRjm_status().equals("end")){
					
					

					 // 状態がnew(提出中)の場合
					if(data1.getRjm_status().equals("new")){
						
						 //削除セット
						 del = "true";
						 tmp.setDel(del);
						 
						 //n_countをカウントアップ
						 n_count++;
					
					
					 // 状態がrenew(修正済)の場合はr_countをカウントアップ
					}else if(data1.getRjm_status().equals("renew")){
						
						r_count++;
					
					//状態がagain(再提出)の場合はa_countをカウントアップ
					}else if(data1.getRjm_status().equals("again")){
						
						a_count++;
						
					}
					
					list.add(tmp);
					
				}else{
					
					e_list.add(tmp);
				
				}
				
				if(iterator1.hasNext()){
					data1 = iterator1.next();
				}else{
					data1 = null;
				}	
			}	
		}
	}
	
	/**
	 * 三つの報告書を一つにまとめた一覧リストを作成します。(企業名から検索用)<br>
	 * プロパティに状態がendの報告書をセットします。
	 * @param ArrayList<BeansReportJoinMeeting> list1 企業説明会報告書のリスト
	 * @param ArrayList<BeansReportWrittenExamination> list2 筆記試験報告書のリスト
	 * @param ArrayList<BeansReportOralExamination> list3 面接試験報告書のリスト
	 */
	public void listSearchCompaniesReport(ArrayList<BeansReportJoinMeeting> list1 , 
			ArrayList<BeansReportWrittenExamination> list2 , ArrayList<BeansReportOralExamination> list3){
		
		//3つのlistのiterator生成
		ListIterator<BeansReportJoinMeeting> iterator1 = list1.listIterator();
		ListIterator<BeansReportWrittenExamination> iterator2 = list2.listIterator();
		ListIterator<BeansReportOralExamination> iterator3 = list3.listIterator();
		list = new ArrayList<ReportInfo>();
		
		BeansReportJoinMeeting data1 = null;
		BeansReportWrittenExamination data2 = null;
		BeansReportOralExamination data3 = null;
		
		// 一時クラス
		ReportInfo tmp = null;
		BeansCompany bc;
		BeansCompanyTrade ct;
		BeansUser user;
		BeansCourse course;
		BeansSubject subject;
		
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
						
					//面接試験報告書一覧をlistに格納
					tmp = new ReportInfo();
					
					// 代入
					bc = BeansCompany.detailCompany(data3.getComp_id());
					ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
					
					//状態を判定
					if(data3.getRoe_status().equals("end")){
						
						//登録者のIDから学科名を検索
						user = BeansUser.detailUser(data3.getL_id());
						course = BeansCourse.detailCourse(user.getC_id());
						subject = BeansSubject.detailSubject(course.getS_id());
						
						tmp.setS_name(subject.getS_name());
						tmp.setId(data3.getRoe_id());
						tmp.setIntroduction(data3.getRoe_introduction());
						tmp.setParticipate(data3.getRoe_participate());
						tmp.setNum(3);
						tmp.setStatus(data3.getRoe_status());
						
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
						
					//筆記試験報告書一覧をlistに格納
					tmp = new ReportInfo();
						
					//代入
					bc = BeansCompany.detailCompany(data2.getComp_id());
					ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
					
					if(data2.getRwe_status().equals("end")){
						
						//登録者のIDから学科名を検索
						user = BeansUser.detailUser(data2.getL_id());
						course = BeansCourse.detailCourse(user.getC_id());
						subject = BeansSubject.detailSubject(course.getS_id());
						
						tmp.setS_name(subject.getS_name());
						tmp.setId(data2.getRwe_id());
						tmp.setIntroduction(data2.getRwe_introduction());
						tmp.setParticipate(data2.getRwe_participate());
						tmp.setNum(2);
						tmp.setStatus(data2.getRwe_status());
					
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
				}				
			}else if(Integer.parseInt(rjm_introduction) < Integer.parseInt(roe_introduction)){
				
				//面接試験報告書一覧をlistに格納
				tmp = new ReportInfo();
				
				// 代入
				bc = BeansCompany.detailCompany(data3.getComp_id());
				ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
				
				if(data3.getRoe_status().equals("end")){
					
					//登録者のIDから学科名を検索
					user = BeansUser.detailUser(data3.getL_id());
					course = BeansCourse.detailCourse(user.getC_id());
					subject = BeansSubject.detailSubject(course.getS_id());
					
					tmp.setS_name(subject.getS_name());
					tmp.setId(data3.getRoe_id());
					tmp.setIntroduction(data3.getRoe_introduction());
					tmp.setParticipate(data3.getRoe_participate());
					tmp.setNum(3);
					tmp.setStatus(data3.getRoe_status());
					
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
				
				//合同説明会報告書一覧をlistに格納
				tmp = new ReportInfo();
				
				// 代入
				bc = BeansCompany.detailCompany(data1.getComp_id());
				ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());			
				
				if(data1.getRjm_status().equals("end")){
					
					//登録者のIDから学科名を検索
					user = BeansUser.detailUser(data1.getL_id());
					course = BeansCourse.detailCourse(user.getC_id());
					subject = BeansSubject.detailSubject(course.getS_id());
					
					tmp.setS_name(subject.getS_name());				
					tmp.setId(data1.getRjm_id());
					tmp.setIntroduction(data1.getRjm_introduction());
					tmp.setParticipate(data1.getRjm_participate());
					tmp.setName(data1.getRjm_name());
					tmp.setNum(1);
					tmp.setStatus(data1.getRjm_status());
					
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
			}	
		}
	}
	
	/**
	 * 企業検索の一覧ページに必要な企業説明会報告書を作成します。(企業名から検索用)
	 * @param ArrayList<BeansReportJoinMeeting> 合同説明会報告書一覧
	 */
	public void listSearchCompaniesJoinReport(
			ArrayList<BeansReportJoinMeeting> array){
		
		ListIterator<BeansReportJoinMeeting> iterator = array.listIterator();
		list = new ArrayList<ReportInfo>();
		
		// 一時クラス
		ReportInfo tmp = null;
		BeansCompany bc = null;
		BeansCompanyTrade ct = null;
		BeansUser user;
		BeansCourse course;
		BeansSubject subject;
		
		// データの読み取り
		while(iterator.hasNext()){
			// データを生成
			BeansReportJoinMeeting data = iterator.next();
			bc = BeansCompany.detailCompany(data.getComp_id());
			ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
			tmp = new ReportInfo();
			
			//状態を判定してリストを分ける
			if(data.getRjm_status().equals("end")){
				
				//登録者のIDから学科名を検索
				user = BeansUser.detailUser(data.getL_id());
				course = BeansCourse.detailCourse(user.getC_id());
				subject = BeansSubject.detailSubject(course.getS_id());
				
				tmp.setS_name(subject.getS_name());				
				tmp.setId(data.getRjm_id());
				tmp.setIntroduction(data.getRjm_introduction());
				tmp.setParticipate(data.getRjm_participate());
				tmp.setName(data.getRjm_name());
				tmp.setNum(1);
				tmp.setStatus(data.getRjm_status());
				
				//商号を追加
				if(bc.getCompt_position().equals("first")){
					tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
				}else{
					tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
				}
				list.add(tmp);
			}
		}
	}
	
	/**
	 * 企業検索の一覧ページに必要な筆記試験報告書を作成します。(企業名から検索用)
	 * @param ArrayList<BeansReportWrittenExamination> 筆記試験報告書一覧
	 */
	public void listSearchCompaniesWrittenReport(
			ArrayList<BeansReportWrittenExamination> array){
		
		ListIterator<BeansReportWrittenExamination> iterator = array.listIterator();
		list = new ArrayList<ReportInfo>();
		
		// 一時クラス
		ReportInfo tmp = null;
		BeansCompany bc = null;
		BeansCompanyTrade ct = null;
		BeansUser user;
		BeansCourse course;
		BeansSubject subject;
		
		// データの読み取り
		while(iterator.hasNext()){
			// データを生成
			BeansReportWrittenExamination data = iterator.next();
			bc = BeansCompany.detailCompany(data.getComp_id());
			ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
			tmp = new ReportInfo();
			
			//状態を判定
			if(data.getRwe_status().equals("end")){
				
				//登録者のIDから学科名を検索
				user = BeansUser.detailUser(data.getL_id());
				course = BeansCourse.detailCourse(user.getC_id());
				subject = BeansSubject.detailSubject(course.getS_id());
				
				tmp.setS_name(subject.getS_name());
				tmp.setId(data.getRwe_id());
				tmp.setIntroduction(data.getRwe_introduction());
				tmp.setParticipate(data.getRwe_participate());
				tmp.setNum(2);
				tmp.setStatus(data.getRwe_status());
				
				//商号を追加
				if(bc.getCompt_position().equals("first")){
					tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
				}else{
					tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
				}
				list.add(tmp);
			}
		}		
	}
	
	/**
	 * 企業検索の一覧ページに必要な面接試験報告書を作成します。(企業名から検索用)
	 * @param ArrayList<BeansReportOralExamination> 面接試験報告書一覧
	 */
	public void listSearchCompaniesOralReport(
			ArrayList<BeansReportOralExamination> array){
		
		ListIterator<BeansReportOralExamination> iterator = array.listIterator();
		list = new ArrayList<ReportInfo>();
		
		// 一時クラス
		ReportInfo tmp = null;
		BeansCompany bc = null;
		BeansCompanyTrade ct = null;
		BeansUser user;
		BeansCourse course;
		BeansSubject subject;
		
		// データの読み取り
		while(iterator.hasNext()){
			// データを生成
			BeansReportOralExamination data = iterator.next();
			bc = BeansCompany.detailCompany(data.getComp_id());
			ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
			tmp = new ReportInfo();
			
			//状態を判定
			if(data.getRoe_status().equals("end")){
				
				//登録者のIDから学科名を検索
				user = BeansUser.detailUser(data.getL_id());
				course = BeansCourse.detailCourse(user.getC_id());
				subject = BeansSubject.detailSubject(course.getS_id());
				
				tmp.setS_name(subject.getS_name());	
				tmp.setId(data.getRoe_id());
				tmp.setIntroduction(data.getRoe_introduction());
				tmp.setParticipate(data.getRoe_participate());
				tmp.setNum(3);
				tmp.setStatus(data.getRoe_status());
				
				//商号を追加
				if(bc.getCompt_position().equals("first")){
					tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
				}else{
					tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
				}
				list.add(tmp);
			}			
		}
	}
	
	/**
	 * 三つの報告書を一つにまとめた一覧リストを作成します。(先生ページ用)<br>
	 * プロパティに状態がendのリストとend以外のリストをセットします。
	 * @param ArrayList<BeansReportJoinMeeting> list1 企業説明会報告書のリスト
	 * @param ArrayList<BeansReportWrittenExamination> list2 筆記試験報告書のリスト
	 * @param ArrayList<BeansReportOralExamination> list3 面接試験報告書のリスト
	 */
	public void authorityListReportInfo(ArrayList<BeansReportJoinMeeting> list1 , 
			ArrayList<BeansReportWrittenExamination> list2 , ArrayList<BeansReportOralExamination> list3){
		
		//3つのlistのiterator生成
		ListIterator<BeansReportJoinMeeting> iterator1 = list1.listIterator();
		ListIterator<BeansReportWrittenExamination> iterator2 = list2.listIterator();
		ListIterator<BeansReportOralExamination> iterator3 = list3.listIterator();
		e_list = new ArrayList<ReportInfo>();
		list = new ArrayList<ReportInfo>();
		
		BeansReportJoinMeeting data1 = null;
		BeansReportWrittenExamination data2 = null;
		BeansReportOralExamination data3 = null;
		
		// 一時クラス
		ReportInfo tmp = null;
		BeansCompany bc;
		BeansCompanyTrade ct;
		
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
						
					//面接試験報告書一覧をlistに格納
					tmp = new ReportInfo();
					
					// 代入
					bc = BeansCompany.detailCompany(data3.getComp_id());
					ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
					String id = data3.getL_id();
					BeansUser user = BeansUser.detailUser(id);
					BeansCourse course = BeansCourse.detailCourse(user.getC_id());
					
					tmp.setId(data3.getRoe_id());
					tmp.setU_name(user.getU_name());
					tmp.setC_name(course.getC_name());
					tmp.setIntroduction(data3.getRoe_introduction());
					tmp.setParticipate(data3.getRoe_participate());
					tmp.setNum(3);
					tmp.setStatus(data3.getRoe_status());
					
					//商号を追加
					if(bc.getCompt_position().equals("first")){
						tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
					}else{
						tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
					}
					
					//状態を判定してリストを分ける
					if(!data3.getRoe_status().equals("end")){
						

						// 状態がnewの場合はn_countをカウントアップ
						if(data3.getRoe_status().equals("new")){
							
							n_count++;
				
						//状態が修正済(renew)の時カウントアップ
						}else if(data3.getRoe_status().equals("renew")){
							
							r_count++;
							
						//状態が再提出（again）の時カウントアップ
						}else if(data3.getRoe_status().equals("again")){
							
							a_count++;
							
						}
						
						list.add(tmp);
					
					}else{
					
						e_list.add(tmp);
					
					}
					
					if(iterator3.hasNext()){
						data3 = iterator3.next();
					}else{
						data3 = null;
					}
					
				}else{
						
					//筆記試験報告書一覧をlistに格納
					tmp = new ReportInfo();
						
					//代入
					bc = BeansCompany.detailCompany(data2.getComp_id());
					ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
					String id = data2.getL_id();
					BeansUser user = BeansUser.detailUser(id);
					BeansCourse course = BeansCourse.detailCourse(user.getC_id());
					
					tmp.setId(data2.getRwe_id());
					tmp.setU_name(user.getU_name());
					tmp.setC_name(course.getC_name());
					tmp.setIntroduction(data2.getRwe_introduction());
					tmp.setParticipate(data2.getRwe_participate());
					tmp.setNum(2);
					tmp.setStatus(data2.getRwe_status());
					
					//商号を追加
					if(bc.getCompt_position().equals("first")){
						tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
					}else{
						tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
					}
					
					if(!data2.getRwe_status().equals("end")){
						

						//状態がnewの場合はn_countをカウントアップ
						if(data2.getRwe_status().equals("new")){
							
							n_count++;
				
						//状態が修正済(renew)の時カウントアップ
						}else if(data2.getRwe_status().equals("renew")){
							
							r_count++;
							
						//状態が再提出（again）の時カウントアップ
						}else if(data2.getRwe_status().equals("again")){
							
							a_count++;
						}
						
						list.add(tmp);
					}else{
						e_list.add(tmp);
					}
					
					if(iterator2.hasNext()){
						data2 = iterator2.next();
					}else{
						data2 = null;
					}
				
				}				
			}else if(Integer.parseInt(rjm_introduction) < Integer.parseInt(roe_introduction)){
				
				//面接試験報告書一覧をlistに格納
				tmp = new ReportInfo();
				
				// 代入
				bc = BeansCompany.detailCompany(data3.getComp_id());
				ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
				String id = data3.getL_id();
				BeansUser user = BeansUser.detailUser(id);
				BeansCourse course = BeansCourse.detailCourse(user.getC_id());
				
				tmp.setId(data3.getRoe_id());
				tmp.setU_name(user.getU_name());
				tmp.setC_name(course.getC_name());
				tmp.setIntroduction(data3.getRoe_introduction());
				tmp.setParticipate(data3.getRoe_participate());
				tmp.setNum(3);
				tmp.setStatus(data3.getRoe_status());
				
				//商号を追加
				if(bc.getCompt_position().equals("first")){
					tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
				}else{
					tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
				}
				
				if(!data3.getRoe_status().equals("end")){
					
					
					// 状態がnewの場合はn_countをカウントアップ
					if(data3.getRoe_status().equals("new")){
						
						n_count++;
			
					//状態が修正済(renew)の場合はr_countカウントアップ
					}else if(data3.getRoe_status().equals("renew")){
						
						r_count++;
						
					//状態が再提出(again)の時にはa_countカウントアップ
					}else if(data3.getRoe_status().equals("again")){
						
						a_count++;
						
					}
					
					list.add(tmp);
					
				}else{
			
					e_list.add(tmp);
				
				}
				
				if(iterator3.hasNext()){
					data3 = iterator3.next();
				}else{
					data3 = null;
				}
			
			}else{
				
				//合同説明会報告書一覧をlistに格納
				tmp = new ReportInfo();
				
				// 代入
				bc = BeansCompany.detailCompany(data1.getComp_id());
				ct = BeansCompanyTrade.detailCompanyTrade(bc.getCompt_id());
				String id = data1.getL_id();
				BeansUser user = BeansUser.detailUser(id);
				BeansCourse course = BeansCourse.detailCourse(user.getC_id());
				
				tmp.setId(data1.getRjm_id());
				tmp.setU_name(user.getU_name());
				tmp.setC_name(course.getC_name());
				tmp.setIntroduction(data1.getRjm_introduction());
				tmp.setParticipate(data1.getRjm_participate());
				tmp.setName(data1.getRjm_name());
				tmp.setNum(1);
				tmp.setStatus(data1.getRjm_status());
				
				//商号を追加
				if(bc.getCompt_position().equals("first")){
					tmp.setComp_name(ct.getCompt_name() + bc.getComp_name());
				}else{
					tmp.setComp_name(bc.getComp_name() + ct.getCompt_name());
				}
				
				//状態が登録(end)でない場合
				if(!data1.getRjm_status().equals("end")){
					
					
					//状態がnewの場合はn_countをカウントアップ
					if(data1.getRjm_status().equals("new")){
						
						n_count++;
			
					//状態がrenewの場合はr_countをカウントアップ
					}else if(data1.getRjm_status().equals("renew")){
						
						r_count++;
						
					}else if(data1.getRjm_status().equals("again")){
						
						a_count++;
					
					}
					
					list.add(tmp);
					
				}else{
					
					e_list.add(tmp);
				
				}
				
				if(iterator1.hasNext()){
					data1 = iterator1.next();
				}else{
					data1 = null;
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
	 * @return s_name
	 */
	public String getS_name() {
		return s_name;
	}

	/**
	 * @param s_name セットする s_name
	 */
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	/**
	 * @return del セット
	 */
	public String getDel() {
		return del;
	}

	/**
	 * @param del
	 */
	public void setDel(String del) {
		this.del = del;
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
	public ArrayList<ReportInfo> getList() {
		return list;
	}

	/**
	 * @param list セットする list
	 */
	public void setList(ArrayList<ReportInfo> list) {
		this.list = list;
	}

	/**
	 * @return e_list
	 */
	public ArrayList<ReportInfo> getE_list() {
		return e_list;
	}

	/**
	 * @param e_list セットする e_list
	 */
	public void setE_list(ArrayList<ReportInfo> e_list) {
		this.e_list = e_list;
	}
}
