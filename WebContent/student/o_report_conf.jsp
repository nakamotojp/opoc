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

<%--　セッションの取得  --%>
<% String u_name = (String)session.getAttribute("u_name"); %>
<%
	request.setCharacterEncoding("UTF-8");

	if(request.getParameter("rjm_introduction_year") == null || request.getParameter("rjm_introduction_year") == ""){
		response.sendRedirect("../ReportNewList");
		return;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">

<!-- 報告書詳細確認 -->

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
<script type="text/javascript">

//変更からきているなら変更サーブレットへ値を投げる。
function letter_count(){
<!--
	//前頁のurlを取得
	ref = top.document.referrer;
	//urlの中にJoinMeetingChangeBeforeがあればJoinMeetingChangeへ
	if(ref.match("JoinMeetingChangeBefore") == "JoinMeetingChangeBefore"){
		document.formMain.action = "../JoinMeetingChange";
	}else if(ref.match("JoinMeetingInsert") == "JoinMeetingInsert"){
		//urlの中にo_report.jspがあればJoinMeetingInsertにactionを変える
		document.formMain.action = "../JoinMeetingInsert";
	}
}
//ブラウザバックの制御（fire foxは動きません）
history.forward();
-->
</script>
</head>


<body
	onload="letter_count();MM_preloadImages('../images/menu_over_01.gif','../images/menu_over_02.gif','../images/menu_over_03.gif','../images/menu_over_04.gif','../images/menu_over_05.gif','../images/menu_over_06.gif');">
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
						src="../images/menu_over_03.gif" alt="企業説明会報告書一覧へ" width="120"
						height="50" id="Image3"
						onmouseover="MM_swapImage('Image3','','../images/menu_over_03.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="../WrittenExaminationList"><img
						src="../images/menu_04.gif" alt="筆記試験報告書一覧へ" width="120"
						height="50" id="Image4"
						onmouseover="MM_swapImage('Image4','','../images/menu_over_04.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="../OralExaminationList"><img
						src="../images/menu_05.gif" alt="面接試験報告書一覧へ" width="120"
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

					<h2>企業説明会報告書入力（確認）</h2>
					<!-- 文字コードをUTF-8にすることでGetしたときに文字化けしなくなる -->
					<% request.setCharacterEncoding("UTF-8"); %>

					<table id="table-02">
						<tbody>
							<tr>
								<th>提出日</th>
								<td>
									<!-- 提出日をもらってくる --> <%=request.getParameter("rjm_introduction_year")%>
									年 <%=request.getParameter("rjm_introduction_month")%> 月 <%=request.getParameter("rjm_introduction_day")%>
									日
								</td>

							</tr>
							<tr>
								<th>参加日</th>
								<td><%=request.getParameter("rjm_participate_year")%> 年 <%=request.getParameter("rjm_participate_month")%>
									月 <%=request.getParameter("rjm_participate_day")%> 日 <!-- 参加日をもらってくる  -->
								</td>
							</tr>
							<tr>
								<th>企業説明会名</th>
								<td><%=xss.escape(request.getParameter("rjm_name")) %> <!-- 企業説明会名をもらってくる -->
								</td>
							</tr>
							<tr>
								<th>企業名</th>
								<td><%=xss.escape(request.getParameter("comp_name")) %></td>
							</tr>
							<tr>
								<th>担当者名</th>
								<td><%=xss.escape(request.getParameter("rjm_owner")) %></td>
							</tr>
							<tr>
								<th>説明内容</th>
								<td><%=transferCRLT(xss.escape(request.getParameter("rjm_content"))) %>
								</td>
							</tr>

							<% String q = ""; 
     for(int i = 0;; i++){
    	 //質問項目が作られていなければ表示させない
    	 if((q = request.getParameter("rjmr_rquestion" + i)) == null){
    		 break;
    	 }
    	//質問内容の中身を記入されていなければ、表示させない
    	 if(q.equals("")){
    		 break;
    	 }
    	 %>
							<tr>
								<th>質問された<br /> 内 容
								</th>
								<td><%=transferCRLT(xss.escape(request.getParameter("rjmr_rquestion" + i))) %>
								</td>
							</tr>
							<tr>
								<th>回答</th>
								<td><%=transferCRLT(xss.escape(request.getParameter("rjmr_answer" + i))) %>
								</td>
							</tr>
							<% }%>
							<%
	for(int i = 0;; i++){
		//質問項目が作られていなければ表示させない
    	 if((q = request.getParameter("rjmq_question" + i)) == null){
    		 break;
    	 }
    	//質問内容の中身を記入されていなければ、表示させない
    	 if(q.equals("")){
    		 break;
    	 }
    	 %>
							<tr>
								<th>質問した<br /> 内 容
								</th>
								<td><%=transferCRLT(xss.escape(request.getParameter("rjmq_question"+ i))) %>
								</td>
							</tr>
							<tr>
								<th>回答</th>
								<td><%=transferCRLT(xss.escape(request.getParameter("rjmq_answer" + i))) %>
								</td>
							</tr>
							<%} %>

							<tr>
								<th>次回</th>
								<td><%=
     	  request.getParameter("rjm_nexttime") %> <%if(request.getParameter("rjm_nexttime").equals("会社別訪問（個別セミナー）予定")
     	 		|| request.getParameter("rjm_nexttime").equals("受験予定"))
     	 { 
     		 out.print(request.getParameter("rjm_nexttime_year")); %> 年 <% out.print(request.getParameter("rjm_nexttime_month")); %>
									月 <% out.print(request.getParameter("rjm_nexttime_day")); %> 日 <% } %>
								</td>
							</tr>
							<tr>
								<th>その他特記事項</th>
								<td><%=transferCRLT(xss.escape(request.getParameter("rjm_particular"))) %>
								</td>
							</tr>

							<tr>
								<th>所感</th>
								<td><%=transferCRLT(xss.escape(request.getParameter("rjm_impression"))) %>
								</td>
							</tr>
						</tbody>
					</table>
					<div id="button_change">
						<input type=button value="" onclick="history.back()" />
					</div>
					<form name="formMain" action="../JoinMeetingInsert" method="post">

						<!--  hidden -->
						<input type="hidden" name="rjm_id"
							value="<%=request.getParameter("rjm_id")%>"></input> <input
							type="hidden" name="comp_id"
							value="<%=request.getParameter("comp_id")%>"></input> <input
							type="hidden" name="comp_name"
							value="<%=request.getParameter("comp_name")%>"></input> <input
							type="hidden" name="rjm_introduction_year"
							value="<%=request.getParameter("rjm_introduction_year")%>"></input>
						<input type="hidden" name="rjm_introduction_month"
							value="<%=request.getParameter("rjm_introduction_month")%>"></input>
						<input type="hidden" name="rjm_introduction_day"
							value="<%=request.getParameter("rjm_introduction_day")%>"></input>
						<input type="hidden" name="rjm_participate_year"
							value="<%=request.getParameter("rjm_participate_year")%>"></input>
						<input type="hidden" name="rjm_participate_month"
							value="<%=request.getParameter("rjm_participate_month")%>"></input>
						<input type="hidden" name="rjm_participate_day"
							value="<%=request.getParameter("rjm_participate_day")%>"></input>
						<input type="hidden" name="rjm_name"
							value="<%=xss.escape(request.getParameter("rjm_name"))%>"></input>
						<input type="hidden" name="rjm_owner"
							value="<%=xss.escape(request.getParameter("rjm_owner"))%>"></input>
						<input type="hidden" name="rjm_content"
							value="<%=deletebr(xss.escape(request.getParameter("rjm_content")))%>"></input>
						<!-- 質問された-->
						<% String q2 = ""; 
   for(int i = 0;; i++){  	 
	 //質問項目が作られていなければ表示させない
    	 if((q2 = request.getParameter("rjmr_rquestion" + i)) == null){
    		 break;
         }
    	//質問内容の中身を記入されていなければ、表示させない
    	 if(q2.equals("")){
    		 break;
    	 }
%>
						<input type="hidden" name="rjmr_rquestion<%=i%>"
							value="<%=deletebr(xss.escape(request.getParameter("rjmr_rquestion" + i)))%>"></input>
						<input type="hidden" name="rjmr_answer<%=i%>"
							value="<%=deletebr(xss.escape(request.getParameter("rjmr_answer" + i)))%>"></input>
						<% } %>

						<!-- 質問した-->
						<% q2 = ""; 
   for(int i = 0;; i++){  	 
    	 if((q2 = request.getParameter("rjmq_question" + i)) == null){
    		 break;
         }
    	 if(q2.equals("")){
    		 break;
    	 }
%>
						<input type="hidden" name="rjmq_question<%=i%>"
							value="<%=deletebr(xss.escape(request.getParameter("rjmq_question" + i)))%>"></input>
						<input type="hidden" name="rjmq_answer<%=i%>"
							value="<%=deletebr(xss.escape(request.getParameter("rjmq_answer" + i)))%>"></input>
						<% } %>


						<input type="hidden" name="rjm_rquestion"
							value=<%=xss.escape(request.getParameter("rjm_rquestion"))%>></input>
						<input type="hidden" name="rjm_answer"
							value="<%=xss.escape(request.getParameter("rjm_answer"))%>"></input>
						<input type="hidden" name="rjm_question"
							value="<%=xss.escape(request.getParameter("rjm_question"))%>"></input>
						<input type="hidden" name="rjm_answer2"
							value="<%=xss.escape(request.getParameter("rjm_answer2"))%>"></input>
						<input type="hidden" name="rjm_particular"
							value="<%=deletebr(xss.escape(request.getParameter("rjm_particular")))%>"></input>
						<input type="hidden" name="rjm_nexttime"
							value="<%=request.getParameter("rjm_nexttime")%>"></input> <input
							type="hidden" name="rjm_nexttime_year"
							value="<%=request.getParameter("rjm_nexttime_year")%>"></input> <input
							type="hidden" name="rjm_nexttime_month"
							value="<%=request.getParameter("rjm_nexttime_month")%>"></input> <input
							type="hidden" name="rjm_nexttime_day"
							value="<%=request.getParameter("rjm_nexttime_day")%>"></input> <input
							type="hidden" name="rjm_impression"
							value="<%=deletebr(xss.escape(request.getParameter("rjm_impression")))%>"></input>

						<div id="button_reg">
							<input type="submit" value="" class="button" />
						</div>
					</form>
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
