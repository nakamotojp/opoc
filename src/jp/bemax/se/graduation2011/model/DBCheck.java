/**
 * 
 */
package jp.bemax.se.graduation2011.model;

import java.io.UnsupportedEncodingException;

/**
 * データベースの型に関するチェック処理を行うクラス
 * @author Takenaka
 */
class DBCheck {
	/*
	 * Method
	 */
	/**
	 * int
	 * @param num チェックする数字
	 * @param isNotNull not nullを許可しない
	 * @param isNotZero 数値0を許可しない
	 */
	protected static boolean number(int num, boolean isNotNull){
		boolean result = false;
		
		if(num > -1 || num < 2147483647){
			result = true;
		}
		
		if(isNotNull){
			if(num < 0){
				result = false;
			}
		}
		
		return result;
	}
	
	/**
	 * varchar(255)
	 * @param str チェックする文字列
	 * @param isNotNull not nullを許可しない
	 * @param isNotZero 0文字を許可しない
	 */
	protected static boolean vchar(String str, boolean isNotNull){
		boolean result = false;
		
		if(str == null){
			if(!isNotNull){
				result = true;
			}
		}else{
			if(str.length() >= 0 || str.length() <= 255){
				if(isNotNull){
					if(str.length() > 0){
						result = true;
					}
				}else{
					result = true;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * text
	 * @param str チェックする文字列
	 * @param isNotNull not nullを許可しない
	 * @param isNotZero 0文字を許可しない
	 */
	protected static boolean text(String str, boolean isNotNull){
		boolean result = false;
		
		if(str == null){
			if(!isNotNull){
				result = true;
			}
		}else{
			int length = -1;
			try {
				length = str.getBytes("utf-8").length;
			} catch (UnsupportedEncodingException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
			if(length >= 0 || length <= 65535){
				if(isNotNull){
					if(length > 0){
						result = true;
					}
				}else{
					result = true;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * enum('yes', 'no')
	 * @param str チェックする文字列
	 */
	protected static boolean yesNo(String str){
		boolean result = false;
		
		if(str != null){
			if(str.equals("yes") || str.equals("no")){
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	 * enum('new', 'again', 'renew', 'end')
	 * @param str チェックする文字列
	 */
	protected static boolean reportStatus(String str){
		boolean result = false;
		
		if(str != null){
			if(str.equals("new") || str.equals("again") ||
				str.equals("renew") || str.equals("end")){
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	 * enum('first', 'last')
	 * @param str チェックする文字列
	 */
	protected static boolean reportPosition(String str){
		boolean result = false;
		
		if(str != null){
			if(str.equals("first") || str.equals("last")){
				result = true;
			}
		}
		
		return result;
	}
}
