<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.bemax.se.graduation2011.auth.Xss"%>
<%!

private String transferCRLT(String value){
	String result = value.replace("【br】","\n");
	return result;
}

%>

<%--　セッションの取得  --%>
<% String u_name = (String)session.getAttribute("u_name");

	Xss xss = new Xss();
%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">

<!-- 筆記試験報告書 -->

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

<!--データベースから値をとってくる-->
<script type="text/javascript" SRC="js/CreateCompany.js"></script>
<script type="text/javascript" SRC="js/reportDate.js"></script>
<script type="text/javascript" SRC="js/ajaxzip3.js"></script>
<script type="text/javascript" SRC="js/enter.js"></script>
<script type="text/javascript" SRC="js/Company.js"></script>
<script type="text/javascript" SRC="js/CrossSite.js"></script>

<script type="text/javascript" src="js/jquery1-6-1.js"></script>
<script type="text/javascript" src="js/jquery.maskedinput-1.3.min.js"></script>
<script type="text/javascript" src="js/jquery-bangou.js"></script>
<script type="text/javascript">
<!--
   $(document).ready(function(){
       $("#SignupForm").formToWizard({ submitButton: 'SaveAccount' })
   });

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
	function open_close_row_next(yaer,month,day,nextexam,preparation,elm_number)
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
</head>


