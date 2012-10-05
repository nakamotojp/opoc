//
// property
//
//提出日
var introduction = {
	year:0,
	month:0,
	day:0
};
// 参加日
var participate = {
	year:0,
	month:0,
	day:0
};
// 合否連絡
var accept = {
	year:0,
	month:0,
	day:0
};
//次回受験
var nexttime = {
	year:0,
	month:0,
	day:0
};


//
// method
//
// フォーマット
function format(value){
	var tmp = new String(value);
	if(!tmp.match(/0/)){
		// 2ケタに統一
		if(value < 10){
			value = "0" + value;
		}
	}
	
	return value;
}

//最後の日
function lastDay(year, month){
	var lastday = 0;
	var tmp = new Date(year, month , 0);
	
	lastday = tmp.getDate();
	
	return lastday;
}

// participate Year Select Element Delete
function participateYearDelete(){
	var element = document.getElementById("participate_year");
	var length = element.length;
	participate.year = 0;
	
	for(var i = 0; i < length - 1; i++){
		element.removeChild(element.lastChild);
	}
	
	participateMonthDelete();
}
// participate Month Select Element Delete
function participateMonthDelete(){
	var element = document.getElementById("participate_month");
	var length = element.length;
	participate.month = 0;
	
	for(var i = 0; i < length - 1; i++){
		element.removeChild(element.lastChild);
	}
	
	participateDayDelete();
}
// participate Day Select Element Delete
function participateDayDelete(){
	var element = document.getElementById("participate_day");
	var length = element.length;
	participate.day = 0;
	
	for(var i = 0; i < length - 1; i++){
		element.removeChild(element.lastChild);
	}
	
	if(document.getElementById("accept_year") == null){
		nexttimeYearDelete();
	}else{
		acceptYearDelete();
	}
}

// accept Year Select Element Delete
function acceptYearDelete(){
	var element = document.getElementById("accept_year");
	var length = element.length;
	accept.year = 0;
	
	for(var i = 0; i < length - 1; i++){
		element.removeChild(element.lastChild);
	}
	
	acceptMonthDelete();
}
// accept Month Select Element Delete
function acceptMonthDelete(){
	var element = document.getElementById("accept_month");
	var length = element.length;
	accept.month = 0;
	
	for(var i = 0; i < length - 1; i++){
		element.removeChild(element.lastChild);
	}
	
	acceptDayDelete();
}
// accept Day Select Element Delete
function acceptDayDelete(){
	var element = document.getElementById("accept_day");
	var length = element.length;
	accept.day = 0;
	
	for(var i = 0; i < length - 1; i++){
		element.removeChild(element.lastChild);
	}
	
	nexttimeYearDelete();
}

// nexttime Year Select Element Delete
function nexttimeYearDelete(){
	var element = document.getElementById("nexttime_year");
	var length = element.length;
	nexttime.year = 0;
	
	for(var i = 0; i < length - 1; i++){
		element.removeChild(element.lastChild);
	}
	
	nexttimeMonthDelete();
}
// nexttime Month Select Element Delete
function nexttimeMonthDelete(){
	var element = document.getElementById("nexttime_month");
	var length = element.length;
	nexttime.month = 0;
	
	for(var i = 0; i < length - 1; i++){
		element.removeChild(element.lastChild);
	}
	
	nexttimeDayDelete();
}
// nexttime Day Select Element Delete
function nexttimeDayDelete(){
	var element = document.getElementById("nexttime_day");
	var length = element.length;
	nexttime.day = 0;
	
	for(var i = 0; i < length - 1; i++){
		element.removeChild(element.lastChild);
	}
}

// 提出日
function introductionLoad(){
	// 現在の日時取得
	var date = new Date();
	
	// propertyにSet
	// 年
	introduction.year = date.getYear();
	// 月
	introduction.month = format(date.getMonth() + 1);
	// 日
	introduction.day = format(date.getDate());
	
	// 年数計算
	if(introduction.year < 2000){
		introduction.year += 1900;
	}
	
	// 値をセット
	document.getElementById("introduction_year_value").innerHTML = introduction.year;
	document.getElementById("introduction_year").value = introduction.year;
	document.getElementById("introduction_month_value").innerHTML = introduction.month;
	document.getElementById("introduction_month").value = introduction.month;
	document.getElementById("introduction_day_value").innerHTML = introduction.day;
	document.getElementById("introduction_day").value = introduction.day;
	
	// 参加日(年)をセット
	participateYearLoad();
}

