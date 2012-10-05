<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="jp.bemax.se.graduation2011.auth.OralExaminationDetailed"%>
<%@page
	import="jp.bemax.se.graduation2011.model.BeansReportOralExaminationQuestion"%>
	<%@page import="jp.bemax.se.graduation2011.auth.Xss"%>
<jsp:useBean id="list" class="java.util.ArrayList" scope="request"></jsp:useBean>

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

<!-- 面接試験報告書詳細 -->

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
<script type="text/javascript" src="js/tableplus.js"></script>
<script type="text/JavaScript">
<!--
	function JUMP(item){
		  if (confirm("削除しますか？")==true){
		    //OKならTOPページにジャンプさせる
		    location.href = "OralExaminationDelete?roe_id=" + item;

		  }
	}

	function JUMP_commit(item,status){
		var op;
		if(status == 'end'){
			if(item == 'again' ||item =="new"||item =="renew"){
				op = '報告書として承認しますか？';
			}else if(item == 'end'){
				op = '承認を取り消ししますか？' ;
			}
		}else if(status == 'again'){
			if(item == 'again'||item =="new"||item =="renew"){
				op = '再提出にしますか？';			
			}
		}
		  return confirm(op);
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
	    window.open("OralExaminationDetailed?roe_id=<%=request.getParameter("roe_id")%>&ppp=true",'features');
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
						src="images/menu_01.gif" alt="トップページへ" width="120" height="50"
						id="Image1"
						onmouseover="MM_swapImage('Image1','','images/menu_over_01.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="AuthorityReportList"><img
						src="images/menu_02_a.gif" alt="担当報告書一覧へ" width="120" height="50"
						id="Image2"
						onmouseover="MM_swapImage('Image2','','images/menu_over_02_a.gif',1)"
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
					<div id="bana">
						<a href="OralExaminationList"><img
							src="images/bana/o_oral_e_list.png" alt="面接試験報告書入力ページへ"></img></a>
					</div>
					<!--この行で見出し表示 -->
					<h2>面接試験報告書詳細</h2>
					<!-- 文字コードをUTF-8にすることでGetしたときに文字化けしなくなる -->
					<% request.setCharacterEncoding("UTF-8"); %>

					<!-- 学生情報テーブル -->
					<table id="table-01">
						<tr>
							<th>学籍番号</th>
							<td><%=request.getAttribute("l_id") %></td>
							<th>学科</th>
							<td><%=request.getAttribute("s_name") %></td>
							<th>コース</th>
							<td><%=request.getAttribute("c_name") %></td>
							<th>名前</th>
							<td><%=request.getAttribute("u_name") %></td>
							<!-- ステータスの受け取り -->
							<td class='status'>
								<%	 String status = (String)request.getAttribute("roe_status");
      		if(status.equals("new")){
				out.print("<span class='fontc-2' >未確認</span>");
			}else if(status.equals("renew")){
				out.print("<span class='fontc-2' >未確認（再）</span>");
			}else if(status.equals("again")){
				out.print("<span class='fontc-1'>再提出</span>");
			}else if(status.equals("end")){
				out.print("登録済");
			}
			%>
							</td>
							<td>
								<FORM method="GET" action="OralExaminationDetailed"
									onsubmit="return false;">
									<input type="hidden" name="roe_id"
										value=<%=request.getParameter("roe_id")%>></input> <input
										type="hidden" name="ppp" value="true"></input> <input
										type="submit" value="印刷プレビュー" onclick="open1()"></input>
							</td>
							</FORM>
						</tr>
					</table>


					<table id="table-02">
						<tr>
							<th>提出日</th>
							<td>
								<% String s = (String)request.getAttribute("roe_introduction");
	String ary[] = s.split("-");
	out.print(ary[0] + "年");
	out.print(ary[1] + "月");
	out.print(ary[2] + "日");
	%>
							</td>
						<tr>
							<th>参加日</th>
							<td>
								<% s = (String)request.getAttribute("roe_participate") ;
	ary = s.split("-");
	out.print(ary[0] + "年");
	out.print(ary[1] + "月");
	out.print(ary[2] + "日");
	%>
							</td>
						</tr>
						<tr>
							<th>受験した企業名</th>
							<td><%=xss.escape((String)request.getAttribute("comp_name")) %></td>
						</tr>

						<tr>
							<th>郵便番号</th>
							<td><%=request.getAttribute("comp_zip") %></td>
						</tr>
						<tr>
							<th>住所</th>
							<td><%=request.getAttribute("comp_address") %></td>
						</tr>
						<tr>
							<th>電話番号</th>
							<td><%=request.getAttribute("comp_phone") %></td>
						</tr>
						<tr>
							<th>何次試験</th>
							<td><%=request.getAttribute("roe_stage") %>試験</td>
						</tr>
						<tr>
							<th>面接官人数</th>
							<td><%=request.getAttribute("roe_interviewer") %>人</td>
						</tr>

						<tr>
							<th>当校受験人数</th>
							<td><%=request.getAttribute("roe_exam") %>人</td>
						</tr>


						<tr>
							<th>他校受験人数</th>
							<td><%=request.getAttribute("roe_examother") %>人</td>
						</tr>


						<tr>
							<th>他校受験者出身校</th>
							<td><%=request.getAttribute("roe_examotherschool") %></td>
						</tr>

						<tr>
							<th>面接担当者名または役職</th>
							<td><%=transferCRLT((String)request.getAttribute("roe_nameortitle")) %>
							</td>
						</tr>

						<tr>
							<th>グループディスカッション</th>
							<td>
								<% String flg = (String)request.getAttribute("roe_discussion"); 
      if(flg.equals("no")){
    	  out.print("無し"); 
      }
     if(flg.equals("yes")){
    	 out.print("有り");
	%>
							</td>
						</tr>
						<tr>
							<th>人数</th>
							<td><%=request.getAttribute("roe_discussionnum") %>人
						</tr>
						<tr>
							<th>時間</th>
							<td><%=request.getAttribute("roe_discussiontime") %>分</td>
						</tr>
						<tr>
							<th>テーマ</th>
							<td><%=transferCRLT((String)request.getAttribute("roe_discussiontheme")) %>
							</td>
						</tr>
						<%} %>
						<% for(Object item : list){
    	  BeansReportOralExaminationQuestion info = (BeansReportOralExaminationQuestion)item;
      		
    	      %>

						<tr>
							<th>質問</th>
							<td><%= transferCRLT(info.getRoeq_question()) %>
						</tr>
						<tr>
							<th>回答</th>
							<td><%= transferCRLT(info.getRoeq_answer()) %></td>
						</tr>
						<% } %>
						<tr>
							<th>合否連絡</th>
							<td>
								<% s = (String)request.getAttribute("roe_accept");
   	ary = s.split("-");
	out.print(ary[0] + "年");
	out.print(ary[1] + "月");
	out.print(ary[2] + "日");

   	%>
							</td>
						</tr>
						<tr>
							<th>次回受験</th>
							<td>
	<%if(request.getAttribute("roe_nexttime") != null && request.getAttribute("roe_nexttime").equals("無し")){
      out.print("無し");
	}else if(request.getAttribute("roe_nexttime") != null && request.getAttribute("roe_nexttime").equals("有るが未定")){ %>
		有るが未定
		</td>
	</tr>
	<tr>
		<th>次回試験内容</th>
		<td><%=transferCRLT((String)request.getAttribute("roe_nextexam")) %>
		</td>
	</tr>
	<tr>
		<th>準備知識・情報</th>
		<td><%=transferCRLT((String)request.getAttribute("roe_preparation")) %>
		</td>
		 <% }else if(request.getAttribute("roe_nexttime") != null){
			 s = (String)request.getAttribute("roe_nexttime");
			 ary = s.split("-");
			 out.print(ary[0] + "年");
			 out.print(ary[1] + "月");
			 out.print(ary[2] + "日");
			 %>
							</td>
						</tr>
						<tr>
							<th>次回試験内容</th>
							<td><%=transferCRLT((String)request.getAttribute("roe_nextexam")) %>
							</td>
						</tr>
						<tr>
							<th>準備知識・情報</th>
							<td><%=transferCRLT((String)request.getAttribute("roe_preparation")) %>
							</td>
						</tr>
						<% } %>
						</tbody>
					</table>
					<%-- 確定ボタンが押されていない場合 --%>
					<% String commit_check = (String)request.getAttribute("roe_status");
if(commit_check.equals("new")||commit_check.equals("renew")||commit_check.equals("again")){ %>
					<div id="button_del">
						<input type="button" value=""
							onclick="JUMP(<%=request.getAttribute("roe_id")%>)"></input>
					</div>
					<FORM name="formMain" method="GET"
						action="OralExaminationChangeStatus"
						onsubmit="return JUMP_commit('<%=commit_check%>','end')"
						style="display: inline;">
						<div id="button_commit">
							<input type="hidden" name="roe_id"
								value=<%=request.getParameter("roe_id")%>></input> <input
								type="hidden" name="roe_status" value="end"></input> <input
								type="submit" value=""></input>
						</div>
					</FORM>

					<FORM method="GET" action="OralExaminationChangeStatus"
						onsubmit="return JUMP_commit('<%=commit_check%>','again')"
						style="display: inline;">
						<%
if(!commit_check.equals("again")){
%>
						<div id="button_again">
							<input type="hidden" name="roe_id"
								value=<%=request.getParameter("roe_id")%>></input> <input
								type="hidden" name="roe_status" value="again"></input> <input
								type="submit" value=""></input>
						</div>
					</FORM>
					<%-- 確定ボタンが押されている場合 --%>
					<%}}else if(commit_check.equals("end")){ %>
					<FORM method="GET" action="OralExaminationChangeStatus"
						onsubmit="return JUMP_commit('<%=commit_check%>','end')"
						style="display: inline;">

						<div id="button_nocommit">
							<input type="hidden" name="roe_status" value="again"></input> <input
								type="hidden" name="roe_id"
								value=<%=request.getParameter("roe_id")%>></input> <input
								type="submit" value=""></input>
						</div>
					</FORM>
					<% } %>

					<a href="OralExaminationList"><img
						src="images/bana/o_oral_e_list.png" alt="面接試験報告書入力ページへ"></img></a>

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


</body>
</html>