<body
	onload="introductionLoad();reportDateInit();MM_preloadImages('images/menu_over_01.gif','images/menu_over_02.gif','images/menu_over_03.gif','images/menu_over_04.gif','images/menu_over_05.gif','images/menu_over_06.gif')">

	<img class="bg" src="images/bg-l.gif" alt=""/>
	<div id="container">


		<div id="header">
			<h1>
				<a href="ChangePassword">パスワード変更</a> <a
					onclick="return confirm('ログアウトしますか？')" href="Logout"　>ログアウト</a>
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
						src="images/menu_over_04.gif" alt="筆記試験報告書一覧へ" width="120"
						height="50" id="Image4"
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


		<div id="contents">


			<div id="wrap">


				<div id="main">

					<!--この行で見出し表示 -->
					<FORM name=formMain method="POST"
						action="./student/o_written_e_conf.jsp">
						<% request.setCharacterEncoding("UTF-8"); %>
						<!--<td><th>に注意-->

						<!-- ここから分割されて -->
						<span id="SignupForm">
							<fieldset>
								<h2>
									<legend>書類登録</legend>
								</h2>

								<table id="table-02">
									<tbody>
										<tr>
											<th>提出日</th>
											<td>

												<div id="introduction_year_value">--</div>
												<label>年</label>
												<div id="introduction_month_value">--</div>
												<label>月</label>
												<div id="introduction_day_value">--</div>
												<label>日</label> <input type="hidden"
												name="rwe_introduction_year" id="introduction_year" value="">
												<input type="hidden" name="rwe_introduction_month"
												id="introduction_month" value=""> <input
												type="hidden" name="rwe_introduction_day"
												id="introduction_day" value="">
											</td>
										</tr>
										<tr>
											<th>参加日<span class="fontc-2">*</span></th>
											<td colspan="4">
												<!-- rj_participate--> <select ID="participate_year"
												name="rwe_participate_year"
												onchange="participateMonthLoad();">
													<option value="">--</option>
											</select><label>年</label> <select ID="participate_month"
												name="rwe_participate_month"
												onchange="participateDayLoad();">
													<option value="">--</option>
											</select><label>月</label> <select ID="participate_day"
												name="rwe_participate_day" onchange="acceptYearLoad();">
													<option value="">--</option>
											</select><label>日</label> <% String pal = (String)request.getAttribute("rwe_participate");
   	   String val1[] = pal.split("-");	%> <input type="hidden"
												name="participate_year_value" id="participate_year_value"
												value="<%= val1[0] %>"> <input type="hidden"
												name="participate_month_value" id="participate_month_value"
												value="<%= val1[1] %>"> <input type="hidden"
												name="participate_day_value" id="participate_day_value"
												value="<%= val1[2] %>">
										</tr>

										<%   String com = (String)request.getAttribute("compt_position");    %>

										<tr>
											<th>企業名<span class="fontc-2">*</span></th>
											<td>
												<DIV ID="CompanyName" style="display: inline">
													<% if(request.getAttribute("compt_position").equals("first")){out.print(request.getAttribute("compt_name")); out.print(xss.escape((String)request.getAttribute("comp_name")));}else{out.print(xss.escape((String)request.getAttribute("comp_name"))); out.print(request.getAttribute("compt_name"));} %>
												</DIV> <INPUT ID="Company" name="comp_name" TYPE="hidden"
												VALUE="<% if(request.getAttribute("compt_position").equals("first")){out.print(request.getAttribute("compt_name")); out.print(request.getAttribute("comp_name"));}else{out.print(request.getAttribute("comp_name")); out.print(request.getAttribute("compt_name"));} %>">
												<INPUT ID="CompanyValue" TYPE="hidden" NAME="comp_id"
												VALUE="<%=request.getAttribute("comp_id") %>"
												onkeypress="return handleEnter(this, event)">
												<BUTTON TYPE="button" onclick="createWindow();"
													onkeypress="return handleEnter(this, event)">企業選択</BUTTON>

											</td>
										</tr>
										<tr>
											<th>何次試験<span class="fontc-2">*</span></th>
											<td><INPUT type="text" id="rwe_stage" name="rwe_stage"
												size="5" maxlength="5"
												onkeypress="return handleEnter(this, event)"
												value="<%=request.getAttribute("rwe_stage") %>">試験
												<div style="color:#999999">例　:　一次試験、一次前試験などと記入</div></td>
										</tr>

										<tr>
											<th>他校受験人数<span class="fontc-2">*</span></th>
											<td><INPUT type="text" id="rwe_examother"
												name="rwe_examother" size="5" maxlength="5"
												value="<%=request.getAttribute("rwe_examother") %>"
												onkeypress="return handleEnter(this, event)">人　　<span style="color:#999999">※半角数字のみ、無ければ0と記入</span>
												</td>
										</tr>

										<tr>
											<th>当校受験人数<span class="fontc-2">*</span></th>
											<td><INPUT type="text" id="rwe_exam" name="rwe_exam"
												size="5" maxlength="5"
												value="<%=request.getAttribute("rwe_exam") %>"
												onkeypress="return handleEnter(this, event)">人　　<span style="color:#999999">※半角数字のみ、自分を含む</span>
												</td>
										</tr>

										<tr>
											<th>他校受験者出身校</th>
											<td><INPUT type="text" id="rwe_examotherschool"
												name="rwe_examotherschool" size="50" maxlength="100"
												value="<%=request.getAttribute("rwe_examotherschool") %>"
												onkeypress="return handleEnter(this, event)"></td>
										</tr>

										<tr>
											<th>適性検査内容</th>
											<td><TEXTAREA
													id="rwe_aptitude" name="rwe_aptitude" rows="5" cols="50"
													><%=transferCRLT((String)request.getAttribute("rwe_aptitude")) %></TEXTAREA></td>
										</tr>

										<tr>
											<th>適性検査時間<span class="fontc-2">*</span></th>
											<td><INPUT type="text" id="rwe_aptitudetime"
												name="rwe_aptitudetime" size="5" maxlength="5"
												value="<%=request.getAttribute("rwe_aptitudetime") %>"
												onkeypress="return handleEnter(this, event)">分　　<span style="color:#999999">※半角数字のみ、無ければ0と記入</span>
												</td>
										</tr>

										<tr>
											<th>筆記試験時間<span class="fontc-2">*</span></th>
											<td><INPUT type="text" id="rwe_writtentime"
												name="rwe_writtentime" size="5" maxlength="5"
												value="<%=request.getAttribute("rwe_writtentime") %>"
												onkeypress="return handleEnter(this, event)">分　　<span style="color:#999999">※半角数字のみ、無ければ0と記入</span>
												</td>
										</tr>

										<tr>
											<th>作文・論文時間<span class="fontc-2">*</span></th>
											<td><INPUT type="text" id="rwe_papertime"
												name="rwe_papertime" size="5" maxlength="5"
												value="<%=request.getAttribute("rwe_papertime") %>"
												onkeypress="return handleEnter(this, event)">分　　<span style="color:#999999">※半角数字のみ、無ければ0と記入</span>
												</td>
										</tr>
								</table>
							</fieldset> <!-- ここから分割されて -->
							<fieldset>
								<h2>
									<legend>筆記問題の内容について</legend>
								</h2>

								<table id="table-02">

									<tr>
										<th>国語問題内容<span class="fontc-2">*</span></th>
										<% String checkR = (String)request.getAttribute("rwe_lang"); %>
										<td><input type="radio" value="yes" name="rwe_lang_check"
											onclick="open_close_row('rwe_lang',1);document.getElementById('rwe_lang').focus();"
											<% if(checkR != null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb2">有る</label> <input id="none" type="radio" value="no"
											name="rwe_lang_check" onclick="open_close_row('rwe_lang',2);"
											<% if(checkR == null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb4">無し</label></td>
									</tr>

									<tr id="rwe_lang1">
										<th>どんな問題か</th>
										<td><span id="slen2"></span> <span id="mes2-1"
											style="font-size: 12px"></span> <span id="mes2-2"
											style="font-size: 12px"></span> <br /> <TEXTAREA
												id="rwe_lang" name="rwe_lang"
												disabled="true"
												rows="5" cols="50" onfocus="test(2, rwe_lang)"
												onKeyup="test(2, rwe_lang)"><% if(checkR != null || checkR.equals("")){%><%=transferCRLT(checkR)%><%}%></TEXTAREA></td>
									</tr>

									<tr>
										<th>数学問題内容<span class="fontc-2">*</span></th>
										<% checkR = (String)request.getAttribute("rwe_math"); %>
										<td><input type="radio" value="yes" id="rwe_math_check"
											name="rwe_math_check"
											onclick="open_close_row('rwe_math',1);document.getElementById('rwe_math').focus();"
											<% if(checkR != null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb2">有る</label> <input id="none" type="radio" value="no"
											id="rwe_math_check" name="rwe_math_check"
											onclick="open_close_row('rwe_math',2);"
											<% if(checkR == null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb4">無し</label></td>
									</tr>
									<tr id="rwe_math1">
										<th>どんな問題か</th>
										<td><span id="slen3"></span> <span id="mes3-1"
											style="font-size: 12px"></span> <span id="mes3-2"
											style="font-size: 12px"></span> <br /> <TEXTAREA
												id="rwe_math" name="rwe_math"
												disabled="true"
												rows="5" cols="50" onfocus="test(3, rwe_math)"
												onKeyup="test(3, rwe_math)"><% if(checkR != null || checkR.equals("")){%><%=transferCRLT(checkR)%><%}%></TEXTAREA></td>
									</tr>

									<tr>
										<th>英語問題内容<span class="fontc-2">*</span></th>
										<% checkR = (String)request.getAttribute("rwe_english"); %>

										<td><input type="radio" value="yes"
											id="rwe_english_check" name="rwe_english_check"
											onclick="open_close_row('rwe_english',1);document.getElementById('rwe_english').focus();"
											<% if(checkR != null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb2">有る</label> <input id="none" type="radio" value="no"
											id="rwe_english_check" name="rwe_english_check"
											onclick="open_close_row('rwe_english',2);"
											<% if(checkR == null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb4">無し</label></td>
									</tr>
									<tr id="rwe_english1">
										<th>どんな問題か</th>
										<td><span id="slen4"></span> <span id="mes4-1"
											style="font-size: 12px"></span> <span id="mes4-2"
											style="font-size: 12px"></span> <br /> <TEXTAREA
												id="rwe_english" name="rwe_english"
												disabled="true"
												rows="5" cols="50" onfocus="test(4, rwe_english)"
												onKeyup="test(4, rwe_english)"><% if(checkR != null || checkR.equals("")){%><%=transferCRLT(checkR)%><%}%></TEXTAREA></td>
									</tr>

									<tr>
										<th>理科問題内容<span class="fontc-2">*</span></th>
										<% checkR = (String)request.getAttribute("rwe_science"); %>
										<td><input type="radio" value="yes"
											id="rwe_science_check" name="rwe_science_check"
											onclick="open_close_row('rwe_science',1);document.getElementById('rwe_science').focus();"
											<% if(checkR != null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb2">有る</label> <input id="none" type="radio" value="no"
											id="rwe_science_check" name="rwe_science_check"
											onclick="open_close_row('rwe_science',2);"
											<% if(checkR == null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb4">無し</label></td>
									</tr>
									<tr id="rwe_science1">
										<th>どんな問題か</th>
										<td><span id="slen5"></span> <span id="mes5-1"
											style="font-size: 12px"></span> <span id="mes5-2"
											style="font-size: 12px"></span> <br /> <TEXTAREA
												id="rwe_science" name="rwe_science"
												disabled="true"
												rows="5" cols="50" onfocus="test(5, rwe_science)"
												onKeyup="test(5, rwe_science)"><% if(checkR != null || checkR.equals("")){%><%=transferCRLT(checkR)%><%}%></TEXTAREA></td>
									</tr>

									<tr>
										<th>社会問題内容<span class="fontc-2">*</span></th>
										<% checkR = (String)request.getAttribute("rwe_society"); %>
										<td><input type="radio" value="yes"
											id="rwe_society_check" name="rwe_society_check"
											onclick="open_close_row('rwe_society',1);document.getElementById('rwe_society').focus();"
											<% if(checkR != null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb2">有る</label> <input id="none" type="radio" value="no"
											id="rwe_society_check" name="rwe_society_check"
											onclick="open_close_row('rwe_society',2);"
											<% if(checkR == null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb4">無し</label></td>
									</tr>
									<tr id="rwe_society1">
										<th>どんな問題か</th>
										<td><span id="slen6"></span> <span id="mes6-1"
											style="font-size: 12px"></span> <span id="mes6-2"
											style="font-size: 12px"></span> <br /> <TEXTAREA
												id="rwe_society" name="rwe_society"
												disabled="true"
												rows="5" cols="50" onfocus="test(6, rwe_society)"
												onKeyup="test(6, rwe_society)"><% if(checkR != null || checkR.equals("")){%><%=transferCRLT(checkR)%><%}%></TEXTAREA></td>
									</tr>

									<tr>
										<th>政治・経済問題内容<span class="fontc-2">*</span></th>
										<% checkR = (String)request.getAttribute("rwe_politics"); %>
										<td><input type="radio" value="yes"
											id="rwe_politics_check" name="rwe_politics_check"
											onclick="open_close_row('rwe_politics',1);document.getElementById('rwe_politics').focus();"
											<% if(checkR != null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb2">有る</label> <input id="none" type="radio" value="no"
											id="rwe_politics_check" name="rwe_politics_check"
											onclick="open_close_row('rwe_politics',2);"
											<% if(checkR == null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb4">無し</label></td>
									</tr>
									<tr id="rwe_politics1">
										<th>どんな問題か</th>
										<td><span id="slen7"></span> <span id="mes7-1"
											style="font-size: 12px"></span> <span id="mes7-2"
											style="font-size: 12px"></span> <br /> <TEXTAREA
												id="rwe_politics" name="rwe_politics"
												disabled="true"
												rows="5" cols="50" onfocus="test(7, rwe_politics)"
												onKeyup="test(7, rwe_politics)"><% if(checkR != null || checkR.equals("")){%><%=transferCRLT(checkR)%><%}%></TEXTAREA></td>
									</tr>

									<tr>
										<th>専門的問題内容<span class="fontc-2">*</span></th>
										<% checkR = (String)request.getAttribute("rwe_expert"); %>
										<td><input type="radio" value="yes" id="rwe_expert_check"
											name="rwe_expert_check"
											onclick="open_close_row('rwe_expert',1);document.getElementById('rwe_expert').focus();"
											<% if(checkR != null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb2">有る</label> <input id="none" type="radio" value="no"
											id="rwe_expert_check" name="rwe_expert_check"
											onclick="open_close_row('rwe_expert',2);"
											<% if(checkR == null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb4">無し</label></td>
									</tr>
									<tr id="rwe_expert1">
										<th>どんな問題か</th>
										<td><span id="slen8"></span> <span id="mes8-1"
											style="font-size: 12px"></span> <span id="mes8-2"
											style="font-size: 12px"></span> <br /> <TEXTAREA
												id="rwe_expert" name="rwe_expert"
												disabled="true"
												rows="5" cols="50" onfocus="test(8, rwe_expert)"
												onKeyup="test(8, rwe_expert)"><% if(checkR != null || checkR.equals("")){%><%=transferCRLT(checkR)%><%}%></TEXTAREA></td>
									</tr>
									<tr>
										<th>作文・論文内容<span class="fontc-2">*</span></th>
										<% checkR = (String)request.getAttribute("rwe_paper"); %>
										<td><input type="radio" value="yes" id="rwe_paper_check"
											name="rwe_paper_check"
											onclick="open_close_row('rwe_paper',1);document.getElementById('rwe_paper').focus();"
											<% if(checkR != null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb2">有る</label> <input id="none" type="radio" value="no"
											id="rwe_paper_check" name="rwe_paper_check"
											onclick="open_close_row('rwe_paper',2);"
											<% if(checkR == null || checkR.equals("")){out.print("checked");}%>
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb4">無し</label></td>
									</tr>
									<tr id="rwe_paper1">
										<th>どんな問題か</th>
										<td><span id="slen9"></span> <span id="mes9-1"
											style="font-size: 12px"></span> <span id="mes9-2"
											style="font-size: 12px"></span> <br /> <TEXTAREA
												id="rwe_paper" name="rwe_paper"
												disabled="true"
												rows="5" cols="50" onfocus="test(9, rwe_paper)"
												onKeyup="test(9, rwe_paper)"><% if(checkR != null || checkR.equals("")){%><%=transferCRLT(checkR)%><%}%></TEXTAREA></td>
									</tr>
									<tr>
										<th>その他問題内容</th>
										<td><span id="slen10"></span> <span id="mes10-1"
											style="font-size: 12px"></span> <span id="mes10-2"
											style="font-size: 12px"></span> <br /> <TEXTAREA
												id="rwe_other" name="rwe_other" rows="5" cols="50"
												onfocus="test(10, rwe_other)" onKeyup="test(10, rwe_other)"><%=transferCRLT((String)request.getAttribute("rwe_other")) %></TEXTAREA></td>
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
										<td><select ID="accept_year" name="rwe_accept_year"
											onchange="acceptMonthLoad();">
												<option value="">--</option>
										</select><label>年</label> <select ID="accept_month"
											name="rwe_accept_month" onchange="acceptDayLoad();">
												<option value="">--</option>
										</select><label>月</label> <select ID="accept_day" name="rwe_accept_day"
											onchange="nexttimeYearLoad();">
												<option value="">--</option>
										</select><label>日 頃</label> <% String acc = (String)request.getAttribute("rwe_accept");
   	   String val2[] = acc.split("-");
   	   if(val2.length > 2){%> <input type="hidden"
											name="accept_year_value" id="accept_year_value"
											value="<%= val2[0] %>"> <input type="hidden"
											name="accept_month_value" id="accept_month_value"
											value="<%= val2[1] %>"> <input type="hidden"
											name="accept_day_value" id="accept_day_value"
											value="<%= val2[2] %>"> <% } %></td>
									</tr>
									<tr>
									<%
										String nex = (String)request.getAttribute("rwe_nexttime");
										String val3[] = null;
										boolean flag = false;
										if(nex != null){
											val3 = nex.split("-");
											if(val3.length > 2){
												flag = true;
											}
										}
									%>
										<th>次回受験<span class="fontc-2">*</span></th>
										<td>
											<% checkR = (String)request.getAttribute("rwe_nexttime"); %> <input
											type="radio" value="有る" name="rwe_nexttime_check"
											<% if(flag){out.print("checked");} %>
											onclick="open_close_row_next('rwe_nexttime_year','rwe_nexttime_month','rwe_nexttime_day','rwe_nextexam','rwe_preparation',1);"
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb1">有る</label> <input id="noneNext" type="radio"
											value="有るが未定" name="rwe_nexttime_check"
											<% if(checkR == null || checkR.equals("有るが未定")){out.print("checked");} %>
											onclick="open_close_row_next('rwe_nexttime_year','rwe_nexttime_month','rwe_nexttime_day','rwe_nextexam','rwe_preparation',2);"
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb1">有るが未定</label> <input id="noneNext" type="radio"
											value="無し" name="rwe_nexttime_check"
											<% if(checkR == null || checkR.equals("無し")){out.print("checked");} %>
											onclick="open_close_row_next('rwe_nexttime_year','rwe_nexttime_month','rwe_nexttime_day','rwe_nextexam','rwe_preparation',3);"
											onkeypress="return handleEnter(this, event)" /> <label
											for="rb1">無し</label>

										</td>
									</tr>
									<tr id="col1">
										<th>受験予定日</th>
										<td><select ID="nexttime_year" name="rwe_nexttime_year"
											onchange="nexttimeMonthLoad();"
											disabled="true">
												<option value="">--</option>
										</select><label>年</label> <select ID="nexttime_month"
											name="rwe_nexttime_month" onchange="nexttimeDayLoad();"
											disabled="true">
												<option value="">--</option>
										</select><label>月</label> <select ID="nexttime_day"
											name="rwe_nexttime_day" onchange="nexttimeValue();"
											disabled="true">
												<option value="">--</option>
										</select><label>日 頃</label>  <input type="hidden" name="nexttime_year_value"
											id="nexttime_year_value"
											value="<% if(flag)out.print(val3[0]); %>"> <input
											type="hidden" name="nexttime_month_value"
											id="nexttime_month_value"
											value="<% if(flag)out.print(val3[1]); %>"> <input
											type="hidden" name="nexttime_day_value"
											id="nexttime_day_value"
											value="<% if(flag)out.print(val3[2]); %>"></td>
									</tr>

									<tr id="col2">
										<th>次回試験内容</th>
										<td><span id="slen11"></span> <span id="mes11-1"
											style="font-size: 12px"></span> <span id="mes11-2"
											style="font-size: 12px"></span> <br /> <TEXTAREA
												id="rwe_nextexam" name="rwe_nextexam"
												disabled="true"
												rows="5" cols="50" onfocus="test(11, rwe_nextexam)"
												onKeyup="test(11, rwe_nextexam)"><% if(request.getAttribute("rwe_nextexam") != null)out.print(transferCRLT((String)request.getAttribute("rwe_nextexam"))); %></TEXTAREA></td>
									</tr>

									<tr id="col3">
										<th>準備知識・情報</th>
										<td><span id="slen12"></span> <span id="mes12-1"
											style="font-size: 12px"></span> <span id="mes12-2"
											style="font-size: 12px"></span> <br /> <TEXTAREA
												id="rwe_preparation" name="rwe_preparation"
												disabled="true"
												rows="5" cols="50" onfocus="test(12, rwe_preparation)"
												onKeyup="test(12, rwe_preparation)"><% if(request.getAttribute("rwe_preparation") != null)out.print(transferCRLT((String)request.getAttribute("rwe_preparation"))); %></TEXTAREA></td>
									</tr>
									</tbody>
								</table>
							</fieldset> <input type="hidden" name="rwe_id"
							value=<%=request.getParameter("rwe_id")%>></input>
						</span>
						<div id="button1">
							<input type="submit" value="" id="SaveAccount"
								onclick="return chkcompany()" />
						</div>
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
	rwe_lang = $("#rwe_lang"),
	rwe_math = $("#rwe_math"),
	rwe_english = $("#rwe_english"),
	rwe_science = $("#rwe_science"),
	rwe_society = $("#rwe_society"),
	rwe_politics = $("#rwe_politics"),
	rwe_expert = $("#rwe_expert"),
	rwe_paper = $("#rwe_paper"),
	nexttime_year = $("#nexttime_year"),
	nexttime_month = $("#nexttime_month"),
	nexttime_day = $("#nexttime_day"),
	rwe_nextexam = $("#rwe_nextexam"),
	rwe_preparation = $("#rwe_preparation");
	validation = $("form")
		.exValidation({
			rules: {
				participate_year:"chkselect",
				participate_month:"chkselect",
				participate_day:"chkselect",
				rwe_stage:"chkrequired",
				rwe_examother:"chkrequired chknumonly",
				rwe_exam:"chkrequired chknumonly",
				rwe_aptitudetime:"chkrequired chknumonly",
				rwe_writtentime:"chkrequired chknumonly",
				rwe_papertime:"chkrequired chknumonly",
				rwe_lang:"chkrequired chkmax200",
				rwe_math:"chkrequired chkmax200",
				rwe_english:"chkrequired chkmax200",
				rwe_science:"chkrequired chkmax200",
				rwe_society:"chkrequired chkmax200",
				rwe_politics:"chkrequired chkmax200",
				rwe_expert:"chkrequired chkmax200",
				rwe_paper:"chkrequired chkmax200",
				rwe_other:"chkmax200",
				accept_year:"chkselect",
				accept_month:"chkselect",
				accept_day:"chkselect",
				nexttime_year:"chkselect",
				nexttime_month:"chkselect",
				nexttime_day:"chkselect",
				rwe_nextexam:"chkmax200",
				rwe_preparation:"chkmax200",
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
				
		});
	if($('input[name=rwe_lang_check][value=no]').attr('checked')){
		rwe_lang.removeClass("chkrequired");
	}
	if($('input[name=rwe_lang_check][value=yes]').attr('checked')){
		rwe_lang.removeAttr("disabled");
	}
	$("input[name=rwe_lang_check]").click(function(){
		if(this.id === "none"){
			rwe_lang.removeClass("chkrequired");
			validation.laterCall("#rwe_lang");
		}else{
			rwe_lang.addClass("chkrequired");
			validation.laterCall("#rwe_lang");
		}
	});
	if($('input[name=rwe_math_check][value=no]').attr('checked')){
		rwe_math.removeClass("chkrequired");
	}
	if($('input[name=rwe_math_check][value=yes]').attr('checked')){
		rwe_math.removeAttr("disabled");
	}
	$("input[name=rwe_math_check]").click(function(){
		if(this.id === "none"){
			rwe_math.removeClass("chkrequired");
			validation.laterCall("#rwe_math");
		}else{
			rwe_math.addClass("chkrequired");
			validation.laterCall("#rwe_math");
		}
	});
	if($('input[name=rwe_english_check][value=no]').attr('checked')){
		rwe_english.removeClass("chkrequired");
	}
	if($('input[name=rwe_english_check][value=yes]').attr('checked')){
		rwe_english.removeAttr("disabled");
	}
	$("input[name=rwe_english_check]").click(function(){
		if(this.id === "none"){
			rwe_english.removeClass("chkrequired");
			validation.laterCall("#rwe_english");
		}else{
			rwe_english.addClass("chkrequired");
			validation.laterCall("#rwe_english");
		}
	});
	if($('input[name=rwe_science_check][value=no]').attr('checked')){
		rwe_science.removeClass("chkrequired");
	}
	if($('input[name=rwe_science_check][value=yes]').attr('checked')){
		rwe_science.removeAttr("disabled");
	}
	$("input[name=rwe_science_check]").click(function(){
		if(this.id === "none"){
			rwe_science.removeClass("chkrequired");
			validation.laterCall("#rwe_science");
		}else{
			rwe_science.addClass("chkrequired");
			validation.laterCall("#rwe_science");
		}
	});
	if($('input[name=rwe_society_check][value=no]').attr('checked')){
		rwe_society.removeClass("chkrequired");
	}
	if($('input[name=rwe_society_check][value=yes]').attr('checked')){
		rwe_society.removeAttr("disabled");
	}
	$("input[name=rwe_society_check]").click(function(){
		if(this.id === "none"){
			rwe_society.removeClass("chkrequired");
			validation.laterCall("#rwe_society");
		}else{
			rwe_society.addClass("chkrequired");
			validation.laterCall("#rwe_society");
		}
	});
	if($('input[name=rwe_politics_check][value=no]').attr('checked')){
		rwe_politics.removeClass("chkrequired");
	}
	if($('input[name=rwe_politics_check][value=yes]').attr('checked')){
		rwe_politics.removeAttr("disabled");
	}
	$("input[name=rwe_politics_check]").click(function(){
		if(this.id === "none"){
			rwe_politics.removeClass("chkrequired");
			validation.laterCall("#rwe_politics");
		}else{
			rwe_politics.addClass("chkrequired");
			validation.laterCall("#rwe_politics");
		}
	});
	if($('input[name=rwe_expert_check][value=no]').attr('checked')){
		rwe_expert.removeClass("chkrequired");
	}
	if($('input[name=rwe_expert_check][value=yes]').attr('checked')){
		rwe_expert.removeAttr("disabled");
	}
	$("input[name=rwe_expert_check]").click(function(){
		if(this.id === "none"){
			rwe_expert.removeClass("chkrequired");
			validation.laterCall("#rwe_expert");
		}else{
			rwe_expert.addClass("chkrequired");
			validation.laterCall("#rwe_expert");
		}
	});
	if($('input[name=rwe_paper_check][value=no]').attr('checked')){
		rwe_paper.removeClass("chkrequired");
	}
	if($('input[name=rwe_paper_check][value=yes]').attr('checked')){
		rwe_paper.removeAttr("disabled");
	}
	$("input[name=rwe_paper_check]").click(function(){
		if(this.id === "none"){
			rwe_paper.removeClass("chkrequired");
			validation.laterCall("#rwe_paper");
		}else{
			rwe_paper.addClass("chkrequired");
			validation.laterCall("#rwe_paper");
		}
	});
	if($('input[name=rwe_nexttime_check][value=無し]').attr('checked') || $('input[name=rwe_nexttime_check][value=有るが未定]').attr('checked')){
		nexttime_year.removeClass("chkselect");
	}
	if($('input[name=rwe_nexttime_check][value=有る]').attr('checked')){
		nexttime_year.removeAttr("disabled");
	}
	$("input[name=rwe_nexttime_check]").click(function(){
		if(this.id === "noneNext"){
			nexttime_year.removeClass("chkselect");
			validation.laterCall("#nexttime_year");
		}else{
			nexttime_year.addClass("chkselect");
			validation.laterCall("#nexttime_year");
		}
	});
	if($('input[name=rwe_nexttime_check][value=無し]').attr('checked') || $('input[name=rwe_nexttime_check][value=有るが未定]').attr('checked')){
		nexttime_month.removeClass("chkselect");
	}
	if($('input[name=rwe_nexttime_check][value=有る]').attr('checked')){
		nexttime_month.removeAttr("disabled");
	}
	$("input[name=rwe_nexttime_check]").click(function(){
		if(this.id === "noneNext"){
			nexttime_month.removeClass("chkselect");
			validation.laterCall("#nexttime_month");
		}else{
			nexttime_month.addClass("chkselect");
			validation.laterCall("#nexttime_month");
		}
	});
	if($('input[name=rwe_nexttime_check][value=無し]').attr('checked') || $('input[name=rwe_nexttime_check][value=有るが未定]').attr('checked')){
		nexttime_day.removeClass("chkselect");
	}
	if($('input[name=rwe_nexttime_check][value=有る]').attr('checked')){
		nexttime_day.removeAttr("disabled");
		rwe_nextexam.removeAttr("disabled");
		rwe_preparation.removeAttr("disabled");
	}
	if($('input[name=rwe_nexttime_check][value=有るが未定]').attr('checked')){
		rwe_nextexam.removeAttr("disabled");
		rwe_preparation.removeAttr("disabled");
	}
	$("input[name=rwe_nexttime_check]").click(function(){
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