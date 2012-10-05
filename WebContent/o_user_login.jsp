<%@ page contentType="text/html; charset=UTF-8" %>
<%
	Cookie cookie;
	String ssid = null;
	String role = null;
	String l_id = null;
	String login = null;
	String pageback = null;
	Cookie[] cookary = request.getCookies();
	String fail_login = (String)request.getAttribute("fail_login");
	
	if(fail_login == null || !fail_login.equals("true")){	
		if(cookary != null){
		
			//session_idがcookie[]に存在するかチェックする
			for(int i =0; i < cookary.length; i++){
				if(cookary[i].getName().equals("session_id")){
					ssid = cookary[i].getValue();
					break;
				}
			}
			
			for(int i =0; i < cookary.length; i++){
				if(cookary[i].getName().equals("role")){
					role = cookary[i].getValue();
					break;
				}
			}
			
			for(int i =0; i < cookary.length; i++){
				if(cookary[i].getName().equals("l_id")){
					l_id = cookary[i].getValue();
					break;
				}
			}
			
			for(int i =0; i < cookary.length; i++){
				if(cookary[i].getName().equals("login")){
					login = cookary[i].getValue();
					break;
				}
			}
			
			for(int i =0; i < cookary.length; i++){
				if(cookary[i].getName().equals("pageback")){
					pageback = cookary[i].getValue();
					break;
				}
			}
			
			
			
			if(ssid != null && role != null && l_id != null || login != null){
				//一時的セッション
				session.setAttribute("session_id", ssid);
				session.setAttribute("role", role);
				session.setAttribute("l_id", l_id);
				session.setAttribute("login", login);
				session.setAttribute("pageback", pageback);
				if(role.equals("student")){
					response.sendRedirect("ReportNewList");
					return;
				}else if(role.equals("teacher")){
					response.sendRedirect("AuthorityNewReportList");
					return;
				}
			}
			
			//ログインしているユーザがログインページを開いた場合はリダイレクトする
			String roles = (String)(session.getAttribute("role"));
			String pagebacks = (String)(session.getAttribute("pageback"));
			if(pagebacks != null && roles != null){
				if(pagebacks.equals("OK") && roles.equals("student")){
					response.sendRedirect("ReportNewList");
					return;
				}else if(pagebacks.equals("OK") && roles.equals("teacher")){
					response.sendRedirect("AuthorityNewReportList");
					return;
				}
			}
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
	<head>
		<!-- ユーザートップ -->
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
		<script type="text/javascript">
		<!--
		var r = 0;

			function sec(){
				if(r % 2 == 0){
					alert("※※ご注意※※" + "\n\nこの設定にすると、第三者がこのPCを使った時にログイン出来てしまいますのでご注意ください!");
				}
				//チェックボックスがおされた回数でアラートを判別
				r++;
			}

			/* onMouseOverイベント発生時 */
			function m_over(obj) {
				obj.src="images/login/login_img2.png";
			}

			/* onMouseOutイベントが発生時 */
			function m_out(obj) {
				obj.src="images/login/login_img1.png";
			}

//			history.forward();
			-->
		</script>
	</head>
	<body onload="MM_preloadImages('images/menu_over_01.gif','images/menu_over_02.gif','images/menu_over_03.gif','images/menu_over_04.gif','images/menu_over_05.gif')">
	<img class="bg" src="images/bg-l.gif" alt=""/>
		<div id="container">
			<div id="header">
				<h1></h1>
				<img src="images/logo.png" name="logo" id="logo" />
			</div>
			<!--/header-->
			<div id="contents">
				<div id="wrap">
					<div id="main">
						<div id="bana">
							<img src="images/bana/bana-index.png" alt="報告書をデータベース化"></img>
							<img src="images/bana/bana-index2.png" alt="就職先情報を検索"></img>
						</div>
					</div>
					<!--/main-->
					<div id="sub">
						<h2>ログイン</h2>
						<div id="login">
<%
	String flg = (String)request.getAttribute("fail_login");
	if(flg!=null){
		out.print("<span class='fontc-2'>ログインに失敗しました。</span>");
	}
%>
							<form name=formMain  method="post" action="Login">
								ログインID<br/>
								<input type="text" id="l_id" name="l_id" size="15" maxlength="7" /><br/>
								パスワード<br/>
								<input type="password" id="l_pass" name="l_pass" size="20" maxlength="64"/><br/> 
								<input type="checkbox" name="checkCookie" value="true" onclick="sec()"/>次回から自動的にログイン
								<input type="image" src="images/login/login_img1.png" id="btn" alt="ログイン"
								 onMouseOver="m_over(this)" 
								 onMouseOut="m_out(this)"/>
							</form>
						</div>
						*未登録の方は<a href="o_newly_st_registration.jsp">ユーザー作成</a>へ
					</div>
					<!--/sub-->
					<!--/wrap-->
					<div id="footer">
						Copyright(C) 2011 Be-Max WEB SITE All Rights Reserved.<br />
						<a href="http://nikukyu-punch.com/" target="_blank">Template design by Nikukyu-Punch</a>
					</div>
				<!--/footer-->
				</div>
			<!--/contents-->
			</div>
		<!--/container-->
		</div>
	</body>
</html>
