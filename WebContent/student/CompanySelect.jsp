<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>

<!-- ファビコン -->
<link rel="shortcut icon" type="image/x-icon" href="images/icon.ico" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Be-Max情報管理-企業検索-</title>

<script type="text/javascript" src="../js/newStRegistration.js"></script>
<script type="text/javascript" src="../js/Subject.js"></script>
<script type="text/javascript" src="../js/Course.js"></script>

<script type="text/javascript" SRC="../js/ajaxzip3.js"></script>
<script type="text/javascript" SRC="../js/enter.js"></script>

<script type="text/javascript" SRC="../js/Company.js"></script>

<link href="../style2.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<noscript>
		<B>このページは、JavaScriptを使用します。<BR>
			お使いのブラウザはJavaScriptが使用できない状態です。
		</B><BR>
	</noscript>

	<div id="contents_company">
		<form action="compConf.jsp">
			<% request.setCharacterEncoding("UTF-8"); %>

			<h2>企業の検索</h2>
			<table id="table-company">
				<tbody>

					<tr>
						<th>企業名</th>
						<td><INPUT ID="Company" TYPE="text"
							onkeypress="return handleEnter(this, event)">
							<BUTTON TYPE="button" onclick="requestCompany();">企業検索</BUTTON></td>
					</tr>

					<tr>
						<th>企業一覧</th>
						<td><span ID="CompanyMSG">企業を検索して下さい</span></td>
					</tr>

				</tbody>
			</table>
		</form>
	</div>
</body>
</html>