/**
 * 専門学校ビーマックス 2011年度 卒業制作<br>
 *　Controller: Servlet: Xss<br>
 * make: 6/October/2011
 * @author kouduki
 */
package jp.bemax.se.graduation2011.auth;

public class Xss {
	/**
	 * デフォルトコンストラクタ
	 */
	public Xss(){
		
		super();
	}
	
	public String escape(String value){
		
		//配列に格納された値を一つずつ取り出す
			
		StringBuffer result = new StringBuffer();
		//取り出した値を一文字ずつ変換"
		if(value != null){
			for(int j = 0 ; j < value.length() ; j++){
					
				switch(value.charAt(j)){
						
				case '&' :
					result.append("&amp;");
					break;
					
				case '<' :
					result.append("&lt;");
					break;
						
				case '>' :
					result.append("&gt;");
					break;
				case '\'' :
					result.append("&#39;");
					break;
				case '\"' : 
					result.append("&quot;");
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
}