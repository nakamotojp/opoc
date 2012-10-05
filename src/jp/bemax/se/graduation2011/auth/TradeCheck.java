/**
 * 
 */
package jp.bemax.se.graduation2011.auth;

import java.util.ArrayList;
import java.util.ListIterator;

import jp.bemax.se.graduation2011.model.BeansCompanyTrade;

/**
 * @author masaya0909
 *
 */
public class TradeCheck {
	//商号の位置
	private String compt_position = null;
	//商号名
	private String compt_name = null;
	//商号ID
	private int compt_id = 0;
	
	/**
	 * コンストラクタ
	 */
	public TradeCheck(){
		super();
	}
	
	/**
	 * 商号をチェックします。<br>
	 * parameterで取得した商号名を引数に入れると<br>
	 * プロパティに商号の位置と、商号名が格納されます。
	 * @param compt_name 商号名
	 */
	public void tradecheck(String compt_name){
	
		int i = 1;
		for(int j = 0 ; j < compt_name.length() ; j++){
					
			//一文字ずつ取り出し
			switch(compt_name.charAt(j)){
				
			//文字が○か検索
			case '○' :
				break;					
					
			//文字が株か検索する
			case '株' :
				//前株か後株か検索する
				if(j == 0){
					compt_position = "first";
				}else{
					compt_position = "last";
				}
				this.compt_name = "株式会社";
				return;
					
			//文字が有か検索する
			case '有' :
				if(j == 0){
					compt_position = "first";
				}else{
					compt_position = "last";
				}
				compt_position = "first";
				this.compt_name = "有限会社";
				return;
				
			//次の文字を検索する
			case '合' :
				if(j == 0){
					compt_position = "first";
				}else{
					compt_position = "last";
				}
				i = j + 1;
				switch(compt_name.charAt(i)){
					
				case '名' :
					this.compt_name = "合名会社";
					return;
						
				case '資' :
					this.compt_name = "合資会社";
					return;
					
				case '同' :
					this.compt_name = "合同会社";
					return;
				}
			}
		}
	}
	
	/**
	 * 商号名から商号IDを取得します<br>
	 * parameterで取得した商号名を引数に入れると<br>
	 * プロパティに商号IDが格納されます。<br>
	 * @param compt_name 商号名
	 */
	public void searchTradeId(String compt_name){
		
		//必要な値を取得
		ArrayList<BeansCompanyTrade> trade_list = BeansCompanyTrade.listCompanyTrade();
		ListIterator<BeansCompanyTrade> trade_iterator = trade_list.listIterator();

		//引数とDatabaseを照合
		while(trade_iterator.hasNext()){
				
			BeansCompanyTrade bct = trade_iterator.next();
			if(bct.getCompt_name().equals(compt_name)){
				this.compt_id = bct.getCompt_id();
				return;
			}
		}			
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
	 * @return compt_name
	 */
	public String getCompt_name() {
		return compt_name;
	}
	/**
	 * @param compt_name セットする compt_name
	 */
	public void setCompt_name(String compt_name) {
		this.compt_name = compt_name;
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
}

