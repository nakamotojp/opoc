<%@page import="jp.bemax.se.graduation2011.auth.ReportInfo"%>
<%@page import="jp.bemax.se.graduation2011.auth.Xss"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="list" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="list2" class="java.util.ArrayList" scope="request"></jsp:useBean>

<%--　セッションの取得  --%>
<%
	String u_name = (String)session.getAttribute("u_name");

	Xss xss = new Xss();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<!--　ファビコン -->
<link rel="shortcut icon" type="image/x-icon" href="images/icon.ico" />
<!--　就職活動検索ページ -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta name="description" content="専門学校ビーマックス就職活動支援ページ" />
<meta name="keywords" content="Opoc,ビーマックス,報告書提出,就職活動,報告書検索" />

<title>Opoc</title>

<link href="style2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="js/CreateCompany.js"></script>
<script type="text/javascript" src="js/reportDate.js"></script>
<script type="text/javascript" src="js/ajaxzip3.js"></script>
<script type="text/javascript" src="js/enter.js"></script>
<script type="text/javascript" src="js/Company.js"></script>
<%--10件表示用スクリプト --%>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script type="text/javascript" src="js/formToWizard_list.js"></script>

<script type="text/javascript" src="js/Subject.js"></script>
<script type="text/javascript" src="js/Course.js"></script>
<script type="text/JavaScript">
<!--
	
	//10件ごとに表示用
	$(document).ready(function(){
		$("#SignupForm").formToWizard_list({ submitButton: 'SaveAccount' ,section: 0 ,ListSize: <%=list.size()%>});
	 });
	 
	//詳細ボタンリンク先
	function jump2(bunrui,id) {
		//企業説明会なら
		if(bunrui == 1){
			location.href='JoinMeetingDetailed?rjm_id=' + id;
		}
		//筆記なら
		if(bunrui == 2){
			location.href='WrittenExaminationDetailed?rwe_id=' + id;
		}
		//面接なら
		if(bunrui == 3){
			location.href='OralExaminationDetailed?roe_id=' + id;
		}
	}
	
	//学科コース学年の選択がきちんとできてなかったときの処理
	function ErrCorse(){
		if(document.formMain.Company.value != ""){
			return true;
		}
		alert("企業を選択してください");
		return false;
	}
-->	
</script>

</head>


