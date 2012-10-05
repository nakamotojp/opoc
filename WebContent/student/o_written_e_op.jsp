<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="jp.bemax.se.graduation2011.auth.Xss"%>

<%!

private String transferCRLT(String value){
	String result = value.replace("【br】","<br/>");
	return result;
}

%>
<%--　セッションの取得  --%>
<% String u_name = (String)session.getAttribute("u_name");
Xss xss = new Xss();
%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">

<!-- 筆記試験詳細 -->

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
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<script type="text/javascript" src="js/formToWizard.js"></script>
<script type="text/JavaScript">
<!--
function jump1() {
	history.go(-1);
}

	function jump2() {
	location.href= "WrittenExaminationList";
	}

	function JUMP(item){
		  if (confirm("削除しますか？")==true){
		    //OKならTOPページにジャンプさせる
		    location.href = "WrittenExaminationDelete?rwe_id=" + item;

		  }
	}	
	
	//印刷用別ウィンドウを開く
	function open1() {
		var width = 600;
		var height = 400;
		 var features="location=no, menubar=no, status=yes, scrollbars=yes, resizable=yes, toolbar=yes";
		 if (width) {
		  if (window.screen.width > width)
		   features+=", left="+(window.screen.width-width)/2;
		  else width=window.screen.width;
		  features+=", width="+width;
		 }
		 if (height) {
		  if (window.screen.height > height)
		   features+=", top="+(window.screen.height-height)/2;
		  else height=window.screen.height;
		  features+=", height="+height;
		 }
	    window.open("WrittenExaminationDetailed?rwe_id=<%=request.getParameter("rwe_id")%>&ppp=true",'features');
	}
	
	-->
	</script>
</head>


