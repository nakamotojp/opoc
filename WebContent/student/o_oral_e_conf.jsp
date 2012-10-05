<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.bemax.se.graduation2011.auth.Xss"%>

<%!

private String transferCRLT(String value){
	if(value == null){
		return "";
	}
	String result = value.replace("\r\n","<br/>");
	result = result.replace("\r","<br/>");
	result = result.replace("\n","<br/>");
	return result;
}

private String deletebr(String item){
	String result = "";
	 try{
	result = item.replace("\r\n","【br】");
	result = result.replace("\r","【br】");
	result = result.replace("\n","【br】");
	}catch(NullPointerException e){
	}
	return result;
}
	Xss xss = new Xss();

%>

<%
	request.setCharacterEncoding("UTF-8");

	if(request.getParameter("roe_introduction_year") == null || request.getParameter("roe_introduction_year") == ""){
		response.sendRedirect("../ReportNewList");
		return;
	}
%>


<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">

<!-- 面接試験報告書入力確認 -->

<head>
<!--　ファビコン -->
<link rel="shortcut icon" type="image/x-icon" href="../images/icon.ico" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta name="description" content="専門学校ビーマックス就職活動支援ページ" />
<meta name="keywords" content="Opoc,ビーマックス,報告書提出,就職活動,報告書検索" />

<title>Opoc</title>
<link href="../style2.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../script.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>

<script type="text/javascript" src="../js/formToWizard.js"></script>

<script type="text/javascript">
<!--
//変更からきているなら変更サーブレットへ値を投げる。
function letter_count(){
	//前頁のurlを取得
	ref = top.document.referrer;
	if(ref.match("OralExaminationChangeBefore")){
		//urlにOralExaminationChangeBeforeが含まれる場合actionをOralExaminationChangeBeforeにactionを変える
		document.formMain.action = "../OralExaminationChange";
	}else if(ref.match("OralExaminationInsert")== "OralExaminationInsert"){
			document.formMain.action = "../OralExaminationInsert";
		}		
}
//ブラウザバックの制御（fire foxは動きません）
history.forward();
-->
</script>
</head>


