<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%--　セッションの取得  --%>
<% String u_name = (String)session.getAttribute("u_name");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<%-- ファビコン --%>
<link rel="shortcut icon" type="image/x-icon" href="images/icon.ico" />

<%--企業説明会報告書 --%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta name="copyright" content="Nikukyu-Punch" />

<title>Be-Max情報管理</title>
<link href="style2.css" rel="stylesheet" type="text/css" />

<!--別の URL に有る外部リソース（資源）を参照-->


<link type="text/css" rel="stylesheet" href="css/reportStyle.css">

<!--データベースから値をとってくる-->
<script type="text/javascript" src="js/CreateCompany.js"></script>




<script type="text/javascript" src="js/jquery1-6-1.js"></script>
<script type="text/javascript" src="js/jquery.easing.js"></script>
<script type="text/javascript" src="js/jQselectable.js"></script>
<script type="text/javascript" src="js/exvalidation2.js"></script>
<script type="text/javascript" src="js/exchecker-ja2.js"></script>

<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="js/formToWizard.js"></script>
<script type="text/javascript" src="js/tablePlus.js"></script>
<script type="text/javascript" src="js/tablePlus2.js"></script>



<script type="text/javascript" SRC="js/reportDate.js"></script>
<script type="text/javascript" SRC="js/ajaxzip3.js"></script>
<script type="text/javascript" SRC="js/enter.js"></script>
<script type="text/javascript" SRC="js/Company.js"></script>
<script type="text/javascript" SRC="js/CrossSite.js"></script>



<script type="text/javascript">
<!--
$(document).ready(function(){
    $("#SignupForm").formToWizard({ submitButton: 'SaveAccount' });
});


	//ラジオボックスが有るならば表を表示させる	
	function open_close_row(elm1,elm2,elm3,elm_number)
	{
		var row1 = document.formMain[elm1];
		var row2 = document.formMain[elm2];
		var row3 = document.formMain[elm3];
		if (elm_number==1) {
			row1.disabled = false;
			row2.disabled = false;
			row3.disabled = false;
		} else if(elm_number==2){
			row1.disabled = true;
			row2.disabled = true;
			row3.disabled = true;
		}
	}
	
	//テキストエリアに対する文字数制限表示
	function test(num,areaID){
		
		o=document.getElementById('slen'+ num);
		n=(areaID.value).length;
		o.value=n;
		o.innerHTML=n;
		o.style.color=(n>200)?'red':'tan';
		document.getElementById('mes' + num + '-1').innerHTML=(n>200)?'文字　文字制限を越えました':'文字';
		document.getElementById('mes' + num + '-2').innerHTML=(n>200)?'':'　あと'+(200-n)+'文字です。';
		}
-->
</script>
<script type="text/javascript">
<!--
function mlength(){
	
	var elements=document.getElementsByTagName("textarea");
	var l = elements.length;
	for(var i = 1; i<l-2; i++){
		var elm =elements[i].value.length;
		if(elm > 200){
			alert("質問項目の文字数制限を超えました");
			return false;
		}
		return true;
	}

}
-->
</script>






</head>

