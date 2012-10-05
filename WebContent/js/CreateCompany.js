function requestCompanyTrade(){
	// XMLHttpRequest生成
	// IE7以上の一般的なブラウザでは使用可能
	var xhr = new XMLHttpRequest();
	
	// XMLHttpRequestのチェック
	if(xhr == null){
		// IE7以前又はXMLHttpRequestに非対応のブラウザ
		return;
	}
	
	// 通信
	xhr.open("GET", "../api/CompanyTradeAjax");
	xhr.onreadystatechange =
		function(){
			// 通信チェック
			if(xhr.readyState == 4 && xhr.status == 200){
				loadCompanyTrade(this.responseText);
			}
		};
	xhr.send();
};

function loadCompanyTrade(req){
	// Jsonへ変換
	data = eval('(' + req + ')');
	
	// Subject ID 取得
	var ele = document.getElementById("CompanyTrade");
	var compt_id = null;
	var compt_position = null;
	if(document.getElementById("compt_id").value != null && document.getElementById("compt_position").value != null){
		compt_id = document.getElementById("compt_id").value;
		compt_position = document.getElementById("compt_position").value;
	}
	
	// 出力
	for(var i = 0; i < data.companytrade.length; i++){
		var optf = document.createElement("OPTION");
		optf.setAttribute("VALUE", data.companytrade[i].compt_id);
		optf.innerHTML = data.companytrade[i].compt_name + "○○○○";
		if(compt_id != null && compt_id == data.companytrade[i].compt_id && compt_position == "first"){
			optf.setAttribute("selected", "selected");
			document.getElementById("compt_name").value = data.companytrade[i].compt_name;
		}
		ele.appendChild(optf);
		var optl = document.createElement("OPTION");
		optl.setAttribute("VALUE", data.companytrade[i].compt_id);
		optl.innerHTML = "○○○○" + data.companytrade[i].compt_name;
		if(compt_id != null && compt_id == data.companytrade[i].compt_id && compt_position == "last"){
			optf.setAttribute("selected", "selected");
			document.getElementById("compt_name").value = data.companytrade[i].compt_name;
		}
		ele.appendChild(optl);
	}
	
	return;
}

function selectCompanyTrade(){
	// 種類の取得
	var companytrade = document.getElementById("CompanyTrade");
	var tradename = document.getElementById("compt_id");
	var c_name= document.getElementById("compt_name");
	var tradeposition = document.getElementById("compt_position");
	var tradenamefirst = document.getElementById("TradeNameFirst");
	var tradenamelast = document.getElementById("TradeNameLast");
	var compt_id = companytrade.options[companytrade.options.selectedIndex].value;
	var compt_position = null;
		
	// 前後の判定
	if((companytrade.options.selectedIndex - 1) % 2 == 0){
		compt_position = "first";
	} else {
		compt_position = "last";
	}
	
	// 選択されたデータを親ウィンドウにセット
	// 値の削除
	tradename.removeAttribute("VALUE");
	// 値のセット
	tradename.setAttribute("VALUE", compt_id);
	
	// 値の削除
	tradeposition.removeAttribute("VALUE");
	// 値のセット
	tradeposition.setAttribute("VALUE", compt_position);
	
	for(var i = 0; i < data.companytrade.length; i++){
		if(data.companytrade[i].compt_id == compt_id){
			// 値の削除
			c_name.removeAttribute("VALUE");
			// 値のセット
			c_name.setAttribute("VALUE", data.companytrade[i].compt_name);
			
			if(compt_position == "first"){
				tradenamefirst.innerHTML = data.companytrade[i].compt_name;
				tradenamelast.innerHTML = "";
			}else{
				tradenamelast.innerHTML = data.companytrade[i].compt_name;
				tradenamefirst.innerHTML = "";
			}
			
			break;
		}
	}
}

function changeCompanyName(){
	// 名前
	document.getElementById("CompanyName").innerHTML = document.getElementById("Company").value;
}