<body
	onload="letter_count();MM_preloadImages('../images/menu_over_01.gif','../images/menu_over_02.gif','../images/menu_over_03.gif','../images/menu_over_04.gif','../images/menu_over_05.gif');">
		<img class="bg" src="../images/bg-l.gif" alt=""/>

	<div id="container">


		<div id="header">
			<h1>
				<a href="../ChangePassword">パスワード変更</a> <a
					onclick="return confirm('ログアウトしますか？')" href="../Logout"　>ログアウト</a>
			</h1>

			<a href="../ReportNewList"><img src="../images/logo.png"
				name="logo" id="logo" /></a>

			<ul id="menu">
				<li><a href="../ReportNewList"><img
						src="../images/menu_01.gif" alt="トップページへ" width="120" height="50"
						id="Image1"
						onmouseover="MM_swapImage('Image1','','../images/menu_over_01.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="../ReportMyList"><img
						src="../images/menu_02.gif" alt="My報告書一覧へ" width="120" height="50"
						id="Image2"
						onmouseover="MM_swapImage('Image2','','../images/menu_over_02.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="../JoinMeetingList"><img
						src="../images/menu_03.gif" alt="企業説明会報告書一覧へ" width="120"
						height="50" id="Image3"
						onmouseover="MM_swapImage('Image3','','../images/menu_over_03.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="../WrittenExaminationList"><img
						src="../images/menu_04.gif" alt="筆記試験報告書一覧へ" width="120"
						height="50" id="Image4"
						onmouseover="MM_swapImage('Image4','','../images/menu_over_04.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="../OralExaminationList"><img
						src="../images/menu_over_05.gif" alt="面接試験報告書一覧へ" width="120"
						height="50" id="Image5"
						onmouseover="MM_swapImage('Image5','','../images/menu_over_05.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="../SearchByCompany"><img
						src="../images/menu_06.gif" alt="検索ページへ" width="120" height="50"
						id="Image6"
						onmouseover="MM_swapImage('Image6','','../images/menu_over_06.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
			</ul>
		</div>
		<!--/header-->


		<div id="contents">


			<div id="wrap">


				<div id="main">

					<!--この行で見出し表示 -->

					<h2>面接試験報告書入力（確認）</h2>
					<!-- 文字コードをUTF-8にすることでGetしたときに文字化けしなくなる -->
					<% request.setCharacterEncoding("UTF-8"); %>
					<table id="table-02">
						<tr>
							<th>提出日</th>
							<td><%=request.getParameter("roe_introduction_year") %>年 <%=request.getParameter("roe_introduction_month") %>月
								<%=request.getParameter("roe_introduction_day") %>日</td>
						<tr>
							<th>参加日</th>
							<td><%=request.getParameter("roe_participate_year") %>年 <%=request.getParameter("roe_participate_month") %>月
								<%=request.getParameter("roe_participate_day") %>日</td>
						</tr>
						<tr>
							<th>受験した企業名</th>
							<td><%=xss.escape(request.getParameter("comp_name")) %></td>
						</tr>

						<tr>
							<th>何次試験</th>
							<td><%=xss.escape(request.getParameter("roe_stage")) %>試験</td>
						</tr>
						<tr>
							<th>面接官人数</th>
							<td><%=request.getParameter("roe_interviewer") %>人</td>
						</tr>

						<tr>
							<th>当校受験人数</th>
							<td><%=request.getParameter("roe_exam") %>人</td>
						</tr>

						<tr>
							<th>他校受験人数</th>
							<td><%=request.getParameter("roe_examother") %>人</td>
						</tr>

						<tr>
							<th>他校受験者出身校</th>
							<td><%=xss.escape(request.getParameter("roe_examotherschool")) %>
							</td>
						</tr>

						<tr>
							<th>面接担当者名または役職</th>
							<td><%=transferCRLT(xss.escape(request.getParameter("roe_nameortitle"))) %>
							</td>
						</tr>
						<tr>
							<th>グループディスカッション</th>
							<td>
								<% String flg = request.getParameter("roe_discussion"); 
      if(flg.equals("no")){
    	  out.print("無し");
    
      }else if(flg.equals("yes")){
    	 out.print("有り");
	%>
							</td>
						</tr>
						<tr>
							<th>人数</th>
							<td><%=request.getParameter("roe_discussionnum") %>人</td>
						</tr>
						<tr>
							<th>時間</th>
							<td><%=request.getParameter("roe_discussiontime") %>分</td>
						</tr>
						<tr>
							<th>テーマ</th>
							<td><%=transferCRLT(xss.escape(request.getParameter("roe_discussiontheme"))) %>
							</td>
						</tr>
						<%} %>
						<% String q = ""; 
     for(int i = 0;; i++){
    	 //質問項目が作られていなければ表示させない
    	 if((q = request.getParameter("roeq_question" + i)) == null){
    		 break;
    	 }
    	//質問内容の中身を記入されていなければ、表示させない
    	 if(q.equals("")){
    		 break;
    	 }
    	 %>
						<tr>
							<th>質問</th>
							<td>
								<!--　　　　　　　　　　　　　　　　 ここを変更　　　　　　　　　　　　　　　 --> <%=transferCRLT(xss.escape(request.getParameter("roeq_question" + i))) %>
						</tr>
						<tr>
							<th>回答</th>
							<td>
								<!--　　　　　　　　　　　　　　　　 ここを変更　　　　　　　　　　　　　　　 --> <%=transferCRLT(xss.escape(request.getParameter("roeq_answer" + i))) %>
							</td>
						</tr>
						<% } %>

						<tr>
							<th>合否連絡</th>
							<td><%=request.getParameter("roe_accept_year") %>年 <%=request.getParameter("roe_accept_month") %>月
								<%=request.getParameter("roe_accept_day") %>日</td>
						</tr>
						<tr>
							<th>次回受験</th>
							<td>
								<%
      if(request.getParameter("roe_nexttime_check").equals("有る")){
	  %> <%=request.getParameter("roe_nexttime_year") %>年 <%=request.getParameter("roe_nexttime_month") %>月
								<%=request.getParameter("roe_nexttime_day") %>日

							</td>
						</tr>
						<tr>
							<th>次回試験内容</th>
							<td><%=transferCRLT(xss.escape(request.getParameter("roe_nextexam"))) %>
							</td>
						</tr>
						<tr>
							<th>準備知識・情報</th>
							<td><%=transferCRLT(xss.escape(request.getParameter("roe_preparation"))) %>
							</td>
						</tr>

						<% }else if(request.getParameter("roe_nexttime_check").equals("有るが未定")){ %>
					    	  有るが未定
							</td>
						</tr>
						<tr>
							<th>次回試験内容</th>
							<td><%=transferCRLT(xss.escape(request.getParameter("roe_nextexam"))) %>
							</td>
						</tr>
						<tr>
							<th>準備知識・情報</th>
							<td><%=transferCRLT(xss.escape(request.getParameter("roe_preparation"))) %>
							</td>
						</tr>
					    <% }else if(request.getParameter("roe_nexttime_check").equals("無し")){
    	  out.print("無し");
      }
      %>
					</table>
					<div id="button_change">
						<input type="submit" value="" onClick="history.back()" " />
					</div>
					<form name="formMain" action="../OralExaminationInsert"
						method="post">

						<!--  hidden -->

						<input type="hidden" name="roe_id"
							value=<%=request.getParameter("roe_id")%>></input> <input
							type="hidden" name="comp_id"
							value="<%=request.getParameter("comp_id")%>" /> <input
							type="hidden" name="compt_position"
							value="<%=request.getParameter("compt_position")%>" /> <input
							type="hidden" name="comp_name"
							value="<%=request.getParameter("comp_name")%>" /> <input
							type="hidden" name="u_subject"
							value=<%=request.getParameter("u_subject")%>></input> <input
							type="hidden" name="u_course"
							value=<%=request.getParameter("u_course")%>></input> <input
							type="hidden" name="u_id" value=<%=request.getParameter("u_id")%>></input>
						<input type="hidden" name="u_name"
							value=<%=request.getParameter("u_name")%>></input> <input
							type="hidden" name="roe_introduction_year"
							value=<%=request.getParameter("roe_introduction_year")%>></input>
						<input type="hidden" name="roe_introduction_month"
							value=<%=request.getParameter("roe_introduction_month")%>></input>
						<input type="hidden" name="roe_introduction_day"
							value=<%=request.getParameter("roe_introduction_day")%>></input>
						<input type="hidden" name="roe_participate_year"
							value=<%=request.getParameter("roe_participate_year")%>></input>
						<input type="hidden" name="roe_participate_month"
							value=<%=request.getParameter("roe_participate_month")%>></input>
						<input type="hidden" name="roe_participate_day"
							value=<%=request.getParameter("roe_participate_day")%>></input> <input
							type="hidden" name="roe_stage"
							value="<%=xss.escape(request.getParameter("roe_stage"))%>"></input>
						<input type="hidden" name="roe_arrivaltime"
							value=<%=request.getParameter("roe_arrivaltime")%>></input> <input
							type="hidden" name="roe_interviewer"
							value=<%=request.getParameter("roe_interviewer")%>></input> <input
							type="hidden" name="roe_examother"
							value=<%=request.getParameter("roe_examother")%>></input> <input
							type="hidden" name="roe_exam"
							value=<%=request.getParameter("roe_exam")%>></input> <input
							type="hidden" name="roe_examotherschool"
							value="<%=xss.escape(request.getParameter("roe_examotherschool"))%>"></input>
						<input type="hidden" name="roe_nameortitle"
							value="<%=deletebr(xss.escape(request.getParameter("roe_nameortitle")))%>"></input>
						<input type="hidden" name="roe_discussion"
							value="<%=xss.escape(request.getParameter("roe_discussion"))%>"></input>
						<input type="hidden" name="roe_discussionnum"
							value=<%=request.getParameter("roe_discussionnum")%>></input> <input
							type="hidden" name="roe_discussiontime"
							value=<%=request.getParameter("roe_discussiontime")%>></input> <input
							type="hidden" name="roe_discussiontheme"
							value="<%=deletebr(xss.escape(request.getParameter("roe_discussiontheme")))%>"></input>
						<!-- 質問-->
						<% String q2 = ""; 
   for(int i = 0;; i++){  	 
    	 if((q2 = request.getParameter("roeq_question" + i)) == null){
    		 break;
         }
    	 if(q2.equals("")){
    		 break;
    	 }
%>
						<input type="hidden" name="roeq_question<%=i%>"
							value="<%=deletebr(xss.escape(request.getParameter("roeq_question" + i)))%>"></input>
						<input type="hidden" name="roeq_answer<%=i%>"
							value="<%=deletebr(xss.escape(request.getParameter("roeq_answer" + i)))%>"></input>
						<% } %>

						<input type="hidden" name="roe_accept_year"
							value=<%=request.getParameter("roe_accept_year")%>></input> <input
							type="hidden" name="roe_accept_month"
							value=<%=request.getParameter("roe_accept_month")%>></input> <input
							type="hidden" name="roe_accept_day"
							value=<%=request.getParameter("roe_accept_day")%>></input> <input
							type="hidden" name="roe_nexttime_check"
							value=<%=request.getParameter("roe_nexttime_check")%>></input>

						<% if(request.getParameter("roe_nexttime_check").equals("有る")){ %>
						<input type="hidden" name="roe_nexttime_year"
							value=<%=request.getParameter("roe_nexttime_year")%>></input> <input
							type="hidden" name="roe_nexttime_month"
							value=<%=request.getParameter("roe_nexttime_month")%>></input> <input
							type="hidden" name="roe_nexttime_day"
							value=<%=request.getParameter("roe_nexttime_day")%>></input> <input
							type="hidden" name="roe_nextexam"
							value="<%=deletebr(xss.escape(request.getParameter("roe_nextexam")))%>"></input>
						<input type="hidden" name="roe_preparation"
							value="<%=deletebr(xss.escape(request.getParameter("roe_preparation")))%>"></input>
						<% }else if(request.getParameter("roe_nexttime_check").equals("有るが未定")){ %>
							<input
							type="hidden" name="roe_nextexam"
							value="<%=deletebr(xss.escape(request.getParameter("roe_nextexam")))%>"></input>
						<input type="hidden" name="roe_preparation"
							value="<%=deletebr(xss.escape(request.getParameter("roe_preparation")))%>"></input>
						<% }%>
						<div id="button_reg">
							<input type="submit" value="" class="button"></Input>
						</div>
					</form>
				</div>


				<!--/main-->

				<div id="sub">
					<!--　各種機能のイメージ  -->
					<ul id="bana-sub">
						<li><a href="../JoinMeetingInsert" class="o_report"></a></li>
						<li><a href="../WrittenExaminationInsert" class="written_e"></a></li>
						<li><a href="../OralExaminationInsert" class="o_oral_e"></a></li>
						<li><a href="../SearchByCompany"
							class="o_jub_hunnting_search"></a></li>
					</ul>
					<div id="cal">
						<script type="text/javascript">
document.write(cal());
</script>
					</div>
					<br />
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
