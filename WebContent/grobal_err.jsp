<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--　セッションの取得  --%>
<%	//セッションがあるか調べて、ない場合はroleにnullを入れる
	String role = "";
	role = (String)session.getAttribute("role");
	if(role==null){
		role= "";
	}
%>
<html>
	<head>
	<!-- ファビコン -->
		<link rel="shortcut icon" type="image/x-icon" href="https://opoc.bemax.jp/images/icon.ico" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<META HTTP-EQUIV="REFRESH" CONTENT="3;URL=
					<%if(role.equals("teacher")){
						out.print("https://opoc.bemax.jp/AuthorityNewReportList");
					}else if(role.equals("student")){
						out.print("https://opoc.bemax.jp/ReportNewList");
					}else{out.print("o_user_login.jsp");}%>">
		<link href="https://opoc.bemax.jp/style2.css" rel="stylesheet" type="text/css" />
		<title>Opoc</title>
	</head>
	<body>
		<div id="contents_company">
		<a href="https://opoc.bemax.jp/o_user_login.jsp"><img src="https://opoc.bemax.jp/images/logo2.png"
			alt="エラーページ"></img></a>
			<br />大変申し訳ございませんが、エラーが発生しました。<br />トップページへご案内致します。
			<br /> 自動的にページが移動しない方は<a href="https://opoc.bemax.jp/o_user_login.jsp">こちら</a>
		</div>
	</body>
</html>