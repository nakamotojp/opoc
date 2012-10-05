function deleteCourse(){
	// Subject Name
	document.getElementById("SubjectName").removeAttribute("VALUE");
	var s_name = document.getElementById("Subject").options[document.getElementById("Subject").options.selectedIndex].innerHTML;
	document.getElementById("SubjectName").setAttribute("VALUE", s_name);
	document.getElementById("CourseName").removeAttribute("VALUE");
	document.getElementById("CourseName").setAttribute("VALUE", "");
	
	// Course ID 取得
	var ele = document.getElementById("Course");
	// option要素の長さを取得
	var opt = ele.options.length;
	
	// 以前のcourseを削除
	for(var i = 0; i < opt-1; i++){
		// 最後の要素を削除
		ele.removeChild(ele.lastChild);
	}
	
	// year ID 取得
	ele = document.getElementById("CourseYear");
	// option要素の長さを取得
	opt = ele.options.length;
	
	// 以前のyearを削除
	for(var i = 0; i < opt-1; i++){
		// 最後の要素を削除
		ele.removeChild(ele.lastChild);
	}
	
	// s_idの取得
	var subject = document.getElementById("Subject");
	var s_id = subject.options[subject.options.selectedIndex].value;
	
	requestCourse(s_id);
}

/**
 * @param s_id
 */
function requestCourse(s_id){
	// XMLHttpRequest生成
	// IE7以上の一般的なブラウザでは使用可能
	var xhr = new XMLHttpRequest();
	
	// XMLHttpRequestのチェック
	if(xhr == null){
		// IE7以前又はXMLHttpRequestに非対応のブラウザ
		return;
	}
	
	// 通信
	xhr.open("GET", "api/CourseAjax?s_id=" + s_id);
	xhr.onreadystatechange =
		function(){
			// 通信チェック
			if(xhr.readyState == 4 && xhr.status == 200){
				loadCourse(this.responseText);
			}
		};
	xhr.send();
}

function loadCourse(req){
	// Jsonへ変換
	data = eval('(' + req + ')');
	
	// Course ID 取得
	var ele = document.getElementById("Course");
	
	// 出力
	for(var i = 0; i < data.course.length; i++){
		var opt = document.createElement("OPTION");
		opt.setAttribute("VALUE", data.course[i].c_id);
		opt.innerHTML = data.course[i].c_name;
		ele.appendChild(opt);
	}
	
	return;
}

function deleteCourseYear(){
	// Course Name
	document.getElementById("CourseName").removeAttribute("VALUE");
	var c_name = document.getElementById("Course").options[document.getElementById("Course").options.selectedIndex].innerHTML;
	document.getElementById("CourseName").setAttribute("VALUE", c_name);
	
	// Subject ID 取得
	var ele = document.getElementById("CourseYear");
	// option要素の長さを取得
	var opt = ele.options.length;
	
	// 以前のcourseを削除
	for(var i = 0; i < opt-1; i++){
		// 最後の要素を削除
		ele.removeChild(ele.lastChild);
	}
	
	// s_idの取得
	var course = document.getElementById("Course");
	var c_id = course.options[course.options.selectedIndex].value;
	
	loadCourseYear(c_id);
}

function loadCourseYear(c_id){
	// Course ID 取得
	var ele = document.getElementById("CourseYear");
	
	// 出力
	for(var i = 0; i < data.course.length; i++){
		if(data.course[i].c_id == c_id){
			for(var j = 1; j <= data.course[i].c_year; j++){
				var opt = document.createElement("OPTION");
				opt.setAttribute("VALUE", j);
				opt.innerHTML = j + "年生";
				ele.appendChild(opt);
			}
		}
	}
	
	return;
}