<body
	onload="MM_preloadImages('images/menu_over_01.gif','images/menu_over_02.gif','images/menu_over_03.gif','images/menu_over_04.gif','images/menu_over_05.gif')">

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
						<h2>筆記試験詳細</h2>
						<% request.setCharacterEncoding("UTF-8"); %>

						<table id="table-02">

							<tbody>
								<tr>
									<th>提出日</th>
									<td>
										<% String s = (String)request.getAttribute("rwe_introduction");
	String ary[] = s.split("-");
	out.print(ary[0] + "年");
	out.print(ary[1] + "月");
	out.print(ary[2] + "日");
	%>
									</td>
									<td id="table-print">
										<FORM method="GET" action="WrittenExaminationDetailed" onsubmit="return false;">
										<input type="hidden" name="rwe_id"
											value=<%=request.getParameter("rwe_id")%>></input> <input
											type="hidden" name="ppp" value="true"></input> <input
											type="submit" value="印刷プレビュー" onclick="open1()"></input>
										</FORM>
									</td>		
								</tr>
								<tr>
									<th>参加日</th>
									<td colspan="2">
										<% s = (String)request.getAttribute("rwe_participate") ;
	ary = s.split("-");
	out.print(ary[0] + "年");
	out.print(ary[1] + "月");
	out.print(ary[2] + "日");
	%>
									</td>
								</tr>
								<tr>
									<th>受験した企業名</th>
									<td colspan="2"><%=xss.escape((String)(request.getAttribute("comp_name"))) %></td>
								</tr>
								<tr>
									<th>郵便番号</th>
									<td colspan="2"><%=request.getAttribute("comp_zip") %></td>
								</tr>
								<tr>
									<th>住所</th>
									<td colspan="2"><%=request.getAttribute("comp_address") %></td>
								</tr>
								<tr>
									<th>電話番号</th>
									<td colspan="2"><%=request.getAttribute("comp_phone") %></td>
								</tr>
								<tr>
									<th>何次試験</th>
									<td colspan="2"><%=request.getAttribute("rwe_stage") %>試験</td>
								</tr>

								<tr>
									<th>他校受験人数</th>
									<td colspan="2"><%=request.getAttribute("rwe_examother") %>人</td>
								</tr>

								<tr>
									<th>当校受験人数</th>
									<td colspan="2"><%=request.getAttribute("rwe_exam") %>人</td>
								</tr>

								<tr>
									<th>他校受験者出身校</th>
									<td colspan="2"><%=request.getAttribute("rwe_examotherschool") %></td>
								</tr>

								<tr>
									<th>適性検査内容</th>
									<td colspan="2"><%=transferCRLT((String)request.getAttribute("rwe_aptitude")) %></td>
								</tr>

								<tr>
									<th>適性検査時間</th>
									<td colspan="2"><%=request.getAttribute("rwe_aptitudetime") %>分</td>
								</tr>

								<tr>
									<th>筆記試験時間</th>
									<td colspan="2"><%=request.getAttribute("rwe_writtentime") %>分</td>
								</tr>

								<tr>
									<th>作文・論文時間</th>
									<td colspan="2"><%=request.getAttribute("rwe_papertime") %>分</td>
								</tr>
								<tr>
									<th>国語問題内容</th>
									<td colspan="2">
										<% if(request.getAttribute("rwe_lang").equals("null")){
			out.print("無し"); %> <% }else{ %> <%=transferCRLT((String)request.getAttribute("rwe_lang")) %>
										<% }
    	  %>
									</td>
								</tr>
								<tr>
									<th>数学問題内容</th>
									<td colspan="2">
										<% if(request.getAttribute("rwe_math").equals("null")){
			out.print("無し"); %> <% }else{ %> <%=transferCRLT((String)request.getAttribute("rwe_math")) %>
										<% }
    	  %>
									</td>
								</tr>

								<tr>
									<th>英語問題内容</th>
									<td colspan="2">
										<% if(request.getAttribute("rwe_english").equals("null")){
			out.print("無し"); %> <% }else{ %> <%=transferCRLT((String)request.getAttribute("rwe_english")) %>
										<% }
    	  %>
									</td>
								</tr>
								<tr>
									<th>理科問題内容</th>
									<td colspan="2">
										<% if(request.getAttribute("rwe_science").equals("null")){
			out.print("無し"); %> <% }else{ %> <%=transferCRLT((String)request.getAttribute("rwe_science")) %>
										<% }
    	  %>
									</td>
								</tr>

								<tr>
									<th>社会問題内容</th>
									<td colspan="2">
										<% if(request.getAttribute("rwe_society").equals("null")){
			out.print("無し"); %> <% }else{ %> <%=transferCRLT((String)request.getAttribute("rwe_society")) %>
										<% }
    	  %>
									</td>
								</tr>

								<tr>
									<th>政治・経済問題内容</th>
									<td colspan="2">
										<% if(request.getAttribute("rwe_politics").equals("null")){
			out.print("無し"); %> <% }else{ %> <%=transferCRLT((String)request.getAttribute("rwe_politics")) %>
										<% }
    	  %>
									</td>
								</tr>

								<tr>
									<th>専門的問題内容</th>
									<td colspan="2">
										<% if(request.getAttribute("rwe_expert").equals("null")){
			out.print("無し"); %> <% }else{ %> <%=transferCRLT((String)request.getAttribute("rwe_expert")) %>
										<% }
    	  %>

									</td>
								</tr>
								<tr>
									<th>作文・論文内容</th>
									<td colspan="2">
										<% if(request.getAttribute("rwe_paper").equals("null")){
			out.print("無し"); %> <% }else{ %> <%=transferCRLT((String)request.getAttribute("rwe_paper")) %>
										<% }
    	  %>
									
								</tr>
								<tr>
									<th>その他問題内容</th>
									<td colspan="2"><%=transferCRLT((String)request.getAttribute("rwe_other")) %></td>
								</tr>
								<tr>
									<th>合否連絡</th>
									<td colspan="2">
										<% s = (String)request.getAttribute("rwe_accept");
   		ary = s.split("-");
	out.print(ary[0] + "年");
	out.print(ary[1] + "月");
	out.print(ary[2] + "日");

   	%>
									</td>
								</tr>
								<tr>
									<th>次回受験</th>
									<td colspan="2">
										<%
    
    s = (String)request.getAttribute("rwe_nexttime");
    ary = s.split("-");
    if(ary.length == 3){
		out.print(ary[0] + "年");
		out.print(ary[1] + "月");
		out.print(ary[2] + "日");
   	%>
									</td>
								</tr>
								<tr>
									<th>次回試験内容</th>
									<td colspan="2"><%=transferCRLT((String)request.getAttribute("rwe_nextexam")) %></td>
								</tr>
								<tr>
									<th>準備知識・情報</th>
									<td colspan="2"><%=transferCRLT((String)request.getAttribute("rwe_preparation")) %></td>
								</tr>
								<%  }else if(request.getAttribute("rwe_nexttime").equals("有るが未定")){ %>
										有るが未定
									</td>
								</tr>
								<tr>
									<th>次回試験内容</th>
									<td colspan="2"><%=transferCRLT((String)request.getAttribute("rwe_nextexam")) %></td>
								</tr>
								<tr>
									<th>準備知識・情報</th>
									<td colspan="2"><%=transferCRLT((String)request.getAttribute("rwe_preparation")) %></td>
								</tr>
								<%	}else if(request.getAttribute("rwe_nexttime").equals("無し")){
	out.print("無し");
}%>
							
						</table>
						<div id="button_listback">
							<input type=button onclick="jump1()" value=""></input>
						</div>
						<%boolean edit =  Boolean.parseBoolean((String)request.getAttribute("edit"));
if(edit){%>
						<div id="button_op_change">
							<input type="button" value=""
								onclick="location.href='WrittenExaminationChangeBefore?rwe_id=<%=request.getAttribute("rwe_id")%>'"></input>
						</div>
						<%} %>
						<%boolean del =  Boolean.parseBoolean((String)request.getAttribute("del"));
if(del){%>
						<div id="button_del_u">
							<input type="button" value=""
								onclick="JUMP(<%=request.getAttribute("rwe_id")%>)"></input>
						</div>
						<%} %>
					<a href="WrittenExaminationList"><img
						src="images/bana/o_written_e_list.png" alt="面接試験報告書入力ページへ"></img></a>
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