// 参加日(年)
function participateYearLoad(){
	// 変更の為の削除
	participateYearDelete();
	
	var element = document.getElementById("participate_year");
	
	var tmp = null;
	for(var i = 0; i < 2; i++){
		tmp = document.createElement("option");
		tmp.setAttribute("value", introduction.year - i);
		tmp.innerHTML = introduction.year - i;
		element.appendChild(tmp);
	}
}

// 参加日(月)
function participateMonthLoad(){
	// 変更の為の削除
	participateMonthDelete();
	
	var element = document.getElementById("participate_month");
	participate.year = document.getElementById("participate_year").options[document.getElementById("participate_year").options.selectedIndex].value;
	
	if(participate.year == null || participate.year == -1){
		return;
	}
	
	var tmp = null;
	
	if(introduction.year == participate.year){
		for(var i = 1; i <= introduction.month; i++){
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(i));
			tmp.innerHTML = format(i);
			element.appendChild(tmp);
		}
	}else{
		for(var i = 1; i <= 12; i++){
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(i));
			tmp.innerHTML = format(i);
			element.appendChild(tmp);
		}
	}
	
	document.getElementById("participate_year_value").value = document.getElementById("participate_year").options[document.getElementById("participate_year").options.selectedIndex].value;
}

// 参加日(日)
function participateDayLoad(){
	// 変更の為の削除
	participateDayDelete();
	
	var element = document.getElementById("participate_day");
	participate.month = document.getElementById("participate_month").options[document.getElementById("participate_month").options.selectedIndex].value;
	
	if(participate.month == null || participate.month == -1){
		return;
	}
	
	var tmp = null;
	
	if(introduction.year == participate.year && introduction.month == participate.month){
		for(var i = 1; i <= introduction.day; i++){
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(i));
			tmp.innerHTML = format(i);
			element.appendChild(tmp);
		}
	}else{
		var last = lastDay(participate.year, participate.month);
		for(var i = 1; i <= last; i++){
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(i));
			tmp.innerHTML = format(i);
			element.appendChild(tmp);
		}
	}
	
	document.getElementById("participate_month_value").value = document.getElementById("participate_month").options[document.getElementById("participate_month").options.selectedIndex].value;
}

// 合否連絡日(年)
function acceptYearLoad(){
	// 変更の為の削除
	acceptYearDelete();
	
	var element = document.getElementById("accept_year");
	participate.day = document.getElementById("participate_day").options[document.getElementById("participate_day").options.selectedIndex].value;
	
	if(participate.day == null || participate.day == -1){
		return;
	}
	
	var tmp = null;
	for(var i = 0; i < 2; i++){
		tmp = document.createElement("option");
		tmp.setAttribute("value", format(new Number(participate.year) + i));
		tmp.innerHTML = format(new Number(participate.year) + i);
		element.appendChild(tmp);
	}
	
	document.getElementById("participate_day_value").value = document.getElementById("participate_day").options[document.getElementById("participate_day").options.selectedIndex].value;
}

// 合否連絡日(月)
function acceptMonthLoad(){
	// 変更の為の削除
	acceptMonthDelete();
	
	var element = document.getElementById("accept_month");
	accept.year = document.getElementById("accept_year").options[document.getElementById("accept_year").options.selectedIndex].value;
	
	if(accept.year == null || accept.year == -1){
		return;
	}
	
	var tmp = null;
	
	if(accept.year == participate.year){
		for(var i = participate.month; i <= 12; i++){
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(i));
			tmp.innerHTML = format(i);
			element.appendChild(tmp);
		}
	}else{
		for(var i = 1; i <= 12; i++){
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(i));
			tmp.innerHTML = format(i);
			element.appendChild(tmp);
		}
	}
	
	document.getElementById("accept_year_value").value = document.getElementById("accept_year").options[document.getElementById("accept_year").options.selectedIndex].value;
}

// 合否連絡日(日)
function acceptDayLoad(){
	// 変更の為の削除
	acceptDayDelete();
	
	var element = document.getElementById("accept_day");
	accept.month = document.getElementById("accept_month").options[document.getElementById("accept_month").options.selectedIndex].value;
	
	if(accept.month == null || accept.month == -1){
		return;
	}
	
	var tmp = null;
	
	if(accept.year == participate.year && accept.month == participate.month){
		var last = lastDay(accept.year, accept.month);
		for(var i = participate.day; i <= last; i++){
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(i));
			tmp.innerHTML = format(i);
			element.appendChild(tmp);
		}
	}else{
		var last = lastDay(accept.year, accept.month);
		for(var i = 1; i <= last; i++){
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(i));
			tmp.innerHTML = format(i);
			element.appendChild(tmp);
		}
	}
	
	document.getElementById("accept_month_value").value = document.getElementById("accept_month").options[document.getElementById("accept_month").options.selectedIndex].value;
}

