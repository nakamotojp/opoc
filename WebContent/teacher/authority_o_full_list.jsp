<%@page import="jp.bemax.se.graduation2011.auth.ReportInfo"%>
<%@page import="jp.bemax.se.graduation2011.auth.Xss"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="list" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="list2" class="java.util.ArrayList" scope="request"></jsp:useBean>
<%--　セッションの取得  --%>
<% String u_name = (String)session.getAttribute("u_name");

Xss xss = new Xss();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">

<!-- 権限のある人が見える報告書一覧 -->

<head>
<!--　ファビコン -->
<link rel="shortcut icon" type="image/x-icon" href="images/icon.ico" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta name="description" content="専門学校ビーマックス就職活動支援ページ" />
<meta name="keywords" content="Opoc,ビーマックス,報告書提出,就職活動,報告書検索" />

<title>Opoc</title>

<link href="style2.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/exvalidation.css" />
<script type="text/javascript" src="js/Subject.js"></script>
<script type="text/javascript" src="js/Course.js"></script>
<%--10件表示用スクリプト --%>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script type="text/javascript" src="js/formToWizard_list.js"></script>

<script type="text/javascript" src="script.js"></script>

<script type="text/JavaScript">
<!--
//10件ごとに表示用
$(document).ready(function(){
    $("#SignupForm").formToWizard_list({ submitButton: 'SaveAccount' ,section: 0 ,ListSize: <%=list.size() %>});
    $("#SignupForm2").formToWizard_list({ submitButton: 'SaveAccount' ,section: 1 ,ListSize:<%=list2.size()%>});
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
//削除ボタンリンク先
function JUMP(bunrui,id){
	var role = "<%= (String)session.getAttribute("role") %>";
	if(role == "teacher"){
	  if (confirm("削除しますか？")==true){
			if(bunrui == 1){
				location.href='JoinMeetingDelete?rjm_id=' + id;
			}
			//筆記なら
			if(bunrui == 2){
				location.href='WrittenExaminationDelete?rwe_id=' + id;
			}
			//面接なら
			if(bunrui == 3){
				location.href='OralExaminationDelete?roe_id=' + id;
			}
	  }
	}else{
		confirm("権限がありません");
		location.href ="AuthorityNewReportList";
	}
}	

//学科コース学年の選択がきちんとできてなかったときの処理
function ErrCorse(){
	if(		document.formMain.s_id.value != "" &&
			document.formMain.c_id.value != ""
	){
		return true;
	}
	return false;

	
}


-->
</script>
</head>


<body
	onload="requestSubject();MM_preloadImages('images/menu_over_01.gif','images/menu_over_02.gif','images/menu_over_03.gif','images/menu_over_04.gif','images/menu_over_05.gif','images/menu_over_06_a.gif')">
	<img class="bg" src="images/bg-l.gif" alt=""/>

	<div id="container">


		<div id="header">
			<h1>
				<a onclick="return confirm('ログアウトしますか？')" href="Logout"　>ログアウト</a>
			</h1>
			<!-- ロゴ -->
			<a href="AuthorityNewReportList"><img src="images/logo.png"
				name="logo" id="logo" /></a>

			<ul id="menu">
				<li><a href="AuthorityNewReportList"><img
						src="images/menu_01.gif" alt="トップページへ" width="120" height="50"
						id="Image1"
						onmouseover="MM_swapImage('Image1','','images/menu_over_01.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="AuthorityReportList"><img
						src="images/menu_over_02_a.gif" alt="担当報告書一覧ページへ" width="120"
						height="50" id="Image2"
						onmouseover="MM_swapImage('Image2','','images/menu_over_02_a.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="JoinMeetingList"><img src="images/menu_03.gif"
						alt="企業説明会報告書一覧ページへ" width="120" height="50" id="Image3"
						onmouseover="MM_swapImage('Image3','','images/menu_over_03.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="WrittenExaminationList"><img
						src="images/menu_04.gif" alt="筆記試験報告書一覧ページへ" width="120"
						height="50" id="Image4"
						onmouseover="MM_swapImage('Image4','','images/menu_over_04.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="OralExaminationList"><img
						src="images/menu_05.gif" alt="面接試験報告書一覧ページへ" width="120"
						height="50" id="Image5"
						onmouseover="MM_swapImage('Image5','','images/menu_over_05.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="ForgetPassword"><img
						src="images/menu_06_a.gif" alt="ユーザー情報変更ページへ" width="120"
						height="50" id="Image6"
						onmouseover="MM_swapImage('Image6','','images/menu_over_06_a.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
			</ul>
		</div>
		<!--/header-->


		<div id="contents">
			<div id="wrap">
				<div id="main">
					<form name="formMain" method="post" action="AuthorityReportList"
						onsubmit="return ErrCorse()">
						<h2>学科選択</h2>
						<table id="table-list">
							<tr>
								<th class="table-listbox">学科<span class="fontc-2">*</span></th>
								<th class="table-listbox">コース<span class="fontc-2">*</span></th>
								<th class="table-listbox">学年</th>
								<th></th>
							</tr>
							<tr>
								<td><select id="Subject" name="s_id"
									onchange="deleteCourse();">
										<option value="">(学科を選択)</option>
								</select> <INPUT TYPE="hidden" ID="SubjectName" NAME="s_name" VALUE="">
								</td>
								<td><SELECT ID="Course" NAME="c_id"
									onchange="deleteCourseYear();">
										<OPTION VALUE="">(コースを選択)</OPTION>
								</SELECT> <INPUT TYPE="hidden" ID="CourseName" NAME="c_name" VALUE="">
								</td>
								<td><SELECT ID="CourseYear" NAME="u_year">
										<OPTION VALUE="-1">(学年を選択)</OPTION>
								</SELECT></td>
								<td><input type="submit" value="選択" class="button" /></td>
							</tr>
						</table>
					</form>
					<center>
						<span class="fontc-2">---未確認<%=request.getAttribute("n_count") %>件
							・ 再提出未確認<%=request.getAttribute("r_count") %>件 ・ 再提出<%=request.getAttribute("a_count") %>件---
						</span>
					</center>

					<span id="SignupForm"> <%
    	  	if(list.size()== 0){
			%>
						<fieldset>
							<h2>報告書一覧 未登録 0件</h2>
							<table id="table-list">
								<tr>
									<th class="table-day">提出日</th>
									<th class="table-day">参加日</th>
									<th class="table-comp-full">説明会名</th>
									<th>企業名</th>
									<th class="table-c_name">コース名</th>
									<th class="table-u_name">登録者名</th>
									<th class="table-class">分類</th>
									<th class="table-Status">提出状態</th>
									<th class="table-details">詳細</th>
								</tr>
								<tr>
									<td colspan="9">未登録データなし</td>
								</tr>
							</table>
						</fieldset> <%
			
			}
			%> <%int i = 0;
			int count = 0; 
	      	for(Object data: list){
  				ReportInfo item = (ReportInfo)data;
		
				if(i%10==0){
					if(i>= 10){ %>
						</table>
						</fieldset> <%} %>
						<fieldset>
							<h2>
								<legend>
									報告書一覧 未登録
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
									<th class="table-comp-full">説明会名</th>
									<th>企業名</th>
									<th class="table-c_name">コース名</th>
									<th class="table-u_name">登録者名</th>
									<th class="table-class">分類</th>
									<th class="table-Status">提出状態</th>
									<th class="table-details">詳細</th>
								</tr>

								<%} i ++;%>

								<tr>
									<td><%=item.getIntroduction() %></td>
									<td><%=item.getParticipate() %></td>
									<% String name = ("---");
			if(item.getName() != null){
				 name = item.getName();
				if(name.length() >= 6){
					name = name.substring(0,6) + "...";
				
				}
			}
				%>
									<td><%=xss.escape(name)%></td>
									<%String comp_name = item.getComp_name();
			if(comp_name.length() >= 8){
				comp_name = comp_name.substring(0,8) + "...";
			}
			%>
									<td><%=xss.escape(comp_name)%></td>
									<%String c_name = item.getC_name();
			if(c_name.length() >= 6){
				c_name = c_name.substring(0,6) + "...";
			}
			%>
									<td><%=c_name%></td>
									<%String u_nameOp = item.getU_name();
			if(u_nameOp.length() > 8){
				u_nameOp = u_nameOp.substring(0,8) + "...";
			}
			%>
									<td><%=u_nameOp%></td>
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
									<td>
										<% String status = item.getStatus();
			if(status.equals("new")){
				out.print("<span class='fontc-2' >未確認</span>");
			}else if(status.equals("renew")){
				out.print("<span class='fontc-2' >未確認（再）</span>");
			}else if(status.equals("again")){
				out.print("<span class='fontc-1'>再提出</span>");
			}%>
									</td>
									<td><input type="button" value="詳細"
										onclick="jump2(<%=bunrui%>,<%=item.getId()%>);"></input> <input
										type="button" id=list_del value="削除"
										onclick="JUMP(<%=bunrui%>,<%=item.getId()%>)"></input></td>
								</tr>
								<%
			}
		%>
							</table>
						</fieldset>
					</span> <span id="SignupForm2"> <%
      	if(list2.size()== 0){
			%>
						<fieldset>
							<h2>報告書一覧 登録済 0件</h2>

							<table id="table-list">
								<tr>
									<th class="table-day">提出日</th>
									<th class="table-day">参加日</th>
									<th class="table-comp-full">説明会名</th>
									<th>企業名</th>
									<th class="table-c_name">コース名</th>
									<th class="table-u_name">登録者名</th>
									<th class="table-class">分類</th>
									<th class="table-details-op">詳細</th>
								</tr>

								<tr>
									<td colspan="8">登録データなし</td>
								</tr>
							</table>
							<%
			}
		%>

							<%			 i = 0;
			 count = 0; 
			for(Object data: list2){
				ReportInfo item = (ReportInfo)data;
		
		if(i%10==0){
			count ++;
			if(i>= 10){ %>
							</table>
						</fieldset> <%} %>
						<fieldset>
							<h2>
								<legend>
									報告書一覧 登録済
									<%	count = i + 10;
			out.print("全" + list2.size() + "件");
			if(list2.size()>10){
						out.print("中　");
					}else if(list2.size()<=10){
						out.print("　");
					}
					if(list2.size() < count){
						count = list2.size();
					}

					if(list2.size()>=10){
						out.print(i + 1 + "～" + count + "件");
					} %>
								</legend>
							</h2>
							<table id="table-list">
								<tr>
									<th class="table-day">提出日</th>
									<th class="table-day">参加日</th>
									<th class="table-comp-full">説明会名</th>
									<th>企業名</th>
									<th class="table-c_name">コース名</th>
									<th class="table-u_name">登録者名</th>
									<th class="table-class">分類</th>
									<th class="table-details-op">詳細</th>
								</tr>
								<%} i ++;%>
								<tr>
									<td><%=item.getIntroduction() %></td>
									<td><%=item.getParticipate() %></td>
									<% String name = ("---");
			if(item.getName() != null){
				 name = item.getName();
				if(name.length() > 6){
					name = name.substring(0,6) + "...";
				
				}
			}
				%>
									<td><%=xss.escape(name)%></td>
									<%String comp_name = item.getComp_name();
			if(comp_name.length() > 15){
				comp_name = comp_name.substring(0,15) + "...";
			}
			%>
									<td><%=xss.escape(comp_name)%></td>
									<%String c_name = item.getC_name();
			if(c_name.length() > 6){
				c_name = c_name.substring(0,6) + "...";
			}
			%>
									<td><%=c_name%></td>
									<%String u_nameOp = item.getU_name();
			if(u_nameOp.length() > 8){
				u_nameOp = u_nameOp.substring(0,8) + "...";
			}
			%>
									<td><%=u_nameOp%></td>
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
					</span> <a href="AuthorityNewReportList"><img
						src="images/bana/o_hunting.png" alt="トップページへ"></img></a>
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
						<li><a href="AuthorityReportList" class="o_authority_full"></a></li>
						<li><a href="JoinMeetingList" class="o_authority_report"></a></li>
						<li><a href="WrittenExaminationList"
							class="authority_o_written_e"></a></li>
						<li><a href="OralExaminationList" class="o_authority_oral_e"></a></li>
						<li><a href="ForgetPassword" class="o_authority_jub_user"></a></li>
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
	<!--<script type="text/javascript" src="js/jquery.js"></script>-->

	<script type="text/javascript" src="js/jquery.easing.js"></script>
	<script type="text/javascript" src="js/exvalidation.js"></script>
	<script type="text/javascript" src="js/exchecker-ja.js"></script>
	<script type="text/javascript">
	<!--
	var validation = $("form")
		.exValidation({
			rules: {
				Subject:"chkselect",
				Course:"chkselect"
				
			},
			
			stepValidation:true,
			scrollToErr:false
		});
	-->
</script>
</body>
</html>
