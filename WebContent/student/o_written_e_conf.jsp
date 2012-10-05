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
	if(item != null){
		result = item.replace("\r\n","【br】");
		result = result.replace("\r","【br】");
		result = result.replace("\n","【br】");
		
	}
	return result;
}
	Xss xss = new Xss();

%>
<%--　セッションの取得  --%>
<% String u_name = (String)session.getAttribute("u_name"); %>
<%
	request.setCharacterEncoding("UTF-8");

	if(request.getParameter("rwe_introduction_year") == null || request.getParameter("rwe_introduction_year") == ""){
		response.sendRedirect("../ReportNewList");
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">

<!-- 合同説明会 -->

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
	//url右はじからo_written_e_change.jspの文字数である１９文字を抜き出す
	if(ref.match("WrittenExaminationChangeBefore")){
		document.formMain.action = "../WrittenExaminationChange";
	}else if(ref.match("WrittenExaminationInsert") == "WrittenExaminationInsert"){
		//もし右はじ１２文字がo_written_e.jspだった場合WrittenExaminationInsertにactionを変える
		document.formMain.action = "../WrittenExaminationInsert";
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
						src="../images/menu_03.gif" alt="企業説明会報告書一覧へ" width="120"
						height="50" id="Image3"
						onmouseover="MM_swapImage('Image3','','../images/menu_over_03.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="../WrittenExaminationList"><img
						src="../images/menu_over_04.gif" alt="筆記試験報告書一覧へ" width="120"
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

					<h2>筆記試験報告書入力（確認）</h2>
					<!-- 文字コードをUTF-8にすることでGetしたときに文字化けしなくなる -->
					<% request.setCharacterEncoding("UTF-8"); %>

					<table id="table-02">
						<tbody>
							<tr>
								<th>提出日</th>
								<td><%=request.getParameter("rwe_introduction_year") %>年 <%=request.getParameter("rwe_introduction_month") %>月
									<%=request.getParameter("rwe_introduction_day") %>日</td>
							</tr>
							<tr>
								<th>参加日</th>
								<td><%=request.getParameter("rwe_participate_year") %>年 <%=request.getParameter("rwe_participate_month") %>月
									<%=request.getParameter("rwe_participate_day") %>日</td>
							</tr>
							<tr>
								<th>受験した企業名</th>
								<td><%=xss.escape(request.getParameter("comp_name")) %></td>
							</tr>
							<tr>
								<th>何次試験</th>
								<td><%=xss.escape(request.getParameter("rwe_stage")) %>試験</td>
							</tr>

							<tr>
								<th>他校受験人数</th>
								<td><%=request.getParameter("rwe_examother") %>人</td>
							</tr>

							<tr>
								<th>当校受験人数</th>
								<td><%=request.getParameter("rwe_exam") %>人</td>
							</tr>

							<tr>
								<th>他校受験者出身校</th>
								<td><%=xss.escape(request.getParameter("rwe_examotherschool")) %></td>
							</tr>

							<tr>
								<th>適性検査内容</th>
								<td><%=transferCRLT(xss.escape(request.getParameter("rwe_aptitude"))) %></td>
							</tr>

							<tr>
								<th>適性検査時間</th>
								<td><%=request.getParameter("rwe_aptitudetime") %>分</td>
							</tr>

							<tr>
								<th>筆記試験時間</th>
								<td><%=request.getParameter("rwe_writtentime") %>分</td>
							</tr>

							<tr>
								<th>作文・論文時間</th>
								<td><%=request.getParameter("rwe_papertime") %>分</td>
							</tr>
							<tr>
								<th>国語問題内容</th>
								<td>
									<% if(request.getParameter("rwe_lang_check").equals("yes")){ %> <%=transferCRLT(xss.escape(request.getParameter("rwe_lang"))) %>
									<% }else if(request.getParameter("rwe_lang_check").equals("no")){
    	  out.print("無し");
      };
    	  %>
								</td>
							</tr>
							<tr>
								<th>数学問題内容</th>
								<td>
									<% if(request.getParameter("rwe_math_check").equals("yes")){ %> <%=transferCRLT(xss.escape(request.getParameter("rwe_math"))) %>
									<% }else if(request.getParameter("rwe_math_check").equals("no")){
    	  out.print("無し");
      };
    	  %>
								</td>
							</tr>

							<tr>
								<th>英語問題内容</th>
								<td>
									<% if(request.getParameter("rwe_english_check").equals("yes")){ %>
									<%=transferCRLT(xss.escape(request.getParameter("rwe_english"))) %>
									<% }else if(request.getParameter("rwe_english_check").equals("no")){
    	  out.print("無し");
      };
    	  %>
								</td>
							</tr>

							<tr>
								<th>理科問題内容</th>
								<td>
									<% if(request.getParameter("rwe_science_check").equals("yes")){ %>
									<%=transferCRLT(xss.escape(request.getParameter("rwe_science"))) %>
									<% }else if(request.getParameter("rwe_science_check").equals("no")){
    	  out.print("無し");
      };
    	  %>
								</td>
							</tr>

							<tr>
								<th>社会問題内容</th>
								<td>
									<% if(request.getParameter("rwe_society_check").equals("yes")){ %>
									<%=transferCRLT(xss.escape(request.getParameter("rwe_society"))) %>
									<% }else if(request.getParameter("rwe_society_check").equals("no")){
    	  out.print("無し");
      };
    	  %>
								</td>
							</tr>

							<tr>
								<th>政治・経済問題内容</th>
								<td>
									<% if(request.getParameter("rwe_politics_check").equals("yes")){ %>
									<%=transferCRLT(xss.escape(request.getParameter("rwe_politics"))) %>
									<% }else if(request.getParameter("rwe_politics_check").equals("no")){
    	  out.print("無し");
      };
    	  %>
								</td>
							</tr>

							<tr>
								<th>専門的問題内容</th>
								<td>
									<% if(request.getParameter("rwe_expert_check").equals("yes")){ %>
									<%=transferCRLT(xss.escape(request.getParameter("rwe_expert"))) %>
									<% }else if(request.getParameter("rwe_expert_check").equals("no")){
    	  out.print("無し");
      };
    	  %>
								</td>
							</tr>
							<tr>
								<th>作文・論文内容</th>
								<td>
									<% if(request.getParameter("rwe_paper_check").equals("yes")){ %>
									<%=transferCRLT(xss.escape(request.getParameter("rwe_paper"))) %>
									<% }else if(request.getParameter("rwe_paper_check").equals("no")){
    	  out.print("無し");
      };
    	  %>
								
							</tr>
							<tr>
								<th>その他問題内容</th>
								<td><%=transferCRLT(xss.escape(request.getParameter("rwe_other"))) %></td>
							</tr>
							<tr>
								<th>合否連絡</th>
								<td><%=request.getParameter("rwe_accept_year") %>年 <%=request.getParameter("rwe_accept_month") %>月
									<%=request.getParameter("rwe_accept_day") %>日</td>
							</tr>
							<tr>
								<th>次回受験</th>
								<td>
									<%
      if(request.getParameter("rwe_nexttime_check").equals("有る")){
	  %> <%=request.getParameter("rwe_nexttime_year") %>年 <%=request.getParameter("rwe_nexttime_month") %>月
									<%=request.getParameter("rwe_nexttime_day") %>日
								</td>
							</tr>
							<tr>
								<th>次回試験内容</th>

								<td><%=transferCRLT(xss.escape(request.getParameter("rwe_nextexam"))) %>
								</td>
							</tr>
							<tr>
								<th>準備知識・情報</th>
								<td><%=transferCRLT(xss.escape(request.getParameter("rwe_preparation"))) %></td>
							</tr>
							<% }else if(request.getParameter("rwe_nexttime_check").equals("有るが未定")){ %>
							有るが未定
							</td>
							</tr>
							<tr>
								<th>次回試験内容</th>

								<td><%=transferCRLT(xss.escape(request.getParameter("rwe_nextexam"))) %>
								</td>
							</tr>
							<tr>
								<th>準備知識・情報</th>
								<td><%=transferCRLT(xss.escape(request.getParameter("rwe_preparation"))) %></td>
							</tr>
						    <% }else if(request.getParameter("rwe_nexttime_check").equals("無し")){
    	  out.print("無し");
      }
      %>

						</tbody>
					</table>
					</fieldset>
					<div id="button_change">
						<input type=button value="" onClick="history.back()"></Input>
					</div>
					<form name="formMain" action="../WrittenExaminationInsert"
						method="post">

						<!--  hidden -->

						<input type="hidden" name="rwe_id"
							value=<%=request.getParameter("rwe_id")%>></input> <INPUT
							TYPE="hidden" NAME="comp_id"
							VALUE="<%=request.getParameter("comp_id")%>" /> <INPUT
							TYPE="hidden" NAME="compt_position"
							VALUE="<%=request.getParameter("compt_position")%>" /> <INPUT
							TYPE="hidden" NAME="comp_name"
							VALUE="<%=request.getParameter("comp_name")%>" /> <input
							type="hidden" name="rwe_introduction_year"
							value=<%=request.getParameter("rwe_introduction_year")%>></input>
						<input type="hidden" name="rwe_introduction_month"
							value=<%=request.getParameter("rwe_introduction_month")%>></input>
						<input type="hidden" name="rwe_introduction_day"
							value=<%=request.getParameter("rwe_introduction_day")%>></input>
						<input type="hidden" name="rwe_participate_year"
							value=<%=request.getParameter("rwe_participate_year")%>></input>
						<input type="hidden" name="rwe_participate_month"
							value=<%=request.getParameter("rwe_participate_month")%>></input>
						<input type="hidden" name="rwe_participate_day"
							value=<%=request.getParameter("rwe_participate_day")%>></input> <input
							type="hidden" name="rwe_stage"
							value="<%=xss.escape(request.getParameter("rwe_stage"))%>"></input> <input
							type="hidden" name="rwe_examother"
							value=<%=request.getParameter("rwe_examother")%>></input> <input
							type="hidden" name="rwe_exam"
							value=<%=request.getParameter("rwe_exam")%>></input> <input
							type="hidden" name="rwe_examotherschool"
							value="<%=xss.escape(request.getParameter("rwe_examotherschool"))%>"></input>
						<input type="hidden" name="rwe_aptitude"
							value="<%=deletebr(xss.escape(request.getParameter("rwe_aptitude")))%>"></input>
						<input type="hidden" name="rwe_aptitudetime"
							value=<%=request.getParameter("rwe_aptitudetime")%>></input> <input
							type="hidden" name="rwe_writtentime"
							value=<%=request.getParameter("rwe_writtentime")%>></input> <input
							type="hidden" name="rwe_papertime"
							value=<%=request.getParameter("rwe_papertime")%>></input> <input
							type="hidden" name="rwe_lang_check"
							value=<%=request.getParameter("rwe_lang_check")%>></input> <input
							type="hidden" name="rwe_lang"
							value="<%=deletebr(xss.escape(request.getParameter("rwe_lang")))%>"></input>
						<input type="hidden" name="rwe_math_check"
							value=<%=request.getParameter("rwe_math_check")%>></input> <input
							type="hidden" name="rwe_math"
							value="<%=deletebr(xss.escape(request.getParameter("rwe_math")))%>"></input>
						<input type="hidden" name="rwe_english_check"
							value=<%=request.getParameter("rwe_english_check")%>></input> <input
							type="hidden" name="rwe_english"
							value="<%=deletebr(xss.escape(request.getParameter("rwe_english")))%>"></input>
						<input type="hidden" name="rwe_science_check"
							value=<%=request.getParameter("rwe_science_check")%>></input> <input
							type="hidden" name="rwe_science"
							value="<%=deletebr(xss.escape(request.getParameter("rwe_science")))%>"></input>
						<input type="hidden" name="rwe_society_check"
							value=<%=request.getParameter("rwe_society_check")%>></input> <input
							type="hidden" name="rwe_society"
							value="<%=deletebr(xss.escape(request.getParameter("rwe_society")))%>"></input>
						<input type="hidden" name="rwe_politics_check"
							value=<%=request.getParameter("rwe_politics_check")%>></input> <input
							type="hidden" name="rwe_politics"
							value="<%=deletebr(xss.escape(request.getParameter("rwe_politics")))%>"></input>
						<input type="hidden" name="rwe_expert_check"
							value=<%=request.getParameter("rwe_expert_check")%>></input> <input
							type="hidden" name="rwe_expert"
							value="<%=deletebr(xss.escape(request.getParameter("rwe_expert")))%>"></input>
						<input type="hidden" name="rwe_paper_chaek"
							value=<%=request.getParameter("rwe_paper_check")%>></input> <input
							type="hidden" name="rwe_paper"
							value="<%=deletebr(xss.escape(request.getParameter("rwe_paper")))%>"></input>
						<input type="hidden" name="rwe_other"
							value="<%=deletebr(xss.escape(request.getParameter("rwe_other")))%>"></input>
						<input type="hidden" name="rwe_accept_year"
							value="<%=request.getParameter("rwe_accept_year")%>"></input> <input
							type="hidden" name="rwe_accept_month"
							value=<%=request.getParameter("rwe_accept_month")%>></input> <input
							type="hidden" name="rwe_accept_day"
							value=<%=request.getParameter("rwe_accept_day")%>></input> <input
							type="hidden" name="rwe_nexttime_check"
							value=<%=request.getParameter("rwe_nexttime_check")%>></input>
						<% if(request.getParameter("rwe_nexttime_check").equals("有る")){ %>
						<input type="hidden" name="rwe_nexttime_year"
							value=<%=request.getParameter("rwe_nexttime_year")%>></input> <input
							type="hidden" name="rwe_nexttime_month"
							value=<%=request.getParameter("rwe_nexttime_month")%>></input> <input
							type="hidden" name="rwe_nexttime_day"
							value=<%=request.getParameter("rwe_nexttime_day")%>></input>
						<% } %>

						<input type="hidden" name="rwe_nextexam"
							value="<%=deletebr(xss.escape(request.getParameter("rwe_nextexam")))%>"></input>
						<input type="hidden" name="rwe_preparation"
							value="<%=deletebr(xss.escape(request.getParameter("rwe_preparation")))%>"></input>

						<div id="button_reg">
							<input type="submit" value="" class="button"></Input>
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
