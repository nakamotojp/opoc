<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.bemax.se.graduation2011.auth.Xss"%>
<%!
	private String transferCRLT(String value){
		String result = value.replace("【br】","\n");
		return result;
	}
%>
<%@page
	import="jp.bemax.se.graduation2011.model.BeansReportOralExaminationQuestion"%>
<jsp:useBean id="list" class="java.util.ArrayList" scope="request"></jsp:useBean>
<%-- セッションの取得  --%>
<%
	String u_name = (String)session.getAttribute("u_name");
Xss xss = new Xss();
%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<!-- 面接試験変更ページ -->
<head>
<!-- ファビコン -->
<link rel="shortcut icon" type="image/x-icon" href="images/icon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta name="description" content="専門学校ビーマックス就職活動支援ページ" />
<meta name="keywords" content="Opoc,ビーマックス,報告書提出,就職活動,報告書検索" />
<title>Opoc</title>

<link href="style2.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/reportStyle.css">
<script type="text/javascript" src="js/jquery1-6-1.js"></script>
<script type="text/javascript" src="js/jquery.easing.js"></script>
<script type="text/javascript" src="js/jQselectable.js"></script>
<script type="text/javascript" src="js/exvalidation2.js"></script>
<script type="text/javascript" src="js/exchecker-ja2.js"></script>
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="js/formToWizard.js"></script>
<script type="text/javascript" src="js/tablePlus.js"></script>
<script type="text/javascript" src="js/jquery1-6-1.js"></script>
<script type="text/javascript" src="js/jquery.maskedinput-1.3.min.js"></script>
<script type="text/javascript" src="js/jquery-bangou.js"></script>
<!--データベースから値をとってくる-->
<script type="text/javascript" SRC="js/CreateCompany.js"></script>
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
			//ENTERを抑止する
			function handleEnter (field, event) {
				var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
				if (keyCode == 13) {
					var i;
					for (i = 0; i < field.form.elements.length; i++){
						if (field == field.form.elements[i]){
							break;
						}
					}
					i = (i + 1) % field.form.elements.length;
					field.form.elements[i].focus();
					return false;
				} else {
					return true;
				}
			} 

			//ラジオボックスが有るならば入力ができるように	
			function open_close_row(elm,elm_number)
			{
				var row = document.formMain[elm];
				//rowのdisabledがtrueかつ、ラジオボタンの「有る」だった場合
				if (row.disabled == true && elm_number==1) {
					//機能させるために、disabledをfalseに
					row.disabled = false;
					//「無し」ならば機能をなくす
				} else if(elm_number==2){
					//機能をなくすために、disabledをtrueに
					row.disabled = true;
				}
			}

			//ラジオボックスが有るならば表を表示させる	
			function open_close_row_button(yaer,month,day,nextexam,preparation,elm_number)
			{
				var yaer_box = document.formMain[yaer];
				var month_box = document.formMain[month];
				var day_box = document.formMain[day];
				var nextexam_box = document.formMain[nextexam];
				var preparation_box = document.formMain[preparation];
				if(yaer_box.disabled == true && elm_number == 1){
					yaer_box.disabled = false;
					month_box.disabled = false;
					day_box.disabled = false;
					nextexam_box.disabled = false;
					preparation_box.disabled = false;
				}else if(elm_number == 2){
					yaer_box.disabled = true;
					month_box.disabled = true;
					day_box.disabled = true;
					nextexam_box.disabled = false;
					preparation_box.disabled = false;
				}else if(elm_number == 3){
					yaer_box.disabled = true;
					month_box.disabled = true;
					day_box.disabled = true;
					nextexam_box.disabled = true;
					preparation_box.disabled = true;
				}
			}

			//ラジオボックスが有るならば入力ができるように	
			function open_close_row(elm,elm_number)
			{
				var row = document.formMain[elm];
				//rowのdisabledがtrueかつ、ラジオボタンの「有る」だった場合
				if (row.disabled == true && elm_number==1) {
					//機能させるために、disabledをfalseに
					row.disabled = false;
				//「無し」ならば機能をなくす
				} else if(elm_number==2){
					//機能をなくすために、disabledをtrueに
					row.disabled = true;
				}
			}

			//ラジオボックスが有るならば表を表示させる	
			function open_close_row(discussionnum,discussiontime,discussiontheme,elm_number2)
			{
				var discussionnum_box = document.formMain[discussionnum];
				var discussiontime_box = document.formMain[discussiontime];
				var discussiontheme_box = document.formMain[discussiontheme];
				if(discussionnum_box.disabled == true && elm_number2 == 1){
					discussionnum_box.disabled = false;
					discussiontime_box.disabled = false;
					discussiontheme_box.disabled = false;
				}else if(elm_number2 == 2){
					discussionnum_box.disabled = true;
					discussiontime_box.disabled = true;
					discussiontheme_box.disabled = true;
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
	for(var i = 2; i<l-2; i++){
		var elm =elements[i].value.length;
		if(elm > 200){
			alert("文字数制限を超えました");
			return false;
		}
	}
	return submit();
}
-->
</script>
</head>
<body
	onload="counterPlus('<%= list.size()%>');introductionLoad();reportDateInit();MM_preloadImages('images/menu_over_01.gif','images/menu_over_02.gif','images/menu_over_03.gif','images/menu_over_04.gif','images/menu_over_05.gif');counterPlus('<%=list.size()%>');"
	onunload="">
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
				<li><a href="JoinMeetingList"><img src="images/menu_03.gif"
						alt="企業説明会報告書一覧へ" width="120" height="50" id="Image3"
						onmouseover="MM_swapImage('Image3','','images/menu_over_03.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="WrittenExaminationList"><img
						src="images/menu_04.gif" alt="筆記試験報告書一覧へ" width="120" height="50"
						id="Image4"
						onmouseover="MM_swapImage('Image4','','images/menu_over_04.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="OralExaminationList"><img
						src="images/menu_over_05.gif" alt="面接試験報告書一覧へ" width="120"
						height="50" id="Image5"
						onmouseover="MM_swapImage('Image5','','images/menu_over_05.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="SearchByCompany"><img src="images/menu_06.gif"
						alt="検索ページへ" width="120" height="50" id="Image6"
						onmouseover="MM_swapImage('Image6','','images/menu_over_06.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
			</ul>
		</div>
		<!--/header-->
		<div id="contents">
			<div id="wrap">
				<div id="main">
					<!--この行で見出し表示 -->
					<FORM name="formMain" method="POST"
						action="./student/o_oral_e_conf.jsp">
						<% request.setCharacterEncoding("UTF-8"); %>
						<!-- ここから分割されて -->
						<span id="SignupForm">
							<fieldset>
								<h2>
									<legend>面接試験について</legend>
								</h2>
								<table id="table-02">
									<tbody>
										<tr>
											<th>提出日<span class="fontc-2">*</span></th>
											<td>
												<div id="introduction_year_value">--</div>
												<label>年</label>
												<div id="introduction_month_value">--</div>
												<label>月</label>
												<div id="introduction_day_value">--</div>
												<label>日</label> <input type="hidden"
												name="roe_introduction_year" id="introduction_year" value="">
												<input type="hidden" name="roe_introduction_month"
												id="introduction_month" value=""> <input
												type="hidden" name="roe_introduction_day"
												id="introduction_day" value="">
											</td>
										</tr>
										<tr>
											<th>参加日<span class="fontc-2">*</span></th>
											<td>
												<!-- rj_participate--> <select ID="participate_year"
												name="roe_participate_year"
												onchange="participateMonthLoad();">
													<option value="">--</option>
											</select><label>年</label> <select ID="participate_month"
												name="roe_participate_month"
												onchange="participateDayLoad();">
													<option value="">--</option>
											</select><label>月</label> <select ID="participate_day"
												name="roe_participate_day" onchange="acceptYearLoad();">
													<option value="">--</option>
											</select><label>日</label> <%
	String pal = (String)request.getAttribute("roe_participate");
	String val1[] = pal.split("-");
%> <input type="hidden" name="participate_year_value"
												id="participate_year_value" value="<%= val1[0] %>">
												<input type="hidden" name="participate_month_value"
												id="participate_month_value" value="<%= val1[1] %>">
												<input type="hidden" name="participate_day_value"
												id="participate_day_value" value="<%= val1[2] %>">
											</td>
										</tr>
										<%
	String com = (String)request.getAttribute("compt_position");
%>
										<tr>
											<th>受験した企業<span class="fontc-2">*</span></th>
											<td>
												<DIV ID="CompanyName" style="display: inline">
													<% if(request.getAttribute("compt_position").equals("first")){out.print(request.getAttribute("compt_name")); out.print(xss.escape((String)(request.getAttribute("comp_name"))));}else{out.print(xss.escape((String)(request.getAttribute("comp_name")))); out.print(request.getAttribute("compt_name"));} %>
												</DIV> <INPUT ID="Company" name="comp_name" TYPE="hidden"
												VALUE="<% if(request.getAttribute("compt_position").equals("first")){out.print(request.getAttribute("compt_name")); out.print(xss.escape((String)(request.getAttribute("comp_name"))));}else{out.print(xss.escape((String)(request.getAttribute("comp_name")))); out.print(request.getAttribute("compt_name"));} %>">
												<INPUT ID="CompanyValue" TYPE="hidden" NAME="comp_id"
												VALUE="<%=request.getAttribute("comp_id") %>"
												onkeypress="return handleEnter(this, event)">
												<BUTTON TYPE="button" onclick="createWindow();"
													onkeypress="return handleEnter(this, event)">企業選択</BUTTON>
											</td>
										</tr>
										<tr>
											<th>何次試験<span class="fontc-2">*</span></th>
											<td><INPUT type="text" id="roe_stage" name="roe_stage"
												size="5" maxlength="5"
												onkeypress="return handleEnter(this, event)"
												value="<%=request.getAttribute("roe_stage") %>">試験
												<br />
												<span style="color:#999999">例　:　一次試験、一次前試験などと記入</span>
												</td>
										</tr>
										<tr>
											<th>面接官人数<span class="fontc-2">*</span></th>
											<td><INPUT type="text" id="roe_interviewer"
												name="roe_interviewer" size="5" maxlength="5"
												
												onkeypress="return handleEnter(this, event)"
												value="<%=request.getAttribute("roe_interviewer") %>">人　　<span style="color:#999999">※半角数字のみ</span>
												</td>
										</tr>
										<tr>
											<th>当校受験人数<span class="fontc-2">*</span></th>
											<td><INPUT type="text" id="roe_exam" name="roe_exam"
												size="5" maxlength="5" 
												onkeypress="return handleEnter(this, event)"
												value="<%=request.getAttribute("roe_exam") %>">人　　<span style="color:#999999">※半角数字のみ、自分を含む</span>
												</td>
										</tr>
										<tr>
											<th>他校受験人数<span class="fontc-2">*</span></th>
											<td><INPUT type="text" id="roe_examother"
												name="roe_examother" size="5" maxlength="5"
												
												onkeypress="return handleEnter(this, event)"
												value="<%=request.getAttribute("roe_examother") %>">人　　<span style="color:#999999">※半角数字のみ、無ければ0と記入</span>
												</td>
										</tr>
										<tr>
											<th>他校受験者出身校</th>
											<td><INPUT type="text" name="roe_examotherschool"
												size="50" maxlength="50"
												onkeypress="return handleEnter(this, event)"
												value="<%=request.getAttribute("roe_examotherschool") %>"></td>
										</tr>
										<tr>
											<th>面接担当者名または役職</th>
											<td><span id="slen1"></span> <span id="mes1-1"
												style="font-size: 12px"></span> <span id="mes1-2"
												style="font-size: 12px"></span> <br /><TEXTAREA name="roe_nameortitle" onfocus="test(1,roe_nameortitle)"
													onkeyup="test(1,roe_nameortitle)" rows="5" cols="50"><%=transferCRLT((String)request.getAttribute("roe_nameortitle")) %></TEXTAREA></td>
										</tr>
								</table>
							</fieldset> <!-- ここから分割されて -->
							<fieldset>
								<h2>
									<legend>グループディスカッションについて</legend>
								</h2>
								<table id="table-02">
									<%String checkR = (String)request.getAttribute("roe_discussion"); %>
									<tr>
										<th>グループディスカッション<span class="fontc-2">*</span></th>
										<td><input type="radio" value="yes" name="roe_discussion"
											onclick="open_close_row('roe_discussionnum','roe_discussiontime','roe_discussiontheme',1);document.getElementById('roe_discussionnum').focus();"
											onkeypress="return handleEnter(this, event)"
											<%if(checkR.equals("yes")){out.print("checked");}%> /> <label
											for="rb1">有る</label> <input id="none" type="radio" value="no"
											name="roe_discussion"
											onclick="open_close_row('roe_discussionnum','roe_discussiontime','roe_discussiontheme',2);"
											onkeypress="return handleEnter(this, event)"
											<% if(checkR.equals("no")){out.print("checked");}%> /> <label
											for="rb1">無し</label></td>
									</tr>
									<tr id="roe_discussion1">
										<th>人数</th>
										<td><INPUT type="text" id="roe_discussionnum"
											name="roe_discussionnum" size="5" maxlength="5"
											onkeypress="return handleEnter(this, event)"
											disabled="true"
											value="<%=request.getAttribute("roe_discussionnum") %>">人　　<span style="color:#999999">※半角数字のみ</span>
											<div id="message4" style="color: #FF3333;"></td>
									</tr>
									<tr id="roe_discussion2">
										<th>時間</th>
										<td><INPUT type="text" id="roe_discussiontime"
											name="roe_discussiontime" size="5" maxlength="5"
											
											onkeypress="return handleEnter(this, event)"
											disabled="true"
											value="<%=request.getAttribute("roe_discussiontime") %>">分　　<span style="color:#999999">※半角数字のみ</span>
											</td>
									</tr>
									<tr id="roe_discussion3">
										<th>テーマ</th>
										<td><span id="slen2"></span> <span id="mes2-1"
											style="font-size: 12px"></span> <span id="mes2-2"
											style="font-size: 12px"></span> <br /><TEXTAREA id="roe_discussiontheme"
												name="roe_discussiontheme" rows="5" cols="50" onfocus="test(2,roe_discussiontheme)"
													onkeyup="test(2,roe_discussiontheme)"
												disabled="true"><%=transferCRLT((String)request.getAttribute("roe_discussiontheme")) %></TEXTAREA></td>
									</tr>
								</table>
							</fieldset> <!-- ここから分割されて -->
							<fieldset>
								<h2>
									<legend>面接官から質問された内容について</legend>
								</h2>
								<table id="table-03">
									<% int i = 0;
	for(Object item : list){
		//ここ変えてね		
		BeansReportOralExaminationQuestion info = (BeansReportOralExaminationQuestion)item;
%>
									<tr>
										<th>質問</th>
										<td><TEXTAREA id="roeq_question<%=i%>="
												name="roeq_question<%=i %>" rows="5" cols="50"><%= transferCRLT(info.getRoeq_question()) %></TEXTAREA></td>
									</tr>
									<tr>
										<th>回答</th>
										<td><TEXTAREA id="roeq_answer<%=i%>"
										name="roeq_answer<%=i %>" rows="5" cols="50"><%if(!info.getRoeq_answer().equals("なし")){%><%=transferCRLT(info.getRoeq_answer())%></TEXTAREA><%}else{%></TEXTAREA><%}%>
										</td>
									</tr>
									<%
		i ++;
	}
%>
									<tr>
										<th></th>
										<td>
											<button type="button" id=addrow
												onClick="AddTableRows('roeq_question','roeq_answer','table-03','delrow' );"
												onkeypress="return handleEnter(this, event)">
												<span class="fontc-1">＋</span>質問を増やす
											</button>
											<button type="button" id=delrow
												onClick="deleteTable2('roeq_question','roeq_answer','table-03','delrow');"
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
										<th>合否連絡<span class="fontc-2">*</span></th>
										<td><select ID="accept_year" name="roe_accept_year"
											onchange="acceptMonthLoad();">
												<option value="">--</option>
										</select><label>年</label> <select ID="accept_month"
											name="roe_accept_month" onchange="acceptDayLoad();">
												<option value="">--</option>
										</select><label>月</label> <select ID="accept_day" name="roe_accept_day"
											onchange="nexttimeYearLoad();">
												<option value="">--</option>
										</select><label>日 頃</label> <%
	String acc = (String)request.getAttribute("roe_accept");
	String val2[] = null;
	boolean flag = false;
	if(acc != null){
		val2 = acc.split("-");
		if(val2.length > 2){
			flag = true;
		}
	}
%> <input type="hidden" name="accept_year_value" id="accept_year_value"
											value="<% if(flag)out.print(val2[0]); %>"> <input
											type="hidden" name="accept_month_value"
											id="accept_month_value"
											value="<% if(flag)out.print(val2[1]); %>"> <input
											type="hidden" name="accept_day_value" id="accept_day_value"
											value="<% if(flag)out.print(val2[2]); %>"></td>
									</tr>
									<tr>
<%
	String nex = (String)request.getAttribute("roe_nexttime");
	String val3[] = null;
	flag = false;
	if(request.getAttribute("roe_nexttime") != null){
		val3 = nex.split("-");	
		if(val3.length > 2){
			flag = true;
		}
	}
%>
										<th>次回受験<span class="fontc-2">*</span></th>
										<td><input type="radio" value="有る"
											name="roe_nexttime_check"
											onclick="open_close_row_button('nexttime_year','nexttime_month','nexttime_day','roe_nextexam','roe_preparation',1);"
											onkeypress="return handleEnter(this, event)"
											<%if(flag){out.print("checked");}%> />
											<label for="rb2">有る</label> <input id="noneNext" type="radio"
											value="有るが未定" name="roe_nexttime_check"
											<%if(request.getAttribute("roe_nexttime") == null || ((String)(request.getAttribute("roe_nexttime"))).equals("有るが未定")){out.print("checked");} %>
											onclick="open_close_row_button('nexttime_year','nexttime_month','nexttime_day','roe_nextexam','roe_preparation',2);"
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb4">有るが未定</label> <input id="noneNext" type="radio"
											value="無し" name="roe_nexttime_check"
											<%if(request.getAttribute("roe_nexttime") == null || ((String)(request.getAttribute("roe_nexttime"))).equals("無し")){out.print("checked");} %>
											onclick="open_close_row_button('nexttime_year','nexttime_month','nexttime_day','roe_nextexam','roe_preparation',3);"
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb4">無し</label></td>
									</tr>
									<tr id="roe_nexttime1">
										<th>受験予定日</th>
										<td><select ID="nexttime_year" name="roe_nexttime_year"
											onchange="nexttimeMonthLoad();"
											disabled="true">
												<option value="">--</option>
										</select><label>年</label> <select ID="nexttime_month"
											name="roe_nexttime_month" onchange="nexttimeDayLoad();"
											disabled="true">
												<option value="">--</option>
										</select><label>月</label> <select ID="nexttime_day"
											name="roe_nexttime_day" onchange="nexttimeValue();"
											disabled="true">
												<option value="">--</option>
										</select><label>日 頃</label> 
										<input type="hidden" name="nexttime_year_value"
											id="nexttime_year_value"
											value="<% if(flag)out.print(val3[0]); %>"> <input
											type="hidden" name="nexttime_month_value"
											id="nexttime_month_value"
											value="<% if(flag)out.print(val3[1]); %>"> <input
											type="hidden" name="nexttime_day_value"
											id="nexttime_day_value"
											value="<% if(flag)out.print(val3[2]); %>"></td>
									</tr>
									<tr>
										<th>次回試験内容</th>
										<td><span id="slen3"></span> <span id="mes3-1"
											style="font-size: 12px"></span> <span id="mes3-2"
											style="font-size: 12px"></span> <br /><TEXTAREA id="roe_nextexam" name="roe_nextexam" rows="5" cols="50" onkeyup = "test(3, roe_nextexam)" onfocus="test(3, roe_nextexam)"
												disabled="true"><%=transferCRLT((String)request.getAttribute("roe_nextexam")) %></TEXTAREA></td>
									</tr>
									<tr>
										<th>準備知識・情報</th>
										<td><span id="slen4"></span> <span id="mes4-1"
											style="font-size: 12px"></span> <span id="mes4-2"
											style="font-size: 12px"></span> <br /><TEXTAREA id="roe_preparation" name="roe_preparation" rows="5" cols="50" onkeyup = "test(4, roe_preparation)" onfocus="test(4, roe_preparation)"
												disabled="true"><%=transferCRLT((String)request.getAttribute("roe_preparation")) %></TEXTAREA></td>
									</tr>
									</tbody>
								</table>
								<input type="hidden" name="roe_id"
									value=<%=request.getParameter("roe_id")%>></input>
							</fieldset>
							<div id="button1">
								<input type="submit" value="" id="SaveAccount"
									onclick="return chkcompany()" />
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
					<!-- 各種機能のイメージ  -->
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
					<br />
					<!--ついったー-->
					<script src="http://widgets.twimg.com/j/2/widget.js"></script>
					<script><!--
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
						--></script>
					<script type="text/javascript">
					<!--		
					var validation,
								roe_discussiontheme = $("#roe_discussiontheme"),
								roe_discussiontime = $("#roe_discussiontime"),
								roe_discussionnum = $("#roe_discussionnum"),
								nexttime_year = $("#nexttime_year"),
								nexttime_month = $("#nexttime_month"),
								nexttime_day = $("#nexttime_day"),
								roe_nextexam = $("#roe_nextexam"),
								roe_preparation = $("#roe_preparation");
							validation = $("form").exValidation({
								rules: {
									participate_year:"chkselect",
									participate_month:"chkselect",
									participate_day:"chkselect",
									roe_stage:"chkrequired",
									roe_interviewer:"chknumonly chkrequired",
									roe_examother:"chkrequired chknumonly",
									roe_exam:"chkrequired chknumonly",
									roe_nameortitle:"chkmax200",
									roe_discussiontheme:"chkrequired chkmax200",
									roe_discussiontime:"chkrequired chknumonly",
									roe_discussionnum:"chkrequired chknumonly",
									accept_year:"chkselect",
									accept_month:"chkselect",
									accept_day:"chkselect",
									nexttime_year:"chkselect",
									nexttime_month:"chkselect",
									nexttime_day:"chkselect",
									roe_nextexam:"chkmax200",
									roe_preparation:"chkmax200",
								},
								firstValidate:true,
								errFocus: true,
								errHoverHide:true,
								//エラーがあった場合に呼び出す関数
								customAddError: function() {
									if ( $("#alert").length<1 ) {
										alert("入力値に誤りがあります。");
									}
								},
//								エラーがなかったときに呼び出す関数
											customClearError:function(){
												mlength();
											},
								});
								if($('input[name=roe_discussion][value=no]').attr('checked')){
									roe_discussiontheme.removeClass("chkrequired");
								}
								if($('input[name=roe_discussion][value=yes]').attr('checked')){
									roe_discussiontheme.removeAttr("disabled");
								}
								$("input[name=roe_discussion]").click(function(){
									if(this.id === "none"){
										roe_discussiontheme.removeClass("chkrequired");
										validation.laterCall("#roe_discussionnum");
									}else{
										roe_discussiontheme.addClass("chkrequired");
										validation.laterCall("#roe_discussionthem");
								}
								});
								if($('input[name=roe_discussion][value=no]').attr('checked')){
									roe_discussiontime.removeClass("chkrequired");
								}
								if($('input[name=roe_discussion][value=yes]').attr('checked')){
									roe_discussiontime.removeAttr("disabled");
								}
								$("input[name=roe_discussion]").click(function(){
									if(this.id === "none"){
										roe_discussiontime.removeClass("chkrequired");
										validation.laterCall("#roe_discussiontime");
									}else{
										roe_discussiontime.addClass("chkrequired");
										validation.laterCall("#roe_discussiontime");
									}
								});
								if($('input[name=roe_discussion][value=no]').attr('checked')){
									roe_discussionnum.removeClass("chkrequired");
								}
								if($('input[name=roe_discussion][value=yes]').attr('checked')){
									roe_discussionnum.removeAttr("disabled");
								}
								$("input[name=roe_discussion]").click(function(){
									if(this.id === "none"){
										roe_discussionnum.removeClass("chkrequired");
										validation.laterCall("#roe_discussiontheme");
									}else{
										roe_discussionnum.addClass("chkrequired");
										validation.laterCall("#roe_discussiontheme");
									}
								});
								if($('input[name=roe_nexttime_check][value=無し]').attr('checked') || $('input[name=roe_nexttime_check][value=有るが未定]').attr('checked')){
									nexttime_year.removeClass("chkselect");
								}
								if($('input[name=roe_nexttime_check][value=有る]').attr('checked')){
									nexttime_year.removeAttr("disabled");
								}
								$("input[name=roe_nexttime_check]").click(function(){
									if(this.id === "noneNext"){
										nexttime_year.removeClass("chkselect");
										validation.laterCall("#nexttime_year");
									}else{
										nexttime_year.addClass("chkselect");
										validation.laterCall("#nexttime_year");
									}
								});
								if($('input[name=roe_nexttime_check][value=無し]').attr('checked') || $('input[name=roe_nexttime_check][value=有るが未定]').attr('checked')){
									nexttime_month.removeClass("chkselect");
								}
								if($('input[name=roe_nexttime_check][value=有る]').attr('checked')){
									nexttime_month.removeAttr("disabled");
								}
								$("input[name=roe_nexttime_check]").click(function(){
									if(this.id === "noneNext"){
											nexttime_month.removeClass("chkselect");
											validation.laterCall("#nexttime_month");
									}else{
											nexttime_month.addClass("chkselect");
											validation.laterCall("#nexttime_month");
									}
								});
								if($('input[name=roe_nexttime_check][value=無し]').attr('checked') || $('input[name=roe_nexttime_check][value=有るが未定]').attr('checked')){
									nexttime_day.removeClass("chkselect");
								}
								if($('input[name=roe_nexttime_check][value=有る]').attr('checked')){
									nexttime_day.removeAttr("disabled");
									roe_nextexam.removeAttr("disabled");
									roe_preparation.removeAttr("disabled");
								}
								if($('input[name=roe_nexttime_check][value=有るが未定]').attr('checked')){
									roe_nextexam.removeAttr("disabled");
									roe_preparation.removeAttr("disabled");
								}
								$("input[name=roe_nexttime_check]").click(function(){
									if(this.id === "noneNext"){
										nexttime_day.removeClass("chkselect");
										validation.laterCall("#nexttime_day");
									}else{
										nexttime_day.addClass("chkselect");
										validation.laterCall("#nexttime_day");
										roe_nextexam.removeAttr("disabled");
										roe_preparation.removeAttr("disabled");
									}
								});
								var selectable = $('#pref').selectable({
									callback: function() {
									validation.laterCall('#pref');
								}
							});
						--></script>
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
</body>
</html>