<body
	onload="introductionLoad();MM_preloadImages('images/menu_over_01.gif','images/menu_over_02.gif','images/menu_over_03.gif','images/menu_over_04.gif','images/menu_over_05.gif','images/menu_over_06.gif');">

	<img class="bg" src="images/bg-l.gif" alt=""/>
	<div id="container">


		<div id="header">
			<h1>
				<a href="ChangePassword">パスワード変更</a> <a
					onclick="return confirm('ログアウトしますか？')" href="Logout">ログアウト</a>
			</h1>

			<a href="ReportNewList"><img src="images/logo.png" name="logo"
				id="logo" /></a>

			<ul id="menu">
				<li><a href="ReportNewList"><img src="images/menu_01.gif"
						alt="トップページへ" width="120" height="50" id="Image1"
						onmouseover="MM_swapImage('Image1','','images/menu_over_01.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="ReportMyList"><img src="images/menu_02.gif"
						alt="My報告書一覧へ" width="120" height="50" id="Image2"
						onmouseover="MM_swapImage('Image2','','images/menu_over_02.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="JoinMeetingList"><img
						src="images/menu_over_03.gif" alt="企業説明会報告書一覧へ" width="120"
						height="50" id="Image3"
						onmouseover="MM_swapImage('Image3','','images/menu_over_03.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="WrittenExaminationList"><img
						src="images/menu_04.gif" alt="筆記試験報告書一覧へ" width="120" height="50"
						id="Image4"
						onmouseover="MM_swapImage('Image4','','images/menu_over_04.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="OralExaminationList"><img
						src="images/menu_05.gif" alt="面接試験報告書一覧へ" width="120" height="50"
						id="Image5"
						onmouseover="MM_swapImage('Image5','','images/menu_over_05.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="SearchByCompany"><img src="images/menu_06.gif"
						alt="検索ページへ" width="120" height="50" id="Image6"
						onmouseover="MM_swapImage('Image6','','images/menu_over_06.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
			</ul>
		</div>
		<!--/header-->
		<!-- 企業説明会 -->

		<div id="contents">


			<div id="wrap">


				<div id="main">

					<form name=formMain id=formMain method="POST"
						action="./student/o_report_conf.jsp">

						<!-- ここから分割されて -->
						<span id="SignupForm">
							<fieldset>
								<h2>
									<legend>企業説明会について</legend>
								</h2>

								<table id="table-02">
									<tbody>
										<tr>
											<th>提出日</th>
											<td>
												<div id="introduction_year_value">--</div> <label>年</label>
												<div id="introduction_month_value">--</div> <label>月</label>
												<div id="introduction_day_value">--</div> <label>日</label> <input
												type="hidden" name="rjm_introduction_year"
												id="introduction_year" value=""> <input
												type="hidden" name="rjm_introduction_month"
												id="introduction_month" value=""> <input
												type="hidden" name="rjm_introduction_day"
												id="introduction_day" value="">
											</td>
										</tr>

										<tr>
											<th>参加日<span class="fontc-2">*</span></th>
											<td>
												<!-- rj_participate--> <select ID="participate_year"
												name="rjm_participate_year"
												onchange="participateMonthLoad();">
													<option value="">--</option>
											</select> <label>年</label> <select ID="participate_month"
												name="rjm_participate_month"
												onchange="participateDayLoad();">
													<option value="">--</option>
											</select> <label>月</label> <select ID="participate_day"
												name="rjm_participate_day" onchange="nexttimeYearLoad();">
													<option value="">--</option>
											</select> <label>日</label> <input type="hidden"
												name="participate_year_value" id="participate_year_value"
												value=""> <input type="hidden"
												name="participate_month_value" id="participate_month_value"
												value=""> <input type="hidden"
												name="participate_day_value" id="participate_day_value"
												value="">

											</td>
										</tr>

										<tr>
											<th>企業説明会名<span class="fontc-2">*</span></th>
											<td><INPUT type="text" id="rjm_name" name="rjm_name"
												size="30" maxlength="100"
												onkeypress="return handleEnter(this, event)"></td>
										</tr>

										<tr>
											<th>企業名<span class="fontc-2">*</span></th>
											<td>
												<DIV ID="CompanyName" style="display: inline"></DIV> <INPUT
												ID="Company" name="comp_name" TYPE="hidden" VALUE="">
												<INPUT ID="CompanyValue" TYPE="hidden" NAME="comp_id"
												VALUE="">
												<BUTTON TYPE="button" onclick="createWindow();"
													onkeypress="return handleEnter(this, event)">企業選択</BUTTON>
											</td>
										</tr>

										<tr>
											<th>担当者名</th>
											<td><INPUT type="text" name="rjm_owner" id="rjm_owner"
												size="30" maxlength="100"
												onkeypress="return handleEnter(this, event)"></td>
										</tr>

										<tr>
											<th>説明内容<span class="fontc-2">*</span></th>
											<td><textarea id="rjm_content" name="rjm_content"
													rows="5" cols="50"></textarea></td>
										</tr>
								</table>
							</fieldset> <!-- ここから分割されて -->

							<fieldset>
								<h2>
									<legend>企業から質問された内容について</legend>
								</h2>

								<table id="table-03">


									<tr>
										<th>質問</th>
										<td><textarea id="rjmr_rquestion0" name="rjmr_rquestion0"
												maxlength="200" class="limited" rows="5" cols="50"></textarea>
										</td>
									</tr>

									<tr>
										<th>回答</th>
										<td><textarea id="rjmr_answer0" name="rjmr_answer0"
												maxlength="200" class="limited" rows="5" cols="50"></textarea>
										</td>
									</tr>

									<tr>
										<th>
											<!-- 質問追加ボタン -->
										</th>
										<td>
											<button type="button" id=addrow_r
												onClick="AddTableRows('rjmr_rquestion','rjmr_answer','table-03','delrow_r' );"
												onkeypress="return handleEnter(this, event)">
												<span class="fontc-1">＋</span>質問を増やす
											</button>
											<button type="button" id=delrow_r
												onClick="deleteTable('rjmr_rquestion','rjmr_answer','table-03','delrow_r');"
												onkeypress="return handleEnter(this, event)" disabled>
												<span class="fontc-2">－</span>質問を減らす
											</button>
										</td>
									</tr>
								</table>
							</fieldset> <!-- ここから分割されて -->

							<fieldset>
								<h2>
									<legend>自分が質問した内容について</legend>
								</h2>

								<table id="table-04">
									<tr>
										<th>質問</th>
										<td><textarea id="rjmq_question0" name="rjmq_question0"
												maxlength="200" class="limited" rows="5" cols="50"></textarea>
										</td>
									</tr>

									<tr>
										<th>回答</th>
										<td><textarea id="rjmq_answer0" name="rjmq_answer0"
												maxlength="200" class="limited" rows="5" cols="50"></textarea>
										</td>
									</tr>

									<tr>
										<th></th>
										<td>
											<button type="button" id=addrow
												onClick="AddTableRows1('rjmq_question','rjmq_answer','table-04','delrow');"
												onkeypress="return handleEnter(this, event)">
												<span class="fontc-1">＋</span>質問を増やす
											</button>
											<button type="button" id=delrow
												onClick="deleteTable1('rjmq_question','rjmq_answer','table-04','delrow');"
												onkeypress="return handleEnter(this, event)" disabled>
												<span class="fontc-2">－</span>質問を減らす
											</button>
										</td>
									</tr>
								</table>
							</fieldset> <!-- ここから分割されて -->
							<fieldset>

								<h2>
									<legend>次回の情報について</legend>
								</h2>

								<table id="table-02">
									<tr>
									<tr>
										<th>次回<span class="fontc-2">*</span></th>
										<td><input type="radio" value="会社別訪問（個別セミナー）予定"
											name="rjm_nexttime"
											onclick="open_close_row('nexttime_year', 'nexttime_month', 'nexttime_day',1);"
											onkeypress="return handleEnter(this, event)" /> <label
											3for="rb1">会社別訪問（個別セミナー）予定</label> <input type="radio"
											value="受験予定" name="rjm_nexttime"
											onclick="open_close_row('nexttime_year', 'nexttime_month', 'nexttime_day',1);"
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb1">受験予定</label> <input type="radio" id="noneNext"
											value="受験日未定" name="rjm_nexttime" checked
											onclick="open_close_row('nexttime_year', 'nexttime_month', 'nexttime_day',2);"
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb1">受験日未定</label> <input type="radio" id="noneNext"
											value="受験予定なし" name="rjm_nexttime" checked
											onclick="open_close_row('nexttime_year', 'nexttime_month', 'nexttime_day',2);"
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb1">受験予定なし</label></td>
									</tr>

									<tr id="oapoint01">
										<th>受験予定日</th>
										<td><select ID="nexttime_year" name="rjm_nexttime_year"
											onchange="nexttimeMonthLoad();" disabled="true">
												<option value="">--</option>
										</select><label>年</label> <select ID="nexttime_month"
											name="rjm_nexttime_month" onchange="nexttimeDayLoad();"
											disabled="true">
												<option value="">--</option>
										</select><label>月</label> <select ID="nexttime_day"
											name="rjm_nexttime_day" onchange="nexttimeValue();"
											disabled="true">
												<option value="">--</option>
										</select><label>日</label> <input type="hidden"
											name="nexttime_year_value" id="nexttime_year_value" value="">
											<input type="hidden" name="nexttime_month_value"
											id="nexttime_month_value" value=""> <input
											type="hidden" name="nexttime_day_value"
											id="nexttime_day_value" value=""></td>
									</tr>

									<tr>
										<th>その他<BR>特記事項
										</th>
										<td><span id="slen1"></span> <span id="mes1-1"
												style="font-size: 12px"></span> <span id="mes1-2"
												style="font-size: 12px"></span> <br /><textarea id="rjm_particular" name="rjm_particular"
												rows="5" cols="50" onfocus="test(1,rjm_particular)"
												onKeyup="test(1,rjm_particular)"></textarea></td>
									</tr>

									<tr>
										<th>所感<span class="fontc-2">*</span></th>
										<td><textarea
												name="rjm_impression" id="rjm_impression" rows="5" cols="50"
												></textarea></td>
									</tr>
								</table>
							</fieldset>

							<div id="button1">
								<input id="SaveAccount" type="submit" value="" class="button"
									 />
							</div>
						</span>
					</FORM>
				</div>

				<!--/main-->

				<div id="sub">
					<div id="sub-wel">
						<p>
							ようこそ
							<%=u_name%>
							さん
						</p>
					</div>
					<!--　各種機能のイメージ  -->
					<ul id="bana-sub">
						<li><a href="JoinMeetingInsert" class="o_report"></a></li>
						<li><a href="WrittenExaminationInsert" class="written_e"></a></li>
						<li><a href="OralExaminationInsert" class="o_oral_e"></a></li>
						<li><a href="SearchByCompany" class="o_jub_hunnting_search"></a></li>
					</ul>
					<div id="cal">
