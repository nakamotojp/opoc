function requestSubject(){
	// XMLHttpRequest生成
	// IE7以上の一般的なブラウザでは使用可能
	var xhr = new XMLHttpRequest();
	
	// XMLHttpRequestのチェック
	if(xhr == null){
		// IE7以前又はXMLHttpRequestに非対応のブラウザ
		return;
	}
	
	// 通信
	xhr.open("GET", "api/SubjectAjax");
	xhr.onreadystatechange =
		function(){
			// 通信チェック
			if(xhr.readyState == 4 && xhr.status == 200){
				loadSubject(this.responseText);
			}
		};
	xhr.send();
}

function loadSubject(req){
	// Jsonへ変換
	var data = eval('(' + req + ')');
	
	// Subject ID 取得
	var ele = document.getElementById("Subject");
	
	// 出力
	for(var i = 0; i < data.subject.length; i++){
		var opt = document.createElement("OPTION");
		opt.setAttribute("VALUE", data.subject[i].s_id);
		opt.innerHTML = data.subject[i].s_name;
		ele.appendChild(opt);
	}
	
	return;
}