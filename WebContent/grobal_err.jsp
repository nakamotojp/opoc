<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--�@�Z�b�V�����̎擾  --%>
<%	//�Z�b�V���������邩���ׂāA�Ȃ��ꍇ��role��null������
	String role = "";
	role = (String)session.getAttribute("role");
	if(role==null){
		role= "";
	}
%>
<html>
	<head>
	<!-- �t�@�r�R�� -->
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
			alt="�G���[�y�[�W"></img></a>
			<br />��ϐ\���󂲂����܂��񂪁A�G���[���������܂����B<br />�g�b�v�y�[�W�ւ��ē��v���܂��B
			<br /> �����I�Ƀy�[�W���ړ����Ȃ�����<a href="https://opoc.bemax.jp/o_user_login.jsp">������</a>
		</div>
	</body>
</html>