<script type="text/javascript">
document.write(cal());
</script>
					</div>
					<!--ついったー-->
					<script src="http://widgets.twimg.com/j/2/widget.js"></script>

<script>
<!--
new TWTR.Widget({
		
	version: 2,
	type: 'profile',
	rpp: 4,
	interval: 6000,
	width: 'auto',
	height: 300,
	theme: {
		
		shell: {	
			background: '#26B6DD',
			color: '#fffcff'
		},
		
		tweets: {
			background: '#ffffff',
			color: '#000000',
			links: '#5bc8e3'
			}
		},
		
		features: {
			scrollbar: false,
			loop: false,
			live: false,
			hashtags: true,
			timestamp: true,
			avatars: false,
			behavior: 'all'
		}
		
		}).render().setUser('joho_bemax').start();
-->
</script>
				</div>
				<!--/sub-->
			</div>
			<!--/wrap-->

			<div id="footer">
				Copyright(C) 2011 Be-Max WEB SITE All Rights Reserved.<br /> <a
					href="http://nikukyu-punch.com/" target="_blank">Template
					design by Nikukyu-Punch</a>
			</div>
			<!--/footer-->

		</div>
		<!--/contents-->


	</div>
	<!--/container-->

	<script type="text/javascript">
	<!--
	var validation,
		nexttime_year = $("#nexttime_year"),
		nexttime_month = $("#nexttime_month"),
		nexttime_day = $("#nexttime_day");
	validation = $("#formMain")
		.exValidation({
			rules: {
				
				participate_year:"chkselect",
				participate_month:"chkselect",
				participate_day:"chkselect",
				rjm_name:"chkrequired",
				rjm_content:"chkrequired",
				nexttime_year:"chkselect",
				nexttime_month:"chkselect",
				nexttime_day:"chkselect",
				rjm_particular:"chkmax200",
				rjm_impression:"chkrequired"
					
			},
			
			firstValidate: true,
			errFocus:true,
			errHoverHide:true,
			//エラーがあった場合に呼び出す関数
			customAddError: function() {
				if ( $("#alert").length<1 ) {
					alert("入力値に誤りがあります。");
					return false;
					}
			},
			
			customClearError:function(){
				
			
				if(!mlength()){
					return false;
				}
				
				var tmp = document.getElementById("CompanyValue").value;
				
				if(tmp > 0){
					return submit();
				}else{
					alert("企業を選択してください");
					return false;
					
				}
			}
			
	});

	if($('input[name=rjm_nexttime][value=受験予定なし]').attr('checked') || $('input[name=rjm_nexttime][value=受験日未定]').attr('checked')){
		nexttime_year.removeClass("chkselect");
	}
	if($('input[name=rjm_nexttime][value=会社別訪問（個別セミナー）予定]').attr('checked') || $('input[name=rjm_nexttime][value=受験予定]').attr('checked')){
		nexttime_year.removeAttr("disabled");
	}
	$("input[name=rjm_nexttime]").click(function(){
		if(this.id === "noneNext"){
			nexttime_year.removeClass("chkselect");
			validation.laterCall("#nexttime_year");
		}else{
			nexttime_year.addClass("chkselect");
			validation.laterCall("#nexttime_year");
		}
	});
	if($('input[name=rjm_nexttime][value=受験予定なし]').attr('checked') || $('input[name=rjm_nexttime][value=受験日未定]').attr('checked')){
		nexttime_month.removeClass("chkselect");
	}
	if($('input[name=rjm_nexttime][value=会社別訪問（個別セミナー）予定]').attr('checked') || $('input[name=rjm_nexttime][value=受験予定]').attr('checked')){
		nexttime_month.removeAttr("disabled");
	}
	$("input[name=rjm_nexttime]").click(function(){
		if(this.id === "noneNext"){
			nexttime_month.removeClass("chkselect");
			validation.laterCall("#nexttime_month");
		}else{
			nexttime_month.addClass("chkselect");
			validation.laterCall("#nexttime_month");
		}
	});
	if($('input[name=rjm_nexttime][value=受験予定なし]').attr('checked') || $('input[name=rjm_nexttime][value=受験日未定]').attr('checked')){
		nexttime_day.removeClass("chkselect");
	}
	if($('input[name=rjm_nexttime][value=会社別訪問（個別セミナー）予定]').attr('checked') || $('input[name=rjm_nexttime][value=受験予定]').attr('checked')){
		nexttime_day.removeAttr("disabled");
	}
	$("input[name=rjm_nexttime]").click(function(){
		if(this.id === "noneNext"){
			nexttime_day.removeClass("chkselect");
			validation.laterCall("#nexttime_day");
		}else{
			nexttime_day.addClass("chkselect");
			validation.laterCall("#nexttime_day");
		}
	});
	var selectable = $('#pref').selectable({
		callback: function() {
			validation.laterCall('#pref');
		}
	});
	-->
</script>


</body>
</html>
