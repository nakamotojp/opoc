<%@page import="jp.bemax.se.graduation2011.auth.JoinMeetingInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="jp.bemax.se.graduation2011.auth.Xss"%>
<jsp:useBean id="list" class="java.util.ArrayList" scope="request"></jsp:useBean>

<%--　セッションの取得  --%>
<% String u_name = (String)session.getAttribute("u_name");
Xss xss = new Xss();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">

<!-- 企業説明会報告書一覧 -->

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

<script type="text/javascript" src="script.js"></script>
<%--10件表示用スクリプト --%>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<script type="text/javascript" src="js/formToWizard_list.js"></script>

<script type="text/JavaScript">
<!--
$(document).ready(function(){
    $("#SignupForm").formToWizard_list({ submitButton: 'SaveAccount' ,section: 0 ,ListSize: <%=list.size() %>});
 });
 -->
</script>
</head>


<body
	onload="MM_preloadImages('images/menu_over_01.gif','images/menu_over_02.gif','images/menu_over_03.gif','images/menu_over_04.gif','images/menu_over_05.gif','images/menu_over_06.gif')">
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


		<div id="contents">


			<div id="wrap">


				<div id="main">
					<div id="bana">
						<a href="JoinMeetingInsert" class="o_report"></a>
					</div>

					<span id="SignupForm"> <%
      	if(list.size()== 0){
			%>
						<fieldset>
							<h2>企業説明会報告書一覧 0件</h2>

							<table id="table-list">
								<tr>
									<th class="table-day">提出日</th>
									<th class="table-day">参加日</th>
									<th class="table-comp-re">企業説明会名</th>
									<th>企業名</th>
									<th class="table-details-op">詳細</th>
								</tr>

								<tr>
									<td colspan="5">登録データなし</td>
								</tr>
							</table>
						</fieldset> <%
			}
		%> <%			int i = 0;
			int count = 0; 
			for(Object data: list){
				JoinMeetingInfo item = (JoinMeetingInfo)data;
		
		if(i%10==0){
			count ++;
			if(i>= 10){ %>
						</table>
						</fieldset> <%} %>
						<fieldset>
							<h2>
								<legend>
									企業説明会報告書一覧
									<%
					count = i + 10;
						out.print("全" + list.size() + "件");
						if(list.size()>10){
									out.print("中　");
								}else if(list.size()<=10){
									out.print("　");
								}
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
									<th class="table-comp-re">企業説明会名</th>
									<th>企業名</th>
									<th class="table-details-op">詳細</th>
								</tr>

								<%} i ++;%>
								<tr>
									<td><%=item.getRjm_introduction() %></td>
									<td><%=item.getRjm_participate() %></td>
									<%String rjm_name = item.getRjm_name();
			if(rjm_name.length() >= 22){
				rjm_name = rjm_name.substring(0,22) + "...";
				
			}
				%>
									<td><%=xss.escape(rjm_name)%></td>
									<%String comp_name = item.getComp_name();
			if(comp_name.length() >= 22){
				comp_name = comp_name.substring(0,22) + "...";
			}
				%><td><%=xss.escape(comp_name)%></td>
									<td><input type="button" value="詳細"
										onclick="location.href='JoinMeetingDetailed?rjm_id=<%=item.getRjm_id() %>'"></input>
									</td>
								</tr>
								<%
			}
		%>
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


</body>
</html>
