function requestLogin(){
	// XMLHttpRequest生成
	// IE7以上の一般的なブラウザでは使用可能
	var xhr = new XMLHttpRequest();
	
	// XMLHttpRequestのチェック
	if(xhr == null){
		// IE7以前又はXMLHttpRequestに非対応のブラウザ
		document.getElementById("LoginMSG").innerHTML = "通信できませんでした";
		return;
	}
	
	l_id = document.getElementById("Login").value;
	
	if(l_id == null || l_id.length == 0){
		document.getElementById("LoginMSG").innerHTML = "ログインIDを入力してください";
		
		// 値の削除
		document.getElementById("Login").removeAttribute("VALUE");
		
		return;
	}
	
	// 文字列チェック(正規表現)
	if(l_id.match("[^(0-9a-zA-Z)]+")){
		document.getElementById("LoginMSG").innerHTML = "使用可能な文字列は半角英数です。";
		return;
	}
	
	// 通信
	xhr.open("GET", "api/LoginAjax?l_id=" + l_id);
	xhr.onreadystatechange =
		function(){
			// 通信チェック
			if(xhr.readyState == 4 && xhr.status == 200){
				checkLogin(this.responseText);
			} else {
				document.getElementById("LoginMSG").innerHTML = "通信できませんでした";
			}
		};
	xhr.send();
}

function checkLogin(req){
	// Jsonへ変換
	var data = eval('(' + req + ')');
	
	// Login ID 取得
	var ele = document.getElementById("LoginValue");
	var msgele = document.getElementById("LoginMSG");
	var msg = null;
	
	// 出力
	if(data.l_id == "false"){
		msg = " " + l_id + " は使用可能です";
	} else {
		msg = " " + l_id + " は使用できません";
		l_id = "";
	}
	
	// 値の削除
	ele.removeAttribute("VALUE");
	// 値のセット
	ele.setAttribute("VALUE", l_id);
	
	// メッセージ出力
	msgele.innerHTML = msg;
	
	return;
}