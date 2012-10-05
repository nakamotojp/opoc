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
<link href="css/style_print.css" rel="stylesheet" type="text/css"
	media="print" />
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<script type="text/javascript" src="js/formToWizard.js"></script>
<script type="text/javascript" src="js/tableplus.js"></script>
<script type="text/JavaScript">
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
</script>
</head>


<body
	onload="MM_preloadImages('images/menu_over_01.gif','images/menu_over_02.gif','images/menu_over_03.gif','images/menu_over_04.gif','images/menu_over_05.gif','images/menu_over_06_a.gif')">

	<div id="print">
		<img src="images/logo2.png" name="logo" id="logo" />
		<!--この行で見出し表示 -->
		<h2><%=xss.escape((String)request.getAttribute("comp_name")) %><br />面接試験報告書
		</h2>
		<!-- 文字コードをUTF-8にすることでGetしたときに文字化けしなくなる -->
		<% request.setCharacterEncoding("UTF-8"); %>


		<table id="table-print">
			<tr>
				<th class="hiduke">提出日</th>
				<td class="ren1">
					<% String s = (String)request.getAttribute("roe_introduction");
	String ary[] = s.split("-");
	out.print(ary[0] + "年");
	out.print(ary[1] + "月");
	out.print(ary[2] + "日");
	%>
				</td>
				<th class="ren2">参加日</th>
				<td class="ren5">
					<% s = (String)request.getAttribute("roe_participate") ;
	ary = s.split("-");
	out.print(ary[0] + "年");
	out.print(ary[1] + "月");
	out.print(ary[2] + "日");
	%>
				</td>
				<th class="ren2">何次試験</th>
				<td class="ren5"><%=request.getAttribute("roe_stage") %>試験</td>
			</tr>
			<tr>
				<th>受験した企業名</th>
				<td colspan="5"><%=xss.escape((String)request.getAttribute("comp_name")) %></td>
			</tr>
			<tr>
				<th>郵便番号</th>
				<td>〒<%=request.getAttribute("comp_zip") %>
				</td>
				<th>電話番号</th>
				<td colspan="3"><%=request.getAttribute("comp_phone") %></td>
			</tr>
			<tr>
				<th>住所</th>
				<td colspan="6"><%=request.getAttribute("comp_address") %>
			</tr>
			<tr>
				<th>面接官人数</th>
				<td><%=request.getAttribute("roe_interviewer") %>人</td>
				<th>当校受験人数</th>
				<td><%=request.getAttribute("roe_exam") %>人</td>
				<th>他校受験人数</th>
				<td><%=request.getAttribute("roe_examother") %>人</td>
			</tr>


			<tr>
				<th>他校受験者出身校</th>
				<td colspan="6"><%=request.getAttribute("roe_examotherschool") %>
				</td>
			</tr>

			<tr>
				<th>面接担当者名<br />または役職
				</th>
				<td colspan="6"><%=transferCRLT((String)request.getAttribute("roe_nameortitle")) %>
				</td>
			</tr>

			<tr>
				<th>グループ<br />ディスカッション
				</th>

				<% String flg = (String)request.getAttribute("roe_discussion"); 
      if(flg.equals("no")){
    	  out.print("<td colspan='6'>無し</td>"); 
      }
     if(flg.equals("yes")){
    	 out.print("<td>有り</td>"); 
	%>
				<th>人数</th>
				<td><%=request.getAttribute("roe_discussionnum") %>人
				<th>時間</th>
				<td><%=request.getAttribute("roe_discussiontime") %>分</td>
			</tr>
			<tr>
				<th>テーマ</th>
				<td colspan="6"><%=transferCRLT((String)request.getAttribute("roe_discussiontheme")) %>
				</td>
			</tr>
			<%} %>
			<% for(Object item : list){
    	  BeansReportOralExaminationQuestion info = (BeansReportOralExaminationQuestion)item;
      		
    	      %>

			<tr>
				<th>質問された内容</th>
				<td colspan="2"><%= transferCRLT(info.getRoeq_question()) %>
				<th>回答</th>
				<td colspan="2"><%= transferCRLT(info.getRoeq_answer()) %></td>
			</tr>
			<% } %>
			<tr>
				<th>合否連絡</th>
				<td colspan="5">
					<% s = (String)request.getAttribute("roe_accept");
   	ary = s.split("-");
	out.print(ary[0] + "年");
	out.print(ary[1] + "月");
	out.print(ary[2] + "日");

   	%>
				</td>
				<tr>
				<th>次回受験</th>
				<td colspan="5">
				<%if(request.getAttribute("roe_nexttime") != null && request.getAttribute("roe_nexttime").equals("無し")){
      out.print("無し");
	}else if(request.getAttribute("roe_nexttime") != null && request.getAttribute("roe_nexttime").equals("有るが未定")){ %>
		有るが未定
				</td>
			</tr>
			<tr>
				<th>次回試験内容</th>
				<td colspan="5"><%=transferCRLT((String)request.getAttribute("roe_nextexam")) %>
				</td>
			</tr>
			<tr>
				<th>準備知識・情報</th>
				<td colspan="5"><%=transferCRLT((String)request.getAttribute("roe_preparation")) %>
				</td>
				<%}else if(request.getAttribute("roe_nexttime")!=null){
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
							<td colspan="5"><%=transferCRLT((String)request.getAttribute("roe_nextexam")) %>
							</td>
						</tr>
						<tr>
							<th>準備知識・情報</th>
							<td colspan="5"><%=transferCRLT((String)request.getAttribute("roe_preparation")) %>
							</td>
						</tr>
						<% } %>	
			</tbody>
		</table>
		<div id="button_print">
			<input type="button" value="" onclick="window.print();"></input>
		</div>

	</div>


</body>
</html>
