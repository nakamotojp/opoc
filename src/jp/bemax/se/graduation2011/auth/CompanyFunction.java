package jp.bemax.se.graduation2011.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.bemax.se.graduation2011.model.BeansCompany;

public class CompanyFunction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  企業ID
	 */
	private int comp_id = -1;
	
	/**
	 *  商号の種類
	 */
	private int compt_id = -1;
	
	/**
	 *  商号の位置
	 */
	private String compt_position = null;
	
	/**
	 *  企業名
	 */
	private String comp_name = null;
	
	/**
	 *  郵便番号
	 */
	private String comp_zip = null;
	
	/**
	 *  住所
	 */
	private String comp_address = null;
	
	/**
	 *  電話番号
	 */
	private String comp_phone = null;
	
	/**
	 *  コンストラクタ
	 */
	public CompanyFunction(){
		super();
	}
	
	
	
	
	

	/**
	 * 企業情報を登録します。<br>
	 * 企業情報がデータベースに存在している場合、<br>
	 * そのIDを返します。<br>
	 * 登録に必要な値をプロパティにセットして下さい。
	 * @return comp_id 登録した企業情報のidを返します。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// エンコード指定
		request.setCharacterEncoding("utf-8");
		// コンテンツタイプ指定
		response.setContentType("text/html; charset=utf-8");
		
		//xss対策のクラスをインスタンス化
		Xss xss = new Xss();
		
		BeansCompany bc = new BeansCompany();
		
		compt_id = Integer.parseInt(request.getParameter("compt_id"));
		compt_position = xss.escape(request.getParameter("compt_position"));
		comp_name = xss.escape(request.getParameter("comp_name"));
		comp_zip = xss.escape(request.getParameter("comp_zip"));
		comp_address = xss.escape(request.getParameter("comp_address"));
		comp_phone = xss.escape(request.getParameter("comp_phone01")) + "-" + xss.escape(request.getParameter("comp_phone02")) + "-" + xss.escape(request.getParameter("comp_phone03"));
		
		//必要な値をセット
		bc.setCompt_id(compt_id);
		bc.setCompt_position(compt_position);
		bc.setComp_name(comp_name);
		bc.setComp_zip(comp_zip);
		bc.setComp_address(comp_address);
		bc.setComp_phone(comp_phone);
		
		//登録
		bc.createCompany();
		
		response.sendRedirect("./CompanyApi.jsp");
	}

	
	
	
	
	
	
	
	/**
	 * 企業情報を登録します。<br>
	 * 企業情報がデータベースに存在している場合、<br>
	 * そのIDを返します。<br>
	 * 登録に必要な値をプロパティにセットして下さい。
	 * @return comp_id 登録した企業情報のidを返します。
	 */
	public int create(){
		
		BeansCompany bc = new BeansCompany();
		
		ArrayList<BeansCompany> list = BeansCompany.searchCompany(comp_name);
		ListIterator<BeansCompany> iterator = list.listIterator();
		
		while(iterator.hasNext()){
			
			BeansCompany company = iterator.next();
			if(comp_name.equals(company.getComp_name()) && comp_zip.equals(company.getComp_zip())
					&& comp_address.equals(company.getComp_address())){
				
				comp_id = company.getComp_id();
				return comp_id;
				
			}
		}
		
		//必要な値をセット
		bc.setCompt_id(compt_id);
		bc.setCompt_position(compt_position);
		bc.setComp_name(comp_name);
		bc.setComp_zip(comp_zip);
		bc.setComp_address(comp_address);
		bc.setComp_phone(comp_phone);
		
		//登録
		bc.createCompany();
		comp_id = bc.getComp_id();
		return comp_id;
	}
	
	public int change(int comp_id){
		
		BeansCompany bc = new BeansCompany();
		
		ArrayList<BeansCompany> list = BeansCompany.searchCompany(this.comp_name);
		ListIterator<BeansCompany> iterator = list.listIterator();
		
		while(iterator.hasNext()){
			
			BeansCompany company = iterator.next();
			if(this.comp_name.equals(company.getComp_name()) && this.comp_zip.equals(company.getComp_zip())
					&& this.comp_address.equals(company.getComp_address())){
				
				//必要な値をセット
				bc.setComp_id(comp_id);
				bc.setCompt_id(this.compt_id);
				bc.setCompt_position(this.compt_position);
				bc.setComp_zip(this.comp_zip);
				bc.setComp_address(this.comp_address);
				bc.setComp_phone(this.comp_phone);
				bc.changeCompany();
				
				return comp_id;
				
			}
		}
			
		//必要な値をセット
		bc.setCompt_id(compt_id);
		bc.setCompt_position(compt_position);
		bc.setComp_name(comp_name);
		bc.setComp_zip(comp_zip);
		bc.setComp_address(comp_address);
		bc.setComp_phone(comp_phone);
		bc.createCompany();
		this.comp_id = bc.getComp_id();
		
		return this.comp_id;		
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
	 * @return compt_id
	 */
	public int getCompt_id() {
		return compt_id;
	}

	/**
	 * @param compt_id セットする compt_id
	 */
	public void setCompt_id(int compt_id) {
		this.compt_id = compt_id;
	}

	/**
	 * @return compt_position
	 */
	public String getCompt_position() {
		return compt_position;
	}

	/**
	 * @param compt_position セットする compt_position
	 */
	public void setCompt_position(String compt_position) {
		this.compt_position = compt_position;
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
	 * @return comp_zip
	 */
	public String getComp_zip() {
		return comp_zip;
	}

	/**
	 * @param comp_zip セットする comp_zip
	 */
	public void setComp_zip(String comp_zip) {
		this.comp_zip = comp_zip;
	}

	/**
	 * @return comp_address
	 */
	public String getComp_address() {
		return comp_address;
	}

	/**
	 * @param comp_address セットする comp_address
	 */
	public void setComp_address(String comp_address) {
		this.comp_address = comp_address;
	}

	/**
	 * @return comp_phone
	 */
	public String getComp_phone() {
		return comp_phone;
	}

	/**
	 * @param comp_phone セットする comp_phone
	 */
	public void setComp_phone(String comp_phone) {
		this.comp_phone = comp_phone;
	}

}