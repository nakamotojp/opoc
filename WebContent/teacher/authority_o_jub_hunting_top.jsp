<%@page import="jp.bemax.se.graduation2011.auth.JoinMeetingInfo"%>
<%@page import="jp.bemax.se.graduation2011.auth.WrittenExaminationInfo"%>
<%@page import="jp.bemax.se.graduation2011.auth.OralExaminationInfo"%>
<%@page import="jp.bemax.se.graduation2011.auth.ReportNewInfo"%>
<%@page import="jp.bemax.se.graduation2011.auth.ReportNewList"%>
<%@page import="jp.bemax.se.graduation2011.auth.Xss"%>

<jsp:useBean id="list1" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="list2" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="list3" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="list4" class="java.util.ArrayList" scope="request"></jsp:useBean>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%--　セッションの取得  --%>
<% String u_name = (String)session.getAttribute("u_name");

Xss xss = new Xss();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>

<!--　ファビコン -->
<link rel="shortcut icon" type="image/x-icon" href="images/icon.ico" />

<!--　権限のある人が見る、就職活動機能一覧トップ -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta name="description" content="専門学校ビーマックス就職活動支援ページ" />
<meta name="keywords" content="Opoc,ビーマックス,報告書提出,就職活動,報告書検索" />

<title>Opoc</title>


<link href="style2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="script.js"></script>
<script type="text/JavaScript">
<!--
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
}
-->
</script>


</head>