// 次回受験日(年)
function nexttimeYearLoad(){
	// 変更の為の削除
	nexttimeYearDelete();
	
	var element = document.getElementById("nexttime_year");
	
	if(document.getElementById("accept_year") == null){
		participate.day = document.getElementById("participate_day").options[document.getElementById("participate_day").options.selectedIndex].value;
		
		if(participate.day == null || participate.day == -1){
			return;
		}
	}else{
		accept.day = document.getElementById("accept_day").options[document.getElementById("accept_day").options.selectedIndex].value;
		
		if(accept.day == null || accept.day == -1){
			return;
		}
	}
	
	
	
	var tmp = null;
	
	if(document.getElementById("accept_year") == null){
		for(var i = 0; i < 2; i++){
			if(i == 0 && accept.month == 12 && accept.day == lastDay(accept.year, 12)){
				continue;
			}
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(new Number(participate.year) + i));
			tmp.innerHTML = format(new Number(participate.year) + i);
			element.appendChild(tmp);
		}
	}else{
		for(var i = 0; i < 2; i++){
			if(i == 0 && accept.month == 12 && accept.day == lastDay(accept.year, 12)){
				continue;
			}
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(new Number(accept.year) + i));
			tmp.innerHTML = format(new Number(accept.year) + i);
			element.appendChild(tmp);
		}
	}
	
	if(document.getElementById("accept_year") == null){
		document.getElementById("participate_day_value").value = document.getElementById("participate_day").options[document.getElementById("participate_day").options.selectedIndex].value;
	}else{
		document.getElementById("accept_day_value").value = document.getElementById("accept_day").options[document.getElementById("accept_day").options.selectedIndex].value;
	}
	
}

// 次回受験日(月)
function nexttimeMonthLoad(){
	// 変更の為の削除
	nexttimeMonthDelete();
	
	var element = document.getElementById("nexttime_month");
	nexttime.year = document.getElementById("nexttime_year").options[document.getElementById("nexttime_year").options.selectedIndex].value;
	
	if(nexttime.year == null || nexttime.year == -1){
		return;
	}
	
	var tmp = null;
	
	if(document.getElementById("accept_year") == null){
		if(nexttime.year == participate.year){
			if(participate.day == lastDay(participate.year, participate.month)){
				for(var i = new Number(participate.month) + 1; i <= 12; i++){
					tmp = document.createElement("option");
					tmp.setAttribute("value", format(i));
					tmp.innerHTML = format(i);
					element.appendChild(tmp);
				}
			}else{
				for(var i = participate.month; i <= 12; i++){
					tmp = document.createElement("option");
					tmp.setAttribute("value", format(i));
					tmp.innerHTML = format(i);
					element.appendChild(tmp);
				}
			}
		}else{
			for(var i = 1; i <= 12; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				element.appendChild(tmp);
			}
		}
	}else{
		if(nexttime.year == accept.year){
			if(accept.day == lastDay(accept.year, accept.month)){
				for(var i = new Number(accept.month) + 1; i <= 12; i++){
					tmp = document.createElement("option");
					tmp.setAttribute("value", format(i));
					tmp.innerHTML = format(i);
					element.appendChild(tmp);
				}
			}else{
				for(var i = accept.month; i <= 12; i++){
					tmp = document.createElement("option");
					tmp.setAttribute("value", format(i));
					tmp.innerHTML = format(i);
					element.appendChild(tmp);
				}
			}
		}else{
			for(var i = 1; i <= 12; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				element.appendChild(tmp);
			}
		}
	}
	
		document.getElementById("nexttime_year_value").value = document.getElementById("nexttime_year").options[document.getElementById("nexttime_year").options.selectedIndex].value;
}

// 次回受験日(日)
function nexttimeDayLoad(){
	// 変更の為の削除
	nexttimeDayDelete();
	
	var element = document.getElementById("nexttime_day");
	nexttime.month = document.getElementById("nexttime_month").options[document.getElementById("nexttime_month").options.selectedIndex].value;
	
	if(nexttime.month == null || nexttime.month == -1){
		return;
	}
	
	var tmp = null;

	if(document.getElementById("accept_year") == null){
		if(nexttime.year == participate.year && nexttime.month == participate.month){
			var last = lastDay(participate.year, participate.month);
			for(var i = new Number(participate.day) + 1; i <= last; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				element.appendChild(tmp);
			}
		}else{
			var last = lastDay(nexttime.year, nexttime.month);
			for(var i = 1; i <= last; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				element.appendChild(tmp);
			}
		}
	}else{
		if(nexttime.year == accept.year && nexttime.month == accept.month){
			var last = lastDay(accept.year, accept.month);
			for(var i = new Number(accept.day) + 1; i <= last; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				element.appendChild(tmp);
			}
		}else{
			var last = lastDay(nexttime.year, nexttime.month);
			for(var i = 1; i <= last; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				element.appendChild(tmp);
			}
		}
	}
	
		document.getElementById("nexttime_month_value").value = document.getElementById("nexttime_month").options[document.getElementById("nexttime_month").options.selectedIndex].value;
}

