/**
 * 値をXSSに変換する処理
 */
function x_Set(){
	
	//input 
	var x_value = document.getElementsByTagName("INPUT");
	
	for(var i = 0; i < x_value.length; i++){
		x_value[i].value = x_Check(x_value[i].value);
	}
	
	//テキストエリア
	x_value = document.getElementsByTagName("TEXTAREA");
	
	for(var i = 0; i < x_value.length; i++){
		x_value[i].value = x_Check(x_value[i].value);
	}
	
}

function x_Check(box){
	box = box.replace(/&/g, '&amp;');
	box = box.replace(/>/g, '&gt;');
	box = box.replace(/</g, '&lt;');
	
	return box;
}

/*
 * メモ
 * get elements by tag name
 * jquery or ぷろとたいぷ 使わなければ dom
 * 
 * if テキスト<tag>であればcheckになげる
 * .イーチ　$('input:text' docment.form[1]);
*/

/**
 * XSSを変換した値を戻す処理
 */
function c_Set(){
	//input 
	var c_value = document.getElementsByTagName("INPUT");
	
	for(var i = 0; i < c_value.length; i++){
		c_value[i].value = c_Check(c_value[i].value);
	}
	
	//テキストエリア
	c_value = document.getElementsByTagName("TEXTAREA");
	
	for(var i = 0; i < c_value.length; i++){
		c_value[i].value = c_Check(c_value[i].value);
	}
	
}
			
function c_Check(box){
	box = box.replace(/&amp;/g, '&');
	box = box.replace(/&gt;/g, '>');
	box = box.replace(/&lt;/g, '<');
	
	return box;
}
