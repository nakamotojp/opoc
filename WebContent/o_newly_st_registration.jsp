<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- ファビコン -->
<link rel="shortcut icon" type="image/x-icon" href="images/icon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<title>新規ユーザー登録ページ</title>
<link type="text/css" rel="stylesheet" href="css/style2.css" />
<link type="text/css" rel="stylesheet" href="css/exvalidation.css" />
<link href="style2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="js/Subject.js"></script>
<script type="text/javascript" src="js/Course.js"></script>
<script type="text/javascript" src="js/enter.js"></script>
<script type="text/javascript">
<!--  		
			function err1(){
				// XMLHttpRequest生成
				// IE7以上の一般的なブラウザでは使用可能
				var xhr = new XMLHttpRequest();
				// XMLHttpRequestのチェック
				if(xhr == null){
					// IE7以前又はXMLHttpRequestに非対応のブラウザ
					alert("通信できませんでした");
					return false;
				}
				var l_id = document.getElementById("l_id").value;
				xhr.open("GET", "api/LoginAjax?l_id=" + l_id, false);
				xhr.send(null);
				var data = eval('(' + xhr.responseText + ')');
				if(data.l_id != "false"){
					alert("使用できないIDです");
					return false;
				}
			}
			-->
		</script>
</head>
<body onload="requestSubject();">
	<img class="bg" src="images/bg-l.gif" alt=""/>
	<div id="container">
		<!-- header/ -->
		<div id="header">
			<h1></h1>
			<a href="o_user_login.jsp"><img src="images/logo.png" name="logo" id="logo" /></a>
		</div>
		<!--/header-->
		<div id="contents">
			<div id="wrap">
				<!--   -->
				<div id="contents_login">
					<form method="post" action="RegisteredConf">
						<!-- 文字コードをUTF-8にすることで出力したときに文字化けしなくなる -->
						<% request.setCharacterEncoding("UTF-8"); %>
						<h2>新規登録</h2>
						<table id="table-login">
							<tr>
								<th>ID　(学籍番号)</th>
								<td><input type="text" id="l_id" name="l_id" size="15"
									maxlength="7" onkeypress="return handleEnter(this, event)"/>
									</td>
							</tr>
							<tr>
								<th>名前</th>
								<td><input type="text" id="u_name" name="u_name" size="15"
									maxlength="15" onkeypress="return handleEnter(this, event)" />
								</td>
							</tr>
							<tr>
								<th>学科</th>
								<td><SELECT ID="Subject" NAME="s_id"
									onchange="deleteCourse();">
										<option value="">(学科を選択してください)</option>
								</SELECT> <INPUT TYPE="hidden" ID="SubjectName" NAME="s_name" VALUE=""></td>
							</tr>
							<tr>
								<th>コース</th>
								<td><SELECT ID="Course" NAME="c_id"
									onchange="deleteCourseYear();">
										<OPTION VALUE="">(コースを選択してください)</OPTION>
								</SELECT> <INPUT TYPE="hidden" ID="CourseName" NAME="c_name" VALUE=""></td>
							</tr>
							<tr>
								<th>学年</th>
								<td><SELECT ID="CourseYear" NAME="u_year">
										<OPTION VALUE="">(学年を選択してください)</OPTION>
								</SELECT></td>
							</tr>
							<tr>
								<th>パスワード</th>
								<td><input type="password" id="l_pass" name="l_pass"
									size="70" maxlength="64"
									onkeypress="return handleEnter(this, event)"/>
									</td>
							</tr>
							<tr>
								<th>パスワード（確認）</th>
								<td><input type="password" id="l_pass2" name="lpass2"
									size="70" maxlength="64"
									onkeypress="return handleEnter(this, event)"/>
									</td>
							</tr>
						</table>
						<div id="button1">
							<input type="submit" value="" class="button"
								onclick="return err1()" />
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
	<!--<script type="text/javascript" src="js/jquery.js"></script>-->
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easing.js"></script>
	<script type="text/javascript" src="js/exvalidation.js"></script>
	<script type="text/javascript" src="js/exchecker-ja.js"></script>
	<script type="text/javascript">
		<!--
		var validation = $("form")
				.exValidation({
				rules: {
					l_id: "chkrequired chknumonly chkmin7",
					u_name: "chkrequired",
					Subject: "chkselect",
					Course: "chkselect",
					CourseYear: "chkselect",
					l_pass: "chkrequired chkmin6",
					l_pass2: "chkrequired chkretype-l_pass",
				},
				FirstValidate:true
			});
		-->
		</script>
</body>
</html>