function nexttimeValue(){
		document.getElementById("nexttime_day_value").value = document.getElementById("nexttime_day").options[document.getElementById("nexttime_day").options.selectedIndex].value;
}

// 初期値を設定する
function reportDateInit(){
	participate.year = document.getElementById("participate_year_value").value;
	participate.month = document.getElementById("participate_month_value").value;
	participate.day = document.getElementById("participate_day_value").value;
	if(document.getElementById("accept_year_value") != null){
		accept.year = document.getElementById("accept_year_value").value;
		accept.month = document.getElementById("accept_month_value").value;
		accept.day = document.getElementById("accept_day_value").value;
	}
	nexttime.year = document.getElementById("nexttime_year_value").value;
	nexttime.month = document.getElementById("nexttime_month_value").value;
	nexttime.day = document.getElementById("nexttime_day_value").value;
	
	// 参加日(年)
	var element = document.getElementById("participate_year");
	var length = element.length;
	for(var i = 0; i < length - 1; i++){
		element.removeChild(element.lastChild);
	}
	var tmp = null;
	for(var i = 0; i < 2; i++){
		tmp = document.createElement("option");
		tmp.setAttribute("value", introduction.year - i);
		tmp.innerHTML = introduction.year - i;
		if(participate.year == introduction.year - i){
			tmp.setAttribute("selected", "selected");
		}
		element.appendChild(tmp);
	}
	
	// 参加日(月)
	element = document.getElementById("participate_month");
	if(introduction.year == participate.year){
		for(var i = 1; i <= introduction.month; i++){
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(i));
			tmp.innerHTML = format(i);
			if(participate.month == i){
				tmp.setAttribute("selected", "selected");
			}
			element.appendChild(tmp);
		}
	}else{
		for(var i = 1; i <= 12; i++){
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(i));
			tmp.innerHTML = format(i);
			if(participate.month == i){
				tmp.setAttribute("selected", "selected");
			}
			element.appendChild(tmp);
		}
	}
	
	// 参加日(日)
	element = document.getElementById("participate_day");
	if(introduction.year == participate.year && introduction.month == participate.month){
		for(var i = 1; i <= introduction.day; i++){
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(i));
			tmp.innerHTML = format(i);
			if(participate.day == i){
				tmp.setAttribute("selected", "selected");
			}
			element.appendChild(tmp);
		}
	}else{
		var last = lastDay(participate.year, participate.month);
		for(var i = 1; i <= last; i++){
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(i));
			tmp.innerHTML = format(i);
			if(participate.day == i){
				tmp.setAttribute("selected", "selected");
			}
			element.appendChild(tmp);
		}
	}
	
	// 合否連絡があるかどうか
	if(accept.year != 0){
		// 合否連絡(年)
		element = document.getElementById("accept_year");
		for(var i = 0; i < 2; i++){
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(new Number(participate.year) + i));
			tmp.innerHTML = format(new Number(participate.year) + i);
			if(accept.year == new Number(participate.year) + i){
				tmp.setAttribute("selected", "selected");
			}
			element.appendChild(tmp);
		}
		
		// 合否連絡(月)
		element = document.getElementById("accept_month");
		if(accept.year == participate.year){
			for(var i = participate.month; i <= 12; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				if(accept.month == i){
					tmp.setAttribute("selected", "selected");
				}
				element.appendChild(tmp);
			}
		}else{
			for(var i = 1; i <= 12; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				if(accept.month == i){
					tmp.setAttribute("selected", "selected");
				}
				element.appendChild(tmp);
			}
		}
		
		// 合否連絡(月)
		element = document.getElementById("accept_day");
		if(accept.year == participate.year && accept.month == participate.month){
			var last = lastDay(accept.year, accept.month);
			for(var i = participate.day; i <= last; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				if(accept.day == i){
					tmp.setAttribute("selected", "selected");
				}
				element.appendChild(tmp);
			}
		}else{
			var last = lastDay(accept.year, accept.month);
			for(var i = 1; i <= last; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				if(accept.day == i){
					tmp.setAttribute("selected", "selected");
				}
				element.appendChild(tmp);
			}
		}
	}
	
	// 次回受験(年)
	element = document.getElementById("nexttime_year");
	if(accept.year == 0){
		for(var i = 0; i < 2; i++){
			if(i == 0 && accept.month == 12 && accept.day == lastDay(accept.year, 12)){
				continue;
			}
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(new Number(participate.year) + i));
			tmp.innerHTML = format(new Number(participate.year) + i);
			if(nexttime.year == new Number(participate.year) + i){
				tmp.setAttribute("selected", "selected");
			}
			element.appendChild(tmp);
		}
	}else{
		for(var i = 0; i < 2; i++){
			if(i == 0 && accept.month == 12 && accept.day == lastDay(accept.year, 12)){
				continue;
			}
			tmp = document.createElement("option");
			tmp.setAttribute("value", format(new Number(accept.year) + i));
			tmp.innerHTML = format(new Number(accept.year) + i);
			if(nexttime.year == new Number(participate.year) + i){
				tmp.setAttribute("selected", "selected");
			}
			element.appendChild(tmp);
		}
	}
	
	// 次回受験(月)
	var element = document.getElementById("nexttime_month");
	if(accept.year == 0){
		if(nexttime.year == participate.year){
			if(participate.day == lastDay(participate.year, participate.month)){
				for(var i = new Number(participate.month) + 1; i <= 12; i++){
					tmp = document.createElement("option");
					tmp.setAttribute("value", format(i));
					tmp.innerHTML = format(i);
					if(nexttime.month == i){
						tmp.setAttribute("selected", "selected");
					}
					element.appendChild(tmp);
				}
			}else{
				for(var i = participate.month; i <= 12; i++){
					tmp = document.createElement("option");
					tmp.setAttribute("value", format(i));
					tmp.innerHTML = format(i);
					if(nexttime.month == i){
						tmp.setAttribute("selected", "selected");
					}
					element.appendChild(tmp);
				}
			}
		}else{
			for(var i = 1; i <= 12; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				if(nexttime.month == i){
					tmp.setAttribute("selected", "selected");
				}
				element.appendChild(tmp);
			}
		}
	}else{
		if(nexttime.year == accept.year){
			if(accept.day == lastDay(accept.year, accept.month)){
				for(var i = new Number(accept.month) + 1; i <= 12; i++){
					tmp = document.createElement("option");
					tmp.setAttribute("value", format(i));
					tmp.innerHTML = format(i);
					if(nexttime.month == i){
						tmp.setAttribute("selected", "selected");
					}
					element.appendChild(tmp);
				}
			}else{
				for(var i = accept.month; i <= 12; i++){
					tmp = document.createElement("option");
					tmp.setAttribute("value", format(i));
					tmp.innerHTML = format(i);
					if(nexttime.month == i){
						tmp.setAttribute("selected", "selected");
					}
					element.appendChild(tmp);
				}
			}
		}else{
			for(var i = 1; i <= 12; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				if(nexttime.month == i){
					tmp.setAttribute("selected", "selected");
				}
				element.appendChild(tmp);
			}
		}
	}
	
	// 次回受験(日)
	var element = document.getElementById("nexttime_day");
	if(accept.year == 0){
		if(nexttime.year == participate.year && nexttime.month == participate.month){
			var last = lastDay(participate.year, participate.month);
			for(var i = new Number(participate.day) + 1; i <= last; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				if(nexttime.day == i){
					tmp.setAttribute("selected", "selected");
				}
				element.appendChild(tmp);
			}
		}else{
			var last = lastDay(nexttime.year, nexttime.month);
			for(var i = 1; i <= last; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				if(nexttime.day == i){
					tmp.setAttribute("selected", "selected");
				}
				element.appendChild(tmp);
			}
		}
	}else{
		if(nexttime.year == accept.year && nexttime.month == accept.month){
			var last = lastDay(accept.year, accept.month);
			for(var i = new Number(accept.day) + 1; i <= last; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				if(nexttime.day == i){
					tmp.setAttribute("selected", "selected");
				}
				element.appendChild(tmp);
			}
		}else{
			var last = lastDay(nexttime.year, nexttime.month);
			for(var i = 1; i <= last; i++){
				tmp = document.createElement("option");
				tmp.setAttribute("value", format(i));
				tmp.innerHTML = format(i);
				if(nexttime.day == i){
					tmp.setAttribute("selected", "selected");
				}
				element.appendChild(tmp);
			}
		}
	}
}
