<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>

<!-- ファビコン -->
<link rel="shortcut icon" type="image/x-icon" href="images/icon.ico" />


<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Be-Max情報管理-企業追加-</title>
<script type="text/javascript" src="../js/Subject.js"></script>
<script type="text/javascript" src="../js/Course.js"></script>

<script type="text/javascript" SRC="../js/ajaxzip3.js"></script>
<script type="text/javascript" SRC="../js/enter.js"></script>

<script type="text/javascript" src="../js/jquery1-6-1.js"></script>
<script type="text/javascript" src="../js/jquery.maskedinput-1.3.min.js"></script>
<script type="text/javascript" src="../js/jquery-bangou.js"></script>

<script type="text/javascript" SRC="../js/CreateCompany.js"></script>
<link href="../style2.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="../css/exvalidation.css" />

</head>


<body onload="requestCompanyTrade();">

	<div id="contents_company">

		<noscript>
			<B>このページは、JavaScriptを使用します。<BR>
				お使いのブラウザはJavaScriptが使用できない状態です。
			</B><BR>
		</noscript>

		<form method="post" action="CompanyConf.jsp">

			<h2>企業の新規登録</h2>

			<table>
				<tbody>
					<tr>
						<th>企業の種類 <span class="fontc-2">*</span></th>
						<td><SELECT ID="CompanyTrade"
							onchange="selectCompanyTrade();">
								<OPTION VALUE="">(商号を選択してください)</OPTION>
						</SELECT> <INPUT TYPE="hidden" name="compt_id" ID="compt_id" VALUE="">
							<INPUT TYPE="hidden" name="compt_name" ID="compt_name" VALUE="">
							<INPUT TYPE="hidden" name="compt_position" ID="compt_position"
							VALUE=""></td>
					</tr>

					<tr>
						<th>企業名<span class="fontc-2">*</span></th>
						<td><INPUT ID="Company" NAME="comp_name" TYPE="text"
							onchange="changeCompanyName();" onblur="changeCompanyName();"
							onkeypress="return handleEnter(this, event)"><BR></td>
					</tr>

					<tr>
						<th>郵便番号<span class="fontc-2">*</span></th>
						<td><INPUT type="text" id="zipcode" name="comp_zip" size="10"
							maxlength="7"
							onKeyUp="AjaxZip3.zip2addr(this,'','comp_address','comp_address');"
							onkeypress="return handleEnter(this, event)"><span style="color:#999999">※半角数字のみ</span></td>
					</tr>

					<tr>
						<th>住所<span class="fontc-2">*</span></th>
						<td><INPUT type="text" id="syozai" name="comp_address"
							size="60" maxlength="100"
							onkeypress="return handleEnter(this, event)"></td>
					</tr>
					<tr>
						<th>電話番号<span class="fontc-2">*</span></th>
						<td><INPUT type="text" id="denwa" name="comp_phone01"
							size="10" maxlength="5" onfocus = "erase()"
							onkeypress="return handleEnter(this, event)">-<INPUT
							type="text" id="denwa2" name="comp_phone02" size="10"
							maxlength="4" onfocus = "erase()" onkeypress="return handleEnter(this, event)">-<INPUT
							type="text" name="comp_phone03" id="denwa3" size="10"
							maxlength="4" onfocus = "erase()" onkeypress="return handleEnter(this, event)">
							<div id="numerr" style="color: #FF3333;">
							
							</td>
					</tr>
			</table>

			<div id="button_change">
				<input type=button value="" onClick="history.back()"
					onkeypress="return handleEnter(this, event)"></Input>
			</div>

			<div id="button1">
				<input id="SaveAccount" type="submit" value="" class="button"/>
			</div>

		</form>
	</div>

	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easing.js"></script>
	<script type="text/javascript" src="../js/exvalidation.js"></script>
	<script type="text/javascript" src="../js/exchecker-ja.js"></script>
	<script type="text/javascript">
	<!--
		var validation = $("form")
			.exValidation({
				rules: {
					CompanyTrade: "chkselect",
					Company: "chkrequired",
					zipcode: "chkrequired chkmin7",
					syozai: "chkrequired",
					denwa: "chkrequired chknumonly",
					denwa2: "chkrequired chknumonly",
					denwa3: "chkrequired chknumonly",
				},
				firstValidate: true,
				customClearError:function() {
					chktell();
				}
			});
	-->
	</script>

</body>
</html>