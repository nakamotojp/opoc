function createWindow(){
	var mWinName = 'SelectCompany';
	var mOption = 'width=815, height=450, location=no, menubar=no, toolbar=no, scrollbars=yes, resizable=yes';
	window.open("./student/CompanyApi.jsp", mWinName, mOption);
}
function selectWindow(){
	var mWinName = 'SelectCompany';
	var mOption = 'width=815, height=450, location=no, menubar=no, toolbar=no, scrollbars=yes, resizable=yes';
	window.open("student/CompanySelect.jsp", mWinName, mOption);
}

function requestCompany(){
	// XMLHttpRequest生成
	// IE7以上の一般的なブラウザでは使用可能
	var xhr = new XMLHttpRequest();
	
	// XMLHttpRequestのチェック
	if(xhr == null){
		// IE7以前又はXMLHttpRequestに非対応のブラウザ
		return;
	}
	
	var comp_name = document.getElementById("Company").value;
	
	if(comp_name == null || comp_name.length == 0){
		document.getElementById("CompanyMSG").innerHTML = "内容を入力してください";
		
		// 値の削除
		document.getElementById("Company").removeAttribute("VALUE");
		
		return;
	}
	
	// 通信
	xhr.open("GET", "../api/CompanyAjax?comp_name=" + encodeURI(comp_name));
	xhr.onreadystatechange =
		function(){
			// 通信チェック
			if(xhr.readyState == 4 && xhr.status == 200){
				loadCompany(this.responseText);
			}
		};
	xhr.send();
}

function loadCompany(req){
	// Jsonへ変換
	data = eval('(' + req + ')');
	
	var html = '企業が見つかりませんでした';
	
	if(data.company.length != 0){
		html = '<TABLE>';
		
		// 企業一覧
		for(var i = 0; i < data.company.length; i++){
			if(data.company.length - i == 1){
				html += '<TR class="bottom-none">';
			}else{
				html += '<TR>';
			}
			html += '<TD class="left-none"><BUTTON TYPE="button" onclick="selectCompany(' + i + ')">選択</BUTTON></TD>';
			if(data.company[i].compt_position == "first"){
				html += '<TD class="name"><div>' + data.company[i].compt_name + x_Check(data.company[i].comp_name) + '</div></td>';
			}else{
				html += '<TD class="name"><div>' + x_Check(data.company[i].comp_name) + data.company[i].compt_name + '</div></td>';
			}
			html += '<TD class="zip">' + data.company[i].comp_zip + '</td>';
			html += '<TD class="name"><div>' + data.company[i].comp_address + '</div></td>';
			html += '<TD class="right-none">' + data.company[i].comp_phone + '</td>';
			html += '<TR>';
		}
		
		html += '</TABLE>';
	}
	
	// 出力
	document.getElementById("CompanyMSG").innerHTML = html;
	
	return;
}

function selectCompany(index){
	if(window.opener == null || window.opener.closed){
		document.getElementById("CompanyMSG").innerHTML = "親ウィンドウが閉じられています";
		return;
	}
	
	// 選択されたデータを親ウィンドウにセット
	var ele = window.opener.document.getElementById("CompanyValue");
	var textbox = window.opener.document.getElementById("Company");
	var companyName = window.opener.document.getElementById("CompanyName");

	// 値の削除
	textbox.removeAttribute("VALUE");
	ele.removeAttribute("VALUE");
	companyName.innerHTML = "";
	
	if(data.company[index].compt_position == "first"){
		// 値のセット
		textbox.setAttribute("VALUE", data.company[index].compt_name + x_Check(data.company[index].comp_name));
//		textbox.value = data.company[index].compt_name + data.company[index].comp_name;
		companyName.innerHTML = data.company[index].compt_name + x_Check(data.company[index].comp_name);
	}else{
		// 値のセット
		textbox.setAttribute("VALUE", x_Check(data.company[index].comp_name) + data.company[index].compt_name);
//		textbox.value = data.company[index].comp_name + data.company[index].compt_name;
		companyName.innerHTML = x_Check(data.company[index].comp_name) + data.company[index].compt_name;
	}
	
	// 値のセット
	ele.setAttribute("VALUE", data.company[index].comp_id);
	
	// Windowを閉じる
	window.close();
	
	return;
}

function CreateCompany(){
	// XMLHttpRequest生成
	// IE7以上の一般的なブラウザでは使用可能
	var xhr = new XMLHttpRequest();
	
	// XMLHttpRequestのチェック
	if(xhr == null){
		// IE7以前又はXMLHttpRequestに非対応のブラウザ
		alert("通信できませんでした");
		return false;
	}
	
	var requestData = 'compt_id=' + document.getElementById("compt_id").value;
	requestData += '&compt_position=' + document.getElementById("compt_position").value;
	requestData += '&comp_name=' + document.getElementById("comp_name").value;
	requestData += '&comp_zip=' + document.getElementById("comp_zip").value;
	requestData += '&comp_address=' + document.getElementById("comp_address").value;
	requestData += '&comp_phone01=' + document.getElementById("comp_phone01").value;
	requestData += '&comp_phone02=' + document.getElementById("comp_phone02").value;
	requestData += '&comp_phone03=' + document.getElementById("comp_phone03").value;
	
	xhr.open("POST", "../api/CompanyCreateAjax", false);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=utf-8'); 
	xhr.send(requestData);
	
	data = eval('(' + xhr.responseText + ')');
	if(data.company.length != 0){
		selectCompany(0);
	}else{
		alert("登録に失敗しました");
	}

	return;
}

function chkcompany(){
	var tmp = document.getElementById("CompanyValue").value;
	
	if(tmp > 0){
		return ;
	}else{
		alert("企業を選択してください");
		return false;
		//submit()をトリガーにしてバリデーション起動
		submit();
		
	}
}
function x_Check(box){
	box = box.replace(/&/g, '&amp;');
	box = box.replace(/>/g, '&gt;');
	box = box.replace(/</g, '&lt;');
	
	return box;
}
function loadcompany(){
	//divに値を渡す
	document.getElementById("CompanyName").innerHTML = document.getElementById("Company").value;
}