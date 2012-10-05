<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%--　セッションの取得  --%>
<%	//セッションがあるか調べて、ない場合はroleにnullを入れる
	String role = "";
	role = (String)session.getAttribute("role");
	if(role==null){
		role= "";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<!-- ファビコン -->
	<link rel="shortcut icon" type="image/x-icon" href="images/icon.ico" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<META HTTP-EQUIV="REFRESH" CONTENT="3;URL=
					<%if(role.equals("teacher")){
						out.print("AuthorityNewReportList");
					}else if(role.equals("student")){
						out.print("ReportNewList");
					}else{out.print("o_user_login.jsp");}%>">
		<link href="style2.css" rel="stylesheet" type="text/css" />
		<title>Be-Max情報管理-報告書登録失敗-</title>
	</head>
	<body>
		<div id="contents_company">
			<a href="<%if(role.equals("teacher")){
						out.print("AuthorityNewReportList");
					}else if(role.equals("student")){
						out.print("ReportNewList");
					}else{out.print("o_user_login.jsp");}%>">
					<img src="images/logo2.png" alt="登録が完了しました"/>
			</a>
			<br/>
			<span class="fontc-err">登録に失敗しました。</span>
			<br/>
			<a href="<%if(role.equals("teacher")){
						out.print("AuthorityNewReportList");
					}else if(role.equals("student")){
						out.print("ReportNewList");
					}else{out.print("o_user_login.jsp");}%>">トップページに戻る</a>／<a href="Javascript:history.back()">一つ前のページに戻る</a>
	
		</div>
	</body>
</html>