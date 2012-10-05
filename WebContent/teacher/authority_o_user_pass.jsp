
<%@ page contentType="text/html; charset=UTF-8"%>
<%--　セッションの取得  --%>
<% String u_name = (String)session.getAttribute("u_name");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<!-- ファビコン -->
<link rel="shortcut icon" type="image/x-icon" href="images/icon.ico" />

<!--　就職活動機能一覧トップ -->
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
	function l_idcheck(){
		
		if(document.getElementById("l_id").value.match(/\d/g)){
			return true;
		}
		return false;
	}
	
	//パスワードチェック
	function change(){
		var l_pass = document.formMain.l_pass;
		var l_pass2 = document.formMain.l_pass2;
		if(l_pass.value == l_pass2.value && l_pass != null && l_pass.value.length >= 6){
			l_pass.style.background = '#fff';
			l_pass2.style.background = '#fff';
		}else{
			l_pass.style.background = '#FFB085';
			l_pass2.style.background = '#FFB085';
		}
	}
	
	//全部の項目が埋まっていないとSubmitさせない
	function err1(){
		var l_id = document.formMain.l_id.value;
		if(l_id == "" || l_id.length < 7){
				alert("正しいIDを入力してください");
				return false;
		}else{
			return confirm("送信しますか？");
		}
	}
	
	// 数値のみを入力可能にする
	function numOnly(event) {
		m = String.fromCharCode(event.keyCode);
		if(m.match(/[\d\ba-i`]/)){
			return true;
		}
		return false;
	}
-->
</script>
</head>


<body
	onload="MM_preloadImages('images/menu_over_01.gif','images/menu_over_02.gif','images/menu_over_03.gif','images/menu_over_04.gif','images/menu_over_05.gif','images/menu_over_06_a.gif')">

	<img class="bg" src="images/bg-l.gif" alt=""/>
	<div id="container">


		<div id="header">
			<h1>
				<a onclick="return confirm('ログアウトしますか？')" href="Logout"　>ログアウト</a>
			</h1>
			<a href="AuthorityNewReportList"><img src="images/logo.png"
				name="logo" id="logo" /></a>
			<ul id="menu">
				<li><a href="AuthorityNewReportList"><img
						src="images/menu_01.gif" alt="トップページへ" width="120" height="50"
						id="Image1"
						onmouseover="MM_swapImage('Image1','','images/menu_over_01.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="AuthorityReportList"><img
						src="images/menu_02_a.gif" alt="担当報告書一覧ページへ" width="120"
						height="50" id="Image2"
						onmouseover="MM_swapImage('Image2','','images/menu_over_02_a.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="JoinMeetingList"><img src="images/menu_03.gif"
						alt="企業説明会報告書一覧ページへ" width="120" height="50" id="Image3"
						onmouseover="MM_swapImage('Image3','','images/menu_over_03.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="WrittenExaminationList"><img
						src="images/menu_04.gif" alt="筆記試験報告書一覧ページへ" width="120"
						height="50" id="Image4"
						onmouseover="MM_swapImage('Image4','','images/menu_over_04.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="OralExaminationList"><img
						src="images/menu_05.gif" alt="面接試験報告書一覧ページへ" width="120"
						height="50" id="Image5"
						onmouseover="MM_swapImage('Image5','','images/menu_over_05.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
				<li><a href="ForgetPassword"><img
						src="images/menu_over_06_a.gif" alt="ユーザー情報変更ページへ" width="120"
						height="50" id="Image6"
						onmouseover="MM_swapImage('Image6','','images/menu_over_06_a.gif',1)"
						onmouseout="MM_swapImgRestore()" /></a></li>
			</ul>
		</div>
		<!--/header-->


		<div id="contents">


			<div id="wrap">


				<div id="main">

					<!--　パスワード変更テーブル  -->
					<%String flg = (String)request.getAttribute("flg");

if(flg!=null){
	if(flg.equals("false2")){
		out.print("<span class='fontc-2'>メールが送信できませんでした。</span>");
	}else if(flg.equals("true")){
		out.print("<span class='fontc-2'>メールを送信しました。</span>");
	}
	if(flg.equals("false")){
		out.print("<span class='fontc-2'>そのIDは存在しません。</span>");
	}
}
%>
					<form name="formMain" method="post" action="ForgetPassword"
						onsubmit="return err1();">
						<h2>パスワード変更メールを送る</h2>
						<table id="table-02">
							<tr>
								<th>学籍番号</th>
								<td><input type="text" name="l_id" value="" size="10"
									maxlength="7" onchange="" /></td>
							</tr>
						</table>
						<div id="button_commit">
							<input type="submit" value="" class="button" />
						</div>
						<a href="AuthorityNewReportList"><img
							src="images/bana/o_hunting.png" alt="トップページへ"></img></a>
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
						<li><a href="AuthorityReportList" class="o_authority_full"></a></li>
						<li><a href="JoinMeetingList" class="o_authority_report"></a></li>
						<li><a href="WrittenExaminationList"
							class="authority_o_written_e"></a></li>
						<li><a href="OralExaminationList" class="o_authority_oral_e"></a></li>
						<li><a href="ForgetPassword" class="o_authority_jub_user"></a></li>
					</ul>
					<div id="cal">
						<script type="text/javascript">
						<!--
document.write(cal());
						-->
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

	</div>

</body>
</html>
