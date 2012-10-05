<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="jp.bemax.se.graduation2011.auth.JoinMeetingDetailed"%>
<%@page
	import="jp.bemax.se.graduation2011.model.BeansReportJoinMeetingReply"%>
<%@page
	import="jp.bemax.se.graduation2011.model.BeansReportJoinMeetingQuestion"%>
	<%@page import="jp.bemax.se.graduation2011.auth.Xss"%>
<jsp:useBean id="list" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="list2" class="java.util.ArrayList" scope="request"></jsp:useBean>

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

<!-- 企業説明会試験報告書詳細 -->

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
<link href="css/style_print.css" rel="stylesheet" type="text/css"
	media="print" />
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<script type="text/javascript" src="js/formToWizard.js"></script>
<script type="text/javascript" src="js/tableplus.js"></script>
<script type="text/JavaScript">
<!--
	function JUMP(item){
		  if (confirm("印刷しますか？")==true){
		    //OKなら印刷を出して印刷ページを開き、小窓を削除する。
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
	-->
</script>
</head>


<body
	onload="MM_preloadImages('images/menu_over_01.gif','images/menu_over_02.gif','images/menu_over_03.gif','images/menu_over_04.gif','images/menu_over_05.gif','images/menu_over_06_a.gif')">

	<div id="print">
		<img src="images/logo2.png" name="logo" id="logo" />
		<!--この行で見出し表示 -->
		<h2><%=xss.escape((String)request.getAttribute("comp_name")) %><br />企業説明会報告書
		</h2>
		<!-- 文字コードをUTF-8にすることでGetしたときに文字化けしなくなる -->
		<% request.setCharacterEncoding("UTF-8"); %>
		<table id="table-print">

			<tr>
				<th class="hiduke">提出日</th>
				<td class="ren-o">
					<% String s = (String)request.getAttribute("rjm_introduction");
	String ary[] = s.split("-");
	out.print(ary[0] + "年");
	out.print(ary[1] + "月");
	out.print(ary[2] + "日");
	%>
				</td>
				<th class="ren2">参加日</th>
				<td class="ren-o" colspan="3">
					<% s = (String)request.getAttribute("rjm_participate") ;
	ary = s.split("-");
	out.print(ary[0] + "年");
	out.print(ary[1] + "月");
	out.print(ary[2] + "日");
	%>
				</td>
				<!-- 参加日をもらってくる  -->
			</tr>
			<tr>
				<th>企業説明会名</th>
				<td colspan="5"><%=xss.escape((String)request.getAttribute("rjm_name")) %> <!-- 合同説明会名をもらってくる -->
				</td>
			</tr>
			<tr>
				<th>企業名</th>
				<td colspan="5"><%=xss.escape((String)request.getAttribute("comp_name")) %></td>
			</tr>
			<tr>
				<th>担当者名</th>
				<td colspan="5"><%=request.getAttribute("rjm_owner") %></td>
			</tr>
			<tr>
				<th>郵便番号</th>
				<td>〒<%=request.getAttribute("comp_zip") %></td>
				<th class="ren4">電話番号</th>
				<td class="ren5" colspan="3"><%=request.getAttribute("comp_phone") %>
				</td>
			</tr>
			<tr>
				<th>住所</th>
				<td colspan="5"><%=request.getAttribute("comp_address") %></td>
			</tr>
			<tr>
				<th>説明内容</th>
				<td colspan="5"><%=transferCRLT((String)request.getAttribute("rjm_content")) %>
				</td>
			</tr>
			<% for(Object item : list){
    		// ここかえてね！！
        	BeansReportJoinMeetingReply info = (BeansReportJoinMeetingReply)item;
      		
    	      %>
			<tr>
				<th>質問された<br /> 内 容
				</th>
				<td><%= transferCRLT(info.getRjmr_question()) %></td>
				<th>回答</th>
				<td colspan="3"><%= transferCRLT(info.getRjmr_answer()) %></td>
			</tr>
			<%} %>
			<!-- ここ変更 -->
			<% for(Object item2 : list2){
        	 BeansReportJoinMeetingQuestion info = (BeansReportJoinMeetingQuestion)item2;
      		
    	      %>
			<tr>
				<th>質問した<br /> 内 容
				</th>
				<td><%= transferCRLT(info.getRjmq_question()) %></td>
				<th>回答</th>
				<td colspan="5"><%= transferCRLT(info.getRjmq_answer()) %></td>
			</tr>
			<%} %>
			<tr>
				<th>その他<br /> 特記事項
				</th>
				<td colspan="5"><%=transferCRLT((String)request.getAttribute("rjm_particular")) %>
				</td>
			</tr>
			<tr>
				<th>次回</th>
				<td colspan="5">
					<% s = (String)request.getAttribute("rjm_nexttime") ;
   		ary = s.split("-");
	out.print(ary[0]);
	if(ary.length >= 2){
	out.print(ary[1] + "年");
	out.print(ary[2] + "月");
	out.print(ary[3] + "日");
	}
	%>
				</td>
			</tr>
			<tr>
				<th>所感</th>
				<td colspan="5"><%=transferCRLT((String)request.getAttribute("rjm_impression")) %>
				</td>
			</tr>
		</table>
		<div id="button_print">
			<input type="button" value="" onclick="window.print();"></input>
		</div>
	</div>


</body>
</html>