<body
	onload="requestSubject();MM_preloadImages('images/menu_over_01.gif','images/menu_over_02.gif','images/menu_over_03.gif','images/menu_over_04.gif','images/menu_over_05.gif','images/menu_over_06.gif')">
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
						src="images/menu_04.gif" alt="筆記試験報告書一覧へ" width="120" height="50"
						id="Image4"
						onmouseover="MM_swapImage('Image4','','images/menu_over_04.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="OralExaminationList"><img
						src="images/menu_05.gif" alt="面接試験報告書一覧へ" width="120" height="50"
						id="Image5"
						onmouseover="MM_swapImage('Image5','','images/menu_over_05.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="SearchByCompany"><img
						src="images/menu_over_06.gif" alt="検索ページへ" width="120" height="50"
						id="Image6"
						onmouseover="MM_swapImage('Image6','','images/menu_over_06.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
			</ul>
		</div>
		<!--/header-->


		<div id="contents">

			<div id="wrap">

				<div id="main">
					<form name="formMain" method="get" action="SearchByCompany"
						onsubmit="return ErrCorse()">
						<h2>企業名で検索</h2>
						<table id="table-list">
							<tr>
								<th>企業名<span class="fontc-2">*</span></th>
								<th>分類</th>
								<th>学科</th>
								<th></th>
							</tr>
							<tr>
								<td class="table-long">
									<DIV ID="CompanyName" style="display: inline">
										<% if(request.getAttribute("comp_name") != null || request.getAttribute("comp_name") != null){out.print(xss.escape((String)request.getAttribute("comp_name")));} %>
									</DIV> <INPUT ID="Company" name="comp_name" TYPE="hidden"
									VALUE="<% if(request.getAttribute("comp_name") != null || request.getAttribute("comp_name") != null){out.print(request.getAttribute("comp_name"));} %>">
									<INPUT ID="CompanyValue" TYPE="hidden" NAME="comp_id"
									VALUE="<% if(request.getAttribute("comp_id") != null || request.getAttribute("comp_id") != null){out.print(request.getAttribute("comp_id"));} %>"
									onkeypress="return handleEnter(this, event)">
									<BUTTON TYPE="button" onclick="selectWindow();"
										onkeypress="return handleEnter(this, event)">企業選択</BUTTON>
								</td>
								<td><select name="Num" ID="Num">
										<option value="-1"
											<% if(request.getAttribute("Num") != null && request.getAttribute("Num").equals("-1")){out.print(" selected ");} %>>全報告書</option>
										<option value="1"
											<% if(request.getAttribute("Num") != null && request.getAttribute("Num").equals("1")){out.print(" selected ");} %>>説明会</option>
										<option value="2"
											<% if(request.getAttribute("Num") != null && request.getAttribute("Num").equals("2")){out.print(" selected ");} %>>筆記試験</option>
										<option value="3"
											<% if(request.getAttribute("Num") != null && request.getAttribute("Num").equals("3")){out.print(" selected ");} %>>面接試験</option>
								</select></td>
								<td><SELECT ID="Subject" NAME="s_id"">
										<OPTION VALUE="-1">(学科を選択してください)</OPTION>
								</SELECT> 
								</td>

								<td><input type="submit" value="検索" /></td>
							</tr>

						</table>
					</form>

					<span id="SignupForm"> <%if(list.size()== 0){ %>
						<fieldset>
							<h2>報告書一覧 検索結果 0件</h2>

							<table id="table-list">
								<tr>
									<th class="table-day">提出日</th>
									<th class="table-day">参加日</th>
									<th class="table-comp">説明会名</th>
									<th>企業名</th>
									<th>学科名</th>
									<th class="table-pre">分類</th>
									<th class="table-details-op">詳細</th>
								</tr>

								<tr>
									<td colspan="8">検索データなし</td>
								</tr>
							</table>
							<% } %>

							<%
			int i = 0;
			int count = 0; 
			for(Object data: list){
				ReportInfo item = (ReportInfo)data;
		
			if(i%10==0){
				count ++;
				if(i>= 10){ 
		%>
							</table>
						</fieldset> <% } %>
						<fieldset>
							<h2>
								<legend>
									報告書一覧 検索結果
									<%
			out.print("全" + list.size() + "件");
			if(list.size()>10){
						out.print("中　");
					}else if(list.size()<=10){
						out.print("　");
					}
					count = i + 10;
					if(list.size() < count){
						count = list.size();
					}

					if(list.size()>=10){
						out.print(i + 1 + "～" + count + "件");
			} %>
								</legend>
							</h2>
							<table id="table-list">
								<tr>
									<th class="table-day">提出日</th>
									<th class="table-day">参加日</th>
									<th class="table-comp">説明会名</th>
									<th>企業名</th>
									<th>学科名</th>
									<th class="table-pre">分類</th>
									<th class="table-details-op">詳細</th>
								</tr>
								<%} i ++;%>
								<tr>
									<td><%=item.getIntroduction() %></td>
									<td><%=item.getParticipate() %></td>
									<% String name = ("---");
			if(item.getName() != null){
				name = item.getName();
				
				if(name.length() >= 10){
					name = name.substring(0,10) + "...";
				}
			}
			%>

									<td><%=name%></td>

									<% String comp_name = item.getComp_name();
			if(comp_name.length() >= 10){
				comp_name = comp_name.substring(0,10) + "...";
			}
			%>
									<td><%=xss.escape(comp_name)%></td>
									<td><%=item.getS_name()%></td>
									<td>
										<%
			int bunrui = item.getNum();
				if(bunrui == 1){
					out.print("説明会");
				}
				if(bunrui == 2){
					out.print("筆記試験");
				}
				if(bunrui == 3){
					out.print("面接試験");
				}
			%>
									</td>
									<td><input type="button" value="詳細"
										onclick="jump2(<%=bunrui%>,<%=item.getId()%>);"></input></td>
								</tr>
								<%} %>
							</table>
						</fieldset>
					</span> <a href="ReportNewList"><img src="images/bana/o_hunting.png"
						alt="トップページへ"></img></a>


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

					<ul id="bana-sub">
						<li><a href="JoinMeetingInsert" class="o_report"></a></li>
						<li><a href="WrittenExaminationInsert" class="written_e"></a></li>
						<li><a href="OralExaminationInsert" class="o_oral_e"></a></li>
						<li><a href="SearchByCompany" class="o_jub_hunnting_search"></a></li>
					</ul>

					<div id="cal">

						<script type="text/javascript">
						<!--
	document.write(cal());
						-->
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

	</div>

</body>
</html>
