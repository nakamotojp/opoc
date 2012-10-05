<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="jp.bemax.se.graduation2011.auth.Xss"%>

<%!
Xss xss = new Xss();
%>
<%-- 新規登録  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<!-- ファビコン -->
<link rel="shortcut icon" type="image/x-icon" href="images/icon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta name="description" content="専門学校ビーマックス就職活動支援ページ" />
<meta name="keywords" content="Opoc,ビーマックス,報告書提出,就職活動,報告書検索" />

<title>Opoc</title>
<link href="style2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<img class="bg" src="images/bg-l.gif" alt=""/>
	<div id="container">
		<!-- header/ -->
		<div id="header">
			<h1></h1>
			<a href="o_user_login.jsp"><img src="images/logo.png" name="logo"
				id="logo" /></a>
		</div>
		<!--/header-->
		<div id="contents">
			<div id="wrap">
				<!--   -->
				<div id="contents_login">
					<h2>新規登録</h2>
					<table id="table-login">
						<!-- 文字コードをUTF-8にすることでGetしたときに文字化けしなくなる -->
						<% request.setCharacterEncoding("UTF-8"); %>
						<tr>
							<th>ID (学籍番号)</th>
							<td><%=request.getParameter("l_id") %></td>
						</tr>
						<tr>
							<th>名前</th>
							<td><%=xss.escape(request.getParameter("u_name")) %></td>
						</tr>
						<tr>
							<th>学科</th>
							<td>
								<!-- 学科をもらってくる --> <%=request.getParameter("s_name") %>
							</td>
						</tr>
						<tr>
							<th>コース</th>
							<td>
								<!-- コースをもらってくる --> <%=request.getParameter("c_name") %>
							</td>
						</tr>
						<tr>
							<th>学年</th>
							<td>
								<!-- 学年 --> <%=request.getParameter("u_year") %>年生
							</td>
						</tr>
						<tr>
							<th>パスワード</th>
							<td>
								<!-- パスワード --> <%
	String pass = request.getParameter("l_pass");

	if(pass == null || pass.length() < 6){
		response.sendRedirect("./o_user_login.jsp");
		return;
	}
 
	for(int i=0;i < pass.length();i++){
		out.print("●");
	}
%>
							</td>
						</tr>
					</table>
					<div id="button_change">
						<input type=button value="" onclick="history.back()" />
					</div>
					<form style="display: inline;" action="UserRegister" method="post">
						<!--  hidden -->
						<input type="hidden" name="l_id"
							value=<%=request.getParameter("l_id")%>></input> <input
							type="hidden" name="u_name"
							value="<%=xss.escape(request.getParameter("u_name"))%>"></input> <input
							type="hidden" name="s_id" value=<%=request.getParameter("s_id")%>></input>
						<input type="hidden" name="c_id"
							value=<%=request.getParameter("c_id")%>></input> <input
							type="hidden" name="u_year"
							value=<%=request.getParameter("u_year")%>></input>
						<div id="button_reg">
							<input type="submit" value="" class="button" />
						</div>
					</form>
				</div>
				<div id="footer">
					Copyright(C) 2011 Be-Max WEB SITE All Rights Reserved.<br /> <a
						href="http://nikukyu-punch.com/" target="_blank">Template
						design by Nikukyu-Punch</a>
				</div>
				<!--/footer-->
			</div>
		</div>
		<!--/contents-->
	</div>
	<!--/container-->
</body>
</html>