<body
	onload="MM_preloadImages('images/menu_over_01.gif','images/menu_over_02.gif','images/menu_over_03.gif','images/menu_over_04.gif','images/menu_over_05.gif','images/menu_over_06_a.gif')">
	<img class="bg" src="images/bg-l.gif" alt=""/>

	<div id="container">


		<div id="header">
			<h1>
				<a onclick="return confirm('ログアウトしますか？')" href="Logout"　>ログアウト</a>
			</h1>
			<a href="AuthorityNewReportList"><img src="images/logo.png"
				name="logo" id="logo" /></a>

			<ul id="menu">
				<li><a href="AuthorityNewReportList"><img
						src="images/menu_over_01.gif" alt="トップページ" width="120" height="50"
						id="Image1"
						onmouseover="MM_swapImage('Image1','','images/menu_over_01.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="AuthorityReportList"><img
						src="images/menu_02_a.gif" alt="担当報告書一覧ページへ" width="120"
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

					<!--提出中一覧テーブル -->
					<center>
						<span class="fontc-2">---未確認<%=request.getAttribute("n_count") %>件
							・ 再提出未確認<%=request.getAttribute("r_count") %>件 ・ 再提出<%=request.getAttribute("a_count") %>件---
						</span>
					</center>
					<h2>現在未登録の報告書</h2>
					<table id="table-list">
						<tr>
							<th class="table-day">提出日</th>
							<th class="table-day">参加日</th>
							<th>説明会名</th>
							<th>企業名</th>
							<th class="table-c_name">コース名</th>
							<th class="table-u_name">登録者名</th>
							<th class="table-class">分類</th>
							<th class="table-pre">提出状態</th>
							<th class="table-details">詳細</th>
						</tr>
						<%
  	if(list4.size()== 0){
	%>
						<tr>
							<td colspan="9">現在提出中未登録の報告書はありません</td>
						</tr>
						<%
  	} 
	
		for(Object data: list4){
			ReportNewInfo item = (ReportNewInfo)data;
			%>
						<tr>
							<!--提出日-->
							<td><%=item.getIntroduction() %></td>
							<!--参加日-->
							<td><%=item.getParticipate() %></td>
							<%
		//説明会名
			String name = ("---");
			if(item.getName() != null){ %>
							<% name = item.getName();
				if(name.length() > 8){
					name = name.substring(0,8) + "...";
				
				}
			}
				%>
							<td><%=xss.escape(name)%></td>
							<!--企業名-->
							<%String comp_name = item.getComp_name();
			if(comp_name.length() > 8){
				comp_name = comp_name.substring(0,8) + "...";
			}
			%>
							<td><%=xss.escape(comp_name)%></td>
							<!--コース名-->
							<%String c_name = item.getC_name();
			if(c_name.length() > 6){
				c_name = c_name.substring(0,6) + "...";
			}
			%>
							<td><%=c_name%></td>
							<!--登録者名-->
							<%String u_nameOp = item.getU_name();
			if(u_nameOp.length() > 8){
				u_nameOp = u_nameOp.substring(0,8) + "...";
			}
			%>
							<td><%=u_nameOp%></td>
							<!--提出状態-->
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
							<!--分類-->
							<td>
								<%
			String status = item.getStatus();
			if(status.equals("new")){
				out.print("<span class='fontc-2' >未確認</span>");
			}else if(status.equals("renew")){
				out.print("<span class='fontc-2' >未確認（再）</span>");
			}else if(status.equals("again")){
				out.print("<span class='fontc-1' >再提出</span>");
			}				
				%>
							</td>
							<td><input type="button" value="詳細"
								onclick="jump2(<%=bunrui%>,<%=item.getId()%>);"></input> <input
								type="button" id=list_del value="削除"
								onclick="JUMP(<%=bunrui%>,<%=item.getId()%>)"></input></td>
						</tr>


						<%	}
			
		%>
					</table>
					<div id="button_more">
						<input type="button" onclick="location.href='AuthorityReportList'" />
					</div>
					<!--企業報告書一覧テーブル -->
					<h2>企業説明会報告書新着</h2>
					<table id="table-list">
						<tr>
							<th class="table-day">提出日</th>
							<th class="table-day">参加日</th>
							<th>説明会名</th>
							<th>企業名</th>
							<th class="table-c_name">コース名</th>
							<th class="table-u_name-long">登録者名</th>
							<th class="table-details-op">詳細</th>

						</tr>
						<%
  	if(list1.size()== 0){
	%>
						<tr>
							<td colspan="7">登録されている報告書はありません</td>
						</tr>
						<%  	}	
		for(Object data: list1){
		JoinMeetingInfo item = (JoinMeetingInfo)data;
 %>
						<tr>
							<td><%=item.getRjm_introduction() %></td>
							<td><%=item.getRjm_participate() %></td>
							<!-- 企業名が長い時に切り捨てて表示させる -->
							<%String rjm_name = item.getRjm_name();
			if(rjm_name.length() > 15){
				rjm_name = rjm_name.substring(0,15) + "...";
			}
				%>
							<td><%=xss.escape(rjm_name)%></td>
							<%String comp_name = item.getComp_name();
			if(comp_name.length() > 15){
				comp_name = comp_name.substring(0,15) + "...";
			}
				%><td><%=xss.escape(comp_name)%></td>
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
							<td><input type="button" value="詳細"
								onclick="location.href='JoinMeetingDetailed?rjm_id=<%=item.getRjm_id() %>'"></input>
							</td>
						</tr>
						<%} %>
					</table>
					<div id="button_more">
						<input type="button" onclick="location.href='JoinMeetingList'" />
					</div>
					<!--筆記試験テーブル -->
					<h2>筆記試験報告書新着</h2>
					<table id="table-list">
						<tr>
							<th class="table-day">提出日</th>
							<th class="table-day">参加日</th>
							<th>企業名</th>
							<th class="table-c_name">コース名</th>
							<th class="table-u_name-long">登録者名</th>
							<th class="table-details-op">詳細</th>
						</tr>
						<%
  	if(list2.size()== 0){
	%>
						<tr>
							<td colspan="6">登録されている報告書はありません</td>
						</tr>
						<%  	}	
		for(Object data: list2){
		WrittenExaminationInfo item = (WrittenExaminationInfo)data;
 %>
						<tr>
							<td><%=item.getRwe_introduction() %></td>
							<td><%=item.getRwe_participate() %></td>
							<!-- 企業名が長い時に切り捨てて表示させる -->
							<%String comp_name = item.getComp_name();
			if(comp_name.length() > 22){
				comp_name = comp_name.substring(0,22) + "...";
			}
				%><td><%=xss.escape(comp_name)%></td>
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
							<td><input type="button" value="詳細"
								onclick="location.href='WrittenExaminationDetailed?rwe_id=<%=item.getRwe_id() %>'"></input>
							</td>
						</tr>
						<%} %>
					</table>
					<div id="button_more">
						<input type="button"
							onclick="location.href='WrittenExaminationList'" />
					</div>

					<!--面接試験テーブル -->
					<h2>面接試験報告書新着</h2>
					<table id="table-list">
						<tr>
							<th class="table-day">提出日</th>
							<th class="table-day">参加日</th>
							<th>企業名</th>
							<th class="table-c_name">コース名</th>
							<th class="table-u_name-long">登録者名</th>
							<th class="table-details-op">詳細</th>
						</tr>
						<%
  	if(list3.size()== 0){
	%>
						<tr>
							<td colspan="6">登録されている報告書はありません</td>
						</tr>
						<%  	}	
		for(Object data: list3){
		OralExaminationInfo item = (OralExaminationInfo)data;
 %>
						<tr>
							<td><%=item.getRoe_introduction() %></td>
							<td><%=item.getRoe_participate() %></td>
							<!-- 企業名が長い時に切り捨てて表示させる -->
							<%String comp_name = item.getComp_name();
			if(comp_name.length() > 22){
				comp_name = comp_name.substring(0,22) + "...";
			}
				%><td><%=xss.escape(comp_name)%></td>
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
							<td><input type="button" value="詳細"
								onclick="location.href='OralExaminationDetailed?roe_id=<%=item.getRoe_id() %>'"></input>
							</td>
						</tr>
						<%} %>
					</table>
					<div id="button_more">
						<input type="button" onclick="location.href='OralExaminationList'" />
					</div>
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
