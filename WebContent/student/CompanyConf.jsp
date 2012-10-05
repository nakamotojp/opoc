<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="jp.bemax.se.graduation2011.auth.Xss"%>

<%!
Xss xss = new Xss();
%>
<% request.setCharacterEncoding("UTF-8"); %>
<%
if(request.getParameter("compt_position") == null){
	response.sendRedirect("../o_user_login.jsp");
	
	return;
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<!-- ファビコン -->
<link rel="shortcut icon" type="image/x-icon" href="images/icon.ico" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Be-Max情報管理-登録確認-</title>

<link href="../style2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/Company.js"></script>
</head>

<body>
	<div id="contents_company">
		<h2>確認</h2>
		<% request.setCharacterEncoding("UTF-8"); %>

		<table>
			<tbody>
				<tr>
					<th>企業の種類</th>
					<td>
						<%
				if(request.getParameter("compt_position").equals("first")){
					out.println(request.getParameter("compt_name"));
    			}
			%> <%= xss.escape(request.getParameter("comp_name")) %> <%
				if(request.getParameter("compt_position").equals("last")){
					out.println(request.getParameter("compt_name"));
    			}
			%>
					</td>
				</tr>

				<tr>
					<th>郵便番号</th>
					<td><%= request.getParameter("comp_zip") %></td>
				</tr>

				<tr>
					<th>住所</th>
					<td><%= xss.escape(request.getParameter("comp_address")) %></td>
				</tr>

				<tr>
					<th>電話番号</th>
					<td><%=request.getParameter("comp_phone01") %> - <%=request.getParameter("comp_phone02") %>
						- <%=request.getParameter("comp_phone03") %></td>
				</tr>
		</table>

		<!-- form name="formMain" action="CompanyFunction" method="post"-->

		<input type="hidden" id="compt_id" name="compt_id"
			value="<%=request.getParameter("compt_id")%>"></input> <input
			type="hidden" id="compt_position" name="compt_position"
			value="<%=request.getParameter("compt_position")%>"></input> <input
			type="hidden" id="comp_name" name="comp_name"
			value="<%=xss.escape(request.getParameter("comp_name"))%>"></input> <input
			type="hidden" id="comp_zip" name="comp_zip"
			value="<%=request.getParameter("comp_zip")%>"></input> <input
			type="hidden" id="comp_address" name="comp_address"
			value="<%=xss.escape(request.getParameter("comp_address"))%>"></input> <input
			type="hidden" id="comp_phone01" name="comp_phone01"
			value="<%=request.getParameter("comp_phone01")%>"></input> <input
			type="hidden" id="comp_phone02" name="comp_phone02"
			value="<%=request.getParameter("comp_phone02")%>"></input> <input
			type="hidden" id="comp_phone03"
			value="<%=request.getParameter("comp_phone03")%>"></input>

		<div id="button_change">
			<input type=button value="" onClick="history.back()"></Input>
		</div>

		<div id="button_reg">
			<input id="SaveAccount" type="submit" value="" class="button"
				onClick="CreateCompany();" />
		</div>

		<!-- /form-->
	</div>
</body>
</html>