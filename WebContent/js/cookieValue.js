function saveValue(){
	var data = "{\"form\":[";
	for(var i = 0; i < document.forms[0].elements.length; i++){
		if(document.forms[0].elements[i].name != ""){
			if(i > 0){
				data += ",";
			}
			switch(document.forms[0].elements[i].type){
				case 'text':
				case 'textarea':
				case 'hidden':
					data += "{\"name\":\"" + document.forms[0].elements[i].name + "\",";
					data += "\"type\":\"" + document.forms[0].elements[i].type + "\",";
					data += "\"value\":\"" + escape(document.forms[0].elements[i].value) + "\"}";
					break;
				case 'radio':
					data += "{\"name\":\"" + document.forms[0].elements[i].name + "\",";
					data += "\"type\":\"" + document.forms[0].elements[i].type + "\",";
					data += "\"value\":\"" + escape(document.forms[0].elements[i].value) + "\",";
					data += "\"checked\":" + document.forms[0].elements[i].checked + "}";
					break;
//				case 'select-one':
//					data += "{\"name\":\"" + document.forms[0].elements[i].name + "\",";
//					data += "\"type\":\"" + document.forms[0].elements[i].type + "\",";
//					data += "\"value\":\"" + escape(document.forms[0].elements[i].value) + "\",";
//					data += "\"selectedIndex\":" + document.forms[0].elements[i].selectedIndex + "}";
//					break;
				default:
					data += "{}";
					break;
			}
		}
	}
	data += "]}";
	data = encodeURI(data, "utf-8");
	document.cookie = "formData=" + data;
}

function loadValue(){
	var data = document.cookie;
	data = data.split(";");
	for(var i = 0; i < data.length; i++){
		if(data[i].indexOf("formData=") != -1){
			data = data[i];
			break;
		}else{
			continue;
		}
	}
	if(data.indexOf("formData=") == -1){
		return;
	}
	data = data.split("formData=");
	data = data[1];
	if(data == ""){
		return;
	}
	data = decodeURI(data, "utf-8");
	data = eval('(' + data + ')');
	for(var i = 0; i < document.forms[0].elements.length; i++){
		if(document.forms[0].elements[i].name != ""){
			for(var j = 0; j < data.form.length; j++){
				if((data.form[j] != null) && (data.form[j].name == document.forms[0].elements[i].name) && (data.form[j].type == document.forms[0].elements[i].type)){
					switch(data.form[j].type){
						case 'text':
						case 'textarea':
						case 'hidden':
							document.forms[0].elements[i].value = data.form[j].value;
							break;
						case 'radio':
							if(document.forms[0].elements[i].value == data.form[j].value){
								document.forms[0].elements[i].checked = data.form[j].checked;
							}
							break;
//						case 'select-one':
//							for(var k = 0; k < document.forms[0].elements[i].childNodes.length; k++){
//								if(document.forms[0].elements[i].item(k).value == data.form[j].value){
//									document.forms[0].elements[i].selectedIndex = k;
//									break;
//								}
//							}
//							break;
						default:
							break;
					}
				}
			}
		}
	}
}

function escape(str){
	if(str != ""){
//		str = str.replace(/(\n|\r)/g, "\\r\\n");
		str = str.replace(/(\n\r)/g, "\\r\\n");
		str = str.replace(/(\r)/g, "\\r");
		str = str.replace(/(\n)/g, "\\n");
	}
	return str;
}

function deleteValue(){
	name = "formData=";
	date = new Date();
	date.setYear(date.getYear() - 1);
	document.cookie = name + ";expires=" + date.toGMTString();
}
