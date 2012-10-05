function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}


//--------------カレンダーの設定-----------------

function cal(year,month,day) {
 today=new Date();
 if (!year) var year=today.getFullYear();
 if (!month) var month=today.getMonth();
 else month--;
 if (!day) var day=today.getDate();
 var leap_year=false;
 
 if ((year%4 == 0 && year%100 != 0) || (year%400 == 0)) leap_year=true;
 
 lom=new Array(31,28+leap_year,31,30,31,30,31,31,30,31,30,31);
 
 dow=new Array("日","月","火","水","木","金","土");
 
 var days=0;
 
 for (var i=0; i < month; i++) days+=lom[i];
 
 var week=Math.floor((year*365.2425+days)%7);
 
 var j=0;
 
 var when=year+"年 "+(month+1)+"月";
 
 var calendar="<table summary=\""+when+"のカレンダー\">\n";
 calendar+="<caption>"+when+"<\/caption>\n<tr>";
 for (i=0; i < 7; i++) calendar+="<td>"+dow[i]+"<\/td>";
 calendar+="<\/tr>\n<tr>";
 for (i=0; i < week; i++,j++) calendar+="<td><\/td>";
 for (i=1; i <= lom[month]; i++) {
  calendar+="<td";
  if (day == i) calendar+=" class=\"today\"";
  calendar+=">"+i+"<\/td>";
  j++;
  if (j > 6) {
   calendar+="<\/tr>\n<tr>";
   j=0;
  }
 }
 for (i=j; i > 6; i++) calendar+="<td><\/td>";
 calendar+="<\/tr>\n<\/table>\n";
 return calendar;
 }
 
 //--------------日付ボックスの設定-----------------
 
 /**
 * 存在しない日（2月30日など）の選択肢を非表示にする
 *
 * @param yearId 「年」のselect要素のID
 * @param monthId 「月」のselect要素のID
 * @param dayId 「日」のselect要素のID
 */
/**
 * 存在しない日（2月30日など）の選択肢を非表示にする
 *
 * @param yearId 「年」のselect要素のID
 * @param monthId 「月」のselect要素のID
 * @param dayId 「日」のselect要素のID
 */
function nonExistDayIsNonDisplayed(yearId, monthId, dayId) {
  var monthSelect = document.getElementById(monthId);
  var daySelect = document.getElementById(dayId);
  var selectedMonth = parseInt(monthSelect.getElementsByTagName("option")[monthSelect.selectedIndex].value, 10);
  var dayOptions = daySelect.getElementsByTagName("option");

  if (selectedMonth === 2) {
    // 2月の場合
    var yearSelect = document.getElementById(yearId);
    var selectedYear = parseInt(yearSelect.getElementsByTagName("option")[yearSelect.selectedIndex].value, 10);

    for (var i = dayOptions.length - 1; i >= 0; i--) {
      var dayOption = dayOptions[i];
      var dayValue = parseInt(dayOption.value, 10);
      var leapYear = isLeapYear(selectedYear); // 閏年か
      if (dayValue >= 30 || (dayValue === 29 && !leapYear)) {
        dayOption.setAttribute("disabled", "disabled"); // 選択不能指定
        if (dayOption.selected) {
          // 29日(閏年でない場合のみ)、30日、31日のいずれかが選択されていた場合は、2月の最終日に変更
          if (leapYear) {
            daySelect.value = "29";
          } else {
            daySelect.value = "28";
          }
        }
      } else if ("disabled" === dayOption.getAttribute("disabled")) {
        // 選択不能指定が成されていたら解除
        dayOption.removeAttribute("disabled");
      } else {
        break;
      }
    }
  } else if (selectedMonth === 4 || selectedMonth === 6 || selectedMonth === 9 || selectedMonth === 11) {
    // 月の日数が30日の場合
    for (var i = dayOptions.length - 1; i >= 0; i--) {
      var dayOption = dayOptions[i];
      var dayValue = parseInt(dayOption.value, 10);
      if (dayValue >= 31) {
        dayOption.setAttribute("disabled", "disabled"); // 選択不能指定
        if (dayOption.selected) {
          // 31日が選択されていた場合は、各月の最終日に変更
          daySelect.value = "30";
        }
      } else if ("disabled" === dayOption.getAttribute("disabled")) {
        // 選択不能指定が成されていたら解除
        dayOption.removeAttribute("disabled");
      } else {
        break;
      }
    }
  } else {
    // 月の日数が31日の場合
    for (var i = dayOptions.length - 1; i >= 0; i--) {
      var dayOption = dayOptions[i];
      if ("disabled" === dayOption.getAttribute("disabled")) {
        // 選択不能指定が成されていたら解除
        dayOption.removeAttribute("disabled");
      } else {
        break;
      }
    }
  }
}

/**
 * 閏年か
 *
 * @param year 年
 *
 * @return 閏年ならtrue、それ以外の場合はfalse
 */
function isLeapYear(year) {
  var date = new Date(year, 1, 29);
  return date.getMonth() === 1;
}




function btnConform(){
	var inputs = document.getElementsByTagName("input");
	for( var i=0,j=inputs.length; i<j; i++){
		var btn = inputs[i]; 
		if(btn.getAttribute("class") ){
			if(btn.getAttribute("class") == "input_btn"){
				btn.onmouseover = function(){
					btn.style.backgroundPosition = 'left -30px';
					return false;
				}
				btn.onmouseout = function(){
					btn.style.backgroundPosition = 'left top';
					return false;
				}
			}
		}
		else if(btn.getAttribute("className")){
			if(btn.getAttribute("className") == "input_btn"){
				btn.onmouseover = function(){
					btn.style.backgroundPosition = 'left -30px';
					return false;
				}
				btn.onmouseout = function(){
					btn.style.backgroundPosition = 'left top';
					return false;
				}
			}
		}